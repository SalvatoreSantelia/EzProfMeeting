$(document).ready(function(){
    $("li").click(function(){
        $("#form"+this.id).submit();
    });


    $(".tdRimuovi").click(function(event){
        var idPrenotazione = $("#idPrenotazione"+this.id).val();
        var idStudente = $("#idStudente"+this.id).val();
        var action="rimuoviPrenotazione";
        $.post("BookingServlet", {
            "idPrenotazione": idPrenotazione,
            "action": action,
        }, function (data, status) {
            alert("Eliminazione riuscita");
        });

        //event.stopPropagation();
        //event.stopImmediatePropagation();
    });

});