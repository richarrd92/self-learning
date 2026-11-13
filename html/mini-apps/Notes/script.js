const notesContainer = document.querySelector(".notes-container");
const createBtn = document.querySelector(".btn");

function showNotes() {
  const notes = localStorage.getItem("notes");
  if (notes) {
    notesContainer.innerHTML = notes;
    attachEventListenersToNotes(); // Attach listeners to existing notes
  }
}

function updateStorage() {
  localStorage.setItem("notes", notesContainer.innerHTML);
}

function createNote() {
  let inputBox = document.createElement("p");
  let img = document.createElement("img");
  inputBox.className = "input-box";
  inputBox.setAttribute("contenteditable", "true");
  img.src = "images/delete.png";

  inputBox.appendChild(img);
  notesContainer.appendChild(inputBox);

  attachEventListenersToNotes(); // Attach listeners to the new note
  updateStorage(); // Update storage when a new note is created
}

function attachEventListenersToNotes() {
  // Attach keyup event to all notes
  document.querySelectorAll(".input-box").forEach((note) => {
    note.onkeyup = updateStorage; // Update storage on keyup
  });
}

// Event listener for creating new notes
createBtn.addEventListener("click", createNote);

// Event listener for deleting notes
notesContainer.addEventListener("click", function (e) {
  if (e.target.tagName === "IMG") {
    e.target.parentElement.remove();
    updateStorage(); // Update storage on deletion
  }
});

document.addEventListener("keydown", (event) => {
  if (event.key === "Enter") {
    document.execCommand("insertLineBreak");
    event.preventDefault();
  }
});

// Initialize notes from localStorage
showNotes();
