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
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/layui/css/layui.css"  media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>爱好</legend>
    <div class="layui-field-box">
        <div class="demoTable">
            搜索ID：
            <div class="layui-inline">
                <input class="layui-input" name="id" id="demoReload" autocomplete="off">
            </div>
            <button class="layui-btn" data-type="reload">搜索</button>
        </div>
        <table class="layui-hide" id="test" lay-filter="test" style="margin-left: 10px"></table>
    </div>
</fieldset>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">批量审批通过</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">审批通过</button>
        <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="isAll">驳回申请</button>
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
                ,{field:'approvalStatus', title:'审批状态', width:100,align:'center'}
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
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    var sendData = [];
                    for(var i=0;i<data.length;i++) {
                        var applicationNumber = data[i].applicationNumber;
                        sendData.push(applicationNumber)
                    }
                    layer.msg('当前选中' + data.length  +'条数据<br>确认是否通过？', {
                        time: 20000, //20s后自动关闭
                        btn: ['审批通过', '放弃']
                        ,success: function(layero){
                            var btn = layero.find('.layui-layer-btn');
                            btn.find('.layui-layer-btn0').attr({
                                function(){
                                    $.ajax({
                                        url: "/alterApplication",
                                        type: "POST",
                                        data: JSON.stringify(sendData),//
                                        dataType: 'json',
                                        contentType: 'application/json',
                                        success: function (res) {//回调函数
                                        }
                                    });
                                }
                                ,target: '_blank'
                            });
                        }
                    });
                    break;
                    case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选');
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
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    $.ajax({
                        url: "/delete-application",
                        type: "GET",
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
                            username: demoReload.val()
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
<script>
    layui.use('table', function(){
        var table = layui.table;
        //监听单元格事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            if(obj.event === 'setSign'){
                layer.open({
                    type: 1
                    ,title: false //不显示标题栏
                    ,closeBtn: false
                    ,area: '300px;'
                    ,shade: 0.8
                    ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                    ,btn: ['通过审批', '驳回','返回页面']
                    ,btnAlign: 'c'
                    ,moveType: 1 //拖拽模式，0或者1
                    ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">申请理由<br>' +
                        '<br>'+ data.reasonsForApplication +'</div>'
                    ,success: function(layero){
                        var btn = layero.find('.layui-layer-btn');
                        btn.find('.layui-layer-btn0').attr({
                            //链接审批通过该条申请
                            function(){
                                $.ajax({
                                    url: "/delete-application",
                                    type: "GET",
                                    data: {"applicationNumber": data.applicationNumber},//
                                    dataType: 'json',
                                    contentType: 'application/json',
                                    success: function (res) {//回调函数
                                    }
                                });
                            }
                            ,target: '_blank'
                        });
                        btn.find('.layui-layer-btn1').attr({
                            //链接审批驳回该条申请
                            href: 'http://www.layui.com/'
                            ,target: '_blank'
                        });
                    }
                }), function(value, index){
                    layer.close(index);

                    //这里一般是发送修改的Ajax请求

                    //同步更新表格和缓存对应的值
                    obj.update({
                        sign: value
                    });
                };
            }
        });
    });
</script>
</body>
</html>
