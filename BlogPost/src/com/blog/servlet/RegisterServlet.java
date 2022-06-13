package com.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.dao.UserDao;
import com.blog.entities.User;
import com.blog.helper.ConnectionProvider;

@WebServlet("/RegisterServlet")
@MultipartConfig
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)
	{
		response.setContentType("text/html");
		try(PrintWriter out=response.getWriter())
		{
			
			//fetch all form data
			String check=request.getParameter("check");
			if(check==null)
			{
				out.println("Please!!! Check the checkbox to proceed further");
				
			}
			
			else
			{
				String name=request.getParameter("user_name");
				String email=request.getParameter("user_email");

				String passwd=request.getParameter("user_password");
				
				String gender=request.getParameter("gender");
				String about=request.getParameter("about");
				
				//create user object and set all data to user object
				
				User u=new User(name,email,passwd,gender,about);
				
				//user daao object
				UserDao dao=new UserDao(ConnectionProvider.getConnection());
				if(dao.saveUser(u))
				{
					out.println("Done");
				}
				else
				{
					out.println("error");
				}
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
