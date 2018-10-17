<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Item</title>
</head>
<jsp:include page="common/header.jsp"/>
<body class="body">
<form:errors path="*"/>
<div class="reg_form text-center">
    <form:form action="${pageContext.request.contextPath}/web/items" modelAttribute="item" method="post" cssClass="form">
        <div class="form group">
            <form:label path="description">Description</form:label>
            <form:input path="description" placeholder="Description"/>
        </div>
        <div class="form group">
            <form:label path="name">Name</form:label>
            <form:input path="name" placeholder="Name"/>
        </div>
        <div class="form group">
            <form:label path="uniqueNumber">Unique number</form:label>
            <form:input path="uniqueNumber" placeholder="Unique number"/>
        </div>
        <div class="form group">
            <form:label path="price">Price</form:label>
            <form:input path="price" placeholder="price"/>
        </div>
        <button type="submit" class="btn btn-primary">Create Item</button>
    </form:form>
</div>
</body>
<jsp:include page="common/footer.jsp"/>
</html>
