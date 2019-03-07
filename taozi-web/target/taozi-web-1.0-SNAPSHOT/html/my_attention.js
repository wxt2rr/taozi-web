$(function () {
    $.ajax({
        url: '/diary/getMyAttention.do',
        dataType: 'json',
        data: {
        },
        success: function(data) {
            var $div = $("#info_div");
            if(data.body.code == 200) {
                var list = data.body.data;
                for(var i=0;i<list.length;i++){
                    var info = list[i];
                    var html = "<a href='javascript:;' id='"+info.user_id+"' class='aui-flex b-line'>\n" +
                        "                        <div class='aui-follow-img'>\n" +
                        "                        <img src='"+info.head_url+"' alt=''>\n" +
                        "                        </div>\n" +
                        "                        <div class='aui-flex-box'>\n" +
                        "                        <h3>"+info.nickname+"</h3>\n" +
                        "                    <p>"+info.profession+"</p>\n" +
                        "                    <p>\n" +
                        "                    <em><i>"+info.aboutme+"</i></em>\n" +
                        "                    </p>\n" +
                        "                    </div>\n" +
                        "                    </a>";
                    $div.append(html);
                }
            }else{
                alert("not login");
            }
        }
    });
});