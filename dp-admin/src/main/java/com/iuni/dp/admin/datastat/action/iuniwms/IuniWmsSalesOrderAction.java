package com.iuni.dp.admin.datastat.action.iuniwms;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.persist.datastat.common.model.IuniWmsWarehouse;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.common.IuniWarehouseService;
import com.iuni.dp.service.datastat.service.wms.IuniWmsSalesOrderService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 销售明细，销售出库，未开票销售明细
 *
 * @author Kenneth.Cai@iuni.com
 * @version dp-admin-1.1.5
 */
@Controller("iuniWmsSalesOrderAction")
@Scope("prototype")
public class IuniWmsSalesOrderAction extends BaseAction {

    private static final long serialVersionUID = -6537949881774757610L;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static Calendar calendar = Calendar.getInstance();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IuniWmsSalesOrderService iuniWmsSalesOrderServie;

    private Map<String, String> statParams = new HashMap<String, String>();

    private InputStream excelStream;

    private String fileName;

    private String flag;

    // 查询条件
    private String orderSource;
    private String orderCode;
    private String materialCode;
    private String parentOrderId;

    @Autowired
    private IuniWarehouseService warehouseService;
    /**
     * 仓库代码
     */
    private static String warehouseCode;
    /**
     * 仓库列表
     */
    private List<IuniWmsWarehouse> warehouseList;

    /**
     * 初始化仓库列表
     */
    private void initWarehouse() {
        warehouseList = warehouseService.queryAllWarehouse();
    }

    /**
     * IUNI WMS销售明细按条件统计
     *
     * @return String
     */
    public String iuniWmsSalesOrderStatView() {
        List<Map<String, Object>> iuniWmsSalesOrderStatList;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap("iuniWmsTransferStat");

            Integer totalRecord = iuniWmsSalesOrderServie.queryIuniWmsSalesOrderStatCount(params);

            //根据当前页、总记录数、页大小获得Page
            //page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
            page = Page.genPage(page.getCurrentPage(), totalRecord, 50);

            //新增Page至参数Map
            setPageInfo2Map(page, params);

            iuniWmsSalesOrderStatList = iuniWmsSalesOrderServie.queryIuniWmsSalesOrderStatByPage(params);

            request.setAttribute("iuniWmsSalesOrderStatList", iuniWmsSalesOrderStatList);

        } catch (DBException dbex) {
            logger.error("IuniWmsSalesOrderAction.iuniWmsSalesOrderStatView found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsSalesOrderAction.iuniWmsSalesOrderStatView found Exception", ex);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * IUNI WMS销售明细按条件统计列表导出至Excel
     *
     * @return String
     */
    public String iuniWmsSalesOrderStat2Excel() {
        List<Map<String, Object>> iuniWmsSalesOrderStatList = null;
        List<String> columnNames = null;
        List<String> columns = null;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap("iuniWmsTransferStat");
            iuniWmsSalesOrderStatList = iuniWmsSalesOrderServie.queryIuniWmsSalesOrderStatByExample(params);
            // 生成导出数据列名列表
            columnNames = genIuniWmsSalesOrderStatColNames();
            // 生成导出数据列名变量列表
            columns = genIuniWmsSalesOrderStatCols();

            if (CollectionUtils.isNotEmpty(iuniWmsSalesOrderStatList) && CollectionUtils.isNotEmpty(columnNames)
                    && CollectionUtils.isNotEmpty(columns)) {
                // 按Sheet数据列表
                Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();

                String sheetName = "IUNI WMS销售单日明细表";
                String exportDate = dateFormat.format(new Date());
                fileName = sheetName + "_" + exportDate;
                fileName = new String(fileName.getBytes(), "ISO8859-1");

                sheetDataList.put(sheetName, iuniWmsSalesOrderStatList);

                XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);

                ByteArrayOutputStream baos = null;
                try {
                    baos = new ByteArrayOutputStream();
                    workbook.write(baos);
                    byte[] ba = baos.toByteArray();
                    excelStream = new ByteArrayInputStream(ba);

                } catch (IOException e) {
                    logger.error("IuniWmsSalesOrderAction.iuniWmsSalesOrderStat2Excel found IOException.", e);
                } finally {
                    if (null != baos) {
                        try {
                            baos.close();
                        } catch (IOException e) {
                            logger.error("IuniWmsSalesOrderAction.iuniWmsSalesOrderStat2Excel found IOException.", e);
                        }
                    }
                }
            } else {
                return ERROR;
            }

        } catch (DBException dbex) {
            logger.error("IuniWmsSalesOrderAction.iuniWmsSalesOrderStat2Excel found DBException", dbex);
            return ERROR;

        } catch (Exception ex) {
            logger.error("IuniWmsSalesOrderAction.iuniWmsSalesOrderStat2Excel found Exception", ex);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * IUNI WMS销售出库明细按条件统计
     *
     * @return String
     */
    public String iuniStockMovDetailsView() {
        initWarehouse();

        List<Map<String, Object>> iuniStockMovDetailsList = null;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap("iuniStockMovDetails");

            Integer totalRecord = iuniWmsSalesOrderServie.queryIuniStockMovDetailsCount(params);

            //根据当前页、总记录数、页大小获得Page
            //page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
            page = Page.genPage(page.getCurrentPage(), totalRecord, 50);

            //新增Page至参数Map
            setPageInfo2Map(page, params);

            iuniStockMovDetailsList = iuniWmsSalesOrderServie.queryIuniStockMovDetailsByPage(params);

            request.setAttribute("iuniStockMovDetailsList", iuniStockMovDetailsList);

        } catch (DBException dbex) {
            logger.error("IuniWmsSalesOrderAction.iuniStockMovDetailsView found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsSalesOrderAction.iuniStockMovDetailsView found Exception", ex);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * IUNI WMS销售出库明细按条件统计列表导出至Excel
     *
     * @return String
     */
    public String iuniStockMovDetails2Excel() {
        List<Map<String, Object>> iuniStockMovDetailsList = null;
        List<String> columnNames = null;
        List<String> columns = null;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap("iuniStockMovDetails");
            iuniStockMovDetailsList = iuniWmsSalesOrderServie.queryIuniStockMovDetailsByExample(params);
            // 生成导出数据列名列表
            columnNames = genIuniStockMovDetailsColNames();
            // 生成导出数据列名变量列表
            columns = genIuniStockMovDetailsCols();

            if (CollectionUtils.isNotEmpty(iuniStockMovDetailsList) && CollectionUtils.isNotEmpty(columnNames)
                    && CollectionUtils.isNotEmpty(columns)) {
                // 按Sheet数据列表
                Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();

                String sheetName = "IUNI WMS销售出库明细表";
                // String exportDate = dateFormat.format(new Date());
                String exportDate = params.get("beginDate") + "~" + params.get("endDate");
                fileName = sheetName + "（" + exportDate + "）";
                fileName = new String(fileName.getBytes(), "ISO8859-1");

                sheetDataList.put(sheetName, iuniStockMovDetailsList);

                XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);

                ByteArrayOutputStream baos = null;
                try {
                    baos = new ByteArrayOutputStream();
                    workbook.write(baos);
                    byte[] ba = baos.toByteArray();
                    excelStream = new ByteArrayInputStream(ba);

                } catch (IOException e) {
                    logger.error("IuniWmsSalesOrderAction.iuniStockMovDetails2Excel found IOException.", e);
                } finally {
                    if (null != baos) {
                        try {
                            baos.close();
                        } catch (IOException e) {
                            logger.error("IuniWmsSalesOrderAction.iuniStockMovDetails2Excel found IOException.", e);
                        }
                    }
                }
            } else {
                return ERROR;
            }

        } catch (DBException dbex) {
            logger.error("IuniWmsSalesOrderAction.iuniStockMovDetails2Excel found DBException", dbex);
            return ERROR;

        } catch (Exception ex) {
            logger.error("IuniWmsSalesOrderAction.iuniStockMovDetails2Excel found Exception", ex);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * IUNI WMS未开票销售明细按条件统计
     *
     * @return String
     */
    public String iuniNoInvoiceSalesDetailsView() {
        List<Map<String, Object>> iuniNoInvoiceSalesDetailsList = null;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap("iuniNoInvoiceSalesDetails");

            Integer totalRecord = iuniWmsSalesOrderServie.queryIuniNoInvoiceSalesDetailsCount(params);

            //根据当前页、总记录数、页大小获得Page
            //page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
            page = Page.genPage(page.getCurrentPage(), totalRecord, 50);

            //新增Page至参数Map
            setPageInfo2Map(page, params);

            iuniNoInvoiceSalesDetailsList = iuniWmsSalesOrderServie.queryIuniNoInvoiceSalesDetailsByPage(params);

            request.setAttribute("iuniNoInvoiceSalesDetailsList", iuniNoInvoiceSalesDetailsList);

        } catch (DBException dbex) {
            logger.error("IuniWmsSalesOrderAction.iuniNoInvoiceSalesDetailsView found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsSalesOrderAction.iuniNoInvoiceSalesDetailsView found Exception", ex);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * IUNI WMS未开票销售明细按条件统计列表导出至Excel
     *
     * @return String
     */
    public String iuniNoInvoiceSalesDetails2Excel() {
        List<Map<String, Object>> iuniNoInvoiceSalesDetailsList = null;
        List<String> columnNames = null;
        List<String> columns = null;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap("iuniNoInvoiceSalesDetails");
            iuniNoInvoiceSalesDetailsList = iuniWmsSalesOrderServie.queryIuniNoInvoiceSalesDetailsByExample(params);
            // 生成导出数据列名列表
            columnNames = genIuniNoInvoiceSalesDetailsColNames();
            // 生成导出数据列名变量列表
            columns = genIuniNoInvoiceSalesDetailsCols();

            if (CollectionUtils.isNotEmpty(iuniNoInvoiceSalesDetailsList) && CollectionUtils.isNotEmpty(columnNames)
                    && CollectionUtils.isNotEmpty(columns)) {
                // 按Sheet数据列表
                Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();

                String sheetName = "IUNI WMS未开票销售明细表";
                String exportDate = dateFormat.format(new Date());
                fileName = sheetName + "_" + exportDate;
                fileName = new String(fileName.getBytes(), "ISO8859-1");

                sheetDataList.put(sheetName, iuniNoInvoiceSalesDetailsList);

                XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);

                ByteArrayOutputStream baos = null;
                try {
                    baos = new ByteArrayOutputStream();
                    workbook.write(baos);
                    byte[] ba = baos.toByteArray();
                    excelStream = new ByteArrayInputStream(ba);

                } catch (IOException e) {
                    logger.error("IuniWmsSalesOrderAction.iuniNoInvoiceSalesDetails2Excel found IOException.", e);
                } finally {
                    if (null != baos) {
                        try {
                            baos.close();
                        } catch (IOException e) {
                            logger.error("IuniWmsSalesOrderAction.iuniNoInvoiceSalesDetails2Excel found IOException.", e);
                        }
                    }
                }
            } else {
                return ERROR;
            }
        } catch (DBException dbex) {
            logger.error("IuniWmsSalesOrderAction.iuniNoInvoiceSalesDetails2Excel found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsSalesOrderAction.iuniNoInvoiceSalesDetails2Excel found Exception", ex);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * IUNI WMS收款发货发票金额核对明细按条件统计
     *
     * @return String
     */
    public String iuniWmsPayAmountCheckView() {
        List<Map<String, Object>> iuniWmsPayAmountCheckList = null;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap("iuniWmsPayAmountCheck");

            Integer totalRecord = iuniWmsSalesOrderServie.queryIuniWmsPayAmountCheckCount(params);

            //根据当前页、总记录数、页大小获得Page
            //page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
            page = Page.genPage(page.getCurrentPage(), totalRecord, 50);

            //新增Page至参数Map
            setPageInfo2Map(page, params);

            iuniWmsPayAmountCheckList = iuniWmsSalesOrderServie.queryIuniWmsPayAmountCheckByPage(params);

            request.setAttribute("iuniWmsPayAmountCheckList", iuniWmsPayAmountCheckList);

        } catch (DBException dbex) {
            logger.error("IuniWmsSalesOrderAction.iuniWmsPayAmountCheckView found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsSalesOrderAction.iuniWmsPayAmountCheckView found Exception", ex);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * IUNI WMS收款发货发票金额核对明细按条件统计列表导出至Excel
     *
     * @return String
     */
    public String iuniWmsPayAmountCheck2Excel() {
        List<Map<String, Object>> iuniWmsPayAmountCheckList = null;
        List<String> columnNames = null;
        List<String> columns = null;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap("iuniWmsPayAmountCheck");
            iuniWmsPayAmountCheckList = iuniWmsSalesOrderServie.queryIuniWmsPayAmountCheckByExample(params);
            // 生成导出数据列名列表
            columnNames = genIuniWmsPayAmountCheckColNames();
            // 生成导出数据列名变量列表
            columns = genIuniWmsPayAmountCheckCols();

            if (CollectionUtils.isNotEmpty(iuniWmsPayAmountCheckList) && CollectionUtils.isNotEmpty(columnNames)
                    && CollectionUtils.isNotEmpty(columns)) {
                // 按Sheet数据列表
                Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();

                String sheetName = "IUNI WMS收款发货发票金额核对明细表";
                String exportDate = dateFormat.format(new Date());
                fileName = sheetName + "_" + exportDate;
                fileName = new String(fileName.getBytes(), "ISO8859-1");

                sheetDataList.put(sheetName, iuniWmsPayAmountCheckList);

                XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);

                ByteArrayOutputStream baos = null;
                try {
                    baos = new ByteArrayOutputStream();
                    workbook.write(baos);
                    byte[] ba = baos.toByteArray();
                    excelStream = new ByteArrayInputStream(ba);

                } catch (IOException e) {
                    logger.error("IuniWmsSalesOrderAction.iuniWmsPayAmountCheck2Excel found IOException.", e);
                } finally {
                    if (null != baos) {
                        try {
                            baos.close();
                        } catch (IOException e) {
                            logger.error("IuniWmsSalesOrderAction.iuniWmsPayAmountCheck2Excel found IOException.", e);
                        }
                    }
                }
            } else {
                return ERROR;
            }

        } catch (DBException dbex) {
            logger.error("IuniWmsSalesOrderAction.iuniWmsPayAmountCheck2Excel found DBException", dbex);
            return ERROR;

        } catch (Exception ex) {
            logger.error("IuniWmsSalesOrderAction.iuniWmsPayAmountCheck2Excel found Exception", ex);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * 生成导出数据列名变量列表
     *
     * @return List<String>
     */
    private List<String> genIuniWmsSalesOrderStatCols() {
        List<String> cols = new ArrayList<String>();
        cols.add("rowNum");
        cols.add("shippingTime");
        cols.add("orderSource");
        cols.add("batchCode");
        cols.add("orderCode");
        cols.add("outerOrderCode");
        cols.add("consignee");
        cols.add("shippingAddr");
        cols.add("mobile");
        cols.add("payType");
        cols.add("shippingName");
        cols.add("shippingNo");
        cols.add("orderTime");
        cols.add("payNo");
        cols.add("orderAmount");
        cols.add("payAmount");
        cols.add("paidAmount");
        cols.add("invoiceEnabled");
        cols.add("invoiceTitle");
        cols.add("invoiceAmount");
        cols.add("orderStatus");
        cols.add("lastPushTime");
        cols.add("skuName");
        cols.add("quantity");
        cols.add("unitPrice");
        cols.add("goodsAmount");
        cols.add("weight");

        return cols;
    }

    /**
     * 生成导出数据列名列表
     *
     * @return List<String>
     */
    private List<String> genIuniWmsSalesOrderStatColNames() {
        List<String> colNames = new ArrayList<String>();
        colNames.add("序号");
        colNames.add("发货时间");
        colNames.add("订单来源");
        colNames.add("拣货批次");
        colNames.add("订单号");
        colNames.add("外部订单号");
        colNames.add("收货人");
        colNames.add("收货地址");
        colNames.add("联系电话");
        colNames.add("付款方式");
        colNames.add("快递类型");
        colNames.add("运单号");
        colNames.add("下单时间");
        colNames.add("交易号");
        colNames.add("订单金额");
        colNames.add("应付金额");
        colNames.add("已支付金额");
        colNames.add("是否需要发票");
        colNames.add("发票抬头");
        colNames.add("发票价格");
        colNames.add("订单状态");
        colNames.add("签收日期");
        colNames.add("SKU名称");
        colNames.add("数量");
        colNames.add("商品单价");
        colNames.add("商品总价");
        colNames.add("重量");

        return colNames;
    }

    /**
     * 生成导出数据列名变量列表
     *
     * @return List<String>
     */
    private List<String> genIuniStockMovDetailsCols() {
        List<String> cols = new ArrayList<String>();
        cols.add("rowNum");
        cols.add("orderSource");
        cols.add("payName");
        cols.add("warehouseName");
        cols.add("stockChangeTime");
        cols.add("orderCode");
        cols.add("outerOrderCode");
//        cols.add("deliveryCode");
        cols.add("skuCode");
        cols.add("materialCode");
//        cols.add("goodsName");
        cols.add("skuName");
        cols.add("quantity");
        cols.add("invoiceTcode");
        cols.add("invoiceCode");
        cols.add("invoiceAmount");
        cols.add("logisticsCost");
        cols.add("isScalper");
        return cols;
    }

    /**
     * 生成导出数据列名列表
     *
     * @return List<String>
     */
    private List<String> genIuniStockMovDetailsColNames() {
        List<String> colNames = new ArrayList<String>();
        colNames.add("序号");
        colNames.add("销售渠道/类型");
        colNames.add("收款类型");
        colNames.add("仓库");
        colNames.add("日期");
        colNames.add("订单号");
        colNames.add("外部订单号");
//        colNames.add("出库单号");
        colNames.add("SKU");
        colNames.add("物料编码");
//        colNames.add("商品名称");
        colNames.add("规格型号");
        colNames.add("数量");
        colNames.add("发票代码");
        colNames.add("发票号码");
        colNames.add("发票金额");
        colNames.add("价外费");
        colNames.add("是否刷单");
        return colNames;
    }

    /**
     * 生成导出数据列名变量列表
     *
     * @return List<String>
     */
    private List<String> genIuniNoInvoiceSalesDetailsCols() {
        List<String> cols = new ArrayList<String>();
        cols.add("rowNum");
        cols.add("orderSource");
        cols.add("stockChangeTime");
        cols.add("orderCode");
        cols.add("outerOrderCode");
//        cols.add("deliveryCode");
        cols.add("skuCode");
        cols.add("materialCode");
//        cols.add("goodsName");
        cols.add("skuName");
        cols.add("quantity");
        cols.add("invoiceAmount");

        return cols;
    }

    /**
     * 生成导出数据列名列表
     *
     * @return List<String>
     */
    private List<String> genIuniNoInvoiceSalesDetailsColNames() {
        List<String> colNames = new ArrayList<String>();
        colNames.add("序号");
        colNames.add("销售渠道/类型");
        colNames.add("日期");
        colNames.add("订单号");
        colNames.add("外部订单号");
//        colNames.add("出库单号");
        colNames.add("SKU");
        colNames.add("物料编码");
//        colNames.add("商品名称");
        colNames.add("规格型号");
        colNames.add("数量");
        colNames.add("发票金额");

        return colNames;
    }

    /**
     * 生成导出数据列名变量列表
     *
     * @return List<String>
     */
    private List<String> genIuniWmsPayAmountCheckCols() {
        List<String> cols = new ArrayList<String>();
        cols.add("rowNum");
        cols.add("stockTime");
        cols.add("orderCode");
        cols.add("parentOrderId");
        cols.add("outerOrderCode");
        cols.add("orderSource");
        cols.add("goodsAmount");
        cols.add("bonus");
        cols.add("shippingFee");
        cols.add("orderAmount");
        cols.add("paySerialNo");
        cols.add("paiedAmount");
        cols.add("payName");
        cols.add("invoiceAmount");
        cols.add("deductAmount");
        cols.add("deductReason");

        return cols;
    }

    /**
     * 生成导出数据列名列表
     *
     * @return List<String>
     */
    private List<String> genIuniWmsPayAmountCheckColNames() {
        List<String> colNames = new ArrayList<String>();
        colNames.add("序号");
        colNames.add("出入库日期");
        colNames.add("子订单号");
        colNames.add("主订单号");
        colNames.add("外部订单号");
        colNames.add("销售渠道/类型");
        colNames.add("商品金额");
        colNames.add("优惠金额");
        colNames.add("代收快递费");
        colNames.add("订单金额");
        colNames.add("支付流水号");
        colNames.add("收款金额");
        colNames.add("收款方式");
        colNames.add("发票金额");
        colNames.add("退货扣款金额");
        colNames.add("退货扣款原因");

        return colNames;
    }

    /**
     * 生成查询参数Map
     *
     * @return Map<String, Object>
     * @throws ParseException
     */
    private Map<String, Object> genParamMap(String condition) throws ParseException {
        Map<String, Object> params = new HashMap<String, Object>();

        if ("default".equals(flag)) {
            calendar.setTime(new Date());
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
            String eDate = dateFormat.format(calendar.getTime());
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 6);
            String sDate = dateFormat.format(calendar.getTime());
            params.put("beginDate", sDate);
            params.put("endDate", eDate);

            // 设置日期过滤框默认值
            statParams.put("beginDate", sDate);
            statParams.put("endDate", eDate);
        }

        if (StringUtils.isNotBlank(statParams.get("beginDate"))) {
            params.put("beginDate", statParams.get("beginDate"));
        }

        if (StringUtils.isNotBlank(statParams.get("endDate"))) {
            params.put("endDate", statParams.get("endDate"));
        }

        // 来源
        if (StringUtils.isNotBlank(orderSource))
            params.put("orderSource", orderSource.trim());
        // 订单号
        if (StringUtils.isNotBlank(orderCode))
            params.put("orderCode", orderCode.trim());
        // 物料编码
        if (StringUtils.isNotBlank(materialCode))
            params.put("materialCode", materialCode.trim());
        // 主订单号（收款发货发票金额核对明细报表 的条件）
        if (StringUtils.isNotBlank(parentOrderId))
            params.put("parentOrderId", parentOrderId.trim());
        // 仓库
        if (StringUtils.isNotBlank(warehouseCode))
            params.put("warehouseCode", warehouseCode.trim());

        return params;
    }

    /**
     * 新增Page至参数Map
     *
     * @param page
     * @param params
     */
    private void setPageInfo2Map(Page page, Map<String, Object> params) {
        params.put("startRec", page.getStartRec());
        params.put("endRec", page.getEndRec());
    }

    public Map<String, String> getStatParams() {
        return statParams;
    }

    public void setStatParams(Map<String, String> statParams) {
        this.statParams = statParams;
    }

    public InputStream getExcelStream() {
        return excelStream;
    }

    public void setExcelStream(InputStream excelStream) {
        this.excelStream = excelStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getParentOrderId() {
        return parentOrderId;
    }

    public void setParentOrderId(String parentOrderId) {
        this.parentOrderId = parentOrderId;
    }

    public static String getWarehouseCode() {
        return warehouseCode;
    }

    public static void setWarehouseCode(String warehouseCode) {
        IuniWmsSalesOrderAction.warehouseCode = warehouseCode;
    }

    public List<IuniWmsWarehouse> getWarehouseList() {
        return warehouseList;
    }

    public void setWarehouseList(List<IuniWmsWarehouse> warehouseList) {
        this.warehouseList = warehouseList;
    }
}
