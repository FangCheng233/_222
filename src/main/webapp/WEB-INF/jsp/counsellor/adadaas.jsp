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
    <meta name="_csrf_header"  content="${_csrf.headerName}"/>
</head>
<body>
<form>
        秒杀一：<input  name="Id" type="number"><br>
    <div>
        <div>
            <input type="button" value="test" onclick="test()"/>
        </div>
    </div>
</form>
<form:form class="form-horizontal" role="form" method="post" action="/test/fixLeader" >
    秒杀一：<input class="td_input" name="id" type="number"><br>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10" >
            <input type="submit" value="test"  class="btn btn-default" />
        </div>
    </div>
</form:form>
<form:form class="form-horizontal" role="form" method="post" action="/test/testEntity" >
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
</form:form>
<script src="/static/plugins/jquery.1.12.4.min.js"></script>
<script type="text/javascript">
    function test(){
        var header = $("meta[name='_csrf_header']").attr("content");
        var token =$("meta[name='_csrf']").attr("content");
        var id={"id":"123"};
        $.ajax({
            url: '/test/fixLeader1',
            contentType: "text/html,charset=utf-8",
            beforeSend : function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            type: 'post',
            async: false,
            data:id,
            success: function (result) {
            }
        });
    }
</script>
</body>
</html>
