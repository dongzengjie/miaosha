$(function() {
	var dologinUrl = '/miaosha/login/do_login';

	$("#submit").click(
			function() {

				var formpassword = $("#password").val();
				var salt = g_passsword_salt;
				var str = "" + salt.charAt(0) + salt.charAt(2) + formpassword
						+ salt.charAt(5) + salt.charAt(4);
				var password = md5(str);

				$.ajax({
					url : dologinUrl,
					type : "POST",
					data : {
						mobile : $("#mobile").val(),
						password : password
					},
					success : function(data) {
						layer.closeAll();
						if (data.code == 0) {
							layer.msg("成功");
							window.location.href = "/miaosha/login/toindex";
						} else {
							layer.msg(data.msg);
						}
					},
					error : function() {
						layer.closeAll();
					}
				});
			});

});