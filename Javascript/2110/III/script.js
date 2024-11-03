let settings = {
      "bold":false,
      "italic":false,
      "underline":false,
      "align":"" // "left","right","justify"
}

let container = document.querySelector(".container");
let output = document.getElementById("output");

container.addEventListener('click',function(e){      
      for(key in settings){
            if(e.target.id.substring(0,key.length+3) == `do-${key}`){

                  if(key == 'align'){
                        settings[key] = e.target.id.split("-")[2]; 
                  }else{
                        settings[key] = document.getElementById(`do-${key}`).checked;
                  }

            }else if(e.target.id == "show-text"){
                  let text = document.getElementById("textarea").value;

                  if(text.trim() == ""){
                        alert("Text is empty!");
                        break;
                  }else{
                        output.innerText = text;

                        output.style.setProperty("font-weight",settings.bold ? "bold":"normal");
                        output.style.setProperty("font-style",settings.italic ? "italic":"normal");
                        output.style.setProperty("text-decoration",settings.underline ? "underline":"none");
                        output.style.setProperty("text-align",settings.align);
                        
                        document.querySelector(".set-style").style.display = "none";
                        document.querySelector(".result").style.display = "block";
                  }

            }else if(e.target.id == "go-styles"){
                  output.innerText = "";
                  document.querySelector(".set-style").style.display = "block";
                  document.querySelector(".result").style.display = "none";
            }
      }
});