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
                    layer.msg('当前选中' + data.length  +'条数据<br>确认是否删除？', {
                        time: 20000, //20s后自动关闭
                        btn: ['删除', '放弃']
                        ,yes: function (index, layero){
                            $.ajax({
                                url: "/alterApplicationPass",
                                type: "POST",
                                data: {applicationNumberList:JSON.stringify(sendData)},
                                contentType: 'application/json',
                                success: function (res) {//回调函数
                                }
                            });
                            layer.close(index);
                        }
                        ,success: function(data){
                            layer.msg('修改用户'+data.success+'成功'+ '</br>'+data.error+'删除失败', {
                                time: 10000, //20s后自动关闭
                            })
                        }
                        ,error:function (data) {

                        }
                    });
                    break;
                case 'addUser'://添加用户的操作  通过EXCEL导入
                    var data = checkStatus.data;
                    //关闭弹出层倒计时
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
                    })
/*                        layer.open({
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
                        });*/
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
            }
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
                            layer.msg(data.resultString)
                        },
                        error:function (data) {
                            alert("data:" +JSON.stringify(data));
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
