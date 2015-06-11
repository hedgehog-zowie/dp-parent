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
		<span>共${twoPage.totalRecord}条记录，页码：${twoPage.currentPage}/${twoPage.totalPage}</span>
<%-- 		<c:if test="${twoPage.totalPage > 1}"> --%>
			<fmt:formatNumber type="number" value="${twoPage.naviPage/2}" maxFractionDigits="0" var="halfNaviPage" />
			<c:set var="remainNaviPage" value="${twoPage.naviPage - halfNaviPage - 1}"/>
			<c:set var="preIndex" value="${twoPage.currentPage - halfNaviPage}" />
			<c:set var="afterIndex" value="${twoPage.currentPage + twoPage.naviPage - halfNaviPage - 1}" />
			<c:set var="preRemain" value="0"/>
			<c:if test="${preIndex < 1}">
				<c:set var="preRemain" value="${1 - preIndex}"/>
			</c:if>
			<c:set var="afterRemain" value="0"/>
			<c:if test="${twoPage.totalPage - afterIndex < 1}">
				<c:set var="afterRemain" value="${afterIndex - twoPage.totalPage}"/>
			</c:if>
			
			<!-- 分页导航开始部分 -->
			<c:choose>
				<c:when test="${preIndex - afterRemain <= 2}">
					<c:forEach begin="1" end="${twoPage.currentPage}" var="i">
						<c:choose>
							<c:when test="${twoPage.currentPage == i}">
								<span class="cur">${i}</span>
							</c:when>
							<c:otherwise>
								<a class="pageNavLink" href="javascript:void(0);" name="navi_a_2" relval="${i}">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<a class="pageNavLink" href="javascript:void(0);" name="navi_a_2" relval="1">1</a>
					<span>...</span>
					<c:forEach begin="${preIndex - afterRemain}" end="${twoPage.currentPage - 1}" var="i">
						<a class="pageNavLink" href="javascript:void(0);" name="navi_a_2" relval="${i}">${i}</a>
					</c:forEach>
					<span class="cur">${twoPage.currentPage}</span>
				</c:otherwise>
			</c:choose>
			
			<!-- 分页导航结束部分 -->
			<c:if test="${twoPage.currentPage < twoPage.totalPage}">
			<c:choose>
				<c:when test="${afterIndex + preRemain + 1 >= twoPage.totalPage}">
					<c:forEach begin="${twoPage.currentPage + 1}" end="${twoPage.totalPage}" var="i">
						<a class="pageNavLink" href="javascript:void(0);" name="navi_a_2" relval="${i}">${i}</a>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach begin="${twoPage.currentPage + 1}" end="${afterIndex + preRemain}" var="i">
						<a class="pageNavLink" href="javascript:void(0);" name="navi_a_2" relval="${i}">${i}</a>
					</c:forEach>
					<span>...</span>
					<a class="pageNavLink" href="javascript:void(0);" name="navi_a_2" relval="${twoPage.totalPage}">${twoPage.totalPage}</a>
				</c:otherwise>
			</c:choose>
			</c:if>
			<span><input type="text" size="1" id="gotoPage_2"></input><button type="button" id="goPageBtn_2">跳转</button></span>
<%-- 		</c:if> --%>
		<div style="display:none;">
			<input type="hidden" id="totalPage_2" value="${twoPage.totalPage}"/>
		</div>
	</div>

</body>
</html>
