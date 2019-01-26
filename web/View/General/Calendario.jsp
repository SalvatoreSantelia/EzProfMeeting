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

<link rel="stylesheet" type="text/css" href="../CSS/CalendarGraphic.css">
<link rel="stylesheet" type="text/css" href="../CSS/mySimpleModal.css">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">




<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

<script src="../JS/CalendarioProfessore.js" type="text/javascript"></script>
<script src="../JS/AggiungiRicevimento.js" type="text/javascript"></script>

<link rel="stylesheet" href="../CSS/CalendarGraphic.css">

<%
    String startWeek, endWeek;
    DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    startWeek = sdf.format(cal.getTime());

    cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
    endWeek = sdf.format(cal.getTime());
%>

<div>
<h1 id="settimana"> Settimana:  <%= startWeek%> - <%= endWeek%> </h1>

<div id="popup" style="display: none">
    <%@include file="../Professore/InserisciRicevimento.jsp"%>
</div>

    <%
        sdf = new SimpleDateFormat("YYYY-MM-DD");
        String[] settimana = new String[5];
        for(int i=0; i<5; i++)
        {
          cal.set(Calendar.DAY_OF_WEEK, (i+2));
          settimana[i] = sdf.format(cal.getTime());
        }


    %>

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

        final int ORE=9, PARTIZIONI=2;
        int ora, oraFine;
        String minuti="00";
        String minutiFine="30";

        for(int i=0; i<ORE*PARTIZIONI; i++)
        {


    %>

    <tr>

        <td class="ore">
        <%

            ora= oraFine = (i/PARTIZIONI) +ORE;

            switch (i%PARTIZIONI) {
                case 0:
                    minuti = "00";
                    minutiFine = "30";
                    oraFine =ora;
                    break;
                case 1:
                    minuti = "30";
                    minutiFine = "00";
                    oraFine = ora+1;
                    break;
            }
        %>

            <%=ora + ":" + minuti %>

        </td>


        <td id="<%=settimana[0] + " " +ora+":"+minuti%>" data-end="<%=settimana[0] + " " +oraFine+":"+minutiFine%>" style="background-color:rgb(255, 255, 255);"> </td>
        <td id="<%=settimana[1] + " " +ora+":"+minuti%>" data-end="<%=settimana[1] + " " +oraFine+":"+minutiFine%>" style="background-color:rgb(255, 255, 255);"> </td>
        <td id="<%=settimana[2] + " " +ora+":"+minuti%>" data-end="<%=settimana[2] + " " +oraFine+":"+minutiFine%>" style="background-color:rgb(255, 255, 255);"> </td>
        <td id="<%=settimana[3] + " " +ora+":"+minuti%>" data-end="<%=settimana[3] + " " +oraFine+":"+minutiFine%>" style="background-color:rgb(255, 255, 255);"> </td>
        <td id="<%=settimana[4] + " " +ora+":"+minuti%>" data-end="<%=settimana[4] + " " +oraFine+":"+minutiFine%>" style="background-color:rgb(255, 255, 255);"> </td>

    </tr>
    <%
        }
    %>
    </>

</table>



</div>

<!-- Modal Game 1 -->
<div class="modal fade" id="new" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content mymodal">
            <%@include file="../Professore/InserisciRicevimento.jsp"%>
        </div>
    </div>
</div>