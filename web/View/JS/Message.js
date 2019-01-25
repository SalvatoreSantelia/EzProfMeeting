$(document).ready(function(){

  $(".chatlista").click(function () {
      alert("ciao");
      var idStudente = $("#idStudente"+this.id).val();
      var idProfessore = $("#idProfessore"+this.id).val();
      var lato = $("#lato"+this.id).val();

      alert(idProfessore+"bb "+idStudente+" "+lato);
      $.post("message",{"idStudente":idStudente,"idProfessore":idProfessore,"lato":lato},function(data,status){

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

