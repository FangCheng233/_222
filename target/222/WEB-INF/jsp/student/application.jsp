<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="forrm" uri="http://www.springframework.org/tags/form" %>
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
                <td>${user.nativePlace}</td>
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
                <td>1989-10-14</td>
                <td>人生似修行</td>
            </tr>
            <tr>
                <td>QQ：</td>
                <td></td>
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
    <legend>家庭信息</legend>
    <table class="layui-table" lay-data="{height:232, url:'/getUserFamily', method:'get',id:'test',toolbar: '#toolbarDemo'}" lay-filter="test">
        <thead>
        <tr>
            <th lay-data="{field:'userName', width:90,align:'center'}">姓名</th>
            <th lay-data="{field:'userAge', width:80, align:'center'}">年龄</th>
            <th lay-data="{field:'occupation', width:80, align:'center'}">职业</th>
            <th lay-data="{field:'relationship', width:120, align:'center'}">与学生关系</th>
            <th lay-data="{field:'health', width:160, align:'center'}">健康状况</th>
            <th lay-data="{field:'annualIncome', width:80,align:'center'}">年收入</th>
            <th lay-data="{field:'workUnit', width:200, align:'center'}">工作（学校）单位</th>
            <th lay-data="{field:'phoneNumber', width:120,  fixed: 'right'}">联系电话</th>
            <th lay-data="{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}"></th>
        </tr>
        </thead>
    </table>
    <script type="text/html" id="barDemo">
        <a href="javascript:;" title="删除" lay-event="del"><i class="layui-icon">&#xe640;</i></a>
    </script>
    <script type="text/html" id="toolbarDemo">
        <okToolbar>
            <button class="layui-btn" id="addRole">
                <i class="layui-icon">&#xe61f;</i>添加角色
            </button>
        </okToolbar>
    </script>
</fieldset>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;margin-left: 20px">
    <legend>影响家庭贫困的因素</legend>
</fieldset>
<form:form class="layui-form" method="post" action="/addapplication" accept-charset="UTF-8">
    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-left: 20px">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">家庭年收入</label>
                <div class="layui-input-inline">
                    <input type="number" name="yearlyIncome" id="yearlyIncome" lay-verify="required|number" value="1000" autocomplete="off" class="layui-input" required='' min="0" step="1000">
                    <label alt='请输入整数' placeholder='单位为￥'></label>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">家庭人口数</label>
                <div class="layui-input-inline">
                    <input type="number" name="populationSize" id="populationSize" lay-verify="required|number" value="5" autocomplete="off" class="layui-input" required='' min="0" max="10">
                    <label alt='家庭成员总数' placeholder='家庭成员'></label>
                </div>
            </div>
            <div class="layui-inline" id="hide">
                <label class="layui-form-label">人均年收入</label>
                <div class="layui-input-inline">
                    <input type="number" name="perCapitaIncome" id="perCapitaIncome" lay-verify="required|number" value="1000" autocomplete="off" class="layui-input" required='' min="0" step="1000">
                    <label alt='请输入整数' placeholder='单位为￥'></label>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
<%--            <div class="layui-inline">
                <label class="layui-form-label">负债情况</label>
                <div class="layui-input-inline" id="CheckBox">
                    <input type="checkbox" name="perCapitaIncome" lay-skin="switch" lay-text="有|无">
                </div>
            </div>--%>
            <div class="layui-inline">
                <label class="layui-form-label">负债金额</label>
                <div class="layui-input-inline">
                    <select name="liabilities">
                        <option value="">无</option>
                        <option value="1万以下" selected="">1万以下</option>
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
                <input type="text" name="naturalDisaster" lay-verify="required|title" value="无自然灾害" autocomplete="off" class="layui-input" required=''>
                <label alt='家庭遭受自然灾害情况' placeholder='时间/原因/损失'></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">突发意外</label>
            <div class="layui-input-block">
                <input type="text" name="unexpectedAccident" lay-verify="required|title" value="无" autocomplete="off" class="layui-input" required=''>
                <label alt='家庭遭受意外事件' placeholder='时间/原因/损失'></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">成员情况</label>
            <div class="layui-input-block">
                <input type="text" name="membershipSituation" lay-verify="required|title" value="身体健康" autocomplete="off" class="layui-input" required=''>
                <label alt='家庭成员身体状况' placeholder='因残疾/患病/年迈而劳动力弱等原因'></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">失业情况</label>
            <div class="layui-input-block">
                <input type="text" name="unemploymentSituation" lay-verify="required|title" value="1人失业" autocomplete="off" class="layui-input" required=''>
                <label alt='家庭成员失业状况' placeholder='何时失业'></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">获资助情况</label>
            <div class="layui-input-block">
                <input type="text" name="fundedSituation" lay-verify="required|title" value="国奖" autocomplete="off" class="layui-input" required=''>
                <label alt='入学以来接受过的资助情况' placeholder='包含奖学金、助学金'></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">其他情况</label>
            <div class="layui-input-block">
                <input type="text" name="otherSituation" lay-verify="required|title" value="身患绝症，无钱医治" autocomplete="off" class="layui-input">
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
                <input type="text" name="address" lay-verify="title" value="陕西西安" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">通讯地址</label>
            <div class="layui-input-block">
                <input type="text" name="postalAddress" lay-verify="title" value="陕西西安" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">邮政编码</label>
                <div class="layui-input-inline">
                    <input type="number" name="postNumber" lay-verify="required|postNumber" value="710124" placeholder="请输入6位邮政编码" autocomplete="off" class="layui-input">
                </div>
            </div>
<%--            <div class="layui-inline">
                <label class="layui-form-label">收信人</label>
                <div class="layui-input-inline">
                    <input type="text" name="email" lay-verify="required|title" value="adadadsa" autocomplete="off" class="layui-input">
                </div>
            </div>--%>
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
                    <option value="">请选择</option>
                    <option value="一般贫困" selected="">一般贫困</option>
                    <option value="一般贫困">特别贫困</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">认定理由</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容" id="reasonsForApplication" name="reasonsForApplication" class="layui-textarea"></textarea>
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
<script src="/static/layui/layui.js" charset="utf-8"></script>
<script src="/static/nprogress/nprogress.js"></script>
<script>
    NProgress.start();
    window.onload = function () {
        NProgress.done();
    }
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
        //添加
        $("#addRole").click(function () {
            layer.open({
                title: '添加家庭成员',
                type: 2,
                shade: false,
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
        })

    });
</script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        //监听工具条
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
                                    type: "GET",
                                    data: {'id':data.id},//
                                    dataType: 'json',
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
/*                            var btn = layero.find('.layui-layer-btn');
                            btn.find('.layui-layer-btn0').attr({
                                url:s
                                ,target: '_blank'
                            });*/
                        }
                    });
            }
        });
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
