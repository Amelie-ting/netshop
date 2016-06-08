<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<script src="<c:url value='/jsps/js/address/area.js'/>"></script>
<script src="<c:url value='/jsps/js/address/address.js'/>"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/jsps/css/address/address.css'/>">
<style type="text/css">
body, html, #allmap {
	width: 100%;
	height: 100%;
	margin: 0;
	font-family: "微软雅黑";
}

p {
	margin-left: 5px;
	font-size: 14px;
}
table.form_table {
	border: 0 solid silver;
	font-size: 12px;
	width: auto;
	height: auto;
}

table.form_table span {
	color: red;
}

table.form_table tr td {
	padding: 8px;
}

table.form_table tr td:first-child {
	width: 120px ;
	text-align: right;
	letter-spacing:2px;
}
table.form_table tr td:nth-child(2) {
	width: 300px;
}
table.form_table tr td input[type="text"] {
	width: 100%;
	height: 20px;
}
table.form_table tr td select {
	margin-left: 3px;
	width: 31%;
	height: 25px;
	overflow: hidden;
}
#editBtn {
	width: 80px;
}
</style>
<script type="text/javascript">	
	/*
	 * 点击编辑按钮时执行本函数
	 */
	function editForm() {
			$("#method").val("update");
			$("#form1").submit();
	}
</script>
</head>
<body>
	<div id="edit">
		<div class="info">
			<form id="form1" action="<c:url value='/addressServlet'/>"
				method="post">
				<input type="hidden" name="method" id="method" /> <input
					type="hidden" name="aid" value="${userddress.a_id }" />
				<!------------------------table------------------------------------->
				<table class="form_table"  border="0" cellspacing="0">

					<caption style="font-size: 18px">收货人信息</caption>
                    
					<tr>
						<td><span >*</span>收货人姓名：</td>
						<td><input  type="text" name="name" value="${userddress.a_uname}"/> </td>
					<tr>

						<td><span >*</span> 所在地区：</td>
						<td><select id="s_province"  name="province" >
						
						</select>
							<select  id="s_city" name="city" >
							</select>
							<select id="s_county"	name="county" >
							</select>
						</td>
						<td><script src="area.js" type="text/javascript"></script> <script
								type="text/javascript">
							_init_area();
						</script></td>

					<tr>

						<td><span >*</span>街道地区：</td>
						<td><input type="text" id="suggestId"  name="addr"  value="${userddress.a_addr}"/></td>

 						<td id="searchResultPanel"
                            style="border: 1px solid #C0C0C0; width: 150px; height: auto; display: none;"></td>
					</tr>

					<tr>
						<td><span >*</span>手机号码：</td>
						<td><input  type="text" name="tel" value="${userddress.a_tel}"/></td>
					</tr>

					<tr>
						<td>&nbsp;&nbsp;邮政编码：</td>
						<td><input type="text" name="zip" value="${userddress.a_zip}"/></td>

					</tr>

					<tr>
						<td>&nbsp;&nbsp;设为默认<input name="isDefault" type="checkbox" value="1"></td>
						<td style="text-align: center"><input onclick="editForm()"
						type="button" name="method" id="editBtn" class="btn" value="修改"></td>
					</tr>

				</table>

				<!------------------------------end------------------------------->

			</form>
		</div>
	</div>
</body>
</html>