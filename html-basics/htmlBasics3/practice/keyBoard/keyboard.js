
document.addEventListener('keydown', function(event){
    var output = document.querySelector('#output2')
    if ( event.keyCode == '77' && event.ctrlKey){
        output.style.border = "5px solid black"
    } else if (event.keyCode == '77' && event.altKey){
        output.style.border = "5px dashed black"
    }
})
