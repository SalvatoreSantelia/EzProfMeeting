<%@ page import="myJava.model.general.AccessManager" %>
<%@ page import="myJava.model.beans.Studente" %>
<%@ page import="myJava.model.beans.Professore" %><%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 14/12/2018
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../General/Header.jsp" %>

<html>
<head>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../CSS/HomeStudente.css">
    <!------ Include the above in your HEAD tag ---------->
</head>

<body id="StudenteForm">

<%
    String email = request.getParameter("email");
    DataManager accessManager = new DataManager();
    Professore professore =  accessManager.getProfessoreByEmail(email);
%>


<div style="margin-top: 30px;">
    <div style="float:left">
        <ul>
            <li ><img style="height: 150px; display: block; margin: auto;" src="http://demos.themes.guide/bodeo/assets/images/users/m101.jpg"></li>
            <li style="padding-left: 5px"><%=professore.getNomeProfessore()%> <%=professore.getCognomeProfessore()%></li>
            <li style="padding-left: 5px"><%=professore.getUfficioProfessore()%></li>
            <li style="padding-left: 5px"><%=professore.getEmailProfessore()%></li>
            <li style="height: 150px;">     <a href="../General/MessaggiStudente.jsp" target="_blank">
                <img style="height: 150px; display: block; margin: auto;" src="../img/messageIcon.png" alt="SMS">
            </a></li>
        </ul>

    </div>
    <div class="container" style="max-width: 1000px !important; ">
        <%@include file="../Studente/CalendarioStudente.jsp"%>
    </div>
</div>


</body>
</html>
