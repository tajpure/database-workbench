<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style type="text/css">
	<!--<%@ include file="/WEB-INF/mycss/flat-ui.css"%>-->
	<!--<%@ include file="/WEB-INF/mycss/functionPage.css"%>-->
	<!--<%@ include file="/WEB-INF/mycss/main.css"%>-->
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Workbench</title>
</head>
<body>
	<div id="content">
		<div id="left">
		</div>
		<div id="main">
			<div id="title">Error!</div>
			${errorInfo}
			<a href="login">Click here to return</a>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>