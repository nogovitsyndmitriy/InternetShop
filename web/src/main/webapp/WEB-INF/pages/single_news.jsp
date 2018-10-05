<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Read News</title>
</head>
<jsp:include page="common/header.jsp"/>
<body>
<h1>SingleNews</h1>
<div class="reg_form text-center">
    <div class="col-md-3">${news.title}</div>
    <div class="col-md-3">${news.content}</div>
    <div class="col-md-3">${news.userId}</div>
</div>
</body>
<jsp:include page="common/footer.jsp"/>
</html>
