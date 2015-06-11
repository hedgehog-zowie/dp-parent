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
	<script type="text/javascript" src="template/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/stat/mallBuriedPoint_list.js?v=131210"></script>
	<script type="text/javascript">
	  var basePath="<%=basePath%>";
	</script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
	<tbody> 
	<tr> 
		<td class="td01"></td> 
		<td class="td02"><h3 class="topTitle fb f14">商城前端埋点位置列表</h3></td> 
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
		<dl class="adminPath clearfix2" style="width:85%;">
			<dt>您现在的位置：</dt>
			<dd>报表管理</dd>
			<dd>商城运营报表</dd>
			<dd>商城前端埋点位置列表</dd>
		</dl>
		<label class="lable2" >操作&nbsp;&nbsp;&gt;</label>
		<input type="button" class="btn7" id="btn_add" value="新增埋点位置"/>
		</div>

		<div>
		<input type="hidden" id="msg" value="<s:property value="msg"/>"/>
		</div>
		
		<div class="frame4" style="height:70px;">
		<form action="" method="post" id="queryFrm">
		<dl>
			<dd class="ddl1">
				<label for="website">站点</label>
			</dd>
			<dd class="ddr1">
				<input name="queryParams['website']" id="website" type="text" value="${queryParams['website']}"></input>
			</dd>
			<dd class="ddl1">
				<label for="pageName">页面</label>
			</dd>
			<dd class="ddr1">
				<input name="queryParams['pageName']" id="pageName" type="text" value="${queryParams['pageName']}"></input>
			</dd>
			<dd class="ddl1">
				<label for="pagePosition">页面位置</label>
			</dd>
			<dd class="ddr1">
				<input name="queryParams['pagePosition']" id="pagePosition" type="text" value="${queryParams['pagePosition']}"></input>
			</dd>
			<dd class="ddl1">
				<label for="pointFlag">埋点标示</label>
			</dd>
			<dd class="ddr1">
				<input name="queryParams['pointFlag']" id="pointFlag" type="text" value="${queryParams['pointFlag']}"></input>
			</dd>
			<dd class="ddl1">
				<label for="pointType">埋点类型</label>
			</dd>
			<dd class="ddr1">
				<input name="queryParams['pointType']" id="pointType" type="text" value="${queryParams['pointType']}"></input>
			</dd>
			<dd class="ddl1">
				<label for="beginDate">埋点日期开始</label>
			</dd>
			<dd class="ddr1">
				<input class="Wdate1" name="queryParams['beginDate']" id="beginDate" type="text" size="10"
					readonly="readonly" value="${queryParams['beginDate']}"></input>
			</dd>
			<dd class="ddl1">
				<label for="endDate">埋点日期结束</label>
			</dd>
			<dd class="ddr1">
				<input class="Wdate1" name="queryParams['endDate']" id="endDate" type="text" size="10"
					readonly="readonly" value="${queryParams['endDate']}"></input>
			</dd>
			<dd class="ddl1">
				<input class="btn4" type="button" id="btn_query" value="查询"/>
			</dd>
		</dl>
		<div style="display: none;">
			<s:hidden id="flag_h" name="flag" value="%{#parameters.flag}"></s:hidden>
		</div>			
		</form>
		</div>
		
		<div style="display: none;">
			<s:iterator value="queryParams" id="paramsEntry">
				<s:hidden id="%{#paramsEntry.key}_h" name="%{#paramsEntry.key}" value="%{#paramsEntry.value}"></s:hidden>
			</s:iterator>
		</div>
		
	<table class="listtable" style="width: 100%">
	<thead>
		<tr>
			<th>序号</th>
			<th>站点</th>
			<th>页面</th>
			<th>页面位置</th>
			<th>埋点标示</th>
			<th>埋点时间</th>
			<th>埋点类型</th>
			<th>备注</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="#request.mallBuriedPointSites" status="s" id="mallBuriedPointSite">
		<tr>
<%-- 			<td class="mytd"><s:property value="#s.index+1" /></td> --%>
			<td class="mytd"><s:property value="rowNum" /></td>
			<td class="mytd"><s:property value="website" /></td>
			<td class="mytd"><s:property value="pageName" /></td>
			<td class="mytd"><s:property value="pagePosition" /></td>
			<td class="mytd"><s:property value="pointFlag" /></td>
			<td class="mytd"><s:property value="bizDate" /></td>
			<td class="mytd"><s:property value="pointType" /></td>
			<td class="mytd"><s:property value="remark" /></td>
			<td class="mytd"><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss.sss"/></td>
			<td class="mytd">
				<!--  
				<a name="a_add" href="javascript:void(0);">新增</a>
				&nbsp;&nbsp;
				-->
				<a name="a_update" href="javascript:void(0);" relval="<s:property value="id"/>">修改</a>
				&nbsp;&nbsp;
				<a name="a_remove" href="javascript:void(0);" relval="<s:property value="id"/>">删除</a>
			</td>
		</tr>
		</s:iterator>
	</tbody>
	</table>
		<c:import url="/WEB-INF/views/common/pagelink.jsp"></c:import>
		<div style="display:none;">
			<input type="hidden" id="curPage" name="curPage" value="${page.currentPage}"/>
		</div>
	</div> 
	</td> 
		<td class="td03"></td> 
	</tr> 
	</tbody> 
	</table>
</body> 
</html> 