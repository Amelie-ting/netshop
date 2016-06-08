<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>地址管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body, html, #allmap {
	width: auto;
	height: auto;
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
<script src="http://api.map.baidu.com/api?v=1.4" type="text/javascript"></script>

<script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<script src="<c:url value='/jsps/js/address/area.js'/>"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/jsps/css/address/address.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/index.css'/>">

	<script src="area.js" type="text/javascript"></script>
<script type="text/javascript">
// 	_init_area();//三级联动

	var Gid = document.getElementById;
	var showArea = function() {
		Gid('show').innerHTML = "<h3>省" + Gid('s_province').value + " - 市"
				+ Gid('s_city').value + " - 县/区" + Gid('s_county').value
				+ "</h3>"
	};
	Gid('s_county').setAttribute('onchange', 'showArea()');

	/*
	 * 点击编辑按钮时执行本函数
	 */
	function addForm() {

		$("#method").val("addAddress");
		$("#form").submit();
	}
</script>
</head>
<body>
	<div>
		<div class="info">
			<form id="form"
				action="<c:url value='/addressServlet?method=addAddress'/>"
				method="post">

				<!------------------------table------------------------------------->
				<table class="form_table"  border="0" cellspacing="0">

					<caption style="font-size: 18px">收货信息</caption>

					<tr>
						<td><span >*</span>收货人姓名：</td>
						<td><input  type="text" name="name" /> </td>
					<tr>

						<td><span >*</span> 所在地区：</td>
						<td><select id="s_province" name="province" >
						</select>
							<select id="s_city" name="city" >
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
						<td><input type="text" id="suggestId"  name="addr"  /></td>

 						<td id="searchResultPanel"
                            style="border: 1px solid #C0C0C0; width: 150px; height: auto; display: none;"></td>
					</tr>

					<tr>
						<td><span >*</span>手机号码：</td>
						<td><input  type="text" name="tel"  /></td>
					</tr>

					<tr>
						<td>&nbsp;&nbsp;邮政编码：</td>
						<td><input type="text" name="zip" /></td>

					</tr>

					<tr>
						<td>&nbsp;&nbsp;设为默认<input name="isDefault" type="checkbox" value="1"></td>
						<td style="text-align: center"><input onclick="addForm()" type="button" name="method" id="editBtn" class="btn" value="保存"></td>
					</tr>

				</table>

				<!------------------------------end------------------------------->
						
			</form>
			<label class="error" id="msg"><h1>${msg}</h1></label>
		</div>
		
	</div>

<div
			style="position: absolute; left: 600px; top: 10px; width: 300px; height: 300px;"
			id="allmap"></div>

	<div id='show'>
		<table class="list_table" width="100%" cellpadding="0" cellspacing="0"
			style="position: absolute; left: 10px; top: 435px; width: 900px; height: 150px;">
			<col width="120px" />
			<col width="120px" />
			<col width="120px" />
			<col width="120px" />
			<col width="120px" />
			<col />
			<thead>
				<tr>

					<td>收货人</td>
					<td>所在地区</td>
					<td>详细地址</td>
					<td>手机号码</td>
					<td>邮编</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userAddressList }" var="address">
					<tr>
						<td>${address.a_uname}</td>
						<td>${address.a_state}${address.a_city}${address.a_county}</td>
						<td>${address.a_addr}</td>
						<td>${address.a_tel}</td>
						<td>${address.a_zip}</td>
						<td><a
							href="<c:url value='/AddressServlet?method=delete&aid=${address.a_id }'/>">删除</a>|
							<a
							href="<c:url value='/AddressServlet?method=findById&aid=${address.a_id }'/>">修改</a>|
							<c:if test="${address.a_isDefault eq 1}">
								<a class="red2"
									href="AddressServlet?method=setNoDefault&aid=${address.a_id}">取消默认</a>
							</c:if> <c:if test="${address.a_isDefault eq 2}">
								<a class="blue"
									href="AddressServlet?method=setDefault&aid=${address.a_id}">设为默认</a>
							</c:if></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap"); // 创建Map实例
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 11); // 初始化地图,设置中心点坐标和地图级别
	map.addControl(new BMap.MapTypeControl()); //添加地图类型控件
	map.setCurrentCity("北京"); // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放

	//根据省市县名定位
	function valuechange(element, callback) {

		$(element).bind("propertychange input", function(e) {
			var province = document.getElementById("s_province").value;
			var city = document.getElementById("s_city").value;
			var county = document.getElementById("s_county").value;
			if (province != "省份") {
				map.centerAndZoom(province, 15); // 用城市名设置地图中心点
				
				
			}
			if(city != "地级市"){
				map.centerAndZoom(city, 15);
			}
			if(county != "市、县级市"){
				map.centerAndZoom(county, 15);
			}
		});
	}
	valuechange('#s_province', function(cur) {
		map.centerAndZoom(province, 15);
	});
	valuechange('#s_city', function(cur) {

		map.centerAndZoom(city, 15);
	});
	valuechange('#s_county', function(cur) {
		map.centerAndZoom(county, 15);
		
	});

	//按关键字搜索（搜索具体位置）
	function G(id) {
		return document.getElementById(id);
	}
	var ac = new BMap.Autocomplete( //建立一个自动完成的对象
	{
		"input" : "suggestId",
		"location" : map
	});
	ac.addEventListener("onhighlight", function(e) { //鼠标放在下拉列表上的事件
		
			var str = "";
			var _value = e.fromitem.value;
			var value = "";
			if (e.fromitem.index > -1) {
				value =value = _value.Gid('s_province') +  _value.city +  _value.district +  _value.street +  _value.business;
			}
			str = "FromItem<br />index = " + e.fromitem.index + "<br />value = "
					+ value;

			value = "";
			if (e.toitem.index > -1) {
				_value = e.toitem.value;
				value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			}
			str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = "
					+ value;
			G("searchResultPanel").innerHTML = str;
		}
		
		
	);

	var myValue;
	ac.addEventListener("onconfirm", function(e) { //鼠标点击下拉列表后的事件
		var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		G("searchResultPanel").innerHTML = "onconfirm<br />index = "
				+ e.item.index + "<br />myValue = " + myValue;
		setPlace();
	});

	function setPlace() {
		map.clearOverlays(); //清除地图上所有覆盖物
		function myFun() {
			var pp = local.getResults().getPoi(0).point; //获取第一个智能搜索的结果
			map.centerAndZoom(pp, 18);
			var marker = new BMap.Marker(pp);
			map.addOverlay(marker); //添加标注
			BMap.Marker(pp).setLabel(label);
		}

		var local = new BMap.LocalSearch(map, { //智能搜索
			onSearchComplete : myFun
		});
		local.search(myValue);
	};
	
	
</script>


</html>