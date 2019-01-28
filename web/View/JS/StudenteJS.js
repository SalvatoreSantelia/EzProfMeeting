$(document).ready(function(){
    $("li").click(function(){
        $("#form"+this.id).submit();
    });


    /*$("BottoneRimuovi").click(function(){
         $(this.id).submit();
     });*/

});