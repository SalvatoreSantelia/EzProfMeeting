<%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 14/12/2018
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message | EzPM</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">




    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
          rel = "stylesheet">
    <script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/simplemodal/1.4.4/jquery.simplemodal.min.js"></script>

    <link rel="stylesheet" href="../CSS/Message.css">

</head>
<body>

<script src="../JS/Message.js"></script>

<!-- Image and text -->
<nav class="navbar navbar-light text-dark">
    <a class="navbar-brand" href="#">
        <img src="../img/messageIcon.png" width="30" height="30" class="d-inline-block align-top" alt="">
        MessageSystem EzProfMeeting
    </a>

</nav>
<div>

    <div id="chatList">
        <ul class="list-group">
            <% for(int i=0; i<10; i++){ %>

            <li class="list-group-item myItem" id=<%= "user"+i%> onclick="selChat(this.id)"> <%= "user"+i%> </li>

            <% } %>
        </ul>
    </div>

    <div id="chatView">

    </div>
</div>
</body>
</html>
