<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style type="text/css">
	<!--<%@ include file="/WEB-INF/mycss/flat-ui.css"%>-->
	<!--<%@ include file="/WEB-INF/mycss/main.css"%>-->
	<!--<%@ include file="/WEB-INF/mycss/login-style.css"%>-->
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DBMS</title>
</head>
<body>

	<div id="main">
		<div id="login_card">
			<div id="login">登录DBMS</div>
		<div id="loginform">
			<form action="checklogin" method="post">
				<br>
				<div id="row">
					<div id="text" class="">
						账号
					</div>
					<div id="text">
						<input id="id" class="form-control input-sm" name="username" />
					</div>
				</div>
				<br>
				<div id="row">
					<div id="text">
						密码
					</div>
					<div id="text">
						<input id="psw" type="password" class="form-control input-sm"
							name="password" />
					</div>
				</div>
				<br>
				<input id="log" class="btn btn-embossed btn-primary" type="submit"
					value="登录" style="cursor:pointer;"/>
			</form>
		</div>
		<div id="register_card" class="">
		
		</div>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>