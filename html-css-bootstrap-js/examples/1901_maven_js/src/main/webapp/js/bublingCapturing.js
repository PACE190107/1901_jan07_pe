window.onload = () => {
	document.getElementById('outer').addEventListener('click',foo,true);
	document.getElementById('inner').addEventListener('click',foo,true);
	document.getElementById('innermost').addEventListener('click',foo,true);
}
function foo(){
	console.dir(this);	//to print the current object
	console.log(event);
	event.stopPropagation();
}