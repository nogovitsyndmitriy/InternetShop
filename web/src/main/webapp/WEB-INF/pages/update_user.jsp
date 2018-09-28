<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <style>
        .btn-primary {
            margin-left: 10px;
            margin-right: 10px;
        }

        .navbar {
            height: 80px;
            opacity: 0.5;
        }

        .body {
            background: url("https://img3.goodfon.ru/original/1280x960/e/61/maslo-zheltyy-bryzgi.jpg") no-repeat;
            background-size: 100%;
        }

        .footer {
            width: 100%;
            margin: 30px 0 0;
            background-color: rgba(189, 183, 107, 0.3);
            color: white;
            font-size: 12px;
            padding: 20px 0;
            position: absolute;
            bottom: 0;
            left: 0;
        }

        .reg_form {
            background: rgba(234, 252, 251, 0.65) center;
            margin-top: 10%;
            margin-right: 30%;
            margin-left: 35%;
            overflow: auto;
            border-radius: 8px;
        }

        .form {
            float: right;
        }

        .btn {
            position: center;
        }

        .form {
            margin: 10px;
        }
    </style>
    <title>User Update</title>
</head>
<body class="body">
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/users">Users<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/items">Items</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#">News</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#">Promo</a>
                </li>
            </ul>
            <a href="${pageContext.request.contextPath}/registration">
                <button class="btn btn-primary">Registration</button>
            </a>
            <a href="${pageContext.request.contextPath}/login">
                <button class="btn btn-primary">Login</button>
            </a>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </nav>
</header>
<h1 align="center">Update User</h1>

<form:errors path="*"/>
<div class="reg_form text-center">
    <form:form action="${pageContext.request.contextPath}/users/${user.id}" modelAttribute="user" method="post"
               cssClass="form">
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
        <button type="submit" class="btn btn-primary">Update</button>
    </form:form>

</div>
<footer class="footer">
    <div class="footer__text">&copy; 2018. Created by Dmitriy</div>
</footer>
</body>
</html>
