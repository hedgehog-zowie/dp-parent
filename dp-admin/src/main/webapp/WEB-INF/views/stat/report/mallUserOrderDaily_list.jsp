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
	<script type="text/javascript" src="js/stat/mallUserOrderDaily_list.js?v=130607"></script>
	<script type="text/javascript">
	  var basePath="<%=basePath%>";
	</script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
	<tbody> 
	<tr> 
		<td class="td01"></td> 
		<td class="td02"><h3 class="topTitle fb f14">金立商城会员注册及订单信息</h3></td> 
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
			<dd>金立商城会员注册及订单信息</dd>
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
			<th>注册日期</th>
			<th>注册会员数</th>
			<th>下单总数<br/>(含无效订单)</th>
			<th>下单总金额</th>
			<th>有效订单数<br/>(已支付+已审核货到付款)</th>			
			<th>有效订单总金额</th>	
			<th>会员平均有效订单数</th>
			<th>会员平均有效购物金额</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="mallUserOrderDailyStatList" status="s" id="mallUserOrderDailyStat">
		<tr>
			<td class="mytd">${bizDate}</td>
			<td class="mytd">${regUserNum}</td>
			<td class="mytd">${totalOrderNum}</td>
			<td class="mytd">${totalOrderAmount}</td>
			<td class="mytd">${validOrderNum}</td>
			<td class="mytd">${validOrderAmount}</td>
			<td class="mytd">${orderNumPerUser}</td>
			<td class="mytd">${amountPerUser}</td>
		</tr>
		</s:iterator>
		<tr>
			<td class="mytd">总计</td>
			<td class="mytd">${mallUserOrderSum.regUserNum}</td>
			<td class="mytd">${mallUserOrderSum.totalOrderNum}</td>
			<td class="mytd">${mallUserOrderSum.totalOrderAmount}</td>
			<td class="mytd">${mallUserOrderSum.validOrderNum}</td>
			<td class="mytd">${mallUserOrderSum.validOrderAmount}</td>
			<td class="mytd">${mallUserOrderSum.orderNumPerUser}</td>
			<td class="mytd">${mallUserOrderSum.amountPerUser}</td>
		</tr>
	</tbody>
	</table>
		<c:import url="/WEB-INF/views/common/pagelink.jsp"></c:import>
                                说明：<br/>注册会员数：所选时间内商城新增的会员数<br/>下单总数：所有订单汇总数<br/>下单总金额：所选时间段会员产生的有效订单总金额
             <br/>有效订单数：订单状态-已支付订单（线上支付）、已审核订单（货到付款），待发货订单、发货中、已签收、已完成<br/>有效订单总金额：所选时间段注册会员产生的有效订单总金额
             <br/>会员平均有效订单数：有效订单数/注册会员数<br/>会员平均有效购物金额：有效订单购物总金额/注册会员数
	</div> 
	</td> 
		<td class="td03"></td> 
	</tr> 
	</tbody> 
	</table>
</body> 
</html> 