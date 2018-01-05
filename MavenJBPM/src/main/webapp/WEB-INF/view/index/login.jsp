<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="http://localhost:8086/res/css/style.css" />
<style>
.clearfix:after{
	display:block;
	content:'';
	clear:both;
}
#container{
	position:relative;
}
.tips{
	position:absolute; 
	top:40px;
	left:280px;
	color:red;
}
</style>
</head>
<body>
	<div id="container">
		<form action="/jbpm/login">
			<div class="clearfix">
				<div class="login">LOGIN</div>
			</div>
			<div class="clearfix">
				<div class="username-text">用户名:</div>
				<div class="password-text">密&nbsp;&nbsp;码:</div>
			</div>
			<c:if test="${not empty error}"><span class="tips">${error}</span></c:if>
			<div class="username-field">
				<input type="text" name="userName" value="" placeholder="请输入用户名" />
			</div>
			<div class="password-field">
				<input type="password" name="passwords" value="" placeholder="请输入密码" />
			</div>
			<input type="checkbox" name="remember-me" id="remember-me" /><label for="remember-me">记住我</label>
			<div class="forgot-usr-pwd">忘记 <a href="#">用户名</a> 或 <a href="#">密码</a>&nbsp;?</div>
			<input type="submit" name="submit" value="GO" />
		</form>
	</div>
</body>
</html>