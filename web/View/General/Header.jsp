<%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 25/12/2018
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>

    #header a,  #header a:hover
    {
        color: black;
        text-decoration: none;
    }

    #header a h1
    {
        display: inline;
        font-family: "Bodoni MT", Didot, "Didot LT STD", "Book Antiqua", Garamond, "Times New Roman", serif;


    }


    #header
    {
        background-color: #deebff;
        padding-top: 10px;
        padding-bottom: 10px;
    }

    #userSession
    {
        font-family: "Century Gothic", CenturyGothic, Geneva, AppleGothic, sans-serif;
        margin-top: 10px;
        float: right;
    }

    #userSession ul
    {
        list-style: none;

    }

    #userSession ul li
    {
        float: left;
    }

    #userImage
    {
        height: 100px;
    }

    #unisaLogo
    {
        margin-left: 5%;
    }

</style>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">


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