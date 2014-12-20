/**
 * Some database workbench functions.
 * author:taojx
 */

var selectedItems = [];

var indexOfItems = 0;

var curMode = 0; // 0 : Save Mode , 1 : Delete Mode

function init() {
	showMenu();
	initSwitch();
}

function initSwitch() {
	$.fn.bootstrapSwitch.defaults.size = "mini";
	$.fn.bootstrapSwitch.defaults.offText = "Delete";
	$.fn.bootstrapSwitch.defaults.onText = "Save";
	$("#mode-switch").bootstrapSwitch();
}

function showTable() {
	$.ajax({type: "Get",url: URL,}).done(refreshTable(columns, values));
}

function showMenu() {
	var schema = $("#table-schema-name").text();
	var schemaId = '#' + schema;
	var tableId = '#' + schema + '-tables';
	$(schemaId.toString()).trigger("click");
	$(tableId.toString()).trigger("click");
}

function saveValue() {
	valueForm.action="saveValue";
	valueForm.submit(); 
}

function deleteValue() {
	/*valueForm.action="deleteValue";
	valueForm.submit(); */
	console.log("delete...");
}

function insertValue() {
	valueForm.action="insertValue";
	valueForm.submit(); 
}

function select(index) {
	if (curMode == 0) {
		return;
	}
	var item = "row_" + index;
	if (!isSelected(item)) {
		document.getElementById(item).style.backgroundColor="lightblue";
		selectedItems[indexOfItems] = item;
	} else {
		document.getElementById(item).style.backgroundColor="#012B39";
		remove(item);
	}
	indexOfItems++;
}

function isSelected(item) {
	for (var i in selectedItems) {
		if (item == selectedItems[i]) {
			return true;
		}
	}
	return false;
}

function indexOf(item) {
	for (var i in selectedItems) {
		if (item == selectedItems[i]) {
			return i;
		}
	}
	return -1;
}

function remove(item) { 
	var index = indexOf(item); 
	if (index >= 0) { 
		selectedItems.splice(index, 1); 
		return true; 
	} 
	return false; 
}

//$("#mode-switch").on('switch-change', changeMode());

function changeMode() {
	if (curMode == 0) {
		curMode = 1;
	} else {
		curMode = 0;
	}
	console.log(curMode);
}

function define() {
	console.log(curMode);
	if (curMode == 0) {
		saveValue();
	} else {
		deleteValue();
	}
}
