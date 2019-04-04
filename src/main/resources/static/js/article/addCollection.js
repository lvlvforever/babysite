;(function(){



    $(function() {
        init();
        initialTriggerEvent();
    });

    function init() {

    }

    function initialTriggerEvent() {
        $(document).on('click', '#saveBtn', function () {
            var param = $('#addForm').serialize();
            $.post('/blog/collection/add',param,function(data){
                if (data.code === 1) {
                    window.location.href = '/collection';
                }else{
                    alert("error");
                }
            });
        });
    }



















}());