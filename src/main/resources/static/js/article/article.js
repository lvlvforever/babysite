;(function(){

    var textEditor;


    $(function() {
        init();
        initialTriggerEvent();
    });

    function init() {
        testEditor = editormd("test-editormd", {
            width   : "90%",
            height  : 640,
            syncScrolling : "single",
            path    : "../lib/"
        });
    }

    function initialTriggerEvent() {
        $(document).on('click', '#saveBtn', function () {
            var param = $('#addForm').serialize();
            $.post('/blog/article/add',param,function(data){
                if (data.code === 1) {
                    window.location.href = '/admin';
                }else{
                    alert("error");
                }
            });
        });
    }



















}());