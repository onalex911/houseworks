let Questions = [
      {
            "phrase": 'How many letters are there in the word "Hello"?',
            "vars": [
                  {
                        "val":4,
                        "correct":false
                  },
                  {
                        "val":5,
                        "correct":true
                  },
                  {
                        "val":6,
                        "correct":false
                  },
            ]
      },
      {
            "phrase": 'How many letters are there in the word "World"?',
            "vars": [
                  {
                        "val":5,
                        "correct":true
                  },
                  {
                        "val":6,
                        "correct":false
                  },
                  {
                        "val":4,
                        "correct":false
                  },
            ]
      },
      {
            "phrase": 'How many letters are there in the word "Javascript"?',
            "vars": [
                  {
                        "val":11,
                        "correct":false
                  },
                  {
                        "val":9,
                        "correct":false
                  },
                  {
                        "val":10,
                        "correct":true
                  },
            ]      
      },
      {
            "phrase": 'What is "python"?',
            "vars": [
                  {
                        "val":"a snake",
                        "correct":false
                  },
                  {
                        "val":"a programming language",
                        "correct":false
                  },
                  {
                        "val":"both",
                        "correct":true
                  },
            ]
      }      
];

generateQuestions();
let currentNum = 0;
showQuestion(currentNum);

let answButtons = document.querySelector("#questions-block");
answButtons.addEventListener('click',function(e){
      if(e.target.classList.contains("next")){
            showQuestion(++currentNum);
      }else if(e.target.classList.contains("prev")){
            showQuestion(--currentNum);
      }else if(e.target.classList.contains("finish")){
            let qBlocks = document.querySelectorAll(".question");
            qBlocks[qBlocks.length-1].style.setProperty("display","none");
            let resBlock = document.getElementById("result-block");
            resBlock.innerHTML = `Result: <b>${getResult()}</b> correct answers to ${Questions.length} questions`;
            resBlock.style.setProperty("display","block");
      }
});

function generateQuestions(){
      let qBlock = document.getElementById("questions-block");
      for (let index = 0; index < Questions.length; index++) {
            const element = Questions[index];
            
            let question = document.createElement("div");
            question.className = "question"; 
            qBlock.appendChild(question);
            let questionPhrase = document.createElement("div");
            questionPhrase.className = "question-phrase";
            questionPhrase.innerHTML = `<b>${index+1})</b> ${element.phrase}`;
            question.appendChild(questionPhrase);

            let answerVars = document.createElement("ul");
            answerVars.className = "answer-vars";
            question.appendChild(answerVars);

            let answerVarsArr = element.vars;
            for (let index2 = 0; index2 < answerVarsArr.length; index2++) {
                  const element2 = answerVarsArr[index2];
                  let answVarLi = document.createElement("li");
                  answVarLi.innerHTML=`<input type="radio" name="answ-rb_${index}" id="answ_${index}_${index2}"><label for="answ_${index}_${index2}">${element2.val}</label>`;
                  answerVars.appendChild(answVarLi);
            }

            let buttons = document.createElement("div");
            buttons.className = "question-button";
            question.appendChild(buttons);
            let prevBut = `<button class="prev answ-button" id="prev-but_${index}">Previous</button>`;
            let nextBut = `<button class="next answ-button" id="next-but_${index}">Next</button>`;
            let finishBut = `<button class="finish answ-button" id="finish-but">Finish</button>`;
            if(index == 0){
                  buttons.innerHTML = nextBut;
            }else if(index == Questions.length - 1){
                  buttons.innerHTML = prevBut+finishBut;
            }else{
                  buttons.innerHTML = prevBut+nextBut;
            }
      }
}

function showQuestion(num){
      let questionDivs = document.querySelectorAll(".question");
      for (let index = 0; index < questionDivs.length; index++) {
            const element = questionDivs[index];
           if(index == num){
                  element.style.setProperty("display","block");
            }else{
                 element.style.setProperty("display","none");

           }
      }
}

function getResult(){
      out = 0;
      for (let i = 0; i < Questions.length; i++) {
            const element1 = Questions[i];
            for (let j = 0; j < element1.vars.length; j++) {                  
                  const element2 = element1.vars[j];
                  if(element2.correct){
                        if(document.getElementById(`answ_${i}_${j}`).checked)
                              out++;    
                  }
            }            
      }
      return out;
}
