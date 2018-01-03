<%-- 
    Document   : logout
    Created on : Dec 2, 2017, 9:17:47 AM
    Author     : Matthias Wens
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css">
        <title>Log Out</title>
        <style>
            #center {
                border: 3px solid #f1f1f1;
                width: 20%;
                position: absolute;
                margin-left: 40%;
                margin-top: 15%;
            }
        </style>
    </head>
    <body style="background-color: #DDE7F0;">
        <div id="center">
            <div class="header">
                <h1>Afmelden geslaagd!</h1>
            </div>
            <form>
                <input type="hidden" name="stage" value="geslaagd">
            </form>
            <p>Afmelden geslaagd! U wordt automatisch doorverwezen!</p>
            <p>Indien dit niet werkt, klik <a href='<% out.println(response.encodeURL("/UurroosterServer-war/")); %>'>HIER</a></p>
            <meta http-equiv="refresh" content="2;url=<% out.println(response.encodeURL("/UurroosterServer-war/")); %>" />
        </div>
    </body>
</html>
