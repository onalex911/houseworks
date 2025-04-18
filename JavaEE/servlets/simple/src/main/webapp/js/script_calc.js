const MAXDIGITS = 10;


document.querySelector(".bottom").addEventListener('click',async function(e){
    if(e.target.id == 'but-equals'){
        console.log(`Pressed Equals`);
        await sendCalculatorData();
    }
    else if(e.target.id == 'but-reset'){
        document.querySelectorAll(".operand input").forEach(elem => {
            elem.value = '';
        });
    }
});

let numberFields = document.getElementsByClassName("num-val");
for (let i = 0; i < numberFields.length; i++) {

    allowNumbers(numberFields[i]);
    console.log(i);

}

function allowNumbers(obj){
    let isMin = 0;
    let isPoint = 0;

    obj.addEventListener('input', function () {
        // Удаляем все символы, кроме цифр, знака минус и точки
        this.value = this.value.replace(/[^0-9.-]/g, '');

        // Убираем ошибочно введенные последовательные минусы
        const partsMin = this.value.split('-');
        if(partsMin.length > 2){
            this.value = "-";
            for (let i = 1; i < partsMin.length; i++) {
                this.value += partsMin[i];
            }
        }
        // Разрешаем только один минус в начале строки
        if (this.value.indexOf('-') !== -1 && this.value.indexOf('-') !== 0) {
            this.value = this.value.replace(/-/, '');
        }
        isMin = this.value.indexOf("-") === 0 ? 1 : 0;

        // Запрещаем более одной точки
        const parts = this.value.split('.');
        if (parts.length > 2) {
            this.value = this.value.replace(/\.+$/, '');
        }

        // Убедитесь, что если есть точка, она находится в правильном месте
        if (this.value.length > 1 && this.value.indexOf('-') !== 0) {
            if (this.value.indexOf('.') !== -1) {
                const integerPart = this.value.split('.')[0];
                const decimalPart = this.value.split('.')[1].replace(/[^0-9]/g, '');

                // Если в дробной части больше одной цифры, то оставляем только первую
                this.value = integerPart + '.' + decimalPart;
            }
        }
        isPoint = this.value.indexOf(".") >= 0 ? 1 : 0;

        // Ограничиваем ввод количества цифр
        if(this.value.length - isMin - isPoint > MAXDIGITS){
            alert(`Введено большое количество цифр (больше ${MAXDIGITS})`);
            this.value = this.value.substring(0,MAXDIGITS + isMin + isPoint);
        }
    });
}

async function sendCalculatorData(){
    let oper;
    if(document.querySelector('input[name="operation"]:checked') !== null) {
        oper = document.querySelector('input[name="operation"]:checked').id;
    }else{
        alert("Вы не выбрали операцию!");
        return;
    }

    let a = document.getElementById("val-x").value;
    if(a === "") a = 0;

    let b = document.getElementById("val-y").value;
    if(b === "") b = 0;

    const jsonData = JSON.stringify({a,b,oper});
    console.log(jsonData);
    let answer = await SendJSONData(jsonData,'calc-servlet');

    console.log(answer);
    let output =  isEmpty(answer.error) && !isEmpty(answer.resVal)  ? answer.resVal : 'ERROR: ' + answer.error;

    document.getElementById("result").value = output;

}



