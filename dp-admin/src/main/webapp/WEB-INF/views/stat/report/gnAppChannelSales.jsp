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
	<link href="template/jquery-ui/css/start/jquery-ui-1.10.3.custom.min.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		td{height: 50px;}
		th{height: 80px;color: #0091D3;}
	</style>
	<script src="template/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="template/jquery-ui/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script type="text/javascript" src="js/stat/gnAppChannelSales.js?v=140103"></script>
	<script type="text/javascript">
	  var basePath="<%=basePath%>";
	</script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
	<tbody> 
	<tr> 
		<td class="td01"></td> 
		<td class="td02"><h3 class="topTitle fb f14">APP渠道销售统计报表</h3></td> 
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
			<dd>报表管理</dd>
			<dd>APP运营报表</dd>
			<dd>APP渠道销售统计报表</dd>
		</dl>
		
		</div>
		
		<div>
		<input type="hidden" id="msg" value="<s:property value="msg"/>"/>
		</div>
		
		<div class="frame4" style="height:105px;">
		<form action="" method="post" id="queryFrm">
		<dl>
			<dd class="ddl1">
				<label for="channels">渠道</label>
			</dd>
			<dd class="ddr3">
				<input type="text" id="channelNames" name="channels" value="${statParams['channels']}" readonly="readonly" style="width:95%"/>
				<input type="hidden" id="channels" name="statParams['channels']" value="${statParams['channels']}"/>
			</dd>
			<dd class="ddl3">
				<input class="btn4" type="button" id="btn_channels" value="渠道选择"/>
			</dd>
		</dl>
		<dl>
			<dd class="ddl1">
				<label for="channels">版本</label>
			</dd>
			<dd class="ddr3">
				<input type="text" id="apkVersionNames" name="apkVersions" value="${statParams['apkVersions']}" readonly="readonly" style="width:95%"/>
				<input type="hidden" id="apkVersions" name="statParams['apkVersions']" value="${statParams['apkVersions']}"/>
			</dd>
			<dd class="ddl3">
				<input class="btn4" type="button" id="btn_apkVersions" value="版本选择"/>
			</dd>
		</dl>
		<dl>
			<dd class="ddl1">
				<label for="channels">APP应用名</label>
			</dd>
			<dd class="ddr1">
				<select id="appName" class="select1" name="statParams['appName']">
					<s:if test="statParams['appName'] == 'ELIFE APP'">
						<option value="ELIFE APP" selected="selected">
							Elife APP
						</option>
						<option value="GIONEESHOP APP">
							金立商城 APP
						</option>
						<option value="IUNI APP">
							IUNI商城 APP
						</option>
						<option value="WJQ APP">
							玩机圈APP
						</option>
					</s:if>
					<s:elseif test="statParams['appName'] == 'GIONEESHOP APP'">
						<option value="ELIFE APP">
							Elife APP
						</option>
						<option value="GIONEESHOP APP" selected="selected">
							金立商城 APP
						</option>
						<option value="IUNI APP">
							IUNI商城 APP
						</option>
						<option value="WJQ APP">
							玩机圈APP
						</option>
					</s:elseif>
					<s:elseif test="statParams['appName'] == 'IUNI APP'">
						<option value="ELIFE APP">
							Elife APP
						</option>
						<option value="GIONEESHOP APP">
							金立商城 APP
						</option>
						<option value="IUNI APP" selected="selected">
							IUNI商城 APP
						</option>
						<option value="WJQ APP">
							玩机圈APP
						</option>
					</s:elseif>
					<s:elseif test="statParams['appName'] == 'WJQ APP'">
						<option value="ELIFE APP">
							Elife APP
						</option>
						<option value="GIONEESHOP APP">
							金立商城 APP
						</option>
						<option value="IUNI APP">
							IUNI商城 APP
						</option>
						<option value="WJQ APP" selected="selected">
							玩机圈APP
						</option>
					</s:elseif>
					<s:else>
						<option value="ELIFE APP" selected="selected">
							Elife APP
						</option>
						<option value="GIONEESHOP APP">
							金立商城 APP
						</option>
						<option value="IUNI APP">
							IUNI商城 APP
						</option>
						<option value="WJQ APP">
							玩机圈APP
						</option>
					</s:else>
				</select>
			</dd>
			<dd class="ddl1">
				<label for="beginDate">统计日期开始</label>
			</dd>
			<dd class="ddr1">
				<input class="Wdate1" name="statParams['beginDate']" id="beginDate" type="text" size="10"
					readonly="readonly" value="${statParams['beginDate']}"></input>
			</dd>
			<dd class="ddl1">
				<label for="endDate">统计日期结束</label>
			</dd>
			<dd class="ddr1">
				<input class="Wdate1" name="statParams['endDate']" id="endDate" type="text" size="10"
					readonly="readonly" value="${statParams['endDate']}"></input>
			</dd>
			<dd class="ddl1">
				<input class="btn4" type="button" id="btn_query" value="查询"/>
			</dd>
			<dd class="ddl3">
				<input class="btn6" type="button" id="btn_exportExcel" value="导出EXCEL"/>
			</dd>
		</dl>
		<div style="display: none;">
			<s:hidden id="flag_h" name="flag" value="%{#parameters.flag}"></s:hidden>
		</div>			
		</form>
		</div>
		
		<div style="display: none;">
			<s:iterator value="statParams" id="paramsEntry">
				<s:hidden id="%{#paramsEntry.key}_h" name="%{#paramsEntry.key}" value="%{#paramsEntry.value}"></s:hidden>
			</s:iterator>
		</div>
		
	<table class="listtable" style="width: 100%">
	<thead>
		<tr>
			<th>序号</th>
			<th>统计日期</th>
			<th>渠道名</th>
			<th>启动用户</th>
			<th>订单数</th>
			<th>支付数</th>
			<th>下单率</th>
			<th>成交率</th>
			<th>支付率</th>
			<th>销售额</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="#request.channelSalesList" status="s" id="channelSales">
		<tr>
			<td class="mytd"><s:property value="rowNum" /></td>
			<td class="mytd"><s:property value="statDate" /></td>
			<td class="mytd"><s:property value="channelName" /></td>
			<td class="mytd"><s:property value="launchUserNum" /></td>
			<td class="mytd"><s:property value="orderNum" /></td>
			<td class="mytd"><s:property value="payNum" /></td>
			<td class="mytd"><s:property value="orderRate" /></td>
			<td class="mytd"><s:property value="closeRate" /></td>
			<td class="mytd"><s:property value="payRate" /></td>
			<td class="mytd"><s:property value="salesAmount" /></td>
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
	
	<!-- ApkVersion Dialog -->
	<div id="apkVersion_dialog">
		<table class="listtable" style="width: 100%">
		<thead>
			<tr>
				<th>序号</th>
				<th>App版本</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="apkVersion_data">
		</tbody>
		</table>
	</div>
	
	<!-- Channel Dialog -->
	<div id="channel_dialog">
		<table class="listtable" style="width: 100%">
		<thead>
			<tr>
				<th>序号</th>
				<th>渠道名</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="channel_data">
		</tbody>
		</table>
	</div>
	
	<!-- Query Validate Dialog -->
	<div id="qv_dialog">
		<p id="qv_msg" style="color:red;text-align: center;"></p>
	</div>
	
</body> 
</html> 