<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	<!--<%@ include file="/WEB-INF/css/homePage.css"%>-->
	<!--<%@ include file="/WEB-INF/css/bootstrap-switch.css"%>-->
	<!--<%@ include file="/WEB-INF/css/bootstrap.css"%>-->
</style>
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-switch.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/workbench.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Workbench</title>
</head>
<body>
	<div id="header">
		<a class="" href="user">Account</a>
		<a class="" href="logout">Logout</a>
	</div>
	<div id="left">
		<ul class="menu-arbol">
			<li>
			<input type="button" class="schema-new-btn" value="New Schema"/>
			</li>
		</ul>
		<c:forEach items="${schemas}" var="schema">
		<ul class="menu-arbol">
			<li>
				<input type="radio" name="nivel-1" class="mostrar-menu" id="${schema.name}">
				<label for="${schema.name}" class="ampliar"></label>
				<a>${schema.name}</a>
				<a class="schema-config-btn" type="button" href="schemaInfo?schemaName=${schema.name}">Config</a>
				<ul class="nivel-01">
					<li>
						<input type="checkbox" name="nivel-1" class="mostrar-menu" id="${schema.name}-tables">
						<label for="${schema.name}-tables" class="ampliar"></label>
						<a>Tables</a>
						<ul class="nivel-02">
						<li>
						<form action="schemaInfo" method="post">
						<input type="hidden" value="${schema.name}" name="schemaName">
						<input type="hidden" value="1" name="curTab">
						<input type="submit" class="table-new-btn" value="New Table"/>
						</form>
						</li>
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
						<a>Views</a>
						<ul class="nivel-02">
						<li>
						<input type="button" class="table-new-btn" value="New View"/>
						</li>
						</ul>
					</li>
					<li>
						<input type="checkbox" name="nivel-1" class="mostrar-menu" id="${schema.name}-sps">
						<label for="${schema.name}-sps" class="ampliar"></label>
						<a>Stored Procedures</a>
						<ul class="nivel-02">
						<li>
						<input type="button" class="table-new-btn" value="New Stored Procedure"/>
						</li>
						</ul>
					</li>
					<li>
						<input type="checkbox" name="nivel-1" class="mostrar-menu" id="${schema.name}-functions">
						<label for="${schema.name}-functions" class="ampliar"></label>
						<a>Functions</a>
						<ul class="nivel-02">
						<li>
						<input type="button" class="table-new-btn" value="New Function"/>
						</li>
						</ul>
					</li>
				</ul>
			</li>
		</ul>
		</c:forEach>
	</div>
	<div id="main">
			<c:choose>
			<c:when test="${not empty curTable.itsSchema}">
			<div id="table-name"><h5 id="table-schema-name">${curTable.itsSchema}</h5> <h5>>></h5> <h5>${curTable.name}</h5></div>
			<br>
			<div calss="tabpanel" role="tabpanel">
  			<!-- Nav tabs -->
  			<ul class="nav nav-tabs" role="tablist">
   			<li role="presentation" class="active"><a id="tab0" href="#home" aria-controls="home" role="tab" data-toggle="tab">Values</a></li>
    		<li role="presentation"><a id="tab1" href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Columns</a></li>
    		<li role="presentation"><a id="tab2" href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Settings</a></li>
  			</ul>
			<!-- Tab panes -->
			  <div class="tab-content">
			    <div role="tabpanel" class="tab-pane active" id="home">
			<div id="table">
				<form name="ValueForm" method=post>
				<table>
  				<thead>
   				 <tr>
					<c:forEach items="${curTable.columns}" var="column">
      				<th>${column.name}
					</c:forEach>
					<c:if test="${fn:length(curTable.columns) gt 0}">
					<th><input class="table-btn" value="Define" type="button" onClick="define_value()">
					<th><input type="checkbox" class="table-btn common" id="mode-switch-value" checked/>
					</c:if>
 				</thead>
  				<tbody>
				<c:set var="i" scope="page" value="0"/>
				<c:set var="j" scope="page" value="0"/>
				<c:forEach items="${values}" var="list">
    			<tr id="value-row${j}" onclick="value.select(${j});">
   				<c:forEach items="${list}" var="object">
      				<td><input type="text" class="table-text"  value="${object}" name="newList[${i}]"/>
      				<input type="hidden" value="${object}" name="oldList[${i}]"/>
					<c:set var="i" scope="page" value="${i+1}"/>
				</c:forEach>
				<c:set var="j" scope="page" value="${j+1}"/>
				</tr>
				<input type="hidden" value="{~_~}" name="newList[${i}]"/>
				<input type="hidden" value="{~_~}" name="oldList[${i}]"/>
				<c:set var="i" scope="page" value="${i+1}"/>
				</c:forEach>
    			<tr>
					<c:set var="i" scope="page" value="0"/>
					<c:forEach items="${curTable.columns}" var="column">
      				<td><input type="text" class="table-text" name="insertObj[${i}]"/>
					<c:set var="i" scope="page" value="${i+1}"/>
					</c:forEach>
					<c:if test="${fn:length(curTable.columns) > 0}">
					<td><input class="table-btn" value="Insert" type="button" onClick="insert_value()">
					</c:if>
  				</tbody>
				</table>
				<input name="curTable.name" value="${curTable.name}" type="hidden"/>
				<input name="curTable.itsSchema" value="${curTable.itsSchema}" type="hidden"/>
				<input name="page" value="${page}" type="hidden"/>
				</form>
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
				<c:if test="${page <= 5}">
				<c:forEach var="i" begin="0" end="9">
   				<c:choose>
				<c:when test="${i+1 <= totalPages && i != page-1}">
					<h5><a href="homePage?curTable.itsSchema=${curTable.itsSchema}&curTable.name=${curTable.name}&page=${i+1}">${i+1}</a></h5>
				</c:when>
				<c:otherwise><h5>${i+1}</h5></c:otherwise>
				</c:choose>
				</c:forEach>
				</c:if>
				<c:if test="${page > 5}">
				<c:forEach var="i" begin="0" end="9">
   				<c:choose>
				<c:when test="${page-5+i <= totalPages && i != 5}">
					<h5><a href="homePage?curTable.itsSchema=${curTable.itsSchema}&curTable.name=${curTable.name}&page=${page-5+i}">${page-5+i}</a></h5>
				</c:when>
				<c:otherwise><h5>${page-5+i}</h5></c:otherwise>
				</c:choose>
				</c:forEach>
				</c:if>
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
			    <div role="tabpanel" class="tab-pane" id="profile">
			<div id="table">
				<form name="ColumnForm" method=post>
				<table>
  				<thead>
   				 <tr>
					<c:if test="${fn:length(columns) gt 0}">
      				<th>Column
      				<th>Data type
      				<th>Size
      				<th><a data-tooltip="Belongs to primary key">PK</a>
      				<th><a data-tooltip="Not Null">NN</a>
      				<th><a data-tooltip="Unique Index">UQ</a>
      				<th><a data-tooltip="Is binary column">BIN</a>
      				<th><a data-tooltip="Insigned data type">UN</a>
      				<th><a data-tooltip="Fill up values for that column with 0's if it is numeric">ZF</a>
      				<th><a data-tooltip="Auto Incremental">AI</a>
      				<th>Remark
					<th><input class="table-btn" value="Define" type="button" onClick="define_column()">
					<th><input type="checkbox" class="table-btn common" id="mode-switch-column" checked/>
					</c:if>
 				</thead>
  				<tbody>
    			<c:set var="i" scope="page" value="0"/>
				<c:forEach items="${columns}" var="column">
    			<tr id="column-row${i}" onclick="column.select(${i});">
      				<td><input type="text" class="table-text"  value="${column.name}" name="newColumns[${i}].name"/>
      				<input type="hidden" value="${column.name}" name="oldColumns[${i}].name"/>
      				<td><select name="newColumns[${i}].dataType" id="select_${i}">
							<option value=12>VARCHAR</option>
							<option value=5>SMALLINT</option>
							<option value=4>INTEGER</option>
							<option value=-5>BIGINT</option>
							<option value=6>FLOAT</option>
							<option value=7>REAL</option>
							<option value=8>DOUBLE</option>
							<option value=2>NUMERIC</option>
							<option value=3>DECIMAL</option>
							<option value=1>CHAR</option>
							<option value=-1>LONGVARCHAR</option>
							<option value=92>DATE</option>
							<option value=92>TIME</option>
							<option value=93>TIMESTAMP</option>
							<option value=-2>BINARY</option>
							<option value=-3>VARBINARY</option>
							<option value=-4>LONGVARBINARY</option>
							<option value=0>NULL</option>
							<option value=1111>OTHER</option>
							<option value=2000>JAVA_OBJECT</option>
							<option value=2001>DISTINCT</option>
							<option value=2002>STRUCT</option>
							<option value=2003>ARRAY</option>
							<option value=2004>BLOB</option>
							<option value=2005>CLOB</option>
							<option value=2006>REF</option>
							<option value=70>DATALINK</option>
							<option value=16>BOOLEAN</option>
							<option value=-8>ROWID</option>
							<option value=-15>NCHAR</option>
							<option value=-9>NVARCHAR</option>
							<option value=-16>LONGNVARCHAR</option>
							<option value=2011>NCLOB</option>
							<option value=2009>SQLXML</option>
						</select>
						<script type="text/javascript">
							$("#select_" + ${i}).val(${column.dataType});
						</script>
      				<td><input type="text" class="table-text"  value="${column.columnSize}" name="newColumns[${i}].columnSize"/>
      				<td><input id="PK${i}" type="checkbox" class="checkbox" name="newColumns[${i}].PK" value="0" onchange="setCheck('PK${i}')"/>
						<script type="text/javascript">
						if (${column.PK}) {
							$("#PK" + ${i}).prop('checked', true);
						}
						</script>
      				<td><input id="NN${i}" type="checkbox" class="checkbox" name="newColumns[${i}].NN"/>
						<script type="text/javascript">
						if (${column.NN}) {
							$("#NN" + ${i}).prop('checked', true);
						}
						</script>
      				<td><input id="UQ${i}" type="checkbox" class="checkbox" name="newColumns[${i}].UQ"/>
						<script type="text/javascript">
						if (${column.UQ}) {
							$("#UQ" + ${i}).prop('checked', true);
						}
						</script>
      				<td><input id="BIN${i}" type="checkbox" class="checkbox" name="newColumns[${i}].BIN"/>
						<script type="text/javascript">
						if (${column.BIN}) {
							$("#BIN" + ${i}).prop('checked', true);
						}
						</script>
      				<td><input id="UN${i}" type="checkbox" class="checkbox" name="newColumns[${i}].UN"/>
						<script type="text/javascript">
						if (${column.UN}) {
							$("#UN" + ${i}).prop('checked', true);
						}
						</script>
      				<td><input id="ZF${i}" type="checkbox" class="checkbox" name="newColumns[${i}].ZF"/>
						<script type="text/javascript">
						if (${column.ZF}) {
							$("#ZF" + ${i}).prop('checked', true);
						}
						</script>
      				<td><input id="AI${i}" type="checkbox" class="checkbox" name="newColumns[${i}].AI"/>
						<script type="text/javascript">
						if (${column.AI}) {
							$("#AI" + ${i}).prop('checked', true);
						}
						</script>
      				<td><input id="default${i}" type="text" class="table-text"  value="${column.columnDefault}" name="newColumns[${i}].columnDefault"/>
				</tr>
				
				<c:set var="i" scope="page" value="${i+1}"/>
				</c:forEach>
    			<tr>
      				<td><input type="text" class="table-text"  value="" name="insertColumn.name"/>
      				<td><select name="insertColumn.dataType">
							<option value=12>VARCHAR</option>
							<option value=5>SMALLINT</option>
							<option value=4>INTEGER</option>
							<option value=-5>BIGINT</option>
							<option value=6>FLOAT</option>
							<option value=7>REAL</option>
							<option value=8>DOUBLE</option>
							<option value=2>NUMERIC</option>
							<option value=3>DECIMAL</option>
							<option value=1>CHAR</option>
							<option value=-1>LONGVARCHAR</option>
							<option value=92>DATE</option>
							<option value=92>TIME</option>
							<option value=93>TIMESTAMP</option>
							<option value=-2>BINARY</option>
							<option value=-3>VARBINARY</option>
							<option value=-4>LONGVARBINARY</option>
							<option value=0>NULL</option>
							<option value=1111>OTHER</option>
							<option value=2000>JAVA_OBJECT</option>
							<option value=2001>DISTINCT</option>
							<option value=2002>STRUCT</option>
							<option value=2003>ARRAY</option>
							<option value=2004>BLOB</option>
							<option value=2005>CLOB</option>
							<option value=2006>REF</option>
							<option value=70>DATALINK</option>
							<option value=16>BOOLEAN</option>
							<option value=-8>ROWID</option>
							<option value=-15>NCHAR</option>
							<option value=-9>NVARCHAR</option>
							<option value=-16>LONGNVARCHAR</option>
							<option value=2011>NCLOB</option>
							<option value=2009>SQLXML</option>
						</select>
					<td><input type="text" class="table-text"  value="" name="insertColumn.columnSize"/>
      				<td><input type="checkbox" class="checkbox" name="insertColumn.PK"/>
      				<td><input type="checkbox" class="checkbox" name="insertColumn.NN"/>
      				<td><input type="checkbox" class="checkbox" name="insertColumn.UQ"/>
      				<td><input type="checkbox" class="checkbox" name="insertColumn.BIN"/>
      				<td><input type="checkbox" class="checkbox" name="insertColumn.UN"/>
      				<td><input type="checkbox" class="checkbox" name="insertColumn.ZF"/>
      				<td><input type="checkbox" class="checkbox" name="insertColumn.AI"/>
      				<td><input type="text" class="table-text"  value="" name="insertColumn.columnDefault"/>
					<c:if test="${fn:length(columns) > 0}">
					<td><input class="table-btn" value="Add" type="button" onClick="column.insertColumn()">
					</c:if>
  				</tbody>
  				</tbody>
				</table>
				<input name="curTable.name" value="${curTable.name}" type="hidden"/>
				<input name="curTable.itsSchema" value="${curTable.itsSchema}" type="hidden"/>
				</form>
			</div>
			    </div>
			    <div role="tabpanel" class="tab-pane" id="settings">
					The settings of this table.
				</div>
			  </div>
			</div>
			</c:when>
			</c:choose>
		<script type="text/javascript">
			switch (${curTab}) {
			case 0 : $("#tab0").trigger("click"); break;
			case 1 : $("#tab1").trigger("click"); break;
			case 2 : $("#tab2").trigger("click"); break;
			}
			column.init();
			value.init();
			value.showMenu();
			function setCheck(item) {
				var checkbox = item;
				if ($(checkbox).is(':checked')) {
					$(checkbox).prop('checked', false);
					$(checkbox).val("0");
				} else {
					$(checkbox).prop('checked', true);
					$(checkbox).val("1");
				}
				console.log(checkbox + ' ' + $(checkbox));
			}
		</script>
	</div>
	<div id="footer">
	</div>
</body>
</html>