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
            $.post('/admin/collection/add',param,function(data){
                if (data.code === 1) {
                    window.location.href = '/admin/collection';
                }else{
                    alert("error");
                }
            });
        });
    }



















}());