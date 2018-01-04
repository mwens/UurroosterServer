<%-- 
    Document   : docent
    Created on : Nov 29, 2017, 4:01:56 PM
    Author     : Matthias Wens
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <title><c:out value="${sessionScope['docentnaam']}" /></title>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
          google.charts.load('current', {'packages':['corechart']});
          google.charts.setOnLoadCallback(drawChart);

          function drawChart() {

            var data = google.visualization.arrayToDataTable([
              ['Status', 'Studenten'],
              ['Geen Keuze',${sessionScope['studenten_0']}],
              ['Onbevestigd',${sessionScope['studenten_1']}],
              ['Bevestigd',${sessionScope['studenten_2']}]
            ]);

            var options = {
                backgroundColor: 'transparent',
                title: 'Status Studenten'
            };

            var chart = new google.visualization.PieChart(document.getElementById('piechart'));

            chart.draw(data, options);
          }
        </script>
    </head>
    <body>
        <c:if test="${sessionScope['alert'] == 1}">
            <div class="alert">
                <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
                <strong>Opgelet!</strong> Bevestigen is nog niet mogelijk! Voeg iedereen aan een groep toe!
            </div>
        </c:if>
        <c:if test="${sessionScope['alert'] == 2}">
            <div class="success">
                <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
                <strong>Succes!</strong> Bevestigen geslaagd!
            </div>
        </c:if>
        <c:if test="${sessionScope['alert'] == 3}">
            <div class="warning">
                <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
                <strong>Opgelet!</strong> Bevestigen ongedaan gemaakt!
            </div>
        </c:if>
        <c:if test="${sessionScope['resetww'] != 0}">
            <div class="success">
                <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
                <strong>Succes!</strong> Wachtwoord van <c:out value="${sessionScope['resetww']}" /> werd gereset!
            </div>
        </c:if>
        <div class="header">
            <h1>Docentenportaal <c:out value="${sessionScope['docentnaam']}" /></h1>
        </div>
        <br/>
        <c:if test="${sessionScope['bevestigd'] != 0}">
            <form method="post" action="<% out.println(response.encodeURL("")); %>">
                <input type="text" name="nieuweGroepNaam">
                <input type="hidden" name="stage" value="voegGroepToe">
                <button type="submit" class="w3-button w3-large w3-green w3-card-4">+</button>
            </form>
        </c:if>
            
        <br/>
        <h2 class="w3-blue">Groepen</h2>   
        <table>
            <c:forEach var="i" items="${sessionScope['klassen']}">
                <tr>
                    <td><c:out value='${i.key.getNaam()}'/></td>
                    <c:if test="${sessionScope['bevestigd'] != 0}">
                        <td>
                            <form method="post" action="<% out.println(response.encodeURL("docent.do")); %>">
                                <input type="hidden" name="stage" value="verwijderen">
                                <input type="hidden" name="verwijderKlas" value="${i.key.getKlasid()}">
                                <button type="submit" class="w3-button w3-large w3-red w3-card-4">-</button>
                            </form>
                        </td>
                    </c:if>
                    <td>
			<form method="post" action="<% out.println(response.encodeURL("docent.do")); %>">
			    <input type="hidden" name="stage" value="edit">
			    <input type="hidden" name="editKlas" value="${i.key.getKlasid()}">
                            <c:if test="${sessionScope['bevestigd'] != 0}">
                                <button type="submit" class="w3-button w3-large w3-blue w3-card-4">&#9998;</button>
                            </c:if>
                            <c:if test="${sessionScope['bevestigd'] == 0}">
                                <button type="submit" class="w3-button w3-large w3-blue w3-card-4">View</button>
                            </c:if>
			</form>
		    </td>
                    <c:if test="${i.value != 0}">
                        <td>
                            <c:out value='${i.value}'/> errors &#9888;
                        </td>
                    </c:if>
                </tr> 
            </c:forEach>
        </table>
        <c:if test="${sessionScope['bevestigd'] != 0}">
            <div class="progress">
                <p style="font-weight: bold;">Toegewezen:</p>
                <div class="container">
                    <div style="width: <%=request.getAttribute("aantalToegewezenStudenten") %>%" class="afgewerkt"><%=request.getAttribute("aantalToegewezenStudenten") %>%</div>
                </div>    
            </div>
        </c:if>
        <c:if test="${sessionScope['bevestigd'] != 0 && sessionScope['periodeGestopt'] != 0}">
            <div id="piechart" style="width: 350px; height: 350px;position: absolute; top: 15%; right: 50%;"></div>
        </c:if>
        <c:if test="${sessionScope['docentnaam'] == 'admin'}">
            <form style="position: absolute; top: 15%; right: 20%;">
                <h2 class="w3-blue">Reset wachtwoord gebruiker:</h2>
                <input list="Users" name="SelectedUser" id="geselecteerdeUser" autocomplete="off">
                <datalist id="Users">
                    <c:forEach var="i" items="${sessionScope['users']}">
                        <option value="<c:out value='${i.getNaam()}'/>">
                    </c:forEach>
                </datalist>
                <input type="hidden" name="stage" value="reset">
                <button type="submit" class="w3-button w3-large w3-green w3-card-4">Reset</button>
            </form>
        </c:if>
        <div class="knopjes">
            <c:if test="${sessionScope['bevestigd'] != 0 && sessionScope['periodeGestopt'] != 0}">
                <form method="post" action="<% out.println(response.encodeURL("")); %>">
                    <input type="hidden" name="stage" value="eindeKeuzes">
                    <button type="submit" class="w3-button w3-large w3-red w3-card-4">Einde keuze</button>
                </form>
            </c:if>
            <c:if test="${sessionScope['bevestigd'] != 0 && sessionScope['periodeGestopt'] == 0}">
                <form method="post" action="<% out.println(response.encodeURL("")); %>">
                    <input type="hidden" name="stage" value="bevestigen">
                    <button type="submit" class="w3-button w3-large w3-green w3-card-4">Bevestigen</button>
                </form>
            </c:if>
            <c:if test="${sessionScope['bevestigd'] == 0}">
                <c:if test="${sessionScope['docentnaam'] == 'admin'}">
                    <form method="post" action="<% out.println(response.encodeURL("")); %>">
                        <input type="hidden" name="stage" value="bevestigenOngedaan">
                        <button type="submit" class="w3-button w3-large w3-blue w3-card-4">Revert</button>
                    </form>
                </c:if>
            </c:if>
            <%@ include file="/WEB-INF/jspf/footer.jspf" %>
        </div>
    </body>
</html>
