$(document).ready(
  function () {


  }
);

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