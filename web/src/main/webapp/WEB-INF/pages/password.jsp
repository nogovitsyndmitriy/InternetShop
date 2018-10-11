<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<jsp:include page="common/header.jsp"/>
<body class="body">
<h1 align="center">Change Password</h1>
<form:errors path="*"/>
<div class="reg_form text-center">
    <form:form action="${pageContext.request.contextPath}/web/users/${user.id}/update/password" modelAttribute="password" method="post"
               cssClass="form">
        <div>
            <div class="form group">
                <form:label path="currentPassword">Current Password</form:label>
                <form:input path="currentPassword" placeholder="Current Password" type="password"/>
            </div>
            <div class="form group">
                <form:label path="newPassword">New Password</form:label>
                <form:input path="newPassword" placeholder="New Password" type="password"/>
            </div>
            <div class="form group">
                <form:label path="confirmPassword">Confirm Password</form:label>
                <form:input path="confirmPassword" placeholder="Confirm Password" type="password"/>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Change</button>
    </form:form>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
