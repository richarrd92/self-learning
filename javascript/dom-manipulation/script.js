const input = document.getElementById("itemInput");
const addItemButton = document.getElementById("addItem-btn");
const itemList = document.getElementById("itemList");

addItemButton.addEventListener("click", () => {
  const item = input.value.trim(); // Get input value, trim spaces
  if (!item) return console.log("Please enter a value"); // Prevent empty input

  const liDiv = document.createElement("div"); // Wrapper div
  liDiv.classList.add("li-div");

  const li = document.createElement("li"); // List item
  li.textContent = item;

  const deleteButton = document.createElement("button"); // Delete button
  deleteButton.classList.add("delete-btn");
  deleteButton.textContent = "Delete";
  deleteButton.addEventListener("click", () => itemList.removeChild(liDiv)); // Remove item

  liDiv.append(li, deleteButton); // Append list item & button
  itemList.appendChild(liDiv); // Add to list

  input.value = ""; // Clear input
});
