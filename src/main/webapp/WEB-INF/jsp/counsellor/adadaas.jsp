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
</head>
<body>
<form class="form-horizontal" role="form" method="post" action="/killApple" >
        秒杀一：<input class="td_input" name="classId" type="number"><br>
        秒杀二：<input class="td_input" name="className" type="number"><br>
        秒杀三：<input class="td_input" name="teacherId" type="number"><br>
        秒杀四：<input class="td_input" name="teacherName" type="number"><br>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10" >
            <input type="submit" class="btn btn-default" />
        </div>
    </div>
</form>
<div class="form-group">
    <div class="col-sm-offset-2 col-sm-10" >
        <input type="button"  value="ajaxtest" onclick="ajaxtest()" class="btn btn-default" />
    </div>
</div>
<script src="/static/plugins/jquery.1.12.4.min.js"></script>
<script type="text/javascript">
    function ajaxtest() {
        var data = {data:{name:'pxxx',
                paramData:[{dataSource:'a1',table:'t1',field:'f1','r':[{name1:"01",p:''},{name2:"02",p:'10,100'},{name2:"01",p:''}]},
                    {dataSource:'a2',table:'t2',field:'f2','r':[{name1:"01",p:''},{name2:"02",p:'10,100'},{name3:"01",p:''}]}]
            }};
        $.ajax({
            type:"POST",
            async:false,
            dataType:'json',
            contentType : "application/json",
            url:"/addPxx",
            data: JSON.stringify(data),
            success:function (data) {
            }
        });
/*        $.ajax({
            url:'/test001',
            data:{rel:13},
            type:'post',
            dataType:"jsonp",
            jsonp:"callback",
            jsonpCallback:"success_jsonp",
            timeout:3000,
            dataFilter:function(json){
                console.log("jsonp.filter:"+json);
                return json;
            },
            success:function(json,textStatus){
                console.log("jsonp.success:"+json.name);
            },
            error:function(XMLHttpRequest,textStatus,errorThrown){
                console.log("jsonp.error:"+textStatus);
            }
        });*/
    }


</script>





</body>
</html>
