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
    <spring:form cssClass="form-control-sm" modelAttribute="newAdminHotel" method="post"
                 action="${pageContext.request.contextPath}/login/adminHotel">
        <br>Email<br>
        <spring:input path="email"/>
        <div style="color: red"><spring:errors  path="email"/>
                ${messageError}
        </div>
        <br>Пароль<br>
        <spring:password path="password"/>
        <div><spring:errors cssStyle="color: red" path="password"/></div>
        <br>
        <p><spring:button>Войти</spring:button></p>
    </spring:form>
</div>
</body>
</html>