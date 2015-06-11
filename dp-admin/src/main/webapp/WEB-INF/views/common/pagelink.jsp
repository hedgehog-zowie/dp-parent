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
		<span>共${page.totalRecord}条记录，页码：${page.currentPage}/${page.totalPage}</span>
<%-- 		<c:if test="${page.totalPage > 1}"> --%>
			<fmt:formatNumber type="number" value="${page.naviPage/2}" maxFractionDigits="0" var="halfNaviPage" />
			<c:set var="remainNaviPage" value="${page.naviPage - halfNaviPage - 1}"/>
			<c:set var="preIndex" value="${page.currentPage - halfNaviPage}" />
			<c:set var="afterIndex" value="${page.currentPage + page.naviPage - halfNaviPage - 1}" />
			<c:set var="preRemain" value="0"/>
			<c:if test="${preIndex < 1}">
				<c:set var="preRemain" value="${1 - preIndex}"/>
			</c:if>
			<c:set var="afterRemain" value="0"/>
			<c:if test="${page.totalPage - afterIndex < 1}">
				<c:set var="afterRemain" value="${afterIndex - page.totalPage}"/>
			</c:if>
			
			<!-- 分页导航开始部分 -->
			<c:choose>
				<c:when test="${preIndex - afterRemain <= 2}">
					<c:forEach begin="1" end="${page.currentPage}" var="i">
						<c:choose>
							<c:when test="${page.currentPage == i}">
								<span class="cur">${i}</span>
							</c:when>
							<c:otherwise>
								<a class="pageNavLink" href="javascript:void(0);" name="navi_a" relval="${i}">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<a class="pageNavLink" href="javascript:void(0);" name="navi_a" relval="1">1</a>
					<span>...</span>
					<c:forEach begin="${preIndex - afterRemain}" end="${page.currentPage - 1}" var="i">
						<a class="pageNavLink" href="javascript:void(0);" name="navi_a" relval="${i}">${i}</a>
					</c:forEach>
					<span class="cur">${page.currentPage}</span>
				</c:otherwise>
			</c:choose>
			
			<!-- 分页导航结束部分 -->
			<c:if test="${page.currentPage < page.totalPage}">
			<c:choose>
				<c:when test="${afterIndex + preRemain + 1 >= page.totalPage}">
					<c:forEach begin="${page.currentPage + 1}" end="${page.totalPage}" var="i">
						<a class="pageNavLink" href="javascript:void(0);" name="navi_a" relval="${i}">${i}</a>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach begin="${page.currentPage + 1}" end="${afterIndex + preRemain}" var="i">
						<a class="pageNavLink" href="javascript:void(0);" name="navi_a" relval="${i}">${i}</a>
					</c:forEach>
					<span>...</span>
					<a class="pageNavLink" href="javascript:void(0);" name="navi_a" relval="${page.totalPage}">${page.totalPage}</a>
				</c:otherwise>
			</c:choose>
			</c:if>
			<span><input type="text" size="1" id="gotoPage"></input><button type="button" id="goPageBtn">跳转</button></span>
<%-- 		</c:if> --%>
		<div style="display:none;">
			<input type="hidden" id="totalPage" value="${page.totalPage}"/>
		</div>
	</div>

</body>
</html>
