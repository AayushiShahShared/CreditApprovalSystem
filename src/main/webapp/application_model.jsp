<%@page import="java.sql.ResultSet"%>
<%@page import="com.credapp.dao.ApplicationDao"%>
<%@page import="com.credapp.helper.ConnectionProvider"%>
<%@page import="com.credapp.entities.Company"%>
<%
    boolean showApplication = false;
    Company company = (Company) session.getAttribute("currentCompany");
    if (company == null) {
        response.sendRedirect("login_page.jsp");
    }
//    get the company name
    String companyName = (String) company.getcName();

//    get the applicatiodao object
    ApplicationDao adao = new ApplicationDao(ConnectionProvider.getConnection());

    ResultSet rs;
    boolean f;
//    get the result
    if (companyName.equals("Admin")) {
        rs = adao.getAllApplications();
        f = true;
    } else {

        rs = adao.getApplications(companyName);
        f = false;
    }
%>

<!--applications modal-->

<div class="modal fade" id="applicationModal" tabindex="-1" role="dialog" aria-labelledby="applicationModalTitle" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <!--header-->
            <div class="modal-header primary-background text-white">
                <h5 class="modal-title" id="applicationModalTitle">Applications</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>


            <!--body-->
            <div class="modal-body">

                application title
                <div class="container text-center">
                    <h4 class="modal-title" id="companyName">

                        <%= companyName%>
                    </h4>
                    <br>
                </div>

                <!--application  details-->
                <div class="container text-left" id="applications">

                    <%
                        while (rs.next()) {
                    %>
                    <br>
                    <b > <h5>  Application Id:

                            <%= rs.getInt("appId")%></h5>
                            <% boolean showCompanyName = companyName.equals("Admin") ? true : false;%>
                            <% String color = rs.getString("appStatus").equals("Approved") ? "green" : "red";%>
                            <% boolean showApprovalFields = rs.getString("appStatus").equals("Approved") ? true : false;%>

                    </b>
                    <br>
                    <table class="table">

                        <tbody>

                            <% if (showCompanyName) {%>
                            <tr>
                                <th scope="row">Company Name: </th>
                                <td><%= rs.getString("appCompanyName")%></td>
                            </tr>
                            <% }%>
                            <tr>
                                <th scope="row">Loan Amount : </th>
                                <td><%= rs.getInt("appLoanAmount")%></td>
                            </tr>
                            <tr>
                                <th scope="row">Period : </th>
                                <td><%= rs.getInt("appPeriod")%></td>

                            </tr>
                            <tr>
                                <th scope="row">Rate : </th>
                                <td><%= rs.getInt("appRate")%></td>
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
                            <% if (showApprovalFields) {%>
                            <tr>
                                <th scope="row"> Total Amount: </th>
                                <td><%= rs.getFloat("appTotalAmt")%></td>
                            </tr>


                            <tr>
                                <th scope="row">Interset : </th>
                                <td><%= rs.getFloat("appInterest")%></td>

                            </tr>
                            <% }%>
                            <tr>
                                <th scope="row">Status : </th>
                                <td style="color: <%= color%>"><b><%= rs.getString("appStatus")%></b></td>

                            </tr><tr>
                                <th scope="row">Date : </th>
                                <td ><%= rs.getTimestamp("appDate")%></td>

                            </tr>

                            <tr>
                                <th scope="row">Id : </th>
                                <td>
                                    <%--<%= company.getcId()%>--%>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Email : </th>
                                <td>
                                    <%--<%= company.getcEmail()%>--%>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Registraion No. : </th>
                                <td>
                                    <%--<%= company.getcRNo()%>--%>
                                </td>

                            </tr>
                            <tr>
                                <th scope="row">Status : </th>
                                <td>
                                    <%--<%= company.getcStatus()%>--%>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Category : </th>
                                <td>
                                    <%--<%= company.getcCategory()%>--%>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Class : </th>
                                <td>
                                    <%--<%= company.getcClass()%>--%>
                                </td>
                            </tr>

                            <tr>
                                <th scope="row">Age : </th>
                                <td>
                                    <%--<%= company.getcAge()%>--%>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Size : </th>
                                <td>
                                    <%--<%= company.getcSize()%>--%>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Office Locs : </th>
                                <td>
                                </td>


                            </tr>

                            <tr>
                                <th scope="row">About : </th>
                                <td>
                                    <%--<%= company.getcAbout()%>--%>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Registration Date :</th>
                                <td>

                                </td>

                            </tr>
                        </tbody>
                    </table>
                    <%    }
                    %>
                </div>


            </div>
            <!--footer-->
            <div class="modal-footer text-center">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>


            </div>

        </div>
    </div>
</div>
