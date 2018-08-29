
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="error">${errorMsg}</div>
    <form action="frontController?command=login" method="post">
        <b>Login</b>
        <input type="text" name="login" maxlength="25">
        <b>Password</b>
        <input type="password" name="password" maxlength="25">
        <br>
        <input type="submit" value="LogIn">
    </form>
</body>
</html>
