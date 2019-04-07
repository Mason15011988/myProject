<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title>Login</title>
</head>
<body>

<c:if test="${role.equals(guest)}">
    <div class="container">
        <h5>Выберите вариант инициализации</h5> <br>
        <ul>
            <li><a href="${pageContext.request.contextPath}/login/adminHotel">Администратор отеля</a></li>
            <li> <a href="${pageContext.request.contextPath}/login/user">Пользователь</a></li>
        </ul>
    </div>
</c:if>

<c:if test="${role.equals(user)}">
    <div class="container">
        <h5>Инициализации пользователя</h5>
        <spring:form cssClass="form-control-sm" modelAttribute="newUser" method="post"
                     action="${pageContext.request.contextPath}/login/user">
            <br>Email<br>
            <spring:input path="email"/>
            <div style="color: red"><spring:errors  path="email"/> ${messageError}  </div>
            <br>Пароль<br>
            <spring:password path="password"/>
            <div><spring:errors cssStyle="color: red" path="password"/></div>
            <br>
            <p><spring:button>Войти</spring:button></p>
            <br>
            <br>
            <div ><a href="${pageContext.request.contextPath}/">Вернуться на главную</a></div>
        </spring:form>
    </div>
</c:if>

<c:if test="${role.equals(adminHotel)}">
    <div class="container">
        <h5>Инициализации администратора отеля</h5>
        <spring:form cssClass="form-control-sm" modelAttribute="newAdminHotel" method="post"
                     action="${pageContext.request.contextPath}/login/adminHotel">
            <br>Email<br>
            <spring:input path="email"/>
            <div style="color: red"><spring:errors  path="email"/>  ${messageError}  </div>
            <br>Пароль<br>
            <spring:password path="password"/>
            <div><spring:errors cssStyle="color: red" path="password"/></div>
            <br>
            <p><spring:button>Войти</spring:button></p>
            <br>
            <br>
            <div ><a href="${pageContext.request.contextPath}/">Вернуться на главную</a></div>
        </spring:form>
    </div>
</c:if>

</body>
</html>
