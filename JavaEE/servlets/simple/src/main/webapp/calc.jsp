<%--
  Created by IntelliJ IDEA.
  User: onale
  Date: 30.12.2024
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator</title>
    <link rel="stylesheet" href="css/style_calc.css">
    <script src="js/script_main.js" defer></script>
    <script src="js/script_calc.js" defer></script>
</head>
<body>
<div class="container">
    <h1>Simple Calculator</h1>
    <form onsubmit="event.preventDefault();" method="post">
        <div class="calculator">
            <div class="top">
                <div class="operands">
                    <div class="operand"><label for="val-x">Value X:</label><input type="text" id="val-x" class="num-val" placeholder="0"></div>
                    <div class="operand"><label for="val-y">Value Y:</label><input type="text" id="val-y" class="num-val" placeholder="0"></div>
                    <div class="operand"><label for="result">Result:</label><input type="text" id="result" disabled></div>
                </div>
                <div class="controls">
                    <ul class="controls-list">
                        <li><input type="radio" name="operation" id="add"><label for="add" title="X + Y">➕</label></li>
                        <li><input type="radio" name="operation" id="sub"><label for="sub" title="X - Y">➖</label></li>
                        <li><input type="radio" name="operation" id="mult"><label for="mult" title="X * Y">✖</label></li>
                        <li><input type="radio" name="operation" id="divd"><label for="divd" title="X / Y">➗</label></li>
                        <li><input type="radio" name="operation" id="perc"><label for="perc" title="X percent of Y">%</label></li>
                        <li><input type="radio" name="operation" id="pow"><label for="pow" title="X to the power of Y">X<sup>Y</sup></label></li>
                    </ul>

                    <div class="bottom">
                        <button id="but-reset">&nbsp;C&nbsp;</button>
                        <button id="but-equals">&nbsp;=&nbsp;</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <a href="index.jsp">&lt;&lt; Go back</a>
</div>
</body>
</html>
