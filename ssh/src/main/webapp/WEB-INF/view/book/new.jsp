<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加书籍</title>
    <link rel="stylesheet" href="/static/css/bootstrap.css">
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h3>添加书籍</h3>
        </div>
        <div class="row">
            <div class="col-xs-4">

                <form action="" method="post">
                    <div class="form-group">
                        <label>书籍名称</label>
                        <input type="text" name="bookname" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>作者</label>
                        <input type="text" name="bookauthor" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>价格</label>
                        <input type="text" name="bookprice" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>数量</label>
                        <input type="text" name="booknum" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>类型</label>
                        <select name="bookType.id" class="form-control">
                            <c:forEach items="${bookTypeList}" var="type">
                                <option value="${type.id}">${type.booktype}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>出版社</label>
                        <select name="publisher.id" class="form-control">
                            <c:forEach items="${publisherList}" var="publisher">
                                <option value="${publisher.id}">${publisher.pubname}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-primary pull-right">保存</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
