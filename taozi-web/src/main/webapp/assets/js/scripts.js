
jQuery(document).ready(function() {

    //window.location.href="/index.html";

    /*
        Fullscreen background
    */
    //$.backstretch("assets/img/backgrounds/1.jpg");

    //登录
	$('.launch-modal').on('click', function(e){
		$( '#' + $(this).data('modal-id') ).modal();
	});

	//注册
	$("#go-to-register").click(function (e) {
        $( '#' + $(this).data('modal-id') ).modal();
    });

    /*
        Form validation
    */
    /*$('.registration-form input[type="text"], .registration-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });*/


    $("#submit").click(function () {
        $(this).find('input[type="text"], textarea').each(function(){
            if( $(this).val() == "" ) {
                e.preventDefault();
                $(this).addClass('input-error');
            }
            else {
                $(this).removeClass('input-error');
            }
        });
    });

    $("#send-v-code").click(function () {
        alert();
		var phone = $("#register-phone").val();
		if(phone == ""){
			alert("请先填写手机号");
		}
        $.ajax({
            url : "/register/sendVCode",
            dataType : 'json',
            type : 'POST',
            data : {
                phone:phone
            },
            success : function(data){
				var code = data.code
				if(code == -103){
					alert("该手机已绑定");
				}else if(code = -105){
					alert("发送异常");
				}else{
					alert("发送成功，请注意查收");
				}
            }
        });
    });
});
