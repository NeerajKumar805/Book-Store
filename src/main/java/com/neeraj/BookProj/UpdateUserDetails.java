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

@WebServlet("/updateuserdetails")
public class UpdateUserDetails extends HttpServlet {
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
        pw.println("<div class='w-25 card text-center text-success p-5 m-auto mt-5'>");
        
        //get the edit data we want to edit
        String name1 = req.getParameter("name");
        String email1 = req.getParameter("email");
        String address1 = req.getParameter("address");
        String city1 = req.getParameter("city");
        String state1 = req.getParameter("state");
        String pin1 = req.getParameter("pin");
        String pwd1 = req.getParameter("pwd");
        String phone1 = req.getParameter("phone");
        
        String ph = (String)session.getAttribute("phoneMove");
        
        String query = "UPDATE uinfo SET name=?,email=?,address=?,city=?,state=?,pin=?,pwd=?,phone=? where phone = ?";
        
        //generate the connection
        
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///bookstore", "root", "root");
        	PreparedStatement ps = con.prepareStatement(query);) {
            ps.setString(1, name1);
            ps.setString(2, email1);
            ps.setString(3, address1);
            ps.setString(4, city1);
            ps.setString(5, state1);
            ps.setString(6, pin1);
            ps.setString(7, pwd1);
            ps.setString(8, phone1);
            ps.setString(9, ph);
            int count = ps.executeUpdate();
            if (count > 0) {
                pw.println("<h5>Edited Successfully</h5>");
//                res.sendRedirect("existinguser.jsp");
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
        String Updatedphone = req.getParameter("phone");
        session.setAttribute("phoneMove", phone1);
        session.setAttribute("yourname", name1);
        pw.println("<div class='d-flex justify-content-center gap-5 mt-3'>");
        pw.println("<a href='useraccount?Updatedphone="+Updatedphone+"'><button class='btn btn-outline-primary'>My Account</button></a>");
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
