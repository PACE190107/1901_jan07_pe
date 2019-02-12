$(document).ready(function() {
	
	console.log("loaded");
	var xmlobject = new XMLHttpRequest();
	    xmlobject.onreadystatechange = function(){
	        if((xmlobject.readyState  == 4) && (xmlobject.status == 200)){

	        		console.log("in the if");


	            var parsed = JSON.parse(xmlobject.responseText);
	            console.log(parsed);
	            
           var viewReqTable = document.getElementById("viewPendingRequests");
	            

           //works!
           /////////////////////////////////////////////////////////////////////
                   
         
                console.log(viewReqTable);
	            for(var i = 0; i < parsed.length; i++) {
	            	$("#viewPendingRequests").find('tbody').append("<tr><td>" 
     			                       +parsed[i].seq_id+            			           
	            			"</td><td>"+parsed[i].e_id + 
	            			"</td><td>"+ parsed[i].date_received + 
	            			"</td><td>" + parsed[i].req_type +
	            			"</td><td>"+ parsed[i].amount +
	            			"</td><td>" +parsed[i].req_description + 
	            			"</td><td>" +  parsed[i].status+
	            			"</td><td>" + parsed[i].manager_id+ 
	            					"</td></tr>");
	            }
	            }
	        
	        xmlobject.open("get", "http://localhost:8080/ERS/viewPendingRequests");
    	    xmlobject.send();
	            
};  
   
	    	    
	            
	    	    /////////////////////////////////////////////////////////////////////////////  
	
			
			
			
	
	    	
	        });	
	    	
	    	
	    	