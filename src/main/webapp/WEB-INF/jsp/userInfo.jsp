<%--
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
&lt;%&ndash;
  Created by IntelliJ IDEA.
  User: FangCheng
  Date: 2019/4/11
  Time: 15:55
  To change this template use File | Settings | File Templates.
&ndash;%&gt;
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header"  content="${_csrf.headerName}"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="/static/layui/css/layui.css"rel="stylesheet" media="all">
    <link href="/static/css/select_gj.css" rel="stylesheet"/>

    <link href="/static/select/css/select.css" rel="stylesheet"/>
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <style>
        input[disabled]{color:#535353;opacity:1}
        select[disabled]{color:#00B83F;opacity:1}
        select[disabled='disabled']::-ms-value {color: #0000FF;}

    </style>
</head>
<body bgcolor="#fffafa">
<form class="layui-form" action="##" lay-filter="example">
<fieldset class="layui-elem-field" style="margin-top: 20px;margin-right: 30%;margin-left: 20px">
    <legend>基本资料</legend>
    <div class="layui-field-box">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="userName" lay-verify="required|title" value="${userInfo.userName}" autocomplete="off" class="layui-input" disabled="disabled">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;性别</label>
                    <div class="layui-input-inline">
                        <select name="userSex"  disabled="true">
                            <option value="" selected="">${userInfo.userSex}</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;证件类别</label>
                    <div class="layui-input-inline">
                        <select name="idType" disabled="disabled">
                            <option value="" select="">${userInfo.idType}</option>
                            <option value="身份证">身份证</option>
                            <option value="护照">护照</option>
                            <option value="港澳台居民居住证">港澳台居民居住证</option>
                            <option value="出入境许可证">出入境许可证</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">曾用名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="nameUsedBefore" lay-verify="title" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;出生日期</label>
                    <div class="layui-input-inline">
                        <input type="date" name="birthDate" id="date" value="${userInfo.birthDate}" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" disabled="disabled">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;证件号码</label>
                    <div class="layui-input-inline">
                        <input type="text" name="idNumber" lay-verify="identity" value="${userInfo.idNumber}" placeholder="" autocomplete="off" class="layui-input" disabled="disabled">
                    </div>
                </div>
            </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label"><span style="color: red">*</span>&nbsp;籍贯-省</label>
                <div class="layui-input-inline">
                    <select name="basePlaceP" lay-filter="province" id="province">
                        <option></option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label"><span style="color: red">*</span>&nbsp;市</label>
                <div class="layui-input-inline">
                    <select name="basePlaceC" lay-filter="city" id="city">
                        <option></option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label"><span style="color: red">*</span>&nbsp;县/区</label>
                <div class="layui-input-inline">
                    <select name="basePlaceA" lay-filter="area" id="area">
                        <option></option>
                    </select>
                </div>
            </div>
        </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;民族</label>
                    <div class="layui-input-inline">
                        <select name="nation" id="nation" disabled="disabled">
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;政治面貌</label>
                    <div class="layui-input-inline">
                        <select name="politicalOutlook">
                            <option value="">请选择</option>
                            <option value="共青团员" selected="">共青团员</option>
                            <option value="党员">党员</option>
                            <option value="群众">群众</option>
                        </select>
                    </div>
                </div>
            </div>
    </div>
</fieldset>
<fieldset class="layui-elem-field" style="margin-top: 20px;margin-right: 30%;margin-left: 20px">
    <legend>学籍信息</legend>
    <div class="layui-field-box">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;学号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="userId" lay-verify="required|title" value="${userInfo.userId}" autocomplete="off" class="layui-input" disabled="disabled">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;校区</label>
                    <div class="layui-input-inline">
                        <select name="school" disabled="disabled">
                            <option value="">西北工业大学沣河校区</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;年级</label>
                    <div class="layui-input-inline">
                        <input type="number" name="userGrade" lay-verify="required" value="${userInfo.userGrade}" autocomplete="off" class="layui-input" disabled="disabled">
                    </div>
                </div>
            </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label"><span style="color: red">*</span>&nbsp;院系</label>
                <div class="layui-input-inline">
                    <select name="userCollege">
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label"><span style="color: red">*</span>&nbsp;专业</label>
                <div class="layui-input-inline">
                    <select name="userMajor">
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label"><span style="color: red">*</span>&nbsp;班级</label>
                <div class="layui-input-inline">
                    <select name="userClass">
                    </select>
                </div>
            </div>
        </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;入学年月</label>
                    <div class="layui-input-inline">
                        <input type="number" value="${userInfo.userGrade}" autocomplete="off" class="layui-input" min="2015" max="2018" disabled="disabled">
                    </div>
                </div>
            </div>
    </div>
</fieldset>
    <c:choose>
        <c:when test="${edit}">
            <fieldset class="layui-elem-field" style="margin-top: 20px;margin-right: 30%;margin-left: 20px">
                <legend>联系方式</legend>
                <div class="layui-field-box">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label"><span style="color: red">*</span>&nbsp;联系电话</label>
                            <div class="layui-input-inline">
                                <input type="tel" name="phoneNumber" lay-verify="required|phone" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label"><span style="color: red">*</span>&nbsp;QQ</label>
                            <div class="layui-input-inline">
                                <input type="number" name="QQ" &lt;%&ndash;lay-verify=""&ndash;%&gt; autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label"><span style="color: red">*</span>&nbsp;邮箱</label>
                            <div class="layui-input-inline">
                                <input type="email" name="userEmail" lay-verify="email" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">邮政编码</label>
                            <div class="layui-input-inline">
                                <input type="tel" name="postNumber" lay-verify="required|postNumber" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">详细通讯地址</label>
                        <div class="layui-input-block">
                            <input type="text" name="postalAddress" lay-verify="required|postNumber" autocomplete="off" placeholder="请输入标题" class="layui-input">
                        </div>
                    </div>
                </div>
            </fieldset>
            <div class="has-error">
                <form:errors path="birthDate" class="help-inline"/>
            </div>
        </c:when>
    </c:choose>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">保存信息</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

        <form class="layui-form layui-form-pane" action="javascript:;">

        </form>
<script src="/static/layui/layui.js" charset="utf-8"></script>
<script src="/static/plugins/jquery.1.12.4.min.js"></script>
<script>
    var header = $("meta[name='_csrf_header']").attr("content");
    var token =$("meta[name='_csrf']").attr("content");
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;
        //日期
        laydate.render({
            elem: '#date'
            ,lang: 'zh'
        });

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            title: function(value){
                var re= /select|update|delete|exec|count|’|"|=|;|>|<|%/i;
                if(re.test(value.toLowerCase())){
                    return '不能含有敏感字符';
                }
            }
            ,reasons: function(value){
                var re= /select|update|delete|exec|count|’|"|=|;|>|<|%/i;
                if(re.test(value.toLowerCase())||value.length < 10){
                    return '标题至少得5个字符啊';
                }
            }
            ,postNumber:[
                /^[1-9][0-9]{5}$/
                ,'6位邮政编码，只能为数字且不能出现空格'
            ]
            ,pass: [
                /^[\S]{6,12}$/
                ,'密码必须6到12位，且不能出现空格'
            ]
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });

        //监听提交
        form.on('submit(demo1)', function(data){
            var sendData = JSON.stringify(data.field)
            layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            })
            $.ajax({
                url: "/edit-user",
                type: "POST",
                beforeSend : function(xhr) {
                    xhr.setRequestHeader(header, token);
                },
                data: sendData,//参数，（注：你后台的方法参数不好传啊）
                contentType: 'application/json',
                async : true,
                success:function (data) {
                    layer.msg(data.resultString)
                },
                error:function (data) {
                    alert("data:" +JSON.stringify(data));
                }
            });
            return false;
        });
    });
</script>
<script>
    var nations = ["汉族", "蒙古族", "回族", "藏族", "维吾尔族", "苗族", "彝族", "壮族", "布依族", "朝鲜族", "满族", "侗族", "瑶族", "白族",
        "土家族", "哈尼族", "哈萨克族", "傣族", "黎族", "傈僳族", "佤族", "畲族", "高山族", "拉祜族", "水族", "东乡族", "纳西族", "景颇族", "柯尔克孜族", "土族",
        "达斡尔族", "仫佬族", "羌族", "布朗族", "撒拉族", "毛南族", "仡佬族", "锡伯族", "阿昌族", "普米族", "塔吉克族", "怒族", "乌孜别克族", "俄罗斯族", "鄂温克族",
        "德昂族", "保安族", "裕固族", "京族", "塔塔尔族", "独龙族", "鄂伦春族", "赫哲族", "门巴族", "珞巴族", "基诺族"];
    var option = "";
    for(var i = 0; i <
    nations.length; i++) {
        option += '<option value="' + (i + 1) + '">' + nations[i] + '</option>';
    }
    $(option).appendTo("#nation");
</script>
<script src="/static/select/js/select.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
--%>
