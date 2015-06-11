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
	<script type="text/javascript" src="js/alarm/receicer_list.js?v=130423"></script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
	<tbody> 
	<tr> 
		<td class="td01"></td> 
		<td class="td02"><h3 class="topTitle fb f14">接收人信息列表</h3></td> 
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
			<dd>接收人信息列表</dd>
		</dl>
			<s:if test="#attr.flag != 'view'">
			<label class="lable2" >操作&nbsp;&nbsp;&gt;</label>
			<input type="button" class="btn2" id="btn_add" value="新增接收人"/>
			</s:if>
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
			<th>电话</th>
			<th>邮箱</th>
			<th>备注</th>
			<th>创建人</th>
			<th>创建时间</th>
			<s:if test="#attr.flag != 'view'">
			<th>操作</th>
			</s:if>
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
			<td class="mytd">
				<s:date name="#receiver.createTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<s:if test="#attr.flag != 'view'">
			<td class="mytd">
			<!--  
			<a name="a_add" href="javascript:void(0);" >新增</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
			-->
			<a name="a_update" href="javascript:void(0);" relval="<s:property value="receivePersonId"/>">修改</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a name="a_remove" href="javascript:void(0);" relval="<s:property value="receivePersonId"/>">删除</a>
			</td>
			</s:if>
		</tr>
		</s:iterator>
	</tbody>
	</table>
		<s:if test="#attr.flag == 'view'">
		<input class="btn1 btn_back" type="button" id="btn_back" value="返回"/>
		</s:if>
		<s:else>
			<c:import url="/WEB-INF/views/common/pagelink.jsp"></c:import>
		</s:else>
		
	</div> 
	</td> 
		<td class="td03"></td> 
	</tr> 
	</tbody> 
	</table>
</body> 
</html> 