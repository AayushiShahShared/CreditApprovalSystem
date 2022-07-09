<%--
    Document   : analyst_details
    Created on : 01-Jun-2022, 12:39:00 PM
    Author     : Aayushi
--%>

<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="com.credapp.dao.CompanyDao"%>
<%@page import="com.credapp.helper.ConnectionProvider"%>
<%@page import="com.credapp.entities.Company"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Company company = (Company) session.getAttribute("currentCompany");
    boolean showPage = false;
    String companyName = "";
    String companyType = "";
    String dbstatus = "";
    CompanyDao cdao = null;
    if (company == null) {
        response.sendRedirect("login_page.jsp");
    } else {
        // get the company name
        companyType = (String) company.getcType();
        cdao = new CompanyDao(ConnectionProvider.getConnection());

        if (companyType.equals("fAnalyst")) {
            showPage = true;
            company = cdao.getCompanyByType(companyType);
            if (company == null) {
                response.sendRedirect("login_page.jsp");

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
        <title>JSP Page</title>

        <!--css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/form.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <!--        <h1>Hello World!</h1>
                <h1>admin delete view company</h1>-->

        <br>
        <br>





        <!--show applications-->
        <div class="company">
            <div class="container text-center">
                <h1 style="color:  teal">Account Details</h1>
            </div>

            <br>


            <br>
            <b > <h4 style="color:  red;
                     margin-left: 290px;">  Company Name :

                    <%= company.getcName()%>
                </h4>
            </b>
            <br>

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
                        <th scope="row">Age : </th>
                        <td><%= company.getcAge()%></td>
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
            <center>
                <a href='analyst_home.jsp'  class="btn" id="back-btn" name="back-btn"
                   style="background-color: teal;
                   border: none;
                   color: white;
                   padding: 7px 20px;
                   /*text-align: center;*/
                   text-decoration: none;
                   display: inline-block;
                   font-size: 20px;"><b> Back </b></a>
            </center>
            <br>
            <br>
        </div>





        <!--javascript-->
        <script src="js/myjs.js" type="text/javascript"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>

    </body>
</html>
