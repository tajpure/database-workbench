<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style type="text/css">
	<!--<%@ include file="/WEB-INF/mycss/homePage.css"%>-->
	<!--<%@ include file="/WEB-INF/mycss/main.css"%>-->
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DBMS</title>
</head>
<body>
	<div id="left">
		<ul class="menu-arbol">
			<li>
				<input type="radio" name="nivel-1" class="mostrar-menu" id="database">
				<label for="database" class="ampliar"></label>
				<a href="">database</a>
				<ul class="nivel-01">
				<c:forEach items="${schemas}" var="schema">
					<li>
						<input type="checkbox" class="mostrar-menu" id="${schema.name}">
						<label for="menu02" class="ampliar"></label>
						<a href="">${schema.name}</a>
						<ul class="nivel-02">
							<li><a href="">table</a></li>
							<li><a href="">table</a></li>
						</ul>
					</li>
				</c:forEach>
				</ul>
			</li>
		</ul>
	</div>
	<div id="main">
	</div>
	<div id="footer"></div>
</body>
</html>