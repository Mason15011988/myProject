<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title>Profile</title>
</head>
<body >

<c:if test="${role.equals(adminHotel)}">
    <div class="header-panel">
        <table style="width: 100%">
            <tr>
                <th>Личный кабинет : ${adminHotelSession.email}</th>
                <th style="text-align: center"><a href="${pageContext.request.contextPath}/adminHotelSession">
                    Вернуться на главную</a></th>
                <th style="text-align: center"><a href="${pageContext.request.contextPath}/exit">
                    Выход</a></th>
            </tr>
        </table>
        <br>
        <ul>
            <li><a href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/hotel/addHotelAddress">
                Создать отель</a></li>
            <li><a href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/editAdminHotel">
                Редактировать profile</a></li>
            <li><a href="${pageContext.request.contextPath}/adminHotelSession/hotels">
                Мои отели</a></li>
        </ul>
    </div>
</c:if>

<c:if test="${role.equals(user)}">
    <div class="header-panel">
        <table style="width: 100%">
            <tr>
                <th>Личный кабинет : ${userSession.email}</th>
                <th style="text-align: center"><a href="${pageContext.request.contextPath}/userSession">
                    Вернуться на главную</a></th>
                <th style="text-align: center"><a href="${pageContext.request.contextPath}/exit">
                    Выход</a></th>
            </tr>
        </table>
        <br>
        <ul>
            <li><a href="${pageContext.request.contextPath}/userSession/userProfile/editUser">
                Редактировать profile</a></li>
            <li><a href="${pageContext.request.contextPath}/userSession/userProfile/reservations">
                Мои бронировиния</a></li>
        </ul>
    </div>
</c:if>

</body>
</html>
