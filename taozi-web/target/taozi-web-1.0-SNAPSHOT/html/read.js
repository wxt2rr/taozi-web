$(function(){
    /*if(navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            function (position) {
                var longitude = position.coords.longitude;
                var latitude = position.coords.latitude;
                console.log(longitude)
                console.log(latitude)
            },
            function (e) {
                var msg = e.code;
                var dd = e.message;
                console.log(msg)
                console.log(dd)
            }
        )
    }*/
    $.ajax({
        url: '/diary/getBlogKind.do',
        dataType: 'json',
        data: {
        },
        success: function(data) {
            if(data.body.code == 200) {
                var secondKinds = data.body.data;
                for (var i = 0; i < secondKinds.length; i++) {
                    $("#blog_kind_id").append("<option value='" + secondKinds[i].id + "'>" + secondKinds[i].title + "</option>");
                }
            }
        }
    });
    $.ajax({
        url: '/diary/getDiaryKind.do',
        dataType: 'json',
        data: {
        },
        success: function(data) {
            if(data.body.code == 200) {
                var secondKinds = data.body.data;
                for (var i = 0; i < secondKinds.length; i++) {
                    $("#article_kind_id").append("<option value='" + secondKinds[i].id + "'>" + secondKinds[i].title + "</option>");
                }
            }
        }
    });

    $("#btnPublish").click(function () {
        var title = $("#title").val();
        var content = editor.getValue();
        var articleKindId = $("#article_kind_id").val();
        var blogKindId = $("#blog_kind_id").val();
        if(title == null){
            alert("写个标题啊");
            return;
        }
        if(content == null){
            alert("确定啥都不写?");
        }
        if(articleKindId == 0){
            alert("选个文章分类");
            return;
        }
        if(blogKindId == 0){
            alert("选个博客分类");
            return;
        }

        var form = new FormData();
        form.append("title",title);
        form.append("content",content);
        form.append("articleKindId", articleKindId);
        form.append("blogKindId", blogKindId);

        $.ajax({
            url : '/diary/saveBlog.do',
            dataType : 'json',
            type : 'POST',
            data : form,
            async: true,
            processData: false,
            contentType: false,
            success : function(data) {
                if(data.body.code == 200){
                    alert("保存成功");
                    window.location.href = "/jumpPage/html/user_diary";
                }else{
                    alert("保存失败");
                }
            }
        });
    });
});
