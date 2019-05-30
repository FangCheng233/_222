<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: FangCheng
  Date: 2019/4/16
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
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
            <div class="layui-inline">
                <input class="layui-input" name="select" placeholder="学号/姓名/班级/专业/学院" id="demoReload" autocomplete="off">
            </div>
            <button class="layui-btn" data-type="reload">搜索</button>
        </div>
        <table class="layui-hide" id="test" lay-filter="test" style="margin-left: 10px"></table>
    </div>
    <sec:authorize access="hasRole('ADMIN')">
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm" lay-event="pass">审批通过</button>
                <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="refuse">驳回申请</button>
            </div>
        </script>
    </sec:authorize>
<script type="text/html" id="barDemo">
    <a href="javascript:;" title="下载文件" lay-event="download"><i class="layui-icon">⬇</i></a>
    <a href="javascript:;" title="编辑" lay-event="edit"><i class="layui-icon">&#xe642;</i></a>
    <a href="javascript:;" title="删除" lay-event="del"><i class="layui-icon">&#xe640;</i></a>
</script>
<script src="/static/layui/layui.js" charset="utf-8"></script>
<script src="/static/plugins/jquery.1.12.4.min.js"></script>
    <script type="text/html" id="systemAudit">
        {{#  if(d.systemAudit === '特别困难'){ }}
        <span style="color: red;">{{ d.systemAudit}}</span>
        {{#  } else { }}
        <span style="color: #2fa0ec;">{{ d.systemAudit}}</span>
        {{#  } }}
    </script>
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
            ,skin: 'row'
            ,even: true
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
                ,{field:'systemAudit', title:'系统评定结果', width:200,align:'center',templet: '#systemAudit'}
                ,{field:'systemValue', title:'系统评分', width:100,align:'center',templet: '#systemValue'}
                ,{field:'remarks', title:'系统备注信息', width:200,align:'center'}
                ,{field:'reasonsForApplication', title:'申请理由',event: 'setSign',style:'cursor: pointer;',width:200,align:'center'}
                ,{field:'userId', title:'学号', width:100,align:'center'}
                ,{field:'userName', title:'姓名', width:100,align:'center'}
                ,{field:'processNode', title: '上一节点', width:100,align:'center'}
                ,{field:'teacherName', title: '审批人', width:100,align:'center'}
                ,{field:'approvalStatus', title:'审批状态', width:100, sort:true, align:'center'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', minWidth:150,align:'center'}
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
                                data: JSON.stringify(sendData),//
                                dataType: 'json',
                                contentType: 'application/json',
                                success: function (data) {//回调函数
                                    layer.msg(data.success,{
                                        time:2000
                                    })
                                    table.reload('testReload', {
                                        page: {
                                            curr: 1 //重新从第 1 页开始
                                        }
                                    });
                                },
                                error:function (data) {

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
                        layer.msg('当前选中' + data.length  +'条数据<br>确认是否驳回？', {
                            time: 20000, //20s后自动关闭
                            btn: ['确认驳回？', '放弃']
                            ,yes: function (index, layero){
                                $.ajax({
                                    url: "/alterApplicationRefuse",
                                    type: "POST",
                                    beforeSend : function(xhr) {
                                        xhr.setRequestHeader(header, token);
                                    },
                                    data: JSON.stringify(sendData),//
                                    dataType: 'json',
                                    contentType: 'application/json',
                                    success: function (data) {//回调函数
                                        layer.msg(data.success+'</br>'+data.error)
                                        table.reload('testReload', {
                                            page: {
                                                curr: 1 //重新从第 1 页开始
                                            }
                                        });
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
            $("#toolbarDemo .addUser").click(function () {
                layer.open({
                    title: '添加用户',
                    type: 2,
                    shade: false,
                    maxmin: true,
                    shade: 0.5,
                    anim: 4,
                    area: ['90%', '90%'],
                    content: '',
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
            if(obj.event === 'del'){
                layer.confirm('真的删除么', function(index){
                    $.ajax({
                        url: "/delete-application",
                        type: "POST",
                        beforeSend : function(xhr) {
                            xhr.setRequestHeader(header, token);
                        },
                        data: data.applicationNumber,//
                        contentType: 'application/json',
                        success: function (res) {//回调函数
                        }
                    });
                    obj.del();
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                var data = obj.data;
                var applicationNumber = data.applicationNumber;
                var userId = data.userId;
                layer.open({
                    title: '查看详细信息',
                    type: 2,
                    maxmin: true,
                    shade: 0.5,
                    anim: 4,
                    area: ['90%', '90%'],
                    content: '/viewapplication?userId='+userId+'&applicationNumber='+applicationNumber,
                    zIndex: layer.zIndex,
                    // skin: 'layui-layer-molv',
                    end: function () {
                        $(".layui-laypage-btn")[0].click();
                    }
                });
            }else if(obj.event === 'download'){
                alert("<%=basePath%>")
                var sendData = [];
                var data = obj.data;
                sendData.push(data.applicationNumber)
                $.ajax({
                    url: "/download",
                    type: "POST",
                    beforeSend : function(xhr) {
                        xhr.setRequestHeader(header, token);
                    },
                    data: JSON.stringify(sendData),//
                    dataType: 'json',
                    contentType: 'application/json',
                    success: function (data) {//回调函数
/*                        layer.msg(data.success,{
                            time:2000
                        })*/
                        window.open("<%=basePath%>"+"static/data/"+data.success)
                    },
                    error:function (data) {
                    }
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
                            select: demoReload.val()
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
