$(function () {
    $.ajax({
        url: '/login/getLoginUser.do',
        dataType: 'json',
        data: {
        },
        success: function(data) {
            var $ul = $("#user_info");
            if(data.body.code == 200){
                var user = data.body.data;
                var n = user.nickname;
                if(user.isSetHead != 0){
                    $ul.append("<li id='user_info_li' style='margin-right: 10px;'><img src='"+user.headUrl+"?v="+user.setHeadTime+"' align=\"absmiddle\" style=\"width:50px;height: 50px;margin-top:4px;border-radius:20px;box-shadow:0px 0px 12px #7E7E7E;\"></li>");
                }
                $ul.append("<li id=''style='margin-right: 25px;'>"+n+"</li>");
            }else{
                $("#login_reigster").html("<li><a class=\"button\" href=\"/jumpPage/html/login\">登录</a></li>\n" +
                    "\t\t\t\t\t\t\t\t<li><a class=\"button\" href=\"/jumpPage/html/register\">注册</a></li>");
            }
            $("#upload_head_a").click(function () {
                $("#upload_head").show();
            });

            $("#name_user").hover(function(){
                $("#user_hover").show();
            },function(){
                $("#user_hover").hide();
            });

        }
    });
    $("#go_user_ingo").click(function () {
        window.location.href = "/jumpPage/html/user_info";
    });

    $("#quitLogin").click(function () {
        $.ajax({
            url: '/login/loginOut.do',
            dataType: 'json',
            data: {
            },
            success: function(data) {
                window.location.reload();
            }
        });
    });

    $.ajax({
        url: '/diary/getDiaryList.do',
        dataType: 'json',
        data: {
        },
        success: function(data) {
            var $main = $("#main");
            var list = data.body.data;
            for(var i=0;i<list.length;i++){
                var l = list[i];
                $main.append("<article class=\"post\">\n" +
                    "        <header>\n" +
                    "        <div class=\"title\" style=\"height: 100px\">\n" +
                    "        <h2><a href=\"#\">"+l.title+"</a></h2>\n" +
                    "    </div>\n" +
                    "    <div class=\"meta\">\n" +
                    "        <time class=\"published\" datetime=\"2015-11-01\">"+l.time+"</time>\n" +
                    "    <a href=\"#\" class=\"author\"><span class=\"name\">"+l.nickname+"</span><img src=\""+l.headUrl+"?v="+new Date()+"\" alt=\"\" /></a>\n" +
                    "    </div>\n" +
                    "    </header>\n" +
                    "    <!--<a href=\"#\" class=\"image featured\"><img src=\"images/pic01.jpg\" alt=\"\" /></a>-->\n" +
                    "        <p>"+l.content+"</p>\n" +
                    "    <footer>\n" +
                    "    <!--<ul class=\"actions\">\n" +
                    "        <li><a href=\"#\" class=\"button big\">Continue Reading</a></li>\n" +
                    "    </ul>-->\n" +
                    "    <ul class=\"stats\">\n" +
                    "        <li><a href='/diary/toGetDiaryInfo.do?did="+l.id+"&a_user_id="+l.userId+"'>查看更多</a></li>\n" +
                    "    <li><a href=\"#\" class=\"icon fa-heart\">28</a></li>\n" +
                    "    <li><a href=\"#\" class=\"icon fa-comment\">128</a></li>\n" +
                    "    </ul>\n" +
                    "    </footer>\n" +
                    "    </article>");
            }
        }
    });
});