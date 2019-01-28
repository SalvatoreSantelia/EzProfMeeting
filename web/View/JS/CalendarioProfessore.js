var SEL_COLOR = "#e6f9ff"
var previous_row;
var previous_col;
var start, end, blockCount;
var MY_BUTTON = '<button id="addButton" class="btn btn-outline-primary" data-toggle="modal" data-target="#new" style="display:block; width: 100%; height: 100%; margin: auto; background-color: transparent;">+</button>'
var posButton;

$(document).ready(function () {


        $("#calendar td").click(
            function () {

                if($(this).data("exist")==true)
                    return;

                var col, row;
                col = parseInt($(this).index());
                row = parseInt($(this).parent().index());

                if (col === 0) return;


                if (previous_col == null) {

                    previous_col = col;
                    previous_row = row;
                    start = ($(this).attr('id'));
                    end = ($(this).data('end'));
                    blockCount = 1;

                    $(this).css("background-color", SEL_COLOR);

                    $("#calendar tr").eq(row).find("td").eq(col).html(MY_BUTTON);
                    posButton = row;
                } else {
                    $("#addButton").remove();

                    if (col != previous_col) {
                        cleanSelection();
                        start = end = null;
                        return;
                    }


                    $("#calendar tr").eq(row).find("td").eq(col).html(MY_BUTTON);

                    if (row < previous_row) {
                        extendStart(row);
                        previous_row = row;
                        posButton = row
                        start = ($(this).attr('id'));
                        return;
                    }
                    if (row == posButton) return;
                    secondClick(col, row);
                    posButton = row;

                    end = ($(this).data('end'));
                }
            }
        );


    }
);



function rgb2hex(o) {
    var orig = o.toString()
    var rgb = orig.replace(/\s/g, '').match(/^rgba?\((\d+),(\d+),(\d+)/i);
    return (rgb && rgb.length === 4) ? "#" +
        ("0" + parseInt(rgb[1], 10).toString(16)).slice(-2) +
        ("0" + parseInt(rgb[2], 10).toString(16)).slice(-2) +
        ("0" + parseInt(rgb[3], 10).toString(16)).slice(-2) : orig;
}

function extendStart(row) {
    for (var i = row; i <= previous_row; i++) {
        if( $("#calendar tr").eq(i).find("td").eq(previous_col).data("exist") ==true)
        {
            cleanSelection();
            return;
        }
        $("#calendar tr").eq(i).find("td").eq(previous_col).css("background-color", SEL_COLOR);
    }
}

function cleanSelection() {
    for (var i = 0; i < 9 * 2; i++) {
        $("#calendar tr").eq(i).find("td").eq(previous_col).css("background-color", "#FFFFFF");
    }

    $("#addButton").remove();
    previous_row = null;
    previous_col = null;

}


function secondClick(col, row) {


    for (var i = previous_row; i <= row; i++) {
        if( $("#calendar tr").eq(i).find("td").eq(previous_col).data("exist") ==true)
        {
            cleanSelection();
            return;
        }
        $("#calendar tr").eq(i).find("td").eq(col).css("background-color", SEL_COLOR);
    }


    for (var i = row + 1; i < 9 * 2; i++) {
        $("#calendar tr").eq(i).find("td").eq(col).css("background-color", "#FFFFFF");
    }


}