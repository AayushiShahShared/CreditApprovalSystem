<%@page  import="com.credapp.entities.Company"%>

<%
    Company company = (Company) session.getAttribute("currrentCompany");
%>
<link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
<nav class="navbar navbar-expand-lg navbar-dark primary-background">
    <a class="navbar-brand" href="home_page.jsp"><span class="fa fa-asterisk"></span>  CredApp</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">


            <li class="nav-item">
                <a class="nav-link" href="ApplicationsServlet">
                    <span class="fa fa-caret-square-o-right">   </span> Applications </a>
            </li>
            <!--            <li>
                            <a class="nav-link" href="company_profile_modal.jsp">
                                <span class="fa fa-address-card-o">   </span>  Company Details
                            </a>

                        </li>-->
            <li>
                <a class="nav-link" type="button" class="btn btn-primary" data-toggle="modal" data-target="#companyProfileModal">
                    <span class="fa fa-address-card-o">   </span> Company Details
                </a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0 ">
            <a href="LogoutServlet" class="btn btn-outline-light my-2 my-sm-0" type="submit"><span class="fa fa-user-times"></span > Logout</a>
        </form>
    </div>
</nav>



<!-- Company Profile Modal -->
<div class="modal fade" id="companyProfileModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Company Profile <%= company.getcName()%> </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
<!--end of company profile model-->


