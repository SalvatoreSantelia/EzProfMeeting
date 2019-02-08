<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="myJava.model.beans.Professore" %>
<%@ page import="myJava.model.general.DataManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="myJava.model.beans.Ricevimento" %><%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 19/12/2018
  Time: 09:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" type="text/css" href="../CSS/CalendarGraphic.css">
<link rel="stylesheet" type="text/css" href="../CSS/mySimpleModal.css">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
      integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">


<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
        integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
        crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
      integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
      integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

<script src="../JS/CalendarioProfessore.js" type="text/javascript"></script>
<script src="../JS/AggiungiRicevimento.js" type="text/javascript"></script>
<script src="../JS/ModificaRicevimento.js" type="text/javascript"></script>

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
    <h1 id="settimana"> Settimana:  <%= startWeek%> - <%= endWeek%>
    </h1>


    <table id="calendar">

        <%

            Professore prof = (Professore) session.getAttribute("user");
            DataManager dm = new DataManager();
            ArrayList<Ricevimento> lista = dm.getRicevimentiByProf(prof);
            if(lista==null) lista = new ArrayList<>();
            System.out.println(lista);
            sdf = new SimpleDateFormat("YYYY-MM-dd");
            String[] settimana = new String[5];
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            if(lista!=null)
            {
              startWeek = sdf.format(cal.getTime());
              for(Ricevimento r: lista)
              {
                if(startWeek.compareTo(r.getData())>0)
                {
                  cal.setTime(sdf.parse(r.getData()));
                  int day = cal.get(Calendar.DAY_OF_WEEK);
                  cal = Calendar.getInstance();
                  cal.set(Calendar.DAY_OF_WEEK, day);
                  r.setData(sdf.format(cal.getTime()));
                  dm.modificaRicevimento(r);
                }
              }
            }


            for (int i = 0; i < 5; i++) {
                cal.set(Calendar.DAY_OF_WEEK, (i+2));
                settimana[i] = sdf.format(cal.getTime());
            }


        %>

        <caption id="prova">MyCalendar</caption>


        <tr class="weekdays">
            <th scope="col" id="ore">Ore</th>
            <th scope="col">Lunedì</th>
            <th scope="col">Martedì</th>
            <th scope="col">Mercoledì</th>
            <th scope="col">Giovedì</th>
            <th scope="col">Venerdì</th>

        </tr>


        <% //creazione righe

            final int ORE = 9, PARTIZIONI = 2;
            int ora, oraFine;
            String minuti = "00";
            String minutiFine = "30";

            for (int i = 0; i < ORE * PARTIZIONI; i++) {


        %>

        <tr>

            <td class="ore">
                <%

                    ora = oraFine = (i / PARTIZIONI) + ORE;

                    switch (i % PARTIZIONI) {
                        case 0:
                            minuti = "00";
                            minutiFine = "30";
                            oraFine = ora;
                            break;
                        case 1:
                            minuti = "30";
                            minutiFine = "00";
                            oraFine = ora + 1;
                            break;
                    }
                %>

                <%=ora + ":" + minuti %>


            </td>

            <% if (ora != 9) {
                for (int j = 0; j < 5; j++) {

                    boolean find = false;
                    for (Ricevimento r : lista) {


                        if ((r.getData() + " " + r.getOrarioInizio()).equals(settimana[j] + " " + ora + ":" + minuti) ||
                                (r.getData() + " " + r.getOrarioInizio()).equals(settimana[j] + " 0" + ora + ":" + minuti) ) {


            %>
            <td id=<%=settimana[j] + " " + ora + ":" + minuti%> data-exist="true">
                <button class="editReceivement" id="<%=r.getIdRicevimento()%>" data-inizio="<%=r.getData() + " "+ r.getOrarioInizio()%>" data-fine="<%=r.getData() + " " + r.getOrarioFine()%>"
                        data-luogo="<%=r.getLuogo()%>"  data-posti="<%=r.getPostiTotali()%>" data-toggle="modal" data-target="#edit" ><i class="far fa-edit"></i></button>            </td>
            <%
                        find = true;
                        break;
                    }


                }

                if (!find) { %>
            <td id="<%=settimana[j] + " " +ora+":"+minuti%>" data-end="<%=settimana[j] + " " +oraFine+":"+minutiFine%>"
             data-exist="false"   style="background-color:rgb(255, 255, 255);"></td>

            <%
                    }
                }
            } else {
                for (int j = 0; j < 5; j++) {

                    boolean find = false;
                    for (Ricevimento r : lista) {

                        if ((r.getData() + " " + r.getOrarioInizio()).equals(settimana[j] + " " + ora + ":" + minuti)||
                                (r.getData() + " " + r.getOrarioInizio()).equals(settimana[j] + " 0" + ora + ":" + minuti)) {


            %>
            <td data-exist="true" id=<%=settimana[j] + " 0" + ora + ":" + minuti%>>
                <button class="editReceivement" id="<%=r.getIdRicevimento()%>" data-inizio="<%=r.getData() + " " + r.getOrarioInizio()%>" data-fine="<%=r.getData() + " " +r.getOrarioFine()%>"
                        data-luogo="<%=r.getLuogo()%>"  data-posti="<%=r.getPostiTotali()%>" data-toggle="modal" data-target="#edit" ><i class="far fa-edit"></i></button></td>
            <%
                        find = true;
                        break;
                    }
                }

                if (!find) {
                  String zero =" ";
                  if(!minuti.equals("30"))
                    zero = " 0";

            %>
            <td id="<%=settimana[j] + " 0" +ora+":"+minuti%>" data-end="<%=settimana[j] + zero +oraFine+":"+minutiFine%>"
                data-exist="false" style="background-color:rgb(255, 255, 255);"></td>

            <%
                }

            %>

            <%
                    }
                }
            %>
        </tr>
        <%
            }
        %>



    </table>





</div>

<!-- Modal New -->
<div class="modal fade" id="new" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content mymodal">
            <%@include file="../Professore/InserisciRicevimento.jsp" %>
        </div>
    </div>
</div>


<!-- Modal Edit -->

<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content mymodal">
            <%@include file="ModificaRicevimento.jsp" %>
        </div>
    </div>
</div>