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
		data += "&searchField=" + name;
		request.open("GET", (url + "?" + data), true);
		request.onreadystatechange = function() {
			handler(request);
		};
		request.send();
		document.getElementById("starting").style.display = "none";
	} else {
		loadAllBooks('Start');
		// document.getElementById("starting").style.display = "none";

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
	document.forms["browseForm"]["searchField"].value = "";
}

function loadByCategory1(url) {

	var request = new XMLHttpRequest();
	var data = '';
	data += "comm=category1";
	request.open("GET", (url + "?" + data), true);
	request.onreadystatechange = function() {
		handler(request);
	};
	request.send();
	document.getElementById("starting").style.display = "none";
}
function loadByCategory2(url) {

	var request = new XMLHttpRequest();
	var data = '';
	data += "comm=category2";
	request.open("GET", (url + "?" + data), true);
	request.onreadystatechange = function() {
		handler(request);
	};
	request.send();
	document.getElementById("starting").style.display = "none";
}
function loadByCategory3(url) {

	var request = new XMLHttpRequest();
	var data = '';
	data += "comm=category3";
	request.open("GET", (url + "?" + data), true);
	request.onreadystatechange = function() {
		handler(request);
	};
	request.send();
	document.getElementById("starting").style.display = "none";
}
function loadByCategory4(url) {

	var request = new XMLHttpRequest();
	var data = '';
	data += "comm=category4";
	request.open("GET", (url + "?" + data), true);
	request.onreadystatechange = function() {
		handler(request);
	};
	request.send();
	document.getElementById("starting").style.display = "none";
}

function analyticsPopUpFunction() {

	// Get the modal
	var modal = document.getElementById('janReport');

	// Get the button that opens the modal
	var btn = document.getElementById("janReport");

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks the button, open the modal
	btn.onclick = function() {
		modal.style.display = "block";
	}

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
		modal.style.display = "none";
	}

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}
}
