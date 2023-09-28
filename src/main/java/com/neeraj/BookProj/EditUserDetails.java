package com.neeraj.BookProj;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/edituserdetails")
public class EditUserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	private static final String query = "SELECT name, email, pwd, phone FROM uinfo where phone=?";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //get PrintWriter
        PrintWriter pw = res.getWriter();
        HttpSession session = req.getSession();
        //set content type
        res.setContentType("text/html");
        pw.println("<link rel=\"stylesheet\" href=\"bootstrap/css/bootstrap.min.css\">");
        pw.println("<body class='alert alert-info'>");
        pw.println("<div class='w-50 card text-center p-5 m-auto'>");
        
        pw.println("<h1 class='alert alert-danger'>My Account</h1></br>");
        //generate the connection
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///bookstore", "root", "root");
        		) {
            
            String ph = (String)session.getAttribute("phoneMove");
        	String query = "SELECT name,email,address,city,state,pin,pwd,phone FROM uinfo where phone = "+ph;
        	PreparedStatement ps = con.prepareStatement(query);
        	ResultSet rs = ps.executeQuery();
            
            rs.next();
            pw.println("<form action='updateuserdetails?ph="+ph+"' method='post'>");
            pw.println("<table class='table table-hover border border-3' align='center'>");
            pw.println("<tr>");
            pw.println("<td>  Name</td>");
            pw.println("<td><input type='text' name='name' value='" + rs.getString(1) + "'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td>  Email</td>");
            pw.println("<td><input type='text' name='email' value='" + rs.getString(2) + "'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td>  Locality</td>");
            pw.println("<td><input type='text' name='address' value='" + rs.getString(3) + "'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td>  City</td>");
            pw.println("<td><input type='text' name='city' value='" + rs.getString(4) + "'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td>  State</td>");
            pw.println("<td><input type='text' name='state' value='" + rs.getString(5) + "'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td>  Pin</td>");
            pw.println("<td><input type='number' name='pin' value='" + rs.getString(6) + "'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td>  Password</td>");
            pw.println("<td><input type='text' name='pwd' value='" + rs.getString(7) + "'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td>  Phone</td>");
            pw.println("<td><input type='number' name='phone' value='" + rs.getString(8) + "'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td><input type='submit' value='Save'></td>");
            pw.println("<td><input type='reset' value='Cancel'></td>");
            pw.println("</tr>");
            pw.println("</table>");
            pw.println("</form>");
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>" + se.getMessage() + "</h2>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>" + e.getMessage() + "</h2>");
        }
        pw.println("<div class='d-flex justify-content-center gap-5 mt-3'>");
        pw.println("<a href='useraccount'><button class='btn btn-outline-primary'>Go back</button></a>");
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
