$(function(){
    $("#sendbtn").on('click', function(){
        var jsonData = {
            'input': $("#input").val()
        };
        $.post("ajax",
            jsonData,
            function (result) {
                var res = "status: " + result.status + ", message:" + result.message;
                $("#message").text(res);
            },
            "json"
        );
    });
});
