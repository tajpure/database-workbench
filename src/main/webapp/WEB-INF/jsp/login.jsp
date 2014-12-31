<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style type="text/css">
	<!--<%@ include file="/WEB-INF/css/bootstrap.min.css"%>-->
	<!--<%@ include file="/WEB-INF/css/login.css"%>-->
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Workbench</title>
</head>
<body>

	<div id="main">
		<div id="login_card">
			<div id="login">Welcome to Database Workbench</div>
		<div id="loginform">
			<form action="checklogin" method="post">
				<br>
				<div id="row">
					<div id="text">
						Username
					</div>
					<div id="text">
						<input id="id" class="form-control input-sm" name="username" />
					</div>
				</div>
				<br>
				<div id="row">
					<div id="text">
						Password
					</div>
					<div id="text">
						<input id="psw" type="password" class="form-control input-sm"
							name="password" />
					</div>
				</div>
				<br>
				<div id="row">
					<div id="text">
						Type
					</div>
					<div id="selector">
						<select name="typeOfDB">
							<option value=0>MySQL</option>
							<option value=1>SQLServer</option>
							<option value=2>Oracle</option>
						</select>
					</div>
				</div>
				<br>
				<input id="log" class="btn btn-embossed btn-primary" type="submit"
					value="login" style="cursor:pointer;"/>
			</form>
		</div>
		<div id="register_card" class="">
		
		</div>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>