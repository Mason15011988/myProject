<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title>Login</title>
</head>
<body>
<div class="container">
        <p>Вход</p>
    <a href="${pageContext.request.contextPath}/login/adminHotel">Администратор Отеля</a>
    <a href="${pageContext.request.contextPath}/login/user">Пользователь</a>
</div>
</body>
</html>
