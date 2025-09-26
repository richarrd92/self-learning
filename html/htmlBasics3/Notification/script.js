let toastBox = document.getElementById("toastBox");
let successMsg =
  '<i class="fa-solid fa-circle-check"></i> Successfully submitted';
let errorMsg = '<i class="fa-solid fa-circle-xmark"></i> Please fix the error';
let invalidMsg =
  '<i class="fa-solid fa-circle-exclamation"></i> Invalid input! Check again!';

function showToast(msg) {
  let toast = document.createElement("div"); // create div
  toast.classList.add("toast"); // assign classname to div
  toast.innerHTML = msg;
  toastBox.appendChild(toast);

  if (msg.includes("error")) {
    toast.classList.add("error");
  }

  if (msg.includes("Invalid")) {
    toast.classList.add("invalid");
  }

  // remove notification after 5 sec
  setTimeout(() => {
    toast.remove();
  }, 5000);
}
