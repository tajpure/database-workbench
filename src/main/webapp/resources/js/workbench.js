/**
 * Some database workbench functions.
 * author:tajpure
 */

var eidtor;

function initEditor() {
	editor = ace.edit("editor");
	editor.setTheme("ace/theme/twilight");
	var JavaScriptMode = require("ace/mode/sql").Mode;
	editor.getSession().setMode(new JavaScriptMode());
}

function define_value() {
	value.define();
};

function define_column() {
	column.defineColumn();
};

function define_table() {
	table.defineTable();
};

function insert_value() {
	value.insertValue();
};

function delete_value() {
	value.deleteValue();
};

function update_value() {
	value.updateValue();
};

function Workbench(name, switcher, row) {
		this.name = name;
		this.selectedItems = [];
		this.indexOfItems = 0;
		this.switcher = switcher;
		this.row = row;
		this.form = '[name=' + name + 'Form' + ']';
		this.init = function() {
			this.initSwitch();
			$('#myTab a').click(function (e) {
				  e.preventDefault()
				  $(this).tab('show')
				});
		};
		this.initSwitch = function() {
			$.fn.bootstrapSwitch.defaults.size = "mini";
			$.fn.bootstrapSwitch.defaults.offText = "Drop";
			$.fn.bootstrapSwitch.defaults.onText = "Update";
			$(this.switcher).bootstrapSwitch('state', true, true);
			$(this.switcher).on('switchChange.bootstrapSwitch', function () {
				console.log("Switch mode changed");
			});
		};
		this.showMenu = function() {
			var schema = $("#table-schema-name").text();
			var schemaId = '#' + schema;
			var tableId = '#' + schema + '-tables';
			$(schemaId.toString()).trigger("click");
			$(tableId.toString()).trigger("click");
		};
		this.updateValue = function() {
			ValueForm.action="updateValue";
			ValueForm.submit(); 
		};
		this.deleteValue = function() {
			ValueForm.action="deleteValue?" + this.getSelectedItems();
			ValueForm.submit();
		};
		this.insertColumn = function() {
			ColumnForm.action = "insertColumn";
			ColumnForm.submit();
		};
		this.updateColumn = function() {
			ColumnForm.action="updateColumn";
			ColumnForm.submit(); 
		};
		this.deleteColumn = function() {
			ColumnForm.action="deleteColumn?" + this.getSelectedItems();
			ColumnForm.submit();
		};
		this.insertTable = function() {
			ValueForm.action = "insertValue";
			ValueForm.submit();
		};
		this.updateTable = function() {
			TableForm.action="updateTable";
			TableForm.submit(); 
		};
		this.deleteTable = function() {
			TableForm.action="deleteTable?" + this.getSelectedItems();
			TableForm.submit();
		};
		this.insertValue = function() {
			ValueForm.action = "insertValue";
			ValueForm.submit();
		};
		this.select = function(index) {
			var item = this.row + index;
			if ($(this.switcher).bootstrapSwitch('state') == true) {
				return;
			}
			if (!this.isSelected(index)) {
				document.getElementById(item).style.backgroundColor="lightblue";
				this.selectedItems[this.indexOfItems] = index;
			} else {
				document.getElementById(item).style.backgroundColor="#012B39";
				this.remove(index);
			}
			this.indexOfItems++;
			/*console.log(index);
			console.log(this.selectedItems);*/
		};
		this.isSelected = function(item) {
			for (var i in this.selectedItems) {
				if (item == this.selectedItems[i]) {
					return true;
				}
			}
			return false;
		};
		this.indexOf = function(item) {
			for (var i in this.selectedItems) {
				if (item == this.selectedItems[i]) {
					return i;
				}
			}
			return -1;
		};
		this.remove = function(item) {
			var index = this.indexOf(item);
			if (index >= 0) {
				this.selectedItems.splice(index, 1); 
				return true; 
			} 
			return false;
		};
		this.define = function() {
			if ($(this.switcher).bootstrapSwitch('state') == true) {
				this.updateValue();
			} else {
				this.deleteValue();
			}
		};
		this.defineColumn = function() {
			if ($(this.switcher).bootstrapSwitch('state') == true) {
				this.updateColumn();
			} else {
				this.deleteColumn();
			}
		};
		this.defineTable = function() {
			if ($(this.switcher).bootstrapSwitch('state') == true) {
				this.updateTable();
			} else {
				this.deleteTable();
			}
		};
		this.getSelectedItems = function() {
			var itemsStr = "";
			var i = 0;
			for (; i < this.selectedItems.length - 1; i++) {
				itemsStr = itemsStr + this.selectedItems[i] + "o";
			}
			itemsStr = itemsStr + this.selectedItems[i];
			return "delIndexStr=" + itemsStr;
		}
};

var value = new Workbench("Value", "#mode-switch-value", "value-row");
var column = new Workbench("Column", "#mode-switch-column", "column-row");
var table = new Workbench("Table", "#mode-switch-table", "table-row");


var indexOfColumn = 1;
// So dirty...
function addTr() {
	var insertTd0 = "<input type='text' class='table-text' name='insertColumns[" + indexOfColumn + "].name'/>";
	var insertTd1 = "<select name='insertColumns[" + indexOfColumn + "].dataType'> <option value=12>VARCHAR</option>" + 
											"<option value=5>SMALLINT</option>" +
											"<option value=4>INTEGER</option>" +
											"<option value=-5>BIGINT</option>" +
											"<option value=6>FLOAT</option>" +
											"<option value=7>REAL</option>" +
											"<option value=8>DOUBLE</option>" +
											"<option value=2>NUMERIC</option>" +
											"<option value=3>DECIMAL</option>" +
											"<option value=1>CHAR</option>" +
											"<option value=-1>LONGVARCHAR</option>" +
											"<option value=92>DATE</option>" +
											"<option value=92>TIME</option>" +
											"<option value=93>TIMESTAMP</option>" +
											"<option value=-2>BINARY</option>" +
											"<option value=-3>VARBINARY</option>" +
											"<option value=-4>LONGVARBINARY</option>" +
											"<option value=0>NULL</option>" +
											"<option value=1111>OTHER</option>" +
											"<option value=2000>JAVA_OBJECT</option>" +
											"<option value=2001>DISTINCT</option>" +
											"<option value=2002>STRUCT</option>" +
											"<option value=2003>ARRAY</option>" +
											"<option value=2004>BLOB</option>" +
											"<option value=2005>CLOB</option>" +
											"<option value=2006>REF</option>" +
											"<option value=70>DATALINK</option>" +
											"<option value=16>BOOLEAN</option>" +
											"<option value=-8>ROWID</option>" +
											"<option value=-15>NCHAR</option>" +
											"<option value=-9>NVARCHAR</option>" +
											"<option value=-16>LONGNVARCHAR</option>" +
											"<option value=2011>NCLOB</option>" +
											"<option value=2009>SQLXML</option>" +
										"</select>";
	var insertTd2 = "<input type='text' class='table-text' name='insertColumns[" + indexOfColumn + "].columnSize'/>";
	var insertTd3 = "<input type='checkbox' class='checkbox' name='insertColumns[" + indexOfColumn + "].PK'/>";
	var insertTd4 = "<input type='checkbox' class='checkbox' name='insertColumns[" + indexOfColumn + "].NN'/>";
	var insertTd5 = "<input type='checkbox' class='checkbox' name='insertColumns[" + indexOfColumn + "].UQ'/>";
	var insertTd6 = "<input type='checkbox' class='checkbox' name='insertColumns[" + indexOfColumn + "].BIN'/>";
	var insertTd7 = "<input type='checkbox' class='checkbox' name='insertColumns[" + indexOfColumn + "].UN'/>";
	var insertTd8 = "<input type='checkbox' class='checkbox' name='insertColumns[" + indexOfColumn + "].ZF'/>";
	var insertTd9 = "<input type='checkbox' class='checkbox' name='insertColumns[" + indexOfColumn + "].AI'/>";
	var insertTd10 = "<input type='text' class='table-text'  value='' name='insertColumns[" + indexOfColumn + "].columnDefault'/>";
	var insertTd11 = "<input class='column-btn-remove' value='Remove' type='button' onclick='removeTr("+ indexOfColumn +")'>";
	
	$('#columnTable tr:last').after('<tr id="tr' + indexOfColumn + '"><td>' + insertTd0 + 
			'<td>' + insertTd1 +
			'<td>' + insertTd2 +
			'<td>' + insertTd3 +
			'<td>' + insertTd4 +
			'<td>' + insertTd5 +
			'<td>' + insertTd6 +
			'<td>' + insertTd7 +
			'<td>' + insertTd8 +
			'<td>' + insertTd9 +
			'<td>' + insertTd10 +
			'<td>' + insertTd11 +
			'</tr>');
	indexOfColumn++;
};

function removeTr(id) {
	$("#tr"+id).remove();
};

function execute() {
	var commond = editor.getSession().getValue();
	var schemaName = $("#schema-name").val();
	$.ajax({type:"GET",url:"executeCommond?commond="+commond+"&schemaName="+schemaName,success:function(result){
	    $("#console").html(result);
	  }});
};

function dropSchema(schema) {
	var message = "Are you sure to delete the database " + schema + "?";
	bootbox.confirm(message, function(result) {
		if (result == true) {
			SchemaForm.action = "dropSchema?curSchema.name="+schema;
			SchemaForm.submit();
		}
	});
}