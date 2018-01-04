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
        <div style="height: 70%; width: 60%; position: absolute; margin-left: 20%;">
            <div class="header" style="text-align: center; background-color: transparent;">
                <h1 style="color: black; font-size: 80pt;">JAMMER!</h1>
            </div>
            <img src="wwvergeten.png" alt="ERROR" style="width: 40%; margin-left:30%;"/>

            <p style="text-align: center; font-size: 10pt;">contacteer de admin voor verdere hulp.</p>
            <p style="text-align: center;"><a href="${pageContext.request.contextPath}">Click here to go back</a></p>
        </div>
    </body>
</html>
