$(document).ready(	function(){


    $("li").click(function(){

       $("#form"+this.id).submit();
    });

});