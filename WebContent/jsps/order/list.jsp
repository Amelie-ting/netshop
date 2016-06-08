<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>订单列表</title>

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
	href="<c:url value='/jsps/css/order/list.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/jsps/pager/pager.css'/>" />
<script type="text/javascript"
	src="<c:url value='/jsps/pager/pager.js'/>"></script>
<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>
<!-- 甭管有没有用，我先把angular引进来再说 -->
<script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<script type="text/javascript">
	/* var sum;
	$(".divClass").each(function(){
		sum+=$(this).find(".inputClass").val();
	})
	//document.getElementByName('testName').innerHTML=sum;
	document.getElementByName("text1").value = sum;
	//var sum；$（“.divClass”）.each（function（）｛sum+=$（this）.find（“.inputClass”）.val（）；｝）； */

	var numbers = document.getElementsByName("number");
	var targetDiv = document.getElementById("div");
	var sum = 0;
	for (var i = 0; i < numbers.length; i++) {
		sum += parseInt(numbers[i].value);
	}
	targetDiv.innerHTML = sum;
</script>

</head>

<body>
<c:choose>
  	<c:when test="${empty pb.beanList }">
  	<table width="95%" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center"><img align="top"
						src="<c:url value='/bodyimages/emptyorder.png'/>" /></td>
					
				</tr>
			</table>
  	</c:when>
  	<c:otherwise>
	<div class="divMain">
		<div class="divTitle">
			<span style="margin-left: 150px; margin-right: 280px;">商品信息</span> <span
				style="margin-left: 40px; margin-right: 38px;">金额</span> <span
				style="margin-left: 50px; margin-right: 40px;">订单状态</span> <span
				style="margin-left: 50px; margin-right: 50px;">操作</span>
		</div>
		<br />

		<table align="center" border="0" width="100%" cellpadding="0"
			cellspacing="0">

			<c:forEach items="${pb.beanList}" var="order">
				<div class="divClass">
					<tr class="tt">
						<td width="320px">订单号：<a
							href="<c:url value='/OrderServlet?method=load&oid=${order.oid }'/>">${order.oid }</a></td>
						<td width="200px">下单时间：${order.odate }</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr style="padding-top: 10px; padding-bottom: 10px;">
						<td colspan="2"><c:forEach items="${order.orderItemList }"
								var="orderItem">
								<a class="link2"
									href="<c:url value='/itemServlet?method=load&bid=${orderItem.item.item_id }'/>">
									<img border="0" width="100" style="margin-right: 40px;"
									src="<c:url value='/${orderItem.item.item_pic}'/>" />
									<h2 style="margin-left: 30px;">${orderItem.item.item_name}</h2>
								</a>


							</c:forEach></td>
						<td width="115px"><span class="price_t">&yen;${order.total }</span>


						</td>
						<td width="142px"><c:choose>
								<c:when test="${order.status eq 1 }">(等待付款)</c:when>
								<c:when test="${order.status eq 2 }">(准备发货)</c:when>
								<c:when test="${order.status eq 3 }">(等待确认)</c:when>
								<c:when test="${order.status eq 4 }">(交易成功)</c:when>
								<c:when test="${order.status eq 5 }">(已取消)</c:when>
							</c:choose></td>
						<td><a
							href="<c:url value='/OrderServlet?method=load_aid&oid=${order.oid}'/>">查看</a><br />
							<c:if test="${order.status eq 1 }">
								<a
									href="<c:url value='/OrderServlet?method=paymentPre&oid=${order.oid}'/>">支付</a>
								<br />
								<a
									href="<c:url value='/OrderServlet?method=load&oid=${order.oid }&btn=cancel'/>">取消</a>
								<br />
							</c:if> <c:if test="${order.status eq 3 }">
								<a
									href="<c:url value='/OrderServlet?method=load&oid=${order.oid}&btn=confirm'/>">确认收货</a>
								<br />
							</c:if></td>
					</tr>
				</div>


			</c:forEach>
		</table>
		<br />
		<%@include file="/jsps/pager/pager.jsp"%>
	</div>
</c:otherwise>
  </c:choose>
</body>
</html>
