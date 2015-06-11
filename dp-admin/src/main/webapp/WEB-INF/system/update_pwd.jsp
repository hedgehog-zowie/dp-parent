<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改密码</title>
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
<script type="text/javascript" language="javascript">
	function checkFrm() {
		var obj = document.updateFrm;
		if (obj.pwd1.value == "") {
			document.getElementById("msg").innerHTML = "原密码不能为空！";
			return false;
		} else if (obj.pwd2.value == "") {
			document.getElementById("msg").innerHTML = "新密码不能为空！";
			return false;
		} else if (obj.pwd2.value.length < 6) {
			document.getElementById("msg").innerHTML = "密码长度不足6位字符请重新输入！";
			return false;
		} else if (obj.password.value == "") {
			document.getElementById("msg").innerHTML = "确认新密码不能为空！";
			return false;
		} else if (obj.password.value.length < 6) {
			document.getElementById("msg").innerHTML = "确认新密码长度不足6位字符请重新输入！";
			return false;
		} else if (obj.pwd2.value != obj.password.value) {
			document.getElementById("msg").innerHTML = "两次输入的新密码不一致！";
			return false;
		} else {
			//obj.submit();
			return true;
		}
	}
</script>
<body>
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top">
		<tbody>
			<tr>
				<td class="td01"></td>
				<td class="td02"><h3 class="topTitle fb f14">修改密码</h3></td>
				<td class="td03"></td>
			</tr>
		</tbody>
	</table>
	<form action="<%=request.getContextPath()%>/updateResult.action"
		name="updateFrm" method="post" onsubmit="return checkFrm();">

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
									<td align="left">位置 > 商户资料 > 修改密码</td>
								</tr>
								<tr>
									<td align="left"><br /></td>
								</tr>
								<tr>
									<td><div
											style="color: red; font-size: 16px; font-weight: bold;"
											id="msg">
											<s:if test="msg==null">修改密码
											</s:if>
											<s:if test="msg!=null"><s:property value="msg" />
											</s:if>
										</div></td>
								</tr>

								<tr>
									<td
										style="height: 20px; background-color: #0093D4; width: 1000px;"
										align="center">&nbsp;</td>
								</tr>

								<tr>
									<td style="width: 1000px;" align="center">

										<table width="1000px;" cellpadding="0" cellspacing="0"
											style="border: 1px gray solid;">


											<tr>
												<td align="right" style="width: 50%"><br /><br />原密码：</td>
												<td align="left"><br /><br /><input type="password" name="pwd1" /> 
												 </td>
											</tr>
											<tr>
												<td align="right" style="width: 50%">新密码：</td>
												<td align="left"><input type="password" name="pwd2" maxlength="12"/> 
												 </td>
											</tr>
											<tr>
												<td align="right" style="width: 50%">确认新密码：</td>
												<td align="left"><input type="password" name="password" maxlength="12"/> 
												 </td>
											</tr>
											<tr>
												<td align="right" style="width: 50%"></td>
												<td align="left"><font color="red">注意：新旧密码不能相同！</font>
												 </td>
											</tr>
											<tr>
												<td align="right">&nbsp;&nbsp; <br />
												<br /></td>
												<td align="left" style="width: 50%"><br /><br /><input type="submit" value="  提  交  " />
													&nbsp;&nbsp;<input type="button" value="  返  回  " onclick="javascript:history.go(-1);" /><br />
												<br /></td>
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
	</form>
</body>
</html>
