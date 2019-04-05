<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
        /*body{*/
        /*background-image:url("../../../images.jpg") ;*/
        /*}*/
    </style>
    <title>Session</title>
</head>
<body style="background-image:url(../../../1.png)">
<c:if test="${role.equals(adminHotel)}">
    <div class="header-panel">
        <table style="width: 100%">
            <tr>
                <th style="text-align: center">Добро пожаловать на сайт</th>
                <th style="text-align: right"><a  href="${pageContext.request.contextPath}/adminHotelSession/adminHotelProfile">
                    Личный кабинет</a></th>
                <th style="text-align: center"><a  href="${pageContext.request.contextPath}/exit">
                    Выход</a></th>
            </tr>
        </table>
    </div>
</c:if>
<c:if test="${role.equals(user)}">
    <div class="header-panel">
        <table style="width: 100%">
            <tr>
                <th style="text-align: center" >Добро пожаловать на сайт</th>
                <th style="text-align: right"><a  href="${pageContext.request.contextPath}/userSession/userProfile">
                    Личный кабинет</a></th>
                <th style="text-align: center"><a  href="${pageContext.request.contextPath}/exit">
                    Выход</a></th>
                <th style="text-align: center"><a  href="${pageContext.request.contextPath}/userSession/userProfile/booking">
                    Бронь</a></th>
            </tr>
        </table>
    </div>
</c:if>
</body>
</html>
