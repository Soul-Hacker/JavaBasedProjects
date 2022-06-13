package com.blog.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.sun.jdi.request.ClassPrepareRequest;


public class PostDao {
	Connection con;

	public PostDao(Connection con) {
		
		this.con = con;
	}
	public ArrayList<Category> getAllCategory()
	{
		ArrayList<Category>list=new ArrayList<Category>();
		try
		{
			String q="select * from categories";
			Statement st=this.con.createStatement();
			ResultSet rs=st.executeQuery(q);
			while(rs.next())
			{
				int cid=rs.getInt("cid");
				String name=rs.getString(2);
				String desc=rs.getString(3);
				Category c=new Category(cid,name,desc);
				list.add(c);
				System.out.print("id ="+rs.getString(1)+" ");
				System.out.print("name ="+rs.getString(2)+" ");
				System.out.print("desc ="+rs.getString(3)+" ");
				System.out.println();
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
//		for(int i=0;i<list.size();i++)
//		{
//			System.out.println(list.get(i));
//		}
		return list;
		
	}
	  public boolean savePost(Post p) {
	        boolean f = false;
	        try {

	           String q="insert into post(pTitle,pContent,pCode,user,catid,pic) values(?,?,?,?,?,?)";
	            PreparedStatement pstmt = this.con.prepareStatement(q);
	            pstmt.setString(1, p.getPtitle());
	            pstmt.setString(2, p.getPcontent());
	            pstmt.setString(3, p.getPcode());
//	            
//	           
	            pstmt.setInt(4, p.getUserId());
	            pstmt.setInt(5,p.getCatid());
	            pstmt.setString(6, p.getPpic());
	            pstmt.executeUpdate();
	            f = true;


	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        return f;
	    }
	  public List<Post>getAllPost()
	  {
		  //fetching all the post
		  List<Post>list=new ArrayList<>();
		  try
		  {			  PreparedStatement p=this.con.prepareStatement("select * from post order by pDate desc");
			  ResultSet rs=p.executeQuery();
			  while(rs.next())
			  {
				  int Pid=rs.getInt("pid");
				  String Title=rs.getString("pTitle");
				  String Content=rs.getString("pContent");
				  String Code=rs.getString("pCode");
				  String Pic=rs.getString("pic");
				  Timestamp Date=rs.getTimestamp("pDate");
				  int cat=rs.getInt("catid");
				  int uid=rs.getInt("user");
//				  int pid, String ptitle, String pcontent, String pcode, String ppic, int catid,int userId,Timestamp pdate
				  Post post=new Post(Pid,Title,Content,Code,Pic,cat,uid,Date);
				  list.add(post);
			  
			  }
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  return list;
	  }
	  public List<Post>getPostById(int cid)
	  {
		  List<Post>list=new ArrayList<>();
		  try
		  {			  
			  PreparedStatement p=this.con.prepareStatement("select * from post where catid=?");
			  p.setInt(1, cid);
			  ResultSet rs=p.executeQuery();
			  while(rs.next())
			  {
				  int Pid=rs.getInt("pid");
				  String Title=rs.getString("pTitle");
				  String Content=rs.getString("pContent");
				  String Code=rs.getString("pCode");
				  String Pic=rs.getString("pic");
				  Timestamp Date=rs.getTimestamp("pDate");
				  //int cat=rs.getInt("catid");
				  int uid=rs.getInt("user");
//				  int pid, String ptitle, String pcontent, String pcode, String ppic, int catid,int userId,Timestamp pdate
				  Post post=new Post(Pid,Title,Content,Code,Pic,cid,uid,Date);
				  list.add(post);
			  
			  }
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  return list;

		  
		  
	  }
	  
	  public Post getPostdetails(int postid) throws SQLException
	  {
		  Post post=null;
		  String q="select * from post where pid=?";
		  try {
		  PreparedStatement pts=this.con.prepareStatement(q);
		  pts.setInt(1, postid);
		  ResultSet rs=pts.executeQuery();
		  if(rs.next())
		  {
			 
			  int Pid=rs.getInt("pid");
			  String Title=rs.getString("pTitle");
			  String Content=rs.getString("pContent");
			  String Code=rs.getString("pCode");
			  String Pic=rs.getString("pic");
			  Timestamp Date=rs.getTimestamp("pDate");
			  int cid=rs.getInt("catid");
			  int uid=rs.getInt("user");
//			  int pid, String ptitle, String pcontent, String pcode, String ppic, int catid,int userId,Timestamp pdate
			   post=new Post(Pid,Title,Content,Code,Pic,cid,uid,Date);
			  
		  }
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  
		return post;
	  }

}
