const passwordBox = document.getElementById("password");
const length = 16; // length of password
const generatePassword = document.getElementById("generatePassword");
const copyThat = document.getElementById("copyIcon");

const upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
const lowerCase = "abcdefghijklmnopqrstuvwxyz";
const number = "0123456789";
const symbol = "!@#$%^&*()_+=-</>~";
const allChars = upperCase + lowerCase + number + symbol;

// generate password
function createPassword() {
    let password = "";
    password += upperCase[Math.floor(Math.random() * upperCase.length)];
    password += lowerCase[Math.floor(Math.random() * lowerCase.length)];
    password += number[Math.floor(Math.random() * number.length)];
    password += symbol[Math.floor(Math.random() * symbol.length)];

    while (length > password.length) {
        password += allChars[Math.floor(Math.random() * allChars.length)];
    }
    passwordBox.value = password;
}

// copy password
function copyPassword() {
    if (passwordBox.value.length > 0) {
      passwordBox.select();
      document.execCommand("copy");
    } 
}

// click of icon 
copyThat.onclick = copyPassword;

// click of button
generatePassword.onclick = createPassword;