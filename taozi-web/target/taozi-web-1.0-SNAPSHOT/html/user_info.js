$(function () {
    $.ajax({
        url: '/login/getLoginUser.do',
        dataType: 'json',
        data: {
        },
        success: function(data) {
            var $ul = $("#user_info");
            if(data.body.code == 200) {
                var user = data.body.data;
                $("#user_head_img").attr("src",user.headUrl+"?v="+user.setHeadTime);
                $("#nick").attr("value",user.nickname);
                $("#job").attr("value",user.profession);
                $("#sex_"+user.sex).attr("checked","checked");
                $("#aboutme").val(user.aboutme);
            }else{
                alert("not login");
            }
        }
    });

    $("#user_head_img").click(function () {
        $("#upload_head").show();
    });

    $("#btnUpload").click(function () {
        var a = $("#head").attr("src");
        $.ajax({
            url: '/user/uploadHead.do',
            dataType: 'json',
            data: {
                base64:a
            },
            type : 'POST',
            success: function(data) {
                if(data.body.code == 200){
                    alert("上传成功");
                    window.location.reload();
                }else{
                    alert("上传失败，" + data.body.code+"," + data.body.msg);
                }
            }
        });
    });
    
    $("#profile-submit").click(function () {
        var name = $("#nick").val();
        var job = $("#job").val();
        var sex = $('input[name="sex"]:checked').val();
        var aboutme = $("#aboutme").val();
        if(name == null || name.length < 4){
            alert("名字不能为空，并且不小于4位");
            return;
        }
        $.ajax({
            url: '/user/updateInfo.do',
            dataType: 'json',
            data: {
                name:name,
                job:job,
                sex:sex,
                aboutme:aboutme
            },
            type : 'POST',
            success: function(data) {
                if(data.body.code == 200){
                    alert("修改成功");
                    window.location.reload();
                }else{
                    alert("修改失败，" + data.body.code+"," + data.body.msg);
                }
            }
        });
    });
});