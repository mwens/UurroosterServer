<%-- 
    Document   : Error
    Created on : 21-nov-2017, 10:03:49
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css">
        <title>Error</title>
    </head>
    <body>
        <div style="height: 80%; width: 60%; position: absolute; margin-left: 20%; margin-top: 10%;">
            <img src="error.png" alt="ERROR" style="width: 40%; margin-left:30%;"/>
            <div class="header" style="text-align: center; background-color: transparent;">
                <h1 style="color: black;">Oops! Login Failed.</h1>
            </div>
            <p style="text-align: center;"><a href="${pageContext.request.contextPath}">Click here to go back</a></p>
        </div>
    </body>
</html>
