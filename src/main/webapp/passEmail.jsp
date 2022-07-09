<%--
    Document   : email
    Created on : 27-May-2022, 6:47:42 PM
    Author     : Aayushi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Email Page</title>

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
                                <h3 style="text-align: center;">Confirm Email Id</h3>
                            </div>
                            <div class ="card-body">

                                <form id="email-confirm-form" action="PassEmailServlet" method="POST">

                                    <div class="form-group">
                                        <label for="exampleInputEmail1" size="18">Email Id : </label>
                                        <input type="text" class="form-control" id="company_email" name ="company_email" aria-describedby="emailHelp" placeholder="Enter Email Id">
                                        <!--<small id="Help" class="form-text text-muted">Enter your verification code </small>-->
                                    </div>
                                    <div class="form-check">
                                        <input type="checkbox" class="form-check-input" id="human_verify" name="human_verify">
                                        <label class="form-check-label" for="user_check">Human verification</label>
                                    </div>
                                    <br>
                                    <div class="container text-center" id="loader" style="display : none">
                                        <span class="fa fa-refresh fa-spin fa-4x" ></span>
                                        <h4> Please wait...</h4>
                                    </div>
                                    <div class="text-center">

                                        <button id="submit-btn" type="submit" class="btn primary-background " style="color: white" >Confirm</button>
                                    </div>

                                    <!--<input type="text" class="form-control" id="verification_code" name ="verification_code" aria-describedby="numberHelp" placeholder="Enter Username">-->

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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <script>
            $(document).ready(function () {
                console.log("loaded....."),
                        $('#email-confirm-form').on('submit', function (event) {
                    event.preventDefault();
                    let form = new FormData(this);
                    $('#submit-btn').hide();
                    $('#loader').show();
                    //        send to register servlet
                    $.ajax({
                        url: "PassEmailServlet",
                        type: 'POST',
                        data: form,
                        success: function (data, textStataus, jqXHR) {
                            console.log(data);
                            $('#submit-btn').show();
                            $('#loader').hide();
                            if (data.trim() === "NotSent") {
                                swal("But there is some issue regarding network or you may have entered wrong Email Id Please try again.");

                            }
                            if (data.trim() === "CodeSent") {
                                swal("We are redirecting you to Verification Page.")
                                        .then((value) => {
                                            window.location = "verify.jsp";

                                        });

                            } else if (data.trim() === "notchecked") {
                                swal('Checkbox not checked.... Please check checkbox.');

                            } else {
                                swal('Something went wrong');
//                                swal(data);
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log(jqXHR);
                            $('#submit-btn').show();
                            $('#loader').hide();
                            swal("Something went wrong.. Try Again");
                        },
                        processData: false,
                        contentType: false
                    });

                });
            });
        </script>
    </body>
</html>
