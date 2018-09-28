
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
        .navbar{
            height: 80px;
            opacity: 0.5;
        }
        .body{
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
        .login{
            margin-top: 150px;
            margin-left: 30%;
            margin-right: 30%;
        }
        .submit{
            margin-left: 35%;
            margin-right: 35%;
            border-radius: 3px;
        }
        .login, password {

        }
    </style>
    <title>Login Page</title>
</head>
<body class="body">
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
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
            <a href="${pageContext.request.contextPath}/registration"><button class="btn btn-primary">Registration</button></a>
            <button class="btn btn-primary">Login</button>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </nav>
</header>
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
<footer class="footer">
    <div class="footer__text">&copy; 2018. Created by Dmitriy</div>
</footer>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
