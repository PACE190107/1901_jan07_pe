
window.onload = () => {
	document.getElementById("outermost").addEventListener("click",foo );
	document.getElementById("inner").addEventListener("click",foo);
	document.getElementById("innermost").addEventListener("click",foo);
}

function foo(){
	console.log(this);
	//to print the current object
}