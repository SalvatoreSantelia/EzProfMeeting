<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
      xmlns:f="http://xmlns.jcp.org/jsf/core">

<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
</head>
    <script src="http://momentjs.com/downloads/moment-with-locales.js"></script>
    <script src="http://momentjs.com/downloads/moment-timezone-with-data.js"></script>
<script src="../JS/AggiungiRicevimento.js"></script>
<link rel="stylesheet" type="text/css" href="../CSS/Ricevimento.css">

<body>



<div style="display:flex;width:100%;">
    <form>
        <div style="width:100%;">
            <p>DATA</p>
            <div class="input-group registration-date-time">
            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
            <input class="form-control" name="registration_date" id="registration-date" type="date">
            </div>
        </div>


        <div style="width:100%;">
            <p>ORARIO</p>

            <div class="input-group registration-date-time">
            <label class="control-label col-sm-3" for="registration-date">OrarioInizio:</label>
            <span class="input-group-addon" id="basic-addon2"><span class="glyphicon glyphicon-time" aria-hidden="true"></span></span>
            <input class="form-control" name="registration_time" id="registration-time1" type="time">
            </div>
            <div class="input-group registration-date-time">
            <label class="control-label col-sm-3" for="registration-date">OrarioFine:  </label>
            <span class="input-group-addon" id="basic-addon3"><span class="glyphicon glyphicon-time" aria-hidden="true"></span></span>
            <input class="form-control" name="registration_time" id="registration-time2" type="time">
            </div>
        </div>

        <div style="width:100%;">
            <p>LUOGO</p>
            <textarea rows="2" cols="2" style="width:20%; max-width:400px; max-height:100px;"></textarea>
        </div>

        <div style="width:100%;margin-left:6%">
            <p>NUMERO STUDENTI</p>
            <input type="number" min="1" max="5" style="width:10%">
        </div>
    </form>
</div>



</body>



</html>
