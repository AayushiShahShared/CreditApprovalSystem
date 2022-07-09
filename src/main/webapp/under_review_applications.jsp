<%--
    Document   : under_review_applications
    Created on : 31-May-2022, 12:50:11 PM
    Author     : Aayushi
--%>


<%@page import="com.credapp.dao.ReviewApplicationDao"%>
<%@page import="com.credapp.dao.PendingApplicationDao"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.credapp.dao.ApplicationDao"%>
<%@page import="com.credapp.helper.ConnectionProvider"%>
<%@page import="com.credapp.entities.Company"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    boolean showApplication = false;
    Company company = (Company) session.getAttribute("currentCompany");
    if (company == null) {
        response.sendRedirect("login_page.jsp");
    }
//    get the company name
    String companyName = (String) company.getcName();
    String companyType = company.getcType();
//    get the applicatiodao object
    ReviewApplicationDao rdao = new ReviewApplicationDao(ConnectionProvider.getConnection());
    ResultSet rs = null;
    boolean f;
//    get the result
    if (companyType.equals("regUser")) {
        rs = rdao.getAllReviewApplicationsByName(companyName);
    } else {
        response.sendRedirect("login_page.jsp");

    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Applications Page</title>
        <!--css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/form.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>

        <!--<h1>pending World!</h1>-->

        <!--navbar-->
        <nav class="navbar navbar-expand-lg navbar-dark primary-background">
            <a class="navbar-brand" href="home_page.jsp"><span class="fa fa-asterisk"></span>  CredApp</a>
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
                    <a href="home_page.jsp" class="btn btn-outline-light my-2 my-sm-0" type="submit"><span class="fa fa-user-times"></span > Back</a>
                </form>
            </div>
        </nav>

        <br>
        <!--show applications-->
        <div class="applications">
            <div class="container text-center">
                <h1 style="color:  teal">Under Review Applications</h1>
            </div>

            <br>
            <% if (rdao.getReviewApplicationsCount() > 0) {%>

            <div class="container text-center">
                <h2 id="companyName" style="color: orangered">

                    <%= companyName%>
                </h2>
                <br>
            </div>


            <!--application  details-->
            <div class="container text-left" id="applications">

                <%
                    while (rs.next()) {
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
                            <th scope="row">Status : </th>
                            <td style="color: goldenrod"><b>Under Review</b></td>

                        </tr>
                        <tr>
                            <th scope="row">Reason : </th>
                            <td ><%= rs.getString("appReason")%></td>

                        </tr>
                        <tr>
                            <th scope="row">Date : </th>
                            <td ><%= rs.getTimestamp("appDate")%></td>

                        </tr>



                    </tbody>
                </table>

                <% }
                } else {
                %>
                <center><h5 style="color: grey">No Applications is under review </h5></center>
                <br>
                <br>
                <% }%>            </div>
        </div>

        <!--javascript-->
        <script src="js/myjs.js" type="text/javascript"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>

    </body>
</html>




