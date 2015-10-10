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
    <script type="text/javascript" src="js/stat/wms/iuniRebates.js?v=140703"></script>
    <script type="text/javascript">
        var basePath = "<%=basePath%>";
    </script>
</head>
<body>
<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top">
    <tbody>
    <tr>
        <td class="td01"></td>
        <td class="td02"><h3 class="topTitle fb f14">IUNI返利明细报表</h3></td>
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
                        <dd>IUNI返利明细报表</dd>
                        <dd>IUNI返利明细报表</dd>
                    </dl>
                </div>

                <div>
                    <input type="hidden" id="msg" value="<s:property value="msg"/>"/>
                </div>

                <div class="frame4" style="height:100px;">
                    <form action="" method="post" id="queryFrm">
                        <dl>
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
                                <label for="orderSource">销售渠道/类型</label>
                            </dd>
                            <dd class="ddr1">
                                <input id="orderSource" name="orderSource" value="${orderSource}"
                                       placeholder="输入销售渠道/类型"
                                       type="text"/>
                            </dd>
                            <dd class="ddl1">
                                <label for="orderCode">订单号</label>
                            </dd>
                            <dd class="ddr1">
                                <input id="orderCode" name="orderCode" value="${orderCode}" placeholder="输入订单号或外部订单号"
                                       type="text"/>
                            </dd>
                            <dd class="ddl1">
                                <label for="materialCode">物料编码</label>
                            </dd>
                            <dd class="ddr1">
                                <input id="materialCode" name="materialCode" value="${materialCode}"
                                       placeholder="输入物料编码"
                                       type="text"/>
                            </dd>

                            <dd class="ddl1">
                                <label for="rebateUserName">用户名称</label>
                            </dd>
                            <dd class="ddr1">
                                <input id="rebateUserName" name="rebateUserName" value="${rebateUserName}"
                                       placeholder="输入用户名称"
                                       type="text"/>
                            </dd>
                            <dd class="ddl1">
                                <label for="rebateStatus">返利状态</label>
                            </dd>
                            <dd class="ddr1">
                                <input id="rebateStatus" name="rebateStatus" value="${rebateStatus}"
                                       placeholder="输入返利状态"
                                       type="text"/>
                            </dd>
                            <dd class="ddl1">
                                <label for="rebateMail">邮箱</label>
                            </dd>
                            <dd class="ddr1">
                                <input id="rebateMail" name="rebateMail" value="${rebateMail}"
                                       placeholder="输入邮箱"
                                       type="text"/>
                            </dd>
                            <dd class="ddl1">
                                <label for="rebatePhone">电话</label>
                            </dd>
                            <dd class="ddr1">
                                <input id="rebatePhone" name="rebatePhone" value="${rebatePhone}"
                                       placeholder="输入电话"
                                       type="text"/>
                            </dd>

                            <dd class="ddl1"/>
                            <dd class="ddr1"/>
                            <dd class="ddl1"/>
                            <dd class="ddr1"/>

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
                        <th>销售渠道/类型</th>
                        <th>日期</th>
                        <th>订单号</th>
                        <th>出库单号</th>
                        <th>SKU</th>
                        <th>物料编码</th>
                        <th>商品名称</th>
                        <th>规格型号</th>
                        <th>数量</th>
                        <th>发票代码</th>
                        <th>发票号码</th>
                        <th>发票金额</th>
                        <th>价外费</th>
                        <th>返利比例</th>
                        <th>返利金额</th>
                        <th>返利状态</th>
                        <th>返利用户名</th>
                        <th>注册手机</th>
                        <th>注册邮箱</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="#request.resultList" status="s" id="result">
                        <tr>
                            <td class="mytd"><s:property value="rowNum"/></td>
                            <td class="mytd"><s:property value="orderSource"/></td>
                            <td class="mytd"><s:date name="addTime" format="yyyy-MM-dd"/></td>
                            <td class="mytd"><s:property value="orderCode"/></td>
                            <td class="mytd"><s:property value="deliveryCode"/></td>
                            <td class="mytd"><s:property value="skuCode"/></td>
                            <td class="mytd"><s:property value="materialCode"/></td>
                            <td class="mytd"><s:property value="goodsName"/></td>
                            <td class="mytd"><s:property value="skuName"/></td>
                            <td class="mytd"><s:property value="quantity"/></td>
                            <td class="mytd"><s:property value="invoiceTcode"/></td>
                            <td class="mytd"><s:property value="invoiceCode"/></td>
                            <td class="mytd"><s:property value="invoiceAmount"/></td>
                            <td class="mytd"><s:property value="logisticsCost"/></td>
                            <td class="mytd"><s:property value="rebateRatio"/></td>
                            <td class="mytd"><s:property value="rebateConfirmAmount"/></td>
                            <td class="mytd"><s:property value="rebateStatus"/></td>
                            <td class="mytd"><s:property value="rebateUserName"/></td>
                            <td class="mytd"><s:property value="rebateMail"/></td>
                            <td class="mytd"><s:property value="rebatePhone"/></td>
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