<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title>Login Admin</title>

</head>
<body>
<div class="container">
    <spring:form cssClass="form-control-sm" modelAttribute="newAdmin" method="post" action="/login/admin">
        <br>Email<br>
        <spring:input path="email"/>
        <div style="color: red"><spring:errors  path="email"/>
                ${messageError}
        </div>
        <br>Пароль<br>
        <spring:password path="password"/>
        <div><spring:errors cssStyle="color: red" path="password"/></div>
        <br>
        <p><spring:button>Зарегаться</spring:button></p>
    </spring:form>
</div>
</body>
</html>
