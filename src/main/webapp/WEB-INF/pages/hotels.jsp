<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <style>
        <%@include file="../static/css/bootstrap.min.css"%>
    </style>
    <title>Hotels</title>
</head>
<body >
<div class="header-panel" >
    <h4>${messageError}<h4>
    <c:if test="${hotels.size() > 0}">
        <h5>Отели:</h5>
        <ul>
            <%int id = 1;%>
            <c:forEach var="hotel" items="${hotels}">
                <li>
                    <a href="${pageContext.request.contextPath}/adminHotelSession/hotels/show/<%=id%>"><%=id%> - ${hotel.name}</a>
                </li>
                <%id++;%>
            </c:forEach>
        </ul>
    </c:if>
</div>
</body>
</html>
