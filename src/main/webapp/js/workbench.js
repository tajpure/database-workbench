/**
 * 
 */

function showTable() {
	$.ajax({type: "Get",url: URL,}).done(refreshTable(columns, values));
}

function refreshTable(columns, values) {
	var tableView = "<table><thead><tr>";
	for (var column in columns) {
		tableView = tableView + "<th>" + column;
	}
	tableView = tableView + "<th><input class=\"table-btn\" value=\"Insert\" type=\"submit\">";
	tableView = tableView + "</thead><tbody>";
	for (var list in values) {
		tableView = tableView + "<tr>";
		for (var val in list) {
			tableView = tableView + "<td>" + list;
		}
	}
	tableView = tableView + "<td><input class=\"table-btn\" value=\"Save\" type=\"submit\">"
						+ "<td><input class=\"table-btn\" value=\"Delete\" type=\"submit\"></tbody></table>";
	$("#table").html(tableView);
}