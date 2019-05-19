<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: FangCheng
  Date: 2019/5/13
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="renderer" content="webkit">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header"  content="${_csrf.headerName}"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;margin-left: 20px;margin-right: 20px">
    <legend>评定参数</legend>
    <table class="layui-hide" id="test1" lay-filter="test1"></table>
</fieldset>
<script src="/static/plugins/jquery.1.12.4.min.js"></script>
<script src="/static/layui/layui.js" charset="utf-8"></script>
<script>
    var header = $("meta[name='_csrf_header']").attr("content");
    var token =$("meta[name='_csrf']").attr("content");
    layui.use('table', function(){
        var table = layui.table;
        //方法级渲染
        table.render({
            elem: '#test1'
            ,url: '/getReferenceData'
            ,width:1600
            ,cols: [[
                {field:'schoolYear', title: '学年', width:100, sort: true, fixed: true}
                ,{field:'livingExpenses', title: '本地最低生活保障', width:150,edit:'number',align:'center'}
                ,{field:'tuition1', title: '学院一(学费)', width:120, edit:'number',align:'center'}
                ,{field:'tuition2', title: '学院二(学费)', width:120, edit:'number',align:'center'}
                ,{field:'tuition3', title: '学院三(学费)', width:120, edit:'number',align:'center'}
                ,{field:'tuition4', title: '学院四(学费)', width:120, edit:'number',align:'center'}
                ,{field:'tuition5', title: '学院五(学费)', width:120, edit:'number',align:'center'}
                ,{field:'tuition6', title: '学院六(学费)', width:120, edit:'number',align:'center'}
                ,{field:'tuition7', title: '学院七(学费)', width:120, edit:'number',align:'center'}
                ,{field:'tuition8', title: '学院八(学费)', width:120, edit:'number',align:'center'}
                ,{field:'tuition9', title: '学院九(学费)', width:120, edit:'number',align:'center'}
                ,{field:'tuition10', title: '学院十(学费)', width:120, edit:'number',align:'center'}
                ,{field:'tuition11', title: '学院十一(学费)', width:130, edit:'number',align:'center'}
            ]]
            ,id: 'test1'
            ,page: true
            ,height: 315
        });
        //单元格监听
        table.on('edit(test1)', function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
            layer.msg( field + ' 更改为：'+ value);
            //Ajax修改对应的参数
            var sendData =[data.schoolYear,field,value];
            $.ajax({
                url: "/setReference",
                type: "POST",
                beforeSend : function(xhr) {
                    xhr.setRequestHeader(header, token);
                },
                data: JSON.stringify(sendData),//
                contentType: 'application/json',
                success: function (res) {//回调函数
                }
            });
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
                            id: demoReload.val()
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
