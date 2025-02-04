// Get the button element(s) by class name
const btn = document.getElementById("add-to-cart-button");

// Add a click event listener to each button
btn.addEventListener("click", () => {
  // Get the parent div of the button
  btn.style.backgroundColor = "Red";
});
