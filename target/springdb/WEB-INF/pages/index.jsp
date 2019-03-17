<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title>Index</title>
</head>
<body >
<%--<img src="<c:url value="/WEB-INF/static/img/images.jpg"/>" alt="">--%>
<div class="header-panel" style="background-color:cornsilk;height: 50px">
    <table style="width: 100%">
        <tr>
            <th>Добро пожаловать на сайт</th>
            <th style="text-align: right"><a  href="${pageContext.request.contextPath}/registration" >Регистрация</a></th>
            <th style="text-align: center"><a  href="${pageContext.request.contextPath}/login">Вход</a></th>
        </tr>
    </table>
</div>
</body>
</html>
