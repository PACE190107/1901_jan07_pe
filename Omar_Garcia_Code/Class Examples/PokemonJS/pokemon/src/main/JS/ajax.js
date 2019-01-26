let btn = document.getElementById("displayButton");
btn.addEventListener("click", getInfo);

function getInfo() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if ((xhr.readyState == 4) && (xhr.status == 200)) {
			/*let rt = document.getElementById("responseText");
			console.log(xhr.responseText + " " + xhr.readyState + " "
					+ xhr.status);*/
			let rt2 = document.getElementById("responseText");
			rt2.innerHTML = xhr.responseText;
			let rh = document.getElementById("responseHeader");
			rh.innerHTML = xhr.getAllResponseHeaders();
			let hs = document.getElementById("httpStatus"); 
			hs.innerHTML = xhr.status;
			let hst = document.getElementById("httpStatusText");
			hst.innerHTML = xhr.statusText;
			let rx = document.getElementById("responseXML");
			rx.innerHTML = xhr.responseXML;

		}
	};

	xhr.open("get", "https://api.myjson.com/bins/tdqfo");
	xhr.send();
}