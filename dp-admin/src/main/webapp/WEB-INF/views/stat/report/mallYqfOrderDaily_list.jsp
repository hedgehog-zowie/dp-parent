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
		td{height: 20px;}
		th{height: 40px;color: #0091D3;}
	</style>
	<script src="template/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/stat/mallYqfOrderDaily_list.js?v=130607"></script>
	<script type="text/javascript">
	  var basePath="<%=basePath%>";
	</script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
	<tbody> 
	<tr> 
		<td class="td01"></td> 
		<td class="td02"><h3 class="topTitle fb f14">金立商城CPS推广订单信息</h3></td> 
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
			<dd>金立商城CPS推广订单信息</dd>
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
				<label for="code">开始日期</label>
			</dd>
			<dd class="ddr1">
				<input class="Wdate1" name="beginDate" id="beginDate" type="text" size="10"
					onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});"
					readonly="readonly" value="${beginDate}"></input>
			</dd>
			<dd class="ddl1">
				<label for="code">结束日期</label>
			</dd>
			<dd class="ddr1">
				<input class="Wdate1" name="endDate" id="endDate" type="text" size="10"
					onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});"
					readonly="readonly" value="${endDate}"></input>
			</dd>
			<dd class="ddl1">
				<label for="code">广告ID</label>
			</dd>
			<dd class="ddr1">
				<input type="text" id="cid" name="cid" value="${cid}"/>
			</dd>
			<dd class="ddl1">
				<label for="code">数据来源</label>
			</dd>
			<dd class="ddr1">
				<input type="text" id="source" name="source" value="${source}"/>
			</dd>
						<dd class="ddl1">
				<label for="code">推广渠道</label>
			</dd>
			<dd class="ddr1">
				<input type="text" id="channel" name="channel" value="${channel}"/>
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
			<th>统计日期</th>
			<th>广告ID</th>
			<th>数据来源</th>
			<th>推广渠道</th>
			<th>订单总数</th>			
			<th>有效订单数</th>	
			<th>拒收订单数</th>
			<th>退单数</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="mallYqfOrderDailyStatList" status="s" id="mallYqfOrderDailyStat">
		<tr>
			<td class="mytd">${bizDate}</td>
			<td class="mytd">${cid}</td>
			<td class="mytd">${source}</td>
			<td class="mytd">${channel}</td>
			<td class="mytd">${totalOrderNum}</td>
			<td class="mytd">${validOrderNum}</td>
			<td class="mytd">${refusedOrderNum}</td>
			<td class="mytd">${returnedOrderNum}</td>
		</tr>
		</s:iterator>
		<tr>
			<td class="mytd" colspan="4" align="center">总计</td>
			<td class="mytd">${mallYqfOrderSum.totalOrderNum}</td>
			<td class="mytd">${mallYqfOrderSum.validOrderNum}</td>
			<td class="mytd">${mallYqfOrderSum.refusedOrderNum}</td>
			<td class="mytd">${mallYqfOrderSum.returnedOrderNum}</td>
		</tr>
	</tbody>
	</table>
		<c:import url="/WEB-INF/views/common/pagelink.jsp"></c:import>
                                说明：<br/>订单总数：所有订单汇总数
             <br/>有效订单数：订单状态-已支付订单（线上支付）、已审核订单（货到付款），待发货订单、发货中、已签收、已完成
             <br/>拒收订单数：时间范围内所有被拒收的订单，即订单状态为：用户已拒收
             <br/>退单数：时间范围内所有申请了退货的订单，包括：退货中、退款中
	</div> 
	</td> 
		<td class="td03"></td> 
	</tr> 
	</tbody> 
	</table>
</body> 
</html> 