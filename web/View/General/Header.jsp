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

<div id="header" class="border border-primary rounded">

    <%
        String home;

        //controllo sessione

        if(true)
            home = "../Studente/HomeStudente.jsp";
        else
            home = "../Professore/HomeDocente.jsp";


    %>

    <nav >
        <a href= <%=home%> >
            <img src="../img/logoEz.png" width="120" height="120"  alt="" style="float: left">
        </a>
        <a href="https://www.unisa.it/">
            <img id="unisaLogo" src="../img/unisa.png" height="120" alt="" >
            <h1 > Università degli studi di Salerno </h1>

        </a>

        <div id="userSession" >
            <ul style="">
                <li>
                    Nome <br> Cognome <br> 0512100000 <br> n.congnome00@studenti.unisa.it
                </li>
                <li>
                    <img id="userImage" src="../img/user.jpg" alt="">
                </li>
            </ul>
        </div>

    </nav>

</div>