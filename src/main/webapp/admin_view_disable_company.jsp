<%--
    Document   : admin_view_delete_company
    Created on : 30-May-2022, 9:27:24 PM
    Author     : Aayushi
--%>

<%@page import="com.credapp.dao.CompanyDao"%>
<%@page import="com.credapp.helper.ConnectionProvider"%>
<%@page import="com.credapp.entities.Company"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Company company = (Company) session.getAttribute("currentCompany");
    String companyName = "";
    String companyType = "";
    String disableStatus = "";
    String isEnable = "";
    boolean mailStatus = false;
    Company currentDelCompany = null;
    CompanyDao cdao = new CompanyDao(ConnectionProvider.getConnection());
    String dbstatus = "";
    if (company == null) {
        response.sendRedirect("login_page.jsp");
    } else {
        // get the company name
        companyType = (String) company.getcType();

        if (companyType.equals("admin")) {
            currentDelCompany = (Company) session.getAttribute("currentDisableCompany");

            disableStatus = (String) session.getAttribute("disableStatus");
            isEnable = (String) session.getAttribute("isEnable");
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
        <div class="applications">
            <div class="container text-center">
                <h1 style="color:  teal">Current Company Account Updated Enable/Disable Details</h1>
            </div>

            <br>

            <!--application  details-->
            <div class="container text-left" id="applications">

                <%
                    String color = disableStatus.equals("disableDone") ? "green" : "red";
                    String disableText = disableStatus.equals("disableDone") ? isEnable + " Successfully" : "Not " + isEnable + " Some issue occured";
                    String verifyColor = currentDelCompany.getVerify().equals("yes") ? "green" : "red";
                    String verifyString = currentDelCompany.getVerify().equals("yes") ? "Verified" : "Not Verified";

                %>

                <br>
                <b > <h4 style="color:  teal">  Company Id:

                        <%= currentDelCompany.getcId()%>
                    </h4>
                </b>
                <br>

                <table class="table">

                    <tbody>
                        <tr>
                            <th scope="row">Company Name: </th>
                            <td ><b>
                                    <%= currentDelCompany.getcName()%>
                                </b>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">Company registration number : </th>
                            <td>
                                <%= currentDelCompany.getcRNo()%>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">Company Status : </th>
                            <td>
                                <%= currentDelCompany.getcStatus()%>
                            </td>

                        </tr>
                        <tr>
                            <th scope="row">Company Category : </th>
                            <td><%= currentDelCompany.getcCategory()%></td>
                        </tr>
                        <tr>

                            <th scope="row">Company class : </th>
                            <td><%= currentDelCompany.getcClass()%></td>
                        </tr>
                        <tr>

                            <th scope="row">Company Age : </th>
                            <td><%= currentDelCompany.getcAge()%></td>

                        </tr>
                        <tr>
                            <th scope="row">Company Size : </th>
                            <td><%= currentDelCompany.getcSize()%></td>
                        </tr>
                        <tr>
                        <tr>
                            <th scope="row">Company Office Locations : </th>
                            <td>
                                <%
                                    String locs = currentDelCompany.getcOfficeLocs();
                                    String[] locsList = locs.split(" ");
                                    for (int i = 0; i < locsList.length; i++) {
                                %>
                                <%= locsList[i]%><br>
                                <%
                                    }
                                %>
                            </td>
                        </tr>



                        <tr>
                            <th scope="row">Company Email Id : </th>
                            <td><%= currentDelCompany.getcEmail()%></td>
                        </tr>
                        <tr>

                            <th scope="row">Company About : </th>
                            <td>
                                <%= currentDelCompany.getcAbout()%>
                            </td>

                        </tr>
                        <tr>
                            <th scope="row">Account Verification : </th>
                            <td style="color: <%= verifyColor%>"><b><%= verifyString%>
                                </b></td>


                        </tr>
                        <!--                        <tr>

                                                    <th scope="row">Company Verification : </th>
                                                    <td>
                        <%--<%= // currentDelCompany.getVerify()%>--%>
                    </td>

                </tr>-->
                        <tr>

                            <th scope="row">Company account type: </th>
                            <td>
                                <%= currentDelCompany.getcType()%>
                            </td>

                        </tr>


                        <tr>
                            <th scope="row">Registration TimeStamp : </th>
                            <td ><%= currentDelCompany.getcRegDateTime()%></td>

                        </tr>

                        <tr>
                            <th scope="row">Delete Status : </th>
                            <td style='color: <%= color%>; font-size: 20px'><b>
                                    <%= disableText%>
                                </b>
                            </td>

                        </tr>



                    </tbody>
                </table>
                <center>
                    <a href='admin_disable_users.jsp'  class="btn" id="delete-btn" name="delete-btn"
                       style="background-color: teal;
                       border: none;
                       color: white;
                       padding: 7px 20px;
                       text-align: center;
                       text-decoration: none;
                       display: inline-block;
                       font-size: 20px;"><b> Back </b></a>
                </center>
                <br>
                <br>

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
