;(function(){



    $(function() {
        init();
        initialTriggerEvent();
    });

    function init() {
    }

    function initialTriggerEvent() {
        $(document).on('click', '.blog-post-title', function () {
            var $this = $(this);
            var id = $this.data("id");
            window.location.href = "/blog/article?id=" + id;
        });
    }

}());