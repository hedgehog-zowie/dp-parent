<%@ page language="java" import="java.util.*,com.iuni.dp.persist.sys.model.User" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改账号</title>
<style type="text/css">
td{
line-height: 30px;
font-size: 13px;
}
</style>
<link href="<%=request.getContextPath()%>/template/css/base.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/template/css/common.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/template/css/page_admin_main.css"
	rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript" src="<%=request.getContextPath() %>/template/javascript/jquery-1.3.2.min.js"></script>
<script type="text/javascript" language="javascript">

function checkFrm() {
		var obj = document.userFrm;
		var reg= /^1[3458]{1}[0-9]{9}$/;
		var emailreg = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+(\.[a-zA-Z0-9._-]+)+$/;
		var telReg =  /^[0-9-]*$/;
		
		/* if(!emailreg.test(obj.user_name.value)){
			document.getElementById("msg").innerHTML = "登录账号必须为Email地址！";
			return false;
		}  */
		if(obj.user_name.value==""){
			document.getElementById("msg").innerHTML = "登录账号不能为空！";
			return false;
		} else if (obj.user_name.value.length>50) {
			document.getElementById("msg").innerHTML = "登录账号长度请不要超过50位！";
			return false;
		} else if ($.trim(obj.password.value).length != 0  && (obj.password.value.length<6||obj.password.value.length>12)) {
			document.getElementById("msg").innerHTML = "登录密码长度在6-12位！";
			return false;
		} else if (obj.mobile.value == "") {
			document.getElementById("msg").innerHTML = "联系手机不能为空！";
			return false;
		} else if (!reg.test(obj.mobile.value)) {
			document.getElementById("msg").innerHTML = "联系手机号码不合法！";
			return false;
		} else {
			//obj.submit();
			return true;
		}
	}

/*
	function checkFrm() {
		var obj = document.userFrm;
		if (obj.user_name.value == "") {
			document.getElementById("msg").innerHTML = "账号不能为空！";
			return false;
		} else if (obj.password.value == "") {
			document.getElementById("msg").innerHTML = "密码不能为空！";
			return false;
		} 
		
		else if (obj.merchant_code.value == "") {
			document.getElementById("msg").innerHTML = "商户编码不能为空！";
			return false;
		} 
		else {
			//obj.submit();
			return true;
		}
	}

*/

	function switchAll(name) {
		obj = eval(name);
		if (typeof (obj.length) == "undefined") {
			obj.checked = !obj.checked;
		} else {
			for ( var i = 0; i < obj.length; i++)
				obj[i].checked = !obj[i].checked;
		}
	}
	
	function trunBack(){
		location.href = "<%=request.getContextPath()%>/getAllUserPage.action?user_type=${user_type}";
	}
</script>
<body>
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top">
		<tbody>
			<tr>
				<td class="td01"></td>
				<td class="td02"><h3 class="topTitle fb f14">修改账号</h3></td>
				<td class="td03"></td>
			</tr>
		</tbody>
	</table>
	<form action="<%=request.getContextPath()%>/updateUserResult.action"
		name="userFrm" method="post" onsubmit="return checkFrm();">

		<table border="0" cellspacing="0" cellpadding="0"
			class="adminMain_main">
			<tbody>
				<tr>
					<td class="td01"></td>
					<td class="adminMain_wrap">
						<div class="wrapMain"
							style="height: 1000px; text-align: center; vertical-align: middle;">
							<table style="text-align: center;">
								<tr>
									<td align="left">位置 > <a href="<%=request.getContextPath()%>/getAllUser.action">账号管理</a> > 修改账号</td>
								</tr>
								<tr>
									<td align="left"><br /></td>
								</tr>
								<tr>
									<td><div
											style="color: red; font-size: 16px; font-weight: bold;"
											id="msg">											
											<s:if test="msg==null">修改账号
											</s:if>
											<s:if test="msg!=null"><s:property value="msg" />
											</s:if>
										</div></td>
								</tr>
								<tr>
									<td style="height: 20px; background-color: #0093D4; width: 1000px;" align="center">&nbsp;</td>
								</tr>
								<tr>
									<td style="width: 1000px;" align="center">
										<table width="1000px;" cellpadding="0" cellspacing="0" style="border: 1px gray solid;">
											 <input type="hidden" name="user_type" value='<s:property value="user_type" />'/>
											 <input type="hidden" name="user_id" value='<s:property value="user_id" />' />
											<tr>
												<td align="right" style="width: 50%">登录账号（Email）：<font color="red">*</font></td>
												<td align="left"><input name="user_name" value='<s:property value="user_name" />'/></td>
											</tr>
											<tr>
												<td align="right" style="width: 50%">登录密码：</td>
												<td align="left"><input name="password" /></td>
											</tr>
											<tr>
												<td align="right" style="width: 50%">联系手机：<font color="red">*</font></td>
												<td align="left"><input name="mobile" value='<s:property value="mobile" />' maxlength="11"/></td>
											</tr>
											<tr>
												<td align="right" style="width: 50%">用户类型：</td>
												<td align="left"> 
												  <s:if test="user_type == 1">金立管理员</s:if>
												  <s:if test="user_type == 0">金立操作员</s:if>
												</td>
											</tr>
											<tr>
												<td align="right" style="width: 50%">用户状态：</td>
												<td align="left"> 
													<select name="status">
													<option value="1" 
													<s:if test="status==1">selected="selected"</s:if>
													>--正常状态--</option>
													<option value="0"
													<s:if test="status==0">selected="selected"</s:if>
													>--冻结状态--</option>
												</select>
												</td>
											</tr>
											<tr>
												<td align="right" style="width: 50%">&nbsp;&nbsp; <br />
												<br /></td>
										 
												<td align="left">
													<br /><br />
													<input type="submit" value="  提  交  " />
														&nbsp;&nbsp; 
													<input type="button" value="  返  回  " onclick="trunBack()"/><br />
													<br />
											   </td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
					</td>
					<td class="td03"></td>
				</tr>
			</tbody>
		</table>
		<input type="hidden" name="pageIndex" id="pageIndex"  value='${pageIndex}'/>
		 
	</form>
</body>
</html>
