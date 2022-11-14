$(document).ready(function () {
    $.ajax({
        url: "https://www.googleapis.com/books/v1/volumes?q=intitle:java"
    }).done(function (data) {
        for (var i = 0; i < data.items.length; i++) {
            $("body").append(
                "<p>" + data.items[i].volumeInfo.title + "</p>");
        }
    });
});

