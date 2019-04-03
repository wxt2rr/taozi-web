;(function () {
    // Placeholder
    var placeholderFunction = function() {
        $('input, textarea').placeholder({ customClass: 'my-placeholder' });
    };
    // Placeholder
    var contentWayPoint = function() {
        var i = 0;
        $('.animate-box').waypoint( function( direction ) {
            if( direction === 'down' && !$(this.element).hasClass('animated-fast') ) {
                i++;
                $(this.element).addClass('item-animate');
                setTimeout(function(){
                    $('body .animate-box.item-animate').each(function(k){
                        var el = $(this);
                        setTimeout( function () {
                            var effect = el.data('animate-effect');
                            if ( effect === 'fadeIn') {
                                el.addClass('fadeIn animated-fast');
                            } else if ( effect === 'fadeInLeft') {
                                el.addClass('fadeInLeft animated-fast');
                            } else if ( effect === 'fadeInRight') {
                                el.addClass('fadeInRight animated-fast');
                            } else {
                                el.addClass('fadeInUp animated-fast');
                            }
                            el.removeClass('item-animate');
                        },  k * 200, 'easeInOutExpo' );
                    });
                }, 100);
            }
        } , { offset: '85%' } );
    };
    // On load
    $(function(){
        placeholderFunction();
        contentWayPoint();
    });

    $("#send-v-code").click(function () {
        var phone = $("#phone").val();
        if(phone == ""){
            alert("请输入手机号");
            return;
        }
        $.ajax({
            url : '/register/sendVCode',
            dataType : 'json',
            type : 'POST',
            data : {
                phone:phone
            },
            success : function(data){
                var code = data.body.code;
                if(code == -103){
                    alert("手机号已绑定");
                }else if(code == 200){
                    alert("验证码已发送，请注意查收");
                }else if(code == -105){
                    alert("系统错误");
                }
            }
        });
    });

    //修改密码
    $('#register-from').on('submit',function(e){
        e.preventDefault();
        var name = $("#name").val();
        var phone = $("#phone").val();
        var password = $("#password").val();
        var re_password = $("#re-password").val();
        var v_code = $("#v-code").val();

        if(name == ""){
            alert("用户名不能为空");
            return;
        }
        if(phone == ""){
            alert("手机号不能为空");
            return;
        }
        if(password == ""){
            alert("密码不能为空");
            return;
        }
        if(password != re_password){
            alert("两次密码不一致");
            return;
        }
        if(v_code == ""){
            alert("验证码不能为空");
            return;
        }

        $.ajax({
            url : '/register/register',
            dataType : 'json',
            type : 'POST',
            data : {
                name:name,
                phone:phone,
                password:password,
                re_password:re_password,
                v_code:v_code
            },
            success : function(data){
                var code = data.body.code;
                if(code == -2) {
                    alert("验证码已过期");
                }else if(code == -3){
                    alert("两次密码不一致");
                }else if(code == 200){
                    alert("注册成功");
                    window.location.href="/html/login.html";
                }else if(code == -4){
                    alert("系统错误");
                }
            }
        });
    });
}());
