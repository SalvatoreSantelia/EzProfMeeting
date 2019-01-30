$(document).ready
(
    function () {

        $("[type='number']").keypress(function (evt) {
            evt.preventDefault();
        });


        $(document).on('click', '.editReceivement',
            function () {
                $("[name='idEdit']").val($(this).attr('id'));
                $("[name='startHourEdit']").val($(this).data('inizio'));
                $("[name='endHourEdit']").val($(this).data('fine'));
                $("[name='placeEdit']").val($(this).data('luogo'));
                $("[name='groupEdit']").val($(this).data('posti'));


            })

    }
)