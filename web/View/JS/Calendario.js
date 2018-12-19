var SEL_COLOR = "#e6f9ff"
var previous_row;
var previous_col;


$(document).ready(	function(){




    $("#calendar td").click(
         function () {

             var col, row;
             col = parseInt( $(this).index() );
             row = parseInt( $(this).parent().index() );



             if(previous_col==null) {

                 previous_col = col;
                 previous_row =  row;
                 $(this).css("background-color", SEL_COLOR);
             }
             else
                 {
                     if(col != previous_col)
                     {
                         cleanSelection();
                         return;
                     }

                     if(row < previous_row ) return;

                     secondClick(col, row);
                 }
         }
    );



}
);

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
    for(var i=row-1; i<9*3; i++)
    {
        $("#calendar tr").eq(previous_row+i).find("td").eq(col).css("background-color", "#FFFFFF");
    }


    for(var i=0; i<=row - previous_row; i++)
    {
        $("#calendar tr").eq(previous_row+i).find("td").eq(col).css("background-color", SEL_COLOR);
    }
}