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
	<script src="template/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/stat/statScheme_edit.js?v=130607"></script>
	<script type="text/javascript">
	  var basePath="<%=basePath%>";
	</script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
		<tbody> 
			<tr> 
				<td class="td01"></td> 
				<td class="td02"><h3 class="topTitle fb f14">统计分析计划</h3></td> 
				<td class="td03"></td> 
			</tr> 
		</tbody> 
	</table>  
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_main"> 
		<tbody> 
		<tr> 
		<td class="td01"></td> 
		<td class="adminMain_wrap"> 
		<div class="wrapMain" style="height:800px;vertical-align: middle;"> 
		<div class="adminUp_wrap" style="height:24px;margin-bottom:10px;">
			<dl class="adminPath clearfix">
				<dt>您现在的位置：</dt>
				<dd>管理配置</dd>
				<dd>统计分析计划</dd>
			</dl>
		</div>
		
		<div class="errorspace">
			<s:fielderror />
		</div>
		<form action="" id="mainFrm" method="post">
			
			<div class="frame3" >
				<div class="inFrame1">
				<dl>
					<dt class="title3"><label class="lable1" for="code">计划编码</label></dt>
					<dd class="input3"><input class="txt2" type="text" id="code" name="statScheme.code" value="${statScheme.code}"/></dd>
					<dt class="title3"><label class="lable1" for="name">计划名称</label></dt>
					<dd class="input3"><input class="txt2" type="text" id="name" name="statScheme.name" value="${statScheme.name}"/></dd>
					<dt class="title3"><label class="lable1" for="status">启用状态</label></dt>
					<dd class="input3">
						<select id="status" class="select2" name="statScheme.status">
							<s:if test="statScheme.status == 0">
								<option value="0" selected="selected">
									未启用
								</option>
								<option value="1">
									启用
								</option>
							</s:if>
							<s:elseif test="statScheme.status == 1">
								<option value="0">
									未启用
								</option>
								<option value="1" selected="selected">
									启用
								</option>
							</s:elseif>
							<s:else>
								<option value="0" selected="selected">
									未启用
								</option>
								<option value="1">
									启用
								</option>
							</s:else>
						</select>
					</dd>
				</dl>
				<dl>
					<dt class="title3"><label class="lable1" for="strategyCode">统计策略</label></dt>
					<dd class="input3">
						<select id="strategyCode" class="select2" name="statScheme.statStrategyCode">
							<s:if test="statScheme.statStrategyCode == '11'">
								<option value="11" selected="selected">
									定时调度统计分析类型
								</option>
								<option value="12">
									间隔快照统计分析类型
								</option>
							</s:if>
							<s:elseif test="statScheme.statStrategyCode == '12'">
								<option value="11">
									定时调度统计分析类型
								</option>
								<option value="12" selected="selected">
									间隔快照统计分析类型
								</option>
							</s:elseif>
							<s:else>
								<option value="11" selected="selected">
									定时调度统计分析类型
								</option>
								<option value="12">
									间隔快照统计分析类型
								</option>
							</s:else>
						</select>
					</dd>
					<dt class="title3"><label class="lable1" for="scheduledTime">调度统计分析定时时间</label></dt>
					<dd class="input3">
						<input class="txt2 Wdate1" type="text" id="scheduledTime" name="statScheme.statScheduledTime" size="10"
							onclick="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'});"
							readonly="readonly" value="${statScheme.statScheduledTime}"></input>
					</dd>
					<dt class="title3"><label class="lable1" for="snapshotTime">快照统计分析间隔时间(秒)</label></dt>
					<dd class="input3"><input class="txt2" type="text" id="snapshotTime" name="statScheme.statSnapshotTime" value="${statScheme.statSnapshotTime}"/></dd>
				</dl>
				<dl>
					<dt class="title3"><label class="lable1" for="rptDataType">上报数据类型</label></dt>
					<dd class="input3"><input class="txt2" type="text" id="rptDataType" name="statScheme.rptDataType" value="${statScheme.rptDataType}"/></dd>
					<dt class="title3"><label class="lable1" for="statField">统计维度</label></dt>
					<dd class="input3"><input class="txt2" type="text" id="statField" name="statScheme.statField" value="${statScheme.statField}"/></dd>
					<dt class="title3"><label class="lable1" for="remark">备注</label></dt>
					<dd class="input3"><input class="txt2" type="text" id="remark" name="statScheme.remark" value="${statScheme.remark}"/></dd>
				</dl>
				</div>
				<div style="width:100%">
					<input id="btn_ok" class="btn1 btn_ok" type="button" name="ok" value="确定"></input>
					<input id="btn_cancel" class="btn1 btn_cancel" type="button" name="cancel" value="取消"></input>
				</div>
			</div>
			<div style="display:none;">
				<input type="hidden" id="id" name="statScheme.id" value="${statScheme.id}"/>
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