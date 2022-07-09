<%--
    Document   : adminViewPendingApplications
    Created on : 30-May-2022, 10:51:09 PM
    Author     : Aayushi
--%>

<%@page import="com.credapp.helper.ConnectionProvider"%>
<%@page import="com.credapp.entities.Company"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.credapp.dao.ReviewApplicationDao"%>
<%@page import="com.credapp.dao.PendingApplicationDao"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    boolean showApplications = false;
    boolean showLine = false;
    boolean showReview = false;
    Connection con = null;
    PendingApplicationDao pdao = null;
    ReviewApplicationDao rdao = null;
    ResultSet prs = null;
    ResultSet rrs = null;
    String target = "";
    Company company = (Company) session.getAttribute("currentCompany");
    String companyName = null;
    String companyType = null;
    if (company == null) {
        response.sendRedirect("login_page.jsp");
    } else {
        // get the company name
        companyName = (String) company.getcName();
        companyType = (String) company.getcType();

        if (!companyType.equals("regUser")) {
            try {

                con = ConnectionProvider.getConnection();
                pdao = new PendingApplicationDao(con);
                prs = pdao.getAllPendingApplications();
                target = "analyst_home.jsp";
                if (companyType.equals("admin")) {
                    rdao = new ReviewApplicationDao(con);
                    rrs = rdao.getAllReviewApplications();
                    showLine = true;
                    target = "admin_home.jsp";
                    showReview = true;
                }

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
        <title>admin View Pending Applications</title>

        <!--css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/form.css" rel="stylesheet" type="text/css"/>

        <!--        <meta name="viewport" content="width=device-width">
                <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">-->

    </head>
    <body>
        <!--navbar-->
        <nav class="navbar navbar-expand-lg navbar-dark primary-background">
            <a class="navbar-brand" href="<%= target%>"><span class="fa fa-asterisk"></span>  CredApp</a>
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
                    <a href="<%= target%>" class="btn btn-outline-light my-2 my-sm-0" type="submit"><span class="fa fa-user-times"></span > Back</a>
                </form>
            </div>
        </nav>

        <br>
        <!--show applications-->
        <div class="applications">
            <br>
            <% if (showReview) {%>
            <div class="container text-center">
                <h2 style="color:  teal">Under Review Applications</h2>
            </div>

            <br>



            <!--application  details-->
            <div class="container text-left" id="review-applications">

                <%
                    if (rdao.getReviewApplicationsCount() > 0) {
                        while (rrs.next()) {
                %>

                <br>
                <b > <h4 style="color:  teal">  Application Id:

                        <%= rrs.getInt("appId")%>
                    </h4>
                </b>
                <br>

                <table class="table">

                    <tbody>

                        <tr>
                            <th scope="row">Company Name: </th>
                            <td><%= rrs.getString("appCompanyName")%></td>
                        </tr>

                        <tr>
                            <th scope="row">Loan Amount : </th>
                            <td><%= rrs.getInt("appLoanAmount")%></td>
                        </tr>
                        <tr>
                            <th scope="row">Period : </th>
                            <td><%= rrs.getInt("appPeriod")%></td>

                        </tr>
                        <tr>
                            <th scope="row">Rate : </th>
                            <td><%= rrs.getInt("appRate")%></td>
                        </tr>
                        <tr>

                            <th scope="row">Cibil : </th>
                            <td><%= rrs.getInt("appCibil")%></td>
                        </tr>
                        <tr>

                            <th scope="row">Bank Account No. : </th>
                            <td><%= rrs.getString("appBankAcc")%></td>

                        </tr>
                        <tr>
                            <th scope="row">Revenue : </th>
                            <td><%= rrs.getInt("appRevenue")%></td>
                        </tr>
                        <tr>

                            <th scope="row">Employee Expense : </th>
                            <td>
                                <%= rrs.getInt("appEmpExpense")%>
                            </td>

                        </tr>
                        <tr>
                            <th scope="row">Status : </th>
                            <td style="color: goldenrod"><b>Under Review</b></td>

                        </tr>
                        <tr>
                            <th scope="row">Date : </th>
                            <td ><%= rrs.getTimestamp("appDate")%></td>

                        </tr>




                    </tbody>
                </table>


                <% }
                } else {
                %>
                <center><h5 style="color: grey">No Application is Under Review </h5></center>
                <br>
                <br>
                <% }%>
            </div>
            <% } %>

            <% if (showLine) {%>

            <br>

            <hr>
            <br>
            <% } %>

            <div class="container text-center">
                <h2 style="color:  teal">Pending Applications</h2>
            </div>

            <br>

            <!--application  details-->
            <% if (pdao.getPendingApplicationsCount() > 0) {%>

            <div class="container text-left" id="pending-applications">

                <%                    while (prs.next()) {
                %>

                <br>
                <b > <h4 style="color:  teal">  Application Id:

                        <%= prs.getInt("appId")%>
                    </h4>
                </b>
                <br>

                <table class="table">

                    <tbody>

                        <tr>
                            <th scope="row">Company Name: </th>
                            <td><%= prs.getString("appCompanyName")%></td>
                        </tr>

                        <tr>
                            <th scope="row">Loan Amount : </th>
                            <td><%= prs.getInt("appLoanAmount")%></td>
                        </tr>
                        <tr>
                            <th scope="row">Period : </th>
                            <td><%= prs.getInt("appPeriod")%></td>

                        </tr>
                        <tr>
                            <th scope="row">Rate : </th>
                            <td><%= prs.getInt("appRate")%></td>
                        </tr>
                        <tr>

                            <th scope="row">Cibil : </th>
                            <td><%= prs.getInt("appCibil")%></td>
                        </tr>
                        <tr>

                            <th scope="row">Bank Account No. : </th>
                            <td><%= prs.getString("appBankAcc")%></td>

                        </tr>
                        <tr>
                            <th scope="row">Revenue : </th>
                            <td><%= prs.getInt("appRevenue")%></td>
                        </tr>
                        <tr>

                            <th scope="row">Employee Expense : </th>
                            <td>
                                <%= prs.getInt("appEmpExpense")%>
                            </td>

                        </tr>
                        <tr>
                            <th scope="row">Status : </th>
                            <td style="color: grey"><b><%= prs.getString("appStatus")%></b></td>

                        </tr>
                        <tr>
                            <th scope="row">Date : </th>
                            <td ><%= prs.getTimestamp("appDate")%></td>

                        </tr>




                    </tbody>
                </table>


                <% }
                } else {
                %>
                <center><h5 style="color: grey">No pending Applications</h5></center>
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

    </body>
</html>


