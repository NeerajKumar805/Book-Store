package com.neeraj.BookProj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class UserRegServlet
 */
@WebServlet("/userreg")
public class UserRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		PreparedStatement pst = null;
		RequestDispatcher dis = null;
		HttpSession session = request.getSession();
		String uname = request.getParameter("name");
		String uemail = request.getParameter("email");
		String uadd = request.getParameter("address");
		String ucity = request.getParameter("city");
		String ustate = request.getParameter("state");
		String upin = request.getParameter("pin");
		String upwd = request.getParameter("pwd");
		String uphone = request.getParameter("phone");
		
		try {
			con = DriverManager.getConnection("jdbc:mysql:///bookstore?useSSL=false", "root", "root");
			
			if (con != null)
				pst = con.prepareStatement("INSERT INTO UINFO VALUES(id,?,?,?,?,?,?,?,?)");
			if (pst != null) {
				pst.setString(1, uname);
				pst.setString(2, uemail);
				pst.setString(3, uadd);
				pst.setString(4, ucity);
				pst.setString(5, ustate);
				pst.setString(6, upin);
				pst.setString(7, upwd);
				pst.setString(8, uphone);
			}
			int rowCount = pst.executeUpdate();
			dis = request.getRequestDispatcher("registration.jsp");
			if (rowCount > 0)
				request.setAttribute("status", "success");
			else
				request.setAttribute("status", "failed");
			dis.forward(request, response);
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
