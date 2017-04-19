$(function () {
    function appendDoc(title, based, date, content,toAppend) {
        var date = date || "4/19/2017";
        var title = title || "Titulo"
        var content = content || "Lorem ipsum dolor sit amet, consectetur adipisicing elit.Quisquam beatae, totam consectetur, ad, saepe voluptas eligendi ducimus, praesentium maxime dolore ab quod fugiat facilis debitis quis numquam inventore.Adipisci, saepe.Lorem ipsum dolor sit amet, consectetur adipisicing elit.Quisquam beatae, totam consectetur, ad, saepe voluptas eligendi ducimus, praesentium maxime dolore ab quod fugiat facilis debitis quis numquam inventore.Adipisci, saepe.";
        var based = based || "#B201719042B";
        var toAppend = toAppend || $('#menuTab0');
        var doc = "<div class='col-xs-12 col-sm-6 col-md-4 col-lg-3 doc'><div class='col-xs-12 paper'><div class='col-xs-6 title'><h4>" + title + "</h4><h6>" + date + "</h6></div><div class='col-xs-6 based text-right'><h5>" + based + "</h5></div><div class='col-xs-10 col-xs-offset-1 content text-justify'><p>" + content + "</p></div><div class='btn-group btn-group-justified col-xs-12'><a href='#'class='btn btn-primary'><span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a><a href='#' class='btn btn-primary'><span class='glyphicon glyphicon-share-alt' aria-hidden='true'></span></a><a href='#' class='btn btn-primary'><span class='glyphicon glyphicon-eye-open' aria-hidden='true'></span></a><a href='#' class='btn btn-primary'><span class='glyphicon glyphicon-trash' aria-hidden='true'></span></a></div></div></div>";
        toAppend.children().append(doc);
    }
    for (var i = 0; i < 20; i++) {
        appendDoc(undefined,undefined,undefined,undefined,undefined);
    }
    for (var i = 0; i < 10; i++) {
        appendDoc(undefined,undefined,undefined,undefined,$('#menuTab1'));
    }
    for (var i = 0; i < 3; i++) {
        appendDoc(undefined,undefined,undefined,undefined,$('#menuTab2'));
    }
})
