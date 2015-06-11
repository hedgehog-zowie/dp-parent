<%@page import="com.iuni.dp.persist.common.constants.SysCons"%>
<%@page import="com.iuni.dp.persist.common.utils.ParseProperties"%>
<%@ page pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>金立信息告警系统平台</title>
<link href="<%=request.getContextPath()%>/view/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/template/javascript/jquery-1.3.2.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/template/javascript/checklogin.js"></script>
<script type="text/javascript">
$(document).ready(function(){ 

$("#username").select();
$("#username").focus();
$("#username").css({"border": "1px red solid"});

if(top.location != self.location){
	top.location = self.location;
}

}); 
function switchToPwd(){
	if(window.event.keyCode==13) { 
	$("#password").select();
    $("#password").focus();
    $("#password").css({"border": "1px red solid"}); 
	}
}
function switchToCode(){
	if(window.event.keyCode==13) { 
	$("#code").css({"border": "1px red solid"}); 
	 $("#code").select();
       $("#code").focus();
	}
}
function checklogin(){ 
	if(window.event.keyCode==13) { 
		 var realname = $.trim($("#username").val().replace(/^\s+|\s+$/g, ""));
		    var realname_length = realname.replace(/[^\x00-\xff]/g, 'xx').length;
		    if ((realname == null) || (realname == "")) {
		        $("#msg").text(" 登录名不能为空");
		        $("#username").select();
		         $("#username").focus();
		         $("#username").css({"border": "1px red solid"}); 
		        return false;
		    } else if ((realname_length > 50) || (realname_length < 5)) {
		        $("#msg").text("  登录姓名长度为5－50个字符");
		        $("#username").select();
		        $("#username").focus();
		        $("#username").css({"border": "1px red solid"}); 
		        return false;
		    } else {
		    	$("#username").css({"border": "1px #ccc solid"}); 
		    	var password = $("#password").val().replace(/^\s+|\s+$/g, "");
		    	var password_length = password.replace(/[^\x00-\xff]/g, 'xx').length;
		    	if ((password == null) || (password == "")) {
		    		$("#msg").text("  密码不能为空");
		    		$("#password").select();
			          $("#password").focus();
			          $("#password").css({"border": "1px red solid"}); 
		    		return false;
		    	} else if ((password_length <5) || (password_length > 20)) {
		    		$("#msg").text("  密码长度在5-20个字符之间");
		    		 $("#password").css({"border": "1px red solid"}); 
		    		 $("#password").select();
		 	          $("#password").focus();
		    		return false;
		    	} else {
		    		 $("#password").css({"border": "1px #ccc solid"}); 
			
					var code = $("#code").val().replace(/^\s+|\s+$/g, "");
					var code_length = code.replace(/[^\x00-\xff]/g, 'xx').length;
					if ((code == null) || (code == "")) {
						$("#msg").text("  验证码不能为空！");
						 $("#code").css({"border": "1px red solid"}); 
						 $("#code").select();
					        $("#code").focus();
						return false;
					} else if (code_length !=4) {
						$("#msg").text("  验证码应该有4个字符！");
						 $("#code").css({"border": "1px red solid"}); 
						 $("#code").select();
					        $("#code").focus();
					        	return false;
					} else {
						 $("#code").css({"border": "1px #ccc solid"}); 
						$("#msg").text("");
						document.forms[0].submit();
						return true;
					} 
		    	}
		    }
	} 
}

function changeCd(){
	var dt = new Date();
	var newSrc = "<%=request.getContextPath()%>/code.action?timestamp=" + dt.getMilliseconds();
	document.getElementById("codeImg").src = newSrc;
}
//window.onload=initial;
</script>
</head>
<%
	double d=Math.random();
%>
<body>
<s:form action="login" method="post">
	<div class="login_main">
    	<div class="login_tbg"></div>
        <div class="login_mbg">
        	<div class="login_frame">
            	<ul>
                	<li>
                    	<h4>账号：</h4>
                        <input type="text" value='<s:property value="user_name"/>' name="user_name" id="username" onkeypress="switchToPwd();" />
                    </li>
                    <li>
                    	<h4>密码：</h4>
                        <input type="password" value=''  name="password" id="password" onkeypress="switchToCode();"  />
                    </li>
                    <%
                    	System.out.println(ParseProperties.getPropertiesValue(SysCons.TESTMODEL));
                    %>
                    <%
                    	if(!"Y".equalsIgnoreCase(ParseProperties.getPropertiesValue(SysCons.TESTMODEL))){
                    %>
                    <li class="code">
                    	<h4>验证码：</h4>
                        <input type="text" value='<s:property value="code"/>'  name="code" id="code"  onkeypress="checklogin()" />
                        <img src="<%=request.getContextPath() %>/code.action?d="<%=d %> title="点击换一张" alt="验证码" id="codeImg"  onclick="changeCd()" />
                    </li>
                    <%} else{%>
                    <input type="hidden" value='1234'  name="code" id="code"/>
                    <%} %>
                    <li class="error_tips" style=""><div id="msg" style="color: red;"> <s:property  value="msg"/><%=request.getAttribute("frozenMsg") != null ? request.getAttribute("frozenMsg") : ""%> </div></li>
                    <li class="btn"><a href="#" onclick="return validate_login();">登 录</a></li>
                </ul>
            </div>
        </div>
        <div class="footer">Copyright &copy;2012-2015 gionee.com 粤ICP备05087105号</div>
    </div>
    </s:form>
</body>
</html>

 

 