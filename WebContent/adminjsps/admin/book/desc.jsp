<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>book_desc.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/book/desc.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/jquery/jquery.datepick.css'/>">
<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>

<script type="text/javascript" src="<c:url value='/adminjsps/admin/js/book/desc.js'/>"></script>

<script type="text/javascript">

$(function() {
	$("#box").attr("checked", false);
	$("#formDiv").css("display", "none");
	$("#show").css("display", "");	
	
	// 操作和显示切换
	$("#box").click(function() {
		if($(this).attr("checked")) {
			$("#show").css("display", "none");
			$("#formDiv").css("display", "");
		} else {
			$("#formDiv").css("display", "none");
			$("#show").css("display", "");		
		}
	});
});

function loadChildren() {
	/*
	1. 获取pid
	2. 发出异步请求，功能之：
	  3. 得到一个数组
	  4. 获取cid元素(<select>)，把内部的<option>全部删除
	  5. 添加一个头（<option>请选择2级分类</option>）
	  6. 循环数组，把数组中每个对象转换成一个<option>添加到cid中
	*/
	// 1. 获取pid
	var ca_pid = $("#ca_pid").val();
	  
	// 2. 发送异步请求
	$.ajax({
		async:true,
		cache:false,
		
		url:"/netstore/adminItemsServlet",
		data:{method:"ajaxFindChildren", ca_pid:ca_pid},
		
		type:"POST",
		dataType:"json",
		success:function(arr) {
			
			// 3. 得到cid，删除它的内容
			$("#item_caid").empty();//删除元素的子元素
			$("#item_caid").append($("<option>====请选择2级分类====</option>"));//4.添加头
			// 5. 循环遍历数组，把每个对象转换成<option>添加到cid中
			for(var i = 0; i < arr.length; i++) {
				var option = $("<option>").val(arr[i].ca_id).text(arr[i].ca_name);
				$("#item_caid").append(option);
			}
		}
	});
}

/*
 * 点击编辑按钮时执行本函数
 */
function editForm() {
	$("#method").val("edit");
	$("#form").submit();
}
/*
 * 点击删除按钮时执行本函数
 */
 function deleteForm() {
	$("#method").val("delete");
	$("#form").submit();	
}

</script>
  </head>
  
  <body>
    <input type="checkbox" id="box"><label for="box">编辑或删除</label>
    <br/>
    <br/>
  <div id="show">
    <div class="sm">${item.item_name }</div>
    <img align="top" src="<c:url value='/${item.item_pic }'/>" class="tp"/>
    <div id="book" style="float:left;">
	    <ul>
	    	<li>商品编号：${item.item_id }</li>
	    	<li>售价：<span class="price_n">&yen;${item.item_price }</span></li>
	    	<li>进货价：<span class="price_n">&yen;${item.purprice }</span></li>
	    	<li>条形码：<span class="price_n">${item.barcode }</span></li>
	    	<li>库存：<span class="price_n">${item.item_stock }</span></li>
	    </ul>
		<hr style="margin-left: 50px; height: 1px; color: #dcdcdc"/>
		<table class="tab">
			<tr>
			    <td colspan="3"><h3>商品描述</h3></td>
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
	</div>
  </div>
  
  
  <div id='formDiv'>
   <div class="sm">&nbsp;</div>
   <form action="<c:url value='/admin/AdminItemServlet'/>" method="post" id="form">
    <input type="hidden" name="method" id="method"/>
    	<input type="hidden" name="item_id" value="${item.item_id }"/> 
    <img align="top" src="<c:url value='/${item.item_pic }'/>" class="tp"/>
    <div style="float:left;">
	    <ul>
	    	<li>商品编号：${item.item_id }</li>
	    	<li>商品条形码:${item.barcode }</li>
	    	<li>商品名：　<input id="item_name" type="text" name="item_name" value="${item.item_name}" style="width:500px;"/></li>
	    	<li>单价：<input id="item_price" type="text" name="item_price" value="${item.item_price }" style="width:50px;"/></li>
	    	<li>进货价：<input id="purprice" type="text" name="purprice" value="${item.item_price }" style="width:50px;"/></li>
	    	<li>库存数量：<input id="item_stock" type="text" name="item_stock" value="${item.item_stock }" style="width:50px;"/></li>
	    	
	    </ul>
		<hr style="margin-left: 50px; height: 1px; color: #dcdcdc"/>
		<table class="tab">
			<tr>
				<td colspan="3">
					<h3>商品描述</h3>
				</td>
			</tr>
			<tr>
				<td colspan="3">
				<input id="item_descn" type="text" name="item_descn" value="${item.item_descn}" style="width:200px;"/>
				</td>
			</tr>
<tr>
				<td>
					一级分类：<select name="ca_pid" id="ca_pid" onchange="loadChildren()">
						<option value="">====请选择1级分类====</option>
				<c:forEach items="${parents }" var="parents">
			    		<option value="${parents.ca_id }"  <c:if test="${item.category.parent.ca_id eq parents.ca_id }">selected="selected"</c:if>>${parents.ca_name }</option>
				</c:forEach>

					</select>
				</td>
				<td>
					二级分类：<select name="item_caid" id="item_caid">
						<option value="">====请选择2级分类====</option>
<c:forEach items="${children }" var="child">
  	<option value="${child.ca_id }" <c:if test="${item.category.ca_id eq child.ca_id }">selected="selected"</c:if>>${child.ca_name }</option>
</c:forEach>
					</select>
				</td>
				<td></td>
			</tr>
			<tr>
				<td colspan="2">
					<input onclick="editForm()" type="button" name="method" id="editBtn" class="btn" value="编　　辑">
					<input onclick="deleteForm()" type="button" name="method" id="delBtn" class="btn" value="删　　除">
				</td>
				<td></td>
			</tr>
		</table>
	</div>
   </form>
  </div>

  </body>
</html>
