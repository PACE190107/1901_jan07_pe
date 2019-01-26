
console.log("did the page load??");
let d = document;
let c = console;

// Simplest example for listener
window.onload = () => {
	c.log("page should be loaded now");


let addButton = d.getElementById("addBtn");

// add event listener for this button when a click occurs
addButton.addEventListener("click", () => {
	// parseInt - convert a string into a number
	let n1 = parseInt(d.getElementById("number1").value);
	let n2 = parseInt(d.getElementById("number2").value);
	
	d.getElementById("results").innerHTML = n1+n2;
});
}