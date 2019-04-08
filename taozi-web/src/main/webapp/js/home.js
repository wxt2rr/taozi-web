;(function () {
    layui.use('element', function(){
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

        //监听导航点击
        element.on('nav(demo)', function(elem){
            layer.msg(elem.text());
        });
    });

    $.ajax({
        url : '/login/getLoginUser',
        dataType : 'json',
        type : 'POST',
        data : {
        },
        success : function(data){
            $user_info = $("#user_info");
            var code = data.body.code;
            var user = data.body.data;
            console.log(user);
            if(code == 200){
                //登录
                var nickname = user.nickname;
                var url = user.headUrl+"?v="+user.setHeadTime;
                var name = "";
                if(nickname.length > 3){
                    name = nickname.substring(0,3) + "...";
                }else{
                    name = nickname;
                }
                $user_info.html(name);
                $("#user-head").attr("src",url);
            }else{
                //未登录
                window.location.href="/html/login.html";
            }
        }
    });

    $("#login-out").click(function () {
        $.ajax({
            url : '/login/loginOut',
            dataType : 'json',
            type : 'POST',
            data : {
            },
            success : function(data){
                window.location.href="/html/login.html";
            }
        });
    });

    //获取博客列表
    $.ajax({
        url : '/diary/getDiaryList',
        dataType : 'json',
        type : 'POST',
        data : {
        },
        success : function(data){
            $list = $("#item-list-div");
            var code = data.body.code;
            if(code == 200){
                var list = data.body.data;
                for(var i=0;i<list.length;i++){
                    var info = list[i];
                    var content = list[i].content;
                    if(content.length > 100){
                        content = content.substring(0,99);
                        content = "";
                    }
                    var single = "<div class=\"item\" style=\"border-width:3px;border-bottom-style:dotted;border-color: silver;padding:5px\">\n" +
                        "          <div class=\"layui-fluid\">\n" +
                        "            <div class=\"layui-row\">\n" +
                        "              <div class=\"layui-col-xs12 layui-col-sm8 layui-col-md7\" style='width: 950px'>\n" +
                        "                <div class=\"item-cont\">\n" +
                        "                  <h3>"+info.title+"<button class=\"layui-btn layui-btn-danger new-icon\">new</button></h3>\n" +
                        "                  <h5>博文</h5>\n" +
                        "                  "+content+"\n" +
                        "                  <a href=\"details.html?id="+list[i].id+"\" class=\"go-icon\"></a>\n" +
                        "                </div>\n" +
                        "            </div>\n" +
                        "            </div>\n" +
                        "           </div>\n" +
                        "        </div>";
                    $list.append(single);
                }
            }
        }
    });
}());

