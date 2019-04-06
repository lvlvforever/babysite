;(function(){

    var textEditor;


    $(function() {
        init();
        initialTriggerEvent();
    });

    function init() {
        testEditor = editormd("test-editormd", {
            width   : "100%",
            height  : 640,
            syncScrolling : "single",
            path    : "/lib/",
            saveHTMLToTextarea : true
        });
    }

    function initialTriggerEvent() {
        $(document).on('click', '#saveBtn', function () {
            var param = $('#addForm').serialize();

            $.post('/admin/article/add',param,function(data){
                if (data.code === 1) {
                    window.location.href = '/admin';
                }else{
                    alert("error");
                }
            });
        });
    }



















}());