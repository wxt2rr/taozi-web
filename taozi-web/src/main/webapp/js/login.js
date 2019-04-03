;(function () {
    // Placeholder
    var placeholderFunction = function() {
        $('input, textarea').placeholder({ customClass: 'my-placeholder' });
    }
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
}());

$('#login-from').on('submit',function(e){
    e.preventDefault();
    var phone = $("#phone").val();
    var password = $("#password").val();
    var rememberMe = $("#remember").prop("checked");
    $.ajax({
        url : '/login/login',
        dataType : 'json',
        type : 'POST',
        data : {
            account:phone,
            password:password,
            is_remember:rememberMe
        },
        success : function(data){
            var code = data.body.code;
            if(code == -1){
                alert("用户名或密码错误");
            }else if(code == 200){
                window.location.href="/html/home.html";
            }
        }
    });
});
