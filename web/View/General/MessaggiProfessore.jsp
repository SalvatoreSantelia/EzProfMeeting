<%@ page import="myJava.model.beans.Studente" %>
<%@ page import="myJava.model.beans.Professore" %>
<%@ page import="java.util.List" %>
<%@ page import="myJava.model.general.DataManager" %>
<%@ page import="myJava.model.beans.Messaggio" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.sql.Time" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<%
    DataManager dataManager = new DataManager();

    Professore professore = (Professore) session.getAttribute("user");
    int id = professore.getIdProfessore();
    ArrayList<Studente> studenti = dataManager.getStudentiContattati(id);
%>

<html>
<head>
    <title>EzPM Chat</title>
    <script src="../JS/Message.js"></script>
    <link rel="stylesheet" type="text/css" href="../CSS/Message.css">
    <!------ Include the above in your HEAD tag ---------->
</head>

<body>
<input type="hidden" id="lato" value="professore">
<div class="container">
    <h3 class=" text-center">Messaging <h5 class="text-center nomeDestinatario"></h5></h3>
    <div class="messaging">
        <div class="inbox_msg">
            <div class="inbox_people">
                <div class="headind_srch">
                    <div class="recent_heading">
                        <h4>Lista Messaggi</h4>
                    </div>

                </div>
                <div class="inbox_chat">

                    <%
                        DateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
                        DateFormat dff = new SimpleDateFormat("HH:mm:ss");
                        int i=0;
                        for (Studente A : studenti) {
                            Messaggio mess =  dataManager.getLastDataMessaggio(A.getIdStudente(),id);
                            String testo="";
                            String data="";
                            if(mess != null && mess.getDataMessaggio()!=null){
                                Date date = (Date) mess.getDataMessaggio();
                                data=df.format(date);
                                Time time = (Time) mess.getOrarioMessaggio();
                                data= data+" "+dff.format(time);
                                testo = mess.getTestoMessaggio();}

                    %>

                    <div class="chat_list chatlista" id="<%=i%>">
                        <div class="chat_people">
                            <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                            <div class="chat_ib" >
                                <input type="hidden" id="idProfessore<%=i%>" name="idProfessore"  value="<%=id%>">
                                <input type="hidden" id="idStudente<%=i%>" name="idStudente" value="<%=A.getIdStudente()%>">
                                <input type="hidden" id="lato<%=i%>" name="lato" value="<%="professore"%>">
                                <h5><div id="nomecognome<%=i%>"><%=A.getNomeStudente()%> <%=A.getCognomeStudente()%></div> <span class="chat_date" id="data<%=A.getIdStudente()%>"><%=data%></span></h5>
                                <p id="lastMessaggio<%=A.getIdStudente()%>"><%=testo%></p>
                            </div>
                        </div>
                    </div>


                    <%
                            i++; }
                    %>

                </div>
            </div>



            <div class="mesgs">
                <div class="msg_history">



                </div>



                <div class="type_msg">
                    <div class="input_msg_write">
                        <input type="hidden" name="lato" value="" class="invioLato">
                        <input type="hidden" name="idStu" value="" class="invioIdStudente">
                        <input type="hidden" name="idPro" value="" class="invioIdProfessore">
                        <input type="text" class="write_msg invioTesto" placeholder="Type a message" name="inviaMess" />
                        <button class="msg_send_btn messButton"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
                    </div>
                </div>



            </div>





        </div>
</div>
</div>

</body>
</html>