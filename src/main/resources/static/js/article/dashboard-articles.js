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
            $.post('/blog/article/delete',{objectId:id,_method:"DELETE"},function(data){
                if (data.code === 1) {
                    window.location.href = '/admin';
                }else{
                    alert("error");
                }
            });
        });
        $(document).on('click', '.js-addArticle', function () {
            window.location.href = "/blog/article/addArticleView";
        });


        $(document).on('click', '.js-query', function () {
            // var param = $('#addForm').serialize();
            // $.post('/blog/article/add',param,function(data){
            //     if (data.code === 1) {
            //         window.location.href = '/admin';
            //     }else{
            //         alert("error");
            //     }
            // });
        });
    }



















}());