
window.onload= function(){
var xmlobject = new XMLHttpRequest();
    xmlobject.onreadystatechange= function(){
        if(xmlobject.readyState==4&&xmlobject.status==200){




            var parsed = JSON.parse(xmlobject.responseText);
            console.log(parsed);
        }

        };
    
    xmlobject.open("get", "http://localhost:8080/ERS/viewRequests");
    xmlobject.send();
    }
