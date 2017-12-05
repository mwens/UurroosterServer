<%-- 
    Document   : student
    Created on : Nov 24, 2017, 6:52:46 PM
    Author     : Matthias Wens
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css">
        <title>Student</title>
    </head>
    <body>
        <div class="header">
            <h1>Studentenportaal</h1>
        </div>
        <div class="toevoegdiv">
        <form>
            <h2>Wel mee samen:</h2>
            <input list="Studenten" name="SelectedStudent" id="geselecteerdeStudent">
            <datalist id="Studenten">
                <c:forEach var="i" items="${sessionScope['studenten']}">
                    <option value="<c:out value='${i.getNaam()}'/>">
                </c:forEach>
            </datalist>
            <input type="hidden" name="stage" value="voegtoe">
            <button type="submit" class="toevoegen">V</button>
        </form><br/>
        <table id="toevoegtable">
            <tr>
                <th>Student</th>
            </tr>     
            <c:forEach var="j" items="${sessionScope['studentenRelatiesWel']}">
                <tr>
                    <td><c:out value='${j}'/></td>
                </tr> 
            </c:forEach>
        </table>
        </div>
        <div class="verwijderdiv">
        <form>
            <h2>Niet mee samen:</h2>
            <input list="Studenten" name="SelectedStudent" id="geselecteerdeStudent">
            <datalist id="Studenten">
                <c:forEach var="i" items="${sessionScope['studenten']}">
                    <option value="<c:out value='${i.getNaam()}'/>">
                </c:forEach>
            </datalist>
            <input type="hidden" name="stage" value="verwijder">
            <button type="submit" class="verwijderen">X</button>
        </form>
            <br/>
        <table id="verwijdertable">
            <tr>
                <th>Student</th>
            </tr>
            <c:forEach var="j" items="${sessionScope['studentenRelatiesNiet']}">
                <tr>
                    <td><c:out value='${j}'/></td>
		    <td>
			<form method="post" action="<% out.println(response.encodeURL("")); %>">
			    <input type="hidden" name="stage" value="verwijdleren">
			    <input type="hidden" name="verwijderen" value="${j}"
			    <button class="verwijderen" type="submit">X</button>
			</form>
		    </td>
                </tr>
            </c:forEach>
        </table>
        </div>
        <form method="post" action="<% out.println(response.encodeURL("common/logout.jsp")); %>">
            <input type="hidden" name="stage" value="afmelden">
            <button type="submit">Afmelden</button>
        </form>
    </body>
</html>
