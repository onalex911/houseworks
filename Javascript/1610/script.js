let numBoxes;
let maxDelay = 1000;
while(true){
      numBoxes = prompt("Введите количество блоков (3-100): ");
      if(numBoxes< 3 || numBoxes > 100){
            alert("Введено нерекомендуемое количество блоков!");
      }else{
            break;
      }
}
let timeOverall;
while(true){
      timeOverall = prompt("Введите время (в секундах), за которое вы уберете все блоки: ");             
      if(timeOverall < 1){
            alert("Введено неверное время!");
      }else{
            break;
      }
}

let boxes = document.getElementById('boxes');
boxes.innerText = numBoxes;

var boxLeft = numBoxes;
let sideSize = 100;     //размер стороны квадрата спрайта
let margin = 20;        //граница поля
let headHeight = 50 + 2*margin;   //высота заголовочного поля

var finished = false;
let timeArr = getTimeArr(timeOverall);

let minX = 0;
let minY = headHeight;
let maxX = document.documentElement.clientWidth-sideSize;
let maxY = document.documentElement.clientHeight-sideSize;

let hoursSpan = document.querySelector('#hours');
hoursSpan.innerHTML = String(timeArr.hours).padStart(2,'0');
let minsSpan = document.querySelector('#mins');
minsSpan.innerHTML = String(timeArr.mins).padStart(2,'0');
let secsSpan = document.querySelector('#secs');
secsSpan.innerHTML = String(timeArr.secs).padStart(2,'0');


let gameField = document.getElementById('gameField');
gameField.style.setProperty('height',document.documentElement.clientHeight - headHeight);
// gameField.style.setProperty('margin',margin);

for (let index = 0; index < numBoxes; index++) {      
      drawBox(index+1,gameField,getRand(minX,maxX),getRand(minY,maxY));      
}

let idOverall = setInterval(() => {        
      console.log(boxLeft);
      if(--timeOverall < 0
             || finished || !boxLeft){
            clearInterval(idOverall);
            clearInterval(idMoving);  
            finished = true;
            if(!boxLeft){
                  alert("Поздравляем! Вы выиграли!\nНажмите F5 для новой игры.");
            }else{
                  alert("Вы проиграли!");
            }
      }else{   
            let timeArr = getTimeArr(timeOverall)
            hoursSpan.innerHTML = String(timeArr.hours).padStart(2,'0');
            minsSpan.innerHTML = String(timeArr.mins).padStart(2,'0');
            secsSpan.innerHTML = String(timeArr.secs).padStart(2,'0');      
      }
}, 1000);

let idMoving = setInterval(() =>{
      let boxArr = document.getElementsByClassName('box');
      let maxX = document.documentElement.clientWidth-sideSize;
      let maxY = document.documentElement.clientHeight-sideSize;
      for (let index = 0; index < boxArr.length; index++) {
            const element = boxArr[index];
            element.style.left = getRand(minX,maxX)+'px';
            element.style.top = getRand(minY,maxY)+'px';
            element.style.setProperty('transition',getRand(100,maxDelay*5)+'ms');

      }
},maxDelay);

gameField.addEventListener('click', function(){
      if(event.target.classList.contains('box')){
            let boxDel = document.getElementById(event.target.id);
            boxDel.remove();
            boxLeft--;
            boxes.innerText = boxLeft;
      }
});



function drawBox(num,cont,x = 0,y = 0,side = sideSize){
      let box = document.createElement('div');
      box.className = "box";
      box.id = "b_"+num;
      box.style.setProperty('width',side+'px');
      box.style.setProperty('height',side+'px');
      
      let red = getRand(0,255);
      let green = getRand(0,255);
      let blue = getRand(0,255);
      let color = (red+green+blue)/3 > 130 ? 'black' : 'white';
      box.style.setProperty('color',color);
      box.style.setProperty('background-color',`rgb(${red},${green},${blue})`);
      box.style.setProperty('top',y+'px');
      box.style.setProperty('left',x+'px');
      box.innerText = num;
      cont.appendChild(box);
      
}

function getTimeArr(timeAll){
      let timeArr = {
            hours:0,
            mins:0,
            secs:0
      }
      
      timeArr.hours = Math.floor(timeAll/3600);
      timeArr.mins = Math.floor((timeAll%3600)/60);
      timeArr.secs = timeAll%60;      
      return timeArr;      
}

function getRand(min = 0, max = 1000) {
      return Math.round(Math.random() * 10000 % (max - min) + min);
}