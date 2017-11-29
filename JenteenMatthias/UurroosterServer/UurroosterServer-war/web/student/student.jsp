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
        <title>Student</title>
        <script>
        function myFunction() {
            // Declare variables
            var input, filter, ul, li, a, i;
            input = document.getElementById('myInput');
            filter = input.value.toUpperCase();
            ul = document.getElementById("myUL");
            li = ul.getElementsByTagName('li');

            // Loop through all list items, and hide those who don't match the search query
            for (i = 0; i < li.length; i++) {
                a = li[i].getElementsByTagName("a")[0];
                if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
                    li[i].style.display = "";
                } else {
                    li[i].style.display = "none";
                }
            }
        }
        </script>
    </head>
    <body>
        <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names..">
	<!--
        <ul id="myUL">
            <c:forEach var="student" items="${studentLijst}">
                <!-- verwijder en voegtoe functie moeten hier nog geïmplementeerd worden
                <li>${student}<button type="button" class="toevoegen" onclick="voegtoe( ${student} )">V</button><button type="button" class="verwijderen" onclick="verwijder( ${student} )">X</button></li>
            </c:forEach>
        </ul>
	-->

	<ul id="myUL">
            <c:forEach var="student" items="${studentLijst}">
                <!-- verwijder en voegtoe functie moeten hier nog geïmplementeerd worden -->
                <li><a href="">Matthias Wens</a><button type="button" class="toevoegen" onclick="voegtoe()">V</button><button type="button" class="verwijderen" onclick="verwijder()">X</button></li>
                <li><a href="">Jente Heremans</a><button type="button" class="toevoegen" onclick="voegtoe()">V</button><button type="button" class="verwijderen" onclick="verwijder()">X</button></li>
                <li><a href="">Jeroen Streulens</a><button type="button" class="toevoegen" onclick="voegtoe()">V</button><button type="button" class="verwijderen" onclick="verwijder()">X</button></li>
                <li><a href="">Wouter Mauriën</a><button type="button" class="toevoegen" onclick="voegtoe()">V</button><button type="button" class="verwijderen" onclick="verwijder()">X</button></li>
                <li><a href="">Toon Blommaerts</a><button type="button" class="toevoegen" onclick="voegtoe()">V</button><button type="button" class="verwijderen" onclick="verwijder()">X</button></li>
                <li><a href="">Kristof De Ridder</a><button type="button" class="toevoegen" onclick="voegtoe()">V</button><button type="button" class="verwijderen" onclick="verwijder()">X</button></li>
            </c:forEach>
        </ul>
    </body>
</html>
