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
            $.post('/blog/tag/remove',{objectId:id,_method:"DELETE"},function(data){
                if (data.code === 1) {
                    window.location.href = '/tag';
                }else{
                    alert("error");
                }
            });
        });
        $(document).on('click', '.js-addTag', function () {
            window.location.href = "/blog/tag/addTagView";
        });
    }



















}());