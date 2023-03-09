// Get the modal
var modal_ruf = document.getElementById("myModal_ruf");
var modal_test = document.getElementById("myModal_test");
var modal_desk = document.getElementById("myModal_desk");

// Get the button that opens the modal
var btn_ruf = document.getElementById("myBtn_ruf");
var btn_test = document.getElementById("myBtn_test");
var btn_desk = document.getElementById("myBtn_desk");

// Get the <span> element that closes the modal
var span_ruf = document.getElementsByClassName("close")[0];
var span_test = document.getElementsByClassName("close")[1];
var span_desk = document.getElementsByClassName("close")[2];

// When the user clicks on the button, open the modal
btn_ruf.onclick = function() {
  modal_ruf.style.display = "block";
}
btn_test.onclick = function() {
  modal_test.style.display = "block";
}
btn_desk.onclick = function() {
  modal_desk.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span_ruf.onclick = function() {
  modal_ruf.style.display = "none";  
}
span_test.onclick = function() {
  modal_test.style.display = "none";  
}
span_desk.onclick = function() {
  modal_desk.style.display = "none";  
}



// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal_ruf) {
    modal_ruf.style.display = "none";    
  }
  if (event.target == modal_test) {    
    modal_test.style.display = "none";
  }
  if (event.target == modal_desk) {    
    modal_desk.style.display = "none";
  }

}