<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title>EditHotelAddress</title>
</head>
<body>
<spring:form cssClass="form-control-sm" modelAttribute="newAddress" method="post"
             action="${pageContext.request.contextPath}/adminHotelSession/editHotelAddress">
    <p>Заполните адрес отеля</p>
    <br>Страна<br>
    <spring:input path="country"/>
    <div style="color: red"><spring:errors path="country"/>
    </div>
    <br>Город<br>
    <spring:input path="city"/>
    <div><spring:errors cssStyle="color: red" path="city"/></div>
    <br>Улица<br>
    <spring:input path="street"/>
    <div><spring:errors cssStyle="color: red" path="street"/></div>
    <br>
    <br>Дом<br>
    <spring:input path="house"/>
    <div><spring:errors cssStyle="color: red" path="house"/></div>
    <br>
    <p><spring:button>Редактировать</spring:button></p>
</spring:form>
</div>
</body>
</html>
