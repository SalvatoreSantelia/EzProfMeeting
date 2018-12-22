var SEL_COLOR = "#e6f9ff"
var previous_row;
var previous_col;
var MY_BUTTON = '<button type="button" id="addButton" class="btn btn-outline-primary" style="display:block; width: 100%; height: 100%;">Crea Ricevimento</button>'




             $(document).ready(	function(){





                     $("#calendar td").on("click", "#addButton", function ()
                     {
                /*         var  clicked = "Orari";

                         for(var i = 1; i<= 3*9; i++)
                         {

                             if( rgb2hex($("#calendar tr").eq(i).find("td").eq(previous_col).css("background-color")) == SEL_COLOR) clicked = clicked + $("#calendar tr").eq(i).find("td").eq(0).html() + "\n" ;

                         }

                         alert(clicked);*/
                         $("#popup").modal();

                     });


                     $("#calendar td").click(
                         function () {

                             var col, row;
                             col = parseInt( $(this).index() );
                             row = parseInt( $(this).parent().index() );




                    if(previous_col==null) {

                        previous_col = col;
                        previous_row =  row;
                        $(this).css("background-color", SEL_COLOR);
                        $("#calendar tr").eq(row).find("td").eq(col).html(MY_BUTTON);
                    }
                    else {
                        $("#addButton").remove();

                        if (col != previous_col) {
                            cleanSelection();

                            return;
                        }


                        $("#calendar tr").eq(row).find("td").eq(col).html(MY_BUTTON);

                        if (row < previous_row) {
                            extendStart(row);
                            previous_row = row;
                            return;
                        }

                        secondClick(col, row);
                    }
                         }

    );


}
);

function rgb2hex(o){
    var orig = o.toString()
    var rgb = orig.replace(/\s/g,'').match(/^rgba?\((\d+),(\d+),(\d+)/i);
    return (rgb && rgb.length === 4) ? "#" +
        ("0" + parseInt(rgb[1],10).toString(16)).slice(-2) +
        ("0" + parseInt(rgb[2],10).toString(16)).slice(-2) +
        ("0" + parseInt(rgb[3],10).toString(16)).slice(-2) : orig;
}
function extendStart(row)
{
    for(var i = row; i<= previous_row; i++)
    {
        $("#calendar tr").eq(i).find("td").eq(previous_col).css("background-color", SEL_COLOR);
    }
}

function cleanSelection() {
    for(var i=0; i<9*3; i++)
    {
        $("#calendar tr").eq(i).find("td").eq(previous_col).css("background-color", "#FFFFFF");
    }

    previous_row = null;
    previous_col = null;

}


function secondClick(col, row)
{

    for(var i=previous_row; i<=row; i++)
    {
        $("#calendar tr").eq(i).find("td").eq(col).css("background-color", SEL_COLOR);
    }


    for(var i=row+1; i<9*3; i++)
    {
        $("#calendar tr").eq(i).find("td").eq(col).css("background-color", "#FFFFFF");
    }





}