

function buttonIsPressed() {
	var ok = false;
	
	
	if(document.getElementById('comment').value==="Enter some text here"){
		document.getElementById('comment').value="";
		//alert(document.getElementById('comment').value)
	}
	
	if (document.getElementById('5-star').checked) {
		ok = true;
	} else if (document.getElementById('4-star').checked) {
		ok = true;
	} else if (document.getElementById('3-star').checked) {
		ok = true;
	} else if (document.getElementById('2-star').checked) {
		ok = true;
	} else if (document.getElementById('1-star').checked) {
		ok = true;
	}

	if (!ok) {
		alert("Please give this book a rating")
		ok = false;
	}
	
	return ok;
}
