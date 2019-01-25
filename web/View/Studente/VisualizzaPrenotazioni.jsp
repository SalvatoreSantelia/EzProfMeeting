<%@ page import="myJava.model.beans.Prenotazione" %>
<%@ page import="myJava.model.beans.Ricevimento" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Time" %>
<%@ page import="javafx.scene.control.Alert" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<%
    Studente studente = (Studente) session.getAttribute("user");
    DataManager dataManager = new DataManager();

    List<Prenotazione> prenotazioni =  dataManager.visualizzaPrenotazioni(studente.getIdStudente());
    int i=1;
    DateFormat df = new SimpleDateFormat();


    String x = request.getParameter("eliminare");

    if(x!=null){
        int idPrenotazione = Integer.parseInt(request.getParameter("ricevimento"));
        Prenotazione a = dataManager.getPranotazioneById(idPrenotazione);
        if(dataManager.eliminaPrenotazione(a)){

        }
    }
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
           System.out.println(idRicevimento+""+idProf+" ciao");


    %>

    <form action="HomeStudente.jsp" id=<%=i%>>
    <tr>
        <th scope="row"><%=i%></th>
        <td><%=prof.getNomeProfessore()%> <%=prof.getCognomeProfessore()%></td>
        <td><%=ric.getData()%> <%=ric.getOrarioInizio()%> - <%=ric.getOrarioFine()%></td>
        <td ><Button class="tdRimuovi" name="idPrenotazione" value="<%=a.getIdPrenotazione()%>">Rimuovi</Button></td>
    </tr>
    </form>


    <%
        i++;}
    %>
    </tbody>
</table>

</body>
</html>
