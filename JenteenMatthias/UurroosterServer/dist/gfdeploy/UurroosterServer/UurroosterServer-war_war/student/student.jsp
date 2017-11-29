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
        <script>
            function voegtoe(){
                var table = document.getElementById("targettable");

                var tr = document.createElement("tr");
                var td = document.createElement("td");            
                var student = document.getElementsByName("studenten");
                var txt = document.createTextNode(student);

                td.appendChild(txt);
                tr.appendChild(td);
                table.appendChild(tr);
            }
        </script>
    </head>
    <body>
        <form>
            <input list="Studenten" name="Studenten">
            <datalist id="Studenten">
                <option value="Matthias Wens">
                <option value="Jente Heremans">
                <option value="Jeroen Streulens">
                <option value="Jeroen Streulens">
                <option value="Wouter Mauriën">
                <option value="Toon Blommaerts">
                <option value="Kristof De Ridder">
            </datalist>
            <button type="button" class="toevoegen" onclick="voegtoe()">V</button>
            <button type="button" class="verwijderen" onclick="verwijder()">X</button>
        </form>
        <table id="targettable">
            <tr>
                <th>Student</th><th>Voorkeur</th>
            </tr>           
        </table>
    </body>
</html>
