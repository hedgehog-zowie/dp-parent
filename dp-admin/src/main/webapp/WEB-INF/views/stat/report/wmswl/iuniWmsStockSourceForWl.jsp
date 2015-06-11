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
    <script type="text/javascript" src="js/stat/wmswl/iuniWmsStockSourceForWl.js?v=140708"></script>
    <script type="text/javascript">
        var basePath = "<%=basePath%>";
    </script>
</head>
<body>
<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top">
    <tbody>
    <tr>
        <td class="td01"></td>
        <td class="td02"><h3 class="topTitle fb f14">仓库出入库来源汇总报表</h3></td>
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
                        <dd>IUNI WMS运营报表（物流）</dd>
                        <dd>仓库出入库来源汇总报表</dd>
                    </dl>

                </div>

                <div>
                    <input type="hidden" id="msg" value="<s:property value="msg"/>"/>
                </div>

                <div class="frame4" style="height:70px;">
                    <form action="" method="post" id="queryFrm">
                        <dl>
                            <dd class="ddl1" style="width:8%">
                                <label for="beginDate">开始日期</label>
                            </dd>
                            <dd class="ddr1">
                                <input class="Wdate1" name="statParams['beginDate']" id="beginDate" type="text"
                                       size="10"
                                       readonly="readonly" value="${statParams['beginDate']}"/>
                            </dd>
                            <dd class="ddl1" style="width:8%">
                                <label for="endDate">结束日期</label>
                            </dd>
                            <dd class="ddr1">
                                <input class="Wdate1" name="statParams['endDate']" id="endDate" type="text" size="10"
                                       readonly="readonly" value="${statParams['endDate']}"/>
                            </dd>
                            <dd class="ddl1" style="width:8%">
                                <label for="warehouseCode">仓库</label>
                            </dd>
                            <dd class="ddr1">
                                <s:select name="warehouseCode" listKey="warehouseCode" listValue="warehouseName"
                                          list="warehouseList"></s:select>
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
                        <s:hidden id="page1Index" name="page1Index" value="%{#attr.onePage.currentPage}"></s:hidden>
                        <s:hidden id="page2Index" name="page2Index" value="%{#attr.twoPage.currentPage}"></s:hidden>
                    </s:iterator>
                </div>

                <label class="lable3">汇总</label>
                <table class="listtable" style="width: 100%">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>时间段</th>
                        <th>仓库</th>
                        <th>订单来源</th>
                        <th>业务类型</th>
                        <th>SKU</th>
                        <th>商品类型</th>
                        <th>名称规格</th>
                        <th>ERP物料编码</th>
                        <th>单位</th>
                        <th>数量</th>
                    </tr>
                    </thead>

                    <tbody>
                    <s:iterator value="#request.iuniWmsStockSourceForWlSum" status="s" id="iuniWmsStockSourceForWlSum">
                        <tr>
                            <td class="mytd"><s:property value="rowNum"/></td>
                                <%--<td class="mytd"><s:date name="date" format="yyyy-MM-dd"/></td>--%>
                            <td class="mytd"><s:property value="DATETIME"/></td>
                            <td class="mytd"><s:property value="WAREHOUSE_NAME"/></td>
                            <td class="mytd"><s:property value="SOURCE_NAME"/></td>
                            <td class="mytd"><s:property value="BIZ_TYPE"/></td>
                            <td class="mytd"><s:property value="SKU_CODE"/></td>
                            <td class="mytd"><s:property value="CAT_NAME"/></td>
                            <td class="mytd"><s:property value="SKU_NAME"/></td>
                            <td class="mytd"><s:property value="MATERIAL_CODE"/></td>
                            <td class="mytd"><s:property value="MEASURE_UNIT"/></td>
                            <td class="mytd"><s:property value="QUANTITY"/></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>

                <c:import url="/WEB-INF/views/common/pagelink_1.jsp"></c:import>

                <br/><br/>

                <label class="lable3">明细</label>
                <table class="listtable" style="width: 100%">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>日期</th>
                        <th>仓库</th>
                        <th>订单来源</th>
                        <th>业务类型</th>
                        <th>SKU</th>
                        <th>商品类型</th>
                        <th>名称规格</th>
                        <th>ERP物料编码</th>
                        <th>单位</th>
                        <th>数量</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="#request.iuniWmsStockSourceForWl" status="s" id="iuniWmsStockSourceForWl">
                        <tr>
                            <td class="mytd"><s:property value="rowNum"/></td>
                                <%--<td class="mytd"><s:date name="date" format="yyyy-MM-dd"/></td>--%>
                            <td class="mytd"><s:property value="DATETIME"/></td>
                            <td class="mytd"><s:property value="WAREHOUSE_NAME"/></td>
                            <td class="mytd"><s:property value="SOURCE_NAME"/></td>
                            <td class="mytd"><s:property value="BIZ_TYPE"/></td>
                            <td class="mytd"><s:property value="SKU_CODE"/></td>
                            <td class="mytd"><s:property value="CAT_NAME"/></td>
                            <td class="mytd"><s:property value="SKU_NAME"/></td>
                            <td class="mytd"><s:property value="MATERIAL_CODE"/></td>
                            <td class="mytd"><s:property value="MEASURE_UNIT"/></td>
                            <td class="mytd"><s:property value="QUANTITY"/></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>

                <c:import url="/WEB-INF/views/common/pagelink_2.jsp"></c:import>

                <label style="color: #FF0000">
                    注意：
                    该表“业务类型”为“换货退回”的数据在2014年11月6日前均统计为“退货”。
                </label>

            </div>
        </td>
        <td class="td03"></td>
    </tr>

    </tbody>
</table>

</body>
</html> 