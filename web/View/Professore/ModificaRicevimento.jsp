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

<link rel="stylesheet" type="text/css" href="../CSS/myForm.css">
<div id = "modificaRicevimento">

    <h1> Modifica Ricevimento </h1>

    <form id="modificaForm" action = "ReceivementServlet" method="post">

        <h4 id="dio"> Inizio </h4>
        <input type="hidden" id="idEdit" name="idEdit">
        <input  type="text" id="startHourEdit" name="startHourEdit" value="inizio"> <br> <br>

        <h4> Fine </h4>
        <input  type="text" id="endHourEdit" name="endHourEdit" value=""> <br> <br>


        <h4> Luogo </h4>
        <textarea  id="placeEdit" name="placeEdit" rows="4" cols="50" style="resize: none;">Ufficio:  </textarea> <br> <br>

        <h4> Posti disponibili per blocco orario </h4>
        <input id="groupEdit" name="groupEdit" type="number" min="1" max="5"> <br> <br>


        <a id="undoButton"  data-dismiss="modal"> Annulla </a>
        &nbsp;
        <a id="editButton" data-dismiss="modal" > Conferma </a>



    </form>

</div>
</body>
</html>
