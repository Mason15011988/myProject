<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title>AddRoom</title>
</head>
<body>
<div class="container">
    <spring:form cssClass="form-control-sm" modelAttribute="newRoom" method="post"
                 action="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/hotel/addRoom">
        <p>Заполните описание комнваты</p>

        <br>Количество мест в номере<br>
        <spring:input path="numberSeat"/>
        <div style="color: red"><spring:errors path="numberSeat"/>
        </div>
        <br>Цена за день<br>
        <spring:input path="price"/>
        <div><spring:errors cssStyle="color: red" path="price"/></div>
        <br>Номер комнаты <br>
        <spring:input path="numberRoom"/>
        <div><spring:errors cssStyle="color: red" path="price"/></div>
        <p><spring:button>Создать</spring:button></p>
    </spring:form>
</div>
</body>
</html>
