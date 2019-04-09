function validate() {
	var ok = true;
	var p;
	p = document.forms["browseForm"]["searchField"].value;
	if (p == null || p == "") {

		ok = false;
	}
	if (!ok)
		return false;

	return ok;
}

function handler(request) {
	if ((request.readyState == 4) && (request.status == 200)) {

		target = document.getElementById("results");
		target.innerHTML = request.responseText;
	}
}

function searchBook(url) {
	if (validate()) {
		var name = document.forms["browseForm"]["searchField"].value;
		var request = new XMLHttpRequest();
		var data = '';
		data += "currPage=search";
		data += "&amp;searchField=" + name;
		request.open("POST", (url + "?" + data), true);
		request.onreadystatechange = function() {
			handler(request);
		};

		request.send();
		document.getElementById("starting").style.display = "none";
	}
	else{
		loadAllBooks('Start');
	//	document.getElementById("starting").style.display = "none";

	}
} 

function loadAllBooks(url) {

	var request = new XMLHttpRequest();
	var data = '';
	data += "comm=ajax";
	request.open("GET", (url + "?" + data), true);
	request.onreadystatechange = function() {
		handler(request);
	};
	request.send();
	document.getElementById("starting").style.display = "none";
}

function loadByCategory(searchcategory) {
	var request = new XMLHttpRequest(); 

	request.open("GET", ("Start?currPage=categories&amp;category=" + searchcategory), true);
	request.onreadystatechange = function() {
		handler(request);
	};
	request.send();
}

function loadPayment(url) {

	var request = new XMLHttpRequest(); 
	var data = '';
	alert("hey");
	data += "currPage='payment'";
	request.open("POST", (url + "?" + data), true);
	request.onreadystatechange = function() {
		handler(request);
	};
	request.send();
}