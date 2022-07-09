<%--
    Document   : forget_password
    Created on : 27-May-2022, 6:46:39 PM
    Author     : Aayushi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Password Page</title>
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
        <!--        new password form
        -->                <main class="d-flex align-items-center primary-background banner-background " style="height:90vh">
            <div class="container ">
                <div class="row">
                    <div class="col-md-4 offset-md-4">
                        <div class ="card p-10 mb-3 ">
                            <div class="card-header text-center ">
                                <br>
                                <span class="fa fa-user-circle-o fa-3x "></span>
                                <h3 style="text-align: center;">Create New Password</h3>
                            </div>
                            <div class ="card-body">
                                <form id="new-pass-form" action="PassUpdateServlet" method="POST">

                                    <div class="form-group">
                                        <label for="exampleInputPassword1">Enter Password : </label>
                                        <input type="password" required class="form-control" id="company_password" name="company_password" placeholder="Enter Password">
                                    </div>

                                    <div class="form-group">
                                        <label for="exampleInputPassword1">Confirm Password : </label>
                                        <input type="password" required class="form-control" id="company_confirm_password" name="company_confirm_password" placeholder="Confirm Password">
                                    </div>

                                    <!-- An element to toggle between password visibility -->
                                    <div class="form-check">
                                        <input type="checkbox" onclick="showPassword()" class="form-check-input" id="human_check" name="human_check">
                                        <label class="form-check-label" for="user_check">Show Password </label>
                                    </div>
                                    <br>


                                    <!--<input type="checkbox" onclick="myFunction()">Show Password-->
                                    <div class="container text-center" id="loader" style="display : none">
                                        <span class="fa fa-refresh fa-spin fa-4x" ></span>
                                        <h4> Please wait...</h4>
                                    </div>

                                    <div class="text-center">
                                        <button id="submit-btn" type="submit" class="btn primary-background " style="color: white " >Confirm</button>

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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <script>
                                            $(document).ready(function () {
                                                console.log("loaded....."),
                                                        $('#new-pass-form').on('submit', function (event) {
                                                    event.preventDefault();
                                                    let form = new FormData(this);
                                                    $('#submit-btn').hide();
                                                    $('#loader').show();
                                                    //        send to register servlet
                                                    $.ajax({
                                                        url: "PassUpdateServlet",
                                                        type: 'POST',
                                                        data: form,
                                                        success: function (data, textStataus, jqXHR) {
                                                            console.log(data);
                                                            $('#submit-btn').show();
                                                            $('#loader').hide();
                                                            if (data.trim() === 'passUpdate') {
                                                                swal("Password updated successully ... ")
                                                                        .then((value) => {
                                                                            window.location = "login_page.jsp";

                                                                        });

                                                            } else if (data.trim() === "notchecked") {
                                                                swal('Checkbox not checked.... Please check checkbox.');

                                                            } else if (data.trim() === "passNotUpdate") {

                                                                swal("Some issue occured. Please try again after some time.");
                                                            } else if (data.trim() === "notSame") {
                                                                swal('Enter same value in both the fields...');
                                                            } else {
                                                                swal('Something went wrong');
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
        <script>

            function showPassword() {
                var pass = document.getElementById("company_password");
                var confirmPass = document.getElementById("company_confirm_password");
                if (pass.type === "password" && confirmPass.type === "password") {
                    pass.type = "text";
                    confirmPass.type = "text";
                } else {
                    pass.type = "password";
                    confirmPass.type = "password";

                }
            }
        </script>
        <script>

            function showPassword() {
                var pass = document.getElementById("company_password");
                var confirmPass = document.getElementById("company_confirm_password");
                if (pass.type === "password" && confirmPass.type === "password") {
                    pass.type = "text";
                    confirmPass.type = "text";
                } else {
                    pass.type = "password";
                    confirmPass.type = "password";

                }
            }
        </script>

    </body>
</html>
