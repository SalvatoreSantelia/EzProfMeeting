<%@ page import="myJava.model.beans.Studente" %>
<%@ page import="myJava.model.beans.Professore" %>
<%@ page import="java.util.List" %>
<%@ page import="myJava.model.general.DataManager" %>
<%@ page import="myJava.model.beans.Messaggio" %>
<%@ page import="java.sql.Date" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<%
    Studente studente = (Studente) session.getAttribute("user");
    int id = studente.getIdStudente();
    DataManager dataManager = new DataManager();
    List<Professore> professori = dataManager.visualizzaProfessori();
%>

<html>
<head>

    <link rel="stylesheet" type="text/css" href="../CSS/Message.css">
    <!------ Include the above in your HEAD tag ---------->
</head>

<body>
<div class="container">
    <h3 class=" text-center">Messaging</h3>
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
                        for(Professore A : professori){
                            Messaggio mess =  dataManager.getLastDataMessaggio(id,A.getIdProfessore());
                            System.out.println(mess.toString());
                            Date date = mess.getDataMessaggio();
                            String data="";
                            if(date == null) data="";
                            String testo = mess.getTestoMessaggio();
                            if(testo==null) testo="";

                    %>

                    <div class="chat_list">
                        <div class="chat_people">
                            <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                            <div class="chat_ib">
                                <h5><%=A.getNomeProfessore()%> <%=A.getCognomeProfessore()%> <span class="chat_date"><%=data%></span></h5>
                                <p><%=testo%></p>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>

                </div>
            </div>
            <div class="mesgs">
                <div class="msg_history">
                    <div class="incoming_msg">

                    </div>


                </div>



                <div class="type_msg">
                    <div class="input_msg_write">
                        <input type="text" class="write_msg" placeholder="Type a message" />
                        <button class="msg_send_btn" type="button"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
                    </div>
                </div>
            </div>
        </div>


    </div></div>
</body>
</html>