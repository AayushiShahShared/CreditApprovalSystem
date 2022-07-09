<%--
    Document   : adminViewUsers
    Created on : 28-May-2022, 8:04:54 PM
    Author     : Aayushi
--%>

<%@page import="com.credapp.dao.CompanyDao"%>
<%@page import="com.credapp.dao.ApplicationDao"%>
<%@page import="com.credapp.helper.ConnectionProvider"%>
<%@page import="com.credapp.entities.Company"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%
    boolean showUsers = false;
    Connection con = null;
    CompanyDao cdao = null;
    ResultSet rs = null;
    Company company = (Company) session.getAttribute("currentCompany");
    Company companyInstance = null;
    String companyName = null;
    String companyType = null;
    int cId = 0, pagno = 0;
    if (company == null) {
        response.sendRedirect("login_page.jsp");
    } else {
// get the company name
        companyName = (String) company.getcName();
        companyType = (String) company.getcType();

        if (companyType.equals("admin")) {
            try {
                pagno = Integer.parseInt(request.getParameter("page"));
                cId = Integer.parseInt(request.getParameter("cid"));
                con = ConnectionProvider.getConnection();
                cdao = new CompanyDao(con);
                companyInstance = cdao.getCompanyById(cId);

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
        <title>View Account details Page</title>
        <!--css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/form.css" rel="stylesheet" type="text/css"/>


    </head>
    <body>

        <!--navbar-->
        <nav class="navbar navbar-expand-lg navbar-dark primary-background">
            <a class="navbar-brand" href="admin_home.jsp"><span class="fa fa-asterisk"></span>  CredApp</a>
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
                    <a href="viewAccounts.jsp?page=<%=pagno%>" class="btn btn-outline-light my-2 my-sm-0" type="submit"><span class="fa fa-user-times"></span > Back</a>
                </form>
            </div>
        </nav>

        <br>
        <!--show applications-->
        <div class="allCompanies">
            <div class="container text-center">
                <h1 style="color:  teal">Account details </h1>
            </div>

            <br>
            <!--            <div class="container text-center">
                            <h2 id="companyName" style="color: orangered">
            -->

            <%--<%= // companyInstance.getcName()%>--%>
            <!--                </h2>
                            <br>
                        </div>-->


            <!--application  details-->
            <div class="container text-left" id="companies">

                <%
                    if (companyInstance != null) {
                        String type = companyInstance.getcType();
                        String verifyColor = companyInstance.getVerify().equals("yes") ? "green" : "red";
                        String verifyString = companyInstance.getVerify().equals("yes") ? "Verified" : "Not Verified";
                        String enableColor = companyInstance.getIsEnable().equalsIgnoreCase("true") ? "green" : "red";
                        String enableContent = companyInstance.getIsEnable().equalsIgnoreCase("true") ? "Enabled" : "Disabled";
                        if (!type.equals("admin")) {
                %>

                <br>
                <b > <h4 style="color:  teal">  Company Id:

                        <%= companyInstance.getcId()%>
                    </h4>
                </b>
                <br>

                <table class="table">

                    <tbody>
                        <tr>
                            <th scope="row">Company Name: </th>
                            <td><%= companyInstance.getcName()%></td>
                        </tr>
                        <tr>
                            <th scope="row">Company Registration Number : </th>
                            <td>
                                <%= companyInstance.getcRNo()%>
                            </td>
                        </tr>

                        <!--                        <tr>
                                                    <th scope="row">Company Status : </th>
                                                    <td>-->
                        <%--<%= // rs.getString("cStatus")%>--%>
                        <!--                            </td>
                                                </tr>-->
                        <tr>
                            <th scope="row">Company Category : </th>
                            <td><%= companyInstance.getcCategory()%></td>

                        </tr>
                        <tr>
                            <th scope="row">Company Class : </th>
                            <td><%=companyInstance.getcClass()%></td>
                        </tr>
                        <tr>

                            <th scope="row">Company Age : </th>
                            <td><%= companyInstance.getcAge()%></td>
                        </tr>
                        <tr>
                        <tr>

                            <th scope="row">Company Size : </th>
                            <td><%= companyInstance.getcSize()%></td>
                        </tr>

                        <tr>
                            <th scope="row">Company Office Locs : </th>
                            <td>
                                <%
                                    String locs = companyInstance.getcOfficeLocs();
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
                            <td><%= companyInstance.getcEmail()%></td>
                        </tr>
                        <tr>

                            <th scope="row">Company About : </th>
                            <td>
                                <%= companyInstance.getcAbout()%>
                            </td>

                        </tr>

                        <tr>
                            <th scope="row">Account Verification : </th>
                            <td style="color: <%= verifyColor%>"><b><%= verifyString%>
                                </b></td>


                        </tr>
                        <tr>
                            <th scope="row">Account Enabled : </th>
                            <td style="color: <%= enableColor%>"><b><%= enableContent%>
                                </b></td>


                        </tr>

                        <tr>
                            <th scope="row">Account Type : </th>
                            <td ><b><%= companyInstance.getcType()%>
                                </b></td>


                        </tr>

                        <tr>
                            <th scope="row">Registration TimeStamp : </th>
                            <td ><%= companyInstance.getcRegDateTime()%></td>

                        </tr>



                    </tbody>
                </table>


                <%
                        }
                    }
                %>
            </div>
        </div>


        <!--        <a href="admin_home.jsp">
                    <button class="button button2">Go Back</button>
                </a>-->


        <!--javascript-->
        <script src="js/myjs.js" type="text/javascript"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>





    </body>
</html>
