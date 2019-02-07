
$(document).ready(function () {

    $(document).keyup(
        function (e) {
            if(e.which==13)
            {
                log();
            }
        }
    )


});


function showPassword() {

    var x = document.getElementById("pass");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }

}
function log(){
    var username = $("#email").val();
    var password = $("#pass").val();
    if(username == "" ){
        $('#messageDiv').css("display","none");
        alert("Username is required");
        return;
    }


    if(password == ""){
        $('#messageDiv').css("display","none");
        alert("Password is required");
        return;
    }
    $.ajax({

        type: "POST",
        url: "login",
        data : {
            username : username,
            password : password
        },
        success : function(results){
            if(results != null && results != ""){

                showMessage(results);

                $('#messageDiv').css("display","block");
            }else{
                $('#messageDiv').css("display","none");
                $('#messageDiv').html("");
                alert("Some exception occurred! Please try again.");
            }
        }
    });
    function showMessage(results){
        if(results == 'FAILURE'){

            $('#messageDiv').html("<font color='red'>Username or password incorrect </font>")

        }else{
            /*
            var uri = "LoginServlet2?name="+results;
            var res = encodeURI(uri);
            window.location.href = res;
        */
            document.loginForm.submit();
        }
    }





}