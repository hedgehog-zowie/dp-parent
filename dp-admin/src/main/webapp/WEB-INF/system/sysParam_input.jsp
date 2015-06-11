<%@page language="java" import="java.util.*,com.iuni.dp.persist.sys.model.User" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统参数管理</title>
<link href="<%=request.getContextPath()%>/template/css/base.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/template/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/template/css/page_admin_main.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/template/javascript/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/template/My97DatePicker/WdatePicker.js" type="text/javascript"></script>

<script type="text/javascript" language="javascript">
	function checkFrm() {
		if ($.trim($("#paramName").val()) == "") {
			document.getElementById("msg").innerHTML = "参数名称不能为空！";
			return false;
		} else if ($.trim($("#paramVal").val()) == "") {
			document.getElementById("msg").innerHTML = "参数值不能为空！";
			return false;
		} else if ($.trim($("#paramDesc").val()) == "") {
			document.getElementById("msg").innerHTML = "参数描述不能为空！";
			return false;
		} else {
			return true;
		}
	}
	
	function trurnBack(){
		location.href = "<%=request.getContextPath()%>/showSysParam.action";
	}
	
	function addItem(){
		if(!checkFrm()){
			return false;
		}
		var obj = document.mainFrm;
		obj.submit();
		 
	}
 
</script>
</head>
<body>
	<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top">
		<tbody>
			<tr>
				<td class="td01"></td>
				<td class="td02"><h3 class="topTitle fb f14">添加系统参数</h3></td>
				<td class="td03"></td>
			</tr>
		</tbody>
	</table>
	<form action="<%=request.getContextPath()%>/saveSysParam.action" id="mainFrm" name="mainFrm" method="post">

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
									<td align="left">位置 > 系统管理 > 添加系统参数</td>
								</tr>
								<tr>
									<td align="left"><br /></td>
								</tr>
								<tr>
									<td><div
											style="color: red; font-size: 16px; font-weight: bold;"
											id="msg">
											<s:property value="msg" />
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
												<td align="right" style="width: 50%"><br /><br />参数名：</td>
												<td align="left"><br /><br /><input name="sysParam.paramName" id="paramName" /></td>
											</tr>
											<tr>
												<td align="right" style="width: 50%">参数值：</td>
												<td align="left"><input name="sysParam.paramVal" id="paramVal"  /></td>
											</tr>
											<tr>
												<td align="right" style="width: 50%">参数描述：</td>
												<td align="left"><input name="sysParam.paramDesc" id="paramDesc"   /></td>
											</tr>
 
											<tr>
												<td align="right" style="width: 50%">&nbsp;&nbsp; <br />
												<br /></td>
												<td align="left">
												<br /><br />
												<input type="button" value=" 提  交  " onclick="addItem()"/>
													&nbsp;&nbsp; <input type="reset" value="  重  置  " />
													&nbsp;&nbsp; <input type="button" value="  返  回  " onclick="trurnBack()" />
													<br />
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
