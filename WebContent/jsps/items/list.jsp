<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>商品列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/jsps/css/item/list.css'/>">
<script type="text/javascript"
	src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/jsps/js/item/list.js'/>"></script>
</head>

<body>
	<ul>
		<c:forEach items="${pb}" var="item">
			<li>
				<div class="inner">
					<a 
						href="<c:url value='/itemServlet?method=load&bid=${item.item_id }'/>"><img
						src="<c:url value='/${item.item_pic }'/>" border="0" /></a>
					<p>
						<a id="itemname" title="${item.item_name }"
							href="<c:url value='/itemServlet?method=load&bid=${item.item_id }'/>">${item.item_name }</a>
					</p>
					<p class="price">
						<span class="price_n">&yen;${item.item_price}</span>
					</p>
				</div>
			</li>
		</c:forEach>
	</ul>
</body>
</html>