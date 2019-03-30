<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
        /*body{*/
        /*background-image:url("../../../images.jpg") ;*/
        /*}*/
    </style>
    <title>ProfileAdminHotel</title>
</head>
<body style="background-image:url(../../../1.png)">
<div class="header-panel" style="background-color:cornsilk;height: 50px">
    <table style="width: 100%">
        <tr>
            <th>Личный кабинет ${adminHotelSession.email}</th>
            <th style="text-align: center"><a href="${pageContext.request.contextPath}/exit">Выход</a></th>
            <th style="text-align: center"><a href="${pageContext.request.contextPath}/adminHotelSession">
                Вернуться на главную</a></th>
        </tr>
    </table>
    <table style="width: 100%">
        <tr>
            <th><a href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/hotel/addHotelAddress">
                Создать отель</a></th>
            <th><a href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/editAdminHotel">
                Редактировать profile</a>
            </th>
            <th><a href="${pageContext.request.contextPath}/adminHotelSession/hotels">
                Мои отели</a></th>
        </tr>
    </table>
</div>
</body>
</html>
