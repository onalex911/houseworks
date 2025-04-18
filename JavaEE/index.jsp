<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Max Number Finder</title>
    <script>
        function findMaxNumber() {
            const num1 = document.getElementById("num1").value;
            const num2 = document.getElementById("num2").value;
            const num3 = document.getElementById("num3").value;
            const xhr = new XMLHttpRequest();
            xhr.open("POST", "maxNumber", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onload = function () {
                if (xhr.status === 200) {
                    const response = JSON.parse(xhr.responseText);
                    document.getElementById("result").innerText = "Максимальное число: " + response.maxNumber;
                }
            };
            xhr.send("num1=" + num1 + "&num2=" + num2 + "&num3=" + num3);
        }
    </script>
</head>
<body>
    <h1>Введите три числа</h1>
    <form onsubmit="event.preventDefault(); findMaxNumber();">
        Число 1: <input type="text" id="num1" name="num1"><br>
        Число 2: <input type="text" id="num2" name="num2"><br>
        Число 3: <input type="text" id="num3" name="num3"><br>
        <input type="submit" value="Получить максимум">
    </form>
    <div id="result"></div>
</body>
</html>
