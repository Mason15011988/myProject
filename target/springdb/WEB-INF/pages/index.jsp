<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<h3>Hello my friend</h3>
<a href="${pageContext.request.contextPath}/registration">Регистрация</a>
<a href="${pageContext.request.contextPath}/log">Вход</a>
<a href="${pageContext.request.contextPath}/users">Users</a>
${sessionScope.user}

</body>
</html>
