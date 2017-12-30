<%-- 
    Document   : klas
    Created on : 29-dec-2017, 19:17:27
    Author     : Matthias Wens
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <title><c:out value="${sessionScope['klasnaam']}" /></title>
    </head>
    <body>
        <div class="header">
            <h1>Studentenportaal <c:out value="${sessionScope['studentnaam']}" /></h1>
        </div>
        <div id="klasgroep">
        <h2 class="w3-blue">Studenten in <c:out value="${sessionScope['klasnaam']}" /></h2>
        <table>
            <c:forEach var="i" items="${sessionScope['klas']}">
                <tr>
                    <td><c:out value='${i}'/></td>
                </tr> 
            </c:forEach>
        </table>
        </div>
        <div class="knopjes">
            <form method="post" action="<% out.println(response.encodeURL("common/logout.jsp")); %>">
                <input type="hidden" name="stage" value="afmelden">
                <button type="submit" class="w3-button w3-large w3-blue w3-card-4">Afmelden</button>
            </form>
        </div>
    </body>
</html>
