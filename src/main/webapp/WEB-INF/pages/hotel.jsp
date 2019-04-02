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
<table class="ui fixed single line celled table" style="width: 80%">
    <h3>Описание отеля </h3>
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
        <td class="single line" style="text-align: center">
            ${hotel.name}
        </td>
        <td style="text-align: center">
            ${hotel.stars}
        </td>
        <td>${hotel.description}</td>
        <td style="text-align: center"><a href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/editHotel">Редактировать</a>
        </td>
        <td style="text-align: center"><a href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/deleteHotel">Удалить
            отель</a></td>
    </tr>
    </tbody>
</table>

<table class="ui fixed single line celled table" style="width: 70%">
    <h3>Адрес отеля </h3>
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
        <td class="single line" style="text-align: center">
            ${hotel.addressHotel.country}
        </td>
        <td style="text-align: center">
            ${hotel.addressHotel.city}
        </td>
        <td style="text-align: center">
            ${hotel.addressHotel.street}
        </td>
        <td style="text-align: center">
            ${hotel.addressHotel.house}
        </td>
        <td style="text-align: center"><a href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/editHotelAddress">Редактировать</a>
        </td>
    </tr>

    </tbody>
</table>

<c:if test="${hotel.roomList.size()== 0}">
    <p>Комнат нет</p>
    <a href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/hotel/addHotelRoom">Добавить комнату</a>
</c:if>

<c:if test="${hotel.roomList.size() > 0}">
    <p>Комнаты:</p>
    <%int id = 0;%>
    <c:forEach var="room" items="${hotel.roomList}">
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
            <tbody>
            <tr>
                <td class="single line" style="text-align: center">
                        ${room.numberSeat}
                </td>
                <td style="text-align: center">
                        ${room.price}
                </td>
                <td style="text-align: center">${room.numberRoom}</td>
                <td style="text-align: center"><a href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/editRoom/<%=id%>">Редактировать</a>
                </td>
                <td style="text-align: center"><a href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/deleteRoom/<%=id%>">Удалить
                    комнату</a></td>
            </tr>
            </tbody>
        </table>
        <%id++;%>
    </c:forEach>
    <a href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/hotel/addHotelRoom">Добавить комнату</a>
</c:if>

</body>
</html>
