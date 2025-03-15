// Function to randomly generate computer's choice
function getComputerChoice() {
  const computerChoices = ["rock", "paper", "scissors"];
  return computerChoices[Math.floor(Math.random() * 3)];
}

// Select all player buttons
const playerButtons = document.querySelectorAll(".player-btn");

// Variables to store the choices of both players and the current game mode
let comp = "";
let player1 = "";
let player2 = "";
let gameMode = ""; // Game mode is empty initially

// Function to get the player's choice from the button click
function getPlayerChoice(event) {
  return event.target.dataset.choice; // Get the choice from the clicked button's data attribute
}

// Function to check the winner based on both players' choices
function checkWinner() {
  const message = document.querySelector(".game-message p"); // Select the <p> inside the .game-message div
  const gameMessageDiv = document.querySelector(".game-message"); // The div itself

  // Display the game message div
  gameMessageDiv.style.display = "block";

  if (gameMode === "humanVsComputer") {
    // Determine winner between human and computer
    if (comp === player1) {
      console.log("It's a tie!");
      message.style.color = "black"; // Default color for a tie
      message.textContent = "It's a tie!";
    } else if (
      (player1 === "rock" && comp === "scissors") ||
      (player1 === "paper" && comp === "rock") ||
      (player1 === "scissors" && comp === "paper")
    ) {
      console.log("Player wins!");
      message.style.color = "green"; // Green for player win
      message.textContent = "Player wins!";
    } else {
      console.log("Computer wins!");
      message.style.color = "red"; // Red for computer win
      message.textContent = "Computer wins!";
    }
  } else if (gameMode === "humanVsHuman") {
    // Determine winner between two players
    if (player1 === player2) {
      console.log("It's a tie!");
      message.style.color = "black"; // Default color for a tie
      message.textContent = "It's a tie!";
    } else if (
      (player1 === "rock" && player2 === "scissors") ||
      (player1 === "paper" && player2 === "rock") ||
      (player1 === "scissors" && player2 === "paper")
    ) {
      console.log("Player 1 wins!");
      message.style.color = "green"; // Green for player 1 win
      message.textContent = "Player 1 wins!";
    } else {
      console.log("Player 2 wins!");
      message.style.color = "red"; // Red for player 2 win
      message.textContent = "Player 2 wins!";
    }
  }

  // Reset choices for the next round
  comp = player1 = player2 = "";
}

// Add event listeners to player buttons
playerButtons.forEach((button) => {
  button.addEventListener("click", (event) => {
    // For Human vs Human, player 1 and player 2 alternate turns
    if (gameMode === "humanVsHuman") {
      if (!player1) {
        player1 = getPlayerChoice(event);
        console.log("Player 1 chose:", player1);
      } else if (!player2) {
        player2 = getPlayerChoice(event);
        console.log("Player 2 chose:", player2);

        // Determine winner once both players have made a choice
        checkWinner();
      }
    } else if (gameMode === "humanVsComputer") {
      // For Human vs Computer, player 1 makes a choice and computer generates one
      if (!player1) {
        player1 = getPlayerChoice(event);
        console.log("Player chose:", player1);

        // Generate computer choice immediately when player picks
        comp = getComputerChoice();
        console.log("Computer chose:", comp);

        // Determine winner
        checkWinner();
      }
    }
  });
});

// Show the appropriate game mode and hide the mode buttons after one is selected
document.getElementById("human-vs-human").addEventListener("click", () => {
  gameMode = "humanVsHuman";
  document.querySelector("#player-buttons").style.display = "block"; // Show game buttons
  document.querySelector(".game-message").style.display = "none"; // Hide message div initially
  document.getElementById("human-vs-human").style.display = "none"; // Hide mode buttons
  document.getElementById("human-vs-computer").style.display = "none"; // Hide mode buttons
});

document.getElementById("human-vs-computer").addEventListener("click", () => {
  gameMode = "humanVsComputer";
  document.querySelector("#player-buttons").style.display = "block"; // Show game buttons
  document.querySelector(".game-message").style.display = "none"; // Hide message div initially
  document.getElementById("human-vs-human").style.display = "none"; // Hide mode buttons
  document.getElementById("human-vs-computer").style.display = "none"; // Hide mode buttons
});
