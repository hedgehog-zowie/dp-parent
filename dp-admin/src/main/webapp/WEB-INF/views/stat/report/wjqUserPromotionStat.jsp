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
		td{height: 50px;}
		th{height: 80px;color: #0091D3;}
	</style>
	<script src="template/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/stat/wjqUserPromotionStat.js?v=130823"></script>
	<script type="text/javascript">
	  var basePath="<%=basePath%>";
	</script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
	<tbody> 
	<tr> 
		<td class="td01"></td> 
		<td class="td02"><h3 class="topTitle fb f14">玩机圈用户推广报表</h3></td> 
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
			<dd>玩机圈用户推广报表</dd>
		</dl>
		
		</div>
		
		<div>
		<input type="hidden" id="msg" value="<s:property value="msg"/>"/>
		</div>
		
		<div class="frame4" style="height:70px;">
		<form action="" method="post" id="queryFrm">
		<dl style="overflow:hidden;">
			<dd class="ddl1">
				<label for="userId">推广者ID</label>
			</dd>
			<dd class="ddr1">
				<input name="statParams['userId']" id="userId" type="text" value="${statParams['userId']}"></input>
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
			<dd class="ddl1" style="width:5%;">
				<label for="isGroupedByDay">按天分组</label>
			</dd>
			<dd class="ddr1" style="width:2%;margin:0;">
				<input type="checkbox" id="isGroupedByDay" name="statParams['isGroupedByDay']" value="groupedByDay"/>
			</dd>
			
		</dl>
		<dl style="padding-top: 10px;">
			<dd class="ddl1" >
				<input class="btn4" type="button" id="btn_query" value="查询"/>
			</dd>
			<dd class="ddl4" >
				<input class="btn6" type="button" id="btn_exportExcel" value="导出EXCEL"/>
			</dd>
			<dd class="ddl4" >
				<input class="btn6" type="button" id="btn_preview" value="汇总预览"/>
			</dd>
		</dl>
		<div style="display:none;">
			<s:hidden id="flag_h" name="flag" value="%{#parameters.flag}"></s:hidden>
		</div>
		</form>
		</div>
		
		<div style="display:none;">
			<s:iterator value="statParams" id="paramsEntry">
				<s:hidden id="%{#paramsEntry.key}_h" name="%{#paramsEntry.key}" value="%{#paramsEntry.value}"></s:hidden>
			</s:iterator>
		</div>
		
	<table class="listtable" style="width: 100%">
	<caption class="cp1">玩机圈用户推广报表</caption>
	<thead>
		<tr>
			<th>序号</th>
			<th>统计日期</th>
			<th>用户ID</th>
			<th>用户名</th>
			<th>注册数</th>
			<th>PV</th>
			<th>UV</th>
			<th>IP</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="#request.userPromotions" status="s" id="userPromotion">
		<tr>
			<td class="mytd"><s:property value="rowNum" /></td>
			<td class="mytd"><s:property value="bizDate" /></td>
			<td class="mytd"><s:property value="userId" /></td>
			<td class="mytd"><s:property value="userName" /></td>
			<td class="mytd"><s:property value="regNum" /></td>
			<td class="mytd"><s:property value="pv" /></td>
			<td class="mytd"><s:property value="uv" /></td>
			<td class="mytd"><s:property value="ip" /></td>
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