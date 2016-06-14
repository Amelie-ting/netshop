<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p><form action="<%= request.getContextPath()%>/testUploadServlet" method="post" enctype="multipart/form-data" style="width: 1094px; position: relative; height: 144px; ">
	  		上传入库Excel文件：
	  		<br>
			<br>
	  		<input type="file" name="myfile1" style="width: 310px; ">	  
			<br>
			<br>	
	  		<input type="submit" value="提交" style="width: 211px; ">
	  		${result1}
	  		</form>
</body>
</html>