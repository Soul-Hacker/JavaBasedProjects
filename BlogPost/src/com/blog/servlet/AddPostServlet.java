package com.blog.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.blog.dao.PostDao;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.helper.ConnectionProvider;
import com.blog.helper.Helper;


/**
 * Servlet implementation class AddPostServlet
 */
@WebServlet("/AddPostServlet")
@MultipartConfig
public class AddPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)
	{
		response.setContentType("text/html");
		try(PrintWriter out=response.getWriter())
		{
			String ptitle=request.getParameter("posttitle");
			//out.println(ptitle);
			String pcontent=request.getParameter("postcontent");
		//	out.println(pcontent);
			String pcode=request.getParameter("postcode");
			//out.println(pcode);
				
//			Part p=request.getPart("postpic");
//			String loc=p.getSubmittedFileName();
//			
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("currentUser");
			int m=user.getId();
			//out.println(m);
			Part part = request.getPart("postpic");
//			InputStream fileInputStream = part.getInputStream();
//			File fileToSave = new File("WebContent/pics/" + part.getSubmittedFileName());
//			Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
			 //out.println(part.getSubmittedFileName());
			 
			int cid=Integer.parseInt(request.getParameter("cid"));
			//out.println(cid);
			//int iD;
//			try {
//				int iD = Integer.parseInt(id);
////				int iD=new Integer(id).intValue();
////				DecimalFormat d=new DecimalFormat("#");
////				int iD=d.parse(id).intValue();
//				out.println(iD);
//			}
//			catch(Exception e)
//			{
//				out.println("ERror in catid");
//				return
//			}
			int id=4;
			Post post=new Post(ptitle,pcontent,pcode,m,cid,part.getSubmittedFileName(),null);
			PostDao pdao=new PostDao(ConnectionProvider.getConnection());
			if(pdao.savePost(post))
			{
				String pathpic="D:\\Projects\\BlogPost\\WebContent\\pics"+File.separator+part.getSubmittedFileName();
			//out.println(pathpic);
		
//				Path of folder saving in my pc
//				C:\Users\Soul Hacker\eclipse-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\BlogPost\pics
//				
				InputStream is=part.getInputStream();
				Helper.saveFile(part.getInputStream(), pathpic);
				out.println("done");
				
//				boolean suc=Helper.saveFile(part.getInputStream(), pathpic);
//				if(suc)
//				{
//					
//					out.println("Up");
//					
//					out.println(pathpic);
//					out.println("Done");
//				}
//				else
//				{
//					out.print("dp");
//				}
				
			}
			else
			{
				out.println("Error");
			}
//		
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
