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
    <h3>Описание отеля </h3>
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
            ${hotelShow.name}
        </td>
        <td style="text-align: center">
            ${hotelShow.stars}
        </td>
        <td>${hotelShow.description}</td>
        <td style="text-align: center"><a href="${pageContext.request.contextPath}/adminHotelSession/editHotel">Редактировать</a></td>
        <td style="text-align: center"><a href="${pageContext.request.contextPath}/adminHotelSession/deleteHotel">Удалить отель</a></td>
    </tr>
    </tbody>
</table>

<table class="ui fixed single line celled table"  style="width: 100%" >
    <h3>Адрес отеля </h3>
    <thead>
    <tr style="text-align: center">
        <th class="single line">Страна</th>
        <th>Город</th>
        <th>Улица</th>
        <th>Дом</th>
        <th></th>

    </tr></thead>
    <tbody>
    <tr>
        <td class="single line" style="text-align: center">
            ${hotelShow.addressHotel.country}
        </td>
        <td style="text-align: center">
            ${hotelShow.addressHotel.city}
        </td>
        <td style="text-align: center">
            ${hotelShow.addressHotel.street}
        </td>
        <td style="text-align: center">
            ${hotelShow.addressHotel.house}
        </td>
        <td style="text-align: center"><a href="${pageContext.request.contextPath}/adminHotelSession/editHotelAddress">Редактировать</a></td>
    </tr>

    </tbody>
</table>
</body>
</html>
