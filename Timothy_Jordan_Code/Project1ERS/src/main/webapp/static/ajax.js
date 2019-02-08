/**
 * 
 */



let btn = document.getElementById("ajaxBtn");

btn.addEventListener("click", function() {
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function() {
    if (xhr.readyState == 4 && xhr.status == 200) {
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

      var parsed = JSON.parse(xhr.responseText);
      console.log(parsed);

      //jr.innerHTML = parsed[0].filename;
      //console.log(xhr.responseText + " " +  xhr.readyState + " " +  xhr.status);
    }
  };
  xhr.open("get", "http://localhost:8080/Project1ERS/info");
  xhr.send();
});
