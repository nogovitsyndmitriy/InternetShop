<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<jsp:include page="common/header.jsp"/>
<body class="body">
<h1 align="center">Current User</h1>
<div class="reg_form text-center">
    <form:form action="${pageContext.request.contextPath}/web/users/${user.id}/current" modelAttribute="user"
               method="post"
               cssClass="form">
        <form:errors path="*" cssClass="errorblock" element="div"/>
        <div>
            <div class="form group">
                <form:label path="name">Name</form:label>
                <form:input path="name" placeholder="Name"/>
            </div>
            <div class="form group">
                <form:label path="surname">Surname</form:label>
                <form:input path="surname" placeholder="Surname"/>
            </div>
            <div hidden class="form group">
                <form:label path="password">Password</form:label>
                <form:input path="password" placeholder="Password" type="password"/>
            </div>
            <div hidden class="form group">
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
            <div class="form group" hidden>
                <form:label path="roleDto.id">Role</form:label>
                <form:input path="roleDto.id" placeholder="Role"/>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
        <a href="${pageContext.request.contextPath}/web/users/${user.id}/update/password">
            Change Password
        </a>
    </form:form>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
