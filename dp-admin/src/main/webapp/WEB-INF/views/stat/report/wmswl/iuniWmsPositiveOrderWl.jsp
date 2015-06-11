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
    <script type="text/javascript" src="js/stat/wmswl/iuniWmsPositiveOrderWl.js?v=140532"></script>
    <script type="text/javascript">
        var basePath = "<%=basePath%>";
    </script>
</head>
<body>
<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top">
    <tbody>
    <tr>
        <td class="td01"></td>
        <td class="td02"><h3 class="topTitle fb f14">正向订单时效统计表</h3></td>
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
                        <dd>IUNI WMS运营报表</dd>
                        <dd>正向订单时效统计表</dd>
                    </dl>
                </div>

                <div>
                    <input type="hidden" id="msg" value="<s:property value="msg"/>"/>
                </div>

                <div class="frame4" style="height:70px;">
                    <form action="" method="post" id="queryFrm">
                        <dl>
                            <dd class="ddl1" style="width:8%">
                                <label for="beginDate">统计日期开始</label>
                            </dd>
                            <dd class="ddr1">
                                <input class="Wdate1" name="statParams['beginDate']" id="beginDate" type="text"
                                       size="10"
                                       readonly="readonly" value="${statParams['beginDate']}"/>
                            </dd>
                            <dd class="ddl1" style="width:8%">
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
                </div>

                <table class="listtable" style="width: 100%">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>订单号</th>
                        <th>出库日期</th>
                        <th>已筛单时间</th>
                        <th>已打单时间</th>
                        <th>配货中时间</th>
                        <th>已配货时间</th>
                        <th>待出库时间</th>
                        <th>已出库时间</th>
                        <th>已筛单-已打单时长</th>
                        <th>已打单-配货中时长</th>
                        <th>配货中-已配货时长</th>
                        <th>已配货 -待出库时长</th>
                        <th>待出库-已出库时长</th>
                        <th>合计时长</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="#request.posOrderList" status="s" id="posOrder">
                        <tr>
                            <td class="mytd"><s:property value="RN"/></td>
                            <td class="mytd"><s:property value="orderCode"/></td>
                            <td class="mytd"><s:date name="opTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                            <td class="mytd"><s:date name="time1" format="yyyy-MM-dd HH:mm:ss"/></td>
                            <td class="mytd"><s:date name="time2" format="yyyy-MM-dd HH:mm:ss"/></td>
                            <td class="mytd"><s:date name="time3" format="yyyy-MM-dd HH:mm:ss"/></td>
                            <td class="mytd"><s:date name="time4" format="yyyy-MM-dd HH:mm:ss"/></td>
                            <td class="mytd"><s:date name="time5" format="yyyy-MM-dd HH:mm:ss"/></td>
                            <td class="mytd"><s:date name="time6" format="yyyy-MM-dd HH:mm:ss"/></td>
                            <td class="mytd"><s:property value="time21"/>分</td>
                            <td class="mytd"><s:property value="time32"/>分</td>
                            <td class="mytd"><s:property value="time43"/>分</td>
                            <td class="mytd"><s:property value="time54"/>分</td>
                            <td class="mytd"><s:property value="time65"/>分</td>
                            <td class="mytd"><s:property value="time61"/>分</td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
                <c:import url="/WEB-INF/views/common/pagelink.jsp"></c:import>
                <div>时长单位：分钟</div>
            </div>
        </td>
        <td class="td03"></td>
    </tr>

    </tbody>
</table>

</body>
</html> 