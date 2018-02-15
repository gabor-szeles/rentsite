console.log("anyád")


$(document).ready(function(){
    $("#username").change(function(){
        $("#message").html("<p></p> checking...");
        var username = $("#username").val();
        $.ajax({
            type:"post",
            url:"/username",
            data:{"username":username},
            success:function(data){
                if (JSON.parse(data).username_exist) {
                    $("#message").html('<span style="font-size:13px; color: red"> Username already taken</span>');
                }
                else {
                    $("#message").html('<span style="font-size:13px; color: black"> Username available</span>');
                }
            }
        });
    });
});