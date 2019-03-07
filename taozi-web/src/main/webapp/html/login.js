$(function () {
    $("#submit").click(function () {
        var phone = $("#phone").val();
        if(phone == null){
            alert("请输入手机号");
        }
        var password = $("#password").val();
        if(password == null){
            alert("请输入密码");
        }
        var isRember = $("input[name='rember_me']:checked").val();
        $.ajax({
            url: '/login/login.do',
            dataType: 'json',
            data: {
                account:phone,
                password:password,
                is_rember:isRember
            },
            success: function(data) {
                if(data.body.code == 200){
                    window.location.href = "/index.html";
                }else{
                    alert("no,msg:" + data.body.code);
                }
            }
        });
    });

    $("#register").click(function () {
        window.location.href = "/jumpPage/html/register";
    });
});