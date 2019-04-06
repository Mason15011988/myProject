<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <style>
    <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title>Hotel</title>
</head>
<body>
<table class="ui fixed single line celled table" style="width: 80%">
    <h5>Описание отеля: </h5>
    <thead>
    <tr style="text-align: center">
        <th class="single line">Название отеля</th>
        <th>Количество звезд</th>
        <th>Описание</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td class="single line" style="text-align: center">
            ${roomSearch.hotel.name}
        </td>
        <td style="text-align: center">
            ${roomSearch.hotel.stars}
        </td>
        <td>${roomSearch.hotel.description}</td>

    </tr>
    </tbody>
</table>

<table class="ui fixed single line celled table" style="width: 70%">
    <h5>Адрес отеля: </h5>
    <thead>
    <tr style="text-align: center">
        <th class="single line">Страна</th>
        <th>Город</th>
        <th>Улица</th>
        <th>Дом</th>

    </tr>
    </thead>
    <tbody>
    <tr>
        <td class="single line" style="text-align: center">
            ${roomSearch.hotel.addressHotel.country}
        </td>
        <td style="text-align: center">
            ${roomSearch.hotel.addressHotel.city}
        </td>
        <td style="text-align: center">
            ${roomSearch.hotel.addressHotel.street}
        </td>
        <td style="text-align: center">
            ${roomSearch.hotel.addressHotel.house}
        </td>
    </tr>

    </tbody>
</table>

<table class="ui fixed single line celled table" style="width: 80%">
    <h5>Комната:</h5>
    <thead>
    <tr style="text-align: center">
        <th class="single line">Количество мест в номере</th>
        <th>Цена за день($)</th>
        <th>Номер комнаты</th>
    </tr>
    </thead>

    <tbody>
    <tr>
        <td class="single line" style="text-align: center">
            ${roomSearch.numberOfSeats}
        </td>
        <td style="text-align: center">
            ${roomSearch.price}
        </td>
        <td style="text-align: center">${roomSearch.numberRoom}</td>
    </tr>
    </tbody>


</table>


</body>
</html>
