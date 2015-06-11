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
	<script type="text/javascript" src="js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/alarm/receiver_add.js?v=130418"></script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
		<tbody> 
			<tr> 
				<td class="td01"></td> 
				<td class="td02"><h3 class="topTitle fb f14">接收人信息</h3></td> 
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
				<dd>管理配置</dd>
				<dd>接收人信息</dd>
			</dl>
		</div>
		
		<div class="errorspace">
			<s:fielderror />
		</div>
		<form action="alarm/addReceiver.action" id="mainFrm" method="post">
			
			<div class="frame1" >
				<div class="inFrame1">
				<dl>
					<dt><label class="lable1" for="code">接收人编码</label></dt>
					<dd><input class="txt1" type="text" id="code" name="receivePerson.code" value="${receiver.code}"/></dd>
				</dl>
				<dl>
					<dt><label class="lable1" for="name">接收人名称</label></dt>
					<dd><input class="txt1" type="text" id="name" name="receivePerson.name" value="${receiver.name}"/></dd>
				</dl>
				<dl>
					<dt><label class="lable1" for="mobile">接收人电话</label></dt>
					<dd><input class="txt1" type="text" id="mobile" name="receivePerson.mobile" value="${receiver.mobile}"/></dd>
				</dl>
				<dl>
					<dt><label class="lable1" for="email">接收人邮箱</label></dt>
					<dd><input class="txt1" type="text" id="email" name="receivePerson.email" value="${receiver.email}"/></dd>
				</dl>
				<dl>
					<dt><label class="lable1" for="remark">备注</label></dt>
					<dd><input class="txt1" type="text" id="remark" name="receivePerson.remark" value="${receiver.remark}"/></dd>
				</dl>
				</div>
				<div style="width:100%">
					<input id="btn_ok" class="btn1 btn_ok" type="button" name="ok" value="确定"></input>
					<input id="btn_cancel" class="btn1 btn_cancel" type="button" name="cancel" value="取消"></input>
				</div>
			</div>
			<div style="display:none;">
				<input type="hidden" id="id" name="receivePerson.receivePersonId" value="${receiver.receivePersonId}"/>
				<input type="hidden" id="flag" name="flag" value="${flag}" />
			</div>
		</form>
			
		</div> 
		</td> 
		</tr> 
	</tbody> 
	</table>
</body> 
</html> 