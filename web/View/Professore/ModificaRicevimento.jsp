<%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 14/12/2018
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    DataManager dataManager = new DataManager();
%>
<link rel="stylesheet" type="text/css" href="../CSS/myForm.css">

<div class="modal fade" id="visualizzaPrenotazioni" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content mymodal">

            <div class="modal-header">
            <h4>Lista Studente</h4>
            </div>
            <div class="modal-body modalbody">

            </div>
            <div class="modal-footer">

            </div>

        </div>
    </div>
</div>
<div id = "modificaRicevimento" style="position: relative">

    <h1> Modifica Ricevimento </h1>

    <form id="modificaForm" method="post">

        <h4 id="dio"> Inizio </h4>
        <input type="hidden" id="idEdit" name="idEdit">
        <input  type="text" id="startHourEdit" name="startHourEdit" value="inizio" disabled="disabled"> <br> <br>

        <h4> Fine </h4>
        <input  type="text" id="endHourEdit" name="endHourEdit" value="" disabled="disabled"> <br> <br>


        <h4> Luogo </h4>
        <textarea  id="placeEdit" name="placeEdit" rows="4" cols="50" style="resize: none; width: 100%;">Ufficio:  </textarea> <br> <br>


        <a id="undoButton"  data-dismiss="modal"> Annulla </a>
        &nbsp;
        <a id="editButton" data-dismiss="modal" > Conferma </a>
        &nbsp;
        <a id="prenotazioniButton"   data-toggle="modal" data-target="#visualizzaPrenotazioni"> Visualizza Prenotazioni </a>
        &nbsp;
        &nbsp;
        &nbsp;
        &nbsp;
        <a id="deleteButton" data-dismiss="modal" style=""><i class="fas fa-trash-alt"></i></a>


    </form>

</div>
</body>
</html>
