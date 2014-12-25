/**
 * Some database workbench functions.
 * author:tajpure
 */

function define_value() {
	value.define();
};

function define_column() {
	column.define();
};

function insert_value() {
	value.insertValue();
};

function delete_value() {
	value.deleteValue();
};

function save_value() {
	value.saveValue();
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
			$.fn.bootstrapSwitch.defaults.offText = "Delete";
			$.fn.bootstrapSwitch.defaults.onText = "Save";
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
		this.saveValue = function() {
			ValueForm.action="saveValue";
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
		this.saveColumn = function() {
			ColumnForm.action="saveColumn";
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
		this.saveTable = function() {
			$(this.form).action="save" + this.name;
			$(this.form).submit(); 
		};
		this.deleteTable = function() {
			$(this.form).action="deleteValue?" + this.getSelectedItems();
			$(this.form).submit();
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
				this.saveValue();
			} else {
				this.deleteValue();
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
var schema = new Workbench("Schema", "#mode-switch-schema", "schema-row");
