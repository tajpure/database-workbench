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
	<!--<%@ include file="/WEB-INF/css/bootstrap.css"%>-->
</style>
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-switch.js"></script>
<script type="text/javascript" src="resources/js/workbench.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
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
			<c:if test="${not empty curSchema.name}">
			<div id="table-name">
				<h5 id="table-schema-name">${curSchema.name}</h5>
				<h5>>></h5> 
				<h5>properties</h5>
			</div>
			</c:if>
			<br>
			<div role="tabpanel">
  			<!-- Nav tabs -->
  			<ul class="nav nav-tabs" role="tablist">
   			<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Tables</a></li>
    		<li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Create Table</a></li>
    		<li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Scheme</a></li>
    		<li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Settings</a></li>
  			</ul>
			<!-- Tab panes -->
			  <div class="tab-content">
			    <div role="tabpanel" class="tab-pane active" id="home">
			    	<div id="table">
				<form name="valueForm" method=post>
				<table>
  				<thead>
   				 <tr>
					<c:if test="${not empty curSchema.name}">
      				<th>Table
      				<th>Table type
					<th><input class="table-btn" value="Define" type="button" onClick="define()">
					<th><input type="checkbox" class="table-btn common" id="mode-switch-schema" checked/>
					</c:if>
 				</thead>
  				<tbody>
    			<c:set var="i" scope="page" value="0"/>
				<c:forEach items="${curSchema.tables}" var="table">
    			<tr id="row_${i}" onclick="schema.select(${i});">
      				<td><input type="text" class="table-text"  value="${table.name}" name="newList[${i}]"/>
      				<input type="hidden" value="${table.name}" name="oldList[${i}]"/>
      				<td><input type="text" class="table-text"  value="${table.tableType}" disabled name="newList[${i}]"/>
      				<input type="hidden" value="${table.tableType}" name="oldList[${i}]"/>
					<c:set var="i" scope="page" value="${i+1}"/>
				</tr>
				<input type="hidden" value="{~_~}" name="newList[${i}]"/>
				<input type="hidden" value="{~_~}" name="oldList[${i}]"/>
				<c:set var="i" scope="page" value="${i+1}"/>
				</c:forEach>
  				</tbody>
  				</tbody>
				</table>
				</form>
			</div>
			    </div>
			    <div role="tabpanel" class="tab-pane" id="profile">Profile</div>
			    <div role="tabpanel" class="tab-pane" id="messages">...</div>
			    <div role="tabpanel" class="tab-pane" id="settings">...</div>
			  </div>
			</div>
			
			<script type="text/javascript">
			schema.init();
			</script>
	</div>
	<div id="footer">
	</div>
</body>
</html>