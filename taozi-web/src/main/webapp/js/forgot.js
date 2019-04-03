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
                phone:phone,
                type:1
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

    //注册
    $('#forgot-from').on('submit',function(e){
        e.preventDefault();
        var phone = $("#phone").val();
        var password = $("#password").val();
        var v_code = $("#v-code").val();

        if(phone == ""){
            alert("手机号不能为空");
            return;
        }
        if(password == ""){
            alert("密码不能为空");
            return;
        }
        if(v_code == ""){
            alert("验证码不能为空");
            return;
        }

        $.ajax({
            url : '/register/forgot',
            dataType : 'json',
            type : 'POST',
            data : {
                phone:phone,
                password:password,
                v_code:v_code
            },
            success : function(data){
                var code = data.body.code;
                if(code == -1) {
                    alert("参数错误");
                }else if(code == -2){
                    alert("验证码已过期");
                }else if(code == 200){
                    alert("修改成功");
                    window.location.href="/html/login.html";
                }else if(code == -3){
                    alert("系统错误");
                }
            }
        });
    });
}());