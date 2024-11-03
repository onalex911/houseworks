let body = document.getElementsByTagName('body')[0];
let container = document.createElement('div');
container.className = 'container';
container.style.setProperty('display','flex');
body.appendChild(container);

drawBlock();
drawBlock();

function drawBlock(){
      let block = document.createElement('div');
      block.className = 'block';
      block.style.setProperty('width','375px');
      block.style.setProperty('margin','20px 0 0 20px');
      container.appendChild(block);

      let h1 = document.createElement('h1');
      let image = document.createElement('img');      
      let para = document.createElement('p');
      let xref = document.createElement('a');
      
      block.appendChild(h1);
      h1.innerText = "What is lorem ipsum?";
      
      block.appendChild(image);
      image.setAttribute('src','pond.png');
      
      block.appendChild(para);
      para.style.setProperty('font-size','large');
      para.style.setProperty('color','red');
      para.innerText = "Lorem ipsum dolor sit amet consectetur "+
            "adipisicing elit. Odit ea nemo explicabo provident nam, "+
            "eligendi amet aperiam architecto, neque necessitatibus "+
            "aliquid laudantium tenetur repellat animi "+
            "consequuntur? Suscipit reiciendis dolorum beatae? ";
            
      para.appendChild(xref);
      xref.setAttribute('href','#');
      xref.innerHTML = "More&#133;";
}

