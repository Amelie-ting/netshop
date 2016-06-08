<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>欢迎来到商城</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/main.css'/>">
	<script language="Javascript">
var getFFVersion=navigator.userAgent.substring(navigator.userAgent.indexOf("Firefox")).split("/")[1]
//extra height in px to add to iframe in FireFox 1.0+ browsers
var FFextraWidth=getFFVersion>=0.1? 16 : 0 
var FFextraHeight=getFFVersion>=0.1? 16 : 0
function dyniframesizeWidth(iframename) {
  var pTar = null;
  if (document.getElementById){
    pTar = document.getElementById(iframename);
  }
  else{
    eval('pTar = ' + iframename + ';');
  }
  if (pTar && !window.opera){
    //begin resizing iframe
    pTar.style.display="block"
    
    if (pTar.contentDocument && pTar.contentDocument.body.offsetWidth){
      //ns6 syntax
      pTar.width = pTar.contentDocument.body.offsetWidth+FFextraWidth; 
    }
    else if (pTar.Document && pTar.Document.body.scrollWidth){
      //ie5+ syntax
      pTar.width = pTar.Document.body.scrollWidth;
    }
  }
}
function dyniframesizeHeight(iframename) {
  var pTar = null;
  if (document.getElementById){
    pTar = document.getElementById(iframename);
  }
  else{
    eval('pTar = ' + iframename + ';');
  }
  if (pTar && !window.opera){
    //begin resizing iframe
    pTar.style.display="block"
    
    if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight){
      //ns6 syntax
      pTar.height = pTar.contentDocument.body.offsetWidth+FFextraHeight; 
    }
    else if (pTar.Document && pTar.Document.body.scrollHeight){
      //ie5+ syntax
      pTar.height = pTar.Document.body.scrollHeight;
    }
  }
}
</script>
</head>
	<!--商城主页，主体框架在此页面之中  -->
<body>
	<table class="table" align="center">
		<!-- top栏指向 -->
		<tr class="trTop">
			<td colspan="2" class="tdTop">
				<iframe frameborder="0" src="<c:url value='/jsps/top.jsp'/>" name="top"></iframe>
			</td>
		</tr>
		<!-- 左侧类别导航栏以及上部搜索栏 -->
		<tr>
			<!-- 类别导航 -->
			<td class="tdLeft" rowspan="2">
				<iframe frameborder="0" src="<c:url value='/categoryServlet?method=findAll'/>" name="left"></iframe>
			</td>
			<!-- 搜索栏 -->
			<td class="tdSearch" style="border-bottom-width: 0px;">
				<iframe frameborder="0" src="<c:url value='/jsps/search.jsp'/>" name="search"></iframe>
			</td>
		</tr>
		<tr>
		<td style="border-top-width: 0px;  ">
			<iframe frameborder="0"  src="<c:url value='/jsps/body.jsp'/>" name="body"></iframe>
		</td>
	</tr>
	</table>
</body>
</html>