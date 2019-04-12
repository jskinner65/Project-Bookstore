function validate() {
	var ok = true;
	var p;
	p = document.forms["adminForm"]["email"].value;
	if (p == null || p == "") {
		alert("Please enter your email.")
		ok = false;
	}
	if (!ok)
		return false;
	p=document.forms["adminForm"]["pword"].value;
	if (p == null || p == "") {
		alert("Please enter your password.")
		ok = false;
	}
	if (!ok)
		return false;
	return true;
}
