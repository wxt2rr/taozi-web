$(function () {
    $("#name").change(function () {
        var name = $(this).val();
        var $span = $("#name_check_span");
        if(name == null || name.length < 4){
            $span.html("<font style='color:red'>名字最少4位</font>");
        }else{
            $span.html("<font style='color: #2ecc71'>好名字</font>");
        }
        $span.show();
    });
    $("#phone").change(function () {
        var name = $(this).val();
        var $span = $("#phone_check_span");
        if(name == null || name.length != 11){
            $span.html("<font style='color:red'>请输入有效手机号</font>");
        }else{
            $span.html("<font style='color: #2ecc71'>好手机号</font>");
        }
        $span.show();
    });
    $("#password").change(function () {
        var name = $(this).val();
        var $span = $("#pw_check_span");
        if(name == null || name.length < 6){
            $span.html("<font style='color:red'>密码最少6位</font>");
        }else{
            $span.html("<font style='color: #2ecc71'>好密码</font>");
        }
        $span.show();
    });
    $("#sure_password").change(function () {
        var name = $(this).val();
        var sure_pw = $("#password").val();
        var $span = $("#sure_password_span");
        if(name == null || name != sure_pw){
            $span.html("<font style='color:red'>两次密码不一致</font>");
        }else{
            $span.html("<font style='color: #2ecc71'>好记性</font>");
        }
        $span.show();
    });
    /*$("#email").change(function () {
        var name = $(this).val();
        var $span = $("#email_check_span");
        if(name == null || !name.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)){
            $span.html("<font style='color: #2ecc71'>yes</font>");
        }else{
            $span.html("<font style='color:red'>no</font>");
        }
        $span.show();
    });*/
    $("#send_v_code").click(function () {
        var phone = $("#phone").val();
        if(null == phone || phone.length != 11){
            var $span = $("#phone_check_span");
            if(name == null || name.length != 11){
                $span.html("<font style='color:red'>手机号有误</font>");
                $span.show();
                return;
            }else{
                $span.html("<font style='color: #2ecc71'>好手机号</font>");
                $span.show();
            }
        }
        $(this).attr("disabled","disabled")
        $.ajax({
            url: '/register/sendVCode.do',
            dataType: 'json',
            data: {
                phone:phone
            },
            success: function(data) {
                if(data.body.code == 200){
                    alert("发送成功，请注意查收！");
                }else{
                    alert("no,msg:" + data.body.code);
                }
            }
        });
    });
    
    $("#register").click(function () {
        var flag = true;
        var phone = $("#phone").val();
        debugger;
        if(null == phone || phone.length != 11){
            var $span = $("#phone_check_span");
            if(name == null || name.length != 11){
                $span.html("<font style='color:red'>no</font>");
                flag = false;
            }else{
                $span.html("<font style='color: #2ecc71'>yes</font>");
            }
            $span.show();
        }
        var name = $("#name").val();
        var $span1 = $("#name_check_span");
        if(name == null || name.length < 4){
            $span1.html("<font style='color:red'>no</font>");
            flag = false;
        }else{
            $span1.html("<font style='color: #2ecc71'>yes</font>");
        }
        $span1.show();
        var password = $("#password").val();
        var $span2 = $("#pw_check_span");
        if(password == null || password.length < 6){
            $span2.html("<font style='color:red'>no</font>");
            flag = false;
        }else{
            $span2.html("<font style='color: #2ecc71'>yes</font>");
        }
        $span2.show();
        var surePw = $("#sure_password").val();
        var $span3 = $("#sure_password_span");
        if(surePw == null || surePw != password){
            $span3.html("<font style='color:red'>no</font>");
            flag = false;
        }else{
            $span3.html("<font style='color: #2ecc71'>yes</font>");
        }
        $span3.show();
        var vCode = $("#v_code").val();
        var $span4 = $("#sure_v_code_span");
        if(vCode == null){
            $span4.html("<font style='color:red'>no</font>");
            flag = false;
        }else{
            $span4.html("<font style='color: #2ecc71'>yes</font>");
        }
        $span4.show();
        var profession = $("#profession").val();
        if(!flag){
            return;
        }
        $.ajax({
            url: '/register/register.do',
            dataType: 'json',
            data: {
                name:name,
                phone:phone,
                password:password,
                surePassword:surePw,
                profession:profession,
                vCode:vCode
            },
            success: function(data) {
                if(data.body.code == 200){
                    alert("注册成功");
                    window.location.href = "/jumpPage/html/login";
                }else{
                    alert("no,msg:" + data.body.code);
                }
            }
        });
    });
    
    $("#login").click(function () {
        window.location.href = "/jumpPage/html/login";
    });
});