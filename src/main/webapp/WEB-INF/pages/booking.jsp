<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title>Booking</title>
</head>
<body >
<div class="container">
    <form method="post" action="${pageContext.request.contextPath}/userSession/booking">
        <table>
            <tr>
                <th>Город<br>
                    <input type="text"  name="city" />
                </th>
                <th>Количкство мест<br>
                    <input type="text" name="numberOfSeats"/>
                </th>
                <th>Заезд<br>
                    <input type="date" name="startDate"/>
                </th>
                <th>Отьезд<br>
                    <input type="date" name="endDate"/><br>
                </th>
                <th>
                    <br>
                    <button type="submit">Поиск</button>
                </th>
            </tr>
        </table>
        <div style="color: red">${messageError}</div>
    </form>
</div>
<h2 style="text-align: center">${messageErrorNoHotel}</h2>

<c:if test="${roomsSearch.size() > 0 && flagBooking==false}">
    <h3>Отели:</h3>
    <table class="ui fixed single line celled table" style="width: 80%">
        <thead>
        <tr style="text-align: center">
            <th class="single line">Название отеля</th>
            <th>Город</th>
            <th>Количество мест в номере</th>
            <th>Количество дней</th>
            <th>Цена($)</th>
            <th></th>
        </tr>
        </thead>
        <%int id = 0;%>
        <c:forEach var="room" items="${roomsSearch}">
            <tbody>
            <tr>
                <td class="single line" style="text-align: center">
                    <a href="${pageContext.request.contextPath}/userSession/booking/hotels/show/<%=id%>">${room.hotel.name}</a>
                </td>
                <td style="text-align: center">${room.hotel.addressHotel.city}</td>
                <td style="text-align: center">${room.numberOfSeats}</td>
                <td style="text-align: center">${days}</td>
                <td style="text-align: center">${days * room.price}</td>
                <td style="text-align: center"><a href="${pageContext.request.contextPath}/userSession/booking/reservationRoom/<%=id%>">Забронировать</a>
                </td>
            </tr>
            </tbody>
            <%id++;%>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
