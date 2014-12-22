/**
 * Some database table manage functions.
 * author:taojx
 */

var selectedItems = [];

var indexOfItems = 0;

var switcher = "#mode-switch";

function init() {
	showMenu();
	initSwitch();
}

function initSwitch() {
	$.fn.bootstrapSwitch.defaults.size = "mini";
	$.fn.bootstrapSwitch.defaults.offText = "Delete";
	$.fn.bootstrapSwitch.defaults.onText = "Save";
	$(switcher).bootstrapSwitch('state', true, true);
	$(switcher).on('switchChange.bootstrapSwitch', function () {
		console.log("Switch mode changed");
	}); 
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
	valueForm.action="deleteValue?" + getSelectedItems();
	valueForm.submit();
}

function insertValue() {
	valueForm.action="insertValue";
	valueForm.submit(); 
}

function select(index) {
	var item = "row_" + index;
	if ($(switcher).bootstrapSwitch('state') == true) {
		return;
	}
	if (!isSelected(index)) {
		document.getElementById(item).style.backgroundColor="lightblue";
		selectedItems[indexOfItems] = index;
	} else {
		document.getElementById(item).style.backgroundColor="#012B39";
		remove(index);
	}
	indexOfItems++;
	console.log(selectedItems);
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

function define() {
	if ($(switcher).bootstrapSwitch('state') == true) {
		saveValue();
	} else {
		deleteValue();
	}
}

function getSelectedItems() {
	var itemsStr = "";
	var i = 0;
	for (; i < selectedItems.length - 1; i++) {
		itemsStr = itemsStr + selectedItems[i] + "o";
	}
	itemsStr = itemsStr + selectedItems[i];
	return "delIndexStr=" + itemsStr;
}
