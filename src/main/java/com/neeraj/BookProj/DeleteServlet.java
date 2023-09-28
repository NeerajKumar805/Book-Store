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

@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet {
    private static final String query = "delete from BOOKdetails where id=?";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //get PrintWriter
        PrintWriter pw = res.getWriter();
        //set content type
        res.setContentType("text/html");
        //get the id of record
        int id = Integer.parseInt(req.getParameter("id"));

        //LOAD jdbc driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        }
        //generate the connection
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///bookReg", "root", "root"); PreparedStatement ps = con.prepareStatement(query);) {
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            pw.println("<body bgcolor=#b6f1b6>");
            if (count == 1) {
                pw.println("<h2>Record is Deleted Successfully</h2>");
            } else {
                pw.println("<h2>Record is not deleted Successfully</h2>");
            }
            pw.print("/body");
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>" + se.getMessage() + "</h2>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>" + e.getMessage() + "</h2>");
        }
        pw.println("<a href='home.html'>Home</a>");
        pw.println("<br>");
        pw.println("<a href='booklist'>Book List</a>");

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}