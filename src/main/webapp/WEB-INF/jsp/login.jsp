<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 郑清
  Date: 2018/12/2 0002
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <meta charset="UTF-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header"  content="${_csrf.headerName}"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>登录界面</title>
    <link href="/static/jQueryLogin/css/default.css" rel="stylesheet" type="text/css" />
    <!--必要样式-->
    <link href="/static/jQueryLogin/css/styles.css" rel="stylesheet" type="text/css" />
    <link href="/static/jQueryLogin/css/demo.css" rel="stylesheet" type="text/css" />
    <link href="/static/jQueryLogin/css/loaders.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="/static/layui/css/layui.css"  media="all">
</head>
<body>
<form:form action="/login" method="post" class="layui-form">
<div class='login'>
    <div class='login_title'>
        <span>用户登录</span>
    </div>
    <div class='login_fields'>
        <c:if test="${param.error != null}">
            <div class="alert alert-danger" style="margin-left: 50px">
                <p>未知用户名或密码错误. </p>
            </div>
        </c:if>
        <div class='login_fields__user'>
            <div class='icon'>
                <img alt="" src='/static/jQueryLogin/img/user_icon_copy.png'>
            </div>
            <input name="userId" placeholder='学号/工号' id = "userId" maxlength="16" type='text' autocomplete="off"/>
            <div class='validation'>
                <img alt="" src='/static/jQueryLogin/img/tick.png'>
            </div>
        </div>
        <div class='login_fields__password'>
            <div class='icon'>
                <img alt="" src='/static/jQueryLogin/img/lock_icon_copy.png'>
            </div>
            <input type="password" id="password" name="password" placeholder="密码" required>
            <div class='validation'>
                <img alt="" src='/static/jQueryLogin/img/tick.png'>
            </div>
        </div>
<%--        <div class='login_fields__password'>
            <div class='icon'>
                <img alt="" src='/static/jQueryLogin/img/key.png'>
            </div>
            <input name="code" placeholder='验证码' lay-verify="required|code" maxlength="4" type='text' autocomplete="off" >
            <div class='validation' style="opacity: 1; right: -5px;top: -3px;">
                <canvas class="J_codeimg" id="myCanvas" onclick="Code();">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
            </div>
        </div>--%>
        <div class='login_fields__submit'>
            <input  lay-submit="" type="submit" value='登录'>
        </div>
    </div>
    <div class='pwd'>
        <a a href="javascript:void(0);" onclick="js_method()"><span>找回密码</span></a>
    </div>
    <div class='disclaimer'>
        <p>欢迎登陆贫困生信息管理系统</p>
    </div>
</div>
    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
</form:form>
<div class="OverWindows"></div>
<script src="/static/plugins/jquery.1.12.4.min.js"></script>
<script src="/static/jQueryLogin/js/Treatment.js" type="text/javascript"></script>
<script src="/static/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
    var header = $("meta[name='_csrf_header']").attr("content");
    var token =$("meta[name='_csrf']").attr("content");
/*    var canGetCookie = 1;//是否支持存储Cookie 0 不支持 1 支持*/
    //默认账号密码
    var userId = "学号/工号";
    var password = "2";
/*    var CodeVal = 0;*/
/*    Code();
    function Code() {
        if(canGetCookie == 1){
            createCode("AdminCode");
            var AdminCode = getCookieValue("AdminCode");
            showCheck(AdminCode);
        }else{
            showCheck(createCode(""));
        }
    }*/
/*    function showCheck(a) {
        CodeVal = a;
        var c = document.getElementById("myCanvas");
        var ctx = c.getContext("2d");
        ctx.clearRect(0, 0, 1000, 1000);
        ctx.font = "80px 'Hiragino Sans GB'";
        ctx.fillStyle = "#E8DFE8";
        ctx.fillText(a, 50, 100);
    }*/
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
        //自定义验证规则
        form.verify({
            title: function(value){
                var re= /select|update|delete|exec|count|’|"|=|;|>|<|%/i;
                if(re.test(value.toLowerCase())){
                    return '不能含有敏感字符';
                }
            },
            code: function(value){
                if(!(value.toLowerCase()).equals("123")){
                    alert("123")
                    return '验证码错误';
                }
                else {
                    alert("456")
                }
            }
        });
    });
    layui.use('layer', function () {
        var msgalert = '默认账号:' + userId+'</br>'+'默认密码:' + password;
        var index = layer.alert(msgalert, { icon: 6, time: 4000, offset: 't', closeBtn: 0, title: '友情提示', btn: [], anim: 2, shade: 0 });
        layer.style(index, {
            color: '#777'
        });
        //非空验证
    })
</script>
<script type="text/javascript">
    function js_method() {
        var userId = $('#userId').val();
        layer.open({
            title: '验证密保问题',
            type: 2,
            shade: false,
            maxmin: true,
            shade: 0.5,
            anim: 4,
            area: ['40%', '40%'],
            content: '/getUserSecurity?userId='+userId,
            zIndex: layer.zIndex,
            // skin: 'layui-layer-molv',
            end: function () {
                $(".layui-laypage-btn")[0].click();
            }
        });
/*        $.ajax({
            url: "/getUserSecurity?userId="+userId,
            type: "get",
            contentType: 'application/json',
            async : true,
            success:function (data) {
            },
            error:function (data) {
                layer.msg(data.error);
            }
        });*/
}
</script>
</body>
</html>

