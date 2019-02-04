<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="myJava.model.beans.Professore" %>
<%@ page import="myJava.model.general.DataManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="myJava.model.beans.Ricevimento" %>
<%@ page import="myJava.model.beans.Prenotazione" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 19/12/2018
  Time: 09:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" type="text/css" href="../CSS/CalendarGraphic.css">
<link rel="stylesheet" type="text/css" href="../CSS/mySimpleModal.css">

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
      integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
      integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
        integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
        integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
        crossorigin="anonymous"></script>

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

<script src="../JS/InserisciPrenotazione.js"></script>

<div>
    <h1 id="settimana"> Settimana:  <%= startWeek%> - <%= endWeek%>
    </h1>

    <table id="calendar">

        <%
            String mail = request.getParameter("email");
            DataManager dm = new DataManager();
            Professore prof = dm.getProfessoreByEmail(mail);
            ArrayList<Ricevimento> listaRicevimenti = dm.getRicevimentiByProf(prof);
            Studente user = (Studente) session.getAttribute("user");
            List<Prenotazione> listaPrenotazioni = dm.visualizzaPrenotazioni(user.getIdStudente());
            /*
            for (Ricevimento r : listaRicevimenti) {
                for (int i = 0; i < listaPrenotazioni.size(); i++) {
                    Prenotazione p = listaPrenotazioni.get(i);
                    if (p.getIdRicevimento() == r.getIdRicevimento()) {
                      listaPrenotazioni.remove(i);
                      i--;
                    }
                }
            }*/

            if(listaPrenotazioni==null) listaPrenotazioni= new ArrayList<>();
            if(listaRicevimenti==null) listaRicevimenti = new ArrayList<>();
            System.out.println(listaRicevimenti);
            sdf = new SimpleDateFormat("YYYY-MM-dd");
            String[] settimana = new String[5];


            for (int i = 0; i < 5; i++) {
                cal.set(Calendar.DAY_OF_WEEK, (i + 2));
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
            int ora;
            String minuti = "00";
            for (int i = 0; i < ORE * PARTIZIONI; i++) {

        %>

        <tr>

            <td class="ore">
                <%

                    ora = (i / PARTIZIONI) + ORE;
                    switch (i % PARTIZIONI) {
                        case 0:
                            minuti = "00";
                            break;
                        case 1:
                            minuti = "30";
                            break;
                    }
                %>

                <%=ora + ":" + minuti %>


            </td>


            <%
                for (int j = 0; j < 5; j++) {

                    boolean find = false;
                    for (Ricevimento r : listaRicevimenti) {
                        if ((r.getData() + " " + r.getOrarioInizio()).equals(settimana[j] + " " + ora + ":" + minuti) ||
                                (r.getData() + " " + r.getOrarioInizio()).equals(settimana[j] + " 0" + ora + ":" + minuti)) {
                            find = true;
                            boolean prenotated = false;
                            for (Prenotazione p : listaPrenotazioni) {
                                if (p.getIdRicevimento() == r.getIdRicevimento()) {
                                    prenotated = true;
                                    //button elimina prenotazione
            %>

            <td>
                <button data-prenotazione="<%=p.getIdPrenotazione()%>" class="prenotato" title="Elimina Prenotazione"
                        data-toggle="modal" data-target="#eliminaPrenotazione"
                        data-data="<%=r.getData()%>" data-inizio="<%=r.getOrarioInizio()%>"
                        data-fine="<%=r.getOrarioFine()%>">
                    <i class="fas fa-trash-alt"></i>
                </button>

            </td>
            <%

                    }
                }
                if (!prenotated) {


                    if (r.getPostiDisponibili() == 0) {

            %>
            <td class="full" title="Non ci sono posti per questo Ricevimento">
                FULL
            </td>
            <%
            } else {
            %>
            <td>
                <button data-ricevimento="<%=r.getIdRicevimento()%> " class="ricevimento"
                        title="Effettua una prenotazione" data-toggle="modal" data-target="#addPrenotazione"
                        data-data="<%=r.getData()%>" data-inizio="<%=r.getOrarioInizio()%>"
                        data-fine="<%=r.getOrarioFine()%>">
                    <i class="fas fa-sign-in-alt"></i>
                </button>
            </td>
            <%
                            }
                        }


                    }

                }
                if (!find) {
            %>
            <td></td>
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


<div class="modal fade" id="eliminaPrenotazione" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content mymodal">

            <script src="../JS/EliminaPrenotazione.js" type="text/javascript"></script>
            <div class="modal-header">
                <h2> Elimina Prenotazione </h2>
                <h5><%=professore.getCognomeProfessore() + " " + professore.getNomeProfessore()%>
                </h5>
                <h5 id="dataScelta2">Data</h5>
                <h5 id="orarioScelto2">Ora</h5>
            </div>
            <div class="modal-body">
                <br>
                <h5>
                Sei sicuro di eliminare la prenotazione?
                </h5>
                <br>
            </div>
            <div class="modal-footer">

                <br>
                <a id="undoButton" data-dismiss="modal"> Annulla </a>
                &nbsp;
                <a id="deleteButton" data-dismiss="modal"> Conferma </a>


            </div>

        </div>
    </div>
</div>

<div class="modal fade" id="addPrenotazione" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content mymodal">
            <%@include file="InserisciPrenotazione.jsp" %>
        </div>
    </div>
</div>

