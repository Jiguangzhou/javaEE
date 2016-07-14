<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>DreamCRM-客户列表</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/plugins/fontawesome/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/plugins/datatables/css/dataTables.bootstrap.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../include/header.jsp"%>
    <jsp:include page="../include/leftSide.jsp">
        <jsp:param name="menu" value="customer"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>　　</h1>
            <ol class="breadcrumb">
                <li><a href="/home"><i class="fa fa-list"></i> 首页</a></li>
                <li class="active">客户列表</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">客户列表</h3>
                    <div class="box-tools pull-right">
                        <a href="javascript:;" id="addBtn" class="btn btn-xs btn-success"><i class="fa fa-plus"></i> 添加客户</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table table-bordered" id="customerTable">
                        <thead>
                        <tr>
                            <th style="width:20px;"></th>
                            <th>客户名称</th>
                            <th>联系电话</th>
                            <th>地址</th>
                            <th>微信</th>
                            <th>电子邮箱</th>
                            <th>客户等级</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->
<%--添加客户--%>
<div class="modal fade" id="addModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加客户</h4>
            </div>
            <div class="modal-body">
                <form id="addForm">
                    <div class="form-group">
                        <label>类型</label>
                        <div>
                            <label class="radio-inline">
                                <input type="radio" name="type" checked id="radioperson" value="person">个人
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="type" id="radiocompany" value="company">公司
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>客户名称</label>
                        <input type="text" class="form-control" name="name">
                    </div>
                    <div class="form-group">
                        <label>联系电话</label>
                        <input type="text" class="form-control" name="tel">
                    </div>
                    <div class="form-group">
                        <label>地址</label>
                        <input type="text" class="form-control" name="address">
                    </div>
                    <div class="form-group">
                        <label>微信</label>
                        <input type="text" class="form-control" name="weixin">
                    </div>
                    <div class="form-group">
                        <label>电子邮箱</label>
                        <input type="text" class="form-control" name="email">
                    </div>
                    <div class="form-group" id="companyList">
                        <label>所属公司</label>
                        <select name="companyid" class="form-control">
                            <option value=""></option>
                            <c:forEach items="${companyList}" var="company">
                                <option value="${company.id}">${company.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>客户等级</label>
                        <select name="level" class="form-control">
                            <option value=""></option>
                            <option value="★">★</option>
                            <option value="★★">★★</option>
                            <option value="★★★">★★★</option>
                            <option value="★★★★">★★★★</option>
                            <option value="★★★★★">★★★★★</option>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="saveBtn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/plugins/datatables/js/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="/static/plugins/moment/moment.min.js"></script>
<script src="/static/plugins/jQuery/jquery.validate.min.js"></script>
<script>
    $(function () {
        var dataTable = $("#customerTable").DataTable({
            serverSide:true,
            ajax:"/customer/load",
            ordering:false,
            "autoWidth":false,
            columns:[
                {"data": function (row) {
                    if (row.type== 'company'){
                        return "<i class='fa fa-bank'></i>"
                    }
                    return "<i class='fa fa-user'></i>"
                }},
                {"data": function (row) {
                    if (row.companyname){
                        return row.name +"-"+row.companyname;
                    }
                    return row.name;
                }},
                {"data":"tel"},
                {"data":"address"},
                {"data":"weixin"},
                {"data":"email"},
                {"data":"level"},
                {"data":function () {
                    return "";
                }}
            ],
            "language": { //定义中文
                "search": "请输入客户名称或电话:",
                "zeroRecords": "没有匹配的数据",
                "lengthMenu": "显示 _MENU_ 条数据",
                "info": "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
                "infoFiltered": "(从 _MAX_ 条数据中过滤得来)",
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
        //添加客户
        $("#addForm").validate({
            errorClass:"text-danger",
            errorElement:"span",
            rules:{
                name:{
                    required:true
                },
                tel:{
                    required:true
                }
            },
            messages:{
                name:{
                    required:"请输入客户姓名"
                },
                tel:{
                    required:"请输入客户电话"
                }
            },
            submitHandler: function (form) {
                $.post("/customer/add",$(form).serialize()).done(function (data) {
                    if (data == "success"){
                        $("#addModal").modal('hide');
                       dataTable.ajax.reload();
                    }
                }).fail(function () {
                    alert("服务器异常");
                });
            }
        });
        $("#addBtn").click(function () {
            $("#addForm")[0].reset();
            $("#addModal").modal({
                show:true,
                dropback:'static',
                keyboard:false
            });
        });
        $("#radioperson").click(function () {
            if($(this)[0].checked){
                $("#companyList").show();
            }
        });
        $("#radiocompany").click(function () {
            if ($(this)[0].checked){
                $("#companyList").hide();
            }
        });
        $("#saveBtn").click(function () {
            $("#addForm").submit();
        });
    });
</script>
</body>
</html>
