$(document).ready(function(){

  $(".messButton").click(function(){
     var lato= $(".invioLato").val();
     var idProfessore = $(".invioIdProfessore").val();
     var idStudente = $(".invioIdStudente").val();
     var testo = $(".invioTesto").val();

      if($.trim($('.invioTesto').val()) == ''){
          alert("Devi inserire almeno un carattere");
      }
      else {
          if (lato == "professore") {
              $.post("inviaMessaggio", {
                  "idStudente": idStudente,
                  "idProfessore": idProfessore,
                  "testo": testo,
                  "lato": lato
              }, function (data, status) {
                  $(".msg_history").empty();
                  $.each(data, function (i, item) {
                      if ((item.lato).localeCompare("studente")) {
                          $(".msg_history").append("<div class=\"outgoing_msg\"> <div class=\"sent_msg\">" +
                              "<p>" + item.testo + "</p>" +
                              "<span class=\"time_date\">" + item.data + " " + item.orario + "</span></div>" +
                              "</div>");
                          $("#data" + idStudente).text("");
                          $("#data" + idStudente).text(item.data + " " + item.orario);
                      }
                      if ((item.lato).localeCompare("professore")) {
                          $(".msg_history").append("<div class=\"incoming_msg\">" +
                              "<div class=\"incoming_msg_img\">" +
                              "<img src=\"https://ptetutorials.com/images/user-profile.png\" alt=\"sunil\"></div>" +
                              "<div class=\"received_msg\">" +
                              "<div class=\"received_withd_msg\">" +
                              "<p>" + item.testo + "</p>" +
                              "<span class=\"time_date\">" + item.data + " " + item.orario + "</span></div>" +
                              "</div></div>");
                          $("#data" + idProfessore).text("");
                          $("#data" + idProfessore).text(item.data + " " + item.orario);
                      }
                  });
                  $(".invioTesto").val("");
                  $("#lastMessaggio" + idStudente).text("");
                  $("#lastMessaggio" + idStudente).append(testo);
              });
          } else {
              $.post("inviaMessaggio", {
                  "idStudente": idStudente,
                  "idProfessore": idProfessore,
                  "testo": testo,
                  "lato": lato
              }, function (data, status) {
                  $(".msg_history").empty();
                  $.each(data, function (i, item) {
                      if ((item.lato).localeCompare("professore")) {
                          $(".msg_history").append("<div class=\"outgoing_msg\"> <div class=\"sent_msg\">" +
                              "<p>" + item.testo + "</p>" +
                              "<span class=\"time_date\">" + item.data + " " + item.orario + "</span></div>" +
                              "</div>");
                          $("#data" + idProfessore).text("");
                          $("#data" + idProfessore).text(item.data + " " + item.orario);
                      }
                      if ((item.lato).localeCompare("studente")) {
                          $(".msg_history").append("<div class=\"incoming_msg\">" +
                              "<div class=\"incoming_msg_img\">" +
                              "<img src=\"https://ptetutorials.com/images/user-profile.png\" alt=\"sunil\"></div>" +
                              "<div class=\"received_msg\">" +
                              "<div class=\"received_withd_msg\">" +
                              "<p>" + item.testo + "</p>" +
                              "<span class=\"time_date\">" + item.data + " " + item.orario + "</span></div>" +
                              "</div></div>");
                          $("#data" + idStudente).text("");
                          $("#data" + idStudente).text(item.data + " " + item.orario);

                      }
                  });
                  $(".invioTesto").val("");
                  $("#lastMessaggio" + idProfessore).text("");
                  $("#lastMessaggio" + idProfessore).append(testo);
              });
          }
      }

  });


  $(".chatlista").click(function () {
      var idStudente = $("#idStudente"+this.id).val();
      var idProfessore = $("#idProfessore"+this.id).val();
      var lato = $("#lato"+this.id).val();
      var destinatario = $(".nomeDestinatario");
      var nomecognome = $("#nomecognome"+this.id).text();

      $(".msg_history").empty();
      var invioIdStudente = $(".invioIdStudente");
      var invioIdProfessore = $(".invioIdProfessore");
      var invioLato = $(".invioLato");

      invioIdStudente.val(idStudente);
      invioIdProfessore.val(idProfessore);
      invioLato.val(lato);
      destinatario.text("With : "+nomecognome);

      if(lato == "professore") {
          $.post("message", {
              "idStudente": idStudente,
              "idProfessore": idProfessore,
              "lato": lato
          }, function (data, status) {
              $(".msg_history").empty();
              $.each(data, function (i, item) {
                  if ((item.lato).localeCompare("studente")) {
                      $(".msg_history").append("<div class=\"outgoing_msg\"> <div class=\"sent_msg\">" +
                          "<p>" + item.testo + "</p>" +
                          "<span class=\"time_date\">" + item.data + " " + item.orario + "</span></div>" +
                          "</div>");
                  }
                  if ((item.lato).localeCompare("professore")) {
                      $(".msg_history").append("<div class=\"incoming_msg\">" +
                          "<div class=\"incoming_msg_img\">" +
                          "<img src=\"https://ptetutorials.com/images/user-profile.png\" alt=\"sunil\"></div>" +
                          "<div class=\"received_msg\">" +
                          "<div class=\"received_withd_msg\">" +
                          "<p>" + item.testo + "</p>" +
                          "<span class=\"time_date\">" + item.data + " " + item.orario + "</span></div>" +
                          "</div></div>");
                  }
              });

          });
      }
      else
      {
          $.post("message", {
              "idStudente": idStudente,
              "idProfessore": idProfessore,
              "lato": lato
          }, function (data, status) {
              $(".msg_history").empty();
              $.each(data, function (i, item) {
                  if ((item.lato).localeCompare("professore")) {
                      $(".msg_history").append("<div class=\"outgoing_msg\"> <div class=\"sent_msg\">" +
                          "<p>" + item.testo + "</p>" +
                          "<span class=\"time_date\">" + item.data + " " + item.orario + "</span></div>" +
                          "</div>");
                  }
                  if ((item.lato).localeCompare("studente")) {
                      $(".msg_history").append("<div class=\"incoming_msg\">" +
                          "<div class=\"incoming_msg_img\">" +
                          "<img src=\"https://ptetutorials.com/images/user-profile.png\" alt=\"sunil\"></div>" +
                          "<div class=\"received_msg\">" +
                          "<div class=\"received_withd_msg\">" +
                          "<p>" + item.testo + "</p>" +
                          "<span class=\"time_date\">" + item.data + " " + item.orario + "</span></div>" +
                          "</div></div>");
                  }
              });
          });
          }
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

