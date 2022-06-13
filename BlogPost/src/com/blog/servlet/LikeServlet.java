package com.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.dao.LikeDao;
import com.blog.helper.ConnectionProvider;

/**
 * Servlet implementation class LikeServlet
 */
@WebServlet("/LikeServlet")
public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)
	{
		response.setContentType("text/html");
		try(PrintWriter out=response.getWriter())
		{
			String operation=request.getParameter("operation");
			out.println(operation);
			int uid=Integer.parseInt(request.getParameter("uid"));
			int pid=Integer.parseInt(request.getParameter("pid"));
			
			LikeDao ldao=new LikeDao(ConnectionProvider.getConnection());
			if(operation.equals("like"))
			{
				boolean f=ldao.like(pid, uid);
				out.println(f);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			
		}
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
