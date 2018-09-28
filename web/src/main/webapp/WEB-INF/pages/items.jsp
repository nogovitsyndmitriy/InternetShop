<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            background-image: url("https://img3.goodfon.ru/original/1280x960/e/61/maslo-zheltyy-bryzgi.jpg");
            background-repeat: no-repeat;
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

        .table {
            margin-top: 150px;


        }
    </style>
    <title>Items</title>
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
<table class="table table-striped table-dark">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Description</th>
        <th scope="col">Name</th>
        <th scope="col">Price</th>
        <th scope="col">Unique Number</th>
        <th scope="col">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${items}" var="item">
        <tr>
            <th scope="row"><input type="checkbox" name="ids" value="${item.id}"></th>
            <td>${item.description}</td>
            <td>${item.name}</td>
            <td>${item.price}$</td>
            <td>${item.uniqueNumber}</td>
            <td>
            <a href="${pageContext.request.contextPath}/items" class="btn btn-primary" aria-pressed="true"
               role="button">UPDATE</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<footer class="footer">
    <div class="footer__text">&copy; 2018. Created by Dmitriy</div>
</footer>
</body>
</html>
