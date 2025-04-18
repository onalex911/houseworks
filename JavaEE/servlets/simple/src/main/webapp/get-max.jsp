<%--
  Created by IntelliJ IDEA.
  User: onale
  Date: 30.12.2024
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Getting maximum</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="js/script_main.js" defer></script>
</head>
<body>
<div class="container">
    <form onsubmit="event.preventDefault(); sendData();" method="post">
        <div class="ui-elems">
            <h3 class="ui-elem">Enter three numbers and press the button</h3>
            <div class="ui-elem"><input id="a" name="a" class="number-field" placeholder="000"></div>
            <div class="ui-elem"><input id="b" name="b" class="number-field" placeholder="000"></div>
            <div class="ui-elem"><input id="c" name="c" class="number-field" placeholder="000"></div>
            <div class="ui-elem">
                <input type="radio" id="oper-max" name="oper" value="max"><label for="oper-max">Maximum</label>
                <input type="radio" id="oper-min" name="oper" value="min"><label for="oper-min">Minimum</label>
                <input type="radio" id="oper-avg" name="oper" value="avg"><label for="oper-avg">Average</label>
            </div>
            <div class="ui-elem"><button name="get-maximum" type="submit">Get value!</button></div>
            <div class="ui-elem" id="result"></div>
        </div>
    </form>
    <a href="index.jsp">&lt;&lt; Go back</a>
</div>
<script>
    let inputField = document.getElementsByClassName("number-field");
    console.log(inputField.length);

    for (let i = 0; i < inputField.length; i++) {

        inputField[i].addEventListener('input', function () {
            // Удаляем все символы, кроме цифр и знака минус
            this.value = this.value.replace(/[^0-9-]/g, '');

            // Разрешаем только один минус в начале строки
            if (this.value.indexOf('-') !== -1 && this.value.indexOf('-') !== 0) {
                this.value = this.value.replace(/-/, '');
            }

            // Если минус в начале, то разрешаем только один
            if (this.value.split('-').length - 1 > 1) {
                this.value = this.value.replace(/-/, '');
            }
            //inputField[i].innerHTML = this.value;
        });
    }
    async function sendData() {
        const a = document.getElementById("a").value;
        const b = document.getElementById("b").value;
        const c = document.getElementById("c").value;
        const oper = document.querySelector('input[name="oper"]:checked').value;
        const operText = oper == "max" ? "Maximum" : (oper == 'min' ? "Minimum" : "Average");

        const jsonData = JSON.stringify({a,b,c,oper});
         console.log(jsonData);
        let data = await SendJSONData(jsonData,'get-max-servlet');
        console.log(data);
        let output =  isEmpty(data.error) ? operText + ' value is: <b>' + data.resVal + '</b>': 'ERROR: ' + data.error;

        document.getElementById("result").innerHTML = output;


    }


</script>
</body>
</html>
