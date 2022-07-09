<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>

        <!--css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/form.css" rel="stylesheet" type="text/css"/>
        <style>
            .banner-background{
                clip-path: polygon(50% 0%, 100% 0, 100% 43%, 100% 88%, 71% 100%, 29% 95%, 0 100%, 0 51%, 0 0);
            }       
        </style>
    </head>
    <body>
        <!--navbar-->
        <%@include  file="outside_navbar.jsp"%>

        <!--Register form-->
        <main class="primary-background   " >
            <div class="container ">
                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <div class="card p-10 mb-3">
                            <div class ="card-header text-center">
                                <br>
                                <span class="fa fa-user-plus fa-3x "></span>
                                <h3 style="text-align: center;">Register</h3>
                            </div>
                            <div class ="card-body">
                                <form id="reg-form" action="RegisterServlet" method="POST">
                                    <div class="form-group">
                                        <label for="exampleInputEmail1" size="18">Username/Company Name </label>
                                        <input type="text" class="form-control" id="company_name" name ="company_name" aria-describedby="emailHelp" placeholder="Enter Username">
                                        <small id="emailHelp" class="form-text text-muted">Enter Company name as Username to register </small>
                                    </div>
                                    <div class="form-group">
                                        <label>Registration Number </label>
                                        <input type="text" class="form-control" id="company_rno" name ="company_rno" aria-describedby="emailHelp" placeholder="Enter Registration Number" >
                                        <small id="emailHelp" class="form-text text-muted">Enter Company's registration number </small>
                                        <!--pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}"-->
                                    </div>
                                    <div class="form-group">
                                        <label for="companyStatus">Company Status </label>
                                        <small id="goverment_organisation" class="form-text text-muted">Goverment Organisation </small>
                                        <select name="company_status" id="company_status">
                                            <option value="Yes" >Yes</option>
                                            <option value="No" >No</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="companyCategory">Company Category </label>
                                        <select name="company_category" id="company_category">
                                            <option value="Shares" >Shares</option>
                                            <option value="Pvt_owned" >Pvt. owner</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="companyClass">Company Class </label>
                                        <select name="company_class" id="company_class">
                                            <option value="Private" >Private</option>
                                            <option value="Organizational" >Organizational</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="companyAge">Age of Company </label>
                                        <input type="text" class="form-control" id="company_age" name ="company_age" aria-describedby="emailHelp" placeholder="Enter Company Age">
                                    </div>
                                    <div class="form-group">
                                        <label for="companySize">Company Size </label>
                                        <input type="text" class="form-control" id="company_size" name ="company_size" aria-describedby="emailHelp" placeholder="Enter Company Size">
                                    </div>


                                    <div class="form-group">
                                        <label for="companyOfficeLoc">Company Office Locations </label>
                                        <input type="loc" class="form-control" id="company_office_loc" name ="company_office_loc" aria-describedby="emailHelp" placeholder="Enter Company Office Location">
                                    </div>

                                    <div class="form-group">
                                        <label for="exampleInputEmail1">Email Address </label>
                                        <input type="email" class="form-control" size="16" id="company_email" name ="company_email" aria-describedby="emailHelp" placeholder="Enter Email Address">
                                        <small id="emailHelp" class="form-text text-muted">Enter Email Address on which you want to get notified regarding approval. </small>
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputPassword1">Password</label>
                                        <input type="password" class="form-control" name="company_password" id="company_password" placeholder="Enter Password">
                                    </div>

                                    <div class="form-group">
                                        <label for="exampleFormControlTextarea1">About</label>
                                        <br>
                                        <textarea class="form-control" name="company_about" id="company_about" cols="10" rows="3" placeholder="Enter about your company"></textarea>
                                    </div>
                                    <div class="form-check">
                                        <input type="checkbox" class="form-check-input" id="company_check" name="company_check">
                                        <label class="form-check-label" for="user_check">Agree Terms and Conditions</label>
                                    </div>
                                    <br>
                                    <div class="container text-center" id="loader" style="display : none">
                                        <span class="fa fa-refresh fa-spin fa-4x" ></span>
                                        <h4> Please wait...</h4>
                                    </div>
                                    <div class="text-center">

                                        <button id="submit-btn" type="submit" class="btn primary-background " style="color: white" >Register</button>
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
                        $('#reg-form').on('submit', function (event) {
                    event.preventDefault();
                    let form = new FormData(this);
                    $('#submit-btn').hide();
                    $('#loader').show();
//        send to register servlet
                    $.ajax({
                        url: "RegisterServlet",
                        type: 'POST',
                        data: form,
                        success: function (data, textStataus, jqXHR) {
                            console.log(data);
                            $('#submit-btn').show();
                            $('#loader').hide();
                            if (data.trim() === 'registerAndCodeSent') {
                                swal("Registered successully ... And verification code is being sent. If not recieved please recheck your Email Id. We are redirecting you to Verification Page. Without verification one can't login")
                                        .then((value) => {
                                            window.location = "verify.jsp";

                                        });

                            } else if (data.trim() === "notchecked") {
                                swal('Checkbox not checked.... Please check checkbox.');

                            } else if (data.trim() === "registerAndCodeNotSent") {

                                swal("Registered successully ... And some issue occured with Email not sent. Please recheck your Email Id.");
                            } else if (data.trim() === "common") {
                                swal('Enter unique company name... Account with the same name already exist.');
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
    </body>
</html>
