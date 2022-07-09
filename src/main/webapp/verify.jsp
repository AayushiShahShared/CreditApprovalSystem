<%--
    Document   : verify
    Created on : 27-May-2022, 3:47:38 PM
    Author     : Aayushi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String type = (String) session.getAttribute("type");
    String formTarget;
    if (type == "otp") {
        formTarget = "new_password.jsp";
    } else {

        formTarget = "login_page.jsp";
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>verify Page</title>

        <!--css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/form.css" rel="stylesheet" type="text/css"/>
        <style>
            .banner-background{
                clip-path: polygon(50% 0%, 100% 0, 100% 43%, 100% 88%, 71% 100%, 29% 95%, 0 100%, 0 51%, 0 0);
            }       </style>
    </head>
    <body>

        <!--navbar-->
        <%@include  file="outside_navbar.jsp"%>

        <!--verification form-->
        <main class="primary-background   " >
            <div class="container ">
                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <div class="card p-10 mb-3">
                            <div class ="card-header text-center">
                                <br>
                                <span class="fa fa-user-plus fa-3x "></span>
                                <h3 style="text-align: center;">Verify</h3>
                            </div>
                            <div class ="card-body">

                                <form id="verify-form" action="VerifyServlet" method="POST">

                                    <div class="form-group">
                                        <label for="exampleInputEmail1" size="18">Verification code : </label>
                                        <input type="text" class="form-control" id="verification_code" name ="verification_code" aria-describedby="emailHelp" placeholder="Enter Username">
                                        <small id="Help" class="form-text text-muted">Enter your verification code </small>
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

                                        <button id="submit-btn" type="submit" class="btn primary-background " style="color: white" >Verify</button>
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
                        $('#verify-form').on('submit', function (event) {
                    event.preventDefault();
                    let form = new FormData(this);
                    $('#submit-btn').hide();
                    $('#loader').show();
                    //        send to register servlet
                    $.ajax({
                        url: "VerifyServlet",
                        type: 'POST',
                        data: form,
                        success: function (data, textStataus, jqXHR) {
                            console.log(data);
                            $('#submit-btn').show();
                            $('#loader').hide();
                            if (data.trim() === "VerificationDoneAndNotUpdate") {
                                swal("Verified successfully ... But there is some issue regarding network please try again.")
                                        .then((value) => {
                                            window.location = "email.jsp";
                                        });

                            } else if (data.trim() === "VerificationUpdate") {
                                swal("Your account verified successfully .. We are redirecting you to Login Page.")
                                        .then((value) => {
                                            window.location = "login_page.jsp";
                                        });
                            } else if (data.trim() === "newpass") {
                                swal("We are redirecting you to create password Page.")
                                        .then((value) => {
                                            window.location = "new_password.jsp";
                                        });
                            } else if (data.trim() === "error") {
                                swal("Some issue occured please try again")
                                        .then((value) => {
                                            window.location = "email.jsp";
                                        });
                            } else if (data.trim() === "otpVerifyPass") {
                                swal("Verified successfully ... Redirecting you to create new password.")
                                        .then((value) => {
                                            window.location = "new_password.jsp";
                                        });
                            } else if (data.trim() === "notchecked") {
                                swal('Checkbox not checked.... Please check checkbox.');
                            } else if (data.trim() === "noVerify") {
                                swal('Please enter correct verification code.');
                            } else if (data.trim() === "NotSent") {
                                swal('Please enter correct Email Id.');
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
