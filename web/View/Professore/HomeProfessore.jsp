<%@ page import="myJava.model.professore.ReceivementManager"%>
<%@ page import="myJava.model.general.AccessManager"%>
<%@ page import="myJava.model.beans.Professore" %>
<%@ page import="java.util.List" %>
<%@ page import="myJava.model.general.DataManager" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../General/Header.jsp"%>
<script src="../JS/ProessoreJS.js"></script>



<html>
<head>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../CSS/HomeProfessore.css">
    <!------ Include the above in your HEAD tag ---------->
</head>
<body id="ProfessoreForm">


<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <%@include file="VisualizzaPrenotazioni.jsp"%>
        </div>
    </div>
</div>



<div class="divProfessore">

    <div class="nav-side-menu navsidemenu">
        <div class="brand">Professore</div>
        <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>

        <div class="menu-list">

            <ul id="menu-content" class="menu-content collapse out">
                <li>
                    <a href="../General/MessaggiProfessore.jsp">
                        <i class="fa fa-dashboard fa-lg"></i><img src="../img/messageIcon.png" style="width:20px"> Messaggi
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-dashboard fa-lg"></i> Aiuto
                    </a>
                </li>
            </ul>
        </div>
    </div>


    <div class="containerA">
        <div class="container">


            <%@include file="../General/Calendario.jsp"%>

                </div>
            </div>
        </div>
    </div>


</div>
</body>
</html>
