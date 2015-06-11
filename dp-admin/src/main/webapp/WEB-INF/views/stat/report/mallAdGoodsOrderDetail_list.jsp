<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
	<base href="<%=basePath%>"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>菜单列表</title> 
	<link href="template/css/base.css" rel="stylesheet" type="text/css" /> 
	<link href="template/css/common.css" rel="stylesheet" type="text/css" /> 
	<link href="template/css/page_admin_main.css" rel="stylesheet" type="text/css" /> 
	<link href="template/css/additional.css" rel="stylesheet" type="text/css" /> 
	<link href="css/alarm/main.css" rel="stylesheet" type="text/css" />	
	<style type="text/css">
		td{height: 50px;}
		th{height: 80px;color: #0091D3;}
	</style>
	<script src="template/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/stat/mallAdGoodsOrderDetail_list.js?v=130607"></script>
	<script type="text/javascript">
	  var basePath="<%=basePath%>";
	</script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
	<tbody> 
	<tr> 
		<td class="td01"></td> 
		<td class="td02">
					<h3 class="topTitle fb f14">					
					<s:if test="detailType == 1">站外推广报表(总表之明细表)</s:if>
					<s:elseif test="detailType == 2">站外推广报表(日表之明细表)</s:elseif>
					<s:elseif test="detailType == 3">站外推广报表(推广表之明细表)</s:elseif>
					</h3>
	    </td> 
		<td class="td03"></td> 
	</tr> 
	</tbody> 	
	</table>  
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_main"> 
	<tbody> 
	<tr> 
		<td class="td01"></td> 
		<td class="adminMain_wrap"> 
		<div class="wrapMain" style="height:auto;vertical-align: middle;"> 
		<!-- 在这里填充 -->
		<div class="adminUp_wrap" style="height:24px;margin-bottom:10px;">
		<dl class="adminPath clearfix2" style="width:50%;">
			<dt>您现在的位置：</dt>
			<dd>查询功能</dd>
			<dd>	
					<s:if test="detailType == 1">站外推广报表(总表之明细表)</s:if>
					<s:elseif test="detailType == 2">站外推广报表(日表之明细表)</s:elseif>
					<s:elseif test="detailType == 3">站外推广报表(推广表之明细表)</s:elseif>
		    </dd>
		</dl>
		
		</div>
		
		<div>
		<input type="hidden" id="msg" value="<s:property value="msg"/>"/>
		
		</div>

    	<div class="" style="height:105px;">
 
    	      <h3 class="topTitle fb f14">					
                   广告名：${adName}
			  </h3>
			  <h3 class="topTitle fb f14">					
                   点击来源：${channelName}
			  </h3>
 
		<form action="" method="post" id="queryFrm">
		<div>
		    <input  type="hidden" id="detailType" name="detailType" value="${detailType}"/>
		    <input  type="hidden" id="bizDate" name="bizDate" value="${bizDate}"/>
		    <input  type="hidden" id="adId" name="adId" value="${adId}"/>
		    <input  type="hidden" id="adName" name="adName" value="${adName}"/>
		    <input  type="hidden" id="channelName" name="channelName" value="${channelName}"/>
			<input class="btn6" type="button" id="btn_exportExcel" value="导出EXCEL"/>
		</div>
			
		</form>
		</div>
		
	<table class="listtable" style="width: 100%">
	<thead>
		<tr>
			<th>统计日期</th>
			<th>商品名称</th>
			<th>商品编号</th>
			<th>商品数</th>
			<th>商品单价</th>
			<th>商品总金额</th>
			<th>订单编号</th>
			<th>订单状态</th>
			<th>结算时间</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="mallAdGoodsOrderDetailList" status="s" id="mallAdGoodsOrderDetail">
		<tr>
			<td class="mytd"><s:property value="bizDate" /></td>
			<td class="mytd"><s:property value="goodsName" /></td>
			<td class="mytd"><s:property value="goodsSn" /></td>
			<td class="mytd"><s:property value="goodsNum" /></td>
			<td class="mytd"><s:property value="goodsPrice" /></td>
			<td class="mytd">${goodsNum*goodsPrice}</td>
			<td class="mytd"><s:property value="oredrSn" /></td>
			<td class="mytd">
				    <s:if test="orderStatus == 0">未确认</s:if>
					<s:elseif test="orderStatus == 1">已确认</s:elseif>
					<s:elseif test="orderStatus == 2">已取消</s:elseif>
					<s:elseif test="orderStatus == 3">无效</s:elseif>
				    <s:elseif test="orderStatus == 4">退货</s:elseif>
					<s:elseif test="orderStatus == 5">已分单</s:elseif>
					<s:elseif test="orderStatus == 6">部分分单</s:elseif>
					<s:else>未知状态</s:else>
			</td>
			<td class="mytd">${payTime}</td>
		</tr>
		</s:iterator>
	</tbody>
	</table>
		<c:import url="/WEB-INF/views/common/pagelink.jsp"></c:import>
	</div> 
	</td> 
		<td class="td03"></td> 
	</tr> 
	</tbody> 
	</table>
</body> 
</html> 