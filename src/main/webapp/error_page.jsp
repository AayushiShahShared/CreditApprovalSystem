
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
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


        <div class="container text-center">

            <img src="img/error.png" alt="Error image " class="img-fluid" height="400px" width="400px"/>
            <br>
            <h4 class="display-4">Sorry ! Something went wrong... </h4>
            <br>
            <%= exception%>
            <br>
            <a href="index.jsp" class="btn primary-background text-white"> Home </a>
        </div>
    </body>
</html>
