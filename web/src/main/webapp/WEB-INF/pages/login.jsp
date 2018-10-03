<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body class="body">
<jsp:include page="common/header.jsp"/>
<div class="login">
    <form method="post">
        <b>Login</b>
        <input class="log" type="text" name="login" maxlength="30"/>
        <b>Password</b>
        <input class="password" type="password" name="password" maxlength="20"/><br/>
        <br>
        <input class="submit btn-primary" type="submit" value="LogIn"/>
    </form>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
