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
    <script src="template/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/common/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="template/jquery-ui/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script type="text/javascript" src="js/stat/iuniOrderAreaStat.js?v=140417"></script>
    <script type="text/javascript">
        var basePath = "<%=basePath%>";
    </script>
</head>
<body>
<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top">
    <tbody>
    <tr>
        <td class="td01"></td>
        <td class="td02"><h3 class="topTitle fb f14">IUNI商城订单区域分布统计报表</h3></td>
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
                        <dd>IUNI商城报表</dd>
                        <dd>IUNI商城订单区域分布统计报表</dd>
                    </dl>
                </div>

                <div>
                    <input type="hidden" id="msg" value="<s:property value="msg"/>"/>
                </div>

                <div class="frame4" style="height:65px;">
                    <form action="" method="post" id="queryFrm">
                        <dl>
                            <dd class="ddl1">
                                <label>地址</label>
                            </dd>
                            <dd class="ddr1" style="width:90%;">
                                <select id="country" name="statParams['country']" data-action="country"
                                        class="radius_5 ui_hide">
                                    <option value="中国">中国</option>
                                </select>
                                <select id="province" name="statParams['province']" data-action="province"
                                        class="radius_5"></select>
                                <select id="city" name="statParams['city']" data-action="city"
                                        class="radius_5"></select>
                                <select id="district" name="statParams['district']" data-action="district"
                                        class="radius_5"></select>
                            </dd>
                        </dl>
                        <dl>
                            <dd class="ddl1">
                                <label for="sourceCode">订单来源</label>
                            </dd>
                            <dd class="ddr1">
                                <s:select id="sourceCode" list="orderSourceList" name="sourceCode" listKey="sourceCode"
                                          listValue="sourceName"/>
                            </dd>
                            <dd class="ddl1">
                                <label for="beginDate">统计日期开始</label>
                            </dd>
                            <dd class="ddr1">
                                <input class="Wdate1" name="statParams['beginDate']" id="beginDate" type="text"
                                       size="10"
                                       readonly="readonly" value="${statParams['beginDate']}"/>
                            </dd>
                            <dd class="ddl1">
                                <label for="endDate">统计日期结束</label>
                            </dd>
                            <dd class="ddr1">
                                <input class="Wdate1" name="statParams['endDate']" id="endDate" type="text" size="10"
                                       readonly="readonly" value="${statParams['endDate']}"/>
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
                    <s:hidden id="page1Index" name="page1Index" value="%{#attr.onePage.currentPage}"></s:hidden>
                    <s:hidden id="page2Index" name="page2Index" value="%{#attr.twoPage.currentPage}"></s:hidden>
                </div>

                <label class="lable3">汇总</label>
                <table class="listtable" style="width: 100%">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>统计时段</th>
                        <th>销售国家</th>
                        <th>销售省区</th>
                        <th>销售市区</th>
                        <th>销售区县</th>
                        <th>销售数量</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="#request.orderAreaSumList" status="s" id="orderAreaSum">
                        <tr>
                            <td class="mytd"><s:property value="rowNum"/></td>
                            <td class="mytd"><s:property value="statDate"/></td>
                            <td class="mytd"><s:property value="country"/></td>
                            <td class="mytd"><s:property value="province"/></td>
                            <td class="mytd"><s:property value="city"/></td>
                            <td class="mytd"><s:property value="district"/></td>
                            <td class="mytd"><s:property value="salesNum"/></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>

                <c:import url="/WEB-INF/views/common/pagelink_1.jsp"></c:import>

                <br/><br/>

                <label class="lable3">日明细</label>
                <table class="listtable" style="width: 100%">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>统计日期</th>
                        <th>销售国家</th>
                        <th>销售省区</th>
                        <th>销售市区</th>
                        <th>销售区县</th>
                        <th>销售数量</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="#request.orderAreaStatList" status="s" id="orderAreaStat">
                        <tr>
                            <td class="mytd"><s:property value="rowNum"/></td>
                            <td class="mytd"><s:property value="statDate"/></td>
                            <td class="mytd"><s:property value="country"/></td>
                            <td class="mytd"><s:property value="province"/></td>
                            <td class="mytd"><s:property value="city"/></td>
                            <td class="mytd"><s:property value="district"/></td>
                            <td class="mytd"><s:property value="salesNum"/></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>

                <c:import url="/WEB-INF/views/common/pagelink_2.jsp"></c:import>

            </div>
        </td>
        <td class="td03"></td>
    </tr>

    </tbody>
</table>

<!-- Query Validate Dialog -->
<div id="qv_dialog">
    <p id="qv_msg" style="color:red;text-align: center;"></p>
</div>

<%-- 	<script type="text/javascript" src="js/common/region_selector.js"></script> --%>

</body>
</html> 