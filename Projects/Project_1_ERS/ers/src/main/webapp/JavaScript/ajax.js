/**
 * 
 */

let btn = document.getElementById("displayBtn");

btn.addEventListener("click",getInfo);

function getInfo(){
	var x = new XMLHttpRequest();
	x.onreadystatechange = function(){
		let rt = document.getElementById("responseText");
		console.log(x.responseText + " " + x.readyState + " " +x.status);
	};
	
	x.open("get","http://myjson.com/f3bx0");
	x.send();
}