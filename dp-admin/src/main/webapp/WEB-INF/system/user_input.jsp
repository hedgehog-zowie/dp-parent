<%@page language="java" import="java.util.*,com.iuni.dp.persist.sys.model.User" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加账号</title>
<style type="text/css">
td{
line-height: 30px;
font-size: 13px;
}
</style>
<link href="<%=request.getContextPath()%>/template/css/base.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/template/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/template/css/page_admin_main.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript" language="javascript">
function checkFrm() {
		var obj = document.userFrm;
		var reg= /^1[3458]{1}[0-9]{9}$/;
		var emailreg = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+(\.[a-zA-Z0-9._-]+)+$/;
		var telReg =  /^[0-9-]*$/; // /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{2,}))?$/;
		 
		/* if(!emailreg.test(obj.user_name.value)){
			document.getElementById("msg").innerHTML = "登录账号必须为Email地址！";
			return false;
		} */
		if(obj.user_name.value==""){
			document.getElementById("msg").innerHTML = "登录账号不能为空！";
			return false;
		} else if (obj.user_name.value.length>50) {
			document.getElementById("msg").innerHTML = "登录账号长度不能大于50位！";
			return false;
		} else if (obj.password.value == "") {
			document.getElementById("msg").innerHTML = "登录密码不能为空！";
			return false;
		} else if (obj.password.value.length<6||obj.password.value.length>12) {
			document.getElementById("msg").innerHTML = "登录密码长度在6-12位！";
			return false;
		} else if (obj.mobile.value == "") {
			document.getElementById("msg").innerHTML = "联系手机不能为空！";
			return false;
		} else if (!reg.test(obj.mobile.value)) {
			document.getElementById("msg").innerHTML = "联系手机号码不合法！";
			return false;
		} else if (obj.contactName.value == "") {
			document.getElementById("msg").innerHTML = "联系人不能为空！";
			return false;
		} else if (obj.phone.value != "" && !telReg.test(obj.phone.value)) {
			document.getElementById("msg").innerHTML = "固定电话不合法！";
			return false;
		} else {
			obj.submit();
			return true;
		}
	}
 
	function changeMerchant(val){
		if(val == "1"){
			document.userFrm.merchant.disabled=true;
			document.userFrm.merchant_code.readOnly="readonly";
			document.userFrm.merchant.value = "";
			document.userFrm.merchant_code.value = "";
		}else{
			document.userFrm.merchant.disabled=false;
			document.userFrm.merchant_code.readOnly="";
		}
	}

	function init(){
		//document.userFrm.merchant_name.disabled=true;
		//document.userFrm.merchant_code.disabled=true;
		//if(document.userFrm.merchant_code.value != "") {
		//	document.userFrm.user_type.value = "0";
		//}
	}
	
	function setMerchantCode(val){
		document.userFrm.merchant_code.value=val;
	}
	 
	function turnBack(){
		location.href = "<%=request.getContextPath()%>/getAllUserPage.action?user_type=${user_type}";
	}
</script>
<body onload="init()">
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top">
		<tbody>
			<tr>
				<td class="td01"></td>
				<td class="td02"><h3 class="topTitle fb f14">添加账号</h3></td>
				<td class="td03"></td>
			</tr>
		</tbody>
	</table>
	<form action="<%=request.getContextPath()%>/addUserResult.action" name="userFrm" method="post" onsubmit="checkFrm()">
		<table border="0" cellspacing="0" cellpadding="0" class="adminMain_main">
			<tbody>
				<tr>
					<td class="td01"></td>
					<td class="adminMain_wrap">
						<div class="wrapMain" style="height: 1000px; text-align: center; vertical-align: middle;">
							<table style="text-align: center;">
								<tr>
									<td align="left">位置 > <a href="<%=request.getContextPath()%>/getAllUser.action">系统管理</a> > 添加账号</td>
								</tr>
								<tr>
									<td align="left"><br /></td>
								</tr>
								<tr>
									<td>
									   <div style="color: red; font-size: 16px; font-weight: bold;" id="msg">											
											<s:if test="msg==null">添加账号
											</s:if>
											<s:if test="msg!=null"><s:property value="msg" />
											</s:if>
										</div>
								    </td>
								</tr>
								<tr>
									<td style="height: 20px; background-color: #0093D4; width: 1000px;" align="center">&nbsp;</td>
								</tr>
								<tr>
									<td style="width: 1000px;" align="center">
										<table width="1000px;" cellpadding="0" cellspacing="0" style="border: 1px gray solid;">
											<tr>
												<td align="right" style="width: 50%">用户类型：</td>
												<td align="left"> 
												<s:if test="user_type == 1">金立管理员</s:if>
												<s:if test="user_type == 0">金立操作员</s:if>
												<input type="hidden" name="user_type" value='<s:property value="user_type" />'/>
												</td>
											</tr>
											<tr>
												<td align="right" style="width: 50%">登录账号（Email）：<font color="red">*</font></td>
												<td align="left"><input name="user_name" maxlength="50"/></td>
											</tr>
											<tr>
												<td align="right" style="width: 50%">登录密码：<font color="red">*</font></td>
												<td align="left"><input name="password" /></td>
											</tr>
											<tr>
												<td align="right" style="width: 50%">联系手机：<font color="red">*</font></td>
												<td align="left"><input name="mobile" maxlength="11"/></td>
											</tr>
											<tr>
												<td align="right" style="width: 50%">固定电话：</td>
												<td align="left"><input name="phone"/>(区号、分机号请用"-"连接)</td>
											</tr>
											<tr>
												<td align="right" style="width: 50%">联 系 人：<font color="red">*</font></td>
												<td align="left"><input name="contactName" maxlength="50"/></td>
											</tr>
											<tr>
												<td align="right" style="width: 50%">用户状态：<font color="red">*</font></td>
												<td align="left"> 
													<select name="status">
													<option value="1">--正常状态--</option>
													<option value="0">--冻结状态--</option>
												</select>
												</td>
											</tr>
											<tr>
												<td align="right" style="width: 50%">&nbsp;&nbsp; <br /><br /></td>
												<td align="left">
												<br /><br />
												<input type="button" value="  提  交  " onclick="checkFrm()" />
													&nbsp;&nbsp; <input type="button" value="  返  回  " onclick="turnBack()" /><br /><br />
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
