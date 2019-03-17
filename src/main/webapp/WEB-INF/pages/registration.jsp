<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title>Registration</title>
</head>
<body>
<div class="container">
    <p>Выбор регистрации</p> <br>
    <a href="${pageContext.request.contextPath}/registration/admin">Администратор Отеля</a>
    <a href="${pageContext.request.contextPath}/registration/user">Пользователь</a>
</div>
</body>
</html>
