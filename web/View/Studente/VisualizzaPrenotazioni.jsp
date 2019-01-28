<%@ page import="myJava.model.beans.Prenotazione" %>
<%@ page import="myJava.model.beans.Ricevimento" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Time" %>
<%@ page import="javafx.scene.control.Alert" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="../JS/StudenteJS.js"></script>
    <link rel="stylesheet" type="text/css" href="../CSS/HomeStudente.css">
    <!------ Include the above in your HEAD tag ---------->
</head>

<%
    Studente studente = (Studente) session.getAttribute("user");
    DataManager dataManager = new DataManager();

    List<Prenotazione> prenotazioni =  dataManager.visualizzaPrenotazioni(studente.getIdStudente());
    int i=1;
    DateFormat df = new SimpleDateFormat();

/*
    String x = request.getParameter("eliminare");

    if(x!=null){
        int idPrenotazione = Integer.parseInt(request.getParameter("ricevimento"));
        Prenotazione a = dataManager.getPranotazioneById(idPrenotazione);
        if(dataManager.eliminaPrenotazione(a)){

        }
    }*/
%>
<body>


<table class="table table-dark">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Professore</th>
        <th scope="col">Data</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>

    <%
        for(Prenotazione a : prenotazioni){
           int idRicevimento =  a.getIdRicevimento();
           Ricevimento ric = dataManager.getRicevimentoById(idRicevimento);
           int idProf = ric.getIdProfessore();
           Professore prof = dataManager.getProfById(idProf);
    %>

    <tr>
        <th scope="row"><%=i%></th>
        <td><%=prof.getNomeProfessore()%> <%=prof.getCognomeProfessore()%></td>
        <td><%=ric.getData()%> <%=ric.getOrarioInizio()%> - <%=ric.getOrarioFine()%></td>
        <td ><Button data-dismiss="modal" class="tdRimuovi" id="<%=i%>">
            <input type="hidden" id="idPrenotazione<%=i%>" value="<%=a.getIdPrenotazione()%>">
            <input type="hidden" id="idStudente<%=i%>" value="<%=studente.getIdStudente()%>">
            Rimuovi
        </Button></td>
    </tr>


    <%
        i++;}
    %>
    </tbody>
</table>

</body>
</html>
