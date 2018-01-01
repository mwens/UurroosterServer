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
        <title>Log Out</title>
    </head>
    <body style="background-color: #DDE7F0;">
	<h1 style="background-color: #0061ff;">Afmelden geslaagd!</h1>
        <form>
            <input type="hidden" name="stage" value="geslaagd">
        </form>
        <p>Afmelden geslaagd! U wordt automatisch doorverwezen!</p>
        <p>Indien dit niet werkt, klik <a href='<% out.println(response.encodeURL("/UurroosterServer-war/")); %>'>HIER</a></p>
        <meta http-equiv="refresh" content="2;url=<% out.println(response.encodeURL("/UurroosterServer-war/")); %>" />
    </body>
</html>
