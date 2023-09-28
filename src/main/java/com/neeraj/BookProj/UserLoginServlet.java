package com.neeraj.BookProj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/userlogin")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("phone");
		String pass = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		RequestDispatcher dis = null;
		
		try (Connection con = DriverManager.getConnection("jdbc:mysql:///bookstore?useSSL=false", "root", "root");
				PreparedStatement pst = con.prepareStatement("SELECT * FROM UINFO WHERE phone=? AND pwd=?");
				)
			{
			pst.setString(1, user);
			pst.setString(2, pass);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				session.setAttribute("yourname", rs.getString("name"));
				session.setAttribute("phoneMove", user);
				dis = request.getRequestDispatcher("existinguser.jsp");
			}
			else {
				request.setAttribute("status", "failed");
				dis = request.getRequestDispatcher("login.jsp");
			}
			dis.forward(request, response);
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
