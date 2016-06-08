<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>top</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
	
		background: #FF3030;
		margin: 0px;
		color: #ffffff;
	}
	a {
		text-decoration:none;
		color: #ffffff;
		font-weight: 900;
	} 
	a:hover {
		text-decoration: underline;
		color: #ffffff;
		font-weight: 900;
	}
</style>
  </head>
<!-- top界面，放置商城标题，注册登陆界面，根据session来显示用户信息 -->
<body>
		<h1 style="text-align: center;">网上购物商城</h1>
		
		<!-- 通过session来判断，显示不同的登陆状态 -->
	<c:choose>
		<c:when test="${empty sessionScope.usersession }">
			  <a href="<c:url value='/jsps/user/login.jsp'/>" target="_parent">登录</a> |&nbsp; 
			  <a href="<c:url value='/jsps/user/regist.jsp'/>" target="_parent">注册</a>	
		</c:when>
		<c:otherwise>
			       账号：${sessionScope.usersession.u_name }&nbsp;&nbsp;|&nbsp;&nbsp;
			  <a href="<c:url value='/CartItemServlet?method=myCart'/>" target="body">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			  <a href="<c:url value='/OrderServlet?method=myOrders'/>" target="body">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			  <a href="<c:url value='/jsps/user/pwd.jsp'/>" target="_parent">修改密码</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			  <a href="<c:url value='/AddressServlet?method=loadaddress'/>" target="body">管理收货地址</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			  <a href="<c:url value='/UserServlet?method=quit'/>" target="_parent">退出</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		</c:otherwise>
	</c:choose>
</body>
</html>