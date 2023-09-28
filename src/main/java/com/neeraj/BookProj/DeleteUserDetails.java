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
import jakarta.servlet.http.HttpSession;

@WebServlet("/deleteuserdetails")
public class DeleteUserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	private static final String query = "UPDATE uinfo SET name=?,email=?,pwd=?,phone=?";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //get PrintWriter
        PrintWriter pw = res.getWriter();
        HttpSession session = req.getSession();
      //set content type
        res.setContentType("text/html");
        pw.println("<link rel=\"stylesheet\" href=\"bootstrap/css/bootstrap.min.css\">");
        pw.println("<body class='bg-info'>");
        pw.println("<div class='w-50 card text-center text-success p-5 m-auto mt-5'>");
        String ph = (String)session.getAttribute("phoneMove");
        
        String query = "delete from uinfo where phone = "+ph;
        
        //generate the connection
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///bookstore", "root", "root");
        	PreparedStatement ps = con.prepareStatement(query);) {
            
            int count = ps.executeUpdate();
            if (count > 0) {
                pw.println("<h5>Account deleted successfully</h5>");
            } else {
                pw.println("<h5>Something went wrong!!!</h5>");
            }
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>" + se.getMessage() + "</h1>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>" + e.getMessage() + "</h1>");
        }
        pw.println("<div class='d-flex justify-content-center gap-5 mt-3'>");
        pw.println("<a href='logout'><button class='btn btn-outline-danger'>Home</button></a>");
        pw.println("<a href='login.jsp'><button class='btn btn-outline-primary'>Login</button></a>");
        pw.println("<a href='registration.jsp'><button class='btn btn-outline-warning'>Create new</button></a>");
        pw.println("</div>");
        pw.println("</div>");
        pw.println("</body>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
