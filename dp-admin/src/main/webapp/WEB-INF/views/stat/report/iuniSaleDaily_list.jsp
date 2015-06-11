<%@page pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link href="css/alarm/main.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        td {
            height: 20px;
        }

        th {
            height: 40px;
            color: #0091D3;
        }
    </style>
    <script src="template/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/common/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="js/stat/iuniSaleDaily_list.js?v=130607"></script>
    <script type="text/javascript">
        var basePath = "<%=basePath%>";
    </script>
</head>
<body>
<table border="0" cellspacing="0" cellpadding="0" class="adminMain_top">
    <tbody>
    <tr>
        <td class="td01"></td>
        <td class="td02"><h3 class="topTitle fb f14">IUNI商城销售报表</h3></td>
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
                        <dd>查询功能</dd>
                        <dd>IUNI商城销售报表</dd>
                    </dl>
                </div>

                <div>
                    <input type="hidden" id="msg" value="<s:property value="msg"/>"/>
                </div>

                <div class="frame4" style="height:70px;">
                    <form action="" method="post" id="queryFrm">
                        <div style="display:none;">
                            <!--
				<input  type="hidden" id="bizDate" name="bizDate" value="${bizDate}"/>
			    <input  type="hidden" id="adName" name="adName" value="${adName}"/>
			    <input  type="hidden" id="channelName" name="channelName" value="${channelName}"/>
			     -->
                        </div>
                        <dl>
                            <dd class="ddl1">
                                <label for="beginDate">开始日期</label>
                            </dd>
                            <dd class="ddr1">
                                <input class="Wdate1" name="beginDate" id="beginDate" type="text" size="10"
                                       onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});"
                                       readonly="readonly" value="${beginDate}"/>
                            </dd>
                            <dd class="ddl1">
                                <label for="endDate">结束日期</label>
                            </dd>
                            <dd class="ddr1">
                                <input class="Wdate1" name="endDate" id="endDate" type="text" size="10"
                                       onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});"
                                       readonly="readonly" value="${endDate}"/>
                            </dd>
                            <dd class="ddl1">
                                <label for="sourceCode">订单来源</label>
                            </dd>
                            <dd class="ddr1">
                                <s:select id="sourceCode" list="orderSourceList" name="sourceCode" listKey="sourceCode"
                                          listValue="sourceName"/>
                            </dd>
                            <dd class="ddl1">
                                <input class="btn4" type="button" id="btn_query" value="查询"/>
                            </dd>
                            <dd class="ddl3">
                                <input class="btn6" type="button" id="btn_exportExcel" value="导出EXCEL"/>
                            </dd>
                        </dl>
                    </form>
                </div>

                <table class="listtable" style="width: 100%">
                    <thead>
                    <tr>
                        <th>统计日期</th>
                        <th>下单总数<br/>(含无效订单)</th>
                        <th>下单总金额</th>
                        <th>下单商品总件数</th>
                        <th>在线支付下单数</th>
                        <th>货到付款下单数</th>
                        <th>退货订单数</th>
                        <th>拒收订单数</th>
                        <th>有效订单数<br/>(已在线支付+已审核货到付款)</th>
                        <th>有效订单金额</th>
                        <th>有效订单商品总件数</th>
                        <th>有效订单比率</th>
                        <th>客单价</th>
                        <th>客件数</th>
                        <th>已支付订单数<br/>(已在线支付)</th>
                        <th>货到付款有效订单数<br/>(已审核)</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="iuniSaleDailyStatList" status="s" id="iuniSaleDailyStat">
                        <tr>
                            <td class="mytd">${bizDate}</td>
                            <td class="mytd">${totalOrderNum}</td>
                            <td class="mytd">${totalOrderAmount}</td>
                            <td class="mytd">${totalGoodsNum}</td>
                            <td class="mytd">${onlinePayOrderNum}</td>
                            <td class="mytd">${offlinePayOrderNum}</td>
                            <td class="mytd">${returnedOrderNum}</td>
                            <td class="mytd">${refusedOrderNum}</td>
                            <td class="mytd">${validOrderNum}</td>
                            <td class="mytd">${validOrderAmount}</td>
                            <td class="mytd">${validGoodsNum}</td>
                            <td class="mytd">${validOrderRate}</td>
                            <td class="mytd">${amountPerOrder}</td>
                            <td class="mytd">${goodsNumPerOrder}</td>
                            <td class="mytd">${payedOrderNum}</td>
                            <td class="mytd">${validOrderNum-payedOrderNum}</td>
                        </tr>
                    </s:iterator>
                    <tr>
                        <td class="mytd">总计</td>
                        <td class="mytd">${iuniSaleSum.totalOrderNum}</td>
                        <td class="mytd">${iuniSaleSum.totalOrderAmount}</td>
                        <td class="mytd">${iuniSaleSum.totalGoodsNum}</td>
                        <td class="mytd">${iuniSaleSum.onlinePayOrderNum}</td>
                        <td class="mytd">${iuniSaleSum.offlinePayOrderNum}</td>
                        <td class="mytd">${iuniSaleSum.returnedOrderNum}</td>
                        <td class="mytd">${iuniSaleSum.refusedOrderNum}</td>
                        <td class="mytd">${iuniSaleSum.validOrderNum}</td>
                        <td class="mytd">${iuniSaleSum.validOrderAmount}</td>
                        <td class="mytd">${iuniSaleSum.validGoodsNum}</td>
                        <td class="mytd">${iuniSaleSum.validOrderRate}</td>
                        <td class="mytd">${iuniSaleSum.amountPerOrder}</td>
                        <td class="mytd">${iuniSaleSum.goodsNumPerOrder}</td>
                        <td class="mytd">${iuniSaleSum.payedOrderNum}</td>
                        <td class="mytd">${iuniSaleSum.validOrderNum-iuniSaleSum.payedOrderNum}</td>
                    </tr>
                    </tbody>
                </table>
                <c:import url="/WEB-INF/views/common/pagelink.jsp"></c:import>
                说明：<br/>下单：所有订单系统中的单,含未支付成功及取消的订单<br/>订单：在线支付及经审核后的货到付款单<br/>订单数：所有订单汇总数<br/>订单总金额：所有订单金额汇总<br/>订单商品总件数：所有订单所含商品件数<br/>在线支付订单：在线支付订单数量合计<br/>货到付款订单：货到付款订单数量合计
                <br/>有效订单比率：有效订单数/订单总数 <br/> 客单价：有效订单总金额/有效订单数 <br/> 客件数：有效订单总件数/有效订单数<br/>有效订单数：订单状态-已支付订单（线上支付）、已审核订单（货到付款），待发货订单、发货中、已签收、已完成
                <br/>退货订单数：统计时间段内申请退货的订单，包括以下状态：退货中、退款中、已退款<br/>拒收订单数：统计时间段内订单状态为用户已拒收的订单<br/>订单时间：当天下的单。eg：如查看8月14日的报表，则指8月14日提交订单且处于以上状态的为有效订单
            </div>
        </td>
        <td class="td03"></td>
    </tr>
    </tbody>
</table>
</body>
</html> 