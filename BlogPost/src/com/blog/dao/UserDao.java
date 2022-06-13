package com.blog.dao;
import java.sql.*;

import com.blog.entities.User;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import com.mysql.cj.protocol.Resultset;

public class UserDao {
	private Connection con;

	public UserDao(Connection con) {
		
		this.con = con;
	}
	
	//method to insert data of database
	
	//Creating a user
	
	public boolean saveUser(User user)
	{
		//This method will insert the details to the user table except for the primary key
		boolean b=false;
		try
		{
		
			String q="insert into user(name,email,password,gender,about) values(?,?,?,?,?)";
			PreparedStatement pt =this.con.prepareStatement(q);
			pt.setString(1, user.getName());
			pt.setString(2, user.getEmail());
			pt.setString(3, user.getPassword());
			pt.setString(4, user.getGender());
			pt.setString(5, user.getAbout());
			pt.executeUpdate();
			b=true;
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return b;
	}
	
	// After Successfully registeration user can login to there account by giving therer user name and password
	public User getuserbyemail(String email,String password)
	{
		User user=null;
		try
		{
			String query="select * from user where email =? and password =?";
			PreparedStatement p=con.prepareStatement(query);
			p.setString(1, email);
			p.setString(2, password);
			Resultset res=(Resultset) p.executeQuery();
			if(((ResultSet) res).next())
			{
				user=new User();
			//GETTING DATA FROM DB
				String name=((ResultSet) res).getString("name");
			//SETTING DATA TO USER OBJECT
				user.setName(name);
				user.setId(((ResultSet) res).getInt("id"));
				user.setEmail(((ResultSet) res).getString("email"));
				user.setPassword(((ResultSet) res).getString("password"));
				user.setGender(((ResultSet) res).getString("gender"));
				user.setAbout(((ResultSet) res).getString("about"));
				user.setDateTime(((ResultSet) res).getTimestamp("rdate"));
				user.setProfile(((ResultSet) res).getString("profile"));
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return user;
	}

	//Updating user details 
	public boolean updateUser(User user)
	{
		boolean f=false;
	try
	{
		String q="update user set name=?,email=?,password=?,gender=?,about=?,profile=? where id=?";
		PreparedStatement p=con.prepareStatement(q);
		p.setString(1, user.getName());
		p.setString(2, user.getEmail());
		p.setString(3, user.getPassword());
		p.setString(4, user.getGender());
		p.setString(5, user.getAbout());
		p.setString(6, user.getProfile());
		p.setInt(7, user.getId());
		p.executeUpdate();
		f=true;;
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return f;
	}
	
	//This is to get user by the post that is it will give the name of the user for the given post
	public User getUserByPost(int userid)
	{
		User user=null;
		
		
		try {
			String q="select * from user where id=?";
			PreparedStatement pt=this.con.prepareStatement(q);
			
			pt.setInt(1, userid);
			ResultSet res=pt.executeQuery();
			if(res.next())
			{
				user=new User();
				//GETTING DATA FROM DB
					String name=res.getString("name");
					System.out.println(name);
				//SETTING DATA TO USER OBJECT
					user.setName(name);
					user.setId(res.getInt("id"));
					user.setEmail(res.getString("email"));
					user.setPassword(res.getString("password"));
					user.setGender(res.getString("gender"));
					user.setAbout(res.getString("about"));
					user.setDateTime(res.getTimestamp("rdate"));
//					user.setProfile(res.getString("profile"));
				
		}} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return user;
		
	}
}
