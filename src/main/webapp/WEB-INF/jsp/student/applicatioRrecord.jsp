<%--
  Created by IntelliJ IDEA.
  User: FangCheng
  Date: 2019/4/14
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table class="layui-hide" id="test" lay-filter="test"></table>


<script src="/static/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test'
            ,url:'/test/table/demo3.json'
            ,toolbar: true
            ,title: '用户数据表'
            ,totalRow: true
            ,cols: [[
                {field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true, totalRowText: '合计行'}
                ,{field:'username', title:'用户名', width:120, edit: 'text'}
                ,{field:'email', title:'邮箱', width:150, edit: 'text'}
                ,{field:'experience', title:'积分', width:80, sort: true, totalRow: true}
                ,{field:'sex', title:'性别', width:80, edit: 'text', sort: true}
                ,{field:'logins', title:'登入次数', width:100, sort: true, totalRow: true}
                ,{field:'sign', title:'签名'}
                ,{field:'city', title:'城市', width:100}
                ,{field:'ip', title:'IP', width:120}
                ,{field:'joinTime', title:'加入时间', width:120}
            ]]
            ,page: true
            ,response: {
                statusCode: 200 //重新规定成功的状态码为 200，table 组件默认为 0
            }
            ,parseData: function(res){ //将原始数据解析成 table 组件所规定的数据
                return {
                    "code": res.status, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.total, //解析数据长度
                    "data": res.rows.item //解析数据列表
                };
            }
        });
    });
</script>
</body>
</html>
