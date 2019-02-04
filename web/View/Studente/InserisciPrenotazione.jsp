<%@ page import="myJava.model.beans.Professore" %><%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 14/12/2018
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="../CSS/myForm.css">


<div class="modal-header">
    <h2> Effettua Prenotazione </h2>
    <h5><%=professore.getCognomeProfessore() + " " + professore.getNomeProfessore()%>
    </h5>
    <h5 id="dataScelta">Data</h5>
    <h5 id="orarioScelto">Ora</h5>
</div>
<div class="modal-body">
    <br>
    <table>
        <tr>
            <td style="display: block;"><h5>Motivazione</h5></td>
            <td>
                            <textarea id="Motivazione" rows="4" cols="50" ></textarea>
                <br>
            </td>

        </tr>

        <br>
        <tr style="padding-top: 10px;">
            <td style="display: block;"><h5>Altri studenti</h5></td>
            <td>
                            <textarea id="elencoStudenti" rows="4" cols="50" ></textarea>
            </td>

        </tr>


    </table>
<br>
</div>
<div class="modal-footer">

    <br>
    <a id="undoButton" data-dismiss="modal"> Annulla </a>
    &nbsp;
    <a id="insertButton" data-dismiss="modal"> Conferma </a>


</div>


<style>


    textarea
    {
        resize: none;
    }

    .modal-footer a
    {
     width: 30%;
    }

    .modal-footer
    {
        display: block;
    }

    .modal-header
    {
        display: grid !important;
    }

    .modal-body
    {
        padding: 0 !important;
    }

    .modal-body h5
    {
        text-align: right;
        padding-right: 10px;
    }

    .modal-dialog-centered
    {
        max-width: none;
        width: fit-content;
    }

</style>
