<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>

    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"
          rel="stylesheet">
    <style>
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #eee;
        }

        .form-signin {
            max-width: 330px;
            padding: 15px;
            margin: 0 auto;
        }

        .form-signin .form-signin-heading, .form-signin .checkbox {
            margin-bottom: 10px;
        }

        .form-signin .checkbox {
            font-weight: normal;
        }

        .form-signin .form-control {
            position: relative;
            height: auto;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            padding: 10px;
            font-size: 16px;
        }

        .form-signin .form-control:focus {
            z-index: 2;
        }

        .form-signin input[type="email"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }

        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
    </style>
</head>

<body>

<div class="container">
    <c:if test="${1001==param.err}">
        <div class="alert alert-warning alert-dismissible fade in"
             role="alert">用户名或密码错误！</div>
    </c:if>
    <form method="post" action="/login" class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">用户名</label> <input
            type="text" id="inputEmail" class="form-control"
            placeholder="请输入用户名" required autofocus name="username"> <label
            for="inputPassword" class="sr-only">密码</label> <input
            type="password" id="inputPassword" class="form-control"
            placeholder="请输入密码" required name="userpwd">
        <div class="checkbox">
            <label> <input value="yes" name="rememberme" type="checkbox" value="rememberme">
                7天内自动登录
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
    </form>

</div>
</body>
</html>