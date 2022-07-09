<%--
    Document   : application_pagination
    Created on : 15-Jun-2022, 10:36:51 AM
    Author     : Aayushi
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="com.credapp.entities.Company"%>
<%@page import="com.credapp.dao.CompanyDao"%>
<%@page import="com.credapp.entities.Application"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.credapp.helper.ConnectionProvider"%>
<%@page import="com.credapp.dao.ApplicationDao"%>
<%@page  errorPage="error_page.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style type="text/css">

            #spanid{
                float: right;
                margin-right: 100px;
            }

        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>applications Page</title>

        <!--css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/form.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>


        <%

            Company company = (Company) session.getAttribute("currentCompany");
            String companyName = "";
            String companyType;
            int start = 0, total = 0;
            int recordCount = 5;
            int pagno = 0, pagNo = 0;
            int count = 0;
            ArrayList<Application> applicationList = new ArrayList<>();
            ApplicationDao adao = null;
            if (company == null) {
                response.sendRedirect("login_page.jsp");
            } else {
//    get the company name
                companyName = company.getcName();
                companyType = company.getcType();
                adao = new ApplicationDao(ConnectionProvider.getConnection());
//    get the applicatiodao object

//    get the result
                if (companyType.equals("regUser")) {

                    pagno = Integer.parseInt(request.getParameter("page"));
                    adao = new ApplicationDao(ConnectionProvider.getConnection());

                    start = (pagno - 1) * recordCount;
                    total = adao.getApplicationsCount(companyName);
                    applicationList = adao.getAllApplicationsFromRange(companyName, start, recordCount);

                } else {
                    response.sendRedirect("login_page.jsp");

                }
            }


        %>




        <!--navbar-->
        <nav class="navbar navbar-expand-lg navbar-dark primary-background">
            <a class="navbar-brand" href="home_page.jsp"><span class="fa fa-asterisk"></span>  CredApp</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">

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
        <br>
        <!--show applications-->
        <div class="applications">
            <div class="container text-center">
                <h2 style="color:  teal">Processed Applications</h2>
            </div>
            <br>
            <br>

            <center>

                <table class="table table-striped" style="width: 1000px">
                    <thead>
                        <tr class="text-center">
                            <th scope="col">Id</th>
                            <!--<th scope="col">Company Name</th>-->
                            <th scope="col">Loan Amount</th>
                            <th scope="col">Status</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>

                        <% for (Application applicationInstance : applicationList) {
                                count = count + 1;
                                String color = applicationInstance.getStatus().equals("Approved") ? "green" : "red";
                        %>

                        <tr class="text-center">
                            <th scope="row"><%= applicationInstance.getId()%></th>
                            <!--<td>-->
                            <%--<%= // applicationInstance.getCompanyName()%>--%>
                            <!--</td>-->
                            <td><%= applicationInstance.getLoanAmount()%></td>
                            <td style="color: <%= color%>">
                                <b>

                                    <%= applicationInstance.getStatus()%>
                                </b>
                            </td>
                            <td>
                                <a href='view_application_details.jsp?appid=<%= applicationInstance.getId()%>&page=<%= pagno%>&appType=processed' class='btn' name='delete-btn' id='delete-btn'
                                   style="background-color: teal;
                                   border: none;
                                   color: white;
                                   /*padding: 7px 30px;*/
                                   /*margin-top: 10px;*/
                                   text-align: center;
                                   text-decoration: none;
                                   display: inline-block;
                                   font-size: 16px;
                                   "><b> View </b></a>
                            </td>

                        </tr>
                        <% }%>
                    </tbody>
                </table>
                <br>
                <%--<%= // total%>--%>
                <%--<%= pagno%>--%>

                <% int remainder = recordCount - count;
                    for (int i = 0; i < remainder; i++) {
                %>
                <br>
                <br>
                <br>
                <%}%>

                <table style="width: 1000px">

                    <tr>
                        <td>
                            <span style="float: left; margin-left: 100px">
                                <a href="application_pagination.jsp?page=<%= pagno - 1%>" class="btn btn-info <%= (start == 0) ? "disabled" : ""%>">
                                    Previous
                                </a>
                            </span>
                        </td>
                        <td class="text-center">
                            <% // if ((total / recordCount) == 1) {
                                if ((start + recordCount) >= total) {
                            %>
                            Page <b>
                                <%= pagno%>
                            </b> of <b>
                                <%= pagno%>
                            </b>
                            <%} else {%>
                            Page <b>
                                <%= pagno%>
                            </b> of <b>
                                <%= ((total % recordCount) == 0) ? (total / recordCount) : (total / recordCount) + 1%>

                                <%--<%= // (total / recordCount) + 1%>--%>
                            </b>
                            <%}%>
                        </td>
                        <td>

                            <span style="float: right; margin-right: 150px;">

                                <a href="application_pagination.jsp?page=<%=pagno + 1%>" class="btn btn-info <%= (start + recordCount >= total) ? "disabled" : ""%>" style="padding-left: 30px ; padding-right:30px ">Next
                                </a>
                            </span>

                        </td>

                        <!--<td >-->
                        <% //for (int i = 0; i <= total / recordCount; i++) {%>
                        <!--<center>-->
                        <!--<a-->
                        <!--href="application_pagination.jsp?start=-->
                        <%--<%= //i + 1%>--%>
                        <!--&page=-->
                        <%--<%= //i + 1%>--%>
                        <!--"-->
                        <!--class="btn btn-info-->
                        <%--<%= //(i == pagno - 1) ? "active" : ""%>--%>
                        <!--                            ">
                                                    Page-->
                        <%--<%= // i + 1%>--%>
                        <!--</a>-->
                        <!--<br>-->
                        <!--<br>-->
                        <!--<br>-->
                        <!--</center>-->
                        <% //}
%>
                        <!--<td>-->
                    </tr>
                </table>
            </center>





    </body>
    <!--javascript-->
    <script src="js/myjs.js" type="text/javascript"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>

</html>
