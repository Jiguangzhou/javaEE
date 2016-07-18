<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>DreamCRM-业务详情-${sale.name}</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/plugins/fontawesome/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/plugins/simditor/styles/simditor.css">
    <link rel="stylesheet" href="/static/plugins/webuploader/webuploader.css">
    <link rel="stylesheet" href="/static/plugins/colorpicker/bootstrap-colorpicker.css">
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">

    <style>
        .timeline > li > .timeline-item {
            box-shadow: none;
            -webkit-box-shadow: none;
        }

        .files li {
            padding: 5px;
        }
    </style>
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
            <h1>　</h1>
            <ol class="breadcrumb">
                <li><a href="/sale"><i class="fa fa-dashboard"></i> 业务列表</a></li>
                <li class="active">${sale.name}</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title">${sale.name}</h3>
                    <shiro:hasRole name="经理">
                        <div class="box-tools">
                            <button class="btn btn-danger btn-xs" id="delBtn"><i class="fa fa-trash"></i> 删除</button>
                        </div>
                    </shiro:hasRole>
                </div>
                <div class="box-body">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td style="width: 100px">关联客户</td>
                            <td style="width: 200px"><a href="/customer/${sale.customerid}"
                                                        target="_blank">${sale.customername}</a></td>
                            <td style="width: 100px">价值</td>
                            <td style="width: 200px">￥<fmt:formatNumber value="${sale.price}"/></td>
                        </tr>
                        <tr>
                            <td>跟进情况</td>
                            <td>${sale.progress} <a href="javascript:;" id="editProgress">修改</a></td>
                            <td>最后跟进时间</td>
                            <td>${empty sale.lasttime ? '无' : sale.lasttime}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="row">
                <div class="col-md-8">
                    <div class="box box-default">
                        <div class="box-header with-border">
                            <h3 class="box-title">跟进记录</h3>
                            <div class="box-tools">
                                <button class="btn btn-xs btn-success" id="addLogBtn"><i class="fa fa-plus"></i> 新增记录
                                </button>
                            </div>
                        </div>
                        <div class="box-body">
                            <ul class="timeline">
                                <c:forEach items="${saleLogList}" var="log">
                                    <li>
                                        <c:choose>
                                            <c:when test="${log.type == 'auto'}">
                                                <i class="fa fa-history bg-yellow"></i>
                                            </c:when>
                                            <c:otherwise>
                                                <i class="fa fa-commenting bg-aqua"></i>
                                            </c:otherwise>
                                        </c:choose>

                                        <div class="timeline-item">
                                            <span class="time"><i class="fa fa-clock-o"></i> <span class="timeago" title="${log.createtime}"></span></span>
                                            <h3 class="timeline-header no-border">
                                                    ${log.context}
                                            </h3>
                                        </div>
                                    </li>
                                </c:forEach>
                                <li>
                                    <i class="fa fa-clock-o bg-gray"></i>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="box box-default">
                        <div class="box-header">
                            <h3 class="box-title"><i class="fa fa-file-o"></i>相关资料</h3>
                            <div class="box-tools">
                                <div id="uploadBtn"><span class="text"><i class="fa fa-upload"></i></span></div>
                            </div>
                        </div>
                        <div class="box-body">
                            <ul class="list-unstyled file">
                                <c:if test="${empty saleDocList}">
                                    暂无资料
                                </c:if>
                                <c:forEach items="${saleDocList}" var="saleDoc">
                                    <li><a href="">${saleDoc.name}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="box box-default">
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-calendar-check-o"></i>待办事项</h3>
                            <div class="box-tools">
                                <button class="btn btn-default btn-xs" id="addTask"><i class="fa fa-plus"></i></button>
                            </div>
                        </div>
                        <div class="box-body">
                            <h5>暂无事项</h5>
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
<div class="modal fade" id="addLogModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加跟进</h4>
            </div>
            <div class="modal-body">
                <form id="addLogForm" action="/sale/log/add" method="post">
                    <input type="hidden" name="saleid" value="${sale.id}">
                    <div class="form-group">
                        <textarea name="context" id="context"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="saveLogBtn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- REQUIRED JS SCRIPTS -->
<div class="modal fade" id="editProModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改进度</h4>
            </div>
            <div class="modal-body">
                <form id="eidtProForm" action="/sale/progress" method="post">
                    <input type="hidden" name="id" value="${sale.id}">
                    <div class="form-group">
                        <label>当前进度</label>
                        <select name="progress" class="form-control">
                            <option value="初次接触">初次接触</option>
                            <option value="确认意向">确认意向</option>
                            <option value="提供合同">提供合同</option>
                            <option value="交易完成">交易完成</option>
                            <option value="交易搁置">交易搁置</option>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="saveProBtn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade" id="addModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加待办事项</h4>
            </div>
            <div class="modal-body">
                <form id="addForm" action="/task/add" method="post">
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
                <button type="button" class="btn btn-primary" id="addBtn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- jQuery 2.2.0 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/plugins/timeago/timeago.js"></script>
<script src="/static/plugins/simditor/scripts/module.min.js"></script>
<script src="/static/plugins/simditor/scripts/hotkeys.min.js"></script>
<script src="/static/plugins/simditor/scripts/uploader.min.js"></script>
<script src="/static/plugins/simditor/scripts/simditor.min.js"></script>
<script src="/static/plugins/webuploader/webuploader.min.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="/static/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script>
    $(function () {
        //相对时间
        $(".timeago").timeago();

        var edit = new Simditor({
            textarea: $("#context"),
            placeholder: '请输入跟进内容',
            toolbar: false
        });
        //添加跟进
        $("#addLogBtn").click(function () {
            $("#addLogModal").modal({
                show: true,
                backdrop: 'static',
                keyboard: false
            });
        });
        $("#saveLogBtn").click(function () {
            if (edit.getValue()) {
                $("#addLogForm").submit();
            } else {
                edit.focus();
            }
        });
        //修改跟进情况
        $("#editProgress").click(function () {
            $("#editProModal").modal({
                show: true,
                backdrop: 'static',
                keyboard: false
            });
        });
        $("#saveProBtn").click(function () {
            $("#eidtProForm").submit();
        });

        //删除业务(经理权限)
        <shiro:hasRole name="经理">
        $("#delBtn").click(function () {
            if (confirm("确认要删除这项业务么？")) {
                window.location.href = "/sale/del/${sale.id}";
            }
        });
        </shiro:hasRole>

        //文件的上传
        var uploader = WebUploader.create({
            swf: "/static/plugins/webuploader/Uploader.swf",
            pick: "#uploadBtn",
            server: "/sale/upload",
            fileValL: "file",
            formData: {"saleid":"${sale.id}"},
            auto: true
        });
        //文件上传结果
        uploader.on("startUpload",function(){
            $("#uploadBtn .text").html('<i class="fa fa-spinner fa-spin"></i>');
        });
        uploader.on('uploadSuccess', function( file,data ) {
            if(data._raw == "success") {
                window.history.go(0);
            }
        });
        uploader.on( 'uploadError', function( file ) {
            alert("上传失败");
        });
        uploader.on( 'uploadComplete', function( file ) {
            $("#uploadBtn .text").html('<i class="fa fa-upload"></i>').removeAttr("disabled");
        });
        //新建事项
        $("#color").colorpicker({
            color:'#fffff'
        });
        $("#startTime,#endTime").datepicker({
            format: 'yyyy-mm-dd',
            autoclose:true,
            language:'zh-CN',
            todayHighlight:true
        });

        $("#addTask").click(function(){
            $("#addModal").modal({
                show:true,
                backdrop:'static'
            });
        });
        $("#addBtn").click(function(){
            $("#addForm").submit();
        });
    });
</script>
</body>
</html>