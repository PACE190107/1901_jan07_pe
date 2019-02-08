/**
 * 
 */

window.onload = function(){
	function toggleNavState(){
		let toggleNav = document.getElementsByClassName("nav-link disabled");
		let i;
		for (i = 0; i < toggleNav.length; i++){
			toggleNav[i].className = "nav-link active";
		}
	};
	var submitNav = document.getElementById("submitNav"),
		pendingNav = document.getElementById("pendingNav"),
		resolvedNav = document.getElementById("resolvedNav"),
		infoNav = document.getElementById("infoNav");
	submitNav.onclick = function(){
		toggleNavState();
		submitNav.className = "nav-link disabled";
		clearMain();
		createSubmitForm();
	};
	pendingNav.onclick = function(){
		toggleNavState();
		pendingNav.className = "nav-link disabled";
		clearMain();
		createPendingRequestTable();
	};
	resolvedNav.onclick = function(){
		toggleNavState();
		resolvedNav.className = "nav-link disabled";
		clearMain();
		createResolvedRequestTable();
	};
	infoNav.onclick = function(){
		toggleNavState();
		infoNav.className = "nav-link disabled";
		clearMain();
		createPersonalInfoForm();
	};
	
	function clearMain() {
		let main = document.getElementById("main");
		while (main.firstElementChild) {
			main.removeChild(main.firstChild);
		}
	}
	function createSubmitForm() {
		let main = document.getElementById("main");
		let head = document.createElement("h3");
		head.innerHTML = "Submit new reimbursement request";
		main.appendChild(head);
		let form = document.createElement("form");
		form.setAttribute("id", "submitForm")
		main.appendChild(form);
		var input = document.createElement("input");
		input.setAttribute("type","text");
		input.setAttribute("id","vendor");
		input.setAttribute("placeholder","Enter Vendor");
		form.appendChild(input);
		input = document.createElement("input");
		input.setAttribute("type","text");
		input.setAttribute("id","desc");
		input.setAttribute("placeholder","Enter Description");
		form.appendChild(input);
		input = document.createElement("input");
		input.setAttribute("type","text");
		input.setAttribute("id","amount");
		input.setAttribute("placeholder","Enter Amount");
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
		let submitVendor = document.getElementById("vendor").value;
		let submitDesc = document.getElementById("desc").value;
		let submitAmt = document.getElementById("amount").value;
		//ajax to submit 
	}
	function createPendingRequestTable() {
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
	}
	function createResolvedRequestTable() {
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