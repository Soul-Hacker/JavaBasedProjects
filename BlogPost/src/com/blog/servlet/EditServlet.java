package com.blog.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.blog.dao.UserDao;
import com.blog.entities.Message;
import com.blog.entities.User;
import com.blog.helper.ConnectionProvider;
import com.blog.helper.Helper;

@WebServlet("/EditServlet")
@MultipartConfig
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)
	{
		response.setContentType("text/html");
		try(PrintWriter out=response.getWriter())
		{
			   out.println("<!DOCTYPE html>");
	            out.println("<html>");
	            out.println("<head>");
	            out.println("<title>Servlet EditServlet</title>");
	            out.println("</head>");
	            out.println("<body>");
			
			String userEmail=request.getParameter("user_email");
			String userName=request.getParameter("user_name");
			String userPassword=request.getParameter("user_password");
			String userAbout=request.getParameter("user_about");
			Part part=request.getPart("user_profile");
			String proName=part.getSubmittedFileName();
			
			//get the user from the session
			
			HttpSession s=request.getSession();
			User user=(User) s.getAttribute("currentUser");
			user.setEmail(userEmail);
			user.setName(userName);
			user.setPassword(userPassword);
			user.setAbout(userAbout);
			String oldFile=user.getProfile();
			user.setProfile(proName);
			
			//updating db
			UserDao userdao=new UserDao(ConnectionProvider.getConnection());
			boolean ans=userdao.updateUser(user);
			if(ans==true)
			{
//				 String path = request.getRealPath("/") + "pics" + File.separator + user.getProfile();

				String path="D:\\Projects\\BlogPost\\WebContent\\Profiles"+File.separator+user.getProfile();
			//deletefile
				String Oldpath="D:\\Projects\\BlogPost\\WebContent\\Profiles"+File.separator+oldFile;
				
				Helper.deleteFile(Oldpath);
				
					if(Helper.saveFile(part.getInputStream(), path))
					{
						out.println("Profile Updated");
						Message msg=new Message("Profile Details Updated Successfully ","success","alert-success");
					//	HttpSession s=request.getSession();
						s.setAttribute("msg1", msg);
					//	response.sendRedirect("login.jsp");
						
					}
					else
					{
						out.println("Not updated");
						Message msg=new Message("Something went Wrong try again","error","alert-danger");
						//HttpSession s=request.getSession();
						s.setAttribute("msg1", msg);
				//		response.sendRedirect("login.jsp");
						
					}
				
			}
			else
			{
				out.println("Not updated");
				Message msg=new Message("Something went Wrong try again","error","alert-danger");
				//HttpSession s=request.getSession();
				s.setAttribute("msg1", msg);
				
				
			}
			response.sendRedirect("profile.jsp");
			out.println("</body>");
			out.println("</html>");
			
			
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
