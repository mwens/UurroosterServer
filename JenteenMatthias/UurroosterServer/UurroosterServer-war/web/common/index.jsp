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
        <title>login</title>
    </head>
    <body>
        <h1>Welkom <%= request.getUserPrincipal().getName() %></h1>
        <h2>U ben hier beland als gevolg van een error</h2>
        <p>U bent: 
    </body>
</html>
