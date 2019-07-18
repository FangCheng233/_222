<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="forrm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: FangCheng
  Date: 2019/3/28
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" pageEncoding="UTF-8">
    <title>layui</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/layui/css/layui.css"  media="all">
</head>
<body>
<script src="/static/plugins/jquery.1.12.4.min.js"></script>
<fieldset class="layui-elem-field" style="margin-top: 20px;margin-left: 20px;" >
    <legend>基本资料</legend>
    <div class="layui-field-box" style="width: 60%">
        <table class="layui-table" lay-even="" lay-skin="nob">
            <colgroup>
                <col width="80">
                <col width="200">
                <col width="80">
                <col width="200">
            </colgroup>
            <thead>
            <tr>
                <th>学号：</th>
                <th>${user.userId}</th>
                <th>姓名：</th>
                <th>${user.userName}</th>
                <th>年级：</th>
                <th>${user.userGrade}</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>院系：</td>
                <td>${user.userCollege}</td>
                <td>专业：</td>
                <td>${user.userMajor}</td>
                <td>班级：</td>
                <td>${user.userClass}</td>
            </tr>
            <tr>
                <td>籍贯：</td>
                <td>${user.basePlaceP}${user.basePlaceC}</td>
                <td>民族：</td>
                <td>${user.nation}</td>
                <td>身份证号：</td>
                <td>${user.idNumber}</td>
            </tr>
            <tr>
                <td>性别：</td>
                <td>${user.userSex}</td>
                <td>寝室：</td>
                <td></td>
                <td>其他</td>
                <td></td>
            </tr>
            <tr>
                <td>QQ：</td>
                <td>${user.QQ}</td>
                <td>邮箱：</td>
                <td>${user.userEmail}</td>
                <td>手机号：</td>
                <td>${user.phoneNumber}</td>
            </tr>
            </tbody>
        </table>
    </div>
</fieldset>
<fieldset class="layui-elem-field" style="margin-top: 20px;margin-left: 20px;">
    <legend>家庭成员信息</legend>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="addRole">添加家庭成员</button>
        </div>
    </script>
    <table class="layui-hide" id="test" lay-filter="test"></table>
    <script type="text/html" id="barDemo">
        <a href="javascript:;" title="删除" lay-event="del"><i class="layui-icon">&#xe640;</i></a>
    </script>
</fieldset>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;margin-left: 20px">
    <legend>影响家庭贫困的因素</legend>
</fieldset>
<form:form class="layui-form" role="form" method="post" action="/addApplication"> <%--表单提交贫困申请所需信息--%>
    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-left: 20px">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">家庭年收入</label>
                <div class="layui-input-inline"><%--lay-verify="required|number"  required为必填项校验，number为自定义校验--%>
                    <input type="number" name="yearlyIncome" id="yearlyIncome" lay-verify="required" value="1000" autocomplete="off" class="layui-input" min="0" step="1000">
                    <label alt='请输入整数' placeholder='单位为￥'></label>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">家庭人口数</label>
                <div class="layui-input-inline">
                    <input type="number" name="populationSize" id="populationSize" lay-verify="required" value="5" autocomplete="off" class="layui-input" min="0" max="10">
                    <label alt='家庭成员总数' placeholder='家庭成员'></label>
                </div>
            </div>
            <div class="layui-inline" id="hide">
                <label class="layui-form-label">户籍地最低生活保障</label>
                <div class="layui-input-inline">
                    <input type="number" name="guarantee" id="guarantee" lay-verify="required" value="500" autocomplete="off" class="layui-input" min="0" step="100">
                    <label alt='请输入整数' placeholder='单位为￥'></label>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">负债金额</label>
                <div class="layui-input-inline">
                    <select name="liabilities">
                        <option value="">无</option>
                        <option value="2万以下" selected="">2万以下</option>
                        <option value="2-5万">2-5万</option>
                        <option value="5万以上">5万以上</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">学生饮食支出占比</label>
                <div class="layui-input-inline">
                    <select name="percentage">
                        <option value="">无</option>
                        <option value="1">小于70%</option>
                        <option value="2">70%-80%</option>
                        <option value="3">80%-90%</option>
                        <option value="4">90%以上</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">大病医疗支出</label>
                <div class="layui-input-inline">
                    <select name="medical">
                        <option value="">无</option>
                        <option value="2万以下">2万以下</option>
                        <option value="2-5万">2-5万</option>
                        <option value="5万以上">5万以上</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">经济困难证明</label>
                <div class="layui-input-inline">
                    <select name="prove">
                        <option value="">无</option>
                        <option value="特困证">特困证</option>
                        <option value="社会扶助证">社会扶助证</option>
                        <option value="最低生活保障证">最低生活保障证</option>
                        <option value="建档立卡">建档立卡</option>
                        <option value="孤残学生">孤残学生</option>
                        <option value="烈士子女">烈士子女</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">自然灾害</label>
            <div class="layui-input-block">
                <input type="text" name="naturalDisaster" lay-verify="title" value="无自然灾害" autocomplete="off" class="layui-input">
                <label alt='家庭遭受自然灾害情况' placeholder='时间/原因/损失'></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">突发意外</label>
            <div class="layui-input-block">
                <input type="text" name="unexpectedAccident" lay-verify="title" value="无" autocomplete="off" class="layui-input">
                <label alt='家庭遭受意外事件' placeholder='时间/原因/损失'></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">成员情况</label>
            <div class="layui-input-block">
                <input type="text" name="membershipSituation" lay-verify="title" placeholder="身体健康" autocomplete="off" class="layui-input">
                <label alt='家庭成员身体状况' placeholder='因残疾/患病/年迈而劳动力弱等原因'></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">失业情况</label>
            <div class="layui-input-block">
                <input type="text" name="unemploymentSituation" lay-verify="title" placeholder="1人失业" autocomplete="off" class="layui-input">
                <label alt='家庭成员失业状况' placeholder='何时失业'></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">获资助情况</label>
            <div class="layui-input-block">
                <input type="number" name="fundedSituation" lay-verify="title" placeholder="填写当前学年所获奖励、补助等补贴金额总数" autocomplete="off" class="layui-input">
                <label alt='入学以来接受过的资助情况' placeholder='包含奖学金、助学金'></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">其他情况</label>
            <div class="layui-input-block">
                <input type="text" name="otherSituation" lay-verify="title" placeholder="身患绝症，无钱医治" autocomplete="off" class="layui-input">
            </div>
        </div>
    </blockquote>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;margin-left: 20px">
        <legend>家庭联系方式</legend>
    </fieldset>
    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 30px;margin-left: 20px">
        <div class="layui-form-item">
            <label class="layui-form-label">家庭地址</label>
            <div class="layui-input-block">
                <input type="text" name="address" lay-verify="required|title" value="陕西西安" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">通讯地址</label>
            <div class="layui-input-block">
                <input type="text" name="postalAddress" lay-verify="required|title" value="陕西西安" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">邮政编码</label>
                <div class="layui-input-inline">
                    <input type="number" name="postalCode" lay-verify="required|postalCode" value="710124" placeholder="请输入6位邮政编码" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">联系人1</label>
                <div class="layui-input-inline">
                    <input type="text" name="addressee" lay-verify="required|title" value="张三" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">联系人1电话</label>
                <div class="layui-input-inline">
                    <input type="tel" name="contactNumber" lay-verify="required|phone" value="17789194920"placeholder="请输入11位手机号" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">联系人2</label>
                <div class="layui-input-inline">
                    <input type="text" name="emergencyContact" lay-verify="required|title" value="李四" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">联系人2电话</label>
                <div class="layui-input-inline">
                    <input type="tel" name="emergencyContactNumber" lay-verify="required|phone" value="17789194920" placeholder="请输入11位手机号"autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
    </blockquote>
    <fieldset class="layui-elem-field" style="margin-top: 20px;margin-left: 20px;">
        <legend>申请记录</legend>
        <table class="layui-table" lay-data="{url:'/getStudentApplicationList?pageStart=1&pageSize=10', id:'test2'}" lay-filter="test2">
            <thead>
            <tr style="width: auto" bgcolor="#008b8b">
                <th lay-data="{field:'schoolYear', width:100, minWidth: 100,align: 'center'}">学年度</th>
                <th lay-data="{field:'povertyLevel', width:150,align: 'center'}">家庭经济困难等级</th>
                <th lay-data="{field:'reasonsForApplication', minWidth: 150, align: 'center'}">申请理由</th>
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
                    <option value="一般贫困" selected="">一般贫困</option>
                    <option value="一般贫困">特别贫困</option>
                </select>
            </div>
            <div class="layui-input-inline">
                <div class="layui-upload">
                    <button type="button" class="layui-btn layui-btn-primary" id="test4"><i class="layui-icon"></i>只允许zip压缩文件</button>
                </div>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">认定理由</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容" id="reasonsForApplication" name="reasonsForApplication" lay-verify="required|reasons"  class="layui-textarea"></textarea>
            </div>
        </div>
    </fieldset>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form:form>
<td id="time" style="color: #777; padding-left: 10px;"></td>
<script src="/static/nprogress/nprogress.js"></script>
<script src="/static/layui/layui.js" charset="utf-8"></script>
<script src="/static/plugins/jquery.1.12.4.min.js"></script>
<script>
    NProgress.start();
    window.onload = function () {
        NProgress.done();
    }
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
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
            ,postalCode:[
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
    });
</script>
<script>
    layui.use('upload', function(){
        var $ = layui.jquery
            ,upload = layui.upload;
        upload.render({ //允许上传的文件后缀
            elem: '#test4'
            ,url: '/upload'
            ,accept: 'file' //普通文件
            ,size:20480
            ,exts: 'zip' //只允许上传压缩文件
            ,done: function(res){
                layer.msg(res.success)
            }
        });
    });
</script>
<script>
    var header = $("meta[name='_csrf_header']").attr("content");
    var token =$("meta[name='_csrf']").attr("content");
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#test'
            ,url:'/getUserFamily'
            ,height:232
            ,width:980
            ,title:'家庭成员表'
            ,toolbar: '#toolbarDemo'
            ,id: 'family'
            ,cols: [[
                {type:'numbers'}
                ,{field:'userName', width:90,align:'center',title:'姓名'}
                ,{field:'userAge', width:80, align:'center', title: '年龄'}
                ,{field:'occupation', width:80, align:'center', title: '职业'}
                ,{field:'relationship', width:120, align:'center', title: '与学生关系'}
                ,{field:'health', width:160, align:'center',title:'健康状况'}
                ,{field:'annualIncome', width:80,align:'center', title:'年收入'}
                ,{field:'workUnit', width:200, align:'center',title:'工作（学校）单位'}
                ,{field:'phoneNumber', width:120, title: '联系电话'}
                ,{fixed: 'right', width:100, align:'center', toolbar: '#barDemo'}
            ]]
        });
        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            switch(obj.event){
                case 'addRole'://批量删除用户的操作
                    layer.open({
                        title: '添加家庭成员',
                        type: 2,
                        maxmin: true,
                        shade: 0.5,
                        anim: 4,
                        area: ['50%', '80%'],
                        offset: [ //为了演示，随机坐标
                            100,280],
                        content: '/addfamily',
                        zIndex: layer.zIndex,
                        // skin: 'layui-layer-molv',
                        end: function () {
                            $(".layui-laypage-btn")[0].click();
                        }
                    });
                    table.reload('family', {
                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                    });
                    break;
            };
        });
        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                //示范一个公告层
                layer.open({
                    type: 1
                    ,title: '要删除吗？' //不显示标题栏
                    ,closeBtn: false
                    ,area: '300px;'
                    ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                    ,btn: ['确认删除', '算了吧']
                    ,yes: function (index, layero){
                        $.ajax({
                            url: "/deletefamily",
                            type: "POST",
                            beforeSend : function(xhr) {
                                xhr.setRequestHeader(header, token);
                            },
                            data: data.id,//
                            contentType: 'application/json',
                            success: function (res) {//回调函数
                            }
                        });
                        obj.del()
                        layer.close(index);
                    }
                    ,btnAlign: 'c'
                    ,moveType: 1 //拖拽模式，0或者1
                    ,offset: [200,400]
                    ,success: function(layero){
                    }
                });
            }
        });
        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
</body>
</html>
