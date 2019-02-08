$(document).ready(function() {
	
	
	console.log("loaded");
	var xmlobject = new XMLHttpRequest();
	    xmlobject.onreadystatechange = function(){
	        if((xmlobject.readyState  == 4) && (xmlobject.status == 200)){

	        		console.log("in the if");


	            var parsed = JSON.parse(xmlobject.responseText);
	            console.log(parsed);
	            
	        
//	            for(let i=0; i<parsed.length;i++){
//	           	$("#viewRequests").find('tbody').append(
//	            	"<tr><td>" + parsed[i].emp_ID + "</td><td>" + parsed[i].date_received  + "</td><td>" + parsed[i]).type + "</td><td> " + parsed[i].amount  + " </td><td>" + parsed[i].description + "</td><td>" + parsed[i].status + "</td></tr>");
//	            
//	            };
//	            
//	        }
	            
	            var viewReqTable = document.getElementById("#viewResolvedRequests");
	            
	            for(var i = 0; i < parsed.length; i++) {
	            	$("#viewRequests").find('tbody').append("<tr><td>" +
	            			             parsed[i].req_id+
	            			"</td><td>"+ parsed[i].e_id + 
	            			"</td><td>"+ parsed[i].date_received + 
	            			"</td><td>" + parsed[i].type +
	            			"</td><td>"+ parsed[i].amount +
	            			"</td><td>" +parsed[i].description + 
	            			"</td><td>" +  parsed[i].status+
	            					"</td></tr>");
	            }
	            }

	        };    
	    
	        
	        
	        
	        
	        
	        
	    xmlobject.open("get", "http://localhost:8080/ERS/viewResolvedRequests");
	    xmlobject.send();
	    
	    

	
	
	
	
});
	