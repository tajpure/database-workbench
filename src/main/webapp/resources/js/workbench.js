/**
 * Some database workbench functions.
 * author:taojx
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

function Workbench(switcher, row) {
		this.selectedItems = [];
		this.indexOfItems = 0;
		this.switcher = switcher;
		this.row = row;
		this.init = function() {
			this.initSwitch();
			$('#myTab a').click(function (e) {
				  e.preventDefault()
				  $(this).tab('show')
				});
			$('#myTab a[href="#profile"]').tab('show'); // Select tab by name
			$('#myTab a:first').tab('show'); // Select first tab
			$('#myTab a:last').tab('show');// Select last tab
			$('#myTab li:eq(2) a').tab('show'); // Select third tab (0-indexed)
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
			valueForm.action="saveValue";
			valueForm.submit(); 
		};
		this.deleteValue = function() {
			valueForm.action="deleteValue?" + this.getSelectedItems();
			valueForm.submit();
		};
		this.insertValue = function() {
			valueForm.action="insertValue";
			valueForm.submit(); 
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

var value = new Workbench("#mode-switch-value", "value-row");
var column = new Workbench("#mode-switch-column", "column-row");
var schema = new Workbench("#mode-switch-schema", "schema-row");
