;(function () {
    layui.use(['form','element'], function(){
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

        //监听导航点击
        element.on('nav(demo)', function(elem){
            layer.msg(elem.text());
        });

        var form = layui.form;


    });

    $("#save").click(function () {
        var title = $("#title").val();
        var content = he.getHtml();

        if(title == ""){
            alert("请输入标题");
            return;
        }
        if(content == ""){
            alert("请输入内容");
            return;
        }
        $.ajax({
            url : '/diary/saveBlog',
            dataType : 'json',
            type : 'POST',
            data : {
                title:title,
                content:content
            },
            success : function(data){
                var code = data.body.code;
                if(code == 200){
                    alert("发表成功");
                    window.location.href="/html/write.html";
                }else{
                    alert("发表出错");
                }
            }
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

}());

