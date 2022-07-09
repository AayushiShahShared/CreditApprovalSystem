<%--
    Document   : review_application
    Created on : 29-May-2022, 4:11:36 PM
    Author     : Aayushi
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.credapp.entities.Application"%>
<%@page import="com.credapp.dao.ReviewApplicationDao"%>
<%@page import="com.credapp.helper.ConnectionProvider"%>
<%@page import="com.credapp.entities.Company"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.credapp.dao.ApplicationDao"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    int appId;
    boolean showApplications = false;
//    Application applicationInstance = new Application();
    Connection con = null;
    ReviewApplicationDao rdao = null;
    List<Application> applications = null;
    ResultSet rs = null;
    Company company = (Company) session.getAttribute("currentCompany");
    String companyName = null;
    String companyType = null;
    if (company == null) {
        response.sendRedirect("login_page.jsp");
    } else {
        // get the company name
        companyName = (String) company.getcName();
        companyType = (String) company.getcType();

        if (companyType.equals("fAnalyst")) {
            try {

                con = ConnectionProvider.getConnection();
                rdao = new ReviewApplicationDao(con);
//                applications = rdao.getAllReviewApplications();
                rs = rdao.getAllReviewApplications();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("login_page.jsp");
        }
    }

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Analyst Review Applications page</title>

        <!--css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/form.css" rel="stylesheet" type="text/css"/>
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <!--        <meta name="viewport" content="width=device-width">
                <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">-->

    </head>
    <body>


        <!--navbar-->
        <nav class="navbar navbar-expand-lg navbar-dark primary-background">
            <a class="navbar-brand" href="analyst_home.jsp"><span class="fa fa-asterisk"></span>  CredApp</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">

                    <!--
                                        <li class="nav-item">
                                            <a class="nav-link" href="ApplicationsServlet">
                                                <span class="fa fa-caret-square-o-right">   </span> Applications </a>
                                        </li>-->

                    <!--                    <li class="nav-item">
                                            <a class="nav-link" type="button" class="btn btn-primary" data-toggle="modal" data-target="#applicationModal">
                                                <span class="fa fa-caret-square-o-right">   </span> Applications </a>
                                        </li>-->
                    <!--                    <li>
                                            <a class="nav-link" href="applications.jsp">
                                                <span class="fa fa-address-card-o">   </span>  Applications
                                            </a>

                                        </li>-->
                    <!--                    <li class="nav-item">
                                            <a class="nav-link" type="button" class="btn btn-primary" data-toggle="modal" data-target="#companyProfileModal">
                                                <span class="fa fa-address-card-o">   </span> Company Details
                                            </a>
                                        </li>-->
                </ul>
                <!--                <form class="form-inline my-2 my-lg-0">


                                    <input class="form-control mr-sm-3" type="search" placeholder="Search" aria-label="Search">


                                    <button class="btn btn-outline-light my-2 my-sm-1 mr-sm-4" type="submit">Search</button>
                                </form>-->

                <form class="form-inline my-2 my-lg-0 ">
                    <a href="analyst_home.jsp" class="btn btn-outline-light my-2 my-sm-0" type="submit"><span class="fa fa-user-times"></span > Back</a>
                </form>
            </div>
        </nav>

        <br>
        <!--show applications-->
        <div class="applications">
            <div class="container text-center">
                <h1 style="color:  teal">Review Applications</h1>
            </div>

            <br>
            <!--            <div class="container text-center">
                            <h2 id="companyName" style="color: orangered">

            <%--<%= // companyName%>--%>
        </h2>
        <br>
    </div>-->

            <!--application  details-->
            <div class="container text-left" id="applications">

                <%  if (rdao.getReviewApplicationsCount() > 0) {
                        int count = 0;
                        while (rs.next()) {
                            count++;
                            int aid = rs.getInt("appId");
                %>

                <br>
                <b > <h4 style="color:  teal">  Application Id:

                        <%= rs.getInt("appId")%>
                    </h4>
                </b>
                <br>
                <form action="ReviewApplicationServlet" name="f1" method="POST">
                    <table class="table">

                        <tbody>
                            <tr>
                                <th scope="row">Company Name: </th>
                                <td><%= rs.getString("appCompanyName")%></td>
                            </tr>
                            <tr>
                                <th scope="row">Loan Amount : </th>
                                <td><%= rs.getInt("appLoanAmount")%></td>
                            </tr>
                            <tr>
                                <th scope="row">Period : </th>
                                <td><%= rs.getInt("appPeriod")%></td>

                            </tr>
                            <tr>
                                <th scope="row">Discount : </th>
                                <td><%= rs.getInt("appDiscount")%></td>
                            </tr>
                            <tr>

                                <th scope="row">Cibil : </th>
                                <td><%= rs.getInt("appCibil")%></td>
                            </tr>
                            <tr>

                                <th scope="row">Bank Account No. : </th>
                                <td><%= rs.getString("appBankAcc")%></td>

                            </tr>
                            <tr>
                                <th scope="row">Revenue : </th>
                                <td><%= rs.getInt("appRevenue")%></td>
                            </tr>
                            <tr>

                                <th scope="row">Employee Expense : </th>
                                <td>
                                    <%= rs.getInt("appEmpExpense")%>
                                </td>

                            </tr>

                            <tr>

                                <th scope="row">Total Amount : </th>
                                <td>
                                    <%= rs.getFloat("appTotalAmt")%>
                                </td>

                            </tr>

                            <tr>

                                <th scope="row">Interest : </th>
                                <td>
                                    <%= rs.getFloat("appInterest")%>
                                </td>

                            </tr>
                            <tr>

                                <th scope="row">Reason : </th>
                                <td>
                                    <%= rs.getString("appReason")%>
                                </td>

                            </tr>

                            <tr>
                                <th scope="row">Date : </th>
                                <td ><%= rs.getTimestamp("appDate")%></td>

                            </tr>
                            <tr>
                                <th scope="row"><label for="comment">Comment:</label> </th>

                                <td>
                                    <div class="form-group">

                                        <textarea class="form-control" placeholder="Max word limit 500"
                                                  rows="3" maxlength="500" id="cmt<%= count%>" name="cmtbox"></textarea>

                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                        <center>
                            <input type="submit" value="Approve"
                                   style="background-color: green;
                                   border: none;
                                   color: white;
                                   padding: 7px 20px;
                                   text-align: center;
                                   text-decoration: none;
                                   display: inline-block;
                                   font-size: 20px;
                                   font-weight: bold;
                                   "
                                   class="btn"
                                   id="approve-btn" name="approve-btn"
                                   onclick='send("approve",<%=aid%>,<%=count%>)'>

                            <!--                            <a href='ReviewApplicationServlet?appid=
                            <%--<%= // rs.getInt("appId")%>--%>
                            &reviewType=approve&cmtno=
                            <%--<%= count%>--%>
                            '  class="btn"  id="approve-btn" name="approve-btn"
                            style="background-color: green;
                            border: none;
                            color: white;
                            padding: 7px 20px;
                            text-align: center;
                            text-decoration: none;
                            display: inline-block;
                            font-size: 20px;"><b>Approve</b></a>-->

                        </center>
                        </td>
                        <td>
                            <input type="submit" value="Reject"
                                   style="background-color: red;
                                   border: none;
                                   color: white;
                                   padding: 7px 30px;
                                   margin-left: 30px;
                                   text-align: center;
                                   text-decoration: none;
                                   display: inline-block;
                                   font-size: 20px;
                                   font-weight: bold;"
                                   class="btn"
                                   id="reject-btn" name="reject-btn"
                                   onclick='send("reject",<%=aid%>,<%=count%>)'>


                            <!--                            <a href='ReviewApplicationServlet?appid=
                            <%--<%= rs.getInt("appId")%>--%>
                            &reviewType=reject' class="btn" id="reject-btn" name="reject-btn"
                            style="background-color: red;
                            border: none;
                            color: white;
                            padding: 7px 30px;
                            margin-left: 30px;
                            text-align: center;
                            text-decoration: none;
                            display: inline-block;
                            font-size: 20px;"><b>Reject</b></a>-->
                        </td>
                        </tr>
                        </tbody>
                    </table>
                </form>

                <!--<button name="approved-btn" id="approved-btn" onclick="submit(event, 'approve')">Approve</button>-->
                <!--<button name= "reject-btn" id="reject-btn" value=" // rs.getInt("appId")">Reject</button>-->

                <%
                    }
                } else {
                %>
                <center><h5 style="color: grey">No Application is Under Review </h5></center>
                <br>
                <br>
                <% }%>


            </div>







            <!--/////////////////////////-->

            <!--application  details-->
            <div class="container text-left" id="applications">

                <%  if (rdao.getReviewApplicationsCount() > 0) {
                        int count = 0;
                        while (rs.next()) {
                            count++;
                %>

                <br>
                <b > <h4 style="color:  teal">  Application Id:

                        <%= rs.getInt("appId")%>
                    </h4>
                </b>
                <br>

                <table class="table">

                    <tbody>
                        <tr>
                            <th scope="row">Company Name: </th>
                            <td><%= rs.getString("appCompanyName")%></td>
                        </tr>
                        <tr>
                            <th scope="row">Loan Amount : </th>
                            <td><%= rs.getInt("appLoanAmount")%></td>
                        </tr>
                        <tr>
                            <th scope="row">Period : </th>
                            <td><%= rs.getInt("appPeriod")%></td>

                        </tr>
                        <tr>
                            <th scope="row">Rate : </th>
                            <td><%= rs.getInt("appRate")%></td>
                        </tr>
                        <tr>

                            <th scope="row">Cibil : </th>
                            <td><%= rs.getInt("appCibil")%></td>
                        </tr>
                        <tr>

                            <th scope="row">Bank Account No. : </th>
                            <td><%= rs.getString("appBankAcc")%></td>

                        </tr>
                        <tr>
                            <th scope="row">Revenue : </th>
                            <td><%= rs.getInt("appRevenue")%></td>
                        </tr>
                        <tr>

                            <th scope="row">Employee Expense : </th>
                            <td>
                                <%= rs.getInt("appEmpExpense")%>
                            </td>

                        </tr>
                        <tr>
                            <th scope="row">Date : </th>
                            <td ><%= rs.getTimestamp("appDate")%></td>

                        </tr>
                        <tr>
                            <th scope="row"><label for="comment">Comment:</label> </th>

                            <td>
                                <div class="form-group">

                                    <textarea class="form-control" placeholder="Max word limit 500" rows="3" maxlength="500" id="comment" name="cmt<%= count%>"></textarea>

                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                    <center>

                        <a href='ReviewApplicationServlet?appid=<%= rs.getInt("appId")%>&reviewType=approve&cmtno=<%= count%>'  class="btn"  id="approve-btn" name="approve-btn"
                           style="background-color: green;
                           border: none;
                           color: white;
                           padding: 7px 20px;
                           text-align: center;
                           text-decoration: none;
                           display: inline-block;
                           font-size: 20px;"><b>Approve</b></a>

                    </center>
                    </td>
                    <td>
                        <a href='ReviewApplicationServlet?appid=<%= rs.getInt("appId")%>&reviewType=reject' class="btn" id="reject-btn" name="reject-btn"
                           style="background-color: red;
                           border: none;
                           color: white;
                           padding: 7px 30px;
                           margin-left: 30px;
                           text-align: center;
                           text-decoration: none;
                           display: inline-block;
                           font-size: 20px;"><b>Reject</b></a>
                    </td>
                    </tr>
                    </tbody>
                </table>


                <!--<button name="approved-btn" id="approved-btn" onclick="submit(event, 'approve')">Approve</button>-->
                <!--<button name= "reject-btn" id="reject-btn" value=" // rs.getInt("appId")">Reject</button>-->

                <%
                    }
                } else {
                %>
                <center><h5 style="color: grey">No Application is Under Review </h5></center>
                <br>
                <br>
                <% }%>


            </div>
        </div>

        <!--javascript-->
        <script src="js/myjs.js" type="text/javascript"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <script>
            <%                String value = (String) session.getAttribute("reload");


            %>
                                       if ("<%= value%>".trim() === "no") {
//                window.location.reload();
//                window.location.reload();
                                           swal("hii");
            <% session.removeAttribute("reload");%>
                                       }



                                       function send(btntype, appId, count) {
//                                    swal(btntype);
                                           event.preventDefault();

                                           var cmtval = document.getElementById("cmt" + count).value;
//                                    var msg = $('textarea#comment').val();
//                                    var btnType;
//                                    if (btnType === "approve") {
//                                        swal("hii");
//                                    }
//                                    var cmtvalue = document.getElementById("comment").value;
                                           console.log(btntype);
                                           console.log(appId);
                                           console.log(count);
                                           console.log(cmtval);
                                           window.location.href = "ReviewApplicationServlet?reviewType=" + btntype + "&appid=" + appId + "&count=" + count + "&cmt=" + cmtval;

                                       }
        </script>
        <!--        <script>
                    function submit(e) {
                        //                        e = e || window.event
                        e.preventDefault();
                        $('#approve-btn').hide();
                        $('#reject-btn').hide();
        //                            var s = aid + " " + reviewType;
        //                            swal(s);
        //                            $.ajax({
        //                                url: "ReviewApplicationServlet",
        //                                type: "POST",
        //                                dataType: "JSON",
        //                                data: JSON.stringify({"r": reviewType}),
        //                                success: function (data, textStatus, jqXHR) {
        //                                    swal(data);
        //                                },
        //                                error: function (jqXHR, textStatus, errorThrown) {
        //                                    swal("error");
        //                                },
        //                                processData: false,
        //                                contentType: false
        //                            });
                    }


                </script>-->
        <!--        <script>

                            $(document).ready(function () {
                    console.log("loaded....."),
                            $('#approve-btn').on('click', function (event) {
                    event.preventDefault();
                            //                    console.log("hii....."),
                            $('#approve-btn').hide();
                            $('#reject-btn').hide();
                            //        send to register servlet
                            $.ajax({
                            url: "ReviewApplicationServlet",
                                    type: 'POST',
                                    success: function (data, textStataus, jqXHR) {
                                    console.log(data);
                                            $('#approve-btn').hide();
                                            $('#reject-btn').hide();
                                            //                                    if (data.trim() === 'notverified') {
                                            //                            swal("Your account is not verified. Please verify it first without verification one can't be able to login. We are redirecting you to Verification Page.")
                                            //                                    .then((value) => {
                                            //                                    window.location = "email.jsp";
                                            //                                    });
                                            //                            } else if (data.trim() === "notchecked") {
                                            //                            swal('Checkbox not checked.... Please check checkbox.');
                                            //                            } else if (data.trim() === "invalid") {
                                            //                            window.location = "login_page.jsp";
                                            //                                    swal("Registered successully ... And some issue occured with Email not sent. Please recheck your Email Id.");
                                            //                            } else if (data.trim() === "home") {
                                            //                            window.location = "home_page.jsp";
                                            //                            } else if (data.trim() === "admin") {
                                            //                            window.location = "admin_home.jsp";
                                            //                            } else if (data.trim() === "financialAnalyst") {
                                            //                            window.location = "analyst_home.jsp";
                                            //                            } else {
                                            //                            swal('Something went wrong');
                                            //                            }
                                            //                            } else {
                                            //                    swal(data);
                                            //
                                    },
                                    error: function (jqXHR, textStatus, errorThrown) {
                                    console.log(jqXHR);
                                            $('#approve-btn').show();
                                            $('#reject-btn').show();
                                            swal("Something went wrong.. Try Again");
                                    },
                                    processData: false,
                                    contentType: false
                            });
                    });
                    };
                </script>-->
        <!-- <script>
        //                    var appid;
        //                    var reviewType;
        //            function submit(aid, value) {
        //                appid = aid;
        //
        //                if (value === 'approve') {
        //                    reviewType = 'approve';
        //
        //                    swal("hii a");
        //
        //
        //                } else if (value === 'reject') {
        //                    reviewType = 'reject';
        //                    swal("hiii r");
        //                }
        //                        var v = document.getElementById("approve-btn").value;
        //                        if (value === 'approve')
        //                        {
        //                        var s = " " + id + " " + value;
        //                        swal(s);

        //                        }
        //                        window.location("ReviewApplicationServlet");
        //            }

        //

                </script>-->

    </body>
</html>


