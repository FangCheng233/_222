<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: FangCheng
  Date: 2019/4/16
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" pageEncoding="UTF-8">
    <title>layui</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header"  content="${_csrf.headerName}"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/layui/css/layui.css"  media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
    <div class="layui-field-box">
        <div class="demoTable">
            搜索学号：
            <div class="layui-inline">
                <input class="layui-input" name="id" id="demoReload" autocomplete="off">
            </div>
            <button class="layui-btn" data-type="reload">搜索</button>
        </div>
        <table class="layui-hide" id="test" lay-filter="test" style="margin-left: 10px"></table>
    </div>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="pass">审批通过</button>
        <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="refuse">驳回申请</button>
    </div>
</script>
<script type="text/html" id="barDemo">
    <a href="javascript:;" title="编辑" lay-event="edit"><i class="layui-icon">&#xe642;</i></a>
    <a href="javascript:;" title="删除" lay-event="del"><i class="layui-icon">&#xe640;</i></a>
</script>
<script src="/static/layui/layui.js" charset="utf-8"></script>
<script src="/static/plugins/jquery.1.12.4.min.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    var header = $("meta[name='_csrf_header']").attr("content");
    var token =$("meta[name='_csrf']").attr("content");
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#test'
            <%--,url:'/${params}'--%>
            ,url:'/${params}'
            ,page: true
            ,title:'用户数据表'
            ,toolbar: '#toolbarDemo'
            /*            ,totalRow: true*/
            ,id: 'testReload'
            ,width: 1630
            ,cols: [[
                {field:'applicationNumber',hide:true}
                ,{type:'numbers'}
                ,{type: 'checkbox'}
                <sec:authorize access="hasRole('ADMIN')">
                ,{field:'userCollege', title:'院系', width:200, unresize: true, sort: true,align:'center'}
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN') or hasRole('COLLEGE') or hasRole('COUNSELLOR')">
                ,{field:'userMajor', title:'专业', width:200,align:'center',sort:true}
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN') or hasRole('COLLEGE') or hasRole('COUNSELLOR')">
                ,{field:'userClass', title:'班级', width:100,align:'center'}
                </sec:authorize>
                ,{field:'userGrade', title: '年级', width:100,align:'center'}
                ,{field:'povertyLevel', title:'申请家庭经济困难等级', width:200,align:'center'}
                ,{field:'reasonsForApplication', title:'申请理由',event: 'setSign',style:'cursor: pointer;',width:200,align:'center'}
                ,{field:'userId', title:'学号', width:100,align:'center'}
                ,{field:'userName', title:'姓名', width:100,align:'center'}
                ,{field:'processNode', title: '上一节点', width:100,align:'center'}
                ,{field:'teacherName', title: '审批人', width:100,align:'center'}
                ,{field:'approvalStatus', title:'审批状态', width:100, sort:true, align:'center'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', minWidth:150,align:'center', fixed: 'right'}
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
                case 'pass':
                    var data = checkStatus.data;
                    if(data.length==0){
                        layer.msg('没有选中要修改的内容', {
                            time: 10000 //20s后自动关闭
                            ,btn: ['好的']
                            ,btnAlign: 'c'
                        });
                        break;
                    }
                    var sendData = [];
                    for(var i=0;i<data.length;i++) {
                        var applicationNumber = data[i].applicationNumber;
                        sendData.push(applicationNumber)
                    }
                    layer.msg('当前选中' + data.length  +'条数据<br>确认是否通过？', {
                        time: 20000, //20s后自动关闭
                        btn: ['审批通过', '放弃']
                        ,yes: function (index, layero){
                            $.ajax({
                                url: "/alterApplicationPass",
                                type: "POST",
                                beforeSend : function(xhr) {
                                    xhr.setRequestHeader(header, token);
                                },
                                data: {applicationNumberList:JSON.stringify(sendData)},//
                                dataType: 'json',
                                contentType: 'application/json',
                                success: function (res) {//回调函数
                                }
                            });
                        layer.close(index);
                        }
                        ,success: function(layero){
                        }
                    });
                    break;
                    case 'refuse':
                        var data = checkStatus.data;
                        if(data.length==0){
                            layer.msg('没有选中要修改的内容', {
                                time: 10000 //20s后自动关闭
                                ,btn: ['好的']
                                ,btnAlign: 'c'
                            });
                break;
                        }
                        var sendData = [];
                        for(var i=0;i<data.length;i++) {
                            var applicationNumber = data[i].applicationNumber;
                            sendData.push(applicationNumber)
                        }
                        layer.msg('当前选中' + data.length  +'条数据<br>确认是否通过？', {
                            time: 20000, //20s后自动关闭
                            btn: ['确认驳回？', '放弃']
                            ,yes: function (index, layero){
                                $.ajax({
                                    url: "/alterApplicationRefuse",
                                    type: "POST",
                                    beforeSend : function(xhr) {
                                        xhr.setRequestHeader(header, token);
                                    },
                                    data: {'applicationNumberList':JSON.stringify(sendData)},//
                                    dataType: 'json',
                                    contentType: 'application/json',
                                    success: function (res) {//回调函数
                                    }
                                });
                                layer.close(index);
                            }
                            ,success: function(layero){
                            }
                        });
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
        });
        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            alert(JSON.stringify(obj))
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    $.ajax({
                        url: "/delete-application",
                        type: "POST",
                        beforeSend : function(xhr) {
                            xhr.setRequestHeader(header, token);
                        },
                        data: {"applicationNumber": data.applicationNumber},//
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
        var $ = layui.$, active = {
            reload: function(){
                var demoReload = $('#demoReload');
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        key: {
                            userName: demoReload.val()
                        }
                    }
                });
            }
        };
        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
</body>
</html>
