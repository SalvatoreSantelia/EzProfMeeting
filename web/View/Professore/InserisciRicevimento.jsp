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

        <form action = "ReceivementServlet" method="post">

            <label> Giorno </label> <br>
            <input type="text" name="day" contenteditable="false"> <br> <br>

            <label> Ora di inizio </label> <br>
            <input type="text" name="startHour" contenteditable="false"> <br> <br>

            <label> Ora di fine </label> <br>
            <input type="text" name="endHour" contenteditable="false"> <br> <br>


            <label> Luogo </label> <br>
            <textarea  name="place" rows="4" cols="50" style="resize: none;"> Ufficio:  </textarea> <br> <br>

            <label> Posti disponibili per blocco orario </label> <br>
            <input type="number" min="1" value="1"> <br> <br>


            <a id="undoButton" class ="btn btn-outline-primary" > Annulla </a>

            <a id="insertButton" class ="btn btn-outline-primary" > Conferma </a>



        </form>

</div>