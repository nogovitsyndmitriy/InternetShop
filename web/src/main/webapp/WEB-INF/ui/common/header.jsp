<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style type="text/css">
        .form-inline {
            float: right;
            margin-right: 50px;
        }

        .btn {
            margin-right: 10px;
        }

        .basket {
            width: 40px;
            height: 40px;
            margin-right: 5px;
        }

        .basketName {
            margin-right: 50px;
        }
    </style>
    <title>Title</title>
</head>
<body>
<header class="header">
    <div class="header-nav">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/frontController?command=home">Home</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
                    aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Categories <span class="sr-only">Categories</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/frontController?command=products">Products</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled"
                           href="${pageContext.request.contextPath}/frontController?command=faq">F.A.Q.</a>
                    </li>
                    <c:if test="${user.role eq 'admin'}">
                    <li>
                        <a class="nav-link disabled"
                           href="${pageContext.request.contextPath}/frontController?command=administrator">Administrator's Page</a>
                    </li>
                    </c:if>
                    <c:if test="${user.role eq 'client'}">
                        <li>
                            <a class="nav-link disabled"
                               href="${pageContext.request.contextPath}/frontController?command=userscabinet">Personal cabinet</a>
                        </li>
                    </c:if>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search">
                    <a href="${pageContext.request.contextPath}/frontController?command=searchresults" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</a>
                </form>
                <a class="basketName" href="${pageContext.request.contextPath}/frontController?command=basket"><img
                        class="basket"
                        src="https://botw-pd.s3.amazonaws.com/styles/logo-thumbnail/s3/012011/basket.png?itok=PFoRHkUs">Basket</a>
                <c:if test="${not empty user}">
                    <div class="text">Hi, ${user.name}!</div>
                    <a class="logout"
                       href="${pageContext.request.contextPath}/frontController?command=logout">LogOut</a>
                </c:if>
                <c:if test="${empty user}">
                    <a class="Login" href="${pageContext.request.contextPath}/frontController?command=login">LogIn</a>
                    <a class="Registration"
                       href="${pageContext.request.contextPath}/frontController?command=RegistrationPage">Registration</a>
                </c:if>
            </div>
        </nav>
    </div>
</header>
</body>
</html>
