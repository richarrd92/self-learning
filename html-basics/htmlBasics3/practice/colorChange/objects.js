function changeColor() {
  var color = this.style.backgroundColor;
  if (color === "white") {
    this.style.backgroundColor = "green";
  } else if (color === "green") {
    this.style.backgroundColor = "blue";
  } else if (color === "blue") {
    this.style.backgroundColor = "white";
  }
}

// mouse click event
document.querySelector("#p1").addEventListener("click", changeColor);
document.querySelector("#p2").addEventListener("click", changeColor);
document.querySelector("#p3").addEventListener("click", changeColor);

