<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <style>
        <%@include file="../css/bootstrap.min.css"%>
    </style>
    <title>Registration User</title>

</head>
<body>

<div class="container">
    <spring:form cssClass="form-control-sm" modelAttribute="newUser" method="post" action="/regUser">
        <p>Выбор регистрации</p>
        <p><spring:radiobutton path="role" value="ADMIN_HOTEL" label="Администратор Отеля"/>
            <spring:radiobutton path="role" value="USER" label="Пользователь"/>
        </p>
        <p>Имя </p>
        <p><spring:input path="name"/>
            <spring:errors path="name"/>
        </p>
        <p>Пароль <spring:password path="password"/>
            <spring:errors path="password"/>
        </p>
        <p>Email <spring:input path="email"/>
            <spring:errors path="email"/>
        </p>
        <p><spring:button>Зарегаться</spring:button></p>
    </spring:form>
</div>


</body>
</html>
