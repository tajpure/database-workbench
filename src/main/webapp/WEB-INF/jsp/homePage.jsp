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
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/workbench.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Workbench</title>
</head>
<body>
	<div id="header">
		<a class="" href="user">Account</a>
		<a class="" href="logout">Logout</a>
	</div>
	<div id="left">
		<c:forEach items="${schemas}" var="schema">
		<ul class="menu-arbol">
			<li>
				<input type="radio" name="nivel-1" class="mostrar-menu" id="${schema.name}">
				<label for="${schema.name}" class="ampliar"></label>
				<a href="">${schema.name}</a>
				<ul class="nivel-01">
					<li>
						<input type="checkbox" name="nivel-1" class="mostrar-menu" id="${schema.name}-tables">
						<label for="${schema.name}-tables" class="ampliar"></label>
						<a href="">Tables</a>
						<ul class="nivel-02">
						<c:forEach items="${schema.tables}" var="table">
						<li><a href="homePage?curTable.itsSchema=${schema.name}&curTable.name=${table.name}">${table.name}</a></li>
						</c:forEach>
						</ul>
					</li>
					<li>
						<input type="checkbox" name="nivel-1" class="mostrar-menu" id="${schema.name}-views">
						<label for="${schema.name}-views" class="ampliar"></label>
						<a href="">Views</a>
						<ul class="nivel-02">
						</ul>
					</li>
					<li>
						<input type="checkbox" name="nivel-1" class="mostrar-menu" id="${schema.name}-sps">
						<label for="${schema.name}-sps" class="ampliar"></label>
						<a href="">Stored Procedures</a>
						<ul class="nivel-02">
						</ul>
					</li>
					<li>
						<input type="checkbox" name="nivel-1" class="mostrar-menu" id="${schema.name}-functions">
						<label for="${schema.name}-functions" class="ampliar"></label>
						<a href="">Functions</a>
						<ul class="nivel-02">
						</ul>
					</li>
				</ul>
			</li>
		</ul>
		</c:forEach>
	</div>
	<div id="main">
			<div id="table">
				<table>
  				<thead>
   				 <tr>
					<c:forEach items="${curTable.columns}" var="column">
      				<th>${column.name}
					</c:forEach>
					<c:choose>
					<c:when test="${fn:length(curTable.columns) gt 0}">
					<th><input class="table-btn" value="Insert" type="submit">
					</c:when>
					</c:choose>
 				</thead>
  				<tbody>
				<c:forEach items="${values}" var="list">
    			<tr>
   				<c:forEach items="${list}" var="object">
      				<td><input type="text" class="table-text"  value="${object}"/>
				</c:forEach>
				<td><input class="table-btn" value="Save" type="submit">
				<td><input class="table-btn" value="Delete" type="submit">
				</c:forEach>
  				</tbody>
				</table>
			</div>
			<div id="page">
			<c:choose>
			<c:when test="${totalPages gt 1}">
			<c:choose>
				<c:when test="${page gt 1}">
					<h5><a href="homePage?curTable.itsSchema=${curTable.itsSchema}&curTable.name=${curTable.name}&page=${page-1}">Previous</a></h5>
				</c:when>
				<c:otherwise><h5>Previous</h5></c:otherwise>
				</c:choose>
				<h5>${page}</h5>
				<c:choose>
				<c:when test="${page+1 lt totalPages}">
					<h5><a href="homePage?curTable.itsSchema=${curTable.itsSchema}&curTable.name=${curTable.name}&page=${page+1}">${page+1}</a></h5>
				</c:when>
				<c:otherwise><h5>${page+1}</h5></c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${page+2 lt totalPages}">
					<h5><a href="homePage?curTable.itsSchema=${curTable.itsSchema}&curTable.name=${curTable.name}&page=${page+2}">${page+2}</a></h5>
				</c:when>
				<c:otherwise><h5>${page+2}</h5></c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${page+3 lt totalPages}">
					<h5><a href="homePage?curTable.itsSchema=${curTable.itsSchema}&curTable.name=${curTable.name}&page=${page+3}">${page+3}</a></h5>
				</c:when>
				<c:otherwise><h5>${page+3}</h5></c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${page+4 lt totalPages}">
					<h5><a href="homePage?curTable.itsSchema=${curTable.itsSchema}&curTable.name=${curTable.name}&page=${page+4}">${page+4}</a></h5>
				</c:when>
				<c:otherwise><h5>${page+4}</h5></c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${page lt totalPages}">
					<h5><a href="homePage?curTable.itsSchema=${curTable.itsSchema}&curTable.name=${curTable.name}&page=${page+1}">Next</a></h5>
				</c:when>
				<c:otherwise><h5>Next</h5></c:otherwise>
			</c:choose>
			</c:when>
			</c:choose>
			</div>
	</div>
	<div id="footer"></div>
</body>
</html>