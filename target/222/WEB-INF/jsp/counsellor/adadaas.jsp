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

</body>
</html>
