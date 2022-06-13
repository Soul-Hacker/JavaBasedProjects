package com.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blog.dao.UserDao;
import com.blog.entities.Message;
import com.blog.entities.User;
import com.blog.helper.ConnectionProvider;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
    	//fetch user name and password from request
    	String userEmail=request.getParameter("email");
    	String userPassword=request.getParameter("password");
    	UserDao dao=new UserDao(ConnectionProvider.getConnection());
    	User u=dao.getuserbyemail(userEmail, userPassword);

		response.setContentType("text/html");
		try(PrintWriter out=response.getWriter())
		{
			if(u==null)
			{
			//	out.println("invlaid login details");
				Message msg=new Message("Invalid Details!! try again","error","alert-danger");
				HttpSession s=request.getSession();
				s.setAttribute("msg1", msg);
			// if credential fails to match then redirect it to login.jsp;
				response.sendRedirect("login.jsp");
				
			}
			else
    		{
    		//storing userdata into session
    		HttpSession s=request.getSession();
    		
    		s.setAttribute("currentUser", u);
    		// if credential  match then redirect it to profile.jsp;
    		response.sendRedirect("profile.jsp");
    		}
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}	
    	
    	
	
	}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}


}
