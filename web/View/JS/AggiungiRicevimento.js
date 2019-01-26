$(document).ready(function() {

        $("[type='number']").keypress(function (evt) {
            evt.preventDefault();
        });

    $(document).on('click', '#addButton',
     function () {

        alert(end);
         $("[name='startHour']").val(start);
        $("[name='endHour']").val(end);
    });


    $(document).on('click', '#insertButton', function () {

        alert(blockCount);

    })

});


