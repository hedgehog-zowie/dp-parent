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
	<script type="text/javascript" src="js/stat/statScheme_list.js?v=130607"></script>
	<script type="text/javascript">
	  var basePath="<%=basePath%>";
	</script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
	<tbody> 
	<tr> 
		<td class="td01"></td> 
		<td class="td02"><h3 class="topTitle fb f14">统计分析计划列表</h3></td> 
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
		<dl class="adminPath clearfix2" style="width:85%;">
			<dt>您现在的位置：</dt>
			<dd>查询功能</dd>
			<dd>统计分析计划列表</dd>
		</dl>
		<label class="lable2" >操作&nbsp;&nbsp;&gt;</label>
		<input type="button" class="btn3" id="btn_add" value="新增统计分析计划"/>
		</div>
		
		<div>
		<input type="hidden" id="msg" value="<s:property value="msg"/>"/>
		</div>
		
		<div class="frame4" style="height:105px;">
		<form action="" method="post" id="queryFrm">
		<dl>
			<dd class="ddl1">
				<label for="code">计划编码</label>
			</dd>
			<dd class="ddr1">
				<input type="text" id="code" name="statSchemeVo.code" value="${statSchemeVo.code}"/>
			</dd>
			<dd class="ddl1">
				<label for="name">计划名称</label>
			</dd>
			<dd class="ddr1">
				<input type="text" id="name" name="statSchemeVo.name" value="${statSchemeVo.name}"/>
			</dd>
			<dd class="ddl1">
				<label for="status">启用状态</label>
			</dd>
			<dd class="ddr1">
				<select id="status" class="select1" name="statSchemeVo.status">
					<s:if test="statSchemeVo.status == 0">
						<option>
							
						</option>
						<option value="0" selected="selected">
							未启用
						</option>
						<option value="1">
							启用
						</option>
					</s:if>
					<s:elseif test="statSchemeVo.status == 1">
						<option>
							
						</option>
						<option value="0">
							未启用
						</option>
						<option value="1" selected="selected">
							启用
						</option>
					</s:elseif>
					<s:else>
						<option selected="selected">
							
						</option>
						<option value="0">
							未启用
						</option>
						<option value="1">
							启用
						</option>
					</s:else>
				</select>
			</dd>
			
			<dd class="ddl1">
				<label for="strategyCode">统计策略</label>
			</dd>
			<dd class="ddr1">
				<select id="strategyCode" class="select1" name="statSchemeVo.statStrategyCode">
					<s:if test="statSchemeVo.statStrategyCode == '11'">
						<option>
							
						</option>
						<option value="11" selected="selected">
							定时调度统计分析类型
						</option>
						<option value="12">
							间隔快照统计分析类型
						</option>
					</s:if>
					<s:elseif test="statSchemeVo.statStrategyCode == '12'">
						<option>
							
						</option>
						<option value="11">
							定时调度统计分析类型
						</option>
						<option value="12" selected="selected">
							间隔快照统计分析类型
						</option>
					</s:elseif>
					<s:else>
						<option selected="selected">
							
						</option>
						<option value="11">
							定时调度统计分析类型
						</option>
						<option value="12">
							间隔快照统计分析类型
						</option>
					</s:else>
				</select>
			</dd>
			<dd class="ddl1">
				<label for="rptDataType">上报数据类型</label>
			</dd>
			<dd class="ddr1">
				<input type="text" id="rptDataType" name="statSchemeVo.rptDataType" value="${statSchemeVo.rptDataType}"/>
			</dd>
			<dd class="ddl1">
				<label for="statField">统计维度</label>
			</dd>
			<dd class="ddr1">
				<input type="text" id="statField" name="statSchemeVo.statField" value="${statSchemeVo.statField}"/>
			</dd>
			<dd class="ddl1">
				<label for="beginDate">创建时间开始</label>
			</dd>
			<dd class="ddr1">
				<input class="Wdate1" name="statSchemeVo.beginDate" id="beginDate" type="text" size="10"
					onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});"
					readonly="readonly" value="${statSchemeVo.beginDate}"></input>
			</dd>
			<dd class="ddl1">
				<label for="endDate">创建时间结束</label>
			</dd>
			<dd class="ddr1">
				<input class="Wdate1" name="statSchemeVo.endDate" id="endDate" type="text" size="10"
					onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginDate\',{M:0,d:0,m:1});}'});"
					readonly="readonly" value="${statSchemeVo.endDate}"></input>
			</dd>
		</dl>
		<div>
			<input class="btn4" type="button" id="btn_query" value="查询"/>
		</div>
			
		</form>
		</div>
		
		<div style="display: none;">
			<s:hidden id="code" name="code" value="%{#attr.statSchemeVo.code}"></s:hidden>
			<s:hidden id="name" name="name" value="%{#attr.statSchemeVo.name}"></s:hidden>
			<s:hidden id="status" name="status" value="%{#attr.statSchemeVo.status}"></s:hidden>
			<s:hidden id="statStrategyCode" name="statStrategyCode" value="%{#attr.statSchemeVo.statStrategyCode}"></s:hidden>
			<s:hidden id="rptDataType" name="rptDataType" value="%{#attr.statSchemeVo.rptDataType}"></s:hidden>
			<s:hidden id="statField" name="statField" value="%{#attr.statSchemeVo.statField}"></s:hidden>
			<s:hidden id="beginDate" name="beginDate" value="%{#attr.statSchemeVo.beginDate}"></s:hidden>
			<s:hidden id="endDate" name="endDate" value="%{#attr.statSchemeVo.endDate}"></s:hidden>
		</div>
		
	<table class="listtable" style="width: 100%">
	<thead>
		<tr>
			<th>序号</th>
			<th>计划编码</th>
			<th>计划名称</th>
			<th>统计策略</th>
			<th>调度统计分析定时时间</th>
			<th>快照统计分析间隔时间(秒)</th>
			<th>上报数据类型</th>
			<th>统计维度</th>
			<th>启用状态</th>
			<th>创建时间</th>
			<th>创建人</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="#request.statSchemes" status="s" id="statScheme">
		<tr>
			<td class="mytd"><s:property value="#s.index+1" /></td>
			<td class="mytd"><s:property value="code" /></td>
			<td class="mytd"><s:property value="name" /></td>
			<td class="mytd">
				<s:if test="statStrategyCode == '11'">
					定时调度统计分析类型
				</s:if>
				<s:elseif test="statStrategyCode == '12'">
					间隔快照统计分析类型
				</s:elseif>
			</td>
			<td class="mytd">
				<s:date name="statScheduledTime" format="HH:mm:ss"/>
			</td>
			<td class="mytd"><s:property value="statSnapshotTime" /></td>
			<td class="mytd"><s:property value="rptDataType" /></td>
			<td class="mytd"><s:property value="statField" /></td>
			<td class="mytd">
				<s:if test="status == 0">
					未启用
				</s:if>
				<s:elseif test="status == 1">
					启用
				</s:elseif>
			</td>
			<td class="mytd">
				<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td class="mytd"><s:property value="creator.user_name" /></td>
			<td class="mytd"><s:property value="remark" /></td>
			<td class="mytd">
			<a name="a_update" href="javascript:void(0);" relval="<s:property value="id"/>">修改</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a name="a_remove" href="javascript:void(0);" relval="<s:property value="id"/>">删除</a>
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