<%-- 
    Document   : changeww
    Created on : Dec 31, 2017, 3:27:29 PM
    Author     : Matthias Wens
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css">
        <title>Login</title>
    </head>
    <body>
        <c:if test="${sessionScope['alert_ww'] == -1}">
            <div class="alert">
                <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
                <strong>Opgelet!</strong> Wachtwoorden zijn niet gelijk!
            </div>
        </c:if>
        <c:if test="${sessionScope['alert_ww'] == 0}">
            <div class="alert">
                <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
                <strong>Fout!</strong> Oud wachtwoord niet correct!
            </div>
        </c:if>
        <div class="header">
            <h1>Wijzig Wachtwoord</h1>
        </div>
        <form method=post action="<% out.println(response.encodeURL("/UurroosterServer-war/")); %>">
            <table>
                <tr> <td> Wachtwoord:</td><td><input required type="password" name="password_old" /></td></tr>
                <tr> <td> Nieuw Wachtwoord:</td><td><input required type="password" name="password_new" /></td></tr>
                <tr> <td> Nieuw Wachtwoord:</td><td><input required type="password" name="password_new2" /></td></tr>
            </table>
            <input type="hidden" name="stage" value="ww">
            <button type="submit" class="w3-button w3-large w3-blue w3-card-4">Wijzigen</button>
        </form>
        <form method="post" action="<% out.println(response.encodeURL("/UurroosterServer-war/")); %>">
            <input type="hidden" name="stage" value="null">
            <button type="submit" class="w3-button w3-large w3-blue w3-card-4">Annuleren</button>
        </form>
    </body>
</html>