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
    <div class="col-xs-5">
        <form action="/servlet/upload" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <lable>文件描述</lable>
                <input type="text" name="fileDesc" class="form-control">
            </div>
            <div class="form-group">
                <lable>上传文件</lable>
                <input type="file" name="doc" class="form-control">
                <button class="btn btn-primary">保存</button>
            </div>
    </form>
    </div>

</div>
</body>
</html>