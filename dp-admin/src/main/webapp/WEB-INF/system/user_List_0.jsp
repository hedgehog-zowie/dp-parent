<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<title>操作员账号列表</title> 
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
<script type="text/javascript" src="<%=request.getContextPath() %>/template/javascript/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
function checkFrm() {
	return true;
}

function search(){
	document.mainFrm.pageIndex.value="1";
	document.mainFrm.action="<%=request.getContextPath()%>/getAllUserPage.action";
	document.mainFrm.method="POST";
	document.mainFrm.submit();
	return true;	 
}

function addAcount(){
	location.href = "<%=request.getContextPath()%>/addUser.action?user_type=1";
}

function switchAll(name) {
	obj = eval(name);
	if (typeof (obj.length) == "undefined") {
		obj.checked = !obj.checked;
	} else {
		for ( var i = 0; i < obj.length; i++)
			obj[i].checked = !obj[i].checked;
	}
}

function gotopage(index){
	var obj=document.mainFrm;
	obj.pageIndex.value=index;
	obj.action="<%=request.getContextPath()%>/getAllUserPage.action";
	obj.method="POST";
	obj.submit();
}

function changeAllStaus(status){
	var str = "";
	if(status==0){
		$('input[type=checkbox][name=ids][id^=1_]:checked').each(function() {
			var idval = $(this).attr("id");
			str += (idval.substr(2, idval.length))+ "\r\n"; 
	    });
		if(str != ""){
			alert("以下用户不是冻结状态："+ "\r\n"+str);
			return;
		}
	}else if(status==1){
		$('input[type=checkbox][name=ids][id^=0_]:checked').each(function() {
			var idval = $(this).attr("id");
			str += (idval.substr(2, idval.length))+ "\r\n"; 
	    });
		if(str != ""){
			alert("以下用户已经被冻结："+ "\r\n"+str);
			return;
		}
	}
	var obj=document.mainFrm;
	obj.action="<%=request.getContextPath()%>/changeAllStaus.action?status="+status;
	obj.method="POST";
	obj.submit();
	return true;
}

function changeStatus(status, user_id){
	var obj=document.mainFrm;
	obj.action='<%=request.getContextPath()%>/changeStaus.action?status='+status+'&user_id='+user_id;
	obj.method="POST";
	obj.submit();
	return true;
}

function checkPage(){
	var index=document.mainFrm.pageIndex.value;
	//index=index.replace(/^\s+|\s+$/g, "");
	if(isNaN(index)){
		document.getElementById("msg").innerHTML="输入的页码应该是一个整数！";
		document.mainFrm.pageIndex.focus();
		document.mainFrm.pageIndex.select();
		return false;
	}else if(/^-?\d+\.\d+$/.test(index)){			 
		document.getElementById("msg").innerHTML="输入的页码不能是小数！";
		document.mainFrm.pageIndex.focus();
		document.mainFrm.pageIndex.select();
		return false;
	}else if(parseInt(index)>parseInt(document.mainFrm.max.value)){			 
		document.getElementById("msg").innerHTML="输入的页码不能超过最大页数："+document.mainFrm.max.value+"页！";
		document.mainFrm.pageIndex.focus();
		document.mainFrm.pageIndex.select();
		return false;
	}else if(index<1){			 
		document.getElementById("msg").innerHTML="输入的页码不能小于1！";
		document.mainFrm.pageIndex.focus();
		document.mainFrm.pageIndex.select();
		return false;
	}else{
		document.mainFrm.action="<%=request.getContextPath()%>/getAllUserPage.action";
		document.mainFrm.method="POST";
		document.mainFrm.submit();
		return true;
	}
}

function init(){
	var msg = $("#tipmsg").val();
	if(msg != null && msg != "" && msg != "null"){
		alert(msg);
	}
}
	
</script>

</head>
<body onload="init()"> 
<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top"> 
<tbody> 
<tr> 
<td class="td01"></td> 
<td class="td02"><h3 class="topTitle fb f14">账号列表</h3></td> 
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
	<dd>社区管理</dd>
	<dd>操作员账号列表</dd>
</dl>
</div>


<form action="<%=request.getContextPath()%>/getAllUserPage.action" id="mainFrm" name="mainFrm" method="post" onsubmit="return checkFrm();">
<input type="hidden" name="logId" id="logId"/>
<input type="hidden" name="tipmsg" id="tipmsg" value="${msg}"/>
<div>
<table class="mytable">
	<tr><td></td>账号名：<input name="user_name" value='<s:property value="user_name" />' /></td></tr>
</table>
<input type="button" value="  搜  索  " onclick="search()" />&nbsp;&nbsp; 
<br/><br/>
</div>
<table class="listtable" style="width: 100%">
	<thead>
		<tr>
			<th>选择</th>
			<th>序号</th>
			<th>用户ID</th>
			<th>账号名</th>
			<th>联系手机</th>
			<th>用户状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="#request.uList" id="u" status="s">
			<tr>
				<td><!-- 选择 -->				
					<input name="ids" type="checkbox" value="<s:property value="user_id"/>" id="<s:property value="status"/>_<s:property value="user_name" />"/>			
				</td>
				<td class="mytd"><s:property value="#s.index+1" /></td>
				<td class="mytd"><s:property value="user_id" /></td>
				<td class="mytd"><s:property value="user_name" /></td>
				<td class="mytd"><s:property value="mobile" /></td>
				<td class="mytd">
					<s:if test="status==1">正常</s:if> 
					<s:if test="status==0"><font color="red">冻结</font></s:if>
				</td>
				<td class="mytd">
					<a href='<%=request.getContextPath()%>/updateUser.action?user_id=<s:property value="user_id"/>'>修改</a>&nbsp;&nbsp;
					<a href="#" onclick="changeStatus(<s:property value="status"/>, <s:property value="user_id"/>)">
					    <s:if test="status==1">冻结</s:if> 
					    <s:if test="status==0">解冻</s:if>
					</a>&nbsp;&nbsp;					
					<s:if test="status==1">
					  <a href="<%=request.getContextPath()%>/showPermission.action?u_id=<s:property value="user_id"/>&user_name=<s:property value="user_name"/>&user_type=${user_type}">
					      权限管理
					  </a>
					</s:if> 
					<s:if test="status==0">无权配置</s:if>
				</td>
			</tr>
		</s:iterator>
	</tbody>
	<s:if test='!#request.uList.isEmpty()'>
	<tbody>
		<tr>
			<td colspan="3">
				<%--<input type="checkbox" id="chooser" /> --%>
				<a href="javascript:void(0)" onclick="selAll()" id="chooser">全选</a> /
				<a href="JavaScript:switchAll('mainFrm.ids')">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" name="approveFlag" value=" 冻  结 " id="approveFlag" onclick="changeAllStaus('1')"></input>&nbsp;&nbsp;
				<input type="button" name="approveFlag" value=" 解  冻 " id="approveFlag" onclick="changeAllStaus('0')"></input>
			</td>
			<td colspan="7">
						
<%-- 总记录数 必须  --%>
<c:set var="totalSize" value="${totalCounts}" />
<%-- 每页记录数 必须  --%>
<c:set var="pageSize" value="${pageSize}" />
<%-- 当前请求也码 必须 --%> 
<c:set var="currentPage" value="${pageIndex}" />
<%-- 总页数 --%>
<c:set var="totalPages" value="${totalPages}" />


<%-- 分页导航条显示的页数  --%>
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
		
		<%-- 输出页数导航 开始  --%>
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
			<a class="pageNavLink" href="#${totalPages }">${totalPages }</a>
		</c:if>
		
		<span><input type="text" size="1" id="gotoPage"></input><button type="button" id="goButton">跳转</button></span>
        </div>
		</td>
		</tr>
	</tbody>
	</s:if>
	 
</table>
<input type="hidden" name="pageIndex" id="pageIndex"  value='${pageIndex}'/>
<input type="hidden" name="user_type" id="user_type"  value='${user_type}'/>

</div> 
</form>
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