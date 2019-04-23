<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: FangCheng
  Date: 2019/4/3
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/layui/css/layui.css"  media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<table class="layui-hide" id="test" lay-filter="test"></table>
<sec:authorize access="hasRole('ADMIN')">
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="removeUser">删除用户</button>
        <button class="layui-btn layui-btn-sm" lay-event="addUser">添加用户</button>
    </div>
</script>
</sec:authorize>
<script type="text/html" id="barDemo">
    <a href="javascript:;" title="编辑" lay-event="edit"><i class="layui-icon">&#xe642;</i></a>
    <a href="javascript:;" title="删除" lay-event="del"><i class="layui-icon">&#xe640;</i></a>
</script>
<script src="/static/layui/layui.js" charset="utf-8"></script>
<script src="/static/plugins/jquery.1.12.4.min.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#test'
            ,url:'/${params}'
            // ,url:'/static/data/user.json'
            ,page: true
            ,title:'用户数据表'
            ,toolbar: '#toolbarDemo'
/*            ,totalRow: true*/
            ,id: 'testReload'
            ,cols: [[
                {type:'numbers'}
                ,{field: 'state', checkbox: true}
                ,{field:'userId', width:100, title: '用户名'}
                ,{field:'username', width:80, title: '用户名'}
                ,{field:'userSex', width:80, title: '性别', sort: true}
                ,{field:'groupId', width:80, title: 'groupId'}
                ,{field:'sign', title: '签名', minWidth: 50}
                ,{field:'experience', width:80, title: '积分', sort: true}
                ,{field:'score', width:80, title: '评分', sort: true}
                ,{field:'classify', width:80, title: '职业'}
                ,{field:'wealth', width:135, title: '财富', sort: true}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150,align:'center'}
            ]]
            ,request:{
                pageName: 'pageStart', //页码的参数名称，默认：page
                limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
        });
        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;
                case 'isAll':
                    $("#toolbarDemo .addUser").click(function () {
                        layer.open({
                            title: '添加用户',
                            type: 2,
                            shade: false,
                            maxmin: true,
                            shade: 0.5,
                            anim: 4,
                            area: ['90%', '90%'],
                            content: 'user-add.html',
                            zIndex: layer.zIndex,
                            // skin: 'layui-layer-molv',
                            end: function () {
                                $(".layui-laypage-btn")[0].click();
                            }
                        });
                    })
                    break;
            };
        });
        //编辑选中行数据
        table.on('tool(tableFilter)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;
            if (layEvent === 'edit') {
                layer.open({
                    title: '编辑用户',
                    type: 2,
                    shade: false,
                    maxmin: true,
                    area: ['90%', '90%'],
                    content: 'user-edit.jsp',
                    zIndex: layer.zIndex,
                    end: function () {
                        $(".layui-laypage-btn")[0].click();
                    }
                });
            } else if (layEvent === 'del') {
                layer.confirm("确定要删除吗？",
                    {skin: 'layui-layer-lan', icon: 2, title: '提示', anim: 6}, function () {
                    layer.msg("操作成功！", {icon: 1, time: 1000});
                });
            }
        });
        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    $.ajax({
                        url: "/delete-user",
                        type: "GET",
                        data: {"userId": data.userId},//参数，（注：你后台的方法参数不好传啊）
                        dataType: 'json',
                        contentType: 'application/json',
                        success: function (res) {//回调函数
                        }
                    });
                    obj.del();
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                layer.prompt({
                    formType: 2
                    ,value: data.email
                }, function(value, index){
                    obj.update({
                        email: value
                    });
                    layer.close(index);
                });
            }
        });
    });
</script>
</body>
</html>

<%--                --%>
