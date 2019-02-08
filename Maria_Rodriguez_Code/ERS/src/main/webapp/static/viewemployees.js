$(document).ready(function() {
	
	
	console.log("loaded");
	var xmlobject = new XMLHttpRequest();
	    xmlobject.onreadystatechange = function(){
	        if((xmlobject.readyState  == 4) && (xmlobject.status == 200)){

	        		console.log("in the if");


	            var parsed = JSON.parse(xmlobject.responseText);
	            console.log(parsed);
	            
	          /////////////////////////////////////////////////////////////////////////////            
	            var viewReqTable = document.getElementById("viewEmployees");
	            
		        console.log(viewReqTable);
	            for(var i = 0; i < parsed.length; i++) {
	            	$("#viewEmployees").find('tbody').append("<tr><td>" 
	            			            +parsed[i].e_id+            			           
	            			"</td><td>"+parsed[i].firstName + 
	            			"</td><td>"+ parsed[i].lastName + 
	            			"</td><td>" + parsed[i].email +
	            					"</td></tr>");
	            }
	            }

	        };    
	    
	    
	        
	        
	        
	        
	        
	        
	    xmlobject.open("get", "http://localhost:8080/ERS/viewEmployees");
	    xmlobject.send();
	    
	    

	
	
	
	
});
	