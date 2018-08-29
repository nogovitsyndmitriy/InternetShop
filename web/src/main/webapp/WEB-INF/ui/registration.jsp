
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <style>
        .form-item {
            width: 50%;
            float: left;
            margin-bottom: 10px;
        }

        .form {
            position: relative;
            width: 80%;
            height: 430px;
            margin: 30px auto;
            font-size: large;
            font-weight: bold;
        }

        .clearfix:before, .clearfix:after {
            content: '';
            display: table;
        }

        .clearfix:after {
            clear: both;
        }

        .error {
            text-align: center;
        }

        .subm-btn {
            display: block;
            color: black;
            width: 120px;
            height: 35px;
            background-color: khaki;
            border: 1px solid gray;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            position: absolute;
            bottom: 10px;
            left: 50%;
            margin: 0px 0px 0px -60px;
        }

        .subm-btn:hover, .subm-btn:active {
            text-decoration: none;
            outline: none;
            animation: button .5s forwards;
        }

        @keyframes button {
            0% {
                transform: scale(1);
                background-color: rgba(240, 230, 140, .3);
            }
            100% {
                transform: scale(1.05);
                background-color: rgba(240, 230, 140, 1);
            }
        }
    </style>
    <title>${title}</title>
</head>
<body>
<div class="error">${errorMsg}</div>
<form class="clearfix form" action="frontController?command=registration" method="post">

    <div class="form-item">
        <label for="log">Login</label>
        <div>
            <input type="text" placeholder="1-15 symbols" id="log" name="login"/>
        </div>
    </div>
    <div class="form-item">
        <label for="pass">Password</label>
        <div>
            <input type="password" placeholder="1-15 symbols" id="pass" name="password"/>
        </div>
    </div>
    <div class="form-item">
        <label for="name">First Name</label>
        <div>
            <input type="text" placeholder="First Name" id="name" name="name"/>
        </div>
    </div>
    <div class="form-item">
        <label for="lastname">Last Name</label>
        <div>
            <input type="text" placeholder="Last Name" id="lastname" name="lastname"/>
        </div>
    </div>
    <div class="form-item">
        <label for="age">Age</label>
        <div>
            <input type="text" placeholder="Age" id="age" name="age"/>
        </div>
    </div>
    <div class="form-item">
        <label for="email">E-mail</label>
        <div>
            <input type="text" placeholder="E-mail" id="email" name="email"/>
        </div>
    </div>
    <input class="subm-btn" value="Register Now!" type="submit"/>
</form>
</body>
</html>
