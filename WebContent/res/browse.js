function validate() {
	var ok = true;
	var p;
	p = document.forms["browseForm"]["searchField"].value;
	if (p == null || p == "") {
		alert("Invalid Name!");
		ok = false;
	}
	if (!ok)
		return false;

	return ok;
}

function handler(request) {
	if ((request.readyState == 4) && (request.status == 200)) {

		target = document.getElementById("resultDisplay");
		target.innerHTML = request.responseText;
	}
}

function searchBook(url) {
	if (validate()) {
		var name = document.forms["browseForm"]["searchField"].value;
		var request = new XMLHttpRequest();
		var data = '';
		data += "comm=ajax";
		// data += "&searchField=" + name;

		request.open("GET", (url + "?" + data), true);
		request.onreadystatechange = function() {
			handler(request);
		};
		request.send();
	}
}