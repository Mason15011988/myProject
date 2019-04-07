<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title>AddHotel</title>
</head>
<body>

<c:if test="${role.equals(hotelAddress)}">
    <div class="container">
        <spring:form cssClass="form-control-sm" modelAttribute="newAddress" method="post"
                     action="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/hotel/addHotelAddress">
            <h5>Заполните адрес отеля</h5>
            <br>Страна<br>
            <spring:input path="country"/>
            <div style="color: red"><spring:errors path="country"/></div>
            <br>Город<br>
            <spring:input path="city"/>
            <div><spring:errors cssStyle="color: red" path="city"/></div>
            <br>Улица<br>
            <spring:input path="street"/>
            <div><spring:errors cssStyle="color: red" path="street"/></div>
            <br>Дом<br>
            <spring:input path="house"/>
            <div><spring:errors cssStyle="color: red" path="house"/></div>
            <br>
            <p><spring:button>Далее</spring:button></p>
        </spring:form>
    </div>
</c:if>

<c:if test="${role.equals(hotel)}">
    <div class="container">
        <spring:form cssClass="form-control-sm" modelAttribute="newHotel" method="post"
                     action="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/hotel/addHotel">
            <h5>Заполните описание отеля</h5>
            <br>Название<br>
            <spring:input path="name"/>
            <div><spring:errors cssStyle="color: red" path="name"/></div>
            <br>Количество звезд<br>
            <spring:input path="stars"/>
            <div><spring:errors cssStyle="color: red" path="stars"/></div>
            <br>Описание<br>
            <spring:textarea   path="description" rows = "5" cols = "50"/>
            <div ><spring:errors cssStyle="color: red" path="description"/></div>
            <br>
            <p><spring:button>Далее</spring:button></p>
        </spring:form>
    </div>
</c:if>

<c:if test="${role.equals(hotelRoom)}">
    <div class="container">
        <spring:form cssClass="form-control-sm" modelAttribute="newRoom" method="post"
                     action="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/hotel/addHotelRoom">
            <h5>Заполните описание комнаты</h5>
            <br>Количество мест в номере<br>
            <spring:input path="numberOfSeats"/>
            <div><spring:errors cssStyle="color: red" path="numberOfSeats"/></div>
            <br>Цена за день($)<br>
            <spring:input path="price"/>
            <div><spring:errors cssStyle="color: red" path="price"/></div>
            <br>Номер комнаты <br>
            <spring:input path="numberRoom"/>
            <div style="color: red"><spring:errors  path="numberRoom"/> ${messageError}</div>
            <br>
            <p><spring:button>Далее</spring:button></p>
        </spring:form>
    </div>
</c:if>

<c:if test="${role.equals(choice)}">
    <div class="header-panel">
        <h5>Выберите вариант для продолжения</h5> <br>
        <ul>
            <li><a  href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile/hotel/addHotelRoom">
                Добавить еще комнату</a></li>
            <li> <a  href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile">
                Выйти</a></li>
        </ul>
    </div>
</c:if>

</body>
</html>
