

function buttonIsPressed() {
	var ok = false;
	
	if (document.getElementById('rate5').checked) {
		ok = true;
	} else if (document.getElementById('rate4').checked) {
		ok = true;
	} else if (document.getElementById('rate3').checked) {
		ok = true;
	} else if (document.getElementById('rate2').checked) {
		ok = true;
	} else if (document.getElementById('rate1').checked) {
		ok = true;
	}

	if (!ok) {
		alert("Please give this book a rating")
		ok = false;
	}
	
	return ok;
}
