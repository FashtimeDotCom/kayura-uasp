<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页</title>
</head>
<body>
	<div id="login-error">${error}</div>
	<form action="${siteUrl}/j_spring_security_check" method="post">
		用户名：<input name="j_username" type="text" /><br> 密码：<input
			name="j_password" type="password" /><br> <input type="submit"
			value="登录" /> <input type="reset" value="重置">
	</form>
</body>
</html>