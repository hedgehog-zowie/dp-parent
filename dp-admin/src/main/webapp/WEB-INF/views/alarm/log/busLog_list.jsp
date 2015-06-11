<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
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
	<link href="css/alarm/main.css" rel="stylesheet" type="text/css"/>
	<style type="text/css">
		td{height: 50px;}
		th{height: 80px;color: #0091D3;}
	</style>
	<script type="text/javascript" src="js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/alarm/busLog_list.js?v=130503"></script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
	<tbody> 
	<tr> 
		<td class="td01"></td> 
		<td class="td02"><h3 class="topTitle fb f14">业务日志列表</h3></td> 
		<td class="td03"></td> 
	</tr> 
	</tbody> 	
	</table>  
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_main"> 
	<tbody> 
	<tr> 
		<td class="td01"></td> 
		<td class="adminMain_wrap"> 
		<div class="wrapMain" style="height:1000px;vertical-align: middle;"> 
		<!-- 在这里填充 -->
		<div class="adminUp_wrap" style="height:24px;margin-bottom:10px;">
		<dl class="adminPath clearfix">
			<dt>您现在的位置：</dt>
			<dd>查询功能</dd>
			<dd>业务日志列表</dd>
		</dl>
		</div>

		<div>
		<input type="hidden" id="msg" value="<s:property value="msg"/>"/>
		</div>
	<table class="listtable" style="width: 100%">
	<thead>
		<tr>
			<th>序号</th>
			<th>操作员ID</th>
			<th>业务类型</th>
			<th>操作前内容</th>
			<th>操作后内容</th>
			<th>业务执行结果</th>
			<th>业务执行时间</th>
			<th>备注</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="#request.busLogs" status="s" id="busLog">
		<tr>
			<td class="mytd"><s:property value="#s.index+1" /></td>
			<td class="mytd"><s:property value="optId" /></td>
			<td class="mytd"><s:property value="busType" /></td>
			<td class="mytd"><s:property value="beforeContent" /></td>
			<td class="mytd"><s:property value="afterContent" /></td>
			<td class="mytd"><s:property value="executResult" /></td>
			<td class="mytd"><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
			<td class="mytd"><s:property value="remark" /></td>
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