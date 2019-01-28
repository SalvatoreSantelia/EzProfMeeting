<%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 14/12/2018
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<link rel="stylesheet" type="text/css" href="../CSS/myForm.css">
<div id = "addRicevimento">

        <h1> Nuovo Ricevimento </h1>

        <form id="insertForm" action = "ReceivementServlet" method="post">

            <h4 id="dio"> Inizio </h4>
            <input  type="text" id="startHour" name="startHour" value="inizio" disabled> <br> <br>

            <h4> Fine </h4>
            <input  type="text" id="endHour" name="endHour" value="" disabled> <br> <br>


            <h4> Luogo </h4>
            <textarea  id="place" name="place" rows="4" cols="50" style="resize: none;">Ufficio:  </textarea> <br> <br>

            <h4> Posti disponibili per blocco orario </h4>
            <input id="group" name="group" type="number" min="1" max="5" value="1"> <br> <br>


            <a id="undoButton"  data-dismiss="modal"> Annulla </a>
            &nbsp;
            <a id="insertButton" data-dismiss="modal" > Conferma </a>



        </form>

</div>