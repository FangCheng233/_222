<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="forrm" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: FangCheng
  Date: 2019/3/28
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<% response.setHeader("refresh","30"); %>--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>layui</title>
<%--    <meta name="renderer" content="webkit">
    <meta http-equiv="Content-Type" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">--%>
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/layui/css/layui.css"  media="all">

    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<script src="/static/plugins/jquery.1.12.4.min.js"></script>
<%--<fieldset class="layui-elem-field" style="margin-top: 20px;margin-left: 20px;" >
    <legend>基本资料</legend>
    <div class="layui-field-box" style="width: 60%">
        <div style="display:inline-block;margin-left: 20px">学号：</div><div style="display:inline-block;margin-left: 220px">${user}</div>
        <div style="display:inline-block;margin-left: 20px">姓名：</div><div style="display:inline-block;margin-left: 220px">${user}</div>
        <div style="display:inline-block;margin-left: 20px">年级：</div><div style="display:inline-block;margin-left: 220px">${user}</div>
        <hr class="layui-bg-red">
        <div style="display:inline-block;margin-left: 20px">院系：</div><div style="display:inline-block;margin-left: 220px">${user}</div>
        <div style="display:inline-block;margin-left: 20px">专业：</div><div style="display:inline-block;margin-left: 220px">${user}</div>
        <div style="display:inline-block;margin-left: 20px">班级：</div><div style="display:inline-block;margin-left: 220px">${user}</div>
        <hr class="layui-bg-orange">
        <div style="display:inline-block;margin-left: 20px">籍贯：</div><div style="display:inline-block;margin-left: 220px">${user}</div>
        <div style="display:inline-block;margin-left: 20px">民族：</div><div style="display:inline-block;margin-left: 220px">${user}</div>
        <div style="display:inline-block;margin-left: 20px">身份证号：</div><div style="display:inline-block;margin-left: 220px">${user}</div>
        <hr class="layui-bg-green">
        <div style="display:inline-block;margin-left: 20px">性别：</div><div style="display:inline-block;margin-left: 220px">${user}</div>
        <div style="display:inline-block;margin-left: 20px">寝室：</div><div style="display:inline-block;margin-left: 220px">${user}</div>
        <div style="display:inline-block;margin-left: 20px">寝室：</div><div style="display:inline-block;margin-left: 220px">${user}</div>&lt;%&ndash;//选择器选择&ndash;%&gt;
        <hr class="layui-bg-cyan">
        <div style="display:inline-block;margin-left: 20px">QQ：</div><div style="display:inline-block;margin-left: 220px">${user}</div>
        <div style="display:inline-block;margin-left: 20px">邮箱：</div><div style="display:inline-block;margin-left: 220px">${user}</div>
        <div style="display:inline-block;margin-left: 20px">手机号：</div><div style="display:inline-block;margin-left: 220px">${user}</div>&lt;%&ndash;//选择器选择&ndash;%&gt;
        &lt;%&ndash;<hr class="layui-bg-blue">&ndash;%&gt;
            &lt;%&ndash;专业&ndash;%&gt;
        &lt;%&ndash;<hr class="layui-bg-black">&ndash;%&gt;

        &lt;%&ndash;灰色分割线&ndash;%&gt;
        &lt;%&ndash;<hr class="layui-bg-gray">&ndash;%&gt;
    </div>

</fieldset>--%>
<%--<fieldset class="layui-elem-field" style="margin-top: 20px;margin-left: 20px;">--%>
    <%--<legend>家庭信息</legend>--%>
    <%--<table class="layui-table" lay-data="{height:232, url:'/static/data/role.json', id:'idTest',toolbar: '#toolbarDemo'}" lay-filter="demo">--%>
        <%--<thead>--%>
        <%--<tr>--%>
            <%--<th lay-data="{field:'username', width:90,align:'center'}">姓名</th>--%>
            <%--<th lay-data="{field:'sex', width:80, sort: true, align:'center'}">年龄</th>--%>
            <%--<th lay-data="{field:'classify', width:80, align:'center'}">职业</th>--%>
            <%--<th lay-data="{field:'city', width:120, align:'center'}">与学生关系</th>--%>
            <%--<th lay-data="{field:'sign', width:160, align:'center'}">健康状况</th>--%>
            <%--<th lay-data="{field:'experience', width:80, sort: true,align:'center'}">年收入</th>--%>
            <%--<th lay-data="{field:'wealth', width:200, sort: true, align:'center'}">工作（学校）单位</th>--%>
            <%--<th lay-data="{field:'score', width:120, sort: true, fixed: 'right'}">联系电话</th>--%>
            <%--<th lay-data="{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}"></th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
    <%--</table>--%>

    <%--<script type="text/html" id="barDemo">--%>
        <%--<a href="javascript:;" title="编辑" lay-event="edit"><i class="layui-icon">&#xe642;</i></a>--%>
        <%--<a href="javascript:;" title="删除" lay-event="del"><i class="layui-icon">&#xe640;</i></a>--%>
    <%--</script>--%>
    <%--<script type="text/html" id="toolbarDemo">--%>
        <%--<div class="layui-btn-container">--%>
            <%--<button class="layui-btn" id="addRole"><i class="layui-icon">&#xe61f;</i>添加家庭成员</button>--%>
        <%--</div>--%>
    <%--</script>--%>
<%--</fieldset>--%>
<%--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;margin-left: 20px">
    <legend>影响家庭贫困的因素</legend>
</fieldset>--%>
<form:form class="layui-form" method="post" action="/addapplication" >
    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-left: 20px">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">家庭年收入</label>
                <div class="layui-input-inline">
                    <input type="number" name="yearlyIncome" id="yearlyIncome" lay-verify="required|number" autocomplete="off" class="layui-input" required='' min="0" step="1000">
                    <label alt='请输入整数' placeholder='单位为￥'></label>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">家庭人口数</label>
                <div class="layui-input-inline">
                    <input type="number" name="populationSize" id="populationSize" lay-verify="required|number" autocomplete="off" class="layui-input" required='' min="0" max="10">
                    <label alt='家庭成员总数' placeholder='家庭成员'></label>
                </div>
            </div>
            <div class="layui-inline" id="hide">
                <label class="layui-form-label">人均年收入</label>
                <div class="layui-input-inline">
                    <script type="text/javascript">
                        var value1 = $("#yearlyIncome").val();
                        var value2 = $("#populationSize").val();
                        document.write(16);
                    </script>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">负债情况</label>
                <div class="layui-input-inline" id="CheckBox">
                    <input type="checkbox" name="close" lay-skin="switch" lay-text="有|无">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">负债金额</label>
                <div class="layui-input-inline">
                    <select name="quiz1">
                        <option value="">无</option>
                        <option value="1万以下">1万以下</option>
                        <option value="1-3万">1-3万</option>
                        <option value="3-5万">3-5万</option>
                        <option value="5万以上">5万以上</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">自然灾害</label>
            <div class="layui-input-block">
                <input type="text" name="title" lay-verify="required|title" autocomplete="off" class="layui-input" required=''>
                <label alt='家庭遭受自然灾害情况' placeholder='时间/原因/损失'></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">突发意外</label>
            <div class="layui-input-block">
                <input type="text" name="unexpectedAccident" lay-verify="required|title" autocomplete="off" class="layui-input" required=''>
                <label alt='家庭遭受意外事件' placeholder='时间/原因/损失'></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">成员情况</label>
            <div class="layui-input-block">
                <input type="text" name="membershipSituation" lay-verify="required|title" autocomplete="off" class="layui-input" required=''>
                <label alt='家庭成员身体状况' placeholder='因残疾/患病/年迈而劳动力弱等原因'></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">失业情况</label>
            <div class="layui-input-block">
                <input type="text" name="unemploymentSituation" lay-verify="required|title" autocomplete="off" class="layui-input" required=''>
                <label alt='家庭成员失业状况' placeholder='何时失业'></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">获资助情况</label>
            <div class="layui-input-block">
                <input type="text" name="fundedSituation" lay-verify="required|title" autocomplete="off" class="layui-input" required=''>
                <label alt='入学以来接受过的资助情况' placeholder='包含奖学金、助学金'></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">其他情况</label>
            <div class="layui-input-block">
                <input type="text" name="title" lay-verify="required|title" autocomplete="off" class="layui-input">
            </div>
        </div>
    </blockquote>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;margin-left: 20px">
        <legend>家庭联系方式</legend>
    </fieldset>
    <blockquote class="layui-elem-quote layui-quote-nm" STYLE="margin-top: 30px;margin-left: 20px">
        <div class="layui-form-item">
            <label class="layui-form-label">家庭地址</label>
            <div class="layui-input-block">
                <input type="text" name="title" lay-verify="title" placeholder="" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">通讯地址</label>
            <div class="layui-input-block">
                <input type="text" name="title" lay-verify="title" placeholder="" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">邮政编码</label>
                <div class="layui-input-inline">
                    <input type="postNumber" name="postNumber" lay-verify="required|postNumber" autocomplete="off" class="layui-input">
                </div>
            </div>
            <%--<div class="layui-inline">--%>
                <%--<label class="layui-form-label">收信人</label>--%>
                <%--<div class="layui-input-inline">--%>
                    <%--<input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input">--%>
                <%--</div>--%>
            <%--</div>--%>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">联系人1</label>
                <div class="layui-input-inline">
                    <input type="text" name="addressee" lay-verify="required|title" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">联系人1电话</label>
                <div class="layui-input-inline">
                    <input type="tel" name="contactNumbere" lay-verify="required|phone" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">联系人2</label>
                <div class="layui-input-inline">
                    <input type="text" name="emergencyContact" lay-verify="required|title" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">联系人2电话</label>
                <div class="layui-input-inline">
                    <input type="tel" name="emergencyContactNumber" lay-verify="required|phone" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
    </blockquote>
    <fieldset class="layui-elem-field" style="margin-top: 20px;margin-left: 20px;">
    <legend>申请记录</legend>
    <table class="layui-table" lay-data="{url:'/static/data/role.json', id:'test2'}" lay-filter="test2">
        <thead>
        <tr style="width: auto" bgcolor="#008b8b">
            <th lay-data="{field:'id', width:80, minWidth: 100}">学年度</th>
            <th lay-data="{field:'povertyLevel', width:150,}">家庭经济困难等级</th>
            <th lay-data="{field:'reasonsForApplication', minWidth: 150}">申请理由</th>
        </tr>
        </thead>
    </table>
</fieldset>
    <fieldset class="layui-elem-field" style="margin-top: 20px;margin-left: 20px;">
        <legend>申请认定等级及理由</legend>
        <div class="layui-form-item">
            <label class="layui-form-label">贫困等级</label>
            <div class="layui-input-inline">
                <select name="povertyLevel" id="povertyLevel">
                    <option value="">请选择</option>
                    <option value="一般贫困">一般贫困</option>
                    <option value="一般贫困">特别贫困</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">认定理由</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容" id="reasonsForApplication" class="layui-textarea"></textarea>
            </div>
        </div>
    </fieldset>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
    <input type="hidden" name="applicationNumber" value="param1">
    <input type="hidden" name="yearlyIncome" value="param1">
    <input type="hidden" name="populationSize" value="param1">
    <input type="hidden" name="perCapitaIncome" value="param1">
    <input type="hidden" name="unexpectedAccident" value="param1">
    <input type="hidden" name="naturalDisaster" value="param1">


</form:form>
<script src="/static/layui/layui.js" charset="utf-8"></script>
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
        //监听指定开关
        form.on('switch(switchTest)', function(data){
            layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
                offset: '6px'
            });
            layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
        });

        //监听提交
        form.on('submit(demo1)', function(data){
            var sendData = JSON.stringify(data.field);
            sendData.yearlyIncome = "getValue";
            sendData.applicationNumber = "asda";
/*            sendData.userMobile = $("#userMobile").textbox("getValue");
            sendData.userTel = $("#userTel").textbox("getValue");
            sendData.orgId = $("#orgId").combobox("getValue");
            sendData.lastdate = $("#lastdate").datebox("getValue");
            sendData.remark = $("#remark").textbox("getValue");
            sendData.password = $("#password").val();*/

/*                $.ajax({
                    url:'/addapplication',
                    type:'post',
                    dataType:'json',
                    contentType:"application/json;charset=utf-8",
                    async:true,//异步请求
                    cache:false,
                    data:sendData,//使用变量sendData
                    //执行成功的回调函数
                    success:function(data) {
                        if (data.length()>=0)
                            alert(123);
                    },
                    //执行失败或错误的回调函数
                });*/
        });

        //表单初始赋值
        form.val('example', {
            "username": "贤心" // "name": "value"
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
    layui.use('table', function(){
        var table = layui.table;
        //监听表格复选框选择
        table.on('checkbox(demo)', function(obj){
            console.log(obj)
        });
        //监听工具条
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                layer.msg('ID：'+ data.id + ' 的查看操作');
            } else if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del();
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                layer.alert('编辑行：<br>'+ JSON.stringify(data))
            }
        });

        var $ = layui.$, active = {
            getCheckData: function(){ //获取选中数据
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
            ,getCheckLength: function(){ //获取选中数目
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
            }
            ,isAll: function(){ //验证是否全选
                var checkStatus = table.checkStatus('idTest');
                layer.msg(checkStatus.isAll ? '全选': '未全选')
            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
    $("#addRole").click(function () {
        layer.open({
            title: '添加角色',
            type: 2,
            shade: false,
            maxmin: true,
            shade: 0.5,
            anim: 4,
            area: ['90%', '90%'],
            content: 'role-add.html',
            zIndex: layer.zIndex,
            // skin: 'layui-layer-molv',
            end: function () {
                $(".layui-laypage-btn")[0].click();
            }
        });
    })
</script>
</body>
</html>
