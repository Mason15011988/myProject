<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title>Edit User Password</title>
</head>
<body>
<div class="container">
    <form method="post"
          action="${pageContext.request.contextPath}/userSession/userProfile/editUserPassword">
        <br>Введите старый пароль<br>
        <input type="password" name="password">
        <h6 style="color: red">${messageError}</h6>
        <br>

        <br>Введите новый пароль<br>
        <input type="password" name="newPassword">
        <br>
        <br>Повторите новый пароль<br>
        <input type="password" name="repeatNewPassword">
        <h6 style="color: red">${messageErrorRepeat}</h6>
        <br>
        <br>
        <button>Редактировать</button>
    </form>
</div>
</body>
</html>
