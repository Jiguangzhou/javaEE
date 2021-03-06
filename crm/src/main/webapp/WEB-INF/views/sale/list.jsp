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
    <title>DreamCRM-业务管理</title>
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
    <link rel="stylesheet" href="/static/plugins/daterangepicker/daterangepicker.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../include/header.jsp" %>
    <jsp:include page="../include/leftSide.jsp">
        <jsp:param name="menu" value="sale"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>　　</h1>
            <ol class="breadcrumb">
                <li><a href="/home"><i class="fa fa-dashboard"></i>首页</a></li>
                <li class="active">业务列表</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="box box-default collapsed-box">
                <div class="box-header with-border">
                    <h3 class="box-title">搜索</h3>
                    <div class="box-tools">
                        <button class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"><i
                                class="fa fa-plus"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <form class="form-inline" method="get">
                        <input type="hidden" id="search_starttime">
                        <input type="hidden" id="search_endtime">
                        <input type="text" class="form-control" name="salename" id="searchName" placeholder="业务名称">
                        <select class="form-control" id="searchProgress" name="progress">
                            <option value="">当前进度</option>
                            <option value="初次接触">初次接触</option>
                            <option value="确认意向">确认意向</option>
                            <option value="提供合同">提供合同</option>
                            <option value="交易完成">交易完成</option>
                            <option value="交易搁置">交易搁置</option>
                        </select>
                        <input type="text" id="Date" class="form-control" placeholder="跟进时间">
                        <button type="button" id="searchBtn" class="btn btn-default"><i class="fa fa-search"></i> 搜索
                        </button>
                    </form>
                </div>
            </div>
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">业务列表</h3>
                    <div class="box-tools pull-right">
                        <a href="javascript:;" id="addBtn" class="btn btn-xs btn-success"><i class="fa fa-plus"></i>
                            新建业务</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table table-bordered" id="saleTable">
                        <thead>
                        <tr>
                            <th>业务名称</th>
                            <th>价格</th>
                            <th>跟进情况</th>
                            <th>最后跟进时间</th>
                            <th>关联客户</th>
                            <th>所属员工</th>
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
<div class="modal fade" id="addModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新建业务</h4>
            </div>
            <div class="modal-body">
                <form id="addForm">
                    <div class="form-group">
                        <label>业务名称</label>
                        <input type="text" class="form-control" name="name">
                    </div>
                    <div class="form-group">
                        <label>价格</label>
                        <input type="text" class="form-control" name="price">
                    </div>
                    <div class="form-group">
                        <label>跟进情况</label>
                        <select name="progress" class="form-control">
                            <option value="初次接触">初次接触</option>
                            <option value="确认意向">确认意向</option>
                            <option value="提供合同">提供合同</option>
                            <option value="交易完成">交易完成</option>
                            <option value="交易搁置">交易搁置</option>
                        </select>
                    </div>
                    <div class="form-group" id="customerList">
                        <label>关联客户</label>
                        <select name="customerid" class="form-control">
                            <option value=""></option>
                            <c:forEach items="${customerList}" var="customer">
                                <option value="${customer.id}">${customer.name}</option>
                            </c:forEach>
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
<script src="/static/plugins/daterangepicker/daterangepicker.js"></script>
<script>
    $(function () {
        var dataTable = $("#saleTable").DataTable({
            serverSide: true,
            ordering: false,
            searching: false,
            "autoWidth": false,
//            ajax: "/sale/load",
            ajax:{
                url:"/sale/load",
                data:function(dataS){
                    dataS.salename=$("#searchName").val();
                    dataS.progress=$("#searchProgress").val();
                    dataS.startdate=$("#search_starttime").val();
                    dataS.enddate=$("#search_endtime").val();
                }
            },
            columns: [
                {
                    "data": function (row) {
                        return "<a href='/sale/" + row.id + "'>" + row.name + "</a>";
                    }
                },
                {
                    "data": function (row) {
                        return "￥" + row.price;
                    }
                },
                {
                    "data": function (row) {
                        if (row.progress == '交易完成') {
                            return "<span class='label label-success'>" + row.progress + "</span>";
                        }
                        if (row.progress == '交易搁置') {
                            return "<span class='label label-danger'>" + row.progress + "</span>";
                        }
                        return row.progress;
                    }
                },
                {"data": "lasttime"},
                {
                    "data": function (row) {
                        return "<a href='/customer/" + row.customerid + "'>" + row.customername + "</a>";
                    }
                },
                {"data": "username"}
            ],

            "language": {
                //定义中文
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

        //新建业务
        $("#addForm").validate({
            errorClass: "text-danger",
            errorElement: "span",
            rules: {
                name: {
                    required: true
                },
                price: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: "请输入业务名称"
                },
                price: {
                    required: "请输入价格"
                }
            },
            submitHandler: function (form) {
                $.post("/sale/add", $(form).serialize()).done(function (data) {
                    if (data == "success") {
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
                show: true,
                backdrop: 'static',
                keyboard: false
            });
        });
        $("#saveBtn").click(function () {
            $("#addForm").submit();
        });

        //搜索
        $("#searchBtn").click(function () {
            dataTable.ajax.reload();
        });

        //daterangepicker
        $("#Date").daterangepicker({
            format: "YYYY-MM-DD",
            locale: {
                "applyLabel": "选择",
                "cancelLabel": "取消",
                "fromLabel": "从",
                "toLabel": "到",
                "customRangeLabel": "自定义",
                "weekLabel": "周",
                "daysOfWeek": [
                    "一",
                    "二",
                    "三",
                    "四",
                    "五",
                    "六",
                    "日"
                ],
                "monthNames": [
                    "一月",
                    "二月",
                    "三月",
                    "四月",
                    "五月",
                    "六月",
                    "七月",
                    "八月",
                    "九月",
                    "十月",
                    "十一月",
                    "十二月"
                ],
                "firstDay": 1
            }
        });
        $('#Date').on('apply.daterangepicker', function(ev, picker) {
            $("#search_starttime").val(picker.startDate.format('YYYY-MM-DD'));
            $("#search_endtime").val(picker.endDate.format('YYYY-MM-DD'));
        });
        });
</script>
</body>
</html>
