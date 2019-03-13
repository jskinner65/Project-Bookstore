function validate() {
	var ok = true;
	alert("hey");
	var p;
	p = document.forms["browseForm"]["lname"].value;
	if (p == null || p =="") {
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

function doSimpleAjax(url) {

	if (validate()) {
		var name = document.forms["myForm"]["lname"].value;
		var minCredit = document.forms["myForm"]["minCredit"].value;
		var request = new XMLHttpRequest();
		var data = '';
		data += "comm=ajax";
		data += "&lname=" + name;
		data += "&minCredit=" + minCredit;

		request.open("GET", (url + "?" + data), true);
		request.onreadystatechange = function() {
			handler(request);
		};
		request.send();
	}
}