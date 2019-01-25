$(document).ready(function(){

  $(".chatlista").click(function () {
      var idStudente = $("#idStudente"+this.id).val();
      var idProfessore = $("#idProfessore"+this.id).val();
      var lato = $("#lato"+this.id).val();


      $.post("message",{"idStudente":idStudente,"idProfessore":idProfessore,"lato":lato},function(data,status){
          alert("CIAO"+data);
          $.each(data, function(i,item){
              //if(item.lato=="professore") {
                  $(".msg_history").append("<div class=\"incoming_msg\">" +
                      "<div class=\"incoming_msg_img\">"+
                      "<img src=\"https://ptetutorials.com/images/user-profile.png\" alt=\"sunil\"></div>"+
                      "<div class=\"received_msg\">"+
                      "<div class=\"received_withd_msg\">"+
                      "<p>"+item.testo+"</p>"+
                      "<span class=\"time_date\">"+item.data+" "+item.orario+"</span></div>"+
                      "</div></div>");
              //}
              if(item.lato="studente"){
                  $(".mesgs msg_history").append("<div class=\"outgoing_msg\"> <div class=\"sent_msg\">"+
                      "<p>\"+item.testo+\"</p>"+
                      "<span class=\"time_date\">\"+item.data+\" \"+item.orario+\"</span></div>"+
                      "</div>");
              }

          });
      });
      });

  });




/*
function selChat(user) {

    $("#chatList li").removeClass("active")
    var element = document.getElementById(user);
    element.classList.add("active");
    showName(user);

}

function showName(e)
{
    $("#chatView").html("<h1> " + e + "</h1>");

    //richiesta ajax per caricare i messaggi
}
*/

