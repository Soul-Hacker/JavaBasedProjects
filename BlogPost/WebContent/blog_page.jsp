   
	<%@page import="com.blog.dao.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.*" %>

<%@page import="com.blog.helper.ConnectionProvider" %>
<%@page errorPage="Error.jsp" %> 

<%@page import="com.blog.entities.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
	User user=(User)session.getAttribute("currentUser");
	if(user==null)
	{
		response.sendRedirect("login.jsp");
	}
 
 
 %>
 <%
	int postid=Integer.parseInt(request.getParameter("post_id"));
 PostDao d=new PostDao(ConnectionProvider.getConnection());
 Post p=d.getPostdetails(postid);
 
 
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><%=p.getPtitle() %></title>
<script src="JS/my.js" type="text/javascript"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
    .banner-background{
        clip-path: polygon(30% 0%, 70% 0%, 100% 0, 100% 87%, 70% 100%, 25% 87%, 0 94%, 0 0);
    }
    body
    {
    background: url(imgs/img-1.jpg);
	
	background-attachment: fixed;
    
    }
    
</style>
</head>
<body>
	
	
	<nav class="navbar navbar-expand-lg navbar-dark primary-background">
        <a class="navbar-brand" href="index.jsp"><span class="fa fa-asterisk"></span>Blogs</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <a class="nav-link" href="#"><span class ="fa fa-bell-o"></span>LearnCode <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"><span class="fa fa-comment"></span>Contacts</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" data-toggle="modal" data-target="#add-post"><span class="fa fa-comment"></span>Post</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="fa fa-cogs"></span>Categories
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="#">Programming Language</a>
                <a class="dropdown-item" href="#">Web Development</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">More..</a>
              </div>
            </li>
           
           
          </ul>
         <ul class="navbar-nav mr-right">
            <li class="nav-item">
                <a class="nav-link" href="#" data-toggle="modal" data-target="#profile-modal"> <span class="fa fa-user-circle"><%=user.getName() %></span></a>
              </li>

            <li class="nav-item">
                <a class="nav-link fa fa-user-plus " href="LogoutServlet">LogOut</a>
              </li>

            </ul>
        </div>
      </nav>
<!-- main content of body -->

	  	<div class="container">
			  <div class="row my-4">
				  <div class="col-md-8 offset-md-2">
					  <div class="card">
						  <div class="card-header">
							  <h4 class="text-center post-title"><%=p.getPtitle()%></h4>
						  </div>
						  <div class="card-body">
						  <%
						  int q=p.getUserId();
						  %>
							  <p class="post-content"><%=p.getPcontent() %></p>
							  <br>
							  <div class="text-center">
								<img class="rounded" src="pics/jaav.png" alt="Card image cap">
							  </div>
							  
							  <br>
							  <div class="post-code">
								<pre><%=p.getPcode() %></pre>
							  </div>
							 
						  </div>
						  <div class="card-footer">
							<a href="#!" onclick="doLike(<%=p.getPid()%>,<%=user.getId()%>)" class="btn btn-outline-primary btn-sm"><i class="fa fa-thumbs-o-up"></i><span>10</span></a>
							<a href="#!" class="btn btn-outline-primary btn-sm"><i class="fa fa-commenting-o"></i><span>10</span></a>
						    <br><br>
							<div class="row my-3">
								<div class="col-md-8">
								<%
								UserDao u=new UserDao(ConnectionProvider.getConnection());
								
								%>
									<p><a href="#!"><%=u.getUserByPost(p.getUserId()).getName() %></a> has posted this post on</p>
								</div>
								<div class="col-md-4">
									<p><%=p.getPdate().toLocaleString() %></p>
								</div>
							</div>
						    
						  </div>
					  </div>
				  </div>
			  </div>
		  </div>


<!-- end of main content -->

	  <div class="modal fade" id="profile-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
		  <div class="modal-content">
			<div class="modal-header primary-background text-white text-center">
			  <h5 class="modal-title" id="exampleModalLabel">TechBlog</h5>
			  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			  </button>
			</div>
			<div class="modal-body">
			  <div class="container text-center">
			  <img src="/Profiles/" class="img-fluid" style="border-radius: 50%;max-width:150px ;">
			  <br>
	
				<h5 class="modal-title mt-3" id="exampleModalLabel"><%=user.getName() %></h5>
				<!-- details -->
				<div id="profile-table">
				  <table class="table">
	
					<tbody>
					  <tr>
						<th scope="row">ID</th>
						<td><%=user.getId() %></td>
					   
					  </tr>
					  <tr>
					  <%
					   String txt=user.getGender();
					   
					  %>
						<th scope="row">Gender</th>
						<td><%=txt.toUpperCase() %></td>
					   
					  </tr>
					  <tr>
						<th scope="row">Email</th>
						<td><%=user.getEmail() %></td>
					   
					  </tr>
					  <tr>
						<th scope="row">Status</th>
						<td><%=user.getAbout() %></td>
					   
					  </tr>
					   <tr>
						<th scope="row">RegisteredDate</th>
						<td><%=user.getDateTime().toString() %></td>
					   
					  </tr>
					  
					</tbody>
				  </table>
				</div>
	
	
				<!-- profile edit -->
				<div id="profile-edit" style="display:none;">
				  <h3 class="mt-2">Edit Your Data</h3>
				  <form action="EditServlet" method="post" enctype="multipart/form-data">
					<table class="table">
					  <tr>
						<td>ID:</td>
						<td><%=user.getId() %></td>
					  </tr>
					  <tr>
						<td>Email:</td>
						
						<td><input type="email" class="form-control" name="user_email" value="<%=user.getEmail()%>"></td>
					  </tr>
					  <tr>
						<td>Name:</td>
						<td><input type="text" class="form-control" name="user_name" value="<%=user.getName()%>"></td>
					  </tr>
					  <tr>
						<td>Password:</td>
						<td><input type="password" class="form-control" name="user_password" value="<%=user.getPassword()  %>"></td>
					  </tr>
	
					  <!-- Confirm password logic apply herer -->
					  <tr>
						<td>Confirm Password:</td>
						<td><input type="text" class="form-control" name="user_cnfpassword" placeholder="Confirm Your Password"></td>
					  </tr>
					 
					  <!-- confirm passwpord ends herer -->
					  <tr>
						<td>Gender:</td>
						<td><%=user.getGender().toUpperCase() %></td>
					  </tr>
					  <tr>
						<td>Status:</td>
						<td><textarea rows="4" class="form-control" name="user_about" placeholder="Please write something about yourself"><%=user.getAbout()%>                      
						  </textarea>
						</td>
					  </tr>
	
					  <tr>
						<td>Select Your Profile</td>
						<td><input type="file" name="user_profile" class="form-control">
						</td>
					  </tr>
	
					</table>
					<div class="container">
					  <button type="submit" class="btn btn-primary">Save</button>
					</div>
	
				  </form>
	
				  
				</div>
				
			  </div>
			</div>
			<div class="modal-footer">
			  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			  <button id="edit-profile" type="button" class="btn btn-primary">Edit</button>
			</div>
		  </div>
		</div>
	  </div>
	
	
	
	  <!-- Button trigger modal -->
	<!-- <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
	  Launch demo modal
	</button> -->
	
	<!-- Modal -->
	<div class="modal fade" id="add-post" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
		<div class="modal-content">
		  <div class="modal-header">
			<h5 class="modal-title" id="exampleModalLabel">Provide the Post Details</h5>
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			  <span aria-hidden="true">&times;</span>
			</button>
		  </div>
		  <div class="modal-body">
			<form id="add-post-form" action="AddPostServlet" method="post" enctype="multipart/form-data">
			  <div class="form-group">
				<div class="form-group">
					<select class="form-control" name="cid" >
				  <option selected disabled >---Select Category---</option>
				   <%
				PostDao postd=new PostDao(ConnectionProvider.getConnection());
				ArrayList<Category>list=postd.getAllCategory();
				for(Category c:list)
				{%>
			   <option value="<%=c.getCid() %>" ><%=c.getName() %></option>
				<%
			   
			   
				}
			   %>
		  
				</select> 
				  
			
				</div>
				<input name="posttitle" type="text"  placeholder="Enter Post Title" class="form-control">
			  </div>
			  <div class="form-group">
				<textarea name="postcontent" class="form-control" style="height: 100px;" placeholder="Enter Your post content here"></textarea>
			  </div>
			  <div class="form-group">
				<textarea name="postcode" class="form-control" style="height: 100px;" placeholder="Enter Your code here (if any)"></textarea>
			  </div>
			  <div class="form-group">
				<input name="postpic" type="file" placeholder="Enter your file here">
			  </div>
			  <div class="container text-center">
				<button type="submit" class="btn btn-primary">Post</button>
			  </div>
			</form>
	
			
		  </div>
		  <div class="modal-footer">
		  </div>
		</div>
	  </div>
	</div>
	  
	  <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
	  
	  
	 
	  <script>
		$(document).ready(function(){
		  let editStatus=false;
		  $('#edit-profile').click(function(){
			
			  if(editStatus==false)
			  {
				  
				  $("#profile-table").hide()
				  $("#profile-edit").show();
				  editStatus=true;
				  $(this).text("Back")
			  }
			  else
			  {
				  $("#profile-table").show()
				  $("#profile-edit").hide();
				  editStatus=false;
				  $(this).text("Edit")
			  }
  
		  })
		});
	  </script>
  
  
	  <!---Add post jaav-->
  
	  <script>
		$(document).ready(function(e)
		{
		  $("#add-post-form").on("submit",function(event)
		  {//This execute when form is submitted
			event.preventDefault();
		 console.log("You have clicked on submit")
			let form=new FormData(this);
			//now requseting to server
  
		  $.ajax({
			url:"AddPostServlet",
			type:'POST',
			data: form,
			success:function(data,textStatus,jqXHR){
			  console.log(data);
  
			  if(data.trim()=='done')
			  {
				swal("Good job!", "Saved Successfully!", "success");
			  }
			  else
			  {
				swal("Error", "Something Went Wrong", "error");
			  }
			
			},
			 error: function (jqXHR, textStatus, errorThrown) {
							  //error..
							  swal("Error", "Something Went Wrong", "error");
						  },
			processData:false,
  
			contentType:false
		  })
  
  
		  })
		})
	  </script>

</body>
</html>