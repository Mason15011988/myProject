<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title></title>
</head>
<body >
<div class="header-panel" style="background-color:cornsilk;height: 50px">
    <table style="width: 100%">
        <tr>
            <th>Выбор</th>
            <th style="text-align: right">
                <a  href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/hotel/addRoom">
                Добавить еще комнату</a></th>
            <th style="text-align: center">
                <a  href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/hotel/saveRoom">
                Сохранить и выйти</a></th>
        </tr>
    </table>
</div>
</body>
</html>
