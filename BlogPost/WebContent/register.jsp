<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
    .banner-background{
        clip-path: polygon(30% 0%, 70% 0%, 100% 0, 100% 90%, 70% 100%, 26% 93%, 0 98%, 0 0);
    }
</style>
</head>
<body>
<%@include file="navbar.jsp" %>
    <main class="primary-background p-5 banner-background ">
        <div class="container">
            <div class="col-md-6 offset-md-3">
                <div class="card">
                    <div class="card-header text-center primary-background text-white">
                        <span class=" fa fa-3x fa-user-circle"></span>
                        <br>
                        Register Here

                    </div>
                    <div class="card-body">
                        <form id="reg-form" action="RegisterServlet" method="post">

                            
                            <div class="form-group">
                                <label for="user_name">User Name</label>
                                <input name="user_name" type="text" class="form-control" id="user_name" aria-describedby="emailHelp" placeholder="Enter Your Name">
                                
                              </div>



                            <div class="form-group">
                              <label for="exampleInputEmail1">Email address</label>
                              <input name="user_email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                              <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                            </div>
                            <div class="form-group">
                              <label for="exampleInputPassword1">Password</label>
                              <input name="user_password" type="password" class="form-control" id="exampleInputPassword1">
                            </div>
                            
                            <div class="form-group">
                                <label for="gender">Select Gender</label>
                                <br>
                                <input type="radio"  id="gender" name="gender" value="male">Male
                                <input type="radio"  id="gender"name="gender" value="female">Female
                              </div>
                              <div class="form-group">
                                  <textarea name="about" class="form-control" id="" rows="5" placeholder="Enter something about yourself" ></textarea>
                              </div>
                            <div class="form-group form-check">
                              <input name="check" type="checkbox" class="form-check-input" id="exampleCheck1">
                              <label class="form-check-label" for="exampleCheck1">agree terms and conditions</label>
                            </div>
                            <br>
                            <div class="container text-center" id="loader" style="display: none;">
                                <span class="fa fa-refresh fa-spin fa-4x"></span>
                                <h4>Please Wait...</h4>
                            </div>
                            <button type="submit" id="submit-btn" class="btn btn-primary">SignUp</button>
                          </form>

                    </div>
                    <div class="card-footer"></div>
                </div>

            </div>
        </div>
    </main>












    <!-- scripting -->


    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="JS/my.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <script>
            $(document).ready(function () {
                console.log("loaded........")
                $('#reg-form').on('submit', function (event) {
                    event.preventDefault();
                    let form = new FormData(this);
                    $("#submit-btn").hide();
                    $("#loader").show();
                    //send register servlet:
                    $.ajax({
                        url: "RegisterServlet",
                        type: 'POST',
                        data: form,
                        success: function (data, textStatus, jqXHR) {
                            console.log(data)
                            $("#submit-btn").show();
                            $("#loader").hide();
                            if (data.trim() === 'Done')
                            {
                                swal("Registered successfully..We are going to redirect you to the login page").then((value) => {
                                            window.location ="login.jsp";
                                            // window.location.replace("http://stackoverflow.com");
                                        });
                            } else
                            {
                                swal(data);
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#submit-btn").show();
                            $("#loader").hide();
                            swal("something went wrong..try again");
                        },
                        processData: false,
                        contentType: false
                    });
                });
            });
        </script>
    

</body>
</html>