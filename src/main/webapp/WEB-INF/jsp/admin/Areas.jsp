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
            <input class="layui-input" name="id" placeholder="省/市/县/" id="demoReload" autocomplete="off">
        </div>
        <button class="layui-btn" data-type="reload">搜索</button>
    </div>
    <table class="layui-hide" id="test" lay-filter="test"></table>
</div>

<sec:authorize access="hasRole('ADMIN')">
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="set">设为贫困的地区</button>
            <button class="layui-btn layui-btn-sm" lay-event="cancel">取消贫困地区</button>
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
            ,url:'/getAreas'
            // ,url:'/static/data/user.json'
            ,page: true
            ,title:'用户数据表'
            ,toolbar: '#toolbarDemo'
            /*            ,totalRow: true*/
            ,id: 'testReload'
            ,cols: [[
                {type:'numbers'}
                ,{field: 'state', checkbox: true}
                ,{field:'id', width:100, title: '编号',hide:true}
                ,{field:'provinceId', width:80, title: '省编号'}
                ,{field:'provinceName',width:80, minWidth:100, title: '省', sort: true}
                ,{field:'cityId', title:'市编号', width:150,align:'center'}
                ,{field:'cityName', title:'市', width:100,align:'center'}
                ,{field:'countyId', title:'区县编号', width:150,align:'center'}
                ,{field:'countyName', minWidth:100, title: '区/县'}
                ,{field:'poorerAreas', minWidth:100, title: '是否贫困县', sort: true}
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
                case 'set'://批量删除用户的操作
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
                        var id = data[i].id;
                        sendData.push(id)
                    }
                    layer.msg('当前选中' + data.length  +'条信息<br>确认设为贫困地区？', {
                        time: 20000, //20s后自动关闭
                        btn: ['确认', '放弃']
                        ,yes: function (index, layero){
                            $.ajax({
                                url: "/setAreas",
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
                case 'cancel'://添加用户的操作  通过EXCEL导入
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
                        var id = data[i].id;
                        sendData.push(id)
                    }
                    layer.msg('当前选中' + data.length  +'条信息<br>确认取消贫困地区？', {
                        time: 20000, //20s后自动关闭
                        btn: ['确认', '放弃']
                        ,yes: function (index, layero){
                            $.ajax({
                                url: "/cancelAreas",
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
            };
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
    /*                        //关闭弹出层倒计时
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
