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
            $.post('/admin/collection/remove',{objectId:id,_method:"DELETE"},function(data){
                if (data.code === 1) {
                    window.location.href = '/collection';
                }else{
                    alert("error");
                }
            });
        });
        $(document).on('click', '.js-addCollection', function () {
            window.location.href = "/admin/collection/addCollectionView";
        });
    }



















}());