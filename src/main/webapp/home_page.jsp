
<%@page import="java.awt.Color"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.credapp.helper.ConnectionProvider"%>
<%@page import="com.credapp.dao.ApplicationDao"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="com.credapp.entities.Company"%>
<%@page errorPage="error_page.jsp"%>
<%
    boolean showApplication = false;
    Company company = (Company) session.getAttribute("currentCompany");
    if (company == null) {
        response.sendRedirect("login_page.jsp");
    }
//    get the company name
    String companyName = (String) company.getcName();
    String name = null;
//    get the applicatiodao object
    ApplicationDao adao = new ApplicationDao(ConnectionProvider.getConnection());

    ResultSet rs;
    boolean f;
//    get the result
    if (companyName.equals("Admin")) {
        rs = adao.getAllApplications();
        f = true;
        name = "Admin";
    } else {

        rs = adao.getApplications(companyName);
        f = false;
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <!--css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/form.css" rel="stylesheet" type="text/css"/>
    </head>
    <body id="applicationBody" >

        <!--navbar-->
        <nav class="navbar navbar-expand-lg navbar-dark primary-background">
            <a class="navbar-brand" href="home_page.jsp"><span class="fa fa-asterisk"></span>  CredApp</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">


                    <!--                    <li class="nav-item">
                                            <a class="nav-link" href="ApplicationsServlet">
                                                <span class="fa fa-caret-square-o-right">   </span> Applications </a>
                                        </li>-->

                    <!--                    <li class="nav-item">
                                            <a class="nav-link" type="button" class="btn btn-primary" data-toggle="modal" data-target="#applicationModal">
                                                <span class="fa fa-caret-square-o-right">   </span> Applications </a>
                                        </li>-->
                    <!--                    <li>
                                            <a class="nav-link" href="applications.jsp">
                                                <span class="fa fa-address-card-o">   </span>  Processed Applications
                                            </a>

                                        </li>-->
                    <li>
                        <a class="nav-link" href="application_pagination.jsp?page=1&appType=processed">
                            <span class="fa fa-address-card-o">   </span> Processed Applications
                        </a>

                    </li>
                    <li>
                        <a class="nav-link" href="pending_applications.jsp">
                            <span class="fa fa-address-card-o">   </span>  Pending Applications
                        </a>

                    </li>
                    <!--                    <li>
                                            <a class="nav-link" href="under_review_applications.jsp">
                                                <span class="fa fa-address-card-o">   </span>  Under Review Applications
                                            </a>

                                        </li>-->
                    <li>
                        <a class="nav-link" href="viewUnderReviewApplication.jsp?page=1">
                            <span class="fa fa-address-card-o">   </span>  Under Review Applications
                        </a>

                    </li>


                    <li class="nav-item">
                        <a class="nav-link" type="button" class="btn btn-primary" data-toggle="modal" data-target="#companyProfileModal">
                            <span class="fa fa-address-card-o">   </span> Company Details
                        </a>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0 ">
                    <a href="LogoutServlet" class="btn btn-outline-light my-2 my-sm-0" type="submit"><span class="fa fa-user-times"></span > Logout</a>
                </form>
            </div>
        </nav>



        <!--Application form-->
        <%@include file="application_form_page.jsp" %>

        <% if (name != "Admin") {%>


        <!--Company Profile Modal-->
        <div class="modal fade" id="companyProfileModal" tabindex="-1" role="dialog" aria-labelledby="companyProfileModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header primary-background text-white">
                        <h5 class="modal-title" id="companyProfileTitle">Company Profile  </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container text-center">

                            <br>
                            <% if (name != null) {

                            %>
                            <h5 class="modal-title" id="companyName"><%= name%> </h5>
                            <%
                            } else {
                            %>
                            <h5 class="modal-title" id="companyName">Company Name : <%= company.getcName()%> </h5>
                            <%
                                }
                            %>
                            <br>
                        </div>

                        <!--company details-->
                        <div class="container text-left" id="companyDetails">
                            <table class="table">

                                <tbody>
                                    <tr>
                                        <th scope="row">Id : </th>
                                        <td><%= company.getcId()%></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Email : </th>
                                        <td><%= company.getcEmail()%></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Registraion No. : </th>
                                        <td><%= company.getcRNo()%></td>

                                    </tr>
                                    <tr>
                                        <th scope="row">Status : </th>
                                        <td><%= company.getcStatus()%></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Category : </th>
                                        <td><%= company.getcCategory()%></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Class : </th>
                                        <td><%= company.getcClass()%></td>
                                    </tr>

                                    <tr>
                                        <th scope="row">Age : </th>
                                        <td><%= company.getcAge()%></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Size : </th>
                                        <td><%= company.getcSize()%></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Office Locs : </th>
                                        <td>
                                            <%
                                                String[] locs = company.getcOfficeLocs().split(" ");
                                                for (int i = 0; i < locs.length; i++) {
                                            %>
                                            <%= locs[i]%><br>
                                            <%
                                                }
                                            %>
                                        </td>


                                    </tr>

                                    <tr>
                                        <th scope="row">About : </th>
                                        <td><%= company.getcAbout()%></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Registration Date :</th>
                                        <td>
                                            <% DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                                                Timestamp ts = new Timestamp(System.currentTimeMillis());
                                                Timestamp ts1 = company.getcRegDateTime();
                                                Date date = new Date(ts1.getTime());

                                            %>
                                            <%= date.toString()%></td>

                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <!--company details editing-->
                        <div class="container text-center" id="companyDetailsEdit" style="display: none">
                            <h5 class="mt-2 " style="color: red">Please Edit carefully</h5>
                            <form name= "saveForm" id="saveForm" action="EditServlet" method="POST">
                                <table class="table text-left form-group">
                                    <tr>
                                        <td > company Id : </td>
                                        <td><%= company.getcId()%></td>

                                    </tr>
                                    <tr>
                                        <td >Company Name : </td>
                                        <td><input type="text" class="form-control" id="company_name" name ="company_name" value="<%= company.getcName()%>"></td>

                                    </tr>


                                    <tr>
                                        <td >Email : </td>
                                        <td><input type="email" class="form-control" name="company_email" id="company_email" value="<%= company.getcEmail()%>"></td>

                                    </tr>
                                    <tr>
                                        <td >Password : </td>
                                        <td><input type="password" class="form-control" name="company_password" id="company_password" value="<%= company.getcPassword()%>"></td>

                                    </tr>
                                    <tr>
                                        <td >Registration No. : </td>
                                        <td><%= company.getcRNo()%></td>
                                    </tr>

                                    <tr>
                                        <td >Status : </td>
                                        <td>
                                            <select name="company_status" id="company_status" value="<%= company.getcStatus()%>">
                                                <option value="Yes" >Yes</option>
                                                <option value="No" >No</option>

                                            </select>
                                            <!--<input type="email" class="form-control" name="company_status" id="company_status" value="
                                            <%--<%= company.getcStatus()%>--%>
                                            ">-->
                                        </td>

                                    </tr>
                                    <tr>
                                        <td >Category : </td>
                                        <td>
                                            <select name="company_category" id="company_category" value="<%= company.getcCategory()%>">
                                                <option value="Shares" >Shares</option>
                                                <option value="Pvt_owned" >Pvt. owner</option>
                                            </select>
                                            <!--<input type="email" class="form-control" name="company_category" id="company_category" value="
                                            <%--<%= company.getcCategory()%>--%>
                                            ">-->
                                        </td>

                                    </tr>
                                    <tr>
                                        <td >Class : </td>
                                        <td>
                                            <select name="company_class" id="company_class" value="<%= company.getcClass()%>">
                                                <option value="Private" >Private</option>
                                                <option value="Organizational" >Organizational</option>
                                            </select>
                                            <!--<input type="email" class="form-control" name="company_class" id="company_class" value="
                                            <%--<%= company.getcClass()%>--%>
                                            ">-->
                                        </td>

                                    </tr>
                                    <tr>
                                        <td >Age : </td>
                                        <td><input type="text"  class="form-control" name="company_age" id="company_age" value="<%= company.getcAge()%>"></td>

                                    </tr>
                                    <tr>
                                        <td >Size : </td>
                                        <td><input type="text" class="form-control" name="company_size" id="company_size" value="<%= company.getcSize()%>"></td>

                                    </tr>
                                    <tr>
                                        <td >Office Locations : </td>
                                        <td><input type="loc" class="form-control" name="company_office_loc" id="company_office_loc" value="<%= company.getcOfficeLocs()%>"></td>

                                    </tr>


                                    <tr>
                                        <td >About : </td>
                                        <td>
                                            <textarea class="form-control" name="company_about" id="company_about" cols="10" rows="3"  > <%=  company.getcAbout()%></textarea>
                                        </td>
                                    </tr>



                                    <tr>
                                        <td >Registration Date : </td>
                                        <td>
                                            <%
                                                Timestamp ts2 = company.getcRegDateTime();
                                                Date date2 = new Date(ts2.getTime());
                                            %>
                                            <%= date2.toString()%></td>


                                    </tr>
                                </table>
                                <div class="container mb-3 text-white" >

                                    <button  type="submit" class="btn primary-background text-white">Save </button>



                                </div>

                            </form>
                        </div>
                        <!--model footer-->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button id ="details-edit-btn" type="button" class="btn primary-background text-white">Edit</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--end of company profile model-->

        <%} else {
        %>

        <!--Company Profile Modal-->
        <div class="modal fade" id="companyProfileModal" tabindex="-1" role="dialog" aria-labelledby="companyProfileModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header primary-background text-white">
                        <h5 class="modal-title" id="companyProfileTitle">Company Profile  </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container text-center">

                            <br>
                            <h5 class="modal-title" id="companyName"><%= name%> </h5>
                            <br>
                        </div>

                        <!--company details-->
                        <div class="container text-left" id="companyDetails">
                            <table class="table">

                                <tbody>
                                    <tr>
                                        <th scope="row">Email : </th>
                                        <td><%= company.getcEmail()%></td>
                                    </tr>

                                    <tr>
                                        <th scope="row">Office Locs : </th>
                                        <td>
                                            <%
                                                String[] locs = company.getcOfficeLocs().split(" ");
                                                for (int i = 0; i < locs.length; i++) {
                                            %>
                                            <%= locs[i]%><br>
                                            <%
                                                }
                                            %>
                                        </td>


                                    </tr>

                                    <tr>
                                        <th scope="row">About : </th>
                                        <td><%= company.getcAbout()%></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Registration Date :</th>
                                        <td>
                                            <% DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                                                Timestamp ts = new Timestamp(System.currentTimeMillis());
                                                Timestamp ts1 = company.getcRegDateTime();
                                                Date date = new Date(ts1.getTime());

                                            %>
                                            <%= date.toString()%></td>

                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <!--company details editing-->
                        <div class="container text-center" id="companyDetailsEdit" style="display: none">
                            <h5 class="mt-2 " style="color: red">Please Edit carefully</h5>
                            <form name= "saveForm" id="saveForm" action="EditServlet" method="POST">
                                <table class="table text-left form-group">
                                    <tr>
                                        <td >Name : </td>
                                        <td><input type="text" class="form-control" id="company_name" name ="company_name" value="<%= company.getcName()%>"></td>

                                    </tr>


                                    <tr>
                                        <td >Email : </td>
                                        <td><input type="email" class="form-control" name="company_email" id="company_email" value="<%= company.getcEmail()%>"></td>

                                    </tr>
                                    <tr>
                                        <td >Password : </td>
                                        <td><input type="password" class="form-control" name="company_password" id="company_password" value="<%= company.getcPassword()%>"></td>

                                    </tr>


                                    <tr>
                                        <td >Office Locations : </td>
                                        <td><input type="loc" class="form-control" name="company_office_loc" id="company_office_loc" value="<%= company.getcOfficeLocs()%>"></td>

                                    </tr>


                                    <tr>
                                        <td >About : </td>
                                        <td>
                                            <textarea class="form-control" name="company_about" id="company_about" cols="10" rows="3"  > <%=  company.getcAbout()%></textarea>
                                        </td>
                                    </tr>



                                    <tr>
                                        <td >Registration Date : </td>
                                        <td>
                                            <%
                                                Timestamp ts2 = company.getcRegDateTime();
                                                Date date2 = new Date(ts2.getTime());
                                            %>
                                            <%= date2.toString()%></td>


                                    </tr>
                                </table>
                                <div class="container mb-3 text-white" >

                                    <button  type="submit" class="btn primary-background text-white">Save </button>



                                </div>

                            </form>
                        </div>
                        <!--model footer-->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button id ="details-edit-btn" type="button" class="btn primary-background text-white">Edit</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--end of company profile model-->
        <%
            }
        %>




        <!--javascript-->
        <script src="js/myjs.js" type="text/javascript"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>

        <!--    <script>
                $(document).ready(function () {
                    alert("document loaded")
                })
            </script>-->
        <script >
            let editStatus = false;
            $(document).ready(function () {
                $('#details-edit-btn').click(function () {
                    if (editStatus === false) {
                        $('#companyDetailsEdit').show();
                        $('#companyDetails').hide();
                        $(this).text("Back");
                        editStatus = true;
                    } else {
                        $('#companyDetailsEdit').hide();
                        $('#companyDetails').show();
                        $(this).text("Edit");
                        editStatus = false;
                    }
                });
                //                    $('#applicationForm').on('submit', function (event) {
                //                        event.preventDefault();
                //                        let aform = new FormData(this);
                //                        $('#applicationFormSubmitBtn').hide();
                //                        $('#applicationFormLoader').show();

                ////                                                send to the application form servlet
                //                        $.ajax({
                //                            url: "ApplicationFormServlet",
                //                            type: "POST",
                //                            data: aform,
                //                            success: function (data, textStataus, jqXHR) {
                //                                $('#applicationFormSubmitBtn').show();
                //                                $('#applicationFormLoader').hide();
                //                                console.log(data);
                //                                if (data.trim() == 'done') {
                //                                    swal("Application Submitted Successfully.. We have sent you the result. If not recieved please check you Email Id again Or else can view result in Applications tab.");
                //                                } else {
                //                                    swal(data);
                //                                }
                //                            },
                //                            error: function (jqXHR, textStatus, errorThrown) {
                //                                console.log(jqXHR);
                //                                $('#applicationFormSubmitBtn').show();
                //                                $('#applicationFormLoader').hide();
                //                                swal("Something went wrong while submiting application . Fill and submit application again.. Try Again");
                //                            },
                //                            processData: false,
                //                            contentType: false,
                //                        });
                //                    });
                $('#applicationForm').on('submit', function (event) {
                    event.preventDefault();
                    let aform = new FormData(this);
                    $('#applicationFormSubmitBtn').hide();
                    $('#applicationFormLoader').show();

                    //                        send to the application form servlet
                    $.ajax({
                        url: "ApplicationFormServlet",
                        type: "POST",
                        data: aform,
                        success: function (data, textStataus, jqXHR) {
                            $('#applicationFormSubmitBtn').show();
                            $('#applicationFormLoader').hide();
                            console.log(data);
                            if (data.trim() === 'maildone') {
                                swal("Application Submitted Successfully.. And mail was sent about application status.If not recieved please recheck you Email Id.");
                            } else if (data.trim() === 'done') {
                                swal("Application Submitted Successfully.. Some issue occured while sending mail so mail was not sent. Please check your internet connectivity. And try again later.");
                            } else {
                                swal("Something went wrong. Please try again.");
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log(jqXHR);
                            $('#applicationFormSubmitBtn').show();
                            $('#applicationFormLoader').hide();
                            swal("Something went wrong while submiting application . Fill and submit application again.. Try Again");
                        },
                        processData: false,
                        contentType: false
                    });
                });

                $('#saveForm').on('submit', function (event) {
                    event.preventDefault();
                    let saveForm = new FormData(this);
                    //                        send to the application form servlet
                    $.ajax({
                        url: "EditServlet",
                        type: "POST",
                        data: saveForm,
                        success: function (data, textStataus, jqXHR) {
                            console.log(data);
                            if (data.trim() == 'done') {
                                swal("Edited Successfully...");
                                //                                    $('#companyDetailsEdit').hide();
                                //                                    $('#companyDetails').show();
                                //                                    $(this).text("Edit");
                                //                                    editStatus = false;
                            } else {
                                swal(data);
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log(jqXHR);
                            swal("Something went wrong.. Try Again");
                            //                                $('#companyDetailsEdit').show();
                            //                                $('#companyDetails').hide();
                            //                                $(this).text("Back");
                            //                                editStatus = true;
                        },
                        processData: false,
                        contentType: false
                    });
                }
                );
            }
            );
        </script>









    </body>
</html>
