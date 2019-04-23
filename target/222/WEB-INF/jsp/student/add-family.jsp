<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: FangCheng
  Date: 2019/4/21
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" pageEncoding="UTF-8">
    <title>家庭成员信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <link rel="stylesheet" href="/static/nprogress/nprogress.css">
</head>
<body>
<script src="/static/plugins/jquery.1.12.4.min.js"></script>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;margin-left: 20px;margin-right: 20px">
    <legend>添加家庭成员</legend>

<form:form class="layui-form layui-form-pane" action="/addfamily" method="post" accept-charset="UTF-8">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="userName" lay-verify="required|title" autocomplete="off" value="方程" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">必填</div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">年龄</label>
            <div class="layui-input-inline">
                <input type="number" name="userAge" autocomplete="off" class="layui-input" value="24" min="1" max="150">
            </div>
            <div class="layui-form-mid layui-word-aux">必填</div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单选框</label>
        <div class="layui-input-block">
            <input type="radio" name="userSex" value="男" title="男">
            <input type="radio" name="userSex" value="女" title="女">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">职业</label>
            <div class="layui-input-inline">
                <input type="text" name="occupation" lay-verify="title" autocomplete="off" value="学生" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">与学生关系</label>
            <div class="layui-input-inline">
                <input type="text" name="relationShip" lay-verify="required|title" autocomplete="off" value="本人" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">必填</div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">健康状况</label>
            <div class="layui-input-inline">
                <input type="text" name="health" lay-verify="required|title" placeholder="健康/患病" value="健康" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">年收入</label>
            <div class="layui-input-inline">
                <input type="number" name="annualIncome" autocomplete="off" value="2000" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">工作单位</label>
            <div class="layui-input-inline">
                <input type="text" name="workUnit" lay-verify="required|title" placeholder="可填“没有”" value="没有" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">联系电话</label>
            <div class="layui-input-inline">
                <input type="tel" name="phoneNumber" lay-verify="required|phone" autocomplete="off" value="17789194920" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">必填</div>
        </div>
    </div>

    <div class="layui-form-item">
        <button class="layui-btn" lay-submit="" lay-filter="demo2">提交</button>
    </div>
</form:form>
</fieldset>
<script src="/static/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            title: function(value){
                var re= /select|update|delete|exec|count|’|"|=|;|>|<|%/i;
                if(re.test(value.toLowerCase())){
                    return '输入的内容有敏感字符呦( ఠൠఠ )ﾉ';
                }if(value.length < 2){
                    return '亲，检查下填写的内容对吗？';
                }
            }
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });
        //监听提交
        form.on('submit(demo1)', function(data){
            layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            })
            return false;
        });
    });
</script>
<!--js逻辑-->
<script src="/static/nprogress/nprogress.js"></script>
<script>
    NProgress.start();
    window.onload = function () {
        NProgress.done();
    }
</script>
</body>
</html>