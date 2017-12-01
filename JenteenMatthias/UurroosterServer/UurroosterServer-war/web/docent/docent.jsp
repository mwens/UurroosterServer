<%-- 
    Document   : docent
    Created on : Nov 29, 2017, 4:01:56 PM
    Author     : Matthias Wens
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Docent</title>
    </head>
    <body>
        <h1>Docentenportaal</h1>
        <form method="post" action="<% out.println(response.encodeURL("common/index.jsp")); %>">
            <input type="hidden" name="stage" value="afmelden">
            <button type="submit">Afmelden</button>
        </form>
    </body>
</html>
