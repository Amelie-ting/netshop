<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>综合搜索</h1>
		<form action="/admin/AdminItemServlet/>"  method="post" >
			
			<input type="hidden" name="method" value="search"/>
			请输入名称或条形码：	<input type="text" name="name"  /><br/>
			<input type="submit" value="搜索"/>
		</form>

</body>
</html>