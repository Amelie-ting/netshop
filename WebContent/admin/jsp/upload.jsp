<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Excel数据上传</title>
</head>
<body>
	<h2>文件批量上传	</h2>
  	<form action="<%= request.getContextPath()%>/excelUpload" method="post" enctype="multipart/form-data">
  		上传文件1：<input type="file" name="myfile1"><br>
  		上传文件2：<input type="file" name="myfile2"><br>
  		上传文件3：<input type="file" name="myfile3"><br>
  		<input type="submit" value="提交">${result}
  	</form>
</body>
</html>