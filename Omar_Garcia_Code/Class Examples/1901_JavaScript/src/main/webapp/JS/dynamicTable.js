/**
 * 
 */

// window.onload = function(){
// console.log("page is loading...")
// var x = document.getElementById("myForm");
// for (let a in x){
// console.log(a);
// }
// }
window.onload = function() {
	document.getElementById("subButton").addEventListener("click", addRows)
}

function addRows() {
	let id = document.getElementById("heroid");
	let name = document.getElementById("heroname");
	let age = document.getElementById("heroage");

	if (id && name && age) {
		if (id.trim != "" && name.trim != "" && age.trim != "") {
			let row = document.createElement("tr");
			let idcol = document.createElement("td");
			let ncol = document.createElement("td");
			let nage = document.createElement("td");

			idcol.texContent = id;
			ncol.textContent = name;
			nage.textContent = age;

			row.appendChild(idcol);
			row.appendChild(ncol);
			row.appendChild(nage);

			document.getElementById("myTable").appendChild(row);
		} else {

		}
	}
}
