<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title>Reservations</title>
</head>
<body >
<div class="header-panel">
    <c:if test="${reservations.size()<1}">
        ${messageError}
        <div style="text-align: right" ><a href="${pageContext.request.contextPath}/userSession">Вернуться на главную</a></div>
    </c:if>

    <c:if test="${reservations.size() > 0}">
        <div style="text-align: right" ><a href="${pageContext.request.contextPath}/userSession">Вернуться на главную</a></div>
        <h3>Отели:</h3>
        <table class="ui fixed single line celled table" style="width: 80%">
            <thead>
            <tr style="text-align: center">
                <th class="single line">Название отеля</th>
                <th>Город</th>
                <th>Количество мест в номере</th>
                <th>Дата заезда</th>
                <th>Дата отьезда</th>
                <th>Цена($)</th>
                <th></th>
            </tr>
            </thead>
            <%int id = 0;%>
            <c:forEach var="reservation" items="${reservations}">
                <tbody>
                <tr>
                    <td class="single line" style="text-align: center"> ${reservation.room.hotel.name}</td>
                    <td style="text-align: center">${reservation.room.hotel.addressHotel.city}</td>
                    <td style="text-align: center">${reservation.room.numberOfSeats}</td>
                    <td style="text-align: center">${reservation.startDate}</td>
                    <td style="text-align: center">${reservation.endDate}</td>
                    <td style="text-align: center">${reservation.price}</td>
                    <td style="text-align: center"><a href="${pageContext.request.contextPath}/userSession/userProfile/deleteReservation/<%=id%>">Удалить бронь</a>
                    </td>
                </tr>
                </tbody>
                <%id++;%>
            </c:forEach>
        </table>
    </c:if>

</div>
</body>
</html>
