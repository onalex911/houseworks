let Personal = [
      {
            "firstName":"Mark",
            "lastName":"Zuckerberg",
            "age":34,
            "company":"Facebook",
      },
      {
            "firstName":"Timothy",
            "lastName":"Cook",
            "age":57,
            "company":"Apple",
      },
      {
            "firstName":"Bill",
            "lastName":"Gates",
            "age":62,
            "company":"Microsoft",
      },
      {
            "firstName":"Larry",
            "lastName":"Page",
            "age":45,
            "company":"Google",
      },
      {
            "firstName":"Jeff",
            "lastName":"Bezos",
            "age":60,
            "company":"Amazon",
      },
      {
            "firstName":"Henry",
            "lastName":"Ford",
            "age":161,
            "company":"Ford Motor Company",
      }
];

let delim = '|';


let mainTblHead = document.querySelectorAll('.tbl-main thead td');
let mainTblBody = document.querySelector('.tbl-main tbody');

drawTable(mainTblBody,Personal);

mainTblHead.forEach(element => {
      element.addEventListener('click',function(e){
            if(e.target.classList.contains('thead')){
                  let keyName = e.target.className.split(' ')[1];
                  let cb = document.getElementsByName(keyName)[0];
                  let cbState = cb.checked;
                  sortAndRedrawBy(mainTblBody,keyName,cbState);
                  
                  document.getElementsByName(keyName)[0].checked = !cbState;
            }
      });  
});

function sortAndRedrawBy(tblBodyElement,keyName,cbState){
      let sortedArr = mySort(Personal,keyName,cbState);
      drawTable(tblBodyElement,sortedArr);
}


function drawTable(tblBodyElement,arr){
      deleteTblBody(tblBodyElement);

      for (let index = 0; index < arr.length; index++) {
            const element = arr[index];
            let tr = document.createElement('tr');
            for(key in element){
                  tr.innerHTML += "<td>" + element[key] + "</td>";
            }
            tblBodyElement.appendChild(tr);
      }
}

function deleteTblBody(tblBodyElement){
      let rows = tblBodyElement.querySelectorAll('tr');
      rows.forEach(element => {
          element.remove();  
      });
}

function mySort(array,byKey,reverse = false){
      let arrToSort = [];
      let out = [];
      for (let index = 0; index < array.length; index++) {
            const element = array[index];   
            arrToSort[index] = element[byKey] + delim + getKeysInStr(element,delim);      
      }
      
      if(typeof(array[0][byKey]) == 'number'){
            let res = reverse ? -1 : 1;
            arrToSort.sort(function(a,b){
                  let x = a.split(delim)[0];
                  let y = b.split(delim)[0];
                  return x*res - y*res;
            });
      }else{
            arrToSort.sort(function(a,b){
                  let x = a.toLowerCase();
                  let y = b.toLowerCase();
                  if(x < y) return reverse ? 1 : -1;
                  if(x > y) return reverse ? -1 : 1;
                  return 0;
            });
      }

      for (index = 0; index < array.length; index++) {
            const element2 = arrToSort[index];
           let values = element2.split(delim);
           out[index] = {
            "firstName":values[1],
            "lastName":values[2],
            "age":values[3],
            "company":values[4],
           };
     }
      
     return out;
}

function getKeysInStr(obj,delim){
      let out = '';
      
      for(key in obj){
            out += obj[key] + delim;
      }
      return out.substring(0,out.length-1);
}