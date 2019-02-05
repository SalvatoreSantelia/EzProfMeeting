<%@ page import="myJava.model.beans.Prenotazione" %>
<%@ page import="myJava.model.beans.Ricevimento" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Time" %>
<%@ page import="javafx.scene.control.Alert" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../CSS/HomeStudente.css">
    <!------ Include the above in your HEAD tag ---------->
</head>
<%
    Professore professore = (Professore) session.getAttribute("user");
    DataManager dataManager = new DataManager();

    String x = request.getParameter("idEdit");
%>

<body>

</body>
</html>
