const canvas = document.getElementById("myCanvas");
const ctx = canvas.getContext("2d");
let maxLevels = document.getElementById("levels").value;
let branches = document.getElementById("Branches").value;
const startX = canvas.width / 2;
const startY = canvas.height / 2;
const initialLength = 100;
const initialLevel = 0;

function drawBranch(x, y, angle, len, level) {
  if (level < maxLevels) {
    // Convert angle from degrees to radians
    const convertedAngle = (angle * Math.PI) / 180;

    // Calculate the new x, y coordinates
    const newX = x + len * Math.cos(convertedAngle);
    const newY = y + len * Math.sin(convertedAngle);

    // Draw the line
    ctx.beginPath();
    ctx.moveTo(x, y);
    ctx.lineTo(newX, newY);
    ctx.stroke();

    // Array of all angle changes
    const angleChanges = [0, 90, -90];

    // Make recursive calls with;
    // new coordinates, adjusted length, and incremented level
    angleChanges.forEach((adj) => {
      drawBranch(newX, newY, angle + adj, len / 2, level + 1);
    });
  }
}

// array of all angles
const angleArray = [0, 90, -90, 180];

function drawFractal() {
  ctx.clearRect(0, 0, canvas.width, canvas.height); // Clear canvas before drawing
  const branches = document.getElementById("Branches").value; // Get selected number of branches
  for (let i = 0; i < branches; i++) {
    drawBranch(startX, startY, angleArray[i], initialLength, initialLevel);
  }
}

$(document).ready(function () {
  $("#levels").change(function () {
    maxLevels = $(this).val(); // Update maxLevels with selected value
    drawFractal(); // Redraw fractal with new maxLevels
  });

  $("#Branches").change(function () {
    drawFractal(); // Redraw fractal with new number of branches
  });

  drawFractal(); // Initial draw
});
