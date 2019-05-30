<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: FangCheng
  Date: 2019/4/16
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" pageEncoding="UTF-8">
    <title>layui</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header"  content="${_csrf.headerName}"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;margin-left: 20px;">
    <legend>条件查询</legend>
    <div class="layui-form layui-form-pane">
        <div class="demoTable">
            <div class="layui-inline">
                <label class="layui-form-label">&nbsp;学年</label>
                <div class="layui-input-inline">
                    <select name="schoolYear" lay-filter="schoolYear" id="schoolYear">
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <input class="layui-input" name="select" placeholder="学号/姓名/班级/专业/学院" id="demoReload" autocomplete="off">
            </div>
            <button class="layui-btn" data-type="reload">搜索</button>
        </div>
        <table class="layui-hide" id="test" lay-filter="test" style="margin-left: 10px"></table>
    </div>
</fieldset>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
</script>
<script src="/static/layui/layui.js" charset="utf-8"></script>
<script src="/static/plugins/jquery.1.12.4.min.js"></script>
<script type="text/html" id="systemAudit">
    {{#  if(d.systemAudit === '特别贫困'){ }}
    <span style="color: #F581B1;">{{ d.systemAudit}}</span>
    {{#  } else { }}
    {{ d.systemAudit }}
    {{#  } }}
</script>
<script>
    var header = $("meta[name='_csrf_header']").attr("content");
    var token =$("meta[name='_csrf']").attr("content");
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#test'
            <%--,url:'/${params}'--%>
            ,url:'${params}'
            ,page: true
            ,title:'用户数据表'
            /*            ,totalRow: true*/
            ,id: 'testReload'
            ,width: 1630
            ,cols: [[
                {field:'applicationNumber',hide:true}
                ,{type:'numbers'}
                ,{type: 'checkbox'}
                <sec:authorize access="hasRole('ADMIN')">
                ,{field:'userCollege', title:'院系', width:200, unresize: true, sort: true,align:'center'}
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN') or hasRole('COLLEGE') or hasRole('COUNSELLOR')">
                ,{field:'userMajor', title:'专业', width:200,align:'center',sort:true}
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN') or hasRole('COLLEGE') or hasRole('COUNSELLOR')">
                ,{field:'userClass', title:'班级', width:100,align:'center'}
                </sec:authorize>
                ,{field:'userGrade', title: '年级', width:100,align:'center'}
                ,{field:'schoolYear', title: '学年', width:100,align:'center'}
                ,{field:'povertyLevel', title:'申请家庭经济困难等级', width:200,align:'center'}
                ,{field:'reasonsForApplication', title:'申请理由',event: 'setSign',style:'cursor: pointer;',width:200,align:'center'}
                ,{field:'systemAudit', title:'系统评定结果', width:200,align:'center',templet: '#systemAudit'}
                ,{field:'userId', title:'学号', width:100,align:'center'}
                ,{field:'userName', title:'姓名', width:100,align:'center',templet: '#userName'}
                ,{field:'approvalStatus', title:'审批状态', width:100, sort:true, align:'center'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', minWidth:90,align:'center'}
            ]]
            ,request:{
                pageName: 'pageStart', //页码的参数名称，默认：page
                limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
        });
        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            var applicationNumber = data.applicationNumber;
            var userId = data.userId;
            if(obj.event === 'detail'){
                //打开申请界面
                layer.open({
                    title: '查看详细信息',
                    type: 2,
                    shade: false,
                    maxmin: true,
                    shade: 0.5,
                    anim: 4,
                    area: ['90%', '90%'],
                    content: '/viewapplication?userId='+userId+'&applicationNumber='+applicationNumber,
                    zIndex: layer.zIndex,
                    // skin: 'layui-layer-molv',
                    end: function () {
                        $(".layui-laypage-btn")[0].click();
                    }
                });
            }
        });
        var $ = layui.$, active = {
            reload: function(){
                //执行重载
                var demoReload = $('#demoReload');
                var schoolYear = $('#schoolYear');
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        select: demoReload.val(),
                        schoolYear:schoolYear.val()
                    }
                });
            }
        };
        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
<script>
    var schoolYears = [${yearly}+"-"+ (${yearly}+1), (${yearly}-1)+"-"+(${yearly}), (${yearly}-2)+"-"+(${yearly}-1), (${yearly}-3)+"-"+(${yearly}-2)];
    var option = "";
    for(var i = 0; i < schoolYears.length; i++) {
        option += '<option value="' + (${yearly}-i)+'-'+(${yearly}-i+1)+ '">' + schoolYears[i] + '</option>';
    }
    $(option).appendTo("#schoolYear");
</script>
<script src="/static/select/js/select.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>