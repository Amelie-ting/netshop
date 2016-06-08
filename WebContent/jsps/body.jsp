<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="">

<title>body</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
a {
	text-decoration: none;
}
</style>
<script type="text/javascript">
	var imglist = new Array(6)//图片个数
	imglist[0] = "../bodyimages/body1.jpg"; //第一个图片的地址
	imglist[1] = "../bodyimages/body2.jpg";
	imglist[2] = "../bodyimages/body3.jpg";
	imglist[3] = "../bodyimages/body4.jpg";
	imglist[4] = "../bodyimages/body5.jpg";
	imglist[5] = "../bodyimages/body6.jpg";
	
	//用于存放对应商品详情的连接
	var itemlist=new Array(6)
	//商品的连接的话，这里要手动添加，我就随便加连接了
	itemlist[0] = "http://localhost:8080/netstore/itemServlet?method=load&bid=26"; //第一个商品的连接
	itemlist[1] = "http://localhost:8080/netstore/itemServlet?method=load&bid=27";
	itemlist[2] = "http://localhost:8080/netstore/itemServlet?method=load&bid=28";
	itemlist[3] = "http://localhost:8080/netstore/itemServlet?method=load&bid=29";
	itemlist[4] = "http://localhost:8080/netstore/itemServlet?method=load&bid=30";
	itemlist[5] = "http://localhost:8080/netstore/itemServlet?method=load&bid=31";
	var i = 0;
	function changeimg() {
		if (i == imglist.length) {
			i = 0;
		}
		document.getElementById("tp").src = imglist[i];
		//用于替换a标签中的href
		document.getElementById("item").href = itemlist[i];
		++i;
	}
	window.setInterval("changeimg()", 3000)//1000等于1秒
</script>
</head>

<body>
	<!--     <h1>欢迎进入网上商城系统</h1> -->
	<a  id="item" href="http://localhost:8080/netstore/itemServlet?method=load&bid=26" ><img id="tp" src="../bodyimages/body1.jpg" /></a>
</body>
</html>
