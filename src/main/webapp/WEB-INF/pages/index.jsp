<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
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
${search}
<%--${v2}--%>

<div class="container">
    <form method="post" action="${pageContext.request.contextPath}/">
        <table>
            <tr>
                <th>Страна<br>
                    <input type="text"  name="country" />
                </th>
                <th>
                    Количкство мест<br>
                    <input type="text" name="numberSeat"/>
                </th>
                <th>
                    Заезд<br>
                    <input type="date" name="startDate"/>
                </th>
                <th>
                    Отьезд<br>
                    <input type="date" name="endDate"/><br>

                </th>
                <th>
                    <br>
                    <button type="submit">Поиск</button>
                </th>
            </tr>
            <tr>
                <th></th>
                <th></th>
                <th><font size="2" color="red">${errorNowDate}</font></th>
                <th><font size="2" color="red">${errorDate}</font> </th>
                <th></th>

            </tr>
        </table>
    </form>
</div>
</body>
</html>
