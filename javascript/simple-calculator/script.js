// --- Calculator State Variables ---

let currentInput = ""; // Stores the number currently being typed (e.g., "42")
let storedValue = null; // Stores the first operand when an operator is pressed
let currentOperator = null; // Stores the selected operator (+, -, *, /)
let shouldResetDisplay = false; // Tells the calculator to reset the display on next digit input

// Get the calculator screen element (where the result is shown)
const display = document.getElementById("display");

// Get all button elements and add click event listeners to them
const buttons = document.querySelectorAll("button");
buttons.forEach((button) => {
  button.addEventListener("click", () => {
    handleButtonClick(button.innerText); // When clicked, pass button text (e.g., "1", "+") to the handler
  });
});


// --- Main Function to Handle keyboard inputs ---

window.addEventListener("keydown", (event) => {
  const key = event.key;

  // Allow digits 0-9, operators, decimal, Enter, Backspace, and Escape
  if (/\d/.test(key)) {
    // Number keys
    handleButtonClick(key);
  } else if (key === "+" || key === "-" || key === "*" || key === "/") {
    handleButtonClick(key);
  } else if (key === ".") {
    handleButtonClick(key);
  } else if (key === "Enter" || key === "=") {
    event.preventDefault(); // Prevent form submit or other side effects
    handleButtonClick("=");
  } else if (key === "Backspace") {
    handleButtonClick("DEL");
  } else if (key === "Escape") {
    handleButtonClick("AC");
  }
});
  


// --- Main Function to Handle Button Clicks ---
function handleButtonClick(value) {
  if (isDigit(value)) {
    handleDigit(value); // If the button is a number, process it as a digit
  } else if (isOperator(value)) {
    handleOperator(value); // If the button is an operator, process the operation
  } else if (value === "=") {
    calculate(); // If the equals button is pressed, compute the result
  } else if (value === "AC") {
    clear(); // If "C" is pressed, reset the calculator
  } else if (value === "DEL") {
    handleDelete(); // If "DEL" is pressed, delete the last digit
  } else if (value === ".") {
    handleDecimal(); // If "." is pressed, add a decimal point
  }
}

// Handle "DEL" button press
function handleDelete() {
  if (currentInput === "") return;
  currentInput = currentInput.slice(0, -1); // Remove the last digit
  updateDisplay(); // Update the calculator screen
}

// Handle "." button press
function handleDecimal() {
  if (currentInput.includes(".")) return; // Don't allow multiple decimal points
  currentInput += "."; // Add a decimal point
  updateDisplay(); // Update the calculator screen
}

// Helper function to check if a button is a digit (0–9)
function isDigit(value) {
  return /\d/.test(value); // \d means digit; this checks if the value is a number
}

// Helper function to check if a button is an operator (+, -, *, /)
function isOperator(value) {
  return value === "+" || value === "-" || value === "*" || value === "/";
}

// Handle digit button press (build the number string)
function handleDigit(digit) {
  if (shouldResetDisplay) {
    // Start new number after an operator or calculation
    currentInput = digit;
    shouldResetDisplay = false;
  } else {
    // Add digit to existing input
    currentInput += digit;
  }
  updateDisplay(); // Update the calculator screen
}

// Handle operator button press
function handleOperator(operator) {
  if (currentInput === "") return; // If nothing is typed, ignore operator press

  if (storedValue !== null && currentOperator !== null) {
    // If there's already an operation pending, calculate it first
    calculate();
  } else {
    // Otherwise, store the current number as the first operand
    storedValue = parseFloat(currentInput);
  }

  currentOperator = operator; // Save the operator (+, -, etc.)
  shouldResetDisplay = true; // Next number will be a new entry
}

// Perform the calculation when "=" is pressed
function calculate() {
  if (currentOperator === null || currentInput === "") return;

  const a = storedValue; // First number
  const b = parseFloat(currentInput); // Second number
  let result;

  // Decide operation based on the operator stored
  switch (currentOperator) {
    case "+":
      result = a + b;
      break;
    case "-":
      result = a - b;
      break;
    case "*":
      result = a * b;
      break;
    case "/":
      if (b === 0) {
        // Prevent division by 0
        display.innerText = "Error: /0";
        resetState();
        return;
      }
      result = a / b;
      break;
  }

  // Show result on screen
  currentInput = result.toString(); // Convert result to string to display
  updateDisplay();
  resetState(); // Clear stored values and operator for next operation
}

// Reset all calculator state
function clear() {
  currentInput = "";
  storedValue = null;
  currentOperator = null;
  shouldResetDisplay = false;
  updateDisplay(); // Reset display to 0
}

// Reset storedValue and operator, but keep the current input (the result)
function resetState() {
  storedValue = null;
  currentOperator = null;
  shouldResetDisplay = true; // Next digit typed will start a new number
}

// Update the calculator screen
function updateDisplay() {
  // If there's no current input, show 0; otherwise, show the current number
  display.innerText = currentInput === "" ? "0" : currentInput;
}
