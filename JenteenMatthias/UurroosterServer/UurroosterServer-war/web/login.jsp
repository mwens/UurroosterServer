<%-- 
    Document   : login
    Created on : 21-nov-2017, 10:01:27
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <title>Login</title>
    </head>
    <body style="background-color: #8cb8ff;">
        <h1 style="background-color: #0061ff;">Welkom</h1>
        <p> Log je hier in:
        <form method=post action="j_security_check">
            <table>
                <tr> <td> Naam: </td> <td> <input required type="text" name="j_username" /></td></tr>
                <tr> <td> Wachtwoord:</td><td><input required type="password" name="j_password" /></td></tr>
            </table>
            <button type="submit" class="w3-button w3-large w3-blue w3-card-4">Log in</button>
        </form>
    </body>
</html>