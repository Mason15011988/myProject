<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
        /*body{*/
        /*background-image:url("../../../images.jpg") ;*/
        /*}*/
    </style>
    <title>SessionAdminHotel</title>
</head>
<body style="background-image:url(../../../1.png)">
<div class="header-panel" style="background-color:cornsilk;height: 50px">
    <table style="width: 100%">
        <tr>
            <th>Добро пожаловать на сайт</th>
            <th style="text-align: right"><a  href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile">
                Личный кабинет</a></th>
            <th style="text-align: center"><a  href="${pageContext.request.contextPath}/exit">Выход</a></th>
        </tr>
    </table>
</div>
</body>
</html>
