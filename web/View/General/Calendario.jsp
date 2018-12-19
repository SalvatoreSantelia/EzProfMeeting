<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 19/12/2018
  Time: 09:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyCalendar</title>
    <link rel="stylesheet" type="text/css" href="../CSS/CalendarGraphic.css">
</head>
<body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="../JS/Calendario.js" type="text/javascript"></script>

<%
    String startWeek, endWeek;
    DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    startWeek = sdf.format(cal.getTime());
    cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
    endWeek = sdf.format(cal.getTime());
%>

<h1 id="settimana"> Settimana:  <%= startWeek%> - <%= endWeek%> </h1>



<table id="calendar">
    <caption id="prova">MyCalendar</caption>


    <tr class="weekdays">
        <th scope="col" id="ore" >Ore</th>
        <th scope="col">Lunedì</th>
        <th scope="col">Martedì</th>
        <th scope="col">Mercoledì</th>
        <th scope="col">Giovedì</th>
        <th scope="col">Venerdì</th>

    </tr>


    <% //creazione righe

        final int ORE=9, PARTIZIONI=3;
        int ora;
        String minuti="00";

        for(int i=0; i<ORE*PARTIZIONI; i++)
        {
    %>

    <tr>
        <td class="ore">
        <%

            ora = (i/3) +9;
            switch (i%3)
            {
                case 0: minuti = "00"; break;
                case 1: minuti = "20"; break;
                case 2: minuti = "40"; break;
            }

        %>

            <%=ora + ":" + minuti %>

        </td>
        <td> </td>
        <td> </td>
        <td> </td>
        <td> </td>
        <td> </td>

    </tr>
    <%
        }
    %>
    </>

</table>


</body>
</html>