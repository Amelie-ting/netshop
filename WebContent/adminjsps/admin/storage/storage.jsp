<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
.in{
border:3px solid #000;

border-length:300px

}
</style>

</head>
<body>
<h1><p align="center">欢迎来到仓库管理系统</p></h1>

		<div class="in" style="width: 617px; height: 333px"><p align="center">
			</p><form action="<%= request.getContextPath()%>/inUpServlet" method="post" enctype="multipart/form-data" style="width: 1094px; position: relative; height: 144px; ">
	  		上传入库Excel文件：
	  		<br>
			<br>
	  		<input type="file" name="myfile1" style="width: 310px; ">	  
			<br>
			<br>	
	  		<input type="submit" value="提交" style="width: 211px; ">
	  		${result1}
	  		</form>
	  		
	  		<form action="<%= request.getContextPath()%>/inProServlet" method="post" enctype="multipart/form-data">
  			<input type="submit" value="开始进行入库数据处理" style="width: 216px; ">
  			<br>
  			${result2}
  			</form>							
		</div>
			<br>
			<br>
			<br>
			<br>
		<div class="out" >
			<form action="<%= request.getContextPath()%>/outUpServlet" method="post" enctype="multipart/form-data" style="width: 1094px; position: relative; height: 144px">
  			上传出库Excel文件：
  			
			<br>
			<br>
			<input type="file" name="myfile1" style="width: 310px; "><br>
			<br>
  			<input type="submit" value="提交" style="width: 213px; "></form>
			${result3}
  			<form action="<%= request.getContextPath()%>/outProServlet" method="post" enctype="multipart/form-data">
  			<input type="submit" value="开始进行出库数据处理" style="width: 216px; ">
  			<br>
  			${result4}
  			</form>	
		</div>

</body>
</html>