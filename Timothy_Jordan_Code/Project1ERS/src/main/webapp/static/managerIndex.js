/**
 * 
 */
//var manpendingReqTable = document.getElementById("#man-pendingReqTable");
//var allTable = document.getElementById("#all-Table");

$(document).ready(function() {
	//var allReqJSON;
	
	var xhr = new XMLHttpRequest();
	  xhr.onreadystatechange = function() {
	    if (xhr.readyState == 4 && xhr.status == 200) {
	    
	     

	      var parsed = JSON.parse(xhr.responseText);
	      console.log("all users" + parsed);
	      //console.log(parsed[0]);
	      
	      //console.log(parsed.length);
	      
	      for(let i = 0; i < parsed.length; i++) {
	    	  $("#all-Table").find('tbody').append( "<tr><td>" + parsed[i].emp_ID + "</td><td>" +
		    		  parsed[i].emp_userName + "</td><td>" + parsed[i].emp_firstName + "</td><td> " +
		    		  parsed[i].emp_lastName + "</td> <td> " + parsed[i].emp_email + "</td></tr>" );
	    	  
	    	  $("#id-Filter").append( "<option value=\"" + parsed[i].emp_ID + "\">Employee ID: "+ parsed[i].emp_ID+" Username: " + parsed[i].emp_userName+ "</option>" );
	    	  
	      }

	    }
	  };
	  xhr.open("get", "http://localhost:8080/Project1ERS/allUsers");
	  xhr.send();
	  
	
	
	
	//POPLATE PENDING REQUEST TABLE
		
		 var allPendingXHR = new XMLHttpRequest();
		 allPendingXHR.onreadystatechange = function() {
		    if (allPendingXHR.readyState == 4 && allPendingXHR.status == 200) {
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

		      var parsed = JSON.parse(allPendingXHR.responseText);
		      //console.log(parsed);
		      //console.log(parsed[0]);
		      
		      //console.log(parsed.length);
		      
		      for(let i = 0; i < parsed.length; i++) {
		    	  
		    	  
		    	  $("#man-pendingReqTable").find('tbody').append( "<tr><td>" + parsed[i].emp_ID + "</td><td>" +
			    		  parsed[i].req_ID + "</td><td>" + parsed[i].sub_date + "</td><td> " +
			    		  parsed[i].req_type + "</td> <td> " + parsed[i].req_amount + "</td><td>" + parsed[i].req_status + "</td></tr>" );
		    	  
		    	  $("#req-filter").append("<option value=\"" + parsed[i].req_ID + "\">Request ID: "+parsed[i].req_ID+" Type: " +parsed[i].req_type + "</option>" );
		    	  
		    	  
		      }

		    }
		  };
		  allPendingXHR.open("get", "http://localhost:8080/Project1ERS/getAllPending");
		  allPendingXHR.send();
		  
		  
		  var allResolvedXHR = new XMLHttpRequest();
			 allResolvedXHR.onreadystatechange = function() {
			    if (allResolvedXHR.readyState == 4 && allResolvedXHR.status == 200) {
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

			      var parsed = JSON.parse(allResolvedXHR.responseText);
			      //console.log(parsed);allResolvedXHR
			      console.log(parsed[0]);
			      
			      //console.log(parsed.length);
			      
			      for(let i = 0; i < parsed.length; i++) {
			    	  $("#allResolved-Table").find('tbody').append( "<tr><td>" + parsed[i].emp_ID + "</td><td>" +
				    		  parsed[i].req_ID + "</td><td>" + parsed[i].sub_date + "</td><td> " +
				    		  parsed[i].req_type + "</td> <td> " + parsed[i].req_amount + "</td><td>" +
				    		  parsed[i].req_status + "</td><td>" + parsed[i].req_man_id + " </td><td>" + parsed[i].resolvedDate + "</td></tr>" );
			      }

			    }
			  };
			  allResolvedXHR.open("get", "http://localhost:8080/Project1ERS/getGResolved");
			  allResolvedXHR.send();
			  
			  
	
			  
			  var allRequestXHR = new XMLHttpRequest();
			  allRequestXHR.onreadystatechange = function() {
				    if (allRequestXHR.readyState == 4 && allRequestXHR.status == 200) {
				      //console.log(allRequestXHR.responseText);
				      //allReqJSON = JSON.parse(allRequestXHR.responseText);
				      //console.log(parsed);allResolvedXHR
				      //console.log(parsed[0]);
				      
				      //console.log(parsed.length);
				      var allReq = JSON.parse(allRequestXHR.responseText);
				      console.log(allReq);
				      
				      
				      
				      for(let i = 0; i < allReq.length; i++) {
				    	  console.log(allReq[0].req_ID);
				    	
				    	  
				      }

				    }
				  };
				  allRequestXHR.open("get", "http://localhost:8080/Project1ERS/AllRequest");
				  allRequestXHR.send();
	
	
	
	
	
	
				  //console.log(allReqJSON + "global");
	
});



var viewAllReqBtn = document.getElementById('view-all-pending-btn');
viewAllReqBtn.addEventListener("click", function() {
	
	var managerTable = document.getElementById("man-pendingReqTable");
	//console.log()
	var rowCount = managerTable.rows.length - 1;
	
	for(var i = rowCount; i > 0; i--) {
		managerTable.deleteRow(i);
		console.log(i);
	
	
	}
	
	
	
	
	 var allPendingXHR = new XMLHttpRequest();
	 allPendingXHR.onreadystatechange = function() {
	    if (allPendingXHR.readyState == 4 && allPendingXHR.status == 200) {
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

	      var parsed = JSON.parse(allPendingXHR.responseText);
	      //console.log(parsed);
	      //console.log(parsed[0]);
	      
	      //console.log(parsed.length);
	      
	      for(let i = 0; i < parsed.length; i++) {
	    	  $("#man-pendingReqTable").find('tbody').append( "<tr><td>" + parsed[i].emp_ID + "</td><td>" +
		    		  parsed[i].req_ID + "</td><td>" + parsed[i].sub_date + "</td><td> " +
		    		  parsed[i].req_type + "</td> <td> " + parsed[i].req_amount + "</td><td>" + parsed[i].req_status + "</td></tr>" );
	      }

	    }
	  };
	  allPendingXHR.open("get", "http://localhost:8080/Project1ERS/getAllPending");
	  allPendingXHR.send();
	
	
	
	
	
});

















var filterById = document.getElementById('id-filter-btn');
filterById.addEventListener("click", function() {
	console.log("button was clicked");
	
	//var rowCount = $("#man-pendingReqTable tr").length - 1;
	//console.log(rowCount);
	
	var managerTable = document.getElementById("man-pendingReqTable");
	//console.log()
	var rowCount = managerTable.rows.length - 1;
	
	for(var i = rowCount; i > 0; i--) {
		managerTable.deleteRow(i);
		console.log(i);
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	//AJAX
	
	
	var allRequestXHR = new XMLHttpRequest();
	  allRequestXHR.onreadystatechange = function() {
		    if (allRequestXHR.readyState == 4 && allRequestXHR.status == 200) {
		      //console.log(allRequestXHR.responseText);
		      //allReqJSON = JSON.parse(allRequestXHR.responseText);
		      //console.log(parsed);allResolvedXHR
		      //console.log(parsed[0]);
		      
		      //console.log(parsed.length);
		      var allReq = JSON.parse(allRequestXHR.responseText);
		      console.log(allReq);
		      
		      
		      
		      for(let i = 0; i < allReq.length; i++) {
		    	  
		    	  //only show request from certain id
		    	  if(allReq[i].emp_ID == $("#id-Filter").val()) {
		    		  //console.log("afasdadadasd");
		    		  console.log($("#id-Filter").val());
		    		  
			    	  $("#man-pendingReqTable").find('tbody').append( "<tr><td>" + allReq[i].emp_ID + "</td><td>" +
			    			  allReq[i].req_ID + "</td><td>" + allReq[i].sub_date + "</td><td> " +
			    			  allReq[i].req_type + "</td> <td> " + allReq[i].req_amount + "</td><td>" +
			    			  allReq[i].req_status + "</td><td>" + allReq[i].req_man_id + " </td><td>" + allReq[i].resolvedDate + "</td></tr>" );
		    		  
		    	  }
		    	  
//		    	  
//		    	  $("#man-pendingReqTable").find('tbody').append( "<tr><td>" + allReq[i].emp_ID + "</td><td>" +
//		    			  allReq[i].req_ID + "</td><td>" + allReq[i].sub_date + "</td><td> " +
//		    			  allReq[i].req_type + "</td> <td> " + allReq[i].req_amount + "</td><td>" +
//		    			  allReq[i].req_status + "</td><td>" + allReq[i].req_man_id + " </td><td>" + allReq[i].resolvedDate + "</td></tr>" );
		      }

		    }
		  };
		  allRequestXHR.open("get", "http://localhost:8080/Project1ERS/AllRequest");
		  allRequestXHR.send();

	
	
	
});






	











