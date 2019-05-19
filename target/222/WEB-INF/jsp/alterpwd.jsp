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
            <input type="password" name="password" id="password" placeholder="密码长度6-12位"lay-verify="required|title|password" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">重复密码</label>
        <div class="layui-input-inline">
            <input type="password" name="password2" id="password2" placeholder="无收入填0" lay-verify="required|title|password" autocomplete="off" class="layui-input">
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
<script src="/static/js/md5.js" charset="utf-8"></script>
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
            },
            password:function(value){
                if(value.length<6){
                    return '密码长度不足六位';
                }
                if(value.length>12){
                    return '密码长度超过12位';
                }
            },
        });
        //监听提交
        form.on('submit(demo1)', function(data){
            var data = data.field;
            if(!data.password==data.password2){
                layer.msg('两次输入的密码不一致，请重新输入')
                return false;
            }
            data.password = hex_md5(data.password)
            var sendData =[data.password,data.userId];
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
                    var i = 3;
                    var interval;
                    layer.msg('密码重置成功', {
                        btn: [i+'s后关闭'], //按钮
                        btnAlign:'c',
                        skin: 'layui-layer-molv',
                        success: function(a,b){
                            $(".layui-layer-btn0").css("backgroundColor","");
                            var fn = function() {
                                $(".layui-layer-btn0").text(i-1+'s后关闭');
                                i--;
                            };
                            interval = setInterval(function(){
                                fn();
                                if(i === 0){
                                    layer.closeAll();
                                    clearInterval(interval);
                                    var index = parent.layer.getFrameIndex(window.name);
                                    parent.layer.close(index);
                                }
                            }, 1000);
                        },
                        end:function(){
                            clearInterval(interval);
                        }
                    })
/*                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);*/
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
