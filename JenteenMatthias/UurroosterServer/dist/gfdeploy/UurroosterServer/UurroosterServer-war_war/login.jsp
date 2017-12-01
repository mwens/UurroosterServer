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
        <title>Login</title>
    </head>
    <body>
        <h1>Welkom</h1>
        <p> Log je hier in:
        <form method=post action="j_security_check">
            <table>
                <tr> <td> Naam: </td> <td> <input type="text" name="j_username" /></td></tr>
                <tr> <td> Paswoord:</td><td><input type="password" name="j_password" /></td></tr>
            </table>
            <button type="submit">Log in</button>
        </form>
    </body>
</html>