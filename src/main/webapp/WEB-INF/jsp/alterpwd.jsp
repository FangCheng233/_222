<%--
  Created by IntelliJ IDEA.
  User: FangCheng
  Date: 2019/5/4
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header"  content="${_csrf.headerName}"/>
    <link rel="stylesheet" href="/static/layui/css/layui.css"  media="all">
</head>
<body>
<form class="layui-form" action="">
    <div class="layui-inline">
        <label class="layui-form-label">输入密码</label>
        <div class="layui-input-inline">
            <input type="password" name="password" id="password" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">重复密码</label>
        <div class="layui-input-inline">
            <input type="password" name="password2" id="password2" autocomplete="off" class="layui-input">
        </div>
    </div>
    </div>
    <input type="hidden" name="userId" id="userId" value="${user.userId}">
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">保存</button>
        </div>
    </div>
</form>
<script src="/static/layui/layui.js" charset="utf-8"></script>
<script src="/static/plugins/jquery.1.12.4.min.js"></script>
<script>
    var header = $("meta[name='_csrf_header']").attr("content");
    var token =$("meta[name='_csrf']").attr("content");
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer;

        //自定义验证规则
        form.verify({
            title: function(value){
                var re= /select|update|delete|exec|count|’|"|=|;|>|<|%/i;
                if(re.test(value.toLowerCase())){
                    return '不能含有敏感字符';
                }
            }
        });
        //监听提交
        form.on('submit(demo1)', function(data){
            var data = data.field;
            var sendData =[data.password,data.password2,data.userId];
            alert(JSON.stringify(sendData))
            $.ajax({
                url: "/alterPassword",
                type: "POST",
                beforeSend : function(xhr) {
                    xhr.setRequestHeader(header, token);
                },
                data: JSON.stringify(sendData) ,//参数，（注：你后台的方法参数不好传啊）
                contentType: 'application/json',
                async : true,
                success:function (data) {
                    layer.msg('修改成功，请关闭该窗口重新登陆')
                },
                error:function (data) {
                    layer.msg(data.error);
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
