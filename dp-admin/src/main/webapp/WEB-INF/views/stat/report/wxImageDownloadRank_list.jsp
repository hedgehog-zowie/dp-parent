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
	<script type="text/javascript" src="js/stat/wxImageDownloadRank_list.js?v=130607"></script>
	<script type="text/javascript">
	  var basePath="<%=basePath%>";
	</script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
	<tbody> 
	<tr> 
		<td class="td01"></td> 
		<td class="td02"><h3 class="topTitle fb f14">微信公众帐号图片下载排行</h3></td> 
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
			<dd>微信公众帐号图片下载排行</dd>
		</dl>
		
		</div>
		
		<div>
		<input type="hidden" id="msg" value="<s:property value="msg"/>"/>
		</div>
		
		<div class="frame4" style="height:35px;">
		<form action="" method="post" id="queryFrm">
			<div style="display:none;">
			    <input  type="hidden" id="adId" name="adId" value="${adId}"></input>
			    <!-- 
				<input  type="hidden" id="bizDate" name="bizDate" value="${bizDate}"/>
			    <input  type="hidden" id="adName" name="adName" value="${adName}"/>
			    <input  type="hidden" id="channelName" name="channelName" value="${channelName}"/>
			     -->
			</div>
		<dl>
			<dd class="ddl1">
				<label for="code">截止日期</label>
			</dd>
			<dd class="ddr1">
				<input class="Wdate1" name="endTime" id="endTime" type="text" size="10"
					onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});"
					readonly="readonly" value="${endTime}"></input>
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
			<th>名称</th>
			<th>图片</th>
			<th>标签</th>
			<th>下发量</th>
			<th>点击量</th>
			<th>点击率</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="wxImageDownloadRanks" status="s" id="wxImageDownloadRank">
		<tr>
			<td class="mytd">${imgName}</td>
			<td class="mytd"><img src="${imgUrl}"  alt="" width="200px" height="100px"/></td>
			<td class="mytd">${tagName}</td>
			<td class="mytd">${showCount}</td>
			<td class="mytd">${clickCount}</td>
			<td class="mytd">${clickRate}</td>
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