var dataP, orarioS, orarioF, idRicevimento;

$(document).ready(
    function () {

        $(document).on('click', '.ricevimento', function () {


            dataP = $(this).data("data");
            orarioS = $(this).data("inizio");
            orarioF = $(this).data("fine");
            idRicevimento = $(this).data("ricevimento");

        });

        $("#addPrenotazione").on('shown.bs.modal', function (e) {


            $("#dataScelta").text(dataP);
            $("#orarioScelto").text(orarioS + " - " + orarioF);

        });


        $(document).on('change', "#Motivazione", function () {
            $("#Motivazione").text($(this).val());

        })


        $(document).on('change', "#elencoStudenti", function () {
            $("#elencoStudenti").text($(this).val());

        })


        $(document).on('click', '#insertButton', function () {


            if ($("#Motivazione").text().trim() === "") {
                alert("Inserire la motivazione per prenotare");
                return
            }

            var motivazioni, studenti;

            motivazioni = $("#Motivazione").text().trim();
            studenti = $("#elencoStudenti").text().trim();

            $.ajax(
                {
                    type: "POST",
                    url: "../General/booking",
                    data:
                        {
                            idRicevimento: idRicevimento,
                            motivazione: motivazioni,
                            studenti: studenti,
                            orario: orarioS,
                            action: "inserisciPrenotazione"
                        },
                    success: function (results) {
                        if (results != null || results !== "FAILURE") {
                            window.location.reload();
                        }
                    }

                });

        });

    });