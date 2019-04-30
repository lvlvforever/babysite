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

        queryCollection();
        queryTags();

    }

    function queryCollection() {
        var url = '/admin/collection/list';
        $.get(url, function (data) {

            data = data.data;
            var html = '';
            $.each(data, function (x, i) {
                html += '<option value="' + i.name + '">' + i.name + '</option>';
            })
            $('#article-collection').html(html);


        });
    }

    function queryTags() {
        var url = '/admin/tag/list';
        $.get(url, function (data) {

            data = data.data;
            var html = '';
            $.each(data, function (x, i) {
                html += '<option value="' + i.name + '">' + i.name + '</option>';
            })
            $('#article-tag').html(html);


        });
    }


    function initialTriggerEvent() {
        $(document).on('click', '#saveBtn', function () {
            var param = {};
            param.name = $('#article-name').val();
            param.code = $('#article-code').val();
            param.html = $('#article-html').val();
            param.collection = $('#article-collection').val();
            param.tags = $('#article-tag').val();


            alert(JSON.stringify(param));

            $.ajax({
                url: '/admin/article/add',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(param),
                dataType: 'json'

            }).done(function (data) {

                if (data.code === 1) {
                    window.location.href = '/admin/article';
                }else{
                    alert("error");
                }
            });


        });
    }


}());