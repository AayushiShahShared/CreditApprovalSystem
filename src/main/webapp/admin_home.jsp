<%--
    Document   : admin_home
    Created on : 28-May-2022, 3:27:25 PM
    Author     : Aayushi
--%>

<%@page import="com.credapp.dao.CompanyDao"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.credapp.dao.ReviewApplicationDao"%>
<%@page import="com.credapp.dao.PendingApplicationDao"%>
<%@page import="com.credapp.dao.PendingApplicationDao"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.credapp.entities.Company" %>
<%@page import="com.credapp.dao.ApplicationDao" %>
<%@page import="com.credapp.helper.ConnectionProvider"%>

<%
    boolean showApplication = false;
    String companyName = null;
    String companyType = null;
    Company company = (Company) session.getAttribute("currentCompany");
    ApplicationDao adao = null;
    PendingApplicationDao pdao = null;
    ReviewApplicationDao rdao = null;
    CompanyDao cdao = null;
    Connection con = null;

    if (company == null) {
        response.sendRedirect("login_page.jsp");
    } else {
        // get the company name
        companyName = (String) company.getcName();
        companyType = (String) company.getcType();

        if (companyType.equals("admin")) {
            con = ConnectionProvider.getConnection();
            pdao = new PendingApplicationDao(con);
            rdao = new ReviewApplicationDao(con);
            adao = new ApplicationDao(con);
            cdao = new CompanyDao(con);
        } else {
            response.sendRedirect("login_page.jsp");
        }
    }
%>


<!DOCTYPE html>
<!-- Designined by CodingLab | www.youtube.com/codinglabyt -->
<html lang="en" dir="ltr">
    <head>
        <meta charset="UTF-8">
        <!--<title> Responsiive Admin Dashboard | CodingLab </title>-->
        <link href="css/adminCss.css" rel="stylesheet" type="text/css"/>
        <!--<link href="newcss.css" rel="stylesheet" type="text/css"/>-->
        <!-- Boxicons CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div class="sidebar">
            <div class="logo-details">
                <i class='bx bxl-c-plus-plus'></i>
                <span class="logo_name">Admin Portal</span>
            </div>
            <ul class="nav-links">
                <li>
                    <a href="admin_home.jsp" class="active">
                        <i class='bx bx-grid-alt' ></i>
                        <span class="links_name">Home</span>
                    </a>
                </li>
                <!--                <li>
                                    <a href="adminViewUsers.jsp">
                                        <i class='bx bx-box' ></i>
                                        <span class="links_name">View Accounts</span>
                                    </a>
                                </li>-->
                <li>
                    <a href="viewAccounts.jsp?page=1">
                        <i class='bx bx-box' ></i>
                        <span class="links_name">View Accounts</span>
                    </a>
                </li>
                <li><a href="admin_disable_users.jsp">

                        <i class='bx bx-list-ul' ></i>
                        <span class="links_name">Disable/Enable Account</span>
                    </a>
                </li>
                <!--                <li>
                                    <a href="adminViewApplications.jsp">
                                        <i class='bx bx-pie-chart-alt-2' ></i>
                                        <span class="links_name">View processed applications</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="adminViewPendingApplications.jsp">
                                        <i class='bx bx-coin-stack' ></i>
                                        <span class="links_name">View unprocessed applications</span>
                                    </a>
                                </li>-->
                <li>
                    <a href="viewUnderReviewApplication.jsp?page=1">
                        <i class='bx bx-coin-stack' ></i>
                        <span class="links_name">Under Review applications</span>
                    </a>
                </li>
                <li><a href="analystViewProcessedApplication.jsp?page=1&appType=pending">

                        <i class='bx bx-list-ul' ></i>
                        <span class="links_name">Pending Applications</span>
                    </a>
                </li>

                <li>
                    <a  href="analystViewProcessedApplication.jsp?page=1&appType=processed">
                        <i class='bx bx-pie-chart-alt-2' ></i>
                        <span class="links_name">
                            Processed Applications
                        </span>
                    </a>
                </li>

                <li class="log_out">
                    <a href="LogoutServlet">
                        <i class='bx bx-log-out'></i>
                        <span class="links_name">Log out</span>
                    </a>
                </li>
            </ul>
        </div>
        <section class="home-section">
            <nav>
                <div class="sidebar-button">
                    <i class='bx bx-menu sidebarBtn'></i>
                    <span class="dashboard">Dashboard</span>
                </div>
                <div class="search-box">
                    <input type="text" placeholder="Search...">
                    <i class='bx bx-search' ></i>
                </div>
                <div class="profile-details">
                    <!--<img src="images/profile.jpg" alt="">-->
                    <span class="admin_name">Cred App</span>
                    <i class='bx bx-chevron-down' ></i>
                </div>
            </nav>
            <!--home content-->
            <div class="home-content">
                <div class="overview-boxes">

                    <div class="box">
                        <div class="right-side">
                            <div class="box-topic">Total Under review applications : </div>
                        </div>
                        <%= rdao.getReviewApplicationsCount()%>

                    </div>

                    <div class="box">
                        <div class="right-side">
                            <div class="box-topic">Total Pending applications : &nbsp; &nbsp;</div>
                        </div>
                        <%= pdao.getPendingApplicationsCount()%>

                    </div>

                    <div class="box">
                        <div class="right-side">
                            <div class="box-topic">Total Approved applications : &nbsp;</div>
                        </div>
                        <%= adao.getApprovedApplicationsCount()%>

                    </div>


                    <div class="box">
                        <div class="right-side">
                            <div class="box-topic">Total Rejected applications : &nbsp;</div>
                        </div>
                        <%= adao.getRejectedApplicationsCount()%>

                    </div>
                    <br>
                    <br>


                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    <div class="box">
                        <div class="right-side">
                            <div class="box-topic">Total Company Accounts : &nbsp; &nbsp;</div>
                        </div>
                        <%= cdao.getCompaniesCount()%>

                    </div>



                </div>
            </div>

        </section>



        <script>
            let sidebar = document.querySelector(".sidebar");
            let sidebarBtn = document.querySelector(".sidebarBtn");
            sidebarBtn.onclick = function () {
                sidebar.classList.toggle("active");
                if (sidebar.classList.contains("active")) {
                    sidebarBtn.classList.replace("bx-menu", "bx-menu-alt-right");
                } else
                    sidebarBtn.classList.replace("bx-menu-alt-right", "bx-menu");
            }
        </script>

    </body>
</html>




<!--<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>admin home</h1>
    </body>
</html>-->
