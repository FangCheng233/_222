<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: FangCheng
  Date: 2019/4/3
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>贫困生信息管理系统</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header"  content="${_csrf.headerName}"/>
    <link href="/static/plugins/fullPage/jquery.fullPage.css" rel="stylesheet"/>
    <link href="/static/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/static/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet"/>
    <link href="/static/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
    <link href="/static/plugins/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css" rel="stylesheet"/>
    <link href="/static/css/admin.css" rel="stylesheet"/>
    <link href="/static/layui/css/layui.css" rel="stylesheet"/>
    <link href="/static/css/okadmin.css" rel="stylesheet"/>
    <link href="/static/font/iconfont.css" rel="stylesheet"/>
    <link href="/static/js/ouibounce.min.css" rel="stylesheet"/>
<style>
        /** skins **/
        #student #header {background: #29A176;}
        #student .content_tab{background: #29A176;}
        #student .s-profile>a{background: url(/static/images/zheng-upms.png) left top no-repeat;}

        #counsellor #header {background: #0B8DE5;}
        #counsellor .content_tab{background: #0B8DE5;}
        #counsellor .s-profile>a{background: url(/static/images/zheng-oss.png) left top no-repeat;}

        #college #header {background: #455EC5;}
        #college .content_tab{background: #455EC5;}
        #college .s-profile>a{background: url(/static/images/zheng-cms.png) left top no-repeat;}

        #admin #header {background: #6539B4;}
        #admin .content_tab{background: #6539B4;}
        #admin .s-profile>a{background: url(/static/images/zheng-ucenter.png) left top no-repeat;}
    </style>
</head>
<body>
<header id="header">
    <ul id="menu">
        <li id="guide" class="line-trigger">
            <div class="line-wrap">
                <div class="line top"></div>
                <div class="line center"></div>
                <div class="line bottom"></div>
            </div>
        </li>
        <li id="logo" class="hidden-xs">
            <a href="main.jsp">
                <img src="/static/images/logo.png"/>
            </a>
            <span id="system_title">信息管理系统</span>
        </li>
        <li class="pull-right">
            <ul class="hi-menu">
                <li class="dropdown">
                    <a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;">
                        <i class="him-icon zmdi zmdi-more-vert"></i>
                    </a>
                    <ul class="dropdown-menu dm-icon pull-right">
                        <li class="hidden-xs">
                            <a class="waves-effect" data-ma-action="fullscreen" href="javascript:fullPage();"><i class="zmdi zmdi-fullscreen"></i> 全屏模式</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </li>
    </ul>
</header>
<section id="main">
    <!-- 左侧导航区 -->
    <aside id="sidebar">
        <!-- 个人资料区 -->
        <div class="s-profile">
            <a class="waves-effect waves-light" href="javascript:;">
                <div class="sp-pic">
                    <img src="/static/images/avatar.jpg"/>
                </div>
                <div class="sp-info">
                    ${user.userName},你好！
                    <i class="zmdi zmdi-caret-down"></i>
                </div>
            </a>
            <ul class="main-menu">
                <li>
                    <a class="waves-effect" href="javascript:;" onclick="userInfo()"><i class="zmdi zmdi-account"></i> 个人资料</a>
                </li>
                <li>
                    <a class="waves-effect" href="javascript:;" onclick="alterpwd()"><i class="zmdi zmdi-lock-outline" lay-event="test"></i> 修改密码</a>
                </li>
                <li>
                    <a class="waves-effect" href="/logout" onclick="logout()"><i class="zmdi zmdi-run"></i> 退出登录</a>
                </li>
            </ul>
        </div>
        <!-- /个人资料区 -->
        <!-- 菜单区 -->
        <ul class="main-menu">
            <li>
                <a class="waves-effect" href="/test"><i class="zmdi zmdi-home"></i> 首页</a>
            </li>
            <%-- 学工部管理员菜单--%>
            <li class="sub-menu system_menus system_1 0">
                <a class="waves-effect" ><i class="zmdi zmdi-menu"></i> 申请审批管理</a><%--href="javascript:;"--%>
                <ul>
                    <li><a class="waves-effect" href="javascript:;" onclick="getAllApplicationList()">待处理审批</a></li>
                    <li><a class="waves-effect" href="javascript:;" onclick="getAllApplicationListOld()">历史审批记录</a></li>
                </ul>
            </li>
            <li class="sub-menu system_menus system_1 1">
                <a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-accounts"></i> 用户管理</a>
                <ul>
                    <li><a class="waves-effect" href="javascript:;" onclick="getAdminList()">学工部人员管理</a></li>
                    <li><a class="waves-effect" href="javascript:;" onclick="getCollegeList()">院系办公室人员管理</a></li>
                    <li><a class="waves-effect" href="javascript:;" onclick="getCounsellorList()">辅导员信息管理</a></li>
                    <li><a class="waves-effect" href="javascript:;" onclick="getStudentList()">学生信息管理</a></li>
                </ul>
            </li>
            <li class="sub-menu system_menus system_1 2">
                <a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-more"></i> 数据管理</a>
                <ul>
<%--                    <li><a class="waves-effect" href="javascript:;" onclick="getAdminList()">数据导出</a></li>--%>
                    <li><a class="waves-effect" href="javascript:;" onclick="getReference()">贫困认定数据管理</a></li>
                    <li><a class="waves-effect" href="javascript:;" onclick="getPoorerAreas()">贫困地区信息维护</a></li>
                </ul>
            </li>
            <%-- 院系管理员菜单--%>
            <li class="sub-menu system_menus system_2 1" style="display:none;">
                <a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-menu"></i> 申请审批管理</a>
                <ul>
                    <li><a class="waves-effect" href="javascript:;" onclick="getCollegeApplicationList()">待处理审批</a></li>
                    <%--<li><a class="waves-effect" id="test" href="javascript:Tab.addTab('历史申请', '/applicationRecord?params=getCollegeApplicationList');">历史审批记录</a></li>--%>
                </ul>
            </li>
            <%-- 辅导员管理菜单--%>
            <li class="sub-menu system_menus system_3 1" style="display:none;">
                <a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-menu"></i> 申请审批管理</a>
                <ul>
                    <li><a class="waves-effect" href="javascript:;" onclick="getCounsellorApplicationList()">待处理审批</a></li>
                    <%--<li><a class="waves-effect" href="javascript:Tab.addTab('历史审批记录', '/applicationRecord?params=getCounsellorApplicationList');">历史审批记录</a></li>--%>
                </ul>
            </li>
            <%-- 学生端菜单--%>
            <li class="sub-menu system_menus system_4 1" style="display:none;">
                <a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-menu"></i> 贫困申请</a>
                <ul>
                    <li><a class="waves-effect" href="javascript:;" onclick="addapplication()">提交申请</a></li>
                    <%--<li><a class="waves-effect" href="javascript:Tab.addTab('历史记录', '/applicationRecord?params=getStudentApplicationList');">历史记录</a></li>--%>
                </ul>
            </li>
            <li>
                <div class="upms-version">
                    &copy; FANG-CHENG V1.0.0
                </div>
            </li>
        </ul>
        <!-- /菜单区 -->
    </aside>
    <!-- /左侧导航区 -->
    <section id="content">
        <div class="content_tab">
            <div class="tab_left">
                <a class="waves-effect waves-light" href="javascript:;"><i class="zmdi zmdi-chevron-left"></i></a>
            </div>
            <div class="tab_right">
                <a class="waves-effect waves-light" href="javascript:;"><i class="zmdi zmdi-chevron-right"></i></a>
            </div>
            <ul id="tabs" class="tabs">
                <li id="tab_home" data-index="home" data-closeable="false" class="cur">
                    <span class="waves-effect waves-light">首页</span>
                </li>
            </ul>
        </div>
        <div class="content_main">
            <div id="iframe_home" class="iframe cur">
                <sec:authorize access="hasRole('ADMIN') or hasRole('COUNSELLOR') or hasRole('COLLEGE')">
                <div>
                <fieldset class="layui-elem-field">
                <div class="layui-row layui-col-space10 layui-row-margin">
                <div class="layui-col-md2">
                <div class="layui-bg-green md2-sub1">
                <i class="iconfont icon-dianliyonghuzongshu"></i>
                </div>
                <div class="md2-sub2">
                <%--<span>${pending}</span>--%>
                    <span>1</span>
                <cite>待处理审批</cite>
                </div>
                </div>
                <div class="layui-col-md2">
                <div class="layui-bg-blue md2-sub1">
                <i class="iconfont icon-wenzhang2"></i>
                </div>
                <div class="md2-sub2">
                <span>${solved}</span>
                <cite>已处理审批</cite>
                </div>
                </div>
                <div class="layui-col-md2">
                <div class="layui-bg-black md2-sub1">
                <i class="iconfont icon-pinglun"></i>
                </div>
                <div class="md2-sub2">
                <span>${count}</span>
                <cite>审批总数</cite>
                </div>
                </div>
                </div>
                </fieldset>
                </div>
                </sec:authorize>
                <sec:authorize access="hasRole('STUDENT')">
                    <div class="layui-field-box" style="margin-right: 20px">
                        <table class="layui-hide" id="test" lay-filter="test" style="margin-left: 10px"></table>
                    </div>
                </sec:authorize>
            </div>
        </div>

    </section>

</section>

<footer id="footer"></footer>

<script src="/static/plugins/jquery.1.12.4.min.js"></script>
<script src="/static/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="/static/plugins/waves-0.7.5/waves.min.js"></script>
<script src="/static/plugins/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="/static/plugins/BootstrapMenu.min.js"></script>
<script src="/static/plugins/device.min.js"></script>
<script src="/static/plugins/fullPage/jquery.fullPage.min.js"></script>
<script src="/static/plugins/fullPage/jquery.jdirk.min.js"></script>
<script src="/static/plugins/jquery.cookie.js"></script>
<script src="/static/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
    var header = $("meta[name='_csrf_header']").attr("content");
    var token =$("meta[name='_csrf']").attr("content");
    if(window!=top){ //代表这是iframe页面 ->解决main主页面里刷新重新加载时tab中出现嵌套页面
        // window.top.location("/login");
        top.location.href = "/login";
    }
    function userInfo() {
        Tab.addTab('个人资料', '/userInfo');
    }
    function alterpwd() {
        Tab.addTab('重置密码','/alterpwd?userId=${user.userId}');
    }

    function logout() {
        var url = "/logout";
        var request = new XMLHttpRequest();
        request.open("GET", url);
    }
    function getAllApplicationList() {
        Tab.addTab('待处理审批', '/approval?params=getAllApplicationList');
    }
    function getAllApplicationListOld() {
        Tab.addTab('历史审批记录', '/applicationRecord?params=getAllApplicationListOld');
    }
    function getAdminList() {
        Tab.addTab('学工部人员管理', '/userList?params=getAdminList');
    }
    function getCollegeList() {
        Tab.addTab('院系办公室人员信息', '/userList?params=getCollegeList');
    }
    function getCounsellorList() {
        Tab.addTab('辅导员信息', '/userList?params=getCounsellorList');
    }
    function getStudentList() {
        Tab.addTab('学生信息', '/userList?params=getStudentList');
    }
    function getReference() {
        Tab.addTab('参考数据修改', '/getReference');
    }
    function getPoorerAreas() {
        Tab.addTab('贫困地区数据修改', '/getPoorerAreas');
    }
    function getCollegeApplicationList() {
        Tab.addTab('待处理审批', '/approval?params=getCollegeApplicationList');
    }
    function getCounsellorApplicationList() {
        Tab.addTab('待处理审批', '/approval?params=getCounsellorApplicationList');
    }
    var  t = ""
    function addapplication() {
        $.ajax({
            url: "/findApplication",
            type: "GET",
            beforeSend : function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            contentType: 'application/json',
            async : false,
            success:function (data) {
                var error = data.error;
                if(error){
                    layer.msg(data.error)
                    t=data.error
/*                    myobject.inc()*/
                    return false;
                }else {
                    Tab.addTab('提交申请', '/addapplication');
                }
            }
        });
    }
/*    var myobject={
        value:0,
        inc:function(){
            alert(t)
        }
    }*/

</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">查看详情</a>
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="look">查看审批过程</a>
</script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#test'
            ,url:'/getStudentApplication'
            ,page: true
            ,title:'审批进度数据表'
            ,id: 'testReload'
            ,width: 1550
            ,cols: [[
                {field:'applicationNumber',hide:true}
                ,{type:'numbers'}
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
                ,{field:'povertyLevel', title:'申请家庭经济困难等级', width:200,align:'center'}
                ,{field:'reasonsForApplication', title:'申请理由',event: 'setSign',style:'cursor: pointer;',width:300,align:'center'}
                ,{field:'userId', title:'学号', width:100,align:'center'}
                ,{field:'userName', title:'姓名', width:100,align:'center'}
                ,{field:'processNode', title: '上一节点', width:100,align:'center'}
                ,{field:'teacherName', title: '审批人', width:100,align:'center'}
                ,{field:'approvalStatus', title:'审批状态', width:100, sort:true, align:'center'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', minWidth:150,align:'center', fixed: 'right'}
            ]]
            ,request:{
                pageName: 'pageStart', //页码的参数名称，默认：page
                limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
        });
        //编辑选中行数据
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;
            if (layEvent === 'look') {
                layer.open({
                    title: '查看审批流程',
                    type: 2,
                    shade: false,
                    maxmin: true,
                    shade: 0.5,
                    anim: 4,
                    area: ['30%', '30%'],
                    content: '/getApprovalList?applicationNumber='+data.applicationNumber,
                    zIndex: layer.zIndex,
                    // skin: 'layui-layer-molv',
                    end: function () {
                        $(".layui-laypage-btn")[0].click();
                    }
                });
            } else if(obj.event === 'edit'){
                var data = obj.data;
                var applicationNumber = data.applicationNumber;
                var userId = data.userId;
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
        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $.ajax({
            url: "/verifyInfo",
            type: "GET",
            beforeSend : function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            contentType: 'application/json',
            async : false,
            success:function (data) {
                var error = JSON.stringify(data.error)
                if(error.length>0){
                    layer.open({
                        type: 1
                        ,title: false //不显示标题栏
                        ,closeBtn: false
                        ,area: '300px;'
                        ,shade: 0.3
                        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                        ,btn: ['我这就来', '残忍拒绝']
                        ,btnAlign: 'c'
                        ,moveType: 1 //拖拽模式，0或者1
                        ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">亲切提示！<br><br><br>您的密保信息未补充完整，<br>为了您的账户安全请前往填写</div>'
                        ,yes: function (index, layero){
                            Tab.addTab('个人资料', '/userInfo');
                            layer.close(index);
                        }
                    });
                }
            }
        });
    });

</script>
<%-- 对不同用户登陆成功后展示不同的界面--%>
<sec:authorize access="hasRole('ADMIN')">
    <script src="/static/js/admin.js"></script>
</sec:authorize>
<sec:authorize access="hasRole('COLLEGE')">
    <script src="/static/js/college.js"></script>
</sec:authorize>
<sec:authorize access="hasRole('COUNSELLOR')">
    <script src="/static/js/counsellor.js"></script>
</sec:authorize>
<sec:authorize access="hasRole('STUDENT')">
    <script src="/static/js/student.js"></script>
</sec:authorize>
<script src="/static/js/ouibounce.js"></script>
<script src="/static/js/md5.js" charset="utf-8"></script>
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
                // layer.close(index);
                //对密码进行加密处理
                value = hex_md5(value)
                //发送Ajax请求验证密码是否正确
                var sendData=[]
                sendData.push(value)
                $.ajax({
                    url: "/verify",
                    type: "POST",
                    beforeSend : function(xhr) {
                        xhr.setRequestHeader(header, token);
                    },
                    data: sendData,//参数，（注：你后台的方法参数不好传啊）
                    contentType: 'application/json',
                    async : true,
                    success:function (data) {
                            //执行重载
                    },
                    error:function (data) {
                    }
                });
            });
        };
        window.onblur=function()
        {
            //点击其他页面时登出操做 session失效

        };
    }
</script>
<body onload="load();">
</body>
</html>
