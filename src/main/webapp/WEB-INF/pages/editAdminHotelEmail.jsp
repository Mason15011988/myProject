<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title>Edit Admin Hotel Email</title>
</head>
<body>
<div class="container">
    <form method="post" action="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/editAdminHotelEmail">
        <br>Введите новый Email<br>
        <input type="email" name="email">
        <h8 style="color: red">${messageError}</h8>
        <br>
        <br>
        <button>Редактировать</button>
    </form>
</div>
</body>
</html>
