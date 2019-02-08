/**
 * 
 */


$(document).ready(function() {
	
	var exit = new XMLHttpRequest();
	exit.onreadystatechange = function() {
	    if (exit.readyState == 4 && exit.status == 200) {
	  

	     
	      //console.log(parsed);
	      ///console.log(parsed[0]);
	      
	      //console.log(parsed.length);
	      
	     

	    }
	  };
	  exit.open("get", "http://localhost:8080/Project1ERS/logout");
	  exit.send();
	
	
	
});




//var iconBtn = document.getElementById("login-icon-btn");
//iconBtn.addEventListener("click", function() {
//	console.log("was clicked");
//	var iconArr = ['fa-user-secret', 'fa-user', 'fa-user-tie', 'fa-user-ninja', 'fa-user-nurse', 'fa-user-injured', 'fa-user-graduate', 'fa-user-astronaut']
//	$('#user_icon_login').addClass('fa-user-secret').removeClass('fa-user');
//});

//var myVar = setInterval(myTimer, 1000);
//
//function myTimer() {
//	  //var d = new Date();
//	  //document.getElementById("login-icon-btn").innerHTML = d.toLocaleTimeString();
//	
//	}


function rand(min, max) {
	  var offset = min;
	  var range = (max - min) + 1;

	  var randomNumber = Math.floor( Math.random() * range) + offset;
	  return randomNumber;
	}

var someNumb = rand(0,7);

console.log(rand(0,7));


var iconArr = ['fa-user-secret', 'fa-user', 'fa-user-tie', 'fa-user-ninja',
	 'fa-user-injured', 'fa-user-graduate', 'fa-user-astronaut', 'fa-user-md'];



//console.log(iconArr[someNumb]);

var myVar = setInterval(myTimer, 1000);
var lastClass = 'fa-user';
var newClass;
var index = 0;
function myTimer() {
	
	console.log(index);
	if(index == 8) {
		index = 0;
	}
	newClass = iconArr[index];
	$('#user_icon_login').addClass(newClass).removeClass(lastClass);
	lastClass = newClass;
	newClass = iconArr[someNumb];
	index++;
	
	  //var d = new Date();
	  //document.getElementById("login-icon-btn").innerHTML = d.toLocaleTimeString();
	//console.log(rand(0,7));
//	 newClass = iconArr[rand(0,6)];
//	 console.log("newClass" + newClass);
//	 
//	$('#user_icon_login').addClass(newClass).removeClass(lastClass);
//	
//	lastClass = newClass;
//	newClass = iconArr[someNumb];
	}


