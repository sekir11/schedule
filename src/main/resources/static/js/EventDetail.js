$(function() {

    $(".select-status").change(function() {
        const splits = $(this).attr("id").split("-");
        const name = splits[2];
        const id = splits[1];
        console.log(id);

        $.ajax({
            url: "/participants",
            type: "PATCH",
            contentType: "application/json",
            data: JSON.stringify({
                "eventDateId" : id,
                "userName" : name,
                "status" : $(this).val()
            })
            
        })
    })
});