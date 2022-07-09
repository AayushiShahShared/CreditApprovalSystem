

<%@page import="com.credapp.entities.Message"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>

        <!--css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/form.css" rel="stylesheet" type="text/css"/>
        <style>
            .banner-background{
                clip-path: polygon(50% 0%, 100% 0, 100% 43%, 100% 75%, 71% 93%, 31% 85%, 0 92%, 0 51%, 0 0);
            }
        </style>
    </head>
    <body>

        <!--navbar-->
        <%@include file="outside_navbar.jsp" %>

        <!--        login form
        -->                <main class="d-flex align-items-center primary-background banner-background " style="height:90vh">
            <div class="container ">
                <div class="row">
                    <div class="col-md-4 offset-md-4">
                        <div class ="card p-10 mb-3 ">
                            <div class="card-header text-center ">
                                <br>
                                <span class="fa fa-user-circle-o fa-3x "></span>
                                <h3 style="text-align: center;">Login</h3>
                            </div>
                            <%
                                Message msg = (Message) session.getAttribute("msg");
                                if (msg != null) {
                            %>
                            <div class = "alert <%= msg.getCssClass()%>" roles="alert">

                                <%= msg.getContent()%>

                            </div>
                            <%
                                    session.removeAttribute("msg");
                                }
                            %>

                            <div class ="card-body">
                                <form action="LoginServlet" method="POST">
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">Username/Company name</label>
                                        <input type="text" required class="form-control" id="company_name" name="company_name" aria-describedby="emailHelp" placeholder="Enter Username">
                                        <small id="emailHelp" class="form-text text-muted"> Enter the company name as your username.</small>
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputPassword1">Password</label>
                                        <input type="password" required class="form-control" id="company_password" name="company_password" placeholder="Enter Password">
                                    </div>
                                    <div class="text-center">
                                        <button type="submit" class="btn primary-background " style="color: white " >Login</button>

                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </main>


        <!--javascript-->
        <script src="js/myjs.js" type="text/javascript"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <!--    <script>
                $(document).ready(function () {
                    alert("document loaded")
                })
            </script>-->
    </body>
</html>
