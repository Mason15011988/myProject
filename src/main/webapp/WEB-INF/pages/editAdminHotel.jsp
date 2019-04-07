<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title>Edit Admin Hotel</title>
</head>
<body>

<c:if test="${role.equals(adminHotel)}">
    <div class="header-panel">
        Личные данные:<br>
        <br>
        Email--> ${adminHotelSession.email}<br>
        Дата регистрации--> ${adminHotelSession.date}<br>
        <br>
        <ul>
            <li><a href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/editAdminHotelEmail">
                Редактировать email</a></li>
            <li><a href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/editAdminHotelPassword">
                Редактировать пароль</a></li>
        </ul>
        <br>
        <a href="${pageContext.request.contextPath}/adminHotelSession">Вернуться на главную</a>
    </div>
</c:if>

<c:if test="${role.equals(email)}">
    <div class="container">
        <form method="post" action="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/editAdminHotelEmail">
            <br>Введите новый Email<br>
            <input type="email" name="email">
            <div style="color:red">${messageError}</div>
            <br>
            <button>Редактировать</button>
        </form>
    </div>
</c:if>

<c:if test="${role.equals(password)}">
    <div class="container">
        <form method="post"
              action="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/editAdminHotelPassword">
            <br>Введите старый пароль<br>
            <input type="password" name="password">
            <div style="color:red">${messageError}</div>
            <br>Введите новый пароль<br>
            <input type="password" name="newPassword">
            <br>
            <br>Повторите новый пароль<br>
            <input type="password" name="repeatNewPassword">
            <div style="color:red">${messageErrorRepeat} ${messageErrorForNull}</div>
            <br>
            <button>Редактировать</button>
        </form>
    </div>
</c:if>

</body>
</html>
