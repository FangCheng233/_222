<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: FangCheng
  Date: 2019/4/20
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/layui/css/layui.css"  media="all">
</head>
<body>
<form:form class="layui-form" role="form" method="post" action="/addApplication"> <%--表单提交贫困申请所需信息--%>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;margin-left: 20px">
        <legend>家庭联系方式</legend>
    </fieldset>
    <fieldset class="layui-elem-field" style="margin-top: 20px;margin-left: 20px;">
        <legend>申请认定等级及理由</legend>
        <div class="layui-form-item">
            <label class="layui-form-label">贫困等级</label>
            <div class="layui-input-inline">
                <select name="povertyLevel" id="povertyLevel" lay-filter="test">
                    <option value="一般贫困">请选择</option>
                    <option value="一般贫困">一般贫困</option>
                    <option value="一般贫困">特别贫困</option>
                </select>
            </div>
        </div>
        <div id="show"style="display:inline;"></div>
    </fieldset>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form:form>
<form action="" id="myform" method="post">
    <tr class="tr">
        <td class="center" style="width:120px;">跟单结果</td>
        <td colspan="6">
            <select name="Ext_WithResult" id="Ext_WithResult" class="select Regional" lay-fiter="test"> //绑定onchange操作
                <option  value="0">--请选择--</option>
                <volist name="dmenu" id="vo">
                    <if condition="$vo.FieldName eq 'Ext_DepositWithResult'">
                        <option value="100"><${vo.MenuName}>&nbsp;&nbsp;</option>
                    </if>
                </volist>
            </select>
<%--            <div id="show"style="display:inline;"></div> --%>  //定义一个div用来存放要添加内容
        </td>
    </tr>
</form>
<div id="sign" style="display:none;">
<b>签单产品：</b>
<select name="Ext_SignType" id="Ext_SignType" class="select Regional">
    <option  value="0">--请选择--</option>
    <volist name="dmenu" id="vo">
        <if condition="$vo.FieldName eq 'Ext_SignType'">
            <option value="${vo.ID}>"><${vo.MenuName}>&nbsp;&nbsp;</option>
        </if>
    </volist>
</select>
<b>签单金额：</b><input class="ctext"  name="Ext_SignAmount" id="Ext_SignAmount" value="" type="text" />    元
<b>签单日期：</b><input class="Wdate  WdateFmtErr"  name="Htime" id="Htime" style="width:120px;" type="text" onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" readonly=""/>
</div>
<form class="layui-form" action="">
    <div class="layui-form-item">
        <label class="layui-form-label" style="width:100px">是否已购买：</label>
        <div class="layui-input-block" id="IsPurchased" >
            <input type="radio"  name="t" value="购买" title="购买" lay-filter="aaa"/>
            <input type="radio"  name="t" value="试用" title="试用" lay-filter="aaa"/>
        </div>
    </div>
    <div class="layui-form-item count" id="test" hidden>
        <label class="layui-form-label">试用次数：</label>
        <div class="layui-input-inline">
            <input type="text" id="ProbationAccount" required="required"  placeholder="请输入试用次数" autocomplete="off" class="layui-input" />
        </div>
        <%--<div class="layui-form-mid layui-word-aux"></div>--%>
    </div>
</form>
<script src="/static/plugins/jquery.1.12.4.min.js"></script>
<script src="/static/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use('form', function () {
        var form = layui.form;
        form.on('radio(aaa)', function (data) {
            alert(123)
            if ($('#IsPurchased input[name="t"]:checked ').val() == "购买") {
                $("#test").hide();
            }
            else {
                $(".count").show();
            }
            form.render();
        });
    });
</script>
<%--<form:form class="form-horizontal" role="form" method="post" action="/test/testEntity" >
    userId：<input class="td_input" name="userId" type="number"><br>
    password：<input class="td_input" name="password" type="number"><br>
    userName：<input class="td_input" name="userName" type="number"><br>
    groupId：<input class="td_input" name="groupId" type="number"><br>
    userSex：<input class="td_input" name="userSex" type="number"><br>
    userClass：<input class="td_input" name="userClass" type="number"><br>
    userMajor：<input class="td_input" name="userMajor" type="number"><br>
    userCollege：<input class="td_input" name="userCollege" type="number"><br>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10" >
            <input type="submit" value="后台用实体类接收"  class="btn btn-default" />
        </div>
    </div>
</form:form>--%>

<script type="text/javascript">
    //跟单结果在选择签单时，向form表单添加相应的显示内容
    function change(obj){
        alert(123)
        if($(obj).val()==100){
            $("#show").html('');    //先清空一下div
            var html1 = $("#sign").html();  //获取要向div中添加的内容
            $("#show").html(html1);  //向div中添加内容
        }else {
            $("#show").html('');
        }
    }
</script>
<script type="text/javascript">
    function load()
    {
        window.onfocus=function()
        {
            //
            var password = ''
            layer.prompt({
                formType: 1
                ,title: '请输入密码确认身份'
                ,value: password
            }, function(value, index){
                layer.close(index);

            });
        };
        window.onblur=function()
        {
            layer.msg(456)
        };
    }
</script>
<body onload="load();">
<script src="//res.layui.com/layui/dist/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use('table', function(){
        var table = layui.table;
        //监听单元格事件
        table.on('tool(demoEvent)', function(obj){
            var data = obj.data;
            if(obj.event === 'setSign'){
                layer.prompt({
                    formType: 2
                    ,title: '修改 ID 为 ['+ data.id +'] 的用户签名'
                    ,value: data.sign
                }, function(value, index){
                    layer.close(index);

                    //这里一般是发送修改的Ajax请求

                    //同步更新表格和缓存对应的值
                    obj.update({
                        sign: value
                    });
                });
            }
        });
    });
</script>
</body>
</html>

