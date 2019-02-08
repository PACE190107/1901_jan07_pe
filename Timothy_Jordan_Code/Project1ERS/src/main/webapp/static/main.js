let pendingBTN = document.querySelector("#pending-req-btn");
let pend_isShowing = false;
let pendingReqCont = document.querySelector("pending_Req_container");

pendingBTN.addEventListener("click", function() {
  if (pend_isShowing) {
    document.querySelector("#pending_Req_container").style.display = "none";
    pend_isShowing = false;
  } else {
    document.querySelector("#pending_Req_container").style.display = "block";
    pend_isShowing = true;
  }
});

let submitBTN = document.querySelector("#submit-req-btn");
let sub_isShowing = false;
submitBTN.addEventListener("click", function() {
  if (sub_isShowing) {
    document.querySelector("#submit_Req_container").style.display = "none";
    sub_isShowing = false;
  } else {
    document.querySelector("#submit_Req_container").style.display = "block";
    sub_isShowing = true;
  }
});

let resolvedBTN = document.querySelector("#resolved_req_btn");
let resolved_isShowing = false;
resolvedBTN.addEventListener("click", function() {
  if (resolved_isShowing) {
    document.querySelector("#resolved_Req_container").style.display = "none";
    resolved_isShowing = false;
  } else {
    document.querySelector("#resolved_Req_container").style.display = "block";
    resolved_isShowing = true;
  }
});
