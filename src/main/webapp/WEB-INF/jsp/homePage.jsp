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
		<c:forEach items="${schemas}" var="schema">
		<ul class="menu-arbol">
			<li>
				<input type="radio" name="nivel-1" class="mostrar-menu" id="${schema.name}">
				<label for="${schema.name}" class="ampliar"></label>
				<a href="">${schema.name}</a>
				<ul class="nivel-01">
					<c:forEach items="${schema.tables}" var="table">
						<li><a href="homePage?schemaName=${schema.name}&tableName=${table.name}">${table.name}</a></li>
					</c:forEach>
				</ul>
			</li>
		</ul>
		</c:forEach>
	</div>
	<div id="main">
			<table>
  				<thead>
   				 <tr>
					<c:forEach items="${table.columns}" var="column">
      				<th>${column.name}
					</c:forEach>
 				</thead>
  					<tbody>
					<c:forEach items="${values}" var="list">
    				<tr>
   					<c:forEach items="${list}" var="object">
      					<td>${object}
					</c:forEach>
					</c:forEach>
  					</tbody>
			</table>
	</div>
	<div id="footer"></div>
</body>
</html>