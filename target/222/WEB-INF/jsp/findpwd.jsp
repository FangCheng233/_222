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
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/layui/css/layui.css"  media="all">
</head>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>表单集合演示</legend>


<form class="layui-form" action="">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">分组选择框</label>
            <div class="layui-input-inline">
                <c:choose>
                    <c:when test="${edit}">
                        <select name="userSecurity">
                            <option value="">请选择问题</option>
                            <optgroup label="城市记忆">
                                <option value="你工作的第一个城市">你工作的第一个城市</option>
                            </optgroup>
                            <optgroup label="学生时代">
                                <option value="你的工号">你的工号</option>
                                <option value="你最喜欢的老师">你最喜欢的老师</option>
                            </optgroup>
                        </select>
                    </c:when>
                    <c:otherwise>
                        <select name="userSecurity" disabled="disabled">
                            <option value="${user.userSecurity}">${user.userSecurity}</option>
                        </select>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">输入框</label>
        <div class="layui-input-block">
            <c:choose>
                <c:when test="${edit}">
                    <input type="text" name="securityAnwser" value="${user.securityAnwser}" autocomplete="off" placeholder="请输入答案" class="layui-input">
                </c:when>
                <c:otherwise>
                    <input type="text" name="securityAnwser" lay-verify="title" autocomplete="off" placeholder="请输入答案" class="layui-input">
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <c:choose>
        <c:when test="${edit}">
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="" lay-filter="demo2">验证密保</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</form>
</fieldset>
<script src="/static/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit;

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
            var sendData =[data.userSecurity,data.securityAnwser];
            alert(JSON.stringify(sendData))
            $.ajax({
                url: "/alterpwd",
                type: "POST",
                beforeSend : function(xhr) {
                    xhr.setRequestHeader(header, token);
                },
                data: JSON.stringify(sendData) ,//参数，（注：你后台的方法参数不好传啊）
                contentType: 'application/json',
                async : true,
                success:function (data) {
                    if(data.success){

                    }
                    layer.msg(data.success)
                },
                error:function (data) {
                    layer.msg(data.error);
                }
            });
            return false;
        });
        form.on('submit(demo2)', function(data){
            var data = data.field;
            var sendData =[data.userSecurity,data.securityAnwser];
            alert(JSON.stringify(sendData))
            $.ajax({
                url: "/alterpwd",
                type: "POST",
                beforeSend : function(xhr) {
                    xhr.setRequestHeader(header, token);
                },
                data: JSON.stringify(sendData) ,//参数，（注：你后台的方法参数不好传啊）
                contentType: 'application/json',
                async : true,
                success:function (data) {
                    if(data.success){

                    }
                    layer.msg(data.success)
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
