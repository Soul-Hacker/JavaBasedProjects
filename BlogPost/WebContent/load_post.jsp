<%@page import="java.util.*" %>
<%@page import="com.blog.dao.*" %>
<%@page import="com.blog.helper.ConnectionProvider" %>

<%@page import="com.blog.entities.*" %>
<div class="row">

<%
//Thread.sleep(1000);
User uuu=(User)session.getAttribute("currentUser");
	PostDao d=new PostDao(ConnectionProvider.getConnection());
int c=Integer.parseInt(request.getParameter("cid"));
List<Post>post=null;

if(c==0){


	post=d.getAllPost();
}
else{
	
	post=d.getPostById(c);
}
if(post.size()==0)
{
	out.println("<h3 class='display-3 text-center'>No Post Regarding This Technology...</h3");
	  
	return;
}

	for(Post p:post)
	{
%>
	<div class="col-md-6 mt-2">
        <div class="card">
        <img class="card-img-top" src="pics/jaav.png" alt="Card image cap">
            <div class="card-body">
                <b><%=p.getPtitle()%></b>
                <p><%=p.getPcontent()%></p>
               
              
                
             </div>
             <div class="card-footer">
                 <a href="blog_page.jsp?post_id=<%=p.getPid() %>" class="btn btn-outline-primary btn-sm">Read More...</a>
                 
                    <a href="#!" onclick="doLike(<%=p.getPid()%>,<%=uuu.getId()%>)" class="btn btn-outline-primary btn-sm"><i class="fa fa-thumbs-o-up"></i><span>10</span></a>
                    <a href="#!" class="btn btn-outline-primary btn-sm"><i class="fa fa-commenting-o"></i><span>10</span></a>
                    
                    <br>
                       <p><%=p.getPdate().toLocaleString()%></p>
                   
                 
             </div>
        </div>


    </div>
<%
	}
%>
</div>