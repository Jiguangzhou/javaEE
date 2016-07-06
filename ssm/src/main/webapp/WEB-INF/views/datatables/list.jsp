<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/js/datatables/css/dataTables.bootstrap.min.css">
</head>
<body>
<div class="container">

    <table id="dataTable" class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>名称</th>
            <th>作者</th>
            <th>价格</th>
            <th>数量</th>
            <th>分类</th>
            <th>出版社</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/datatables/js/jquery.dataTables.min.js"></script>
<script src="/static/js/datatables/js/dataTables.bootstrap.min.js"></script>
<script>
    $(function () {
        $("#dataTable").DataTable({
            "serverSide": true,//表示所有的操作都在服务端进行
            "ajax": "/datatable/data.json",//服务端的url
            "columns": [    //配置json中data属性数据key和表中列的对应关系
                {"data": "id"},
                {"data": "bookname"},
                {"data": "bookauthor"},
                {"data": "bookprice"},
                {"data": "booknum"},
                {"data": "bookType.booktype"},
                {"data": "publisher.pubname"},
                {"data": function (row) {
                        return "#"
                    }
                }
            ]
        });
    });
</script>
</body>
</html>
