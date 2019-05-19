<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.security.MessageDigest"%>
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
<form:form action="/login" method="post" class="layui-form" onsubmit="return checkForm()">
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
            <input name="userId" placeholder='学号/工号' id = "userId" maxlength="16" type='text' AUTOCOMPLETE="off"/>
            <div class='validation'>
                <img alt="" src='/static/jQueryLogin/img/tick.png'>
            </div>
        </div>
        <div class='login_fields__password'>
            <div class='icon'>
                <img alt="" src='/static/jQueryLogin/img/lock_icon_copy.png'>
            </div>
            <input type="password" id="password"  placeholder="密码" value="" required>
            <input type='hidden' name='password' id='md5_pwd' value=''/>
            <div class='validation'>
                <img alt="" src='/static/jQueryLogin/img/tick.png'>
            </div>
        </div>
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
</form:form>
<div class="OverWindows"></div>
<script src="/static/plugins/jquery.1.12.4.min.js"></script>
<script src="/static/jQueryLogin/js/Treatment.js" type="text/javascript"></script>
<script src="/static/layui/layui.js" charset="utf-8"></script>
<script src="/static/js/md5.js" charset="utf-8"></script>
<script type="text/javascript">
    function checkForm() {
        var input_pwd= document.getElementById('password');
        var password= document.getElementById('md5_pwd');
        password.value = hex_md5(input_pwd.value)
        return ture;
    }
    var header = $("meta[name='_csrf_header']").attr("content");
    var token =$("meta[name='_csrf']").attr("content");
/*    var canGetCookie = 1;//是否支持存储Cookie 0 不支持 1 支持*/
    //默认账号密码
    var userId = "学号/工号";
    var password = "2";

    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
        //自定义验证规则
        form.verify({
            title: function(value){
                var re= /select|update|delete|exec|count|’|"|=|;|>|<|%/i;
                if(re.test(value.toLowerCase())){
                    return '不能含有敏感字符';
                }
            }
        });
        form.on('submit(demo1)', function(data){
            var data1 = data.field;
            var sendData =[data1.userId,data1.password];
            $.ajax({
                url: "/login",
                type: "POST",
                beforeSend : function(xhr) {
                    xhr.setRequestHeader(header, token);
                },
                data: JSON.stringify(sendData) ,//参数，（注：你后台的方法参数不好传啊）
                contentType: 'application/json',
                async : false,
                success:function (data) {
                },
                error:function (data) {
                    layer.msg(data.error);
                }
            });
            return false;
        });
    });
    layui.use('layer', function () {
        var msgalert = '默认账号:' + userId+'</br>'+'默认密码:' + '身份证号后六位';
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
        $.ajax({
            url: "/findUser",
            type: "GET",
            beforeSend : function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {userId:userId },//参数，（注：你后台的方法参数不好传啊）
            contentType: 'application/json',
            async : false,
            success:function (data) {
                var error = data.error;
                layer.msg(error)
                if(error){
                    layer.msg(data.error)
                    return false;
                }
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
                    end: function () {
                        $(".layui-laypage-btn")[0].click();
                        window.parent.location.reload();
                    }
                });
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
<script>
    //定义画布宽高和生成点的个数
    var WIDTH = window.innerWidth, HEIGHT = window.innerHeight, POINT = 35;

    var canvas = document.getElementById('Mycanvas');
    canvas.width = WIDTH,
        canvas.height = HEIGHT;
    var context = canvas.getContext('2d');
    context.strokeStyle = 'rgba(0,0,0,0.02)',
        context.strokeWidth = 1,
        context.fillStyle = 'rgba(0,0,0,0.05)';
    var circleArr = [];

    //线条：开始xy坐标，结束xy坐标，线条透明度
    function Line (x, y, _x, _y, o) {
        this.beginX = x,
            this.beginY = y,
            this.closeX = _x,
            this.closeY = _y,
            this.o = o;
    }
    //点：圆心xy坐标，半径，每帧移动xy的距离
    function Circle (x, y, r, moveX, moveY) {
        this.x = x,
            this.y = y,
            this.r = r,
            this.moveX = moveX,
            this.moveY = moveY;
    }
    //生成max和min之间的随机数
    function num (max, _min) {
        var min = arguments[1] || 0;
        return Math.floor(Math.random()*(max-min+1)+min);
    }
    // 绘制原点
    function drawCricle (cxt, x, y, r, moveX, moveY) {
        var circle = new Circle(x, y, r, moveX, moveY)
        cxt.beginPath()
        cxt.arc(circle.x, circle.y, circle.r, 0, 2*Math.PI)
        cxt.closePath()
        cxt.fill();
        return circle;
    }
    //绘制线条
    function drawLine (cxt, x, y, _x, _y, o) {
        var line = new Line(x, y, _x, _y, o)
        cxt.beginPath()
        cxt.strokeStyle = 'rgba(0,0,0,'+ o +')'
        cxt.moveTo(line.beginX, line.beginY)
        cxt.lineTo(line.closeX, line.closeY)
        cxt.closePath()
        cxt.stroke();

    }
    //初始化生成原点
    function init () {
        circleArr = [];
        for (var i = 0; i < POINT; i++) {
            circleArr.push(drawCricle(context, num(WIDTH), num(HEIGHT), num(15, 2), num(10, -10)/40, num(10, -10)/40));
        }
        draw();
    }
    //每帧绘制
    function draw () {
        context.clearRect(0,0,canvas.width, canvas.height);
        for (var i = 0; i < POINT; i++) {
            drawCricle(context, circleArr[i].x, circleArr[i].y, circleArr[i].r);
        }
        for (var i = 0; i < POINT; i++) {
            for (var j = 0; j < POINT; j++) {
                if (i + j < POINT) {
                    var A = Math.abs(circleArr[i+j].x - circleArr[i].x),
                        B = Math.abs(circleArr[i+j].y - circleArr[i].y);
                    var lineLength = Math.sqrt(A*A + B*B);
                    var C = 1/lineLength*7-0.009;
                    var lineOpacity = C > 0.03 ? 0.03 : C;
                    if (lineOpacity > 0) {
                        drawLine(context, circleArr[i].x, circleArr[i].y, circleArr[i+j].x, circleArr[i+j].y, lineOpacity);
                    }
                }
            }
        }
    }

    //调用执行
    window.onload = function () {
        init();
        setInterval(function () {
            for (var i = 0; i < POINT; i++) {
                var cir = circleArr[i];
                cir.x += cir.moveX;
                cir.y += cir.moveY;
                if (cir.x > WIDTH) cir.x = 0;
                else if (cir.x < 0) cir.x = WIDTH;
                if (cir.y > HEIGHT) cir.y = 0;
                else if (cir.y < 0) cir.y = HEIGHT;

            }
            draw();
        }, 16);
    }

</script>

</body>
</html>

