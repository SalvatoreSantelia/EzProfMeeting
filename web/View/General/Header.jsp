<%@ page import="myJava.model.beans.Studente" %>
<%@ page import="myJava.model.beans.User" %>
<%@ page import="myJava.model.beans.Professore" %>
<%@ page import="myJava.model.general.AccessManager"%>
<%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 25/12/2018
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="../CSS/Header.css">

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<div id="header" class="border border-primary rounded">


<%
    if(session.getAttribute("user")==null){
        response.sendRedirect("../View/General/Login.jsp");
    }
%>
    <nav >
        <a >
            <img src="../img/logoEz.png" width="120" height="120"  alt="" style="float: left">
        </a>
        <a href="https://www.unisa.it/">
            <img id="unisaLogo" src="../img/unisa.png" height="120" alt="" >
            <h1 > Università degli studi di Salerno </h1>

        </a>

        <div id="userSession" >
            <ul style="">
                <%
                    if(session.getAttribute("user") instanceof Studente){
                        Studente studente = new Studente();
                        studente = (Studente) session.getAttribute("user");
                %>
                <li>
                    <%=studente.getNomeStudente()%> <br> <%=studente.getCognomeStudente()%> <br> <%=studente.getMatricola()%> <br> <%=studente.getEmailStudente()%>
                </li>
                <li>
                    <img class="userImage" src="../img/user.jpg" alt="" style="    margin-right: 10px;">
                </li>
                <%
                    }
                %>


                <%
                    if(session.getAttribute("user") instanceof Professore){
                        Professore professore = new Professore();
                        professore = (Professore) session.getAttribute("user");
                %>
                <li>
                    <%=professore.getNomeProfessore()%> <br> <%=professore.getCognomeProfessore()%> <br> <%=professore.getEmailProfessore()%> <br> <%=professore.getUfficioProfessore()%>
                </li>
                <li>
                    <img class="userImage" src="../img/user.jpg" alt="">
                </li>
                <%
                    }
                %>
            </ul>
        </div>

    </nav>

</div>