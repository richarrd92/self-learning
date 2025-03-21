const container = document.querySelector(".game-container");
const resetButton = document.querySelector(".reset-button");
let drawing = false; // Track whether the mouse button is held down

container.addEventListener("mousedown", () => {
  drawing = true; // Start drawing
});

container.addEventListener("mouseup", () => {
  drawing = false; // Stop drawing when the button is released
});

container.addEventListener("mouseleave", () => {
  drawing = false; // Stop drawing if the mouse leaves the container
});

container.addEventListener("mouseenter", (e) => {
  if (e.buttons === 1) {
    drawing = true; // Start drawing if the mouse button is held down
  }
});

container.addEventListener("mousemove", (e) => {
  if (drawing) {
    drawDot(e);
  }
});

function drawDot(e) {
  const rect = container.getBoundingClientRect(); // Get container position
  const x = e.clientX - rect.left; // Mouse X relative to container
  const y = e.clientY - rect.top; // Mouse Y relative to container

  // Ensure dots stay within the container
  if (x >= 0 && x <= rect.width - 8 && y >= 0 && y <= rect.height - 8) {
    const dot = document.createElement("div");
    dot.classList.add("dot");

    // Dot styling
    dot.style.position = "absolute";
    dot.style.width = "8px";
    dot.style.height = "8px";
    dot.style.backgroundColor = "white";
    dot.style.borderRadius = "50%";
    dot.style.pointerEvents = "none";

    // Position inside container
    dot.style.left = `${x}px`;
    dot.style.top = `${y}px`;

    container.appendChild(dot);
  }
}

function clearCanvas() {
  container.innerHTML = ""; // Remove all child elements (dots)
}

resetButton.addEventListener("click", () => {
  clearCanvas();
});
