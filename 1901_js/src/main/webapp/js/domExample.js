console.log("did the page load?");
let d = document;
let c = console;

//example for listener

window.onload = function(){
	c.log("page is loaded now");

	//creating an object for add button
	let addButton = d.getElementById("addBtn");
	
	//add event listeners for this button when a click occurs
	addButton.addEventListener("click",() => {
		//parseInt - convert a string to a number
		let n1 = parseInt(d.getElementById("number1").value);
		let n2 = parseInt(d.getElementById("number2").value);
		//assigning the results of addition to an existing DOM element
		d.getElementById("results").innerHTML = n1 + n2;
	});
}
