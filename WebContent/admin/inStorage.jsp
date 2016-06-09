<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>入库</title>
</head>
<body>
<h1>欢迎来到入库管理界面</h1>
	
	<div class="upload"> 
	<form action="<%= request.getContextPath()%>/inUpServlet" method="post" enctype="multipart/form-data" style="width: 1094px; position: relative; height: 144px">
  		上传入库Excel文件：
  		<br>
  		<input type="file" name="myfile1" style="width: 310px; ">
  		<br>
  		${result}
  	<input type="submit" value="提交"></form>
	</div>
	<br>
	<br>
	<br>
	<br>
	<div class="processing">
		<form action="<%= request.getContextPath()%>/inProServlet" method="post" enctype="multipart/form-data">
  		<input type="submit" value="开始进行入库数据处理">
  		<br>
  		${result2}
  	</form>
	</div>

</body>
</html>