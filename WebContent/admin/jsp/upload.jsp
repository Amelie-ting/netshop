<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Excel数据上传</title>
</head>
<body>
	<h2>文件批量上传	<form action="<%= request.getContextPath()%>/excelUpload" method="post" enctype="multipart/form-data" style="width: 1094px; position: relative; height: 144px">
  		上传Excel文件：<input type="file" name="myfile1" style="width: 310px; "><br>
  		${result}
  	<input type="submit" value="提交"></form></h2>
  	
  	<br>
  	<br>
  	<br>
  	<br>
  	<h2>数据处理</h2>
  	<form action="<%= request.getContextPath()%>/excelProcessing" method="post" enctype="multipart/form-data">
  		<input type="submit" value="开始进行入库数据处理">${result}
  		<input type="submit" value="开始进行出库数据处理">${result}
  	</form>
</body>
</html>