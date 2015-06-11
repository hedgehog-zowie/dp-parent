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
	<script type="text/javascript" src="/js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/js/alarm/msgType_add.js?v=130419"></script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
		<tbody> 
			<tr> 
				<td class="td01"></td> 
				<td class="td02"><h3 class="topTitle fb f14">消息类型</h3></td> 
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
				<dd>消息类型</dd>
			</dl>
		</div>

		<form action="alarm/relReceiverView.action" id="mainFrm" method="post">
			
			<div class="frame2" >
				<div class="inFrame1">
				<dl>
					<dt><label class="lable1" for="code">类型编码</label></dt>
					<dd>
						<s:textfield cssClass="txt1" id="code" name="msgType.msgTypeCode" value="%{#attr.msgType.msgTypeCode}"></s:textfield>
						<!--<input class="txt1" type="text" id="i_code" name="msgType.msgTypeCode" value="${msgType.msgTypeCode}"/>  -->
					</dd>
					<dt><label class="lable1" for="name">类型名称</label></dt>
					<dd><input class="txt1" type="text" id="name" name="msgType.msgTypeName" value="${msgType.msgTypeName}"/></dd>
				</dl>
				<dl>
					<dt><label class="lable1" for="isUsed">消息类型是否启用</label></dt>
					<dd>
						<select class="select1" id="isUsed" name="msgType.flag">
							<c:choose>
								<c:when test="${msgType.flag == 0}">
								<option value="0" selected="selected">禁用</option>
								<option value="1">启用</option>
								</c:when>
								<c:otherwise>
								<option value="0">禁用</option>
								<option value="1" selected="selected">启用</option>
								</c:otherwise>
							</c:choose>
						</select>
					</dd>
					<dt><label class="lable1" for="emailAbled">是否发送邮件</label></dt>
					<dd>
						<select class="select1" id="emailAbled" name="msgType.sendEmailFlag">
							<c:choose>
								<c:when test="${msgType.sendEmailFlag == 1}">
								<option value="0">不发送</option>
								<option value="1" selected="selected">发送</option>
								</c:when>
								<c:otherwise>
								<option value="0" selected="selected">不发送</option>
								<option value="1">发送</option>
								</c:otherwise>
							</c:choose>
						</select>
					</dd>
				</dl>
				<dl>
					<dt><label class="lable1" for="msgAbled">是否发送短信</label></dt>
					<dd>
						<select class="select1" id="msgAbled" name="msgType.sendSmsFlag">
							<s:if test="#attr.msgType.sendSmsFlag == 1">
								<option value="0">不发送</option>
								<option value="1" selected="selected">发送</option>
							</s:if>
							<s:else>
								<option value="0" selected="selected">不发送</option>
								<option value="1">发送</option>
							</s:else>
						</select>
					</dd>
					<dt><label class="lable1" for="sendWay">发送策略</label></dt>
					<dd>
						<select class="select1" id="sendWay" name="msgType.senderStrategyCode">
							<s:if test="#attr.msgType.senderStrategyCode == 12">
								<option value="11">立即发送</option>
								<option value="12" selected="selected">延迟发送</option>
								<option value="13">定时发送</option>
								<option value="14">循环发送</option>
							</s:if>
							<s:elseif test="#attr.msgType.senderStrategyCode == 13">
								<option value="11">立即发送</option>
								<option value="12">延迟发送</option>
								<option value="13" selected="selected">定时发送</option>
								<option value="14">循环发送</option>
							</s:elseif>
							<s:elseif test="#attr.msgType.senderStrategyCode == 14">
								<option value="11">立即发送</option>
								<option value="12">延迟发送</option>
								<option value="13">定时发送</option>
								<option value="14" selected="selected">循环发送</option>
							</s:elseif>
							<s:else>
								<option value="11" selected="selected">立即发送</option>
								<option value="12">延迟发送</option>
								<option value="13">定时发送</option>
								<option value="14">循环发送</option>
							</s:else>
						</select>
					</dd>
				</dl>
				<dl>
					<dt><label class="lable1" for="delaySendTime">延时发送时间</label></dt>
					<dd><input class="txt1" type="text" id="delaySendTime" name="msgType.senderDelaySeconds" value="${msgType.senderDelaySeconds}" /></dd>
					<dt><label class="lable1" for="fixedSendTime">定时发送时间</label></dt>
					<dd><input class="txt1" type="text" id="fixedSendTime" name="msgType.senderTimingSeconds" value="${msgType.senderTimingSeconds}" /></dd>
				</dl>
				<dl>
					<dt><label class="lable1" for="executeWay">执行策略</label></dt>
					<dd>
						<select class="select1" id="executeWay" name="msgType.executeStrategyCode">
							<s:if test="#attr.msgType.executeStrategyCode == 22">
								<option value="21">一次执行</option>
								<option value="22" selected="selected">多次执行</option>
							</s:if>
							<s:else>
								<option value="21" selected="selected">一次执行</option>
								<option value="22">多次执行</option>
							</s:else>
						</select>
					</dd>
					<dt><label class="lable1" for="executeNum">执行次数</label></dt>
					<dd><input class="txt1" type="text" id="executeNum" name="msgType.executeNumber" value="${msgType.executeNumber}"/></dd>
				</dl>
				<dl>
					<dt><label class="lable1" for="delayExecTime">执行延时时间</label></dt>
					<dd><input class="txt1" type="text" id="delayExecTime" name="msgType.executeDelaySeconds" value="${msgType.executeDelaySeconds}"/></dd>
					<dt><label class="lable1" for="failedWay">失败策略</label></dt>
					<dd>
						<select class="select1" id="failedWay" name="msgType.failStrategyCode">
							<s:if test="#attr.msgType.failStrategyCode == 32">
								<option value="31">不处理</option>
								<option value="32"  selected="selected">立即重试N次</option>
								<option value="33">延时重试N次</option>
							</s:if>
							<s:elseif test="#attr.msgType.failStrategyCode == 33">
								<option value="31">不处理</option>
								<option value="32">立即重试N次</option>
								<option value="33"  selected="selected">延时重试N次</option>
							</s:elseif>
							<s:else>
								<option value="31" selected="selected">不处理</option>
								<option value="32">立即重试N次</option>
								<option value="33">延时重试N次</option>
							</s:else>
						</select>
					</dd>
				</dl>
				<dl>
					<dt><label class="lable1" for="retryNum">失败重试次数</label></dt>
					<dd><input class="txt1" type="text" id="retryNum" name="msgType.failRepeatNumber" value="${msgType.failRepeatNumber}" /></dd>
					<dt><label class="lable1" for="retryDelayTime">失败重试延时时间</label></dt>
					<dd><input class="txt1" type="text" id="retryDelayTime" name="msgType.failDelaySeconds" value="${msgType.failDelaySeconds}" /></dd>
				</dl>
				<dl>
					<dt><label class="lable1" for="remark">备注</label></dt>
					<dd><input class="txt1" type="text" id="remark" name="msgType.remark" value="${msgType.remark}"/></dd>
				</dl>
				</div>
				
				<div style="width:100%">
					<input id="btn_next" class="btn1 btn_ok" type="button" name="next" value="下一步"></input>
					<input id="btn_cancel" class="btn1 btn_cancel" type="button" name="cancel" value="取消"></input>
				</div>
			</div>
			<div style="display:none;">
				<s:hidden id="flag" name="flag" value="%{#attr.flag}"></s:hidden>
				<s:if test="#attr.flag == 'update'">
					<s:set name="relReIds" value="''"></s:set>
					<s:iterator value="#attr.msgType.receivePersonList" id="relReceiver" status="s">
						<s:if test="#s.index == 0">
							<s:set name="relReIds" value="#relReIds + #relReceiver.receivePersonId"></s:set>
						</s:if>
						<s:else>
							<s:set name="relReIds" value="#relReIds + ',' + #relReceiver.receivePersonId"></s:set>
						</s:else>
					</s:iterator>
					<s:hidden id="relReceiverIds" name="relReceiverIds" value="%{#relReIds}"></s:hidden>
					<s:hidden id="msgTypeId" name="msgType.msgTypeId" value="%{#attr.msgType.msgTypeId}"></s:hidden>
				</s:if>
			</div>
		</form>

		</div> 
		</td> 
		</tr> 
	</tbody> 
	</table>
</body> 
</html> 