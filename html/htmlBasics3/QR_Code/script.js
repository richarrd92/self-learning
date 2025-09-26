let imgBox = document.getElementById("imgBox");
let qrImage = document.getElementById("qrImage");
let qrText = document.getElementById("qrText");
let btn = document.getElementById("btn")
    
function generateQR(){
    if (qrText.value.length > 0) {
       qrImage.src =
         "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" +
         encodeURIComponent(qrText.value);
    
        imgBox.classList.add("show-img"); 
    } else {
        qrText.classList.add('error');
        setTimeout(() => {
            qrText.classList.remove("error");  
        }, 1000);
    }
}

// Remove the QR image if the text is deleted
qrText.addEventListener("input", () => {
    if (qrText.value.length === 0) {
        imgBox.classList.remove("show-img");
        qrImage.src = ""; // Clear the image source
    }
});

qrText.addEventListener("keydown", (e) =>{
    if (e.key === "Enter") {
        generateQR();
    }
})

btn.addEventListener("click", generateQR);