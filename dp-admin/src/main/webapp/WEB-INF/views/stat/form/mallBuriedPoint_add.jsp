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
	<link rel="stylesheet" type="text/css" href="template/css/base.css" /> 
	<link rel="stylesheet" type="text/css" href="template/css/common.css" /> 
	<link rel="stylesheet" type="text/css" href="template/css/page_admin_main.css" /> 
	<link rel="stylesheet" type="text/css" href="template/css/additional.css" /> 
	<link rel="stylesheet" type="text/css" href="css/alarm/main.css" />	
	<script src="template/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript" src="/js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/js/stat/mallBuriedPoint_add.js?v=131210"></script>
	<script type="text/javascript">
	  var basePath="<%=basePath%>";
	</script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
		<tbody> 
			<tr> 
				<td class="td01"></td> 
				<td class="td02"><h3 class="topTitle fb f14">商城前端埋点位置</h3></td> 
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
		<div class="adminUp_wrap" style="height:24px;margin-bottom:10px;">
			<dl class="adminPath clearfix">
				<dt>您现在的位置：</dt>
				<dd>报表管理</dd>
				<dd>商城运营报表</dd>
				<dd>商城前端埋点位置编辑页</dd>
			</dl>
		</div>

		<form action="" id="mainFrm" method="post">
			
			<div class="frame2" >
				<div class="inFrame1">
				<dl>
					<dt><label class="lable1" for="website">站点名称</label></dt>
					<dd>
						<s:textfield cssClass="txt1" id="website" name="mallBuriedPointSite.website" value="%{#attr.mallBuriedPointSite.website}"></s:textfield>
					</dd>
					<dt><label class="lable1" for="pageName">页面名称</label></dt>
					<dd>
						<s:textfield cssClass="txt1" id="pageName" name="mallBuriedPointSite.pageName" value="%{#attr.mallBuriedPointSite.pageName}"></s:textfield>
					</dd>
				</dl>
				<dl>
					<dt><label class="lable1" for="pagePosition">页面位置</label></dt>
					<dd>
						<s:textfield cssClass="txt1" id="pagePosition" name="mallBuriedPointSite.pagePosition" value="%{#attr.mallBuriedPointSite.pagePosition}"></s:textfield>
					</dd>
					<dt><label class="lable1" for="pointFlag">埋点标示</label></dt>
					<dd>
						<s:textfield cssClass="txt1" id="pointFlag" name="mallBuriedPointSite.pointFlag" value="%{#attr.mallBuriedPointSite.pointFlag}"></s:textfield>
					</dd>
				</dl>
				<dl>
					<dt class="lable1"><label for="bizDate">埋点时间</label></dt>
					<dd>
						<input class="txt1 Wdate1" name="mallBuriedPointSite.bizDate" id="bizDate" type="text" size="10"
							readonly="readonly" value="${mallBuriedPointSite.bizDate}"></input>
					</dd>
					<dt><label class="lable1" for="pointType">埋点类型</label></dt>
					<dd>
						<s:textfield cssClass="txt1" id="pointType" name="mallBuriedPointSite.pointType" value="%{#attr.mallBuriedPointSite.pointType}"></s:textfield>
					</dd>
				</dl>
				<dl>
					<dt><label class="lable1" for="remark">备注</label></dt>
					<dd>
						<s:textfield cssClass="txt1" id="remark" name="mallBuriedPointSite.remark" value="%{#attr.mallBuriedPointSite.remark}"></s:textfield>
					</dd>
				</dl>
				</div>
				
				<div style="width:100%">
					<input id="btn_ok" class="btn1 btn_ok" type="button" name="next" value="确定 "></input>
					<input id="btn_cancel" class="btn1 btn_cancel" type="button" name="cancel" value="取消"></input>
				</div>
			</div>
			<div style="display:none;">
				<s:hidden id="flag" name="flag" value="%{#attr.flag}"></s:hidden>
				<s:if test="#attr.flag == 'update'">
					<s:hidden id="id" name="mallBuriedPointSite.id" value="%{#attr.mallBuriedPointSite.id}"></s:hidden>
				</s:if>
				<input type="hidden" id="curPage" name="page.currentPage" value="${page.currentPage}"/>
			</div>
		</form>

		</div> 
		</td> 
		</tr> 
	</tbody> 
	</table>
</body> 
</html> 