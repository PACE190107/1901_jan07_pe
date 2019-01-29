/**
 * more dom manipulation
 */

//print all properties and methods of an object -how?
//window.onload = function(){
//	console.log("page is loading...");
//	var x = document.getElementById("myForm");
//	for (let a in x){
//		console.log(a);
//	}
//}

window.onload = function(){
	document.getElementById("subButton").addEventListener("click",function(){
		let id = document.getElementById("heroid").value;
		let name = document.getElementById("heroname").value;
		let age = document.getElementById("heroage").value;
		
		if (id && name && age){
			if(id.trim != "" || name.trim != "" || age.trim != ""){
				let row = document.createElement("tr");
				let idcol = document.createElement("td");
				let ncol = document.createElement("td");
				let agecol = document.createElement("td");
				
				idcol.textContent = id;
				ncol.textContent = name;
				agecol.textContent = age;
				
				row.appendChild(idcol);
				row.appendChild(ncol);
				row.appendChild(agecol);
				
				document.getElementById("myTable").appendChild(row);		
			} else {
				console.log("no values provided in the form");
			}		
		}
	});
	document.getElementById("subButton").addEventListener("click",clearRows);
	document.getElementById("resButton").addEventListener("click",clearRows);
}



function clearRows(){
	document.getElementById("heroid").value = "";
	document.getElementById("heroname").value = "";
	document.getElementById("heroage").value = "";
}





