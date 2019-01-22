<%@ page import="myJava.model.professore.ReceivementManager"%>
<%@ page import="myJava.model.general.AccessManager"%>
<%@ page import="myJava.model.beans.Professore" %>
<%@ page import="java.util.List" %>
<%@ page import="myJava.model.general.DataManager" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../General/Header.jsp"%>
<script src="../JS/StudenteJS.js"></script>



<html>
<head>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../CSS/profiloStudente.css">
    <!------ Include the above in your HEAD tag ---------->
</head>
<body id="StudenteForm">




<div class="divStudente">

    <div class="nav-side-menu navsidemenu">
        <div class="brand">Studente</div>
        <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>

        <div class="menu-list">

            <ul id="menu-content" class="menu-content collapse out">

                <li>
                    <a href="#">
                        <i class="fa fa-dashboard fa-lg"></i> Visualizza Prenotazione
                    </a>
                </li>
                <li>
                    <a href="../General/Messaggi.jsp">
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
            <h2 style="margin-left:10%">Lista Professori</h2>
            <br>
            <!-- contacts card -->
            <%
                DataManager dataManager = new DataManager();
                List<Professore> professori = dataManager.visualizzaProfessori();
                int count=0;
            %>

            <div class="card card-default" id="card_contacts">
                <div id="contacts" class="panel-collapse collapse show" aria-expanded="true" style="">
                    <ul class="list-group pull-down" id="contact-list">


                        <%
                            for(Professore A : professori){
                        %>
                        <form id="form<%=count%>" action="../Studente/ProfiloDocente.jsp" method="post">
                        <li class="list-group-item singoloprofessore" id=<%=count%>>
                            <div class="row w-100">

                                <div class="col-12 col-sm-6 col-md-3 px-0">
                                    <img src="image/<%=A.getNomeProfessore()%><%=A.getCognomeProfessore()%>.jpg" alt="http://demos.themes.guide/bodeo/assets/images/users/m101.jpg" class="rounded-circle mx-auto d-block img-fluid">
                                </div>

                                <div class="col-12 col-sm-6 col-md-9 text-center text-sm-left">
                                    <span class="fa fa-mobile fa-2x text-success float-right pulse" title="online now"></span>
                                    <label class="name lead"><%=A.getNomeProfessore()%> <%=A.getCognomeProfessore()%></label>
                                    <br>
                                    <span class="fa fa-map-marker fa-fw text-muted" data-toggle="tooltip" title="" data-original-title="5842 Hillcrest Rd"></span>
                                    <span class="text-muted"><%=A.getUfficioProfessore()%></span>
                                    <br>
                                    <span class="fa fa-envelope fa-fw text-muted" data-toggle="tooltip" data-original-title="" title=""></span>
                                    <span class="text-muted small text-truncate"><%=A.getTelefonoProfessore()%> <%=A.getEmailProfessore()%></span>
                                </div>
                            </div>
                            <input type="hidden" name="email" value="<%=A.getEmailProfessore()%>" id="email<%=count%>">
                        </li>
                        </form>
                        <%
                            count++;}
                        %>


                    </ul>
                    <!--/contacts list-->
                </div>
            </div>
        </div>
    </div>


</div>
</body>
</html>
