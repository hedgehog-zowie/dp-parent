<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<link href="css/alarm/main.css" rel="stylesheet" type="text/css"/>
</head>
<body>

	<div class="pagenavi">
		<span>共${onePage.totalRecord}条记录，页码：${onePage.currentPage}/${onePage.totalPage}</span>
<%-- 		<c:if test="${onePage.totalPage > 1}"> --%>
			<fmt:formatNumber type="number" value="${onePage.naviPage/2}" maxFractionDigits="0" var="halfNaviPage" />
			<c:set var="remainNaviPage" value="${onePage.naviPage - halfNaviPage - 1}"/>
			<c:set var="preIndex" value="${onePage.currentPage - halfNaviPage}" />
			<c:set var="afterIndex" value="${onePage.currentPage + onePage.naviPage - halfNaviPage - 1}" />
			<c:set var="preRemain" value="0"/>
			<c:if test="${preIndex < 1}">
				<c:set var="preRemain" value="${1 - preIndex}"/>
			</c:if>
			<c:set var="afterRemain" value="0"/>
			<c:if test="${onePage.totalPage - afterIndex < 1}">
				<c:set var="afterRemain" value="${afterIndex - onePage.totalPage}"/>
			</c:if>
			
			<!-- 分页导航开始部分 -->
			<c:choose>
				<c:when test="${preIndex - afterRemain <= 2}">
					<c:forEach begin="1" end="${onePage.currentPage}" var="i">
						<c:choose>
							<c:when test="${onePage.currentPage == i}">
								<span class="cur">${i}</span>
							</c:when>
							<c:otherwise>
								<a class="pageNavLink" href="javascript:void(0);" name="navi_a_1" relval="${i}">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<a class="pageNavLink" href="javascript:void(0);" name="navi_a_1" relval="1">1</a>
					<span>...</span>
					<c:forEach begin="${preIndex - afterRemain}" end="${onePage.currentPage - 1}" var="i">
						<a class="pageNavLink" href="javascript:void(0);" name="navi_a_1" relval="${i}">${i}</a>
					</c:forEach>
					<span class="cur">${onePage.currentPage}</span>
				</c:otherwise>
			</c:choose>
			
			<!-- 分页导航结束部分 -->
			<c:if test="${onePage.currentPage < onePage.totalPage}">
			<c:choose>
				<c:when test="${afterIndex + preRemain + 1 >= onePage.totalPage}">
					<c:forEach begin="${onePage.currentPage + 1}" end="${onePage.totalPage}" var="i">
						<a class="pageNavLink" href="javascript:void(0);" name="navi_a_1" relval="${i}">${i}</a>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach begin="${onePage.currentPage + 1}" end="${afterIndex + preRemain}" var="i">
						<a class="pageNavLink" href="javascript:void(0);" name="navi_a_1" relval="${i}">${i}</a>
					</c:forEach>
					<span>...</span>
					<a class="pageNavLink" href="javascript:void(0);" name="navi_a_1" relval="${onePage.totalPage}">${onePage.totalPage}</a>
				</c:otherwise>
			</c:choose>
			</c:if>
			<span><input type="text" size="1" id="gotoPage_1"></input><button type="button" id="goPageBtn_1">跳转</button></span>
<%-- 		</c:if> --%>
		<div style="display:none;">
			<input type="hidden" id="totalPage_1" value="${onePage.totalPage}"/>
		</div>
	</div>

</body>
</html>
