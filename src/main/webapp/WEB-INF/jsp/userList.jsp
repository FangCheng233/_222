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
            <input class="layui-input" name="id" placeholder="学号/姓名/班级/专业/学院" id="demoReload" autocomplete="off">
        </div>
        <button class="layui-btn" data-type="reload">搜索</button>
    </div>
    <table class="layui-hide" id="test" lay-filter="test"></table>
</div>

<sec:authorize access="hasRole('ADMIN')">
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="removeUser">删除用户</button>
        <c:choose>
        <c:when test="${getStudentList}">
            <button class="layui-btn layui-btn-sm" lay-event="addStudent">添加学生</button>
        </c:when>
        <c:when test="${getCounsellorList}">
            <button class="layui-btn layui-btn-sm" lay-event="addCounsellor">添加辅导员</button>
        </c:when>
        <c:when test="${getCollegeList}">
            <button class="layui-btn layui-btn-sm" lay-event="addCollege">添加院系负责人</button>
        </c:when>
        <c:when test="${getAdminList}">
            <button class="layui-btn layui-btn-sm" lay-event="addAdmin">添加学工部管理员</button>
        </c:when>
    </c:choose>
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
    var header = $("meta[name='_csrf_header']").attr("content");
    var token =$("meta[name='_csrf']").attr("content");
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
                ,{field:'userId', width:100, title: '学号/工号'}
                ,{field:'userName', width:80, title: '用户名'}
                ,{field:'userSex',width:80, minWidth:100, title: '性别', sort: true}
                <sec:authorize access="hasRole('COLLEGE') or hasRole('COUNSELLOR')">
                ,{field:'userClass', title:'班级', width:100,align:'center'}
                </sec:authorize>
                <sec:authorize access="hasRole('COLLEGE') or hasRole('COUNSELLOR')">
                ,{field:'userMajor', title:'专业', width:100,align:'center'}
                </sec:authorize>
                ,{field:'userGrade', title:'班级', width:100,align:'center'}
                ,{field:'groupId', minWidth:100, title: '所属组织'}
                ,{field:'userCollege', minWidth:100, title: '所属院系', sort: true}
                <sec:authorize access="hasRole('ADMIN')">
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150,align:'center'}
                </sec:authorize>
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
                case 'removeUser'://批量删除用户的操作
                    var data = checkStatus.data;
                    if(data.length==0){
                        layer.msg('没有选中要修改的内容', {
                            time: 5000 //5s后自动关闭
                            ,btn: ['好的']
                            ,btnAlign: 'c'
                        });
                        break;
                    }
                    var sendData = [];
                    for(var i=0;i<data.length;i++) {
                        var userId = data[i].userId;
                        sendData.push(userId)
                    }
                    layer.msg('当前选中' + data.length  +'条用户<br>确认是否删除？', {
                        time: 20000, //20s后自动关闭
                        btn: ['删除', '放弃']
                        ,yes: function (index, layero){
                            $.ajax({
                                url: "/remove-user",
                                type: "POST",
                                data: JSON.stringify(sendData),
                                contentType: 'application/json',
                                beforeSend : function(xhr) {
                                    xhr.setRequestHeader(header, token);
                                },
                                success: function (data) {//回调函数
                                layer.msg(data.success+'</br>'+data.error,{
                                    time: 10000
                                    })
                                    //数据重载
                                    table.reload('testReload', {
                                        page: {
                                            curr: 1 //重新从第 1 页开始
                                        }
                                    });
                                },error:function (data) {
                                }
                            });
                            layer.close(index);
                        }
                    });
                    break;
                case 'addStudent'://添加用户的操作  通过EXCEL导入
                        layer.open({
                            title: '添加用户',
                            type: 2,
                            shade: false,
                            maxmin: true,
                            shade: 0.5,
                            anim: 4,
                            area: ['90%', '90%'],
                            content: '/addStudent',
                            zIndex: layer.zIndex,
                            // skin: 'layui-layer-molv',
                            end: function () {
                                $(".layui-laypage-btn")[0].click();
                            }
                        });
                    break;
                case 'addCounsellor'://添加用户的操作  通过EXCEL导入
                    layer.open({
                        title: '添加用户',
                        type: 2,
                        shade: false,
                        maxmin: true,
                        shade: 0.5,
                        anim: 4,
                        area: ['90%', '90%'],
                        content: '/addCounsellor',
                        zIndex: layer.zIndex,
                        // skin: 'layui-layer-molv',
                        end: function () {
                            $(".layui-laypage-btn")[0].click();
                        }
                    });
                    break;
                case 'addCollege'://
                    layer.open({
                        title: '添加用户',
                        type: 2,
                        shade: false,
                        maxmin: true,
                        shade: 0.5,
                        anim: 4,
                        area: ['90%', '90%'],
                        content: '/addCollege',
                        zIndex: layer.zIndex,
                        // skin: 'layui-layer-molv',
                        end: function () {
                            $(".layui-laypage-btn")[0].click();
                        }
                    });
                    break;
                case 'addAdmin'://添加用户的操作  通过EXCEL导入
                    layer.open({
                        title: '添加用户',
                        type: 2,
                        shade: false,
                        maxmin: true,
                        shade: 0.5,
                        anim: 4,
                        area: ['90%', '90%'],
                        content: '/addAdmin',
                        zIndex: layer.zIndex,
                        // skin: 'layui-layer-molv',
                        end: function () {
                            $(".layui-laypage-btn")[0].click();
                        }
                    });
                    break;
            };
        });
        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            var sendData = data.userId;
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    $.ajax({
                        url: "/delete-user",
                        type: "POST",
                        beforeSend : function(xhr) {
                            xhr.setRequestHeader(header, token);
                        },
                        data: sendData,//参数，（注：你后台的方法参数不好传啊）
                        contentType: 'application/json',
                        async : true,
                        success:function (data) {
                            layer.msg(data.resultString),
                                    //执行重载
                                    table.reload('testReload', {
                                        page: {
                                            curr: 1 //重新从第 1 页开始
                                        }
                                    });
                        },
                        error:function (data) {
                            alert("data:" +JSON.stringify(data));
                        }
                    });
                    obj.del();
                    layer.close(index);
                });

            } else if(obj.event === 'edit'){
                layer.open({
                    title: '修改密码',
                    type: 2,
                    shade: false,
                    maxmin: true,
                    shade: 0.5,
                    anim: 4,
                    area: ['20%', '20%'],
                    content: '/alterpwd',
                    zIndex: layer.zIndex,
                    // skin: 'layui-layer-molv',
                    end: function () {
                        $(".layui-laypage-btn")[0].click();
                    }
                });
/*                layer.open({
                    title: '修改权限分组',
                    type: 2,
                    shade: false,
                    maxmin: true,
                    shade: 0.5,
                    anim: 4,
                    area: ['30%', '30%'],
                    content: '/editRole?userId='+sendData,
                    zIndex: layer.zIndex,
                    // skin: 'layui-layer-molv',
                    end: function () {
                        $(".layui-laypage-btn")[0].click();
                    }
                });*/
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
<script>
    /*                    //关闭弹出层倒计时
                    var i = 5;
                    var interval;
                    layer.msg('是否提交？', {
                        btn: [i+1+'s后关闭'], //按钮
                        btnAlign:'c',
                        skin: 'layui-layer-molv',
                        success: function(a,b){
                            $(".layui-layer-btn0").css("backgroundColor","");
                            var fn = function() {
                                //              layer.title(i+' 秒后，系统将自动退出并关闭。',b);
                                $(".layui-layer-btn0").text(i-1+'s后关闭');
                                i--;
                            };
                            interval = setInterval(function(){
                                fn();
                                if(i === 0){
                                  layer.closeAll();
                                    clearInterval(interval);
                                }
                            }, 1000);
                        },
                        end:function(){
                            clearInterval(interval);
                        }
                    })*/
</script>
</body>
</html>

<%--                --%>
