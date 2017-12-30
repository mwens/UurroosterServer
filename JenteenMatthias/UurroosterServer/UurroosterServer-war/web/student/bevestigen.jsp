<%-- 
    Document   : bevestigen
    Created on : 5-dec-2017, 10:21:27
    Author     : student
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <title>Student</title>
    </head>
    <body>
        <div class="header">
            <h1>Studentenportaal <c:out value="${sessionScope['studentnaam']}" /></h1>
        </div>
        <div class="toevoegdiv">
        <h2 class="w3-blue">Wel mee samen:</h2>
        <table style="background-color: green">
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
        <h2 class="w3-blue">Niet mee samen:</h2>
        <br/>
        <table style="background-color: red">
            <tr>
                <th>Student</th>
            </tr>
            <c:forEach var="j" items="${sessionScope['studentenRelatiesNiet']}">
                <tr>
                    <td><c:out value='${j}'/></td>
                </tr>
            </c:forEach>
        </table>
        </div>
        <div class="knopjes">
            <table>
                <tr>
                    <td>
                        <form method="post" action="<% out.println(response.encodeURL("/UurroosterServer-war/student.do")); %>">
                            <input type="hidden" name="stage" value="bevestigen">
                            <button type="submit" class="w3-button w3-large w3-blue w3-card-4" ${sessionScope["bevestigknop"]}>Bevestigen</button>
                        </form>       
                    </td>
                    <c:if test='${empty sessionScope["bevestigknop"]}'>
                        <td>
                            <form method="post" action="<% out.println(response.encodeURL("/UurroosterServer-war/student.do")); %>">
                                <input type="hidden" name="stage" value="annuleren">
                                <button type="submit" class="w3-button w3-large w3-blue w3-card-4">Annuleren</button>
                            </form> 
                        </td>
                    </c:if>
                </tr>
            </table>
            <form method="post" action="<% out.println(response.encodeURL("/UurroosterServer-war/")); %>">
                <input type="hidden" name="stage" value="afmelden">
                <button type="submit" class="w3-button w3-large w3-blue w3-card-4">Afmelden</button>
            </form>
        </div>
    </body>
</html>
