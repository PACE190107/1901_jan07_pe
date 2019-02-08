window.onload = () =>{
	let btn = document.getElementById("displayBtn");
	btn.addEventListener("click",getInfo);
	function getInfo(){
		var x = new XMLHttpRequest();
		x.onreadystatechange = function(){
			//comment out if condition to print all different ready states
			if((x.readyState == 4) && (x.status == 200)){
				let rt = document.getElementById("responseText");
				let rh = document.getElementById("responseHeaders");
				let hs = document.getElementById("httpStatus");
				let hst = document.getElementById("httpStatusText");
				let rx = document.getElementById("responseXML");
				//let jr = document.getElementById("myJSONResponse");
				rt.innerHTML = x.responseText
				rh.innerHTML = x.getAllResponseHeaders();
				hs.innerHTML = x.status
				hst.innerHTML = x.statusText
				rx.innerHTML = x.responseXML
				//jr.innerHTML = x.responseText
				//console.log(x.responseText + " - " + x.readyState + " - " + x.status);
			}
		};
		x.open("get","https://api.myjson.com/bins/mtys4");
		x.send();
	}
}
//code without arrow notation
//window.onload = function(){
//	let btn = document.getElementById("displayBtn");
//	btn.addEventListener("click",getInfo);
//}
//
//
//function getInfo(){
//	var x = new XMLHttpRequest();
//	x.onreadystatechange = function(){
//		//comment out if condition to print all different ready states
//		if((x.readyState == 4) && (x.status == 200)){
//			let rt = document.getElementById("responseText");
//			let rh = document.getElementById("responseHeaders");
//			let hs = document.getElementById("httpStatus");
//			let hst = document.getElementById("httpStatusText");
//			let rx = document.getElementById("responseXML");
//			//let jr = document.getElementById("myJSONResponse");
//			rt.innerHTML = x.responseText
//			rh.innerHTML = x.getAllResponseHeaders();
//			hs.innerHTML = x.status
//			hst.innerHTML = x.statusText
//			rx.innerHTML = x.responseXML
//			//jr.innerHTML = x.responseText
//			//console.log(x.responseText + " - " + x.readyState + " - " + x.status);
//		}
//	};
//	x.open("get","https://api.myjson.com/bins/mtys4");
//	x.send();
//}