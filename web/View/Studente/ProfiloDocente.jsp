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


<div>
    <div style="float:left">
        <ul>
            <li><img src="http://demos.themes.guide/bodeo/assets/images/users/m101.jpg"></li>
            <li><%=professore.getNomeProfessore()%> <%=professore.getCognomeProfessore()%></li>
            <li><%=professore.getUfficioProfessore()%></li>
            <li><%=professore.getEmailProfessore()%></li>
            <li><%=professore.getTelefonoProfessore()%></li>
        </ul>
        <a href="../General/MessaggiStudente.jsp" target="_blank">
            <img src="../img/messageIcon.png" alt="SMS">
        </a>
    </div>
    <div class="container">
        <%@include file="../Studente/CalendarioStudente.jsp"%>
    </div>
</div>


</body>
</html>
