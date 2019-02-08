/**
 * 
 */

window.onload = function(){
	function toggleNavState(){
		let toggleState = document.getElementsByClassName("nav-link disabled");
		let i;
		for (i = 0; i < toggleState.length; i++){
			toggleState[i].className = "nav-link active";
		}
	};
	var pendingNav = document.getElementById("pendingNav"),
		resolvedNav = document.getElementById("resolvedNav"),
		empInfoNav = document.getElementById("empInfoNav"),
		mgrInfoNav = document.getElementById("mgrInfoNav");
	pendingNav.onclick = function(){
		toggleNavState();
		pendingNav.className = "nav-link disabled";
		clearMain();
		createPendingTable();
	};
	resolvedNav.onclick = function(){
		toggleNavState();
		resolvedNav.className = "nav-link disabled";
		clearMain();
		createResolvedTable();
	};
	empInfoNav.onclick = function(){
		toggleNavState();
		empInfoNav.className = "nav-link disabled";
		clearMain();
		createEmployeeInfoTable();
	};
	mgrInfoNav.onclick = function(){
		toggleNavState();
		mgrInfoNav.className = "nav-link disabled";
		clearMain();
		createPersonalInfoForm();
	};

	function clearMain() {
		let main = document.getElementById("main");
		while (main.firstElementChild) {
			main.removeChild(main.firstChild);
		}
	}
	function createPendingTable() {
		let main = document.getElementById("main");
		let head = document.createElement("h3");
		head.innerHTML = "Pending Requests";
		main.appendChild(head);
		let reqTable = document.createElement("table");
		reqTable.setAttribute("class","table table-striped")
		main.appendChild(reqTable);
		let tablehead = document.createElement("thead");
		reqTable.appendChild(tablehead);
		let tablerow = document.createElement("tr");
		tablehead.appendChild(tablerow);
		let reqId = document.createElement("th");
		reqId.innerHTML = "Request ID";
		let reqVendor = document.createElement("th");
		reqVendor.innerHTML = "Vendor";
		let reqDesc = document.createElement("th");
		reqDesc.innerHTML = "Description";
		let reqAmt = document.createElement("th");
		reqAmt.innerHTML = "Amount";
		tablehead.appendChild(reqId);
		tablehead.appendChild(reqVendor);
		tablehead.appendChild(reqDesc);
		tablehead.appendChild(reqAmt);
		let tablebody = document.createElement("tbody");
		reqTable.appendChild(tablebody);
		//ajax to populate
		//add approve/deny buttons on each row to update
	}
	function createResolvedTable() {
		let main = document.getElementById("main");
		let head = document.createElement("h3");
		head.innerHTML = "Resolved Requests";
		main.appendChild(head);
		let reqTable = document.createElement("table");
		reqTable.setAttribute("class","table table-striped")
		main.appendChild(reqTable);
		let tablehead = document.createElement("thead");
		reqTable.appendChild(tablehead);
		let tablerow = document.createElement("tr");
		tablehead.appendChild(tablerow);
		let reqId = document.createElement("th");
		reqId.innerHTML = "Request ID";
		let reqVendor = document.createElement("th");
		reqVendor.innerHTML = "Vendor";
		let reqDesc = document.createElement("th");
		reqDesc.innerHTML = "Description";
		let reqAmt = document.createElement("th");
		reqAmt.innerHTML = "Amount";
		tablehead.appendChild(reqId);
		tablehead.appendChild(reqVendor);
		tablehead.appendChild(reqDesc);
		tablehead.appendChild(reqAmt);
		let tablebody = document.createElement("tbody");
		reqTable.appendChild(tablebody);
		//ajax to populate
	}
	function createEmployeeInfoTable() {
		let main = document.getElementById("main");
		let head = document.createElement("h3");
		head.innerHTML = "Employee Information";
		main.appendChild(head);
		let reqTable = document.createElement("table");
		reqTable.setAttribute("class","table table-striped")
		main.appendChild(reqTable);
		let tablehead = document.createElement("thead");
		reqTable.appendChild(tablehead);
		let tablerow = document.createElement("tr");
		tablehead.appendChild(tablerow);
		let empId = document.createElement("th");
		empId.innerHTML = "Employee ID";
		let fname = document.createElement("th");
		fname.innerHTML = "First Name";
		let lname = document.createElement("th");
		lname.innerHTML = "Last Name";
		let email = document.createElement("th");
		email.innerHTML = "Email";
		let uname = document.createElement("th");
		uname.innerHTML = "User Name";
		tablehead.appendChild(empId);
		tablehead.appendChild(fname);
		tablehead.appendChild(lname);
		tablehead.appendChild(email);
		tablehead.appendChild(uname);
		let tablebody = document.createElement("tbody");
		reqTable.appendChild(tablebody);
		//ajax to populate
	}
	function createPersonalInfoForm() {
		let main = document.getElementById("main");
		let head = document.createElement("h3");
		head.innerHTML = "Personal Information";
		main.appendChild(head);
		let form = document.createElement("form");
		form.setAttribute("id", "submitForm")
		main.appendChild(form);
		var input = document.createElement("input");
		input.setAttribute("type","text");
		input.setAttribute("id","fname");
		input.setAttribute("placeholder","First Name");
		form.appendChild(input);
		input = document.createElement("input");
		input.setAttribute("type","text");
		input.setAttribute("id","lname");
		input.setAttribute("placeholder","Last Name");
		form.appendChild(input);
		input = document.createElement("input");
		input.setAttribute("type","text");
		input.setAttribute("id","address");
		input.setAttribute("placeholder","Address");
		form.appendChild(input);
		input = document.createElement("input");
		input.setAttribute("type","text");
		input.setAttribute("id","email");
		input.setAttribute("placeholder","email@example.com");
		form.appendChild(input);
		input = document.createElement("input");
		input.setAttribute("type","text");
		input.setAttribute("id","uname");
		input.setAttribute("placeholder","User Name");
		form.appendChild(input);
		input = document.createElement("input");
		input.setAttribute("type","text");
		input.setAttribute("id","pass");
		input.setAttribute("placeholder","Password");
		form.appendChild(input);
		var button = document.createElement("input");
		button.setAttribute("class","btn btn-primary");
		button.setAttribute("type","submit");
		button.setAttribute("id","submitBtn");
		button.setAttribute("value","Submit");
		form.appendChild(button);
		button = document.createElement("input");
		button.setAttribute("class","btn btn-warning");
		button.setAttribute("type","reset");
		button.setAttribute("id","reset");
		button.setAttribute("value","Reset");
		form.appendChild(button);
		//ajax to populate with existing info
		let submitFname = document.getElementById("fname").value;
		let submitLname = document.getElementById("lname").value;
		let submitAddr = document.getElementById("address").value;
		let submitEmail = document.getElementById("email").value;
		let submitUname = document.getElementById("uname").value;
		let submitPass = document.getElementById("pass").value;
		//ajax to submit on click
	}
}