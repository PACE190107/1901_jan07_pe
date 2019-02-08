window.onload = () => {
	document.getElementById('outer').addEventListener('click',foo);
	document.getElementById('inner').addEventListener('click',foo);
	document.getElementById('innermost').addEventListener('click',foo,);
}

function foo(){
	console.dir(this);
	//to print the current object
	//console.log(event);
	//event.stopPropagation(); - to stop event from continuing
}