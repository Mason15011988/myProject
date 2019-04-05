<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title>Index</title>
</head>
<body >
<%--<img src="<c:url value="/WEB-INF/static/img/images.jpg"/>" alt="">--%>
<div class="header-panel" style="background-color:cornsilk;height: 50px">
    <table style="width: 100%">
        <tr>
            <th>Добро пожаловать на сайт</th>
            <th style="text-align: right"><a  href="${pageContext.request.contextPath}/registration" >Регистрация</a></th>
            <th style="text-align: center"><a  href="${pageContext.request.contextPath}/login">Вход</a></th>
        </tr>
    </table>
</div>
${search}
<%--${v2}--%>

<div class="container">
    <form method="post" action="${pageContext.request.contextPath}/">
        <table>
            <tr>
                <th>Страна<br>
                    <input type="text"  name="city" />
                </th>
                <th>
                    Количкство мест<br>
                    <input type="text" name="numberOfSeats"/>
                </th>
                <th>
                    Заезд<br>
                    <input type="date" name="startDate"/>
                </th>
                <th>
                    Отьезд<br>
                    <input type="date" name="endDate"/><br>

                </th>
                <th>
                    <br>
                    <button type="submit">Поиск</button>
                </th>
            </tr>
            <tr>
                <th></th>
                <th></th>
                <th><font size="2" color="red">${errorNowDate}</font></th>
                <th><font size="2" color="red">${errorDate}</font> </th>
                <th></th>

            </tr>
        </table>
    </form>
</div>

<c:if test="${roomsDates.size() > 0}">
    <p>Отели:</p>
    <ul>

        <c:forEach var="room" items="${roomsDates}">
            <li>
                ${room.id}<br>
                ${room.hotel.name}<br>
                ${room.hotel.addressHotel.city}<br>
                ${room.numberOfSeats}<br>
            </li>

        </c:forEach>
    </ul>
</c:if>
<c:if test="${roomsAddress.size() > 0}">
    <p>Отели:</p>
    <ul>

        <c:forEach var="room" items="${roomsAddress}">
            <li>
                    ${room.id}<br>
                    ${room.hotel.name}<br>
                    ${room.hotel.addressHotel.city}<br>
                    ${room.numberOfSeats}<br>
            </li>

        </c:forEach>
    </ul>
</c:if>


</body>
</html>
