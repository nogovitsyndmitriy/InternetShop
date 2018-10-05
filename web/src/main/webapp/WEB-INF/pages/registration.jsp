<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>

    <title>Registration</title>
</head>
<jsp:include page="common/header.jsp"/>
<body class="body">
<form:errors path="*"/>
<div class="reg_form text-center">
    <form:form action="${pageContext.request.contextPath}/web/users" modelAttribute="user" method="post" cssClass="form">
        <div class="form group">
            <form:label path="name">Name</form:label>
            <form:input path="name" placeholder="Name"/>
        </div>
        <div class="form group">
            <form:label path="surname">Surname</form:label>
            <form:input path="surname" placeholder="Surname"/>
        </div>
        <div class="form group">
            <form:label path="password">Password</form:label>
            <form:input path="password" placeholder="Password" type="password"/>
        </div>
        <div class="form group">
            <form:label path="email">Email</form:label>
            <form:input path="email" placeholder="Email"/>
        </div>
            <button type="submit" class="btn btn-primary">Create User</button>
    </form:form>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
