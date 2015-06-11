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
	<link href="template/jquery-ui/css/start/jquery-ui-1.10.3.custom.min.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		td{height: 50px;}
		th{height: 80px;color: #0091D3;}
	</style>
	<script src="template/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="template/jquery-ui/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script type="text/javascript" src="template/FusionCharts/js/FusionCharts.js"></script>
	<script type="text/javascript" src="js/stat/scheduledStat_list.js?v130617"></script>
	<script type="text/javascript">
	  var basePath="<%=basePath%>";
	</script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
	<tbody> 
	<tr> 
		<td class="td01"></td> 
		<td class="td02"><h3 class="topTitle fb f14">上报数据定时调度类型统计分析报表</h3></td> 
		<td class="td03"></td> 
	</tr> 
	</tbody> 	
	</table>  
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_main"> 
	<tbody> 
	<tr> 
		<td class="td01"></td> 
		<td class="adminMain_wrap"> 
		<div class="wrapMain" style="height:800px;vertical-align: middle;"> 
		<!-- 在这里填充 -->
		<div class="adminUp_wrap" style="height:24px;margin-bottom:10px;">
		<dl class="adminPath clearfix2" style="width:50%;">
			<dt>您现在的位置：</dt>
			<dd>查询功能</dd>
			<dd>上报数据定时调度类型统计分析报表</dd>
		</dl>
		</div>
			
		<div>
		<input type="hidden" id="msg" value="<s:property value="msg"/>"/>
		</div>
		
		<div class="frame4">
		<form action="" method="post" id="queryFrm">
		<dl>
			<dd class="ddl1">
				<label for="statSchemeName">统计分析计划</label>
			</dd>
			<dd class="ddr1">
				<input type="text" id="statSchemeName" name="statSchemeName" value="" readonly="readonly"/>
				<input type="hidden" id="statSchemeId" name="statScheduledResultVo.statSchemeId" value="${statScheduledResultVo.statSchemeId}"/>
			</dd>
			<dd class="ddl2">
				<input class="btn4" type="button" id="btn_statScheme" value="选择统计分析计划"/>
			</dd>
			<dd class="ddl1">
				<label for="sourceId">上报数据来源</label>
			</dd>
			<dd class="ddr2">
				<input type="text" id="sourceId" name="statScheduledResultVo.sourceId" value="${statScheduledResultVo.sourceId}"/>
			</dd>
			<dd class="ddl1">
				<label for="scheduledTime">统计分析时间</label>
			</dd>
			<dd class="ddr2">
				<input class="Wdate" name="statScheduledResultVo.scheduledTime" id="scheduledTime" type="text" size="10"
					onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd', maxDate:'%y-%M-%d'});"
					readonly="readonly" value="${statScheduledResultVo.scheduledTime}"></input>
			</dd>
			
		</dl>
		<dl>
			<dd class="ddl1" style="float:right;margin-right:5%;">
				<input class="btn4" type="button" id="btn_query" value="查询"/>
			</dd>
		</dl>
			
		</form>
		</div>
		
		<div id="chartContainer"></div>
		
	</div> 
	</td> 
		<td class="td03"></td> 
	</tr> 
	</tbody> 
	</table>
	
	<!-- StatType Dialog -->
	<div id="ss_dialog">
		<table class="listtable" style="width: 100%">
		<thead>
			<tr>
				<th>序号</th>
				<th>计划编码	</th>
				<th>计划名称</th>
				<th>统计策略</th>
				<th>调度统计分析定时时间</th>
				<th>快照统计分析间隔时间(秒)</th>
				<th>上报数据类型</th>
				<th>统计维度</th>
				<th>启用状态</th>
				<th>创建时间</th>
				<th>创建人</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="ss_data">
		</tbody>
		</table>
	</div>
	
	<!-- Query Validate Dialog -->
	<div id="qv_dialog">
		<p id="qv_msg" style="color:red;text-align: center;"></p>
	</div>
	
</body> 
</html> 