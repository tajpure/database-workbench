<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	<!--<%@ include file="/WEB-INF/css/home.css"%>-->
	<!--<%@ include file="/WEB-INF/css/bootstrap-switch.min.css"%>-->
	<!--<%@ include file="/WEB-INF/css/bootstrap.min.css"%>-->
</style>
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-switch.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/workbench.js"></script>
<script type="text/javascript" src="resources/js/ace/ace.js"></script>
<script type="text/javascript" src="resources/js/ace/theme-twilight.js"></script>
<script type="text/javascript" src="resources/js/bootbox.min.js"></script>
<script src="resources/js/ace/mode-sql.js" type="text/javascript" charset="utf-8"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Workbench</title>
</head>
<body>
	<div id="header">
		<a href="home">Home</a>
		<a href="user">Account</a>
		<a href="logout">Logout</a>
	</div>
	<div id="left">
	</div>
	<div id="main">
			User name:<input type="text" class="form-control input-sm" value="${user.name}" name="newSchema.name"/><br><br>
			Driver:<input type="text" class="form-control input-sm" value="${user.driver}" name="newSchema.name"/><br><br>
			URL:<input type="text" class="form-control input-sm" value="${user.url}" name="newSchema.name"/><br><br>
	</div>
	<div id="footer">
	</div>
</body>
</html>