<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title>AddHotel</title>
</head>
<body>
<div class="container">
    <spring:form cssClass="form-control-sm" modelAttribute="newHotel" method="post"
                 action="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/hotel/addHotel">
        <p>Заполните описание отеля</p>
        <br>Название<br>
        <spring:input path="name"/>
        <div style="color: red"><spring:errors path="name"/>
        </div>
        <br>Количество звезд<br>
        <spring:input path="stars"/>
        <div><spring:errors cssStyle="color: red" path="stars"/></div>
        <br>Описание<br>
        <spring:textarea   path="description" rows = "5" cols = "30"/>
        <br>
        <p><spring:button>Создать</spring:button></p>
    </spring:form>
</div>
</body>
</html>
