<%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 14/12/2018
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<link rel="stylesheet" type="text/css" href="../CSS/myForm.css">
<div id="addRicevimento">


    <h1> Nuovo Ricevimento </h1>

    <form id="insertForm" action="ReceivementServlet" method="post">

        <h4 id="dio"> Inizio </h4>
        <input type="text" id="startHour" name="startHour" value="inizio" disabled/> <br> <br>

        <h4> Fine </h4>
        <input type="text" id="endHour" name="endHour" value="" disabled> <br> <br>

        <input type="text" id="prova"><br>
        <h4> Luogo </h4>
        <textarea id="placeRecivement" name="place" rows="4" cols="50"
                  style="resize: none;"> <%=((Professore) session.getAttribute("user")).getUfficioProfessore()%>  </textarea>
        <br> <br>

        <h4> Posti disponibili per blocco orario </h4>
        <select id="group">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select> <br> <br>


        <a id="undoButton" data-dismiss="modal"> Annulla </a>
        &nbsp;
        <a id="insertButton" data-dismiss="modal"> Conferma </a>


    </form>

</div>