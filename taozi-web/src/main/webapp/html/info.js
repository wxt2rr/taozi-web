$(function () {
    $.ajax({
        url: '/diary/getDiaryInfo.do',
        dataType: 'json',
        data: {
            did:getPms("did")
        },
        success: function(data) {
            if(data.body.code == 200) {
                var user = data.body.data;
                $("#authorInfo_name").html(user.nickname);
                $("#authorInfo_avatar").attr("src",user.head_url);
                $("#cont").html(user.content);
                $("#a_user_id").val(user.user_id);
                var info = user.isAttention == 1 ? "取消关注" : "关注";
                $("#follow-author").html(info);
                $("#follow-author").attr("type",user.isAttention);
            }else{
                alert("not login");
            }
        }
    });

    function getPms(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return decodeURIComponent(r[2]);
        }
        return null;
    }

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
});