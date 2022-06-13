package com.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
    	 response.setContentType("text/html");
         try (PrintWriter out = response.getWriter()) {
             /* TODO output your page here. You may use following sample code. */
             out.println("<!DOCTYPE html>");
             out.println("<html>");
             out.println("<head>");
             out.println("<title>Servlet LogoutServlet</title>");
             out.println("</head>");
             out.println("<body>");

             HttpSession s = request.getSession();

             s.removeAttribute("currentUser");

             Message m = new Message("Logout Successfully", "success", "alert-success");

             s.setAttribute("msg1", m);

             response.sendRedirect("login.jsp");

             out.println("</body>");
             out.println("</html>");
    	
    	
         }
	}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}

}
