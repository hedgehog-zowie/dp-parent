<%@page pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>菜单列表</title>
    <link href="template/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="template/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="template/css/page_admin_main.css" rel="stylesheet" type="text/css"/>
    <link href="template/css/additional.css" rel="stylesheet" type="text/css"/>
    <link href="css/alarm/main.css?v=140429" rel="stylesheet" type="text/css"/>
    <link href="template/jquery-ui/css/start/jquery-ui-1.10.3.custom.min.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        td {
            height: 50px;
        }

        th {
            height: 80px;
            color: #0091D3;
        }
    </style>
    <script type="text/javascript" src="template/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="js/common/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="template/jquery-ui/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script type="text/javascript" src="js/stat/iuniSalesOfGoods.js?v=140532"></script>
    <script type="text/javascript">
        var basePath = "<%=basePath%>";
    </script>
</head>
<body>
<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top">
    <tbody>
    <tr>
        <td class="td01"></td>
        <td class="td02"><h3 class="topTitle fb f14">商品销售报表</h3></td>
        <td class="td03"></td>
    </tr>
    </tbody>
</table>
<table border="0" cellspacing="0" cellpadding="0" class="adminMain_main">
    <tbody>
    <tr>
        <td class="td01"></td>
        <td class="adminMain_wrap">
            <div class="wrapMain" style="height:auto;vertical-align: middle;">
                <div class="adminUp_wrap" style="height:24px;margin-bottom:10px;">
                    <dl class="adminPath clearfix2" style="width:50%;">
                        <dt>您现在的位置：</dt>
                        <dd>报表管理</dd>
                        <dd>IUNI商城销售报表</dd>
                        <dd>商品销售报表</dd>
                    </dl>
                </div>
                <div>
                    <input type="hidden" id="msg" value="<s:property value="msg"/>"/>
                </div>
                <div class="frame4" style="height:120px;">
                    <form action="" method="post" id="queryFrm">
                        <dl>
                            <dd class="ddl1">
                                <label for="beginDate">开始日期</label>
                            </dd>
                            <dd class="ddr1">
                                <input class="Wdate1" name="statParams['beginDate']" id="beginDate" type="text"
                                       size="10"
                                       readonly="readonly" value="${statParams['beginDate']}"/>
                            </dd>
                            <dd class="ddl1">
                                <label for="endDate">结束日期</label>
                            </dd>
                            <dd class="ddr1">
                                <input class="Wdate1" name="statParams['endDate']" id="endDate" type="text" size="10"
                                       readonly="readonly" value="${statParams['endDate']}"/>
                            </dd>
                            <dd class="ddr1" style="width:51%"></dd>
                        </dl>
                        <div style="height:60px">
                            <span class="ddl1" style="float:left; margin-left:1%; margin-bottom:10px;">
                                <label for="selectAll">订单来源</label>
                            </span>
                            <span class="ddr1" style="margin-left:1%; margin-bottom:10px;">
                                <input type="checkbox" name="selectAll" id="selectAll"/>
                                <label>全选</label>
                            </span>
                            <span class="ddr1" style="margin-left:1%; margin-bottom:10px;">
                                <s:checkboxlist name="orderSourceId"
                                                listKey="sourceCode" listValue="sourceName" list="orderSourceList"></s:checkboxlist>
                            </span>
                        </div>
                        <dl>
                            <dd class="ddl1">
                                <label>商品类型</label>
                            </dd>
                            <dd class="ddr1">
                                <div><s:select id="goodsTypeId" name="goodsType" list="goodsTypeMap" headerKey="0"
                                               headerValue="全部"></s:select></div>
                            </dd>
                            <dd class="ddl1" id="skuNameCodeDiv1">
                                <label for="skuNameCode">规格型号</label>
                            </dd>
                            <dd class="ddr1" id="skuNameCodeDiv2">
                                <s:select id="skuNameCode" name="skuNameCode" listKey="id" listValue="skuName"
                                          list="skuNameList" headerKey="0" headerValue="全部"></s:select>
                            </dd>
                            <dd class="ddl1" style="width:8%">
                                <label for="dateStyle">展示方式</label>
                            </dd>
                            <dd class="ddr1">
                                <s:select id="dateStyle" name="dateStyle" list="dateStyleMap"></s:select>
                            </dd>
                            <dd class="ddl1">
                                <input class="btn4" type="button" id="btn_query" value="查询"/>
                            </dd>
                            <dd class="ddl3">
                                <input class="btn6" type="button" id="btn_exportExcel" value="导出EXCEL"/>
                            </dd>
                        </dl>
                        <div style="display: none;">
                            <s:hidden id="flag_h" name="flag" value="%{#parameters.flag}"></s:hidden>
                        </div>
                    </form>
                </div>

                <div style="display: none;">
                    <s:iterator value="statParams" id="paramsEntry">
                        <s:hidden id="%{#paramsEntry.key}_h" name="%{#paramsEntry.key}"
                                  value="%{#paramsEntry.value}"></s:hidden>
                    </s:iterator>

                </div>

                <table class="listtable" style="width: 100%">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>支付日期</th>
                        <th>销售渠道</th>
                        <th>规格型号</th>
                        <th>商品</th>
                        <th>SKU</th>
                        <th>数量</th>
                        <th>支付金额</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="#request.salesOfGoodsList" status="s" id="salesOfGoods">
                        <tr>
                            <td class="mytd" align="center"><s:property value="RN"/></td>
                                <%-- <td class="mytd" ><s:date name="paytime" format="yyyy-MM-dd"/></td> --%>
                            <td class="mytd"><s:property value="paytime"/></td>
                            <td class="mytd"><s:property value="orderSource"/></td>
                            <td class="mytd"><s:property value="goodsName"/></td>
                            <td class="mytd"><s:property value="wareName"/></td>
                            <td class="mytd"><s:property value="sku"/></td>
                            <td class="mytd"><s:property value="num"/></td>
                            <td class="mytd"><s:property value="salePrice"/></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
                <c:import url="/WEB-INF/views/common/pagelink.jsp"></c:import>

            </div>
        </td>
        <td class="td03"></td>
    </tr>

    </tbody>
</table>

</body>
</html> 