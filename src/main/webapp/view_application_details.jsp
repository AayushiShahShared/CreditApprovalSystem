<%--
    Document   : view_application_details
    Created on : 15-Jun-2022, 11:28:14 AM
    Author     : Aayushi
--%>

<%@page import="com.credapp.dao.ReviewApplicationDao"%>
<%@page import="com.credapp.dao.PendingApplicationDao"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.credapp.entities.Application"%>
<%@page import="com.credapp.helper.ConnectionProvider"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.credapp.dao.ApplicationDao"%>
<%@page import="com.credapp.entities.Company"%>
<%--<%@page errorPage="error_page.jsp" %>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!--css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/form.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <%

            boolean showCompanyName = false;
            boolean showApplication = false;
            boolean showUnderReviewApplication = false;
            Company company = (Company) session.getAttribute("currentCompany");
            String companyName = "";
            String companyType;
            String appType = "";
            String title = "";
            Connection con = null;
            Application applicationInstance = null;
            int appId = 0;
            int pagno = 0;
            String targetHome = "";
            String target = "";
            ApplicationDao adao = null;
            PendingApplicationDao pdao = null;
            ReviewApplicationDao rdao = null;

            ResultSet rs = null;
            if (company == null) {
                response.sendRedirect("login_page.jsp");
            } else {

                companyName = (String) company.getcName();
                companyType = (String) company.getcType();
                con = ConnectionProvider.getConnection();
                adao = new ApplicationDao(con);
                pdao = new PendingApplicationDao(con);
                rdao = new ReviewApplicationDao(con);
                appType = request.getParameter("appType");
                pagno = Integer.parseInt(request.getParameter("page"));
                appId = Integer.parseInt(request.getParameter("appid"));
                try {

                    if (companyType.equals("admin")) {
                        targetHome = "admin_home.jsp";
                        target = "analystViewProcessedApplication.jsp";

                        showCompanyName = true;
                    } else if (companyType.equals("regUser")) {
                        targetHome = "home_page.jsp";
                        target = "application_pagination.jsp";

                    } else {
                        targetHome = "analyst_home.jsp";
                        target = "analystViewProcessedApplication.jsp";

                        showCompanyName = true;

                    }

                    if (appType.equals("pending")) {
                        applicationInstance = pdao.getPendingApplicationById(appId);
                        title = "Pending Application";
                    } else if (appType.equals("processed")) {

                        applicationInstance = adao.getApplicationById(appId);

                        title = "Processed Application";

                    } else {
                        applicationInstance = rdao.getReviewApplicationById(appId);
                        title = "Under Review Application";
                        showUnderReviewApplication = true;
                        target = "viewUnderReviewApplication.jsp";
//                        rdao.getAllReviewApplicationsByName(companyName)
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (applicationInstance != null) {
                    showApplication = true;
                } else {
                    showApplication = false;
//                    response.sendRedirect("login_page.jsp");
                }
            }

//    get the company name
//            companyName = company.getcName();
//            companyType = company.getcType();
            //    get the applicatiodao object
//            adao = new ApplicationDao(ConnectionProvider.getConnection());
//            appId = Integer.parseInt(request.getParameter("appid"));
//            applicationInstance = adao.getApplicationById(appId);
//            pagno = Integer.parseInt(request.getParameter("page"));
//    get the result
//            if (companyType.equals("regUser")) {
//                targetHome = "application_pagination.jsp";
//            } else if (companyType.equals("fAnalyst")) {
//                targetHome = "analystViewProcessedApplication.jsp";
//                showCompanyName = true;
//
//            } else {
//                response.sendRedirect("login_page.jsp");
//
//            }

        %>
        <%--<%= // appId%>--%>
        <%--<%= // pagno%>--%>
    </body>

    <!--navbar-->
    <nav class="navbar navbar-expand-lg navbar-dark primary-background">
        <a class="navbar-brand" href="<%= targetHome%>"><span class="fa fa-asterisk"></span>  CredApp</a>
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
                <a href="<%= target%>?page=<%=pagno%>&appType=<%=appType%>" class="btn btn-outline-light my-2 my-sm-0" type="submit"><span class="fa fa-user-times"></span > Back</a>
            </form>
            <form class="form-inline my-2 my-lg-0 ">
                <!--<a href="-->
                <%--<%= // targetHome%>--%>
                <!--"?page=-->
                <%--<%= // pagno + 1%>--%>
                <!--"-->
                <!--                   class="btn btn-outline-light my-2 my-sm-0"
                                   type="submit">
                                    <span class="fa fa-user-times"></span > Back</a>
                            </form>-->
        </div>
    </nav>

    <br>

    <!--show applications-->
    <div class="applications">
        <div class="container text-center">
            <h1 style="color:  teal">Application </h1>
        </div>

        <br>
        <% if (appId
                    != 0) {%>




        <!--application  details-->
        <div class="container text-left" id="applications">

            <%
                if (applicationInstance != null) {
                    boolean aStatus = applicationInstance.getStatus().equals("Approved");
                    boolean showApprovedFields = aStatus ? true : false;
                    String color = appType.equals("underReview") ? "goldenrod" : appType.equals("pending") ? "grey" : aStatus ? "green" : "red";
                    String reasonText = showApprovedFields ? "Reason (Application rejected by automated system)" : "Reason of rejection";
            %>



            <br>
            <center>
                <b > <h4 style="color:  teal">  Application Id:

                        <%= applicationInstance.getId()%>
                    </h4>
                </b>
            </center>
            <br>

            <table class="table">

                <tbody class="text-center">
                    <%if (showCompanyName) {%>
                    <tr>
                        <th scope="row">Company Name : </th>
                        <td><%= applicationInstance.getCompanyName()%></td>
                    </tr>
                    <%}%>
                    <tr>
                    <tr>
                        <th scope="row">Loan Amount : </th>
                        <td><%= applicationInstance.getLoanAmount()%></td>
                    </tr>
                    <tr>
                        <th scope="row">Period : </th>
                        <td><%= applicationInstance.getPeriod()%></td>

                    </tr>
                    <tr>
                        <th scope="row">Discount : </th>
                        <td><%= applicationInstance.getDiscount()%></td>
                    </tr>
                    <tr>

                        <th scope="row">Cibil : </th>
                        <td><%= applicationInstance.getCibil()%></td>
                    </tr>
                    <tr>

                        <th scope="row">Bank Account No. : </th>
                        <td><%= applicationInstance.getBankAcc()%></td>

                    </tr>
                    <tr>
                        <th scope="row">Revenue : </th>
                        <td><%= applicationInstance.getRevenue()%></td>
                    </tr>
                    <tr>

                        <th scope="row">Employee Expense : </th>
                        <td>
                            <%= applicationInstance.getEmpExpense()%>
                        </td>

                    </tr>

                    <tr>
                        <th scope="row">Status : </th>
                        <td style="color: <%=color%>"><b><%= applicationInstance.getStatus()%></b></td>

                    </tr>


                    <% if (showApprovedFields) {%>

                    <tr>

                        <th scope="row">Total Amount : </th>
                        <td>
                            <%= applicationInstance.getTotalAmt()%>
                        </td>

                    </tr>
                    <tr>

                        <th scope="row">Interest: </th>
                        <td>
                            <%= applicationInstance.getInterest()%>
                        </td>

                    </tr>
                    <% }%>

                    <tr>

                        <th scope="row">Comment by Reviewer: </th>
                            <%if ((applicationInstance.getComment()) != null) {%>

                        <td>
                            <%= applicationInstance.getComment()%>
                        </td>

                    </tr>
                    <%} else {%>
                <td>
                    No Comment specified
                </td>
                <%}%>
                <tr>
                    <%if ((applicationInstance.getReason()) != null) {
                            if (!((applicationInstance.getReason()).isEmpty())) {

                    %>

                    <th scope="row">
                        <%=reasonText%>
                    </th>
                    <td>
                        <%= applicationInstance.getReason()%>
                    </td>

                    <%
                    } else {
                    %>
                    <th scope="row">
                        Reason :
                    </th>
                    <td>
                        No reason specified
                    </td>


                    <%
                        }
                    } else {%>
                    <th scope="row">
                        Reason :
                    </th>
                    <td>
                        No reason specified
                    </td>

                    <%}
                    %>
                </tr>

                <tr>
                    <th scope="row">Date : </th>
                    <td ><%= applicationInstance.getApplicationDate()%></td>

                </tr>



                </tbody>
            </table>


            <% }
            } else {
            %>
            <center><h5 style="color: grey">No Application is <%=(showUnderReviewApplication) ? "under review" : appType%> </h5></center>
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

</html>
