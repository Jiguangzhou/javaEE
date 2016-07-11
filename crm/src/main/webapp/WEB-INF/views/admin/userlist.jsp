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
    <title>DreamCRM-员工管理</title>
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

    <%@include file="../include/header.jsp" %>
    <%@include file="../include/leftSide.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <section class="content-header">
            <h1>员工管理</h1>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">员工列表</h3>
                    <div class="box-tools pull-right">
                        <a href="javascript:;" id="addBtn" class="btn btn-xs btn-success"><li class="fa fa-plus"></li> 添加</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table table-bordered" id="userTable">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>账户</th>
                            <th>姓名</th>
                            <th>微信ID</th>
                            <th>身份</th>
                            <th>状态</th>
                            <th>创建时间</th>
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
<%--Modal--%>
<%--添加新用户--%>
<div class="modal fade" id="addModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加用户</h4>
            </div>
            <div class="modal-body">
                <form id="addForm">
                    <div class="form-group">
                        <label>账号(用于登录系统)</label>
                        <input type="text" class="form-control" name="username">
                    </div>
                    <div class="form-group">
                        <label>密码(默认000000)</label>
                        <input type="text" class="form-control" name="password" value="000000">
                    </div>
                    <div class="form-group">
                        <label>用户姓名</label>
                        <input type="text" class="form-control" name="realname">
                    </div>
                    <div class="form-group">
                        <label>微信ID</label>
                        <input type="text" class="form-control" name="weixin">
                    </div>
                    <div class="form-group">
                        <label>身份</label>
                        <select class="form-control" name="roleid">
                            <c:forEach items="${roleList}" var="role">
                                <option value="${role.id}">${role.rolename}</option>
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
<%--修改员工信息--%>
<div class="modal fade" id="editModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改信息</h4>
            </div>
            <div class="modal-body">
                <form id="editForm">
                    <input type="hidden" name="id" id="edit_id">
                    <div class="form-group">
                        <label>账号(用于登录系统)</label>
                        <input type="text" class="form-control" disabled name="username" id="edit_username">
                    </div>
                    <div class="form-group">
                        <label>员工姓名</label>
                        <input type="text" class="form-control" name="realname" id="edit_realname">
                    </div>
                    <div class="form-group">
                        <label>微信ID</label>
                        <input type="text" class="form-control" name="weixin" id="edit_weixin">
                    </div>
                    <div class="form-group">
                        <label>身份</label>
                        <select class="form-control" name="roleid" id="edit_roleid">
                            <c:forEach items="${roleList}" var="role">
                                <option value="${role.id}">${role.rolename}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>账号状态</label>
                        <select name="enable" id="edit_enable" class="form-control">
                            <option value="true">正常</option>
                            <option value="false">禁用</option>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="editBtn">保存</button>
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
<%--DataTables--%>
<script src="/static/plugins/datatables/js/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="/static/plugins/moment/moment.min.js"></script>
<script src="/static/plugins/jQuery/jquery.validate.min.js"></script>
<script>
    $(function () {
        var dataTable = $("#userTable").DataTable({
            serverSide:true,
            ajax:"/admin/load",
            ordering:false,
            "autoWidth": false,
            columns:[
                {"data":"id"},
                {"data":"username"},
                {"data":"realname"},
                {"data":"weixin"},
                {"data":"role.rolename"},
                {"data": function (row) {
                    if (row.enable){
                        return "<span class='label label-success'>正常</span>";
                    }else {
                        return "<span class='label label-danger'>禁用</span>";
                    }
                }},
                {"data":function(row){
                    var timestamp = row.createtime;
                    var day = moment(timestamp);
                    return day.format("YYYY-MM-DD HH:mm");
                }},
                {"data": function (row) {
                    if(row.username == "admin"){
                        return ""
                    }else {
                        return "<a href='javascript:;' class='resetPwd' rel='" + row.id + "'>重置密码</a>&nbsp;|&nbsp;" +
                                "<a href='javascript:;' class='edit' rel='" + row.id + "'>修改</a>"
                    }
                    }}
            ],
            "language": {
                //定义中文
                "search":"请输入账号或员工姓名",
                "zeroRecords": "没有匹配的数据",
                "lengthMenu": "显示 _MENU_ 条数据",
                "info": "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
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
        //添加用户
        $("#addForm").validate({
            errorClass:"text-danger",
            errorElement:"span",
            rules:{
                username:{
                    required:true,
                    rangelength:[3,20],
                    remote:"/admin/user/checkusername"
                },
                password:{
                    required:true,
                    rangelength:[6,18]
                },
                realname:{
                    required:true,
                    rangelength:[2,20]
                },
                weixin:{
                    required:true
                }
            },
            messages:{
                username:{
                    required:"请输入账号",
                    rangelength:"账号长度3-20位",
                    remote:"该账号已被占用"
                },
                password:{
                    required:"请输入密码",
                    rangelength:"密码长度6-18位"
                },
                realname:{
                    required:"请输入姓名",
                    rangelength:"姓名长度2-20位"
                },
                weixin:{
                    required:"请输入微信ID"
                }
            },
            submitHandler: function (form) {
                $.post("/admin/add",$(form).serialize()).done(function (data) {
                    if (data == "success"){
                        $("#addModal").modal('hide');
                        dataTable.ajax.reload();
                    }
                }).fail(function () {
                    alert("服务器异常")
                });
            }

        });
        //弹出添加框
        $("#addBtn").click(function () {
            $("#addForm")[0].reset();
            $("#addModal").modal({
                show:true,
                backdrop:'static',
                keyboard:false
            });
        });
        //提交添加员工的form表单
        $("#saveBtn").click(function () {
            $("#addForm").submit();
        });
        
        //重置密码
        $(document).delegate(".resetPwd","click",function () {
            var id = $(this).attr("rel");
            if(confirm("确认将密码重置为 000000？")){
                $.post("/admin/resetpassword",{"id":id}).done(function (data) {
                    if (data == "success"){
                        alert("重置密码成功");
                    }
                }).fail(function () {
                    alert("服务器异常");
                });
            }
        });
        //修改员工信息
        $("#editForm").validate({
            errorClass:"text-danger",
            errorElement:"span",
            rules:{
                realname:{
                    required:true,
                    rangelength:[2,20]
                },
                weixin:{
                    required:true
                }
            },
            messages:{
                realname:{
                    required:"请输入姓名",
                    rangelength:"姓名长度2-20位"
                },
                weixin:{
                    required:"请输入微信号码"
                }
            },
            submitHandler:function(form){
                $.post("/admin/edit",$(form).serialize()).done(function(data){
                    if(data == "success") {
                        $("#editModal").modal('hide');
                        dataTable.ajax.reload();
                    }
                }).fail(function(){
                    alert("服务器异常");
                });
            }
        });
        $(document).delegate(".edit","click",function(){
            var id = $(this).attr("rel");
            $.get("/admin/"+id+".json").done(function(result){
                if(result.state == "success") {
                    $("#edit_id").val(result.data.id);
                    $("#edit_username").val(result.data.username);
                    $("#edit_realname").val(result.data.realname);
                    $("#edit_weixin").val(result.data.weixin);
                    $("#edit_roleid").val(result.data.roleid);
                    $("#edit_enable").val(result.data.enable.toString());
                    $("#editModal").modal({
                        show:true,
                        dropback:'static',
                        keyboard:false
                    });
                } else {
                    alert(result.message);
                }
            }).fail(function(){
                alert("服务器异常");
            });
        });
        $("#editBtn").click(function(){
            $("#editForm").submit();
        });
    });
</script>
</body>
</html>
