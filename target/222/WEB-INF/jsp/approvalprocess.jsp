<%--
  Created by IntelliJ IDEA.
  User: FangCheng
  Date: 2019/5/5
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/layui/css/layui.css"  media="all">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;margin-left: 30px;">
<table class="layui-hide" id="test"></table>
</fieldset>
<script src="/static/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#test'
            ,url:'/getTableApproval?applicationNumber=${applicationNumber}'
            ,width:500
            ,cols: [[
                {field:'id', hide:true}
                ,{field:'processNode', width:80, title: '节点'}
                ,{field:'approvalStatus', width:180, title: '状态', sort: true}
                ,{field:'userName', width:80, title: '操作人'}
                ,{field:'remarks', title: '备注', minWidth: 50}
            ]]
            ,page: true
        });
    });
</script>
</body>
</html>
