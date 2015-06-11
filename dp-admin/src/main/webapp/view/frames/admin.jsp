<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<title>数据统计分析管理平台</title> 
<link href="<%=request.getContextPath() %>/template/css/base.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="<%=request.getContextPath() %>/template/javascript/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/template/javascript/tab.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/template/javascript/weebox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/template/javascript/bgiframe.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/template/My97DatePicker/WdatePicker.js"></script>
</head>
  <frameset rows="58,*" cols="*" frameborder="no" border="0" framespacing="0"> 
	  <frame src="<%=request.getContextPath() %>/view/frames/admin_top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" /> 
	  <frameset cols="180,*" frameborder="no" border="0" framespacing="0">
	    	<frameset framespacing="0" border="0" frameborder="no" rows="40,*" id="leftFrame"> 
	  			<frame src="<%=request.getContextPath() %>/view/frames/admin_left_top.jsp" scrolling="no" title="left_topFrame" id="left_topFrame" noresize="noresize" name="left_topFrame" />
				<frame src="<%=request.getContextPath() %>/sso/getMenus.action" scrolling="yes" title="left_botFrame" id="left_botFrame" name="left_botFrame" />
			</frameset>
	        <frameset cols="10,*" frameborder="no" border="0" framespacing="0"> 
	            <frame src="<%=request.getContextPath() %>/view/frames/admin_center.jsp" name="centerFrame" scrolling="no" id="centerFrame" title="centerFrame" /> 
	            <frameset rows="*,26" cols="*" frameborder="no" border="0" framespacing="0"> 
	                <frame src="<%=request.getContextPath() %>/view/frames/admin_main.jsp" name="mainFrame" id="mainFrame" title="mainFrame" /> 
	                <frame src="<%=request.getContextPath() %>/view/frames/admin_bottom.jsp" name="bottomFrame" scrolling="no" id="bottomFrame" title="bottomFrame" /> 
	            </frameset> 
	        </frameset> 
	  </frameset> 
 </frameset> 
 <noframes>
   <body></body>
 </noframes> 
</html>