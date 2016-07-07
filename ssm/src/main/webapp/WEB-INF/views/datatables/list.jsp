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
    <div>
        <h3>图书列表</h3>
    </div>
    <a href="javascript:;" id="addBtn" class="btn btn-success" style="margin-bottom: 10px">添加新书籍</a>
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
<!-- Modal -->
<div class="modal fade" id="addBookModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加新书籍</h4>
            </div>
            <div class="modal-body">
                <form id="saveForm">
                    <div class="form-group">
                        <label>书籍名称</label>
                        <input type="text" class="form-control" name="bookname">
                    </div>
                    <div class="form-group">
                        <label>书籍作者</label>
                        <input type="text" class="form-control" name="bookauthor">
                    </div>
                    <div class="form-group">
                        <label>书籍价格</label>
                        <input type="text" class="form-control" name="bookprice">
                    </div>
                    <div class="form-group">
                        <label>书籍数量</label>
                        <input type="text" class="form-control" name="booknum">
                    </div>
                    <div class="form-group">
                        <label>书籍分类</label>
                        <select class="form-control" name="typeid">
                            <c:forEach items="${types}" var="type">
                                <option value="${type.id}">${type.booktype}</option>
                            </c:forEach>

                        </select>
                    </div>
                    <div class="form-group">
                        <label>出版社</label>
                        <select class="form-control" name="pubid">
                            <c:forEach items="${pubs}" var="pub">
                                <option value="${pub.id}" }>${pub.pubname}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="saveBtn">保存</button>
            </div>
        </div>
    </div>
</div>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/datatables/js/jquery.dataTables.min.js"></script>
<script src="/static/js/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script>
    $(function () {
        var dataTable = $("#dataTable").DataTable({
            "lengthMenu": [5, 10, 15, 20],
            "serverSide": true,//表示所有的操作都在服务端进行
            "ajax": "/datatable/data.json",//服务端的url
            "order":[0,'desc'],
            "columns": [    //配置json中data属性数据key和表中列的对应关系
                {"data": "id", "name": "id"},
                {"data": "bookname"},
                {"data": "bookauthor"},
                {"data": "bookprice", "name": "bookprice"},
                {"data": "booknum", "name": "booknum"},
                {"data": "bookType.booktype"},
                {"data": "publisher.pubname"},
                {
                    "data": function (row) {
                        return "<a href='javascript:;' class='editLink' rel='"+row.id+"'>修改</a>|<a href='javascript:;' class='delLink' rel='"+row.id+"'>删除</a>"
                    }
                }
            ],
            columnDefs: [
                //定义列的特征
                {targets: [0], visible: false},
                {targets: [1, 2, 6, 7], orderable: false}
            ],
            "language": {
                //定义中文
                "search": "请输入书籍名称:",
                "zeroRecords": "没有匹配的数据",
                "lengthMenu": "显示 _MENU_ 条数据",
                "info": "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
                "infoFiltered": "(从 _MAX_ 条数据中获得)",
                "loadingRecords": "加载中...",
                "processing": "处理中...",
                "paginate": {
                    "first": "首页",
                    "last": "末页",
                    "next": "下一页",
                    "previous": "上一页"
                }
            }

        });
        //添加书籍

        $("#saveForm").validate({
            errorElement: "span",
            errorClass: "text-danger",
            rules: {
                bookname: {
                    required: true
                },
                bookauthor: {
                    required: true
                },
                bookprice: {
                    required: true,
                    number: true
                },
                booknum: {
                    required: true,
                    digits: true
                }
            },
            messages: {
                bookname: {
                    required: "请输入书籍名称"
                },
                bookauthor: {
                    required: "请输入作者"
                },
                bookprice: {
                    required: "请输入价格",
                    number: "请输入正确价格"
                },
                booknum: {
                    required: "请输入数量",
                    digits: "请输入正确的数量"
                }
            },
            submitHandler: function (form) {
                $.post("/datatable/new", $(form).serialize())
                        .done(function (data) {
                            if (data == "success") {
                                $("#addBookModal").modal("hide");
                                dataTable.ajax.reload();
                            }
                        })
                        .fail(function () {
                            alert("请求服务器异常")
                        });
            }
        });


        $("#addBtn").click(function () {
            $("#saveForm")[0].reset();//让表单form进行自动重置
            $("#addBookModal").modal({
                show: true,
                backdrop: 'static',
                keyboard: false
            });
        });
        $("#saveBtn").click(function () {
            $("#saveForm").submit();
        });

        //删除书籍(事件委托)可以给当前或未来出现的符合选择器的元素绑定事件
        $(document).delegate(".delLink","click",function(){
            var id = $(this).attr("rel");
            if(confirm("确定要删除吗?")) {
                $.get("/datatable/"+id+"/del").done(function(data){
                    if(data == "success") {
                        dataTable.ajax.reload();
                    }
                }).fail(function(){
                    alert("请求服务器异常");
                });
            }
        });
        
        //修改书籍
        $(document).delegate(".editLink","click", function () {
            var id = $(this).attr("rel");
            $.get("/datatable/"+id+".json").done(function (data) {
                alert(data);
            }).fail(function () {
                alert("请求服务器异常")
            })
        })
    });
</script>
</body>
</html>
