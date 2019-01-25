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
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="../JS/Message.js"></script>
    <link rel="stylesheet" type="text/css" href="../CSS/Message.css">
    <!------ Include the above in your HEAD tag ---------->
</head>

<body>
<input type="hidden" id="lato" value="professore">
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
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        int i=0;
                        for (Studente A : studenti) {
                            Messaggio mess = dataManager.getLastDataMessaggio(A.getIdStudente(), id);
                            System.out.println(mess.toString());
                            Date date = mess.getDataMessaggio();
                            String data = df.format(date);
                            if (date == null) data = "";
                            String testo = mess.getTestoMessaggio();
                            if (testo == null) testo = "";

                    %>

                    <div class="chat_list">
                        <div class="chat_people">
                            <div class="chat_img"><img src="https://ptetutorials.com/images/user-profile.png"
                                                       alt="sunil"></div>
                            <div class="chat_ib">
                                <h5><%=A.getNomeStudente()%> <%=A.getCognomeStudente()%> <span
                                        class="chat_date"><%=data%></span></h5>
                                <p><%=testo%>
                                </p>
                            </div>
                        </div>
                    </div>


                    <form id="messform<%=i%>" action="message" method="post">
                        <div class="chat_list chatlista" id="<%=i%>">
                            <div class="chat_people">
                                <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                                <div class="chat_ib" >
                                    <input type="hidden" id="idProfessore<%=i%>" name="idProfessore"  value="<%=id%>">
                                    <input type="hidden" id="idStudente<%=i%>" name="idStudente" value="<%=A.getIdStudente()%>">
                                    <input type="hidden" id="lato<%=i%>" name="lato" value="<%="professore"%>">
                                    <h5><%=A.getNomeStudente()%> <%=A.getCognomeStudente()%> <span class="chat_date"><%=data%></span></h5>
                                    <p><%=testo%></p>
                                </div>
                            </div>
                        </div>
                    </form>
                    <%
                        i++;}
                    %>

                </div>
            </div>



            <div class="mesgs">
                <div class="msg_history">
                    <%
                        ArrayList<Messaggio> messaggi = (ArrayList<Messaggio>) session.getAttribute("messaggi");
                    %>
                    <div class="incoming_msg">
                        <%
                            if(messaggi!=null){
                                for(Messaggio a : messaggi){
                                    if((a.getLato()).equals("professore")){
                        %>
                        <div class="outgoing_msg">
                            <div class="sent_msg">
                                <p><%=a.getTestoMessaggio()%></p>
                                <span class="time_date"> <%=a.getDataMessaggio()%></span> </div>
                        </div><%
                        }

                        if((a.getLato()).equals("studente")){
                    %>
                        <div class="incoming_msg">
                            <div class="incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                            <div class="received_msg">
                                <div class="received_withd_msg">
                                    <p><%=a.getTestoMessaggio()%></p>
                                    <span class="time_date"> <%=a.getDataMessaggio()%></span></div>
                            </div>
                        </div>
                        <%
                                    }
                                }
                            }
                        %>
                    </div>


                </div>



                <div class="type_msg">
                    <div class="input_msg_write">
                        <form action="/View/General/inviaMessaggio" method="post">
                            <input type="hidden" name="lato" value="studente">
                            <input type="hidden" name="idStu" value="<%=session.getAttribute("idStud")%>">
                            <input type="hidden" name="idPro" value="<%=session.getAttribute("idProf")%>">
                            <input type="text" class="write_msg" placeholder="Type a message" name="inviaMess" />
                            <%
                                System.out.println(session.getAttribute("idProf")+"ciao");
                                if(session.getAttribute("idStud")!=null){%>
                            <button class="msg_send_btn" type="submit"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
                            <%}
                            %>
                        </form>
                    </div>
                </div>



            </div>




    </div>
</div>
</div>

</body>
</html>