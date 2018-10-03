<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>User Update</title>
</head>
<jsp:include page="common/header.jsp"/>
<body class="body">
<h1 align="center">Update User</h1>
<form:errors path="*"/>
<div class="reg_form text-center">
    <form:form action="${pageContext.request.contextPath}/users/${user.id}" modelAttribute="user" method="post"
               cssClass="form">
        <div>
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
                <form:input path="password" placeholder="Password"/>
            </div>
            <div class="form group">
                <form:label path="email">Email</form:label>
                <form:input path="email" placeholder="Email"/>
            </div>
            <div class="form group">
                <form:label path="profileDto.address">Address</form:label>
                <form:input path="profileDto.address" placeholder="Address"/>
            </div>
            <div class="form group">
                <form:label path="profileDto.telephone">Telephone</form:label>
                <form:input path="profileDto.telephone" placeholder="Telephone"/>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
    </form:form>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
