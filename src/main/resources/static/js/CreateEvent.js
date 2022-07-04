let searchResult = [];

$(function() {

    $('.search').click(() => {
        fetch('/search?name=' + $('.search-name').val())
        .then((res) => {
            return res.json();
        })
        .then((json) => {
            searchResult = json;

            $(".search-result-box").children().remove();
            $.each(json, function(index, value) {
                $(".search-result-box").append("<p class='search-result-name' id=user-" + index + ">" + value.name);
            })
            
            $('.search-result-name').click(function() {
                const id = $(this).attr('id').split("-")[1];

                if ($(this).hasClass("selected")) {
                    $(this).removeClass("selected");
                    searchResult[id].selected = false;
                } else {
                    $(this).addClass("selected");
                    searchResult[id].selected = true;
                }
            })
            
        })
    })

    $('.create-event').click(() => {
        const dates = [];
        $.each($(".table-calendar .selected"), function(index, selectedDate) {
            let date = $(selectedDate).attr("data-date");
            let month = $(selectedDate).attr("data-month");
            let year = $(selectedDate).attr("data-year");

            if (date.length === 1) {
                date = '0' + date;
            }

            if (month.length === 1) {
                month = '0' + month;
            }
            dates.push(year + "-" + month + "-" + date);
        })

        const users = [];
        users.push({
            name : user.name,
            address :user.address
        })
        searchResult.filter(user => user.selected)
                    .forEach(user => {
                        users.push(user);
                    });

        let loading = document.createElement("div");
        loading.className = 'loader';
        document.body.appendChild(loading);

        $.ajax({
            url: "/events",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                "eventName" : $(".event-name").val(),
                "memo" : $(".event-memo").val(),
                "candidateDates" : dates,
                "users" : users,
                "createUserName" : user.name
            })
            
        }).then(() => {
            $('.go-events').submit();
        }, () => {
            $(".loader").removeClass("loader");
        })

        
    })


    $('.date-picker').on('click', function() {
        if ($(this).hasClass("selected")) {
          $(this).removeClass("selected");
        } else if ($(this).hasClass("today")) {
          $(this).removeClass("today");
          $(this).addClass("selected");
        } else {
          $(this).addClass("selected");
        }
    })

    

});
