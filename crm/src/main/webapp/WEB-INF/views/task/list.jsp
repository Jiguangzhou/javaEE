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
    <title>DreamCRM-待办事项</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/plugins/fontawesome/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="/static/plugins/fullcalendar/fullcalendar.min.css">
    <link rel="stylesheet" href="/static/plugins/colorpicker/bootstrap-colorpicker.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../include/header.jsp" %>
    <jsp:include page="../include/leftSide.jsp">
        <jsp:param name="menu" value="task"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h4></h4>
            <ol class="breadcrumb">
                <li><a href="/home"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">待办事项</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-8">
                    <div class="box box-solid">
                        <div class="box-body">
                            <div id="calendar"></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">我的待办</h3>
                            <div class="box-tools">
                                <button class="btn btn-success btn-xs" id="addBtn"><i class="fa fa-plus"></i>添加</button>
                            </div>
                        </div>
                        <div class="box-body">

                        </div>
                    </div>
                    <div class="box box-danger">
                        <div class="box-header with-border">
                            <h3 class="box-title">已经延期的事项</h3>
                        </div>
                        <div class="box-body">

                        </div>
                    </div>
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
                <h4 class="modal-title">添加待办事项</h4>
            </div>
            <div class="modal-body">
                <form id="addForm" method="post" action="/task/add">
                    <div class="form-group">
                        <label>待办内容</label>
                        <input type="text" class="form-control" name="title" id="taskTitle">
                    </div>
                    <div class="form-group">
                        <label>开始日期</label>
                        <input type="text" class="form-control" name="start" id="startTime">
                    </div>
                    <div class="form-group">
                        <label>结束日期</label>
                        <input type="text" class="form-control" name="end" id="endTime">
                    </div>
                    <div class="form-group">
                        <label>提醒时间</label>
                        <div>
                            <select name="hour" style="width: 100px">
                                <option value="0">0</option>
                                <option value="2">2</option>
                                <option value="4">4</option>
                                <option value="6">6</option>
                                <option value="8">8</option>
                                <option value="10">10</option>
                                <option value="12">12</option>
                                <option value="14">14</option>
                                <option value="16">16</option>
                                <option value="18">18</option>
                                <option value="20">20</option>
                                <option value="22">22</option>
                            </select>
                            :
                            <select name="min" style="width: 100px">
                                <option value="0">0</option>
                                <option value="10">10</option>
                                <option value="20">20</option>
                                <option value="30">30</option>
                                <option value="40">40</option>
                                <option value="50">50</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <lable>文本颜色</lable>
                        <input type="text" class="form-control" name="color" id="color" value="#ffff">
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
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="/static/plugins/moment/moment.min.js"></script>
<script src="/static/plugins/fullcalendar/fullcalendar.js"></script>
<script src="/static/plugins/fullcalendar/lang-all.js"></script>
<script src="/static/plugins/colorpicker/bootstrap-colorpicker.js"></script>
<script>
    $(function () {
        //文本颜色
        $("#color").colorpicker({
            color:'#ffff'
        });
        //开始结束时间
        $("#startTime,#endTime").datepicker({
            format:'yyyy-mm-dd',
            autoclose:true,
            language:'zh-CN',
            startDate:moment().format("YYYY-MM-DD"),
            todayHighlight:true
        });

        //添加待办
        $("#addBtn").click(function () {
            $("#addForm")[0].reset();
            $("#startTime").val(moment().format("YYYY-MM-DD"));
            $("#endTime").val(moment().format("YYYY-MM-DD"));

            $("#addModal").modal({
                show:true,
                backdorp:'static',
                keyboard:false
            });
        });
        $("#saveBtn").click(function(){
            $("#addForm").submit();
        });

        //日历
        $('#calendar').fullCalendar({
            lang: 'zh-cn',
            dayClick: function(date, jsEvent, view) {
                $("#addForm")[0].reset();
                $("#startTime").val(date.format());
                $("#endTime").val(date.format());
                $("#addModal").modal({
                    show:true,
                    backdrop:'static'
                });
            },
            eventClick: function(calEvent, jsEvent, view) {
            },
            buttonText: {
                today: "今天"
            },
            events: "/task/load"
        });
        $("#saveBtn").click(function () {
            if (!$("#taskTitle").val()){
                $("#taskTitle").focus();
            }
            $.post("/task/add",$("#addForm").serialize()).done(function (data) {
                if (data == "success"){
                    var myEvent = {
                        title:$("#taskTitle").val(),
                        start:$("#startTime").val(),
                        end:$("#endTime").val(),
                        color:$("#color").val()
                    };
                    $calendar.fullCalendar('renderEvent',myEvent);
                    $("#addModal").modal('hide');
                }
            }).fail(function () {
                alert("服务器异常");
            });
        });
    });
</script>
</body>
</html>
