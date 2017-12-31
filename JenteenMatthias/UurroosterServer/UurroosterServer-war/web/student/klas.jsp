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
        <title><c:out value="${sessionScope['studentnaam']}" /></title>
    </head>
    <body>
        <div class="header">
            <h1>Studentenportaal <c:out value="${sessionScope['studentnaam']}" /></h1>
        </div>
        <div id="klasgroep">
        <h2 class="w3-blue">Studenten in <c:out value="${sessionScope['klasnaam']}" /></h2>
        <table>
            <c:forEach var="i" items="${sessionScope['klas']}">
                <c:choose>
                    <c:when test="${i == sessionScope['studentnaam']}">
                        <tr style="font-weight: bolder">
                            <td><c:out value='${i}'/></td>
                        </tr> 
                    </c:when>
                    <c:when test="${i != sessionScope['studentnaam']}">
                        <% int ok=0; %>
                        <c:forEach var="j" items="${sessionScope['studentenRelatiesWel']}">
                            <c:if test="${j == i}">
                                <% ok=1; %>
                                <tr>
                                    <td style="background-color: green"><c:out value='${i}'/></td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        <c:forEach var="j" items="${sessionScope['studentenRelatiesNiet']}">
                            <c:if test="${j == i}">
                                <% ok=1; %>
                                <tr>
                                    <td style="background-color: red"><c:out value='${i}'/></td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        <% if(ok == 0){%> 
                            <tr>
                                <td><c:out value='${i}'/></td>
                            </tr>    
                        <%}%>
                    </c:when>
                </c:choose>
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
