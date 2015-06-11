<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<title>系统参数列表</title> 
<style type="text/css">
td{
height: 50px;

}

th{
height: 80px;
color: #0091D3;
}
</style>
<link href="<%=request.getContextPath() %>/template/css/base.css" rel="stylesheet" type="text/css" /> 
<link href="<%=request.getContextPath() %>/template/css/common.css" rel="stylesheet" type="text/css" /> 
<link href="<%=request.getContextPath() %>/template/css/page_admin_main.css" rel="stylesheet" type="text/css" /> 
<link href="<%=request.getContextPath() %>/template/css/additional.css" rel="stylesheet" type="text/css" /> 
<script src="<%=request.getContextPath()%>/template/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/template/javascript/jquery-1.3.2.min.js" type="text/javascript"></script>
</head>
<body> 
<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
<tbody> 
<tr> 
<td class="td01"></td> 
<td class="td02"><h3 class="topTitle fb f14">参数列表</h3></td> 
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
	<dd>系统管理</dd>
	<dd>系统参数列表</dd>
</dl>
</div>


<form action="<%=request.getContextPath()%>/showSysParam.action" id="mainFrm"  method="post">

<div>
<table class="mytable">
	<tr>
		<td>参数名: </td>
		<td><s:textfield name="paramName"></s:textfield></td>
	</tr>
</table>
<input type="button" value="  搜  索  " onclick="query()"/>&nbsp;&nbsp; 
<input type="button" value=" 新  增  " onclick="addItem()"/>
<input type="hidden" id="msg" value="<s:property value="msg"/>"/>
</div>
<table class="listtable" style="width: 100%">
	<thead>
		<tr>
			<th>序号</th>
			<th>参数名</th>
			<th>参数值</th>
			<th>参数描述</th>
			<th>创建人ID</th>
			<th>创建时间</th>
			<th>修改人ID</th>
			<th>修改时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="sysParamList" var="item" status="s">
		<tr>
			<td ><s:property value="#s.index+1" /></td>
			<td >${item.paramName}</td>
			<td >${item.paramVal}</td>
			<td >${item.paramDesc}</td>
			<td ><s:if test="#item.createor!=0">${item.createor}</s:if>&nbsp;</td>
			<td ><s:date name="#item.createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
			<td ><s:if test="#item.modifier!=0">${item.modifier}</s:if>&nbsp;</td>
			<td ><s:date name="#item.modifyTime" format="yyyy-MM-dd HH:mm:ss" /></td>
			<td ><a href="<%=request.getContextPath()%>/preUpdateSysParam.action?name=${item.paramName}">修改<a/></td>
		</tr>
		</s:iterator>
		<s:if test="sysParamList.isEmpty()"><td colspan="9">对不起，没有查询到相关记录！</td></s:if>
	</tbody>
	 
</table>
</form>

</div> 
</td> 
<td class="td03"></td> 
</tr> 
</tbody> 
</table>
<script type="text/javascript">
	function addItem(){
		location.href = "<%=request.getContextPath()%>/addSysParam.action";
	}
	
	function query(){
		var frm = $("#mainFrm");
		frm.attr("action", "<%=request.getContextPath()%>/showSysParam.action");
		frm.submit();
	}
	
	$(function() {
		var msg = $("#msg").val();
		if(msg != '' && msg != null)
			alert(msg);
	});
</script>	
</body> 
</html> 