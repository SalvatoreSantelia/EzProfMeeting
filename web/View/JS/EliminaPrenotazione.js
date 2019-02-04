var dataP, orarioS, orarioF, idPrenotazione;

$(document).ready(
    function () {

        $(document).on('click', '.prenotato', function () {


            dataP = $(this).data("data");
            orarioS = $(this).data("inizio");
            orarioF = $(this).data("fine");
            idPrenotazione = $(this).data("prenotazione");

        });

        $("#eliminaPrenotazione").on('shown.bs.modal', function (e) {


            $("#dataScelta2").text(dataP);
            $("#orarioScelto2").text(orarioS + " - " + orarioF);

        });



        $(document).on('click', '#deleteButton', function () {


            $.ajax(
                {
                    type: "POST",
                    url: "../General/booking",
                    data:
                        {
                            idPrenotazione: idPrenotazione,
                            orario: orarioS,
                            action: "rimuoviPrenotazione"
                        },
                    success: function (results) {
                        alert(results)
                        if (results != null || results !== "FAILURE") {
                            window.location.reload();
                        }
                    }

                });

        });

    });