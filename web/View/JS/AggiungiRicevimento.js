$(document).ready(function() {

        $("[type='number']").keypress(function (evt) {
            evt.preventDefault();
        });

    $(document).on('click', '#addButton',
     function () {


         $("[name='startHour']").val(start);
        $("[name='endHour']").val(end);
    });


    $(document).on('click', '#insertButton', function () {

        if($("textarea").val().trim() === "")
        {
            alert("Professore, inserisca un luogo per il ricevimento");
            return
        }

        var start, end, luogo, numPosti;
        start = $("#startHour").val();
        end = $("#endHour").val();
        luogo = $("#place").val();

        $.ajax(
            {
                type: "POST",
                url: "receivement",
                data:
                    {
                        inizio: start,
                        fine: end,
                        luogo:  luogo,
                        operazione: "inserimento"
                    },
                success: function (results) {
                    if(results!=null && results!="" && results!="FAILURE")
                    {
                        alert("fungeee")
                        $( "#container" ).load(window.location.href + " #container" );
                    }
                }

            });

    })

});


