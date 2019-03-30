<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <%--<style>--%>
        <%--<%@include file="../static/css/bootstrap.min.css"%>--%>
    <%--</style>--%>
    <title>Hotel</title>
</head>
<body>
<table class="ui fixed single line celled table"  style="width: 100%" >
    <thead>
    <tr style="text-align: center">
        <th class="single line">Название отеля</th>
        <th>Количество звезд</th>
        <th>Описание</th>
        <th></th>
        <th></th>

    </tr></thead>
    <tbody>
    <tr>
        <td class="single line" style="text-align: center">
            ${hotelshow.name}
        </td>
        <td style="text-align: center">
            ${hotelshow.stars}
        </td>
        <td>${hotelshow.description}</td>
        <td style="text-align: center"><a href="${pageContext.request.contextPath}/adminHotelSession/hotels/show/">Редатировать</a></td>
        <td style="text-align: center"><a href="${pageContext.request.contextPath}/adminHotelSession/hotels/show/">Удалить отель</a></td>
    </tr>

    </tbody>

</table>
<div class="header-panel" style="background-color:cornsilk;height: 50px">
    <table>
        <tr>
            <th>Страна</th>
            <th>Город</th>
            <th>Улица</th>
            <th>Дом</th>
        </tr>
        <tr>
            <th>${hotelshow.addressHotel.country}</th>
            <th>${hotelshow.addressHotel.city}</th>
            <th>${hotelshow.addressHotel.street}</th>
            <th>${hotelshow.addressHotel.house}</th>
        </tr>
    </table>
</div>

<%--${hotelshow.addressHotel}--%>
<%--${hotelshow.roomList}--%>
</body>
</html>
