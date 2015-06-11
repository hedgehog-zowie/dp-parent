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
	<script type="text/javascript" src="js/stat/iuniDayOrderConvertRate_list.js?v=130607"></script>
	<script type="text/javascript">
	  var basePath="<%=basePath%>";
	</script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
	<tbody> 
	<tr> 
		<td class="td01"></td> 
		<td class="td02"><h3 class="topTitle fb f14">IUNI商城订单转化率</h3></td> 
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
			<dd>IUNI商城订单转化率</dd>
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
                <label for="code">订单来源</label>
            </dd>
            <dd class="ddr1">
                <s:select name="orderSourceIdConv" list="orderSourceMapConv"></s:select>
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
			<th>商城PV</th>
			<th>商城UV</th>
			<th>订单总数<br/>(含无效订单)</th>
			<th>下单转化率</th>
			<th>已支付订单数</th>
			<th>已支付订单比例</th>
			<th>在线支付订单数</th>	
			<th>在线支付订单比例</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="iuniDayOrderConvertRateList" status="s" id="iuniDayOrderConvertRate">
		<tr>
			<td class="mytd">${bizDate}</td>
			<td class="mytd">${pv}</td>
			<td class="mytd">${uv}</td>
			<td class="mytd">${totalOrderNum}</td>
			<td class="mytd">${orderConvertRate=='%'?'':orderConvertRate}</td>
			<td class="mytd">${payedOrderNum}</td>
			<td class="mytd">${payedOrderRate=='%'?'':payedOrderRate}</td>
			<td class="mytd">${onlinePayOrderNum}</td>
			<td class="mytd">${onlinePayOrderRate=='%'?'':onlinePayOrderRate}</td>
		</tr>
		</s:iterator>
		<tr>
			<td class="mytd">总计</td>
			<td class="mytd">${iuniDayOrderConvertRateSum.pv}</td>
			<td class="mytd">${iuniDayOrderConvertRateSum.uv}</td>
			<td class="mytd">${iuniDayOrderConvertRateSum.totalOrderNum}</td>
			<td class="mytd">${iuniDayOrderConvertRateSum.orderConvertRate=='%'?'':iuniDayOrderConvertRateSum.orderConvertRate}</td>
			<td class="mytd">${iuniDayOrderConvertRateSum.payedOrderNum}</td>
			<td class="mytd">${iuniDayOrderConvertRateSum.payedOrderRate=='%'?'':iuniDayOrderConvertRateSum.payedOrderRate}</td>
			<td class="mytd">${iuniDayOrderConvertRateSum.onlinePayOrderNum}</td>
			<td class="mytd">${iuniDayOrderConvertRateSum.onlinePayOrderRate=='%'?'':iuniDayOrderConvertRateSum.onlinePayOrderRate}</td>
		</tr>
	</tbody>
	</table>
		<c:import url="/WEB-INF/views/common/pagelink.jsp"></c:import>
                                说明：<br/>下单转化率：订单总数/商城UV
                  <br/>已支付订单：已支付订单数量
                  <br/>已支付订单比例：已支付订单/订单总数(以%计数，保留小数点后2位)		
                  <br/>在线支付订单数：以在线支付方式支付的支付订单数	
                  <br/>在线支付订单比例：以线支付方式的支付订单/订单总数(以%计数，保留小数点后2位)
	</div> 
	</td> 
		<td class="td03"></td> 
	</tr> 
	</tbody> 
	</table>
</body> 
</html> 