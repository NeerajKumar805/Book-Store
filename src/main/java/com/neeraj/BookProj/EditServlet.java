package com.neeraj.BookProj;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
    private static final String query = "UPDATE BOOKdetails SET BOOKNAME=?,BOOKEDITION=?,BOOKPRICE=? WHERE ID=?";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //get PrintWriter
        PrintWriter pw = res.getWriter();
        //set content type
        res.setContentType("text/html");
        pw.println("<link rel=\"stylesheet\" href=\"bootstrap/css/bootstrap.min.css\">");
        pw.println("<body class='bg-info'>");
        pw.println("<div class='w-25 card text-center text-success p-5 m-auto mt-5'>");
        //get the id of record
        int id = Integer.parseInt(req.getParameter("id"));
        //get the edit data we want to edit
        String bookName = req.getParameter("bookName");
        String bookEdition = req.getParameter("bookEdition");
        float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
        //LOAD jdbc driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        }
        //generate the connection
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///bookStore", "root", "root"); 
        	PreparedStatement ps = con.prepareStatement(query);) {
            ps.setString(1, bookName);
            ps.setString(2, bookEdition);
            ps.setFloat(3, bookPrice);
            ps.setInt(4, id);
            int count = ps.executeUpdate();
            if (count == 1) {
                pw.println("<h5>Updated Successfully...</h5>");
            } else {
                pw.println("<h5>Oops, Something went wrong!!!</h5>");
            }
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>" + se.getMessage() + "</h2>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>" + e.getMessage() + "</h2>");
        }
        pw.println("<div class='d-flex justify-content-center gap-5 mt-3'>");
        pw.println("<a href='booklist'><button class='btn btn-outline-primary'>Book list</button></a>");
        pw.println("<a href='existinguser.jsp'><button class='btn btn-outline-danger'>Home</button></a>");
        pw.println("</div>");
        pw.println("</div>");
        pw.println("</body>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}