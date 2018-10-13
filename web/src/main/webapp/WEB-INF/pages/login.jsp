<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body class="body">
<jsp:include page="common/header.jsp"/>
<div class="login">
    <form:form method="post" action="${pageContext.request.contextPath}/web/login">
        <form:errors path="*" cssClass="error" element="div"/>
        <b>Email</b>
        <input class="log" type="text" name="email" maxlength="30"/>
        <b>Password</b>
        <input class="password" type="password" name="password" maxlength="20"/><br/>
        <br>
        <input class="submit btn-primary" type="submit" value="LogIn"/>
    </form:form>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
