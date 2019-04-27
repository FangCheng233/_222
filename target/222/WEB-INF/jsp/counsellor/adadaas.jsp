<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
&lt;%&ndash;
  Created by IntelliJ IDEA.
  User: FangCheng
  Date: 2019/4/20
  Time: 19:55
  To change this template use File | Settings | File Templates.
&ndash;%&gt;
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
    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable" for="tableAuthors">Roles</label>&lt;%&ndash;authorType&ndash;%&gt;
            <div class="col-md-7">
                <form:select path="tableAuthors" items="${roles}" multiple="true" itemValue="authorId" itemLabel="authorType" class="form-control input-sm" />
                <div class="has-error">
                    <form:errors path="tableAuthors" class="help-inline"/>
                </div>
            </div>
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
</html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/layui/css/layui.css"  media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>上传多张图片</legend>
</fieldset>
<div class="layui-upload">
    <button type="button" class="layui-btn" id="test2">多图片上传</button>
    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px; width: 60%;height: 10%">
        预览图：
        <div class="layui-upload-list" id="demo2"></div>
    </blockquote>
</div>
<script src="/static/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use('upload', function(){
        var $ = layui.jquery
            ,upload = layui.upload;

        //多图片上传
        upload.render({
            elem: '#test2'
            ,url: '/upload/'
            ,multiple: true
            ,size:2048
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo2').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
                });
            }
            ,done: function(res){
                //上传完毕
            }
        });

        //设定文件大小限制
        upload.render({
            elem: '#test7'
            ,url: '/upload/'
            ,size: 60 //限制文件大小，单位 KB
            ,done: function(res){
                console.log(res)
            }
        });

    });
</script>

</body>
</html>