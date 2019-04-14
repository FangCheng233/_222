<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: FangCheng
  Date: 2019/4/10
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>通用增删改查</title>
    <link href="/static/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/static/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet"/>
    <link href="/static/plugins/bootstrap-table-1.11.0/bootstrap-table.min.css" rel="stylesheet"/>
    <link href="/static/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
    <link href="/static/plugins/jquery-confirm/jquery-confirm.min.css" rel="stylesheet"/>
    <link href="/static/plugins/select2/css/select2.min.css" rel="stylesheet"/>

    <link href="/static/css/common.css" rel="stylesheet"/>
</head>
<body>
<div id="main">
    <div id="toolbar">
        <a class="waves-effect waves-button" href="javascript:;" onclick="createAction()"><i class="zmdi zmdi-plus"></i> 新增用户</a>
        <a class="waves-effect waves-button" href="javascript:;" onclick="updateAction()"><i class="zmdi zmdi-edit"></i> 编辑用户</a>
        <a class="waves-effect waves-button" href="javascript:;" onclick="deleteAction()"><i class="zmdi zmdi-close"></i> 删除用户</a>
    </div>
    <table id="table"></table>
</div>
<!-- 新增 -->
<div id="createDialog" class="crudDialog" hidden>
    <form>
        <div class="form-group">
            <label for="input1">标题</label>
            <input id="input1" type="text" class="form-control">
        </div>
        <div class="form-group">
            <label for="input2">名称</label>
            <input id="input2" type="text" class="form-control">
        </div>
        <div class="form-group">
            <label for="input3">根目录</label>
            <input id="input3" type="text" class="form-control">
        </div>
        <div class="form-group">
            <label for="input4">图标</label>
            <input id="input4" type="text" class="form-control">
        </div>
    </form>
</div>
<script src="/static/plugins/jquery.1.12.4.min.js"></script>
<script src="/static/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="/static/plugins/bootstrap-table-1.11.0/bootstrap-table.min.js"></script>
<script src="/static/plugins/bootstrap-table-1.11.0/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="/static/plugins/waves-0.7.5/waves.min.js"></script>
<script src="/static/plugins/jquery-confirm/jquery-confirm.min.js"></script>
<script src="/static/plugins/select2/js/select2.min.js"></script>
<script src="/static/js/bootbox.js"></script>
<script src="/static/js/common.js"></script>
<script>
    var $table = $('#table');
    $(function() {
        $(document).on('focus', 'input[type="text"]', function() {
            $(this).parent().find('label').addClass('active');
        }).on('blur', 'input[type="text"]', function() {
            if ($(this).val() == '') {
                $(this).parent().find('label').removeClass('active');
            }
        });
        $table.bootstrapTable({
            url: '/getUserInfo',
            height: getHeight(),
            striped: true,
            search: true,
            searchOnEnterKey: true,
            showRefresh: true,
            showToggle: true,
            showColumns: true,
            minimumCountColumns: 2,
            showPaginationSwitch: true,
            clickToSelect: true,
            detailView: true,
            detailFormatter: 'detailFormatter',
            pagination: true,
            paginationLoop: false,
            classes: 'table table-hover table-no-bordered',
            //sidePagination: 'server',
            //silentSort: false,
            smartDisplay: false,
            idField: 'id',
            sortName: 'id',
            sortOrder: 'desc',
            escape: true,
            // idField: 'systemId',
            maintainSelected: true,
            toolbar: '#toolbar',
            columns: [
                {field: 'state', checkbox: true},
                {
                    field: 'id', title: '序号', sortable: true, halign: 'center',
                    formatter: function (value, row, index) {
                        return index + 1;
                    }
                },
                {field: 'userId', title: '工号', sortable: true, halign: 'center'},
                {field: 'userName', title: '姓名', sortable: true, halign: 'center'},
                {field: 'userCollege', title: '学院', sortable: true, halign: 'center'},
                {field: 'userClass', title: '姓名', sortable: true, halign: 'center'},
                {field: 'userSex', title: '性别', sortable: true, halign: 'center'},
                {field: 'age', title: '年龄', sortable: true, halign: 'center'},
                {field: 'phone', title: '手机', sortable: true, halign: 'center'},
                {field: 'email', title: '邮箱', sortable: true, halign: 'center'},
                {field: 'address', title: '地址', sortable: true, halign: 'center'},
                {field: 'remark', title: '备注', sortable: true, halign: 'center'},
                {field: 'action', title: '操作', halign: 'center', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
            ]
        }).on('all.bs.table', function (e, name, args) {
            $('[data-toggle="tooltip"]').tooltip();
            $('[data-toggle="popover"]').popover();
        });
    });
    function actionFormatter(value, row, index) {
        return [
            '<a class="like" href="javascript:void(0)" data-toggle="tooltip" title="Like"><i class="glyphicon glyphicon-heart"></i></a>　',
            '<a class="edit ml10" href="javascript:void(0)" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>　',
            '<a class="remove ml10" href="javascript:void(0)" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>'
        ].join('');
    }

    window.actionEvents = {
        'click .like': function (e, value, row, index) {
            alert('You click like icon, row: ' + JSON.stringify(row));
            console.log(value, row, index);
        },
        'click .edit': function (e, value, row, index) {
            alert('You click edit icon, row: ' + JSON.stringify(row));
            console.log(value, row, index);
        },
        'click .remove': function (e, value, row, index) {
            console.log(value, row, index);
            bootbox.confirm({
                size: "small",
                message: "您确定删除学号为"+row.userId+"的学生吗？",
                buttons: {
                    confirm: {
                        label: '是',
                        className: 'btn-success'
                    },
                    cancel: {
                        label: '否',
                        className: 'btn-danger'
                    }
                },
                callback: function(result) {
                    if(result) {
                        $.ajax({
                            url: "/delete-user",
                            type: "GET",
                            data: {"userId": row.userId},//参数，（注：你后台的方法参数不好传啊）
                            dataType: 'json',
                            contentType: 'application/json',
                            success: function (res) {//回调函数

                            }
                        });

                    }
                }
            });
        }
    };

    function detailFormatter(index, row) {
        var html = [];
        $.each(row, function (key, value) {
            html.push('<p><b>' + key + ':</b> ' + value + '</p>');
        });
        return html.join('');
    }
    // 新增
    function createAction() {
        $.confirm({
            type: 'dark',
            animationSpeed: 300,
            title: '新增系统',
            content: $('#createDialog').html(),
            buttons: {
                confirm: {
                    text: '确认',
                    btnClass: 'waves-effect waves-button',
                    action: function () {
                        $.alert('确认');
                    }
                },
                cancel: {
                    text: '取消',
                    btnClass: 'waves-effect waves-button'
                }
            }
        });
    }
    // 编辑
    function updateAction() {
        var rows = $table.bootstrapTable('getSelections');
        if (rows.length == 0) {
            $.confirm({
                title: false,
                content: '请至少选择一条记录！',
                autoClose: 'cancel|3000',
                backgroundDismiss: true,
                buttons: {
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        } else {
            $.confirm({
                type: 'blue',
                animationSpeed: 300,
                title: '编辑系统',
                content: $('#createDialog').html(),
                buttons: {
                    confirm: {
                        text: '确认',
                        btnClass: 'waves-effect waves-button',
                        action: function () {
                            $.alert('确认');
                        }
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        }
    }
    // 删除
    function deleteAction() {
        var rows = $table.bootstrapTable('getSelections');
        if (rows.length == 0) {
            $.confirm({
                title: false,
                content: '请至少选择一条记录！',
                autoClose: 'cancel|3000',
                backgroundDismiss: true,
                buttons: {
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        } else {
            $.confirm({
                type: 'red',
                animationSpeed: 300,
                title: false,
                content: '确认删除该系统吗？',
                buttons: {
                    confirm: {
                        text: '确认',
                        btnClass: 'waves-effect waves-button',
                        action: function () {
                            var ids = new Array();
                            for (var i in rows) {
                                ids.push(rows[i].systemId);
                            }
                            $.alert('删除：id=' + ids.join("-"));
                        }
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        }
    }
</script>
</body>

