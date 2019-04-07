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
    <div style="text-align: right" ><a href="${pageContext.request.contextPath}/adminHotelSession">Вернуться на главную</a></div>
    <h5>Описание отеля: </h5>
    <thead>
    <tr style="text-align: center">
        <th class="single line">Название отеля</th>
        <th>Количество звезд</th>
        <th>Описание</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td class="single line" style="text-align: center">${hotel.name}</td>
        <td style="text-align: center"> ${hotel.stars}</td>
        <td>${hotel.description}</td>
        <td style="text-align: center">
            <a href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/editHotel">Редактировать</a>
        </td>
        <td style="text-align: center">
            <a href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/deleteHotel">Удалить
            отель</a></td>
    </tr>
    </tbody>
</table>

<table class="ui fixed single line celled table" style="width: 70%">
    <h5>Адрес отеля:</h5>
    <thead>
    <tr style="text-align: center">
        <th class="single line">Страна</th>
        <th>Город</th>
        <th>Улица</th>
        <th>Дом</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td class="single line" style="text-align: center"> ${hotel.addressHotel.country} </td>
        <td style="text-align: center">${hotel.addressHotel.city}</td>
        <td style="text-align: center">${hotel.addressHotel.street}</td>
        <td style="text-align: center"> ${hotel.addressHotel.house}</td>
        <td style="text-align: center">
            <a href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/editHotelAddress">Редактировать</a>
        </td>
    </tr>
    </tbody>
</table>

<c:if test="${hotel.roomList.size()== 0}">
    <h5>Комнат нет</h5>
    <a href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/hotel/addHotelRoom">Добавить комнату</a>
</c:if>

<c:if test="${hotel.roomList.size() > 0}">
    <h5>Комнаты:</h5>
    <%int id = 0;%>
        <table class="ui fixed single line celled table" style="width: 80%">
            <thead>
            <tr style="text-align: center">
                <th class="single line">Количество мест в номере</th>
                <th>Цена за день($)</th>
                <th>Номер комнаты</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach var="room" items="${hotel.roomList}">
            <tbody>
            <tr>
                    <td class="single line" style="text-align: center">${room.numberOfSeats}</td>
                    <td style="text-align: center">${room.price}</td>
                    <td style="text-align: center">${room.numberRoom}</td>
                    <td style="text-align: center">
                        <a href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/editRoom/<%=id%>">Редактировать</a>
                    </td>
                    <td style="text-align: center">
                        <a href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/deleteRoom/<%=id%>">Удалить
                        комнату</a></td>
            </tr>
            </tbody>
                <%id++;%>
                </c:forEach>
        </table>
    <a href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/hotel/addHotelRoom">Добавить комнату</a>
</c:if>

</body>
</html>
