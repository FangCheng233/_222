<%--
  Created by IntelliJ IDEA.
  User: FangCheng
  Date: 2019/4/11
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/layui/css/layui.css"  media="all">
    <%--<link rel="stylesheet" href="css/min/reset.css"/>--%>
    <link rel="stylesheet" href="/static/css/select_gj.css">
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
</head>
<body bgcolor="#fffafa">
<form class="layui-form" action="##" lay-filter="example">
<fieldset class="layui-elem-field" style="margin-top: 20px;margin-right: 37%;margin-left: 20px">
    <legend>基本资料</legend>
    <div class="layui-field-box">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="userName" lay-verify="required|title" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;性别</label>
                    <div class="layui-input-inline">
                        <select name="userSex">
                            <option value="">请选择性别</option>
                            <option value="男">男</option>
                            <option value="女">女</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;证件类别</label>
                    <div class="layui-input-inline">
                        <select name="idType">
                            <option value="身份证" select="">身份证</option>
                            <option value="护照">护照</option>
                            <option value="港澳台居民居住证">港澳台居民居住证</option>
                            <option value="出入境许可证">出入境许可证</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;证件号码</label>
                    <div class="layui-input-inline">
                        <input type="text" name="idNumber" lay-verify="identity" placeholder="" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;出生日期</label>
                    <div class="layui-input-inline">
                        <input type="date" name="birthDate" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">曾用名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="nameUsedBefore" <%--lay-verify="email" --%>autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;出生地-省</label>
                    <div class="layui-input-inline">
                        <select name="province1">
                            <option value="">请选择省</option>
                            <option value="浙江" selected="">浙江省</option>
                            <option value="你的工号">江西省</option>
                            <option value="你最喜欢的老师">福建省</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;市</label>
                    <div class="layui-input-inline">
                        <select name="city1">
                            <option value="">请选择省</option>
                            <option value="浙江" selected="">浙江省</option>
                            <option value="你的工号">江西省</option>
                            <option value="你最喜欢的老师">福建省</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;县/区</label>
                    <div class="layui-input-inline">
                        <select name="county1">
                            <option value="">请选择省</option>
                            <option value="浙江" selected="">浙江省</option>
                            <option value="你的工号">江西省</option>
                            <option value="你最喜欢的老师">福建省</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;籍贯-省</label>
                    <div class="layui-input-inline">
                        <select name="province2">
                            <option value="">请选择省</option>
                            <option value="浙江" selected="">浙江省</option>
                            <option value="你的工号">江西省</option>
                            <option value="你最喜欢的老师">福建省</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;市</label>
                    <div class="layui-input-inline">
                        <select name="city2">
                            <option value="">请选择省</option>
                            <option value="浙江" selected="">浙江省</option>
                            <option value="你的工号">江西省</option>
                            <option value="你最喜欢的老师">福建省</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;县/区</label>
                    <div class="layui-input-inline">
                        <select name="county2">
                            <option value="">请选择省</option>
                            <option value="浙江" selected="">浙江省</option>
                            <option value="你的工号">江西省</option>
                            <option value="你最喜欢的老师">福建省</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;生源地-省</label>
                    <div class="layui-input-inline">
                        <select name="country" onchange="turn()">
                            <option value="">请选择省</option>
                            <option value="中国">中国</option>
                            <option value="美国">美国</option>
                            <option value="英国">英国</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;市</label>
                    <div class="layui-input-inline">
                        <select name="province">
                            <option value="">请选择省</option>
                            <option value="浙江" selected="">浙江省</option>
                            <option value="你的工号">江西省</option>
                            <option value="你最喜欢的老师">福建省</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;县/区</label>
                    <div class="layui-input-inline">
                        <select name="city">
                            <option value="">请选择省</option>
                            <option value="浙江" selected="">浙江省</option>
                            <option value="你的工号">江西省</option>
                            <option value="你最喜欢的老师">福建省</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;民族</label>
                    <div class="layui-input-inline">
                        <select name="nation" id="nation">
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
<%--                <div class="layui-inline">
                    <label class="layui-form-label">国家地区</label>
                    <div class="layui-input-inline">
                        <select name="country" class="fastbannerform__country">
                            <option value="AND" title="AD" >Andorra</option>
                            <option value="ARE" title="AE" >United Arab Emirates</option>
                            <option value="AFG" title="AF" >Afghanistan</option>
                        </select>
                    </div>
                </div>--%>
            </div>
   <%--         <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">血型</label>
                    <div class="layui-input-inline">
                        <input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">健康状况</label>
                    <div class="layui-input-inline">
                        <input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">婚姻状况</label>
                    <div class="layui-input-inline">
                        <input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">港澳台侨</label>
                    <div class="layui-input-inline">
                        <input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">姓名拼音</label>
                    <div class="layui-input-inline">
                        <input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>--%>
    </div>
</fieldset>
<fieldset class="layui-elem-field" style="margin-top: 20px;margin-right: 3%;margin-left: 20px">
    <legend>学籍信息</legend>
    <div class="layui-field-box">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;学号</label>
                    <div class="layui-input-inline">
                        <input type="number" name="userId" lay-verify="required|title" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;校区</label>
                    <div class="layui-input-inline">
                        <input type="text" name="school" value="西北工业大学沣河校区" <%--lay-verify="email"--%> autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;年级</label>
                    <div class="layui-input-inline">
                        <input type="number" name="userGrade" lay-verify="email" autocomplete="off" class="layui-input" min="2015" max="2018">
                    </div>
                </div>
            </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label"><span style="color: red">*</span>&nbsp;院系</label>
                <div class="layui-input-inline">
                    <select name="userCollege">
                        <option value="">请选择省</option>
                        <option value="浙江" selected="">浙江省</option>
                        <option value="你的工号">江西省</option>
                        <option value="你最喜欢的老师">福建省</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label"><span style="color: red">*</span>&nbsp;专业</label>
                <div class="layui-input-inline">
                    <select name="userMajor">
                        <option value="">请选择省</option>
                        <option value="浙江" selected="">浙江省</option>
                        <option value="你的工号">江西省</option>
                        <option value="你最喜欢的老师">福建省</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label"><span style="color: red">*</span>&nbsp;班级</label>
                <div class="layui-input-inline">
                    <select name="userClass">
                        <option value="">请选择省</option>
                        <option value="浙江" selected="">浙江省</option>
                        <option value="你的工号">江西省</option>
                        <option value="你最喜欢的老师">福建省</option>
                    </select>
                </div>
            </div>
        </div>

            <div class="layui-form-item">
  <%--              <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;学生当前状态</label>
                    <div class="layui-input-inline">
                        <input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input">
                    </div>
                </div>--%>
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;学制</label>
                    <div class="layui-input-inline">
                        <input type="number" name="xuezhi" <%--lay-verify="email" --%>autocomplete="off" class="layui-input" min="2" max="4">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span style="color: red">*</span>&nbsp;入学年月</label>
                    <div class="layui-input-inline">
                        <input type="number" name="ruxue" <%--lay-verify="email"--%> autocomplete="off" class="layui-input" min="2015" max="2018">
                    </div>
                </div>
            </div>
    </div>
</fieldset>
<fieldset class="layui-elem-field" style="margin-top: 20px;margin-right: 37%;margin-left: 20px">
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
                        <input type="number" name="QQ" <%--lay-verify=""--%> autocomplete="off" class="layui-input">
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
                        <input type="tel" name="postNumber" lay-verify="required|phone" autocomplete="off" class="layui-input">
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
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script src="/static/layui/layui.js" charset="utf-8"></script>
<script src="/static/plugins/jquery.1.12.4.min.js"></script>
<script src="/static/js/area.js"></script>
<select name="country" onchange="turn()">
    <option value="请选择国家" selected="selected">请选择国家</option>
    <option value="中国">中国</option>
    <option value="美国">美国</option>
    <option value="英国">英国</option>
</select>
<select name="province">
    <option value="0" selected="selected">请选择省份</option>
</select>
<select name="city">
    <option value="0" selected="selected">请选择城市</option>
</select>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        //日期
        laydate.render({
            elem: '#date'
        });
        laydate.render({
            elem: '#date1'
        });

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            title: function(value){
                var re= /select|update|delete|exec|count|’|"|=|;|>|<|%/i;
                if(re.test(value.toLowerCase())||value.length < 2){
                    return '标题至少得5个字符啊';
                }
            }
            ,pass: [
                /^[\S]{6,12}$/
                ,'密码必须6到12位，且不能出现空格'
            ]
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });

        //监听指定开关
        form.on('switch(switchTest)', function(data){
            layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
                offset: '6px'
            });
            layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
        });

        //监听提交
        form.on('submit(demo1)', function(data){
            layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            })
            return false;
        });

        //表单初始赋值
        form.val('example', {
            "userName": "贤心" // "name": "value"
            ,"password": "123456"
            ,"interest": 1
            ,"like[write]": true //复选框选中状态
            ,"close": true //开关状态
            ,"sex": "女"
            ,"desc": "我爱 layui"
        })


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

</body>
</html>
