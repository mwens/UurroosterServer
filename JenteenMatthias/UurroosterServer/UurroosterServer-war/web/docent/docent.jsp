<%-- 
    Document   : docent
    Created on : Nov 29, 2017, 4:01:56 PM
    Author     : Matthias Wens
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css">
        <title>Docent</title>
    </head>
    <body>
        <h1>Docentenportaal</h1>
        
        <form method="post" action="<% out.println(response.encodeURL("")); %>">
            <input type="hidden" name="stage" value="voegGroepToe">
            <button type="submit">Groep toevoegen +</button>
        </form>
            
            
        <table>
            <tr>
                <th>Klassen</th>
            </tr>     
            <c:forEach var="i" items="${sessionScope['klassen']}">
                <tr>
                    <td><c:out value='${i.getNaam()}'/></td>
                    <td>
			<form method="post" action="<% out.println(response.encodeURL("docent.do")); %>">
			    <input type="hidden" name="stage" value="verwijderen">
			    <input type="hidden" name="verwijderKlas" value="${i.getKlasid()}">
			    <button class="verwijderen" type="submit">X</button>
			</form>
		    </td>
                    <td>
			<form method="post" action="<% out.println(response.encodeURL("docent.do")); %>">
			    <input type="hidden" name="stage" value="edit">
			    <input type="hidden" name="editKlas" value="${i.getKlasid()}">
			    <button class="edit" type="submit">Edit</button>
			</form>
		    </td>
                </tr> 
            </c:forEach>
        </table>
            
        <form method="post" action="<% out.println(response.encodeURL("")); %>">
            <input type="hidden" name="stage" value="bevestigen">
            <button type="submit">Bevestigen</button>
        </form>
        
        <form method="post" action="<% out.println(response.encodeURL("common/logout.jsp")); %>">
            <input type="hidden" name="stage" value="afmelden">
            <button type="submit">Afmelden</button>
        </form>
    </body>
</html>
