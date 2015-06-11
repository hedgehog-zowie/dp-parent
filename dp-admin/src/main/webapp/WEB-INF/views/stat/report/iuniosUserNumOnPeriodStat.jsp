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
	<script type="text/javascript" src="js/stat/iuniosUserNumOnPeriodStat.js?v=140214"></script>
	<script type="text/javascript">
	  var basePath="<%=basePath%>";
	</script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
	<tbody> 
	<tr> 
		<td class="td01"></td> 
		<td class="td02"><h3 class="topTitle fb f14">IUNI OS用户数据按区间统计报表</h3></td> 
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
			<dd>IUNI OS运营报表</dd>
			<dd>IUNI OS用户数据按区间统计报表</dd>
		</dl>
		
		</div>
		
		<div>
		<input type="hidden" id="msg" value="<s:property value="msg"/>"/>
		</div>
		
		<div class="frame4" style="height:135px;">
		<form action="" method="post" id="queryFrm">
		<dl>
			<dd class="ddl1">
				<label for="appNames">机型</label>
			</dd>
			<dd class="ddr3">
				<input type="text" id="appNames" name="appNames" value="${statParams['appNames']}" readonly="readonly" style="width:95%"/>
				<input type="hidden" id="apps" name="statParams['appNames']" value="${statParams['appNames']}"/>
			</dd>
			<dd class="ddl3">
				<input class="btn4" type="button" id="btn_appNames" value="机型选择"/>
			</dd>
			
		</dl>
		<dl>
			<dd class="ddl1">
				<label for="apkVersionNames">版本</label>
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
				<label for="channelName">渠道名</label>
			</dd>
			<dd class="ddr4">
				<select id="channelName" class="select1" name="statParams['channelName']">
					<s:if test="statParams['appName'] == 'IUNI OS'">
						<option value="IUNI OS" selected="selected">
							IUNI OS
						</option>
					</s:if>
					<s:else>
						<option value="IUNI OS" selected="selected">
							IUNI OS
						</option>
					</s:else>
				</select>
			</dd>
			<dd class="ddl1">
				<label for="periodType">统计区间类型</label>
			</dd>
			<dd class="ddr4">
				<select id="periodType" class="select1" name="statParams['periodType']">
					<s:if test="statParams['periodType'] == 'daily'">
						<option value="daily" selected="selected">
							每日
						</option>
						<option value="weekly">
							每周
						</option>
						<option value="monthly">
							每月
						</option>
						<option value="30days">
							每30天
						</option>
					</s:if>
					<s:elseif test="statParams['periodType'] == 'weekly'">
						<option value="daily">
							每日
						</option>
						<option value="weekly" selected="selected">
							每周
						</option>
						<option value="monthly">
							每月
						</option>
						<option value="30days">
							每30天
						</option>
					</s:elseif>
					<s:elseif test="statParams['periodType'] == 'monthly'">
						<option value="daily">
							每日
						</option>
						<option value="weekly">
							每周
						</option>
						<option value="monthly" selected="selected">
							每月
						</option>
						<option value="30days">
							每30天
						</option>
					</s:elseif>
					<s:elseif test="statParams['periodType'] == '30days'">
						<option value="daily">
							每日
						</option>
						<option value="weekly">
							每周
						</option>
						<option value="monthly">
							每月
						</option>
						<option value="30days"  selected="selected">
							每30天
						</option>
					</s:elseif>
					<s:else>
						<option value="daily" selected="selected">
							每日
						</option>
						<option value="weekly">
							每周
						</option>
						<option value="monthly">
							每月
						</option>
						<option value="30days">
							每30天
						</option>
					</s:else>
				</select>
			</dd>
			<dd class="ddl1">
				<label for="beginDate">统计日期开始</label>
			</dd>
			<dd class="ddr4">
				<input class="Wdate1" name="statParams['beginDate']" id="beginDate" type="text" size="10"
					readonly="readonly" value="${statParams['beginDate']}"></input>
			</dd>
			<dd class="ddl1">
				<label for="endDate">统计日期结束</label>
			</dd>
			<dd class="ddr4">
				<input class="Wdate1" name="statParams['endDate']" id="endDate" type="text" size="10"
					readonly="readonly" value="${statParams['endDate']}"></input>
			</dd>
			<dd class="ddl1">
				<label for=""></label>
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
			<th>统计时间</th>
			<th>用户数</th>
			<th>新增用户数</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="#request.dailyUserStatList" status="s" id="dailyUserStat">
		<tr>
			<td class="mytd"><s:property value="rowNum" /></td>
			<td class="mytd"><s:property value="statDate" /></td>
			<td class="mytd"><s:property value="userNum" /></td>
			<td class="mytd"><s:property value="newUserNum" /></td>
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
	
	<!-- AppName Dialog -->
	<div id="appName_dialog">
		<table class="listtable" style="width: 100%">
		<thead>
			<tr>
				<th>序号</th>
				<th>机型</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="appName_data">
		</tbody>
		</table>
	</div>
	
	<!-- ApkVersion Dialog -->
	<div id="apkVersion_dialog">
		<table class="listtable" style="width: 100%">
		<thead>
			<tr>
				<th>序号</th>
				<th>OS版本</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="apkVersion_data">
		</tbody>
		</table>
	</div>
	
	<!-- Query Validate Dialog -->
	<div id="qv_dialog">
		<p id="qv_msg" style="color:red;text-align: center;"></p>
	</div>
	
</body> 
</html> 