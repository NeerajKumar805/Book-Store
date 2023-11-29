package com.neeraj.BookProj;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/booklist")
public class BookList extends HttpServlet {
	private static final String query = "SELECT ID,BOOKNAME,BOOKEDITION,BOOKPRICE FROM BOOKDETAILS";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		// get PrintWriter
		PrintWriter pw = res.getWriter();
		// set content type
		res.setContentType("text/html");
		pw.println("<link rel=\"stylesheet\" href=\"bootstrap/css/bootstrap.min.css\">");
		pw.println("<body class='alert alert-info'>");
		pw.println("<div class='w-50 card text-center p-5 m-auto'>");
		// generate the connection
		try (Connection con = DriverManager.getConnection("jdbc:mysql:///bookstore", "root", "root");) {
			String query = "SELECT id,bookname,bookedition,bookprice FROM bookdetails";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			pw.println("<h1 class='alert alert-danger'>Book List</h1></br>");
			pw.println("<table class='table table-hover table-striped text-center' align='center'>");
			pw.println("<tr>");
			
			pw.println("<th>Book Id</th>");
			pw.println("<th>Book Name</th>");
			pw.println("<th>Book Edition</th>");
			pw.println("<th>Book Price</th>");
			pw.println("<th>Edit</th>");
			pw.println("<th>Delete</th>");
			
			pw.println("</tr>");
			while (rs.next()) {

				pw.println("<tr>");

				pw.println("<td>" + rs.getInt(1) + "</td>");
				pw.println("<td>" + rs.getString(2) + "</td>");
				pw.println("<td>" + rs.getString(3) + "</td>");
				pw.println("<td>" + rs.getFloat(4) + "</td>");
				pw.println("<td><a class='text-primary text-decoration-none h6' href='editScreen?id=" + rs.getInt(1)
						+ "'>Edit</a></td>");
				pw.println("<td><a class='text-danger text-decoration-none h6' href='deleteurl?id=" + rs.getInt(1)
						+ "'>Delete</a></td>");
				pw.println("</tr>");
			}
			pw.println("</table>");
			pw.print("</body>");
		} catch (SQLException se) {
			se.printStackTrace();
			pw.println("<h1>" + se.getMessage() + "</h2>");
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>" + e.getMessage() + "</h2>");
		}
		pw.println("<div class='d-flex justify-content-center gap-5 mt-3'>");
		pw.println("<a href='addbook.jsp'><button class='btn btn-outline-primary'>Add Book</button></a>");
		pw.println("<a href='logout'><button class='btn btn-outline-danger'>Logout</button></a>");
		pw.println("<a href='existinguser.jsp'><button class='btn btn-outline-primary'>Home</button></a>");
		pw.println("</div>");
		pw.println("</div>");
		pw.println("</body>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}