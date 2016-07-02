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
    <c:choose>
        <c:when test="${param.code==1001}">
            <div class="alert alert-danger">
                验证码错误
            </div>
        </c:when>
        <c:when test="${param.code==1002}">
            <div class="alert alert-danger">
                账号密码错误
            </div>
        </c:when>
    </c:choose>
    <form action="/login" method="post" class="form-signin" id="loginForm">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">用户名</label> <input
            type="text" id="inputEmail" class="form-control"
            placeholder="请输入用户名" required autofocus name="username"> <label
            for="inputPassword" class="sr-only">密码</label> <input
            type="password" id="inputPassword" class="form-control"
            placeholder="请输入密码" required name="password">
        <div class="form-group">
            <input type="text" class="form-control"  placeholder="请输入验证码" name="captcha">
            <a href="javascript:;" id="changePic"><img id="captcha" src="/captcha.png" alt=""></a>
        </div>
        <div class="checkbox">
            <label> <input value="yes" name="rememberme" type="checkbox" value="rememberme">
                7天内自动登录
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
        <div class="loginLinks" style="float: right">
            <a href="#" target="_blank">忘记密码？</a>|<a href="/form.jsp" target="_blank">注册</a>
        </div>
    </form>

</div>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/cryptojs/core-min.js"></script>
<script src="/static/js/cryptojs/md5-min.js"></script>>
<script src=""></script>
<script>
    $(function () {
        $("#submitBtn").click(function () {
            var pwd = $("#password").val();
            $("#password").val(pwd);
            $("#loginForm").submit();
        });
        $("#changePic").click(function () {
            $("#captcha").attr("src", "/captcha.png?t=" + new Date().getTime())
        });
    });
</script>
</body>
</html>