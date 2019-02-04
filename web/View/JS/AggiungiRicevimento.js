$(document).ready(function () {

    $("[type='number']").keypress(function (evt) {
        evt.preventDefault();
    });

    $(document).on('click', '#addButton',
        function () {


            $("[name='startHour']").val(start);
            $("[name='endHour']").val(end);
        });


    $(document).on('change' , "#group", function () {
       $("#group").val($(this).val());
    })



    $(document).on('change' , "#placeRecivement", function () {
        $("#placeRecivement").val($(this).val());

    })



    $(document).on('click', '#insertButton', function () {


        if ($("#placeRecivement").text().trim() === "") {
            alert("Professore, inserisca un luogo per il ricevimento");
            return
        }

        var start, end, luogo, numPosti;
        start = $("#startHour").val();
        end = $("#endHour").val();
        numPosti = $("#group").find(":selected").val();
        luogo = $("#placeRecivement").val();


        $.ajax(
            {
                type: "POST",
                url: "../General/receivement",
                data:
                    {
                        inizio: start,
                        fine: end,
                        posti: numPosti,
                        luogo: luogo,
                        operazione: "inserimento"
                    },
                success: function (results) {
                    document.location = "../Professore/HomeProfessore.jsp";
                }

            });

    })

});


