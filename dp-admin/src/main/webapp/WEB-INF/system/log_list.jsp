<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<title>日志列表</title> 
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
<link href="<%=request.getContextPath() %>/template/css/weebox.css" rel="stylesheet" type="text/css" /> 
<script src="<%=request.getContextPath()%>/template/My97DatePicker/WdatePicker.js" type="text/javascript"></script>		
<script type="text/javascript" src="<%=request.getContextPath() %>/template/javascript/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/template/javascript/bgiframe.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/template/javascript/weebox.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		$("#busdiv").hide();
		$("#logdiv").hide();
	});

	function checkFrm() {
		var operId = $("#operId").val();
		if (operId.length > 0 && isNaN(operId)) {
			alert("操作者ID只能输入数字！");
			return false;
		}
		return true;
	}

	function batchDel() {
		var selObj = $("input:checked");
		if (selObj && selObj.length > 0) {
			if (!confirm("确定要批量删除吗？")) {
				return;
			}
			var frm = $("#mainFrm");
			frm.attr("action","<%=request.getContextPath()%>/deleteLogs.action");
			frm.submit();
		}else{
			alert("请选择要删除的记录");
		}
		return false;
	}
	
	function query(){
		if(!checkFrm()){
			return false;
		}
		var frm = $("#mainFrm");
		frm.attr("action", "<%=request.getContextPath()%>/getLogs.action");
		frm.submit();
	}
 
	function delItem(logId){
		if(!confirm("确定要删除吗？")){
			return false;
		}
		$("#logId").attr("value", logId);
		var frm = $("#mainFrm");
		frm.attr("action", "<%=request.getContextPath()%>/deleteLogs.action");
		frm.submit();
	}
	
	function switchAll(name) {
		obj = eval(name);
		if (typeof (obj.length) == "undefined") {
			obj.checked = !obj.checked;
		} else {
			for ( var i = 0; i < obj.length; i++){
				obj[i].checked = !obj[i].checked;
			}
		}
	}
	function selectLogType(temp){
		if(temp=="1"){
			$("#busdiv").hide();	
			$("#logdiv").show();	
		}else if(temp=="2"){
			$("#logdiv").hide();	
			$("#busdiv").show();
		}else{
			$("#logdiv").hide();	
			$("#busdiv").hide();
		}
	}
	
</script>

</head>
<body> 
<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
<tbody> 
<tr> 
<td class="td01"></td> 
<td class="td02"><h3 class="topTitle fb f14">日志列表</h3></td> 
<td class="td03"></td> 
</tr> 
</tbody> 
</table>  
<table border="0" cellspacing="0" cellpadding="0" class="adminMain_main"> 
<tbody> 
<tr> 
<td class="td01"></td> 
<td class="adminMain_wrap"> 
<div class="wrapMain" style="height:1400px;vertical-align: middle;"> 
<!-- 在这里填充 -->
<div class="adminUp_wrap" style="height:24px;margin-bottom:10px;">
<dl class="adminPath clearfix">
	<dt>您现在的位置：</dt>
	<dd>系统管理</dd>
	<dd>日志列表</dd>
</dl>
</div>


<form action="<%=request.getContextPath()%>/getLogs.action" id="mainFrm" name="mainFrm" method="post">
<input type="hidden" name="logId" id="logId"/>
<div>
<table class="mytable">
	<tr>
		<td>时间： </td>
		<td><input name="begDate" id="begDate" type="text" size="10"
											onClick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});"
											readonly="readonly" value="<s:property value='begDate' />"></input>至<input
											name="endDate" id="endDate" type="text" size="10"
											onClick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'begDate\',{M:0,d:0,m:1});}'});"
											readonly="readonly" value="<s:property value='endDate' />"></input></td>
		<td>日志类型：</td>
		<td><s:select name="logType" value="%{logType}" list="#{'':'请选择','2':'业务日志','1':'系统日志'}" onChange="selectLogType(this.value);"/></td>
		<td>
			<div id="busdiv">
				<s:select name="logbustype" value="%{logsystype}" list="#{'21':'商品日志','22':'用户信息日志','23':'商户日志','24':'订单日志'}" />
			</div>
			<div id="logdiv">
			    <s:select name="logsystype" value="%{logsystype}" list="#{'11':'自身系统日志','12':'外部系统对接异常'}" />
		    </div>
		  </td>				
	</tr>
	<tr>
		<td>操作者ID：</td>
		<td><input name="operId" id="operId" type="text"
			value="<s:property value='operId' />"></input></td>
		<td>操作者名称：</td>
		<td><input name="operName" id="operName" type="text" value="<s:property value='operName' />"></input></td>	
	</tr>
</table>
<input type="button" value="  搜  索  " onclick="query()"/>
</div>

<table class="listtable" style="width: 100%">
	<thead>
		<tr>
			<th>选择</th>
			<th>操作者名称</th>
			<th>操作者IP</th>
			<th width="30%">日志内容</th>
			<th>日志类型</th>
			<th>记录时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="logList" var="item" status="s">
		<tr>
			<td><!-- 选择 -->				
				<input name="ids" type="checkbox" value="${item.logId}"/>			
			</td>
			<%-- 
			<td class="mytd">${s.index+1}</td>
			<td class="mytd"><s:if test="#item.operId != 0">${item.operId}</s:if>&nbsp;</td>
			--%>
			<td class="mytd">${item.operName}</td>
			<td class="mytd">${item.operIp}</td>
			<td class="mytd" title="${item.memo}" onclick="$.weeboxs.open('${item.memo}',{type: 'dialog',title:'日志内容',width : 600,height : 220 })">
			<c:choose>
				<c:when test="${fn:length(item.memo) > 80}">
						<c:out value="${fn:substring(item.memo, 0, 80)}......" escapeXml="false"/>
				</c:when>
				<c:otherwise>
						<c:out value="${item.memo}" escapeXml="false"/>
				</c:otherwise>
				</c:choose>
			</td>
			<td class="mytd">
				${item.logTypeName}
			</td>
			<td class="mytd"><s:date name="#item.createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
			<td class="mytd"><a href="javaScript:void(0)" onclick="delItem('${item.logId}')">删除</a></td>
		</tr>
		</s:iterator>
		
	</tbody>
	<s:if test='!logList.isEmpty()'>
	<tbody>
		<tr>
			<td colspan="3">
				<%--<input type="checkbox" id="chooser" />&nbsp; --%>
				<a href="javascript:void(0)" onclick="selAll()" id="chooser">全选</a> /
				<a href="JavaScript:switchAll('mainFrm.ids')">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" name="approveFlag" value=" 删除 " id="approveFlag" onclick="batchDel()"></input>
			</td>
			<td colspan="6">
			
			
<%-- 总记录数 必须 --%>
<c:set var="totalSize" value="${totalCounts}" />
<%-- 每页记录数 必须 --%>
<c:set var="pageSize" value="${pageSize}" />
<%-- 当前请求也码 必须 --%>
<c:set var="currentPage" value="${pageIndex}" />
<%-- 总页数 --%>
<c:set var="totalPages" value="${totalPages}" />


<%-- 分页导航条显示的页数 --%>
<c:set var="spanPages" value="5" />
<%-- 定义默认显示的导航页数 --%>
<c:if test="${spanPages == null || spanPages <= 0}">
	<c:set var="spanPages" value="5" />
</c:if>

<div class="pagenav">
		<span>共${totalSize}条记录，页码：${currentPage}/${totalPages}</span>

		
		<c:set var="startNav" value="${currentPage - (spanPages/2-1)}" />
				
		<c:if test="${startNav < 2}">
			<c:set var="startNav" value="1" />
		</c:if>
		<c:set var="endNav" value="${startNav + spanPages - 1}" />
		<c:if test="${endNav >= totalPages}">
			<c:set var="endNav" value="${totalPages}" />
			<c:set var="startNav" value="${endNav - spanPages + 1}" />
		</c:if>
		<c:if test="${startNav < 1}">
			<c:set var="startNav" value="1" />
		</c:if>
		
		
		<c:if test="${(startNav+0) > 1}">
			<a class="pageNavLink" href="#1">1</a>
			<span>...</span>
		</c:if>
		
		<%-- 输出页数导航 开始 --%>
		<c:forEach var="i" begin="${startNav}" end="${endNav}">
			<c:choose>
				<c:when test="${i == currentPage}">
					<span class="cur">${i}</span>
				</c:when>
				<c:otherwise>
					<a class="pageNavLink" href="#${i }">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<%-- 输出页数导航 结束 --%>

		<c:if test="${endNav < totalPages}">
			<span>...</span>
			<a class="pageNavLink" href="#${totalPages }">${totalPages}</a>
		</c:if>
		
		<span><input type="text" size="1" id="gotoPage"></input><button type="button" id="goButton">跳转</button></span>
        </div>
		</td>
		</tr>
	</tbody>
	</s:if>
	<s:if test='logList.isEmpty()'>
		<tr><td colspan="9">对不起，没有查询到相关记录！</td></tr>
	</s:if>
</table>
<input type="hidden" name="pageIndex" id="pageIndex"  value='${pageIndex}'/>
</form>
</div> 
</td> 
<td class="td03"></td> 
</tr> 
</tbody> 
</table>

<script type="text/javascript">

$(function() {
	$("#goButton").click(function(){
		var page = $.trim($("#gotoPage").val());
		if (page==""||isNaN(page)) {
			alert("请输入数字");
			return;
		}
		
		page = parseInt(page);
		var totalPage = ${totalPages};
		
		if (page > totalPage) {
			page = totalPage;
		}else if (page <= 0) {
			page = 1;
		}
		 
		$('#pageIndex').attr("value", page);
		$('#mainFrm').attr('action', $('#mainFrm').attr('action') + '?page=' + page);
		$('#mainFrm').submit();
	});
	
	$('.pageNavLink').click(function() {
		var page = $(this).attr('href').replace('#', '');
		$('#pageIndex').attr("value", page);
		$('#mainFrm').attr('action', $('#mainFrm').attr('action') + '?page=' + page);
		$('#mainFrm').submit();
		return false;
	});
});

$(function() {
	$('#chooser').click(function(){
		var checked = $(this).attr("checked");
		$('input[name=ids]').each(function(){
			$this = $(this);
			//alert($(this).attr(""));
			if (! $this.attr("disabled")) {
				$this.attr("checked", checked);
			}
		});
	});
	 
});

function selAll(){
	$('input[name=ids]').each(function(){
		$this = $(this);
		if (! $this.attr("disabled")) {
			$this.attr("checked", "checked");
		}
	});
}
</script>
</body> 
</html> 