<%-- 
    Document   : groepen
    Created on : 28-nov-2017, 11:17:27
    Author     : student
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css">
        <title><c:out value="${sessionScope['klasnaam']}" /></title>
    </head>
    <body>
        <h1><c:out value="${sessionScope['klasnaam']}" /></h1>
        <table id="klasgroep">
            <tr>
                <th>Studenten</th>
                <th></th>
            </tr>
            <c:forEach var="i" items="${sessionScope['klas']}">
                <tr>
                    <td><c:out value='${i.getNaam()}'/></td>
                    <td>
			<form method="post" action="<% out.println(response.encodeURL("docent.do")); %>">
			    <input type="hidden" name="stage" value="verwijderenStudent">
			    <input type="hidden" name="verwijderStudent" value="${i.getUserid()}">
			    <button class="verwijderen" type="submit">X</button>
			</form>
		    </td>
                </tr> 
            </c:forEach>
        </table>
        <table id="overigeStudenten">
            <tr>
                <th>Overige Studenten</th>
                <th></th>
            </tr>
            <c:forEach var="i" items="${sessionScope['overige']}">
                <tr>
                    <td><c:out value='${i.getNaam()}'/></td>
                    <td>
                        <form method="post" action="<% out.println(response.encodeURL("docent.do")); %>">
                            <input type="hidden" name="stage" value="voegtoeStudent">
                            <input type="hidden" name="voegtoeStudent" value="${i.getUserid()}">
                            <button class="toevoegen" type="submit">V</button>
                        </form>
                    </td>
                </tr> 
            </c:forEach>
        </table>
        <div class="knopjes">
            <form method="post" action="<% out.println(response.encodeURL("docent.do")); %>">
                <input type="hidden" name="stage" value="null">
                <button class="terug" type="submit">Terug</button>
            </form>
            <form method="post" action="<% out.println(response.encodeURL("common/logout.jsp")); %>">
                <input type="hidden" name="stage" value="afmelden">
                <button type="submit">Afmelden</button>
            </form>
        </div>
    </body>
</html>
