<%-- 
    Document   : index
    Created on : 21-nov-2017, 10:12:02
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Geheim</h1>
        <p> Welkom <%= request.getUserPrincipal() %>! Op deze pagina staat keigeheime informatie!</p>
    </body>
</html>
