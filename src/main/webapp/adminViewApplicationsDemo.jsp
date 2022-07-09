<%--
    Document   : analystViewUsers
    Created on : 28-May-2022, 11:23:06 PM
    Author     : Aayushi
--%>

<%@page import="com.credapp.dao.ApplicationDao"%>
<%@page import="com.credapp.helper.ConnectionProvider"%>
<%@page import="com.credapp.entities.Company"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.credapp.dao.CompanyDao"%>
<%@page import="java.sql.Connection"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    boolean showApplications = false;
    Connection con = null;
    ApplicationDao adao = null;
    ResultSet rs = null;
    Company company = (Company) session.getAttribute("currentCompany");
    String companyName = null;
    String companyType = null;
    if (company == null) {
        response.sendRedirect("login_page.jsp");
    } else {
        try {
// get the company name
            companyName = (String) company.getcName();
            companyType = (String) company.getcType();

            con = ConnectionProvider.getConnection();
            adao = new ApplicationDao(con);
            rs = adao.getAllApplications();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin View Applications page</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

    </head>
    <body>
        <div class="container">
            <table class="responsive-table">
                <caption>List of all Applications</caption>
                <thead>
                    <tr>
                        <th scope="col">Application Id</th>
                        <th scope="col">Company Name</th>
                        <th scope="col">Loan Amount </th>
                        <th scope="col">Period</th>
                        <th scope="col">Rate</th>
                        <th scope="col">Cibil</th>
                        <th scope="col">Bank Account Number</th>
                        <th scope="col">Revenue</th>
                        <th scope="col">Employee Expense</th>
                        <th scope="col">Total Amount</th>
                        <th scope="col">Interest</th>
                        <th scope="col">Status</th>
                        <th scope="col">About</th>
                        <th scope="col">Registration Date</th>

                    </tr>
                </thead>
                <%                    try {
                        while (rs.next()) {
                %>
                <tr bgcolor="">

                    <% String color = rs.getString("appStatus").equals("Approved") ? "green" : rs.getString("appStatus").equals("Rejected") ? "red" : "grey";%>
                    <% boolean showApprovalFields = rs.getString("appStatus").equals("Approved") ? true : false;%>

                    <td><%=rs.getInt("appId")%></td>
                    <td><%=rs.getString("appCompanyName")%></td>
                    <td><%=rs.getInt("appLoanAmount")%></td>
                    <td><%=rs.getInt("appPeriod")%></td>
                    <td><%=rs.getInt("appRate")%></td>
                    <td><%=rs.getInt("appCibil")%></td>
                    <td><%=rs.getString("appBankAcc")%></td>
                    <td><%=rs.getInt("appRevenue")%></td>
                    <td><%=rs.getInt("appEmpExpense")%></td>
                    <% if (showApprovalFields) {%>
                    <td><%=rs.getFloat("appTotalAmt")%></td>
                    <td><%=rs.getFloat("appInterest")%></td>
                    <% } else {%>
                    <td> Nill </td>
                    <td> Nill </td>
                    <% }%>

                    <td style="color: <%= color%>"><b><%=rs.getString("appStatus")%></b></td>
                    <td><%=rs.getTimestamp("appDate")%></td>

                </tr>

                <%
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                %>

            </table>
        </div>
    </tr>

    <a href="index.jsp">
        <button class="button button2">Go Back</button>
    </a>
    <style>
        .button {
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }
        .button2 {background-color: #008CBA;} /* Blue */
    </style>

</body>
</html>
