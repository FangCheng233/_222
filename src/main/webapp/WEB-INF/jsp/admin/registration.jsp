<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>User Registration Form</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
 	<div class="generic-container">
		<%@include file="../authheader.jsp" %>

		<div class="well lead">User Registration Form</div>
	 	<form:form method="POST" modelAttribute="user" class="form-horizontal" accept-charset="UTF-8" onsubmit="document.characterSet='utf-8'">
			<%--<form:input type="hidden" path="userId" id="userId"/>--%>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="userId">User Id</label>
					<div class="col-md-7">
						<c:choose>
							<c:when test="${edit}">
								<form:input type="text" path="userId" id="userId" class="form-control input-sm" disabled="true"/>
							</c:when>
							<c:otherwise>
								<form:input type="text" path="userId" id="userId" class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="userId" class="help-inline"/>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="userName">User Name</label>
					<div class="col-md-7">
						<form:input type="text" path="userName" id="userName" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="userName" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="groupId">Group Id</label>
					<div class="col-md-7">
						<c:choose>
							<c:when test="${edit}">
								<form:input type="text" path="groupId" id="groupId" class="form-control input-sm" disabled="true"/>
							</c:when>
							<c:otherwise>
								<form:input type="text" path="groupId" id="groupId" class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="groupId" class="help-inline"/>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="password" ng-value="2">Password</label>
					<div class="col-md-7">
						<form:input type="password" path="password" id="password" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="password" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="userSex">性别</label>
					<div class="col-md-7">
						<form:input type="text" path="userSex" id="userSex" class="form-control input-sm" accept-charset="UTF-8"/>
						<div class="has-error">
							<form:errors path="userSex" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="userClass">班级</label>
					<div class="col-md-7">
						<form:input type="text" path="userClass" id="userClass" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="userClass" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="userMajor">专业</label>
					<div class="col-md-7">
						<form:input type="text" path="userMajor" id="userMajor" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="userMajor" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="userCollege">院系</label>
					<div class="col-md-7">
						<form:input type="text" path="userCollege" id="userCollege" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="userCollege" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="userGrade">年纪</label>
					<div class="col-md-7">
						<form:input type="text" path="userGrade" id="userGrade" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="userGrade" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="tableAuthors">Roles</label>
					<div class="col-md-7">
						<form:select path="tableAuthors" items="${roles}" multiple="true" itemValue="authorId" itemLabel="authorType" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="tableAuthors" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/login' />">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/login' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>