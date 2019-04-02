<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title>Edit User </title>
</head>
<body>
<c:if test="${role.equals(user)}">
    <div class="header-panel" style="background-color:cornsilk;height: 50px">
        Личные данные:<br>
        Email: ${userSession.email}<br>
        Пароль: ${userSession.password}<br>
        Дата регистрации: ${userSession.date}
        <table style="width: 100%">
            <tr>
                <th>Выбор</th>
                <th style="text-align: right"><a
                        href="${pageContext.request.contextPath}/userSession/userProfile/editUserEmail">
                    Редактировать email</a></th>
                <th style="text-align: center"><a
                        href="${pageContext.request.contextPath}/userSession/userProfile/editUserPassword">
                    Редактировать пароль</a></th>
            </tr>
        </table>
    </div>
</c:if>
<c:if test="${role.equals(email)}">
    <div class="container">
        <form method="post" action="${pageContext.request.contextPath}/userSession/userProfile/editUserEmail">
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
              action="${pageContext.request.contextPath}/userSession/userProfile/editUserPassword">
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
