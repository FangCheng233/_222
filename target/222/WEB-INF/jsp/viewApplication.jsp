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
    <meta name="_csrf_header"  content="${_csrf.headerName}"/>
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
    <table class="layui-table" lay-data="{height:232, url:'/viewUserFamily?userId=${user.userId}', method:'get',id:'test'}" lay-filter="test">
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
        </tr>
        </thead>
    </table>
</fieldset>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;margin-left: 20px">
    <legend>影响家庭贫困的因素</legend>
</fieldset>
<form class="layui-form" role="form">
    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-left: 20px">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">家庭年收入</label>
                <div class="layui-input-inline"><%--lay-verify="required|number"  required为必填项校验，number为自定义校验--%>
                    <input type="number" name="yearlyIncome" id="yearlyIncome" lay-verify="required" value="1000" autocomplete="off" class="layui-input" min="0" step="1000" disabled="disabled">
                    <label alt='请输入整数' placeholder='单位为￥'></label>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">家庭人口数</label>
                <div class="layui-input-inline">
                    <input type="number" name="populationSize" id="populationSize" lay-verify="required" value="5" autocomplete="off" class="layui-input" min="0" max="10" disabled="disabled">
                    <label alt='家庭成员总数' placeholder='家庭成员'></label>
                </div>
            </div>
            <div class="layui-inline" id="hide">
                <label class="layui-form-label">人均年收入</label>
                <div class="layui-input-inline">
                    <input type="number" name="perCapitaIncome" id="perCapitaIncome" lay-verify="required" value="1000" autocomplete="off" class="layui-input" min="0" step="1000" disabled="disabled">
                    <label alt='请输入整数' placeholder='单位为￥'></label>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">负债金额</label>
                <div class="layui-input-inline">
                    <select name="liabilities" disabled="disabled">
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
                <input type="text" name="naturalDisaster" value="${application.naturalDisaster}" autocomplete="off" class="layui-input" disabled="disabled">
                <label alt='家庭遭受自然灾害情况' placeholder='时间/原因/损失'></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">突发意外</label>
            <div class="layui-input-block">
                <input type="text" name="unexpectedAccident" value="${application.unexpectedAccident}" autocomplete="off" class="layui-input" disabled="disabled">
                <label alt='家庭遭受意外事件' placeholder='时间/原因/损失'></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">成员情况</label>
            <div class="layui-input-block">
                <input type="text" name="membershipSituation" value="${application.membershipSituation}" autocomplete="off" class="layui-input" disabled="disabled">
                <label alt='家庭成员身体状况' placeholder='因残疾/患病/年迈而劳动力弱等原因'></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">失业情况</label>
            <div class="layui-input-block">
                <input type="text" name="unemploymentSituation"value="${application.unemploymentSituation}" autocomplete="off" class="layui-input" disabled="disabled">
                <label alt='家庭成员失业状况' placeholder='何时失业'></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">获资助情况</label>
            <div class="layui-input-block">
                <input type="text" name="fundedSituation" value="${application.fundedSituation}" autocomplete="off" class="layui-input" disabled="disabled">
                <label alt='入学以来接受过的资助情况' placeholder='包含奖学金、助学金'></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">其他情况</label>
            <div class="layui-input-block">
                <input type="text" name="otherSituation" value="${application.otherSituation}" autocomplete="off" class="layui-input" disabled="disabled">
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
                <input type="text" name="address" value="${application.address}" autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">通讯地址</label>
            <div class="layui-input-block">
                <input type="text" name="postalAddress" value="${application.postalAddress}" autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">邮政编码</label>
                <div class="layui-input-inline">
                    <input type="number" name="postalCode" value="${application.postalCode}" autocomplete="off" class="layui-input" disabled="disabled">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">联系人1</label>
                <div class="layui-input-inline">
                    <input type="text" name="addressee" value="${application.addressee}" autocomplete="off" class="layui-input" disabled="disabled">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">联系人1电话</label>
                <div class="layui-input-inline">
                    <input type="tel" name="contactNumber" value="${application.contactNumber}" autocomplete="off" class="layui-input" disabled="disabled">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">联系人2</label>
                <div class="layui-input-inline">
                    <input type="text" name="emergencyContact" value="${application.emergencyContact}" autocomplete="off" class="layui-input" disabled="disabled">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">联系人2电话</label>
                <div class="layui-input-inline">
                    <input type="tel" name="emergencyContactNumber" value="${application.emergencyContactNumber}" autocomplete="off" class="layui-input" disabled="disabled">
                </div>
            </div>
        </div>
    </blockquote>
    <fieldset class="layui-elem-field" style="margin-top: 20px;margin-left: 20px;">
        <legend>申请记录</legend>
        <table class="layui-table" lay-data="{url:'/getStudentApplicationList?pageStart=1&pageSize=10&param=${user.userId}', id:'test2'}" lay-filter="test2">
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
                <select name="povertyLevel" id="povertyLevel" disabled="disabled">
                    <option value="一般贫困" selected="">一般贫困</option>
                    <option value="一般贫困">特别贫困</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">认定理由</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容" id="reasonsForApplication" name="reasonsForApplication" class="layui-textarea" disabled="disabled" value="${application.reasonsForApplication}"></textarea>
            </div>
        </div>
    </fieldset>
    <input type="hidden" name="applicationNumber" value="${application.applicationNumber}">
    <sec:authorize access="hasRole('ADMIN') or hasRole('COLLEGE') or hasRole('COUNSELLOR')">
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="demo1">通过</button>
            </div>
        </div>
    </sec:authorize>

</form>
<script src="/static/layui/layui.js" charset="utf-8"></script>
<script src="/static/nprogress/nprogress.js"></script>
<script src="/static/plugins/jquery.1.12.4.min.js"></script>
<script>
    var header = $("meta[name='_csrf_header']").attr("content");
    var token =$("meta[name='_csrf']").attr("content");
    NProgress.start();
    window.onload = function () {
        NProgress.done();
    }
    layui.use('form', function(){
        //监听提交
        var form = layui.form
            ,layer = layui.layer;
        form.on('submit(demo1)', function(data){
            var sendData = [];
            var data = data.field
            sendData.push(data.applicationNumber)
            layer.msg('确认通过吗？', {
                title: ''
                ,time: 5000
                ,offset: [300,300]
                ,btn:['通过','放弃']
                ,btnAlign: 'c'
                ,yes: function(){
                    $.ajax({
                        url: "/alterApplicationPass",
                        type: "POST",
                        beforeSend : function(xhr) {
                            xhr.setRequestHeader(header, token);
                        },
                        data: JSON.stringify(sendData),//
                        dataType: 'json',
                        contentType: 'application/json',
                        success: function (data) {//回调函数
                            layer.msg(data.success,{
                                time:2000
                            })
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            table.reload('testReload', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
                        },
                        error:function (data) {

                        }
                    });
                    layer.close(index);
                }
            })
            return false;
        });
        //下载按钮监听

        //监听工具条
        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
</body>
</html>
