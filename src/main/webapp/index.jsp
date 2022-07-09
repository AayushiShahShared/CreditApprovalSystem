

<%@page import="com.credapp.scheduler.SubmitPendingApplication"%>
<%@page import="org.quartz.impl.StdSchedulerFactory"%>
<%@page import="org.quartz.Scheduler"%>
<%@page import="org.quartz.TriggerBuilder"%>
<%@page import="org.quartz.Trigger"%>
<%@page import="org.quartz.Trigger"%>
<%@page import="org.quartz.CronScheduleBuilder"%>
<%@page import="org.quartz.JobDetail"%>
<%@page import="org.quartz.JobBuilder"%>
<%@page import="com.credapp.scheduler.ReviewApplicationForm"%>
<%@page import="com.credapp.helper.ConnectionProvider"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
        <!--css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .banner-background{
                clip-path: polygon(50% 0%, 100% 0, 100% 43%, 100% 91%, 68% 100%, 35% 94%, 0 100%, 0 51%, 0 0);
            }
        </style>
    </head>

    <body>

        <!--navbar-->
        <%@include file="outside_navbar.jsp" %>

        <!--banner-->
        <div class="container-fluid m-0 p-0
             banner-background
             " >
            <div class="jumbotron

                 primary-background
                 text-white ">
                <div class="container">
                    <h3 class="display-3">CredApp</h3>

                    <p>Welcome to Credit Approval System</p>

                    <p>Banks in the process of financial intermediation are confronted with various kinds of financial
                        and non-financial risks viz., credit, interest rate, foreign exchange rate, liquidity, equity price,
                        commodity price, legal, regulatory, reputational, operational, etc. These risks are highly
                        interdependent and events that affect one area of risk can have ramifications for a range of other
                        risk categories</p>
                    <br>

                    <br>

                    <!--                    <p>A programming language is any set of rules that converts strings, or graphical program elements in the case of visual programming languages, to various kinds of machine code output. Programming languages are one kind of computer language, and are used in computer programming to implement algorithms.</p>
                                        <br>
                                        <p>Most programming languages consist of instructions for computers. There are programmable machines that use a set of specific instructions, rather than general programming languages. Since the early 1800s, programs have been used to direct the behavior of machines such as Jacquard looms, music boxes and player pianos.[1] The programs for these machines (such as a player piano's scrolls) did not produce different behavior in response to different inputs or conditions.</p>
                                        <br>-->
                    <!--                    <p>                 Thousands of different programming languages have been created, and more are being created every year. Many programming languages are written in an imperative form (i.e., as a sequence of operations to perform) while other languages use the declarative form (i.e. the desired result is specified, not how to achieve it).</p>
                                        <br>
                                        <p>The description of a programming language is usually split into the two components of syntax (form) and semantics (meaning), which are usually defined by a formal language. Some languages are defined by a specification document (for example, the C programming language is specified by an ISO Standard) while other languages (such as Perl) have a dominant implementation that is treated as a reference. Some languages have both, with the basic language defined by a standard and extensions taken from the dominant implementation being common.</p>-->

                    <a href="register_page.jsp" class="btn btn-outline-light btn-lg"> <span class="fa fa-external-link"></span>   Register here ! to Start... </a>
                    <a href="login_page.jsp" class="btn btn-outline-light btn-lg">  <span class="fa fa-user-circle-o fa-spin"></span>  Login </a>
                </div>
            </div>
        </div>

        <!--cards-->
        <div class="container ">

            <h3 class="m-5">Trusted Partners..</h3>
            <div class="row ml-5 mb-3 ">
                <div class="col-md-5">
                    <div class="card" >

                        <div class="card-body">
                            <h5 class="card-title">Credit Risks</h5>
                            <p class="card-text">Lending involves a number of risks. In addition to the risks related to creditworthiness of
                                the counter party, the banks are also exposed to interest rate, forex and country risks.</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <div class="card" >

                        <div class="card-body">
                            <h5 class="card-title">Market Risks</h5>
                            <p class="card-text">Market risk is the possibility that an individual or other entity will experience losses due to factors that affect the overall performance of investments in the financial markets.</p>
                        </div>
                    </div>
                </div>

            </div>

            <div class="row ml-5 mb-3">
                <div class="col-md-5">
                    <div class="card" >

                        <div class="card-body">
                            <h5 class="card-title">Operational Risks</h5>
                            <p class="card-text">Operational risk summarizes the uncertainties and hazards a company faces when it attempts to do its day-to-day business activities.</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <div class="card" >

                        <div class="card-body">
                            <h5 class="card-title">Liquidity Risks</h5>
                            <p class="card-text">The Bank liquidity risk management planning process safeguards the funding of long-term assets by short-term liabilities during the project risk management process.</p>
                        </div>
                    </div>
                </div>

            </div>


        </div>



        <%

//    define job
//            JobDetail job = JobBuilder.newJob(ReviewApplicationForm.class).build();
            JobDetail job = JobBuilder.newJob(SubmitPendingApplication.class).build();
//    create trigger
//    Trigger t1 =  TriggerBuilder.newTrigger().withIdentity("simpleTrigger").startNow().build();
//    Trigger t1 =  TriggerBuilder.newTrigger().withIdentity("CronTrigger").withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")).build();
//            Trigger t1 = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")).build();
            Trigger t1 = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0 0/5 * 1/1 * ? *")).build();
//            Trigger t2 = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0 0/10 * 1/1 * ? *")).build();
//    trigger job
            Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
            sc.start();
            sc.scheduleJob(job, t1);

        %>

        <!--javascript-->
        <script src="js/myjs.js" type="text/javascript"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <!--    <script>
                $(document).ready(function () {
                    alert("document loaded")
                })
            </script>-->
    </body>




</html>
