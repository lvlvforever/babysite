;(function(){




    $(function() {
        init();
        initialTriggerEvent();
    });

    function init() {

    }

    function initialTriggerEvent() {

        $(document).on('click', '.js-del', function () {
            var $this = $(this);
            var id = $this.data("id");
            $.post('/admin/article/delete',{objectId:id,_method:"DELETE"},function(data){
                if (data.code === 1) {
                    window.location.href = '/admin/article';
                }else{
                    alert("error");
                }
            });
        });
        $(document).on('click', '.js-addArticle', function () {
            window.location.href = "/admin/article/addArticleView";
        });


        $(document).on('click', '.js-query', function () {
            var url = '/article?id=' + $(this).data('id');
            window.open(url, 'blank')
        });
    }



















}());