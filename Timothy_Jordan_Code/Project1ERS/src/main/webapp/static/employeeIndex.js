/**
 * 
 */



var pendingReqTable = document.getElementById("#pendingReqTable");
var resolvedReqTable = document.getElementById("#resolvedReqTable");



$(document).ready(function() { 
	
	
	
	//POPLATE PENDING REQUEST TABLE
	
	 var xhr = new XMLHttpRequest();
	  xhr.onreadystatechange = function() {
	    if (xhr.readyState == 4 && xhr.status == 200) {
	    
	      

	      var parsed = JSON.parse(xhr.responseText);
	   console.log(parsed);
	      
	      for(let i = 0; i < parsed.length; i++) {
	    	  $("#pendingReqTable").find('tbody').append( "<tr><td>" + parsed[i].emp_ID + "</td><td>" +
		    		  parsed[i].req_ID + "</td><td>" + parsed[i].sub_date + "</td><td> " +
		    		  parsed[i].req_type + "</td> <td> " + parsed[i].req_amount + "</td><td>" + parsed[i].req_status + "</td></tr>" );
	      }

	    }
	  };
	  xhr.open("get", "http://localhost:8080/Project1ERS/getPending");
	  xhr.send();
	  
	  
	  
	  
	  
	  
	  //END PENDING REQUEST TABLE
	  
	  var popResolved = new XMLHttpRequest();
	  popResolved.onreadystatechange = function() {
	    if (popResolved.readyState == 4 && popResolved.status == 200) {
	      let rt = document.getElementById("responseText");
	      let rh = document.getElementById("responseHeaders");
	      let hs = document.getElementById("httpStatus");
	      let ht = document.getElementById("httpStatusText");
	      let rx = document.getElementById("responseXML");
	      let jr = document.getElementById("myjsonresponse");
	      //rt.innerHTML = xhr.responseText;
	      //rh.innerHTML = xhr.getAllResponseHeaders();
	      //hs.innerHTML = xhr.status;
	      //ht.innerHTML = xhr.statusText;
	      //rx.innerHTML = xhr.responseXML;
	      var rImg = document.getElementById("randomImage");

	      var parsed = JSON.parse(popResolved.responseText);
	      //console.log(parsed);
	      ///console.log(parsed[0]);
	      
	      //console.log(parsed.length);
	      
	      for(let i = 0; i < parsed.length; i++) {
	    	  $("#resolvedReqTable").find('tbody').append( "<tr><td>" + parsed[i].emp_ID + "</td><td>" +
		    		  parsed[i].req_ID + "</td><td>" + parsed[i].sub_date + "</td><td> " +
		    		  parsed[i].req_type + "</td> <td> " + parsed[i].req_amount + "</td><td>" + parsed[i].req_status + "</td><td> " +parsed[i].req_man_id + "</td><td>"+ parsed[i].resolvedDate + "</td></tr>" );
	      }

	    }
	  };
	  popResolved .open("get", "http://localhost:8080/Project1ERS/getResolved");
	  popResolved .send();
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  


 });



//
//var newReqBtn = document.getElementById("newReq-modal-submit-btn");
//newReqBtn.addEventListener("click", function () {
//	
//	//POPLATE PENDING REQUEST TABLE
//	
//	 var addReqXHR = new XMLHttpRequest();
//	 addReqXHR.onreadystatechange = function() {
//	    if (addReqXHR.readyState == 4 && addReqXHR.status == 200) {
//	      let rt = document.getElementById("responseText");
//	      let rh = document.getElementById("responseHeaders");
//	      let hs = document.getElementById("httpStatus");
//	      let ht = document.getElementById("httpStatusText");
//	      let rx = document.getElementById("responseXML");
//	      let jr = document.getElementById("myjsonresponse");
//	      //rt.innerHTML = xhr.responseText;
//	      //rh.innerHTML = xhr.getAllResponseHeaders();
//	      //hs.innerHTML = xhr.status;
//	      //ht.innerHTML = xhr.statusText;
//	      //rx.innerHTML = xhr.responseXML;
//	      var rImg = document.getElementById("randomImage");
//
//	      var parsed = JSON.parse(addReqXHR.responseText);
//	      console.log("Add request parsed")
//	      console.log(parsed);
//	      //console.log(parsed[0]);
//	      
//	      console.log(parsed.length);
//	      
////	      for(let i = 0; i < parsed.length; i++) {
////	    	  $("#pendingReqTable").find('tbody').append( "<tr><td>" + parsed[i].emp_ID + "</td><td>" +
////		    		  parsed[i].req_ID + "</td><td>" + parsed[i].sub_date + "</td><td> " +
////		    		  parsed[i].req_type + "</td> <td> " + parsed[i].req_amount + "</td><td>" + parsed[i].req_status + "</td></tr>" );
////	      }
//
//	    }
//	  };
//	  xhr.open("POST", "http://localhost:8080/Project1ERS/addRequest");
//	  xhr.send();
//	
//	
//	
//});

//
//
//
//
//
//
//
//


//$(window).unload(function() {
//	  
//	
//	
//	
//	
//	
//	
//	});
