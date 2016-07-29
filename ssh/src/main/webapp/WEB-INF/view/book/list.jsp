<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/static/css/bootstrap.css">
</head>
<body>
<div class="container">
    <div class="page-header">
        <h3>SSH</h3>
    </div>
    <div class="well well-sm">
        <form method="get" class="form-inline">
            <div class="form-group">
                <input type="text" placeholder="书籍名称" name="q_s_like_bookname_or_bookauthor" value="${q_s_like_bookname_or_bookauthor}" class="form-control">
            </div>
            <div class="form-group">
                <input type="text" placeholder="最低价格" name="q_f_ge_bookprice" value="${q_f_ge_bookprice}" class="form-control"> - <input type="text" placeholder="最高价格" name="q_f_le_bookprice" value="${q_f_le_bookprice}" class="form-control">
            </div>
            <div class="form-group">
                <select name="q_i_eq_bookType.id" class="form-control">
                    <option value="">请选择类型</option>
                    <c:forEach items="${bookTypeList}" var="type">
                        <option value="${type.id}" ${requestScope['q_i_eq_bookType.id'] == type.id ? 'selected' : '' }>${type.booktype}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <select name="q_i_eq_publisher.id" class="form-control">
                    <option value="">请选择出版社</option>
                    <c:forEach items="${publisherList}" var="pub">
                        <option value="${pub.id}" ${requestScope['q_i_eq_publisher.id'] == pub.id ? 'selected' : ''} >${pub.pubname}</option>
                    </c:forEach>
                </select>
            </div>
            <button class="btn btn-default">搜索</button>
        </form>
    </div>
    <a href="/book/add" class="btn btn-info" style="margin-bottom: 20px">添加书籍</a>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>名称</th>
            <th>作者</th>
            <th>价格</th>
            <th>数量</th>
            <th>类型</th>
            <th>出版社</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.items}" var="book">
            <tr>
                <td>${book.bookname}</td>
                <td>${book.bookauthor}</td>
                <td>${book.bookprice}</td>
                <td>${book.booknum}</td>
                <td>${book.bookType.booktype}</td>
                <td>${book.publisher.pubname}</td>
                <td>

                    <a href="/book/${book.id}/edit" class="btn btn-primary">修改</a>
                    <a href="javascript:;" rel="${book.id}" class="delLink btn btn-danger">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <ul class="pagination pull-right" id="page"></ul>
</div>
</body>
<script src="/static/js/jquery-2.2.3.min.js"></script>
<script src="/static/js/jquery.twbsPagination.min.js"></script>
<script>
    $(function () {

        $(".delLink").click(function () {
            var id = $(this).attr("rel");
            if (confirm("确定删除么")) {
                window.location.href = "book/" + id + "/del"
            }
        });

        $("#page").twbsPagination({
            totalPages:${page.totalPages},
            visiblePages:5,
            first:'第一页',
            prev:'首页',
            next:'下一页',
            last:'末页',
            href:'?p={{number}}&q_s_like_bookname_or_bookauthor=${q_s_like_bookname_or_bookauthor}&q_f_ge_bookprice=${q_f_ge_bookprice}&q_f_le_bookprice=${q_f_le_bookprice}&q_i_eq_bookType.id=${requestScope['q_i_eq_bookType.id']}&q_i_eq_publisher.id=${requestScope['q_i_eq_publisher.id']}'
        });
    });
</script>
</html>

