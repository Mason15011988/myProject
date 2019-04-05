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
<div class="container">
    <form method="post" action="${pageContext.request.contextPath}/userSession/userProfile/booking">
        <table>
            <tr>
                <th>Город<br>
                    <input type="text"  name="city" />
                </th>
                <th>
                    Количкство мест<br>
                    <input type="text" name="numberOfSeats"/>
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
        </table>
        <div style="color: red">${messageError}</div>
    </form>
</div>

<c:if test="${hotels.size() > 0}">
    <p>Отели:</p>
    <ul>

        <c:forEach var="hotel" items="${hotels}">
            <li>
                    ${hotel.name}
            </li>

        </c:forEach>
    </ul>
</c:if>
</body>
</html>
