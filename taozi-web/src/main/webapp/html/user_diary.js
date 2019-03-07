$(function () {
    $.ajax({
        url: '/diary/getDiaryListByUserId.do',
        dataType: 'json',
        data: {
        },
        success: function(data) {
            var list = data.body.data;
            for(var i=0;i<list.length;i++){
                var l = list[i];
                $("#list").append("<li><div><font color='#a9a9a9'>标题：</font>" + l.title + "</div><div><font color='#a9a9a9'>内容：</font>"+l.content+"</div></li><hr style=\"height:1px;border:none;border-top:3px solid #555555;\">");
            }
        }
    });
});