<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style type="text/css">
	<!--<%@ include file="/WEB-INF/css/homePage.css"%>-->
	<!--<%@ include file="/WEB-INF/css/main.css"%>-->
	<!--<%@ include file="/WEB-INF/css/bootstrap-switch.css"%>-->
</style>
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-switch.js"></script>
<script type="text/javascript" src="resources/js/table.js"></script>
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
				<a class="schema-config-btn" type="button" href="schemaInfo?schemaName=${schema.name}">Config</a>
				<ul class="nivel-01">
					<li>
						<input type="checkbox" name="nivel-1" class="mostrar-menu" id="${schema.name}-tables">
						<label for="${schema.name}-tables" class="ampliar"></label>
						<a href="">Tables</a>
						<ul class="nivel-02">
						<c:forEach items="${schema.tables}" var="table">
						<li>
							<a href="homePage?curTable.itsSchema=${schema.name}&curTable.name=${table.name}">${table.name}</a>
							<a class="table-config-btn" type="button" href="tableInfo?curTable.itsSchema=${schema.name}&curTable.name=${table.name}">Config</a>
						</li>
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
			<c:if test="${not empty curTable.itsSchema}">
			<div id="table-name">
				<h5 id="table-schema-name">${curTable.itsSchema}</h5> 
				<h5>>></h5> 
				<h5>${curTable.name}</h5>
				<h5>>></h5> 
				<h5>property</h5>
			</div>
			</c:if>
			<div id="table">
				<form name="valueForm" method=post>
				<table>
  				<thead>
   				 <tr>
					<c:if test="${fn:length(columns) gt 0}">
      				<th>Column
      				<th>Data type
      				<th>Size
					<th><input class="table-btn" value="Define" type="button" onClick="define()">
					<th><input type="checkbox" class="table-btn common" id="mode-switch" checked/>
					</c:if>
 				</thead>
  				<tbody>
    			<c:set var="i" scope="page" value="0"/>
				<c:forEach items="${columns}" var="column">
    			<tr id="row_${i}" onclick="select(${i});">
      				<td><input type="text" class="table-text"  value="${column.name}" name="newList[${i}]"/>
      				<input type="hidden" value="${column.name}" name="oldList[${i}]"/>
      				<td><input type="text" class="table-text"  value="${column.dataType}" name="newList[${i}]"/>
      				<input type="hidden" value="${column.dataType}" name="oldList[${i}]"/>
      				<td><input type="text" class="table-text"  value="${column.columnSize}" name="newList[${i}]"/>
      				<input type="hidden" value="${column.columnSize}" name="oldList[${i}]"/>
					<c:set var="i" scope="page" value="${i+1}"/>
				</tr>
				<input type="hidden" value="{~_~}" name="newList[${i}]"/>
				<input type="hidden" value="{~_~}" name="oldList[${i}]"/>
				<c:set var="i" scope="page" value="${i+1}"/>
				</c:forEach>
    			<tr>
					<c:forEach var="i" begin="0" end="2">
      				<td><input type="text" class="table-text"  value="" name="insertObj[${i}]"/>
					<c:set var="i" scope="page" value="${i+1}"/>
					</c:forEach>
					<c:if test="${fn:length(columns) > 0}">
					<td><input class="table-btn" value="Insert" type="button" onClick="insertValue()">
					</c:if>
  				</tbody>
  				</tbody>
				</table>
				<input name="curTable.name" value="${curTable.name}" type="hidden"/>
				<input name="curTable.itsSchema" value="${curTable.itsSchema}" type="hidden"/>
				</form>
			</div>
			<script type="text/javascript">
				init();
			</script>
	</div>
	<div id="footer">
	</div>
</body>
</html>