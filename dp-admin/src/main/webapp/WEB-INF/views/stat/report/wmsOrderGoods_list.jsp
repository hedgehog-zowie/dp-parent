<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		td{height: 20px;}
		th{height: 40px;color: #0091D3;}
	</style>
	<script src="template/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/stat/wmsOrderGoods_list.js?v=130607"></script>
	<script type="text/javascript">
	  var basePath="<%=basePath%>";
	</script>
	<script type="text/javascript">
/*超链接文字提示*/
$(function() {
	var x = 10;
	var y = 20;
	$("a.tooltip").mouseover(function(e) {
		$("#tooltip").text(this.title).css({
			"top" : (e.pageY + y) + "px",
			"left" : (e.pageX + x) + "px"
		}).show(); // 设置x坐标和y坐标，并且显示
	}).mouseout(function() {
		$("#tooltip").text("").hidden(); // 移除

	}).mousemove(function(e) {
		$("#tooltip").css({
			"top" : (e.pageY + y) + "px",
			"left" : (e.pageX + x) + "px"
		});
	});
})
</script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
	<tbody> 
	<tr> 
		<td class="td01"></td> 
		<td class="td02"><h3 class="topTitle fb f14">WMS订单统计</h3></td> 
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
			<dd>WMS订单统计</dd>
		</dl>
		
		</div>
		
		<div>
		<input type="hidden" id="msg" value="<s:property value="msg"/>"/>
		</div>
		
		<div class="frame4" style="height:70px;">
		<form action="" method="post" id="queryFrm">
			<div style="display:none;">
			    <!-- 
				<input  type="hidden" id="bizDate" name="bizDate" value="${bizDate}"/>
			    <input  type="hidden" id="adName" name="adName" value="${adName}"/>
			    <input  type="hidden" id="channelName" name="channelName" value="${channelName}"/>
			     -->
			</div>
		<dl>
			<dd class="ddl1">
				<label for="code">下单开始日期</label>
			</dd>
			<dd class="ddr1">
				<input class="Wdate1" name="beginDate" id="beginDate" type="text" size="10"
					onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});"
					readonly="readonly" value="${beginDate}"></input>
			</dd>
			<dd class="ddl1">
				<label for="code">下单结束日期</label>
			</dd>
			<dd class="ddr1">
				<input class="Wdate1" name="endDate" id="endDate" type="text" size="10"
					onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});"
					readonly="readonly" value="${endDate}"></input>
			</dd>
			<dd class="ddl1">
				<label for="code">拣货开始日期</label>
			</dd>
			<dd class="ddr1">
				<input class="Wdate1" name="jhBeginDate" id="jhBeginDate" type="text" size="10"
					onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});"
					readonly="readonly" value="${jhBeginDate}"></input>
			</dd>
			<dd class="ddl1">
				<label for="code">拣货结束日期</label>
			</dd>
			<dd class="ddr1">
				<input class="Wdate1" name="jhEndDate" id="jhEndDate" type="text" size="10"
					onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});"
					readonly="readonly" value="${jhEndDate}"></input>
			</dd>
			<dd class="ddl1">
				<label for="gdName">运单号</label>
			</dd>
			<dd class="ddr1">
				<input type="text" id="shippingNo" name="shippingNo" value="${shippingNo}"/>
			</dd>
			<dd class="ddl1">
				<label for="gdName">订单号</label>
			</dd>
			<dd class="ddr1">
				<input type="text" id="orderCode" name="orderCode" value="${orderCode}"/>
			</dd>
			<dd class="ddl1">
				<label for="gdName">拣货批次号</label>
			</dd>
			<dd class="ddr1">
				<input type="text" id="batchCodeStr" name="batchCodeStr" value="${batchCodeStr}"/>(以单个空格、逗号、分号、斜杠、单竖线分隔)
			</dd>
			<dd class="ddl1">
				<input class="btn4" type="button" id="btn_query" value="查询"/>
			</dd>
			<dd class="ddl3">
				<input class="btn6" type="button" id="btn_exportExcel" value="导出EXCEL"/>
			</dd>
		</dl>
			
		</form>
		</div>
		
	<table class="listtable" style="width: 100%">
	<thead>
		<tr>
			<th>下单时间</th>
			<th>拣货时间</th>
			<th>订单来源</th>
			<th>订单号</th>
			<th>支付类型</th>
			<th>交易号</th>
			<th>付款人</th>
			<th>SKU名称</th>
			<th>商品数量</th>
			<th>商品单价</th>			
			<th>商品总价</th>
			<th>发票金额</th>
		    <th>发票抬头</th>			
			<th>快递类型</th>
			<th>运单号</th>
			<th>拣货批次号</th>
			<th>发货状态</th>
			<th>收货人</th>
			<th>收货地址</th>
			<th>订单附言</th>
			<th>客服备注</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="wmsOrderGoodsList" status="s" id="wmsOrderGoods">
		<tr>
			<td class="mytd"><fmt:formatDate value="${orderTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td class="mytd"><fmt:formatDate value="${jhTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td class="mytd">${orderSource}</td>
			<td class="mytd">${orderCode}</td>
			<td class="mytd">${paymentName}</td>
			<td class="mytd">${payNo}</td>
			<td class="mytd">${orderUser}</td>
			<td class="mytd">${skuName}</td>
			<td class="mytd">${quantity}</td>
			<td class="mytd">${unitPrice}</td>
			<td class="mytd">${subtotalPrice}</td>
			<td class="mytd">${invoiceAmount}</td>
			<td class="mytd">${invoiceTitle}</td>
			<td class="mytd">${shippingName}</td>
			<td class="mytd">${shippingNo}</td>
			<td class="mytd">${batchCode}</td>
			<td class="mytd">${orderStatus}</td>
			<td class="mytd">${consignee}</td>
			<td class="mytd" ><a class="tooltip"  title="${address}" href="javascript:;">${shortAddress}</td>
			<td class="mytd">${postscript}</td>
			<td class="mytd">${remark}</td>
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