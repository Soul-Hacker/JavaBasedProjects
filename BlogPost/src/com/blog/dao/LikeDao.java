package com.blog.dao;

import java.sql.*;

import com.blog.helper.ConnectionProvider;

public class LikeDao {
	Connection con;
	
	
	public LikeDao(Connection con) {
		super();
		this.con = con;
	}


	public boolean like(int pid,int uid)
	{
		boolean f=false;
		try
		{
			String q="insert into liked(pid,uid) values(?,?)";
			PreparedStatement p=this.con.prepareStatement(q);
			p.setInt(1, pid);
			p.setInt(2, uid);
			p.executeUpdate();
			f=true;
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return f;
	}
	public int  countlike(int pid) {
		int count=0;
		try
		{
			String q="select count(*) from liked where pid=?";
			PreparedStatement p=this.con.prepareStatement(q);
			p.setInt(1, pid);
			ResultSet rs=p.executeQuery();
			if(rs.next())
			{
				count=rs.getInt("count(*)");
			}
			
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return count;
		
	}
	public boolean isliked(int pid,int uid)
	{
		boolean f=false;
		try
		{
			String q="select * from liked where pid=? and uid=? ";
			PreparedStatement p=this.con.prepareStatement(q);
			p.setInt(1, pid);
			p.setInt(2,uid);
			ResultSet rs=p.executeQuery();
			if(rs.next())
			{
				f=true;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return f;
	}
	public boolean dislike(int pid,int uid)
	{
		boolean f=false;
		try
		{
			String q="delete from liked where pid=? and uid=?";
			PreparedStatement p=this.con.prepareStatement(q);
			p.setInt(1, pid);
			p.setInt(2, uid);
			p.executeUpdate();
			f=true;
		}
		catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return f;
	}
}
