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
	<link rel="stylesheet" type="text/css" href="css/alarm/main.css" />
	<style type="text/css">
		td{height: 50px;}
		th{height: 80px;color: #0091D3;}
	</style>
	<script type="text/javascript" src="js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/alarm/msgReceiver_list.js"></script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
	<tbody> 
	<tr> 
		<td class="td01"></td> 
		<td class="td02"><h3 class="topTitle fb f14">消息类型对应接收人列表</h3></td> 
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
			<dd>消息类型对应接收人列表</dd>
		</dl>
		</div>

	<form id="mainFrm" action="alarm/addMsgType.action" method="post">
	<table class="listtable" style="width: 100%">
	<thead>
		<tr>
			<th>序号</th>
			<th>编码</th>
			<th>名称</th>
			<th>电话</th>
			<th>邮箱</th>
			<th>备注</th>
			<th>创建人</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="#request.receivers" status="s" id="receiver">
		<tr>
			<td class="mytd"><s:property value="#s.index+1" /></td>
			<td class="mytd"><s:property value="code" /></td>
			<td class="mytd"><s:property value="name" /></td>
			<td class="mytd"><s:property value="mobile" /></td>
			<td class="mytd"><s:property value="email" /></td>
			<td class="mytd"><s:property value="remark" /></td>
			<td class="mytd"><s:property value="creator" /></td>
			<td class="mytd"><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
			<td class="mytd">
				<s:if test="#receiver.relStatus == 1">
					<s:checkbox name="ckIdList" fieldValue="%{#receiver.receivePersonId}" value="true"></s:checkbox>
					<!-- <input type="checkbox" name="ckIdList" value="<s:property value="#receiver.receivePersonId"/>" checked="checked"/>	-->
				</s:if>
				<s:else>
					<s:checkbox name="ckIdList" fieldValue="%{#receiver.receivePersonId}" value="false"></s:checkbox>
					<!-- <input type="checkbox" name="ckIdList" value="<s:property value="#receiver.receivePersonId"/>"/> -->
				</s:else>
			</td>
		</tr>
		</s:iterator>
	</tbody>
	</table>
		
<%-- 	<c:import url="/WEB-INF/views/common/pagelink.jsp"></c:import> --%>
	
	<div style="display:none;">
		<s:hidden id="flag" name="flag" value="%{#attr.flag}"/>
		<s:if test="#attr.flag == 'update'">
			<s:hidden id="msgTypeId" name="msgType.msgTypeId" value="%{#attr.msgType.msgTypeId}"></s:hidden>
		</s:if>
		<input type="hidden" id="msgTypeCode" name="msgType.msgTypeCode" value="${msgType.msgTypeCode}"/>
		<input type="hidden" id="msgTypeName" name="msgType.msgTypeName" value="${msgType.msgTypeName}"/>
		<input type="hidden" id="msgTypeFlag" name="msgType.flag" value="${msgType.flag}"/>
		<input type="hidden" id="sendEmailFlag" name="msgType.sendEmailFlag" value="${msgType.sendEmailFlag}"/>
		<input type="hidden" id="sendSmsFlag" name="msgType.sendSmsFlag" value="${msgType.sendSmsFlag}"/>
		<input type="hidden" id="senderStrategyCode" name="msgType.senderStrategyCode" value="${msgType.senderStrategyCode}"/>
		<input type="hidden" id="senderDelaySeconds" name="msgType.senderDelaySeconds" value="${msgType.senderDelaySeconds}"/>
		<input type="hidden" id="senderTimingSeconds" name="msgType.senderTimingSeconds" value="${msgType.senderTimingSeconds}"/>
		<input type="hidden" id="executeStrategyCode" name="msgType.executeStrategyCode" value="${msgType.executeStrategyCode}"/>
		<input type="hidden" id="executeNumber" name="msgType.executeNumber" value="${msgType.executeNumber}"/>
		<input type="hidden" id="executeDelaySeconds" name="msgType.executeDelaySeconds" value="${msgType.executeDelaySeconds}"/>
		<input type="hidden" id="failStrategyCode" name="msgType.failStrategyCode" value="${msgType.failStrategyCode}"/>
		<input type="hidden" id="failRepeatNumber" name="msgType.failRepeatNumber" value="${msgType.failRepeatNumber}"/>
		<input type="hidden" id="failDelaySeconds" name="msgType.failDelaySeconds" value="${msgType.failDelaySeconds}"/>
		<input type="hidden" id="remark" name="msgType.remark" value="${msgType.remark}"/>
		
		<s:iterator value="#request.ckIdList" var="ckId">
			<s:checkbox name="ckIdList" fieldValue="%{#ckId}" value="true"></s:checkbox>
		</s:iterator>
		
	</div>
	</form>
	<div>
	<input id="btn_ok" class="btn1 btn_common" type="button" name="ok" value="确定"/>
	<input id="btn_pre" class="btn1 btn_common" type="button" name="pre" value="上一步"/>
	<input id="btn_cancel" class="btn1 btn_common" type="button" name="cancel" value="取消"/>
	</div>
	</div> 
	</td> 
		<td class="td03">
		</td> 
	</tr> 
	</tbody> 
	</table>
</body> 
</html> 