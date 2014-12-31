<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="resources/js/workbench.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Workbench</title>
</head>
<body>
	<div id="header">
		<a href="user">Account</a>
		<a href="logout">Logout</a>
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
   			<li role="presentation" class="active"><a id="tab0" href="#home" aria-controls="home" role="tab" data-toggle="tab">Tables</a></li>
    		<li role="presentation"><a id="tab1" href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Create Table</a></li>
    		<li role="presentation"><a id="tab2" href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Settings</a></li>
  			</ul>
			<!-- Tab panes -->
			  <div class="tab-content">
			    <div role="tabpanel" class="tab-pane active" id="home">
			    	<div id="table">
				<form name="TableForm" method=post>
				<table>
  				<thead>
   				 <tr>
					<c:if test="${not empty curSchema.name}">
      				<th>Table
      				<th>Table type
					<th><input class="table-btn" value="Define" type="button" onClick="define_table()">
					<th><input type="checkbox" class="table-btn common" id="mode-switch-table" checked/>
					</c:if>
 				</thead>
  				<tbody>
    			<c:set var="i" scope="page" value="0"/>
				<c:forEach items="${curSchema.tables}" var="table">
    			<tr id="table-row${i}" onclick="table.select(${i});">
      				<td><input type="text" class="table-text"  value="${table.name}" name="newTables[${i}].name"/>
      				<input type="hidden" value="${curSchema.name}" name="oldTables[${i}].itsSchema"/>
      				<input type="hidden" value="${table.name}" name="oldTables[${i}].name"/>
      				<td><input type="text" class="table-text"  value="${table.tableType}" disabled/>
      				<input type="hidden" value="${table.tableType}"/>
					<c:set var="i" scope="page" value="${i+1}"/>
				</tr>
				</c:forEach>
  				</tbody>
				</table>
				</form>
			</div>
			    </div>
			    <div role="tabpanel" class="tab-pane" id="profile">
			    	<form action="createTable" method="post">
						<table>
  						<thead>
	  						<tr>
		      				<th><a data-tooltip="Table Name">Name</a>
		      				<th><a data-tooltip="Alternative Storage Engines">Engine</a>
		      				<th><a data-tooltip="Table Remark">Remark</a>
 						</thead>
  						<tbody>
  							<tr>
  							<td><input type="text" class="table-text"  name="newTable.name"/>
  							<input type="hidden" value="${curSchema.name}" name="newTable.itsSchema"/>
  							<td><select name="newTable.storageEngine" id="select_${i}">
							<option value="MylSAM">MylSAM</option>
							<option value="InnoDB">InnoDB</option>
							<option value="MEMORY">MEMORY</option>
							<option value="MRG_MYISAM">MRG_MYISAM</option>
							</select>
  							<td><input type="text" class="table-text" name="newTable.remarks"/>
							<td><input class="table-btn" value="Save" type="submit">
							<td><input class="table-btn-add" value="Add Column" type="button" onclick="addTr()">
  						</tbody>
						</table>
						<table id="columnTable">
			  				<thead>
			   				 <tr>
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
				 			</thead>
				  			<tbody>
				  			<tr id="tr0">
			      				<td><input type="text" class="table-text"  value="" name="insertColumns[0].name"/>
			      				<td><select name="insertColumns.dataType">
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
								<td><input type="text" class="table-text"  value="" name="insertColumns[0].columnSize"/>
			      				<td><input type="checkbox" class="checkbox" name="insertColumns[0].PK"/>
			      				<td><input type="checkbox" class="checkbox" name="insertColumns[0].NN"/>
			      				<td><input type="checkbox" class="checkbox" name="insertColumns[0].UQ"/>
			      				<td><input type="checkbox" class="checkbox" name="insertColumns[0].BIN"/>
			      				<td><input type="checkbox" class="checkbox" name="insertColumns[0].UN"/>
			      				<td><input type="checkbox" class="checkbox" name="insertColumns[0].ZF"/>
			      				<td><input type="checkbox" class="checkbox" name="insertColumns[0].AI"/>
			      				<td><input type="text" class="table-text"  value="" name="insertColumns[0].columnDefault"/>
								<td><input class="column-btn-remove" value="Remove" type="button" onclick='removeTr(0)'>
				  			</tbody>
				  			<tr></tr>
						</table>
			    	</form>
				</div>
			    <div role="tabpanel" class="tab-pane" id="settings">
			    	Settings
			    </div>
			  </div>
			</div>
			
			<script type="text/javascript">
			switch (${curTab}) {
			case 0 : $("#tab0").trigger("click"); break;
			case 1 : $("#tab1").trigger("click"); break;
			case 2 : $("#tab2").trigger("click"); break;
			};
			table.init();
			table.showMenu();
			</script>
	</div>
	<div id="footer">
	</div>
</body>
</html>