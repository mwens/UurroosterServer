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
        <!-- <script>
            function voegtoe(){                
                var table = document.getElementById("targettable");

                var tr = document.createElement("tr");
                var td = document.createElement("td");            
                var student = document.getElementById("geselecteerdeStudent").value;
                var txt = document.createTextNode(student);
                var td2 = document.createElement("td");            
                var txt2 = document.createTextNode("V");

                td.appendChild(txt);
                tr.appendChild(td);
                td2.appendChild(txt2);
                tr.appendChild(td2);
                table.appendChild(tr);
            }
            
            function verwijder(){
                var table = document.getElementById("targettable");

                var tr = document.createElement("tr");
                var td = document.createElement("td");            
                var student = document.getElementById("geselecteerdeStudent").value;
                var txt = document.createTextNode(student);
                var td2 = document.createElement("td");            
                var txt2 = document.createTextNode("X");

                td.appendChild(txt);
                tr.appendChild(td);
                td2.appendChild(txt2);
                tr.appendChild(td2);
                table.appendChild(tr);
            }
        </script> -->
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
        </form>
        <table id="toevoegtable">
            <tr>
                <th>Student</th>
            </tr>     
            <c:forEach var="i" items="${sessionScope['studentenRelaties']}">
                <c:if test="${i.getRelatie()} = '1'">
                    <tr>
                        <td><c:out value='${i.getNaam()}'/></td>
                    </tr>
                </c:if>
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
        <table id="verwijdertable">
            <tr>
                <th>Student</th>
            </tr>
            <c:forEach var="i" items="${sessionScope['studentenRelaties']}">
                <c:if test="${i.getRelatie()} = '2'">
                    <tr>
                        <td><c:out value='${i.getNaam()}'/></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
        </div>
        <form method="post" action="<% out.println(response.encodeURL("common/logout.jsp")); %>">
            <input type="hidden" name="stage" value="afmelden">
            <button type="submit">Afmelden</button>
        </form>
    </body>
</html>
