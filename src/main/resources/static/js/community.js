function post() {
    var questionId = $("#question_id").val();
    var content = $("#commont_content").val();
    var data = {
        "parentId": questionId,
        "content": content,
        "type": 1
    };
    $.ajax({
        type: "POST",
        contentType: 'application/json',
        url: "/comment",
        data: JSON.stringify(data),
        success: function (result) {
            if(result.code == '200'){
                $("#commont_sh").hide();
            }else{
                alert(result);
            }
        },
        dataType: "json"
    });
}
