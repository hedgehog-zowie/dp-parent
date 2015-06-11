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
		td{height: 20px;}
		th{height: 40px;color: #0091D3;}
	</style>
	<script src="template/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/stat/iuniUserRegDaily_list.js?v=130607"></script>
	<script type="text/javascript">
	  var basePath="<%=basePath%>";
	</script>
</head>
<body> 
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
	<tbody> 
	<tr> 
		<td class="td01"></td> 
		<td class="td02"><h3 class="topTitle fb f14">IUNI商城会员注册统计</h3></td> 
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
		<dl class="adminPath clearfix2" style="width:50%;">
			<dt>您现在的位置：</dt>
			<dd>查询功能</dd>
			<dd>IUNI商城会员注册统计</dd>
		</dl>
		
		</div>
		
		<div>
		<input type="hidden" id="msg" value="<s:property value="msg"/>"/>
		</div>
		
		<div class="frame4" style="height:70px;">
		<form action="" method="post" id="queryFrm">
			<div style="display:none;">
			    <!-- 
				<input  type="hidden" id="bizDate" name="bizDate" value="${bizDate}"/>
			    <input  type="hidden" id="adName" name="adName" value="${adName}"/>
			    <input  type="hidden" id="channelName" name="channelName" value="${channelName}"/>
			     -->
			</div>
		<dl>
			<dd class="ddl1">
				<label for="code">开始日期</label>
			</dd>
			<dd class="ddr1">
				<input class="Wdate1" name="beginDate" id="beginDate" type="text" size="10"
					onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});"
					readonly="readonly" value="${beginDate}"></input>
			</dd>
			<dd class="ddl1">
				<label for="code">结束日期</label>
			</dd>
			<dd class="ddr1">
				<input class="Wdate1" name="endDate" id="endDate" type="text" size="10"
					onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});"
					readonly="readonly" value="${endDate}"></input>
			</dd>
			<dd class="ddl1">
				<input class="btn4" type="button" id="btn_query" value="查询"/>
			</dd>
			<dd class="ddl3">
				<input class="btn6" type="button" id="btn_exportExcel" value="导出EXCEL"/>
			</dd>
		</dl>
			
		</form>
		</div>
		
	<table class="listtable" style="width: 100%">
	<thead>
		<tr>
			<th>统计日期</th>
			<th>注册页PV</th>
			<th>注册页UV</th>
			<th>商城注册会员数</th>
			<th>注册成功率</th>
			<th>新浪微博联合登录</th>
			<th>QQ联合登录</th>
			<th>支付宝联合登录</th>
			<th>豆瓣联合登录</th>
			<!-- 
			<th>访问活跃用户数</th>		 -->	
			<th>购买活跃用户数</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="iuniDayPvuvUserRegList" status="s" id="iuniDayPvuvUserReg">
		<tr>
			<td class="mytd">${bizDate}</td>
			<td class="mytd">${pv}</td>
			<td class="mytd">${uv}</td>
			<td class="mytd">${gioneeRegNum}</td>
			<td class="mytd">${regSuccRate}</td>
			<td class="mytd">${sinaRegNum}</td>
			<td class="mytd">${qqRegNum}</td>
			<td class="mytd">${zfbRegNum}</td>
			<td class="mytd">${doubanRegNum}</td>
			<!-- 
			<td class="mytd">${loginUserNum}</td>  -->
			<td class="mytd">${buyUserNum}</td>
		</tr>
		</s:iterator>
		<tr>
			<td class="mytd">总计</td>
			<td class="mytd">${iuniDayPvuvUserRegSum.pv}</td>
			<td class="mytd">${iuniDayPvuvUserRegSum.uv}</td>
			<td class="mytd">${iuniDayPvuvUserRegSum.gioneeRegNum}</td>
			<td class="mytd">${iuniDayPvuvUserRegSum.regSuccRate}</td>
			<td class="mytd">${iuniDayPvuvUserRegSum.sinaRegNum}</td>
			<td class="mytd">${iuniDayPvuvUserRegSum.qqRegNum}</td>
			<td class="mytd">${iuniDayPvuvUserRegSum.zfbRegNum}</td>
			<td class="mytd">${iuniDayPvuvUserRegSum.doubanRegNum}</td>
			<!-- 
			<td class="mytd">${iuniDayPvuvUserRegSum.loginUserNum}</td>  -->
			<td class="mytd">${iuniDayPvuvUserRegSum.buyUserNum}</td>
		</tr>
	</tbody>
	</table>
		<c:import url="/WEB-INF/views/common/pagelink.jsp"></c:import>
                                说明：<br/>注册页pv：注册页面的PV
             <br/>注册页uv	：注册页面的UV	
             <br/>商城注册会员数：通过商城入口进行注册的用户		
             <br/>注册成功率：商城注册会员数/注册页UV		
             <br/>新浪微博联合登录：通过新浪微博联合登录入口进行注册的用户			
             <br/>QQ联合登录：通过QQ登录入口进行注册的用户
             <br/>支付宝联合登录：通过支付宝联合登录入口进行注册的用户
             <br/>豆瓣联合登录：通过豆瓣联合登录入口进行注册的用户
             <!-- 
             <br/>访问活跃用户数：时间点倒推15天进行过商城登录的用户
              -->			 
             <br/>购买活跃用户数：时间点倒推30天下单的用户		
	</div> 
	</td> 
		<td class="td03"></td> 
	</tr> 
	</tbody> 
	</table>
</body> 
</html> 