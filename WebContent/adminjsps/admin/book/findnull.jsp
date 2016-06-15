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
		<table width="1000" border="1" align="center">
				 <tr>
				  <th align="left">条形码</th>
				  <th align="right">内容修改</th>

				 </tr>
			<c:forEach items="${nullItems}" var="item">
				 <tr>
				  <td align="left">${item.barcode }</td>
				  <td align="right"><a href="<c:url value='/admin/AdminItemServlet?method=findByItemBar&bid=${item.item_id }'/>">查看信息</a></td>
				 </tr>
			</c:forEach>	 
	 </table>
</body>
</html>