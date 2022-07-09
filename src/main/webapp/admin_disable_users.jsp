<%--
    Document   : admin_delete_users
    Created on : 30-May-2022, 8:11:14 PM
    Author     : Aayushi
--%>

<%@page import="com.credapp.dao.CompanyDao"%>
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
    boolean showUsers = false;
//    Application applicationInstance = new Application();
    Connection con = null;
    CompanyDao cdao = null;
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

        if (companyType.equals("admin")) {
            try {

                con = ConnectionProvider.getConnection();
                cdao = new CompanyDao(con);
                rs = cdao.getAllCompanies();

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
        <title>Admin Delete Users page</title>

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
                    <a href="admin_home.jsp" class="btn btn-outline-light my-2 my-sm-0" type="submit"><span class="fa fa-user-times"></span > Back</a>
                </form>
            </div>
        </nav>

        <br>


        <!--show applications-->
        <div class="applications">
            <div class="container text-center">
                <h1 style="color:  teal">Enable/Disable Companies Account</h1>
            </div>

            <br>


            <!--application  details-->
            <div class="container text-left" id="applications">

                <%                   while (rs.next()) {
                        String type = rs.getString("cType");
                        String color = rs.getString("cVerify").equals("yes") ? "green" : "red";
                        String verifyString = rs.getString("cVerify").equals("yes") ? "Verified" : "Not Verified";
                        String isEnable = rs.getString("isEnable");
                        String btnContent = isEnable.equalsIgnoreCase("true") ? "Disable" : "Enable";
                        String enableContent = isEnable.equalsIgnoreCase("true") ? "Enabled" : "Disabled";
                        String enableColor = isEnable.equalsIgnoreCase("true") ? "green" : "red";
                        String btnColor = isEnable.equalsIgnoreCase("true") ? "red" : "green";
                        if (!type.equals("admin")) {
                %>

                <br>
                <b> <h4 style="color:  teal">  Company Id:

                        <%= rs.getInt("cId")%>
                    </h4>
                </b>
                <br>

                <table class="table">

                    <tbody>
                        <tr>
                            <th scope="row">Company Name: </th>
                            <td><%= rs.getString("cName")%></td>
                        </tr>
                        <tr>
                            <th scope="row">Company registration number : </th>
                            <td>
                                <%= rs.getInt("cRno")%>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">Company Status : </th>
                            <td>
                                <%= rs.getString("cStatus")%>
                            </td>

                        </tr>
                        <tr>
                            <th scope="row">Company Category : </th>
                            <td><%= rs.getString("cCategory")%></td>
                        </tr>
                        <tr>

                            <th scope="row">Company class : </th>
                            <td><%= rs.getString("cClass")%></td>
                        </tr>
                        <tr>

                            <th scope="row">Company Age : </th>
                            <td><%= rs.getInt("cAge")%></td>

                        </tr>
                        <tr>
                            <th scope="row">Company Size : </th>
                            <td><%= rs.getInt("cSize")%></td>
                        </tr>
                        <tr>
                        <tr>
                            <th scope="row">Company Office Locations : </th>
                            <td>
                                <%
                                    String locs = rs.getString("cOfficeLocs");
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
                            <td><%= rs.getString("cEmail")%></td>
                        </tr>
                        <tr>

                            <th scope="row">Company About : </th>
                            <td>
                                <%= rs.getString("cAbout")%>
                            </td>

                        </tr>


                        <tr>
                            <th scope="row">Registration TimeStamp : </th>
                            <td ><%= rs.getTimestamp("cRDate")%></td>

                        </tr>

                        <tr>
                            <th scope="row">Account Verification : </th>
                            <td style="color: <%= color%>"><b><%= verifyString%>
                                </b></td>


                        </tr>
                        <tr>
                            <th scope="row">Account Enable/disable : </th>
                            <td style="color: <%=enableColor%>"><b><%= enableContent%>
                                </b></td>


                        </tr>
                        <tr>
                            <th scope="row">Account Type : </th>
                            <td ><%= rs.getString("cType")%></td>

                        </tr>

                        <tr>
                            <td >
                            </td>
                            <td>

                                <!--<center>-->
                                <a href='DisableCompanyServlet?name=<%= rs.getString("cName")%>&email=<%= rs.getString("cEmail")%>&isEnable=<%= btnContent%>' class='btn' name='delete-btn' id='delete-btn'
                                   style="background-color: <%= btnColor%>;
                                   border: none;
                                   color: white;
                                   padding: 7px 30px;
                                   margin-top: 10px;
                                   text-align: center;
                                   text-decoration: none;
                                   display: inline-block;
                                   font-size: 20px;"><b> <%= btnContent%></b></a>
                                <!--</center>-->

                            </td>

                        </tr>
                        <!--
                                                <tr>
                                                    <td>
                                                        <a href='ReviewApplicationServlet?name=-->
                        <%--<%= // rs.getInt("cName")%>--%>
                        <!--                                   &deleteStatus=delete' class="btn" id="delete-btn" name="delete-btn"
                                                           style="background-color: red;
                                                           border: none;
                                                           color: white;
                                                           padding: 7px 30px;
                                                           margin-left: 30px;
                                                           text-align: center;
                                                           text-decoration: none;
                                                           display: inline-block;
                                                           font-size: 20px;"><b>Delete</b></a>
                                                    </td>
                                                </tr>-->
                    </tbody>
                </table>


                <!--<button name="approved-btn" id="approved-btn" onclick="submit(event, 'approve')">Approve</button>-->
                <!--<button name= "reject-btn" id="reject-btn" value=" // rs.getInt("appId")">Reject</button>-->

                <%
                        }
                    }
                %>
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


