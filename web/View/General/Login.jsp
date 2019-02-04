<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script src="log.js" type="text/javascript"></script>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../CSS/Login.css">


    <!------ Include the above in your HEAD tag ---------->
</head>
<body id="LoginForm">
<div class="container">
    <div class="login-form">
        <div class="main-div">
            <div class="panel">
                <img src="../img/unisa.png" >
                <img src="../img/logoEz.png">
                   <h2>Login</h2>
                <p>Please enter your email and password</p>
            </div>
            <div id="messageDiv" style="display:none;"></div>
            <form action="access" role="form"  method="post" id="login-form" name="loginForm" autocomplete="off">

                <div class="form-group">


                    <input type="email" class="form-control" id="email" name="email" placeholder="Email Address">

                </div>

                <div class="form-group">

                    <input type="password" class="form-control" id="pass" name=pass placeholder="Password">
                    <div class="checkbox">
                        <input type="checkbox" onclick="showPassword()">Mostra Password
                    </div>

                </div>

                <a href="javascript: log();" class="btn btn-primary" >Log In</a>

            </form>
        </div>

    </div></div></div>


</body>
</html>
