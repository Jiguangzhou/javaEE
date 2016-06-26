<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-4">
            <form id="regForm">
                <legend>注册表单</legend>
                <div class="form-group">
                    <lable>帐号</lable>
                    <input type="text" class="form-control" name="username">
                </div>
                <div class="form-group">
                    <lable>密码</lable>
                    <input type="text" class="form-control" name="password">
                </div>
                <div class="form-group">
                    <lable>个人简介</lable>
                    <input type="text" class="form-control" name="other">
                </div>
                <div class="form-group">
                    <button type="button" id="subbtn" class="btn btn-primary">注册</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="/static/js/jquery-1.11.1.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script>
    $(function () {
        $("regForm").validate({
            errorElement: "span",
            errorClass: "text-danger",
            rules: {
                username: {
                    required: true,
                    minlength: 4,
                    remote: "/checkusername"
                },
                password: {
                    required: true,
                    rangelength: [6, 18]
                },
                other: {
                    required: true
                }
            },
            message: {
                username: {
                    required: "请输入帐号",
                    minlength: "帐号不得低于三位",
                    remote: "帐号已被占用"
                },
                password: {
                    required: "请输入密码",
                    rangelength: "密码长度为6-18位"
                },
                other: {
                    requried: "请输入个人简介"
                }
            },
            submitHandler: function (form) {
                var $btn = $("subbtn");
                $.ajax({
                    url: "/reg",
                    type: "post",
                    data: $(form).serialize(),
                    beforeSend: function () {
                        $btn.text("注册中...").attr("disabled", "disabled");
                    },
                    success: function () {
                        alret("注册成功");
                    },
                    error: function () {
                        alert("服务器繁忙，请稍后再试");
                    },
                    complete: function () {
                        $btn.text("注册").removeAttr("disabled");
                    }

                });
            }

        });
        $("#subbtn").click(function () {
            $("#regForm").submit();
        });
    });
</script>
</body>
</html>
