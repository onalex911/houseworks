
    $(document).ready(function () {
    $(".pane-list li").click(function () {
        window.location = $(this).find("a").attr("href");
        return false;
    });
});

    $(document).ready(function () {
    $('.rss').append('<span class="hover"></span>').each(function () {
        var $span = $('> span.hover', this).css('opacity', 0);
        $(this).hover(function () {
            $span.stop().fadeTo(500, 1);
        }, function () {
            $span.stop().fadeTo(500, 0);
        });
    });
    $('.facebook').append('<span class="hover"></span>').each(function () {
    var $span = $('> span.hover', this).css('opacity', 0);
    $(this).hover(function () {
    $span.stop().fadeTo(500, 1);
}, function () {
    $span.stop().fadeTo(500, 0);
});
});
    $('.twitter').append('<span class="hover"></span>').each(function () {
    var $span = $('> span.hover', this).css('opacity', 0);
    $(this).hover(function () {
    $span.stop().fadeTo(500, 1);
}, function () {
    $span.stop().fadeTo(500, 0);
});
});
});
