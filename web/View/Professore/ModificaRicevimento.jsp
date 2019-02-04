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

        <a id="deleteButton" data-dismiss="modal" style="margin-left: 50%;"><i class="fas fa-trash-alt"></i></a>


    </form>

</div>
</body>
</html>
