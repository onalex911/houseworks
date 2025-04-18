<%--
  Created by IntelliJ IDEA.
  User: onale
  Date: 30.12.2024
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Count vowels/consonants</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="js/script_main.js" defer></script>
    <script src="js/script_vc.js" defer></script>
</head>
<body>
<div class="container">
    <form onsubmit="event.preventDefault(); sendVCData();" method="post">
        <div class="ui-elems">
            <h3 class="ui-elem">Enter text</h3>
            <div class="ui-elem"><textarea id="main-text" name="main-text"></textarea></div>
            <div class="ui-elem"><button name="get-statistics" type="submit">Get statistics!</button></div>
            <div class="ui-elem" id="result"></div>
        </div>
    </form>
    <a href="index.jsp">&lt;&lt; Go back</a>
</div>
</body>
</html>
