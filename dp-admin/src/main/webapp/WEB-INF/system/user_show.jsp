<%@ page language="java" import="java.util.*,com.iuni.dp.persist.sys.model.User" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看资料</title>
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
<body>
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top">
		<tbody>
			<tr>
				<td class="td01"></td>
				<td class="td02"><h3 class="topTitle fb f14">查看资料</h3></td>
				<td class="td03"></td>
			</tr>
		</tbody>
	</table>
		<table border="0" cellspacing="0" cellpadding="0" class="adminMain_main">
			<tbody>
				<tr>
					<td class="td01"></td>
					<td class="adminMain_wrap">
						<div class="wrapMain"
							style="height: 1000px; text-align: center; vertical-align: middle;">
							<table style="text-align: center;">
								<tr>
									<td align="left">位置 > <a href="<%=request.getContextPath()%>/showUser.action">个人资料</a> > 查看资料</td>
								</tr>
								<tr>
									<td align="left"><br /></td>
								</tr>
								<tr>
									<td>
									    <div style="color: red; font-size: 16px; font-weight: bold;" id="msg">
											我的资料
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
												<td align="right" style="width: 50%">用户ID：</td>
												<td align="left"> ${user.user_id}</td>
											</tr>
										    <tr>
												<td align="right" style="width: 50%">用户类型：</td>
												<td align="left">
												<s:if test="#user"></s:if>
												 ${user.user_type==0?'金立操作员':'金立管理员'}
												</td>
											</tr>
											<tr>
												<td align="right" style="width: 50%">登录账号：</td>
												<td align="left"> ${user.user_name}</td>
											</tr>
											<tr>
												<td align="right" style="width: 50%">手机号码：</td>
												<td align="left">${user.mobile}</td>
											</tr>
											<tr>
												<td align="right" style="width: 50%">用户状态：</td>
												<td align="left">${user.status==1?'正常状态':'冻结状态'}
												</td>
											</tr>
											<tr>
												<td align="right" style="width: 50%">&nbsp;&nbsp;
												  <input type="button" value="  返  回  " onclick="javascript:history.go(-1);" />
											    </td>
												<td align="left">
												  &nbsp;&nbsp; 
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
</body>
</html>
