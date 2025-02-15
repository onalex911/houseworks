<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Novoxel. <c:out value="${title}"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
</head>
<body>

<div id="container">
    <div id="nav">
        ${nav}
    </div>

    <div id="center">
        <h1 class="main_heading">${mainHeading}</h1>

        <h3><c:out value="${head}"/></h3>
        <div class="container">
            ${content}
        </div>
        <div class="go-back" style="display:<c:out value="${displayBack}" default="none"/>">&lt;&lt;Назад</div>


    </div>

    <div id="footer">Template by: <a href="http://www.csstemplateheaven.com">CssTemplateHeaven</a>
        <div id="sharethis">
            <ul>
                <li><a href="#" class="twitter"><span>Twitter</span></a></li>
                <li><a href="#" class="facebook"><span>Facebook</span></a></li>
                <li><a href="#" class="rss"><span>RSS feed</span></a></li>
            </ul>
        </div>
    </div>
</body>
</html>