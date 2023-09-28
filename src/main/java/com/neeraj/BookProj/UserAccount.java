package com.neeraj.BookProj;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.catalina.connector.Response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class UserAccount
 */
@WebServlet("/useraccount")
public class UserAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	HttpSession session = req.getSession();
        //get PrintWriter
        PrintWriter pw = res.getWriter();
        //set content type
        res.setContentType("text/html");
        pw.println("<link rel=\"stylesheet\" href=\"bootstrap/css/bootstrap.min.css\">");
        pw.println("<body class='alert alert-info'>");
        pw.println("<div class='w-50 card text-center p-5 m-auto'>");
        //generate the connection
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///bookstore", "root", "root");
        	) {
        	String ph = (String)session.getAttribute("phoneMove");
        	String query = "SELECT name,email,address,city,state,pin,pwd,phone FROM uinfo where phone = "+ph;
        	PreparedStatement ps = con.prepareStatement(query);
        	ResultSet rs = ps.executeQuery();
            pw.println("<h1 class='alert alert-danger'>My Account</h1></br>");
            pw.println("<table class='table table-hover table-striped' align='center'>");
            	rs.next();
            	pw.println("<tr>");
            	pw.println("<th>Name : </th>");
            	pw.println("<td>" + rs.getString(1) + "</td>");
            	pw.println("</tr>");
            	pw.println("<tr>");
            	pw.println("<th>Email : </th>");
            	pw.println("<td>" + rs.getString(2) + "</td>");
            	pw.println("</tr>");
            	pw.println("<tr>");
            	pw.println("<th>Home Address : </th>");
            	pw.println("<td>" + rs.getString(3) + "</td>");
            	pw.println("</tr>");
            	pw.println("<tr>");
            	pw.println("<th>City : </th>");
            	pw.println("<td>" + rs.getString(4) + "</td>");
            	pw.println("</tr>");
            	pw.println("<tr>");
            	pw.println("<th>State : </th>");
            	pw.println("<td>" + rs.getString(5) + "</td>");
            	pw.println("</tr>");
            	pw.println("<tr>");
            	pw.println("<th>Pin : </th>");
            	pw.println("<td>" + rs.getString(6) + "</td>");
            	pw.println("</tr>");
            	pw.println("<tr>");
            	pw.println("<th>Password : </th>");
            	pw.println("<td>" + rs.getString(7) + "</td>");
            	pw.println("</tr>");
            	pw.println("<tr>");
            	pw.println("<th>Phone : </th>");
            	pw.println("<td>" + rs.getString(8) + "</td>");
            	pw.println("</tr>");
            	pw.println("<tr>");
            	pw.println("<th>Edit</th>");
            	pw.println("<td><a class='text-primary text-decoration-none h6' href='edituserdetails?ph="+ph+"'>Edit Profile</a></td>");
            	pw.println("</tr>");
            	pw.println("<tr>");
            	pw.println("</tr>");
            	pw.println("<th>Delete : </th>");
            	pw.println("<td><a class='text-danger text-decoration-none h6' href='deleteuserdetails?ph="+ph+"'>Close My Account</a></td>");
            	pw.println("</tr>");
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
        pw.println("<a href='existinguser.jsp'><button class='btn btn-outline-primary'>Home</button></a>");
        pw.println("<a href='logout'><button class='btn btn-outline-danger'>Logout</button></a>");
        pw.println("</div>");
        pw.println("</div>");
        pw.println("</body>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
