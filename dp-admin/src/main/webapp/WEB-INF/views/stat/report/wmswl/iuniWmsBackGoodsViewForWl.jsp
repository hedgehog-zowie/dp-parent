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
    <script type="text/javascript" src="js/stat/wmswl/iuniWmsBackGoodsViewForWl.js?v=140532"></script>
    <script type="text/javascript">
        var basePath = "<%=basePath%>";
    </script>
</head>
<body>
<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top">
    <tbody>
    <tr>
        <td class="td01"></td>
        <td class="td02"><h3 class="topTitle fb f14">退货明细报表（物流）</h3></td>
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
                        <dd>退货明细报表（物流）</dd>
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
                                       readonly="readonly" value="${statParams['beginDate']}" />
                            </dd>
                            <dd class="ddl1" style="width:8%">
                                <label for="endDate">统计日期结束</label>
                            </dd>
                            <dd class="ddr1">
                                <input class="Wdate1" name="statParams['endDate']" id="endDate" type="text" size="10"
                                       readonly="readonly" value="${statParams['endDate']}" />
                            </dd>
                            <dd class="ddl1">
                                <input class="btn4" type="button" id="btn_query" value="查询"/>
                            </dd>
                            <dd class="ddl3">
                                <input class="btn6" type="button" id="btn_exportExcel" value="导出EXCEL"/>
                            </dd>
                            <dd class="ddl1" style="width: 30%"></dd>
                            <dd class="ddl1" style="width:8%">
                                <label for="selectAll">销售渠道/类型</label>
                            </dd>
                            <dd class="ddl1" style="width:8%">
                                <input style="width: 30%" onclick="selectAll()" type="checkbox" name="selectAll"
                                       id="selectAll"/>
                                <label>全选</label>
                            </dd>
                            <%--<s:checkboxlist name="backChannelOfReceiveCodes" listKey="channelCode"--%>
                                            <%--listValue="channelName" list="backChannelOfReceiveList">--%>
                            <%--</s:checkboxlist>--%>
                            <s:checkboxlist name="backChannelOfReceiveCodes" listKey="sourceCode"
                                            listValue="sourceName"
                                            list="transferSourceList">
                            </s:checkboxlist>
                            <br/>
                            <label style="width: 8%"></label>
                            <%--<s:checkboxlist name="backChannelOfbackCodes" listKey="channelCode" listValue="channelName"--%>
                                            <%--list="backChannelOfbackList">--%>
                            <%--</s:checkboxlist>--%>
                            <s:checkboxlist name="backChannelOfbackCodes" listKey="sourceCode"
                                            listValue="sourceName"
                                            list="orderSourceList">
                            </s:checkboxlist>
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
                        <th>日期</th>
                        <th>退货渠道</th>
                        <th>订单号</th>
                        <th>客户姓名</th>
                        <th>SKU</th>
                        <th>名称规格</th>
                        <th>数量</th>
                        <th>退回原因</th>
                        <th>备注</th>
                        <th>发票</th>
                        <th>退回物流</th>
                        <th>退回单号</th>
                        <th>IMEI</th>
                        <th>快递签收人</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="#request.backGoodsList" status="s" id="backGoods">
                        <tr>
                            <td class="mytd"><s:property value="RN"/></td>
                            <td class="mytd"><s:date name="time" format="yyyy-MM-dd HH:mm:ss"/></td>
                            <td class="mytd"><s:property value="backChannel"/></td>
                            <td class="mytd"><s:property value="orderCode"/></td>
                            <td class="mytd"><s:property value="orderUser"/></td>
                            <td class="mytd"><s:property value="sku"/></td>
                            <td class="mytd"><s:property value="skuName"/></td>
                            <td class="mytd"><s:property value="quantity"/></td>
                            <td class="mytd" style="width:25%"><s:property value="backReason"/></td>
                            <td class="mytd"><s:property value="remark"/></td>
                            <td class="mytd"><s:property value="invoice"/></td>
                            <td class="mytd"><s:property value="shippingName"/></td>
                            <td class="mytd"><s:property value="backCode"/></td>
                            <td class="mytd"><s:property value="IMEI"/></td>
                            <td class="mytd"><s:property value="handledBy"/></td>
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