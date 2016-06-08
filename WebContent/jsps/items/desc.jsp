<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>商品详细</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>

<link rel="stylesheet" type="text/css"
	href="<c:url value='/jsps/css/item/desc.css'/>">
<script src="<c:url value='/jsps/js/item/desc.js'/>"></script>
</head>

<body>
	<div class="divItemName">${item.item_name }</div>
	<div>
		<img align="top" src="<c:url value='/${item.item_pic }'/>"
			class="img_image_w" />
		<div class="divItemDesc">
			<ul>
				<li>商品编号：${item.item_id }</li>
				<li>价格：<span class="price_n">&yen;${item.item_price }</span></li>
			</ul>
			<hr class="hr1" />
			
			<table>
			<tr>
			    <td colspan="2"><h3>商品描述</h3></td>
			</tr>
				<tr>
					<td colspan="3"> ${item.item_descn}</td>
				</tr>
				<tr>
					<td colspan="3"></td>
				</tr>
<!-- 				<tr> -->
<%-- 					<td colspan="3">生产日期：${item.item_gdate}</td> --%>
<!-- 				</tr> -->
				<tr>
					<td width="180"></td>
					<td></td>
					<td></td>
				</tr>
			</table>
			<div class="divForm">
				<form id="form1" action="<c:url value='/CartItemServlet'/>"
					method="post">
					<input type="hidden" name="method" value="add" /> <input
						type="hidden" name="bid" value="${item.item_id}" /> 我要买：<input
						id="cnt" style="width: 40px; text-align: center;" type="text"
						name="quantity" value="1" />件
				</form>
				<a id="btn" href="javascript:$('#form1').submit();"></a>
			</div>
		</div>
	</div>
</body>
</html>