<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP  starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%-- <link rel="stylesheet" type="text/css" href="<c:url value='/app/css/add.css'/>"> --%>


<link rel="stylesheet" type="text/css" href="<c:url value='/jquery/jquery.datepick.css'/>">
<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>


<script type="text/javascript">
$(function () {
	
	$("#gdate").datepick({dateFormat:"yy-mm-dd"});
	
	$("#btn").click(function() {
		var item_name = $("#item_name").val();
		var item_price = $("#item_price").val();
		var item_price = $("#purprice").val();
		var item_pic = $("#item_pic").val();
		var item_wid = $("#item_wid").val();
		var item_caid = $("#item_caid").val();
		var ca_pid = $("#ca_pid").val();
		var item_descn = $("#item_descn").val();
		
		if(!item_name || !item_price||!purprice || !item_pic || !item_wid || !item_caid || !item_descn || !item_gdate) {
			alert("商品名、单价、图片、仓库id、商品类别、商品描述都不能为空！");
			return false;
		}
		$("#form").submit();
	
	
	
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

</script>
  </head>
  
  <body>
  
   <p style="font-weight: 900; color: red;">${msg }</p>
   <form action="<%= request.getContextPath() %>/AdminAddItemsServlet" enctype="multipart/form-data" method="post" id="form">
                              <table border="1" bgcolor="#0099CC">
    <tr>
      <td>商品名:
        <input id="item_name" type="text" name="item_name">
      </td>
    </tr>
    <tr >
      <td>单价：
        <input id= "item_price" type="text" name="item_price" >
      </td>
    </tr>
    <tr valign="middle">
      <td>进货价：
        <input id= "purprice" type="text" name="purprice" >
      </td>
    </tr>
    <tr valign="middle">
      <td>图片：
        <input id="file" type="file" name="file" >
      </td>
     </tr>
     <tr valign="middle">
      <td>仓库id:
        <input id="item_wid" type="text" name="item_wid" >
      </td>
    </tr>
    <!-- <tr valign="middle">
      <td>商品类别id:
        <input id="item_caid" type="text" name="item_caid" >
      </td>
    </tr> -->
    <tr valign="middle">
      <td>商品描述:
        <input id="item_descn" type="text" name="item_descn" >
      </td>
    </tr>
      <tr valign="middle">
      <td>库存数量:
        <input id="item_stock" type="text" name="item_stock" >
      </td>
    </tr>
   <tr>
    <tr valign="middle">
      <td>条形码:
        <input id="barcode" type="text" name="barcode" >
      </td>
    </tr>
   <tr>
		<td colspan="3">生产日期：<input type="text" id="gdate" name="item_gdate" value="2016-04-08" style="width:100px;"/></td>
   </tr>
   <tr>
				<td>
					一级分类：<select name="ca_pid" id="ca_pid" onchange="loadChildren()">
						<option value="">====请选择1级分类====</option>
<c:forEach items="${parents }" var="parents">
			    		<option value="${parents.ca_id }">${parents.ca_name }</option>
</c:forEach>

					</select>
				</td>
				<td>
					二级分类：<select name="item_caid" id="item_caid">
						<option value="">====请选择2级分类====</option>
					</select>
				</td>
				<td></td>
			</tr>
   <tr>
	<td>
		<input type="submit"   value="提交">
	</td>
	<td></td>
	<td></td>
   </tr>
    
  </table>
   </form>
 
  
  </body>
</html>
