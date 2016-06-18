<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div class="page-header">
            <h3>File List</h3>
        </div>
        <div class="panel panel-default"></div>
        <div class="panel-heading">
            文件列表
        </div>
        <div class="panel-body">
            <table class="table">
                <thead>
                    <tr>
                        <th>文件名称</th>
                        <th>文件大小</th>
                        <th>MD5</th>
                        <th>操作列表</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${documentList}" var="doc">
                        <tr>
                            <td>${doc.filename}</td>
                            <td>${doc.displaysize}</td>
                            <td>${doc.md5}</td>
                            <td>
                                <a href="">下载</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>