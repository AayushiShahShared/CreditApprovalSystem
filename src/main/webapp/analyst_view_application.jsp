<%--
    Document   : analyst_view_application
    Created on : 30-May-2022, 1:26:30 PM
    Author     : Aayushi
--%>


<%@page import="com.credapp.helper.ConnectionProvider"%>
<%@page import="com.credapp.dao.CompanyDao"%>
<%@page import="com.credapp.services.MailService"%>
<%@page import="com.credapp.entities.MailMessage"%>
<%@page import="com.credapp.entities.Company"%>
<%@page import="com.credapp.entities.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Company company = (Company) session.getAttribute("currentCompany");
    String companyName = "";
    String companyType = "";
    String status = "";
    boolean mailStatus = false;
    String cEmailMsg = "";
    String cEmailSubject = "CredApp Application report..";
    String cEmailTo = "";
    MailMessage mailObject = null;
    Application currentApplication = null;

    CompanyDao cdao = new CompanyDao(ConnectionProvider.getConnection());
    String dbstatus = "";
    if (company == null) {
        response.sendRedirect("login_page.jsp");
    } else {
        // get the company name
        companyType = (String) company.getcType();

        if (companyType.equals("fAnalyst")) {
            currentApplication = (Application) session.getAttribute("currentapplication");
            companyName = currentApplication.getCompanyName();
            cEmailTo = cdao.getEmailByName(companyName);
//
            dbstatus = (String) session.getAttribute("dbstatus");
            status = currentApplication.getStatus();
            if (status.trim().equals("Approved")) {
                cEmailMsg = " Congratulations ... Your application submitted successfully and also being approved . Total Amount to be paid : " + currentApplication.getTotalAmt() + " and interest is  " + currentApplication.getInterest();
            } else {
                cEmailMsg = "Sorry !! Your application submitted successfully but Your application was rejected... Reason of rejection : " + currentApplication.getReason();
            }

            if (dbstatus.equalsIgnoreCase("deletedone")) {
                mailObject = new MailMessage();
                mailObject.setMessage(cEmailMsg);
                mailObject.setSubject(cEmailSubject);
                mailObject.setTo(cEmailTo);

                MailService mailService = new MailService();

                if (mailService.sendEmail(mailObject)) {
                    mailStatus = true;
                }

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
        <title>Analyst view application Page</title>
        <!--css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/form.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <!--        <h1>Hello World!</h1>
                <h1>Analyst view application Page</h1>
        -->
        <br>
        <br>





        <!--show applications-->
        <div class="applications">
            <div class="container text-center">
                <h1 style="color:  teal">Current Application Submitted Details</h1>
            </div>

            <br>
            <!--            <div class="container text-center">
                            <h2 id="companyName" style="color: orangered">

                            </h2>
                            <br>
                        </div>-->


            <!--application  details-->
            <div class="container text-left" id="applications">

                <% String color = currentApplication.getStatus().equals("Approved") ? "green" : "red";%>
                <% String mailColor = mailStatus ? "green" : "red";%>
                <% String mailText = mailStatus ? "Mail Sent" : "Mail not Sent";%>
                <% boolean showApprovalFields = currentApplication.getStatus().equals("Approved") ? true : false;%>

                <br>
                <b > <h4 style="color:  teal">  Application Id:

                        <%= currentApplication.getId()%>
                    </h4>
                </b>
                <br>

                <table class="table">

                    <tbody>
                        <tr>
                            <th scope="row">Company Name: </th>
                            <td>
                                <%= currentApplication.getCompanyName()%>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">Loan Amount : </th>
                            <td>
                                <%= currentApplication.getLoanAmount()%>

                            </td>
                        </tr>
                        <tr>
                            <th scope="row">Period : </th>
                            <td>
                                <%= currentApplication.getPeriod()%>
                            </td>

                        </tr>
                        <tr>
                            <th scope="row">Discount : </th>
                            <td>
                                <%= currentApplication.getDiscount()%>
                            </td>
                        </tr>
                        <tr>

                            <th scope="row">Cibil : </th>
                            <td>
                                <%= currentApplication.getCibil()%>
                            </td>
                        </tr>
                        <tr>

                            <th scope="row">Bank Account No. : </th>
                            <td>
                                <%= currentApplication.getBankAcc()%>

                            </td>

                        </tr>
                        <tr>
                            <th scope="row">Revenue : </th>
                            <td>
                                <%= currentApplication.getRevenue()%>
                            </td>
                        </tr>
                        <tr>

                            <th scope="row">Employee Expense : </th>
                            <td>
                                <%= currentApplication.getEmpExpense()%>
                            </td>

                        </tr>
                        <% if (showApprovalFields) {%>
                        <tr>
                            <th scope="row"> Total Amount: </th>
                            <td>

                                <%= currentApplication.getTotalAmt()%>
                            </td>
                        </tr>


                        <tr>
                            <th scope="row">Interset : </th>
                            <td>

                                <%= currentApplication.getInterest()%>
                            </td>

                        </tr>
                        <% }%>
                        <tr>
                            <th scope="row">Status : </th>
                            <td style="color: <%= color%>"><b>
                                    <%= currentApplication.getStatus()%>
                                </b></td>

                        </tr>
                        <tr>
                            <th scope="row">Date : </th>
                            <td>
                                <%= currentApplication.getApplicationDate()%>
                            </td>

                        </tr>
                        <%if (currentApplication.getComment() != null) {%>
                        <tr>
                            <th scope="row">Comment : </th>
                            <td>
                                <%= currentApplication.getComment()%>
                            </td>

                        </tr>
                        <%}%>
                        <tr>
                            <th scope="row">Reason : </th>
                            <td>
                                <%= currentApplication.getReason()%>
                            </td>

                        </tr>
                        <tr>

                            <th scope="row">Mail Sent Status : </th>
                            <td style="color: <%= mailColor%>"><b>
                                    <%= mailText%>
                                </b></td>
                        </tr>


                        <!--                        <tr>
                                                    <th scope="row">Company Name: </th>
                                                    <td>-->
                        <%--<%= // companyName%>--%>
                        <!--                            </td>
                                                </tr>-->
                        <!--                        <tr>
                                                    <th scope="row">Email From: </th>
                                                    <td>-->
                        <%--<%= // mailObject.getFrom()%>--%>
                        <!--                            </td>
                                                </tr>-->
                        <tr>
                            <th scope="row">Email To: </th>
                            <td>
                                <%= mailObject.getTo()%>

                            </td>
                        </tr>

                        <!--                        <tr>
                                                    <th scope="row">Email Subject: </th>
                                                    <td>-->
                        <%--<%= // mailObject.getSubject()%>--%>
                        <!--                            </td>
                                                </tr>-->
                        <!--                        <tr>
                                                    <th scope="row">Email Message: </th>
                                                    <td>-->
                        <%--<%= // mailObject.getMessage()%>--%>
                        <!--                            </td>
                                                </tr>-->


                    </tbody>
                </table>
                <center>
                    <a href='analyst_review_application.jsp'  class="btn" id="approve-btn" name="approve-btn"
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
