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
    var id = getPms("id");
    $.ajax({
        url : '/diary/getDiaryInfo',
        dataType : 'json',
        type : 'POST',
        data : {
            did:id
        },
        success : function(data){
            if(data.body.code == 200){
                var detail = data.body.data;
                console.log(detail);
                $("#h-title").html(detail.title);
                $("#content").html(detail.content);

                $("#authorInfo_name").html(detail.nickname);
                $("#authorInfo_avatar").attr("src",detail.head_url);
                //$("#cont").html(detail.content);
                $("#a_user_id").val(detail.user_id);
                var info = detail.isAttention == 1 ? "取消关注" : "关注";
                $("#follow-author").html(info);
                $("#follow-author").attr("type",detail.isAttention);
            }
        }
    });

    $("#follow-author").click(function () {
        var aUserId = $("#a_user_id").val();
        var type = $("#follow-author").attr("type");
        $.ajax({
            url: '/user/attentionUser.do',
            dataType: 'json',
            data: {
                a_user_id:aUserId,
                type:type
            },
            success: function(data) {
                if(data.body.code == 200) {
                    window.location.reload();
                }else{
                    alert("not login");
                }
            }
        });
    });
}());

function getPms(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURIComponent(r[2]);
    }
    return null;
}

