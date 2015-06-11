<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>数据查询</title>
<link href="<%=request.getContextPath() %>/template/css/page_querySql.css" rel="stylesheet" type="text/css" /> 
<link href="<%=request.getContextPath() %>/template/css/base.css" rel="stylesheet" type="text/css" /> 
<link href="<%=request.getContextPath() %>/template/css/common.css" rel="stylesheet" type="text/css" /> 
<link href="<%=request.getContextPath() %>/template/css/page_admin_main.css" rel="stylesheet" type="text/css" />  
<script src="<%=request.getContextPath()%>/template/javascript/jquery-1.3.2.min.js" type="text/javascript"></script>
	
<script type="text/javascript">
$(function(){
	$("tr:odd").each(function(){this.style.backgroundColor  =  '#fff' })   
})	
</script>

</head>
<body> 
<table class="adminMain_top">
	<tbody><tr>
			<td class="td01"></td>
			<td class="td02"><h3 class="topTitle fb f14">sql 语句</h3></td>
			<td class="td03"></td></tr>
	</tbody>
</table>

<div id="infoMessage" style="color:red">
	<s:iterator value="infoMessages">
		<s:property value="value"/><br />
	</s:iterator>
</div>
<div style="color:red">
    <s:fielderror />
    <s:actionerror/>
</div>

<div style="padding-left: 5px;">
	<s:form id="querySql" name="querySql" action="querySql" method="post">
		<s:textarea name="queryStr" cols="100" rows="10" id="queryStr" ></s:textarea>
		<s:submit id="querySql_submit1" name="submit1" value="查询" />
	</s:form>
</div>

<s:if test="queryResult.size!=0">
<table height="50%" width="95%" style="TABLE-LAYOUT:FIXED; BORDER-COLLAPSE:COLLAPSE;" class="tb1"> 
	<TR> 
      <TD><DIV id="colDiv" style="OVERFLOW-Y: AUTO; OVERFLOW-X: AUTO; WIDTH: 100%; HEIGHT: 100%"> 
       
          <TABLE style="BORDER-COLLAPSE: COLLAPSE;" class="table_bg01">  
			<s:iterator value="queryResult" status="count" id="obj" begin="0" end="0">
              <TR class="headtr" > 
                <s:iterator value="obj" var="temp"><th><s:property value="#temp.key"/></th></s:iterator>
              </TR> 
             </s:iterator>
             
			 <s:iterator value="queryResult" status="count" id="obj">
              <TR class="ctr"> 
				<s:iterator value="obj" var="temp"><td><s:property value="#temp.value"/></td></s:iterator>
              </TR> 
             </s:iterator>
          </TABLE> 
          
        </DIV></TD> 
    </TR> 

</table> 
<div align="right" style="width:85%;">共显示[${size}]条结果集</div>
</s:if>
</body>
</html>
