<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: FangCheng
  Date: 2019/5/3
  Time: 21:23
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
</head>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
<form class="layui-form" action="">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">密保问题</label>
            <div class="layui-input-inline">
                <select name="userSecurity" id="userSecurity" disabled="disabled">
                    <option value="${user.userSecurity}">${user.userSecurity}</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密保答案</label>
        <div class="layui-input-inline">
            <input type="text" name="securityAnwser" id="securityAnwser" autocomplete="off" placeholder="请输入答案" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">验证密保</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
    <input type="hidden" name="userId" id="userId" value="${user.userId}">
</form>
</fieldset>
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
            var data1 = data.field;
            var sendData =[data1.userSecurity,data1.securityAnwser,data1.userId];
            $.ajax({
                url: "/compareAnwser",
                type: "POST",
                beforeSend : function(xhr) {
                    xhr.setRequestHeader(header, token);
                },
                data: JSON.stringify(sendData) ,//参数，（注：你后台的方法参数不好传啊）
                contentType: 'application/json',
                async : false,
                success:function (data) {
                    if(data.success){
                        layer.open({
                            title: '重置密码',
                            type: 2,
                            maxmin: true,
                            shade: 0.5,
                            anim: 4,
                            area: ['50%', '100%'],
                            content: '/alterpwd?userId='+data1.userId,
                            zIndex: layer.zIndex,
                            // skin: 'layui-layer-molv',
                            end: function () {
                                window.parent.location.reload();
                               /* $(".layui-laypage-btn")[0].click();*/
                            }
                        });

                    }

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
