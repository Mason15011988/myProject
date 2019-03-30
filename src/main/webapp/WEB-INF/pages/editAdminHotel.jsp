<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title>Edit Admin Hotel</title>
</head>
<body>
<div class="header-panel" style="background-color:cornsilk;height: 50px">
    Личные данные:<br>
    Email: ${adminHotelSession.email}<br>
    Пароль: ${adminHotelSession.password}<br>
    <table style="width: 100%">
        <tr>
            <th>Выбор</th>
            <th style="text-align: right"><a
                    href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/editAdminHotelEmail">
                Редактировать email</a></th>
            <th style="text-align: center"><a
                    href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/editAdminHotelPassword">
                Редактировать пароль</a></th>
        </tr>
    </table>
</div>
</body>
</html>
