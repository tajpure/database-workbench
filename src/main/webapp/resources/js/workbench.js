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

var value = {
		"selectedItems" : [],
		"indexOfItems" : 0,
		"switcher" : "#mode-switch-value",
		"init" : function() {
			this.showMenu();
			this.initSwitch();
			$('#myTab a').click(function (e) {
				  e.preventDefault()
				  $(this).tab('show')
				});
			$('#myTab a[href="#profile"]').tab('show'); // Select tab by name
			$('#myTab a:first').tab('show'); // Select first tab
			$('#myTab a:last').tab('show');// Select last tab
			$('#myTab li:eq(2) a').tab('show'); // Select third tab (0-indexed)
		},
		"initSwitch" : function() {
			$.fn.bootstrapSwitch.defaults.size = "mini";
			$.fn.bootstrapSwitch.defaults.offText = "Delete";
			$.fn.bootstrapSwitch.defaults.onText = "Save";
			$(this.switcher).bootstrapSwitch('state', true, true);
			$(this.switcher).on('switchChange.bootstrapSwitch', function () {
				console.log("Switch mode changed");
			});
		},
		"showMenu" : function() {
			var schema = $("#table-schema-name").text();
			var schemaId = '#' + schema;
			var tableId = '#' + schema + '-tables';
			$(schemaId.toString()).trigger("click");
			$(tableId.toString()).trigger("click");
		},
		"saveValue" : function() {
			valueForm.action="saveValue";
			valueForm.submit(); 
		},
		"deleteValue" : function() {
			valueForm.action="deleteValue?" + this.getSelectedItems();
			valueForm.submit();
		},
		"insertValue" : function() {
			valueForm.action="insertValue";
			valueForm.submit(); 
		},
		"select" : function(index) {
			var item = "row_" + index;
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
			console.log(this.selectedItems);
		},
		"isSelected" : function(item) {
			for (var i in this.selectedItems) {
				if (item == this.selectedItems[i]) {
					return true;
				}
			}
			return false;
		},
		"indexOf" : function(item) {
			for (var i in this.selectedItems) {
				if (item == this.selectedItems[i]) {
					return i;
				}
			}
			return -1;
		},
		"remove" : function(item) {
			var index = this.indexOf(item); 
			if (index >= 0) { 
				this.selectedItems.splice(index, 1); 
				return true; 
			} 
			return false;
		},
		"define" : function() {
			if ($(this.switcher).bootstrapSwitch('state') == true) {
				this.saveValue();
			} else {
				this.deleteValue();
			}
		},
		"getSelectedItems" : function() {
			var itemsStr = "";
			var i = 0;
			for (; i < this.selectedItems.length - 1; i++) {
				itemsStr = itemsStr + this.selectedItems[i] + "o";
			}
			itemsStr = itemsStr + this.selectedItems[i];
			return "delIndexStr=" + itemsStr;
		}
};

var column = {
		"selectedItems" : [],
		"indexOfItems" : 0,
		"switcher" : "#mode-switch-column",
		"init" : function() {
			this.initColumnSwitch();
			$('#myTab a').click(function (e) {
				  e.preventDefault()
				  $(this).tab('show')
				});
			$('#myTab a[href="#profile"]').tab('show'); // Select tab by name
			$('#myTab a:first').tab('show'); // Select first tab
			$('#myTab a:last').tab('show');// Select last tab
			$('#myTab li:eq(2) a').tab('show'); // Select third tab (0-indexed)
		},
		"initColumnSwitch" : function() {
			$.fn.bootstrapSwitch.defaults.size = "mini";
			$.fn.bootstrapSwitch.defaults.offText = "Drop";
			$.fn.bootstrapSwitch.defaults.onText = "Save";
			$(this.switcher).bootstrapSwitch('state', true, true);
			$(this.switcher).on('switchChange.bootstrapSwitch', function () {
				console.log("Switch mode changed");
			});
		},
		"saveValue" : function() {
			tableForm.action="saveValue";
			tableForm.submit(); 
		},
		"deleteValue" : function() {
			tableForm.action="deleteValue?" + this.getSelectedItems();
			tableForm.submit();
		},
		"insertValue" : function() {
			tableForm.action="insertValue";
			tableForm.submit(); 
		},
		"select" : function(index) {
			var item = "row_" + index;
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
			console.log(selectedItems);
		},
		"isSelected" : function(item) {
			for (var i in this.selectedItems) {
				if (item == this.selectedItems[i]) {
					return true;
				}
			}
			return false;
		},
		"indexOf" : function(item) {
			for (var i in this.selectedItems) {
				if (item == this.selectedItems[i]) {
					return i;
				}
			}
			return -1;
		},
		"remove" : function(item) {
			var index = this.indexOf(item); 
			if (index >= 0) { 
				this.selectedItems.splice(index, 1); 
				return true; 
			} 
			return false;
		},
		"define" : function() {
			if ($(this.switcher).bootstrapSwitch('state') == true) {
				this.saveValue();
			} else {
				this.deleteValue();
			}
		},
		"getSelectedItems" : function() {
			var itemsStr = "";
			var i = 0;
			for (; i < this.selectedItems.length - 1; i++) {
				itemsStr = itemsStr + this.selectedItems[i] + "o";
			}
			itemsStr = itemsStr + this.selectedItems[i];
			return "delIndexStr=" + itemsStr;
		}
};

var schema = {
		"selectedItems" : [],
		"indexOfItems" : 0,
		"switcher" : "#mode-switch-schema",
		"init" : function() {
			this.showMenu();
			this.initSwitch();
			$('#myTab a').click(function (e) {
				  e.preventDefault()
				  $(this).tab('show')
				});
			$('#myTab a[href="#profile"]').tab('show'); // Select tab by name
			$('#myTab a:first').tab('show'); // Select first tab
			$('#myTab a:last').tab('show');// Select last tab
			$('#myTab li:eq(2) a').tab('show'); // Select third tab (0-indexed)
		},
		"initSwitch" : function() {
			$.fn.bootstrapSwitch.defaults.size = "mini";
			$.fn.bootstrapSwitch.defaults.offText = "Drop";
			$.fn.bootstrapSwitch.defaults.onText = "Save";
			$(this.switcher).bootstrapSwitch('state', true, true);
			$(this.switcher).on('switchChange.bootstrapSwitch', function () {
				console.log("Switch mode changed");
			});
		},
		"showMenu" : function() {
			var schema = $("#table-schema-name").text();
			var schemaId = '#' + schema;
			var tableId = '#' + schema + '-tables';
			$(schemaId.toString()).trigger("click");
			$(tableId.toString()).trigger("click");
		},
		"saveValue" : function() {
			valueForm.action="saveValue";
			valueForm.submit(); 
		},
		"deleteValue" : function() {
			valueForm.action="deleteValue?" + this.getSelectedItems();
			valueForm.submit();
		},
		"insertValue" : function() {
			valueForm.action="insertValue";
			valueForm.submit(); 
		},
		"select" : function(index) {
			var item = "row_" + index;
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
			console.log(this.selectedItems);
		},
		"isSelected" : function(item) {
			for (var i in this.selectedItems) {
				if (item == this.selectedItems[i]) {
					return true;
				}
			}
			return false;
		},
		"indexOf" : function(item) {
			for (var i in this.selectedItems) {
				if (item == this.selectedItems[i]) {
					return i;
				}
			}
			return -1;
		},
		"remove" : function(item) {
			var index = this.indexOf(item); 
			if (index >= 0) { 
				this.selectedItems.splice(index, 1); 
				return true; 
			} 
			return false;
		},
		"define" : function() {
			if ($(this.switcher).bootstrapSwitch('state') == true) {
				this.saveValue();
			} else {
				this.deleteValue();
			}
		},
		"getSelectedItems" : function() {
			var itemsStr = "";
			var i = 0;
			for (; i < this.selectedItems.length - 1; i++) {
				itemsStr = itemsStr + this.selectedItems[i] + "o";
			}
			itemsStr = itemsStr + this.selectedItems[i];
			return "delIndexStr=" + itemsStr;
		}
};