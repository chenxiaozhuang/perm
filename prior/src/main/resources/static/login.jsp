<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#loginBtn").click(function(){
			var username = $("#user_name").val();
			var password = $("#pass_word").val();
			if(username == null || username == ''){
				alert("用户名不能为空！");
				return;
			}
			if(password == null || password == ''){
				alert("用户密码不能为空！");
				return;
			}
			//通过ajax提交
			$.post(	
				"login.do",
				{"username":username,"password":password},
				function(data){
					if(data.success){
						alert(data.msg);
						//跳转到主页面
						document.location.href="toIndex.do";
					}
				},
				"json"
			);
			
		});
	})

</script>
</head>
<body>
	<form action="login.do" name="loginForm" method="post">
		用户名称：<input type="text" name="username" value="" id="user_name"/><br>
		用户密码：<input type="password" name="password" value="" id="pass_word"/><br>
		<input type="button" value="登录" name="sub" id="loginBtn"/>
		<input type="reset" value="重置" name="rest" id="rest"/>
	</form>
</body>
</html>