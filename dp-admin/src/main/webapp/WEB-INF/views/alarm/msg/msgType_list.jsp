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
	<script type="text/javascript" src="js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/alarm/msgType_list.js?v=130422"></script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
	<tbody> 
	<tr> 
		<td class="td01"></td> 
		<td class="td02"><h3 class="topTitle fb f14">消息类型列表</h3></td> 
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
			<dd>查询功能</dd>
			<dd>消息类型列表</dd>
		</dl>
		<label class="lable2" >操作&nbsp;&nbsp;&gt;</label>
		<input type="button" class="btn2" id="btn_add" value="新增消息类型"/>
		</div>

		<div>
		<input type="hidden" id="msg" value="<s:property value="msg"/>"/>
		</div>
	<table class="listtable" style="width: 100%">
	<thead>
		<tr>
			<th>序号</th>
			<th>编码</th>
			<th>名称</th>
			<th>是否发送邮件</th>
			<th>是否发送短信</th>
			<th>发送策略</th>
			<th>延时发送时间(秒)</th>
			<th>定时发送时间(秒)</th>
			<th>执行策略</th> 
			<th>执行次数</th>
			<th>执行延时时间(秒)</th>
			<th>失败策略</th>
			<th>失败重试次数</th>
			<th>失败重试延时时间(秒)</th>
			<th>对应接收人</th>
			<th>是否启用</th>
			<th>创建人</th>
			<th>创建时间</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="#request.msgTypes" status="s" id="msgType">
		<tr>
			<td class="mytd"><s:property value="#s.index+1" /></td>
			<td class="mytd"><s:property value="msgTypeCode" /></td>
			<td class="mytd"><s:property value="msgTypeName" /></td>
			<td class="mytd">
				<s:if test="#msgType.sendEmailFlag =='0'">不发送</s:if>
				<s:else>发送</s:else>
			</td>
			<td class="mytd">
				<s:if test="#msgType.sendSmsFlag == '0'">不发送</s:if>
				<s:else>发送</s:else>
			</td>
			<td class="mytd">
				<s:if test="#msgType.senderStrategyCode == '11'">立即发送</s:if>
				<s:elseif test="#msgType.senderStrategyCode == '12'">延迟发送</s:elseif>
				<s:elseif test="#msgType.senderStrategyCode == '13'">定时发送</s:elseif>
				<s:elseif test="#msgType.senderStrategyCode == '14'">循环发送</s:elseif>
				<s:else>异常</s:else>
			</td>
			<td class="mytd"><s:property value="senderDelaySeconds" /></td>
			<td class="mytd"><s:property value="senderTimingSeconds" /></td>
			<td class="mytd">
				<s:if test="#msgType.executeStrategyCode == '21'">一次执行</s:if>
				<s:elseif test="#msgType.executeStrategyCode == '22'">多次执行</s:elseif>
			</td>
			<td class="mytd"><s:property value="executeNumber" /></td>
			<td class="mytd"><s:property value="executeDelaySeconds" /></td>
			<td class="mytd">
				<s:if test="#msgType.failStrategyCode == '31'">不处理</s:if>
				<s:elseif test="#msgType.failStrategyCode == '32'">立即重试N次</s:elseif>
				<s:elseif test="#msgType.failStrategyCode == '33'">延时重试N次</s:elseif>
				<s:else>异常</s:else>
			</td>
			<td class="mytd"><s:property value="failRepeatNumber" /></td>
			<td class="mytd"><s:property value="failDelaySeconds" /></td>
			<td class="mytd">
				<s:set name="receivers" value="''"></s:set>
				<s:iterator value="#msgType.receivePersonList" id="receiver" status="r">
					<s:if test="#r.index == 0">
						<s:set name="receivers" value="#receivers + #receiver.code"></s:set>
					</s:if>
					<s:else>
						<s:set name="receivers" value="#receivers + ', ' + #receiver.code"></s:set>
					</s:else>
				</s:iterator>
				<a name="a_receivers" href="javascript:void(0);" relval="<s:property value="msgTypeId"/>">
					<s:property value="#receivers"/>
				</a>
			</td>
			<td class="mytd">
				<s:if test="#msgType.flag == 0">禁用</s:if>
				<s:else>启用</s:else>
			</td>
			<td class="mytd"><s:property value="creator" /></td>
			<td class="mytd"><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
			<td class="mytd"><s:property value="remark" /></td>
			<td class="mytd">
				<!--  
				<a name="a_add" href="javascript:void(0);">新增</a>
				&nbsp;&nbsp;
				-->
				<a name="a_update" href="javascript:void(0);" relval="<s:property value="msgTypeId"/>">修改</a>
				&nbsp;&nbsp;
				<a name="a_remove" href="javascript:void(0);" relval="<s:property value="msgTypeId"/>">删除</a>
			</td>
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