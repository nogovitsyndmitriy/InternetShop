<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ERROR</title>
</head>
<jsp:include page="common/header.jsp"/>
<body class="body">
<h2>Application Error, please contact support...</h2>
<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJ10U1GViOOl7mpQuHTwSvMUkcn_uSLZ9oE4NB7MghSRnp7Nl_">
<h3>Debug Information:</h3>
<div class="error">
    Requested URL = ${url}<br><br>

    Exception = ${exception.message}<br><br>

    <strong>Exception Stack Trace</strong><br>
    <c:forEach items="${exception.stackTrace}" var="trace">
        ${trace}
    </c:forEach>
</div>
</body>
<jsp:include page="common/footer.jsp"/>
</html>
