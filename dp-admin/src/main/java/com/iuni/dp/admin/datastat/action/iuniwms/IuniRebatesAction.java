package com.iuni.dp.admin.datastat.action.iuniwms;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
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
 * 返利
 *
 */
@Controller("iuniRebatesAction")
@Scope("prototype")
public class IuniRebatesAction extends BaseAction {

    private static final long serialVersionUID = -6537949881774757610L;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static Calendar calendar = Calendar.getInstance();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IuniWmsSalesOrderService iuniWmsSalesOrderService;

    private Map<String, String> statParams = new HashMap<String, String>();

    private InputStream excelStream;

    private String fileName;

    private String flag;

    // 查询条件
    private String orderSource;
    private String orderCode;
    private String materialCode;
    private String parentOrderId;

    private String rebateUserName;
    private String rebateStatus;
    private String rebateMail;
    private String rebatePhone;

    /**
     * IUNI WMS返利明细报表
     *
     * @return String
     */
    public String iuniRebates() {
        List<Map<String, Object>> resultList = null;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap();

            Integer totalRecord = iuniWmsSalesOrderService.queryIuniRebatesDetailCount(params);

            //根据当前页、总记录数、页大小获得Page
            //page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
            page = Page.genPage(page.getCurrentPage(), totalRecord, 50);

            //新增Page至参数Map
            setPageInfo2Map(page, params);

            resultList = iuniWmsSalesOrderService.queryIuniRebatesDetailByPage(params);

            request.setAttribute("resultList", resultList);

        } catch (DBException dbex) {
            logger.error("IuniRebatesAction.iuniRebates found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniRebatesAction.iuniRebates found Exception", ex);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * IUNI WMS返利明细报表导出至Excel
     *
     * @return String
     */
    public String iuniRebates2Excel() {
        List<Map<String, Object>> iuniStockMovDetailsList = null;
        List<String> columnNames = null;
        List<String> columns = null;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap();
            iuniStockMovDetailsList = iuniWmsSalesOrderService.queryIuniRebatesDetailByPage(params);
            // 生成导出数据列名列表
            columnNames = genColNames();
            // 生成导出数据列名变量列表
            columns = genCols();

            if (CollectionUtils.isNotEmpty(iuniStockMovDetailsList) && CollectionUtils.isNotEmpty(columnNames)
                    && CollectionUtils.isNotEmpty(columns)) {
                // 按Sheet数据列表
                Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();

                String sheetName = "IUNI WMS返利明细报表";
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
                    logger.error("IuniRebatesAction.iuniRebates2Excel found IOException.", e);
                } finally {
                    if (null != baos) {
                        try {
                            baos.close();
                        } catch (IOException e) {
                            logger.error("IuniRebatesAction.iuniRebates2Excel found IOException.", e);
                        }
                    }
                }
            } else {
                return ERROR;
            }

        } catch (DBException dbex) {
            logger.error("IuniRebatesAction.iuniRebates2Excel found DBException", dbex);
            return ERROR;

        } catch (Exception ex) {
            logger.error("IuniRebatesAction.iuniRebates2Excel found Exception", ex);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * 生成导出数据列名变量列表
     *
     * @return List<String>
     */
    private List<String> genCols() {
        List<String> cols = new ArrayList<String>();
        cols.add("rowNum");
        cols.add("orderSource");
        cols.add("stockChangeTime");
        cols.add("orderCode");
        cols.add("outerOrderCode");
        cols.add("deliveryCode");
        cols.add("skuCode");
        cols.add("materialCode");
        cols.add("goodsName");
        cols.add("skuName");
        cols.add("quantity");
        cols.add("invoiceTcode");
        cols.add("invoiceCode");
        cols.add("invoiceAmount");
        cols.add("logisticsCost");
        return cols;
    }

    /**
     * 生成导出数据列名列表
     *
     * @return List<String>
     */
    private List<String> genColNames() {
        List<String> colNames = new ArrayList<String>();
        colNames.add("序号");
        colNames.add("销售渠道/类型");
        colNames.add("日期");
        colNames.add("订单号");
        colNames.add("外部订单号");
        colNames.add("出库单号");
        colNames.add("SKU");
        colNames.add("物料编码");
        colNames.add("商品名称");
        colNames.add("规格型号");
        colNames.add("数量");
        colNames.add("发票代码");
        colNames.add("发票号码");
        colNames.add("发票金额");
        colNames.add("价外费");
        return colNames;
    }

    /**
     * 生成查询参数Map
     *
     * @return Map<String, Object>
     * @throws ParseException
     */
    private Map<String, Object> genParamMap() throws ParseException {
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

        // 用户名称
        if (StringUtils.isNotBlank(rebateUserName))
            params.put("rebateUserName", rebateUserName.trim());
        // 返利状态
        if (StringUtils.isNotBlank(rebateStatus))
            params.put("rebateStatus", rebateStatus.trim());
        // 返利邮箱
        if (StringUtils.isNotBlank(rebateMail))
            params.put("rebateMail", rebateMail.trim());
        // 电话
        if (StringUtils.isNotBlank(rebatePhone))
            params.put("rebatePhone", rebatePhone.trim());

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

    public String getRebateUserName() {
        return rebateUserName;
    }

    public void setRebateUserName(String rebateUserName) {
        this.rebateUserName = rebateUserName;
    }

    public String getRebateStatus() {
        return rebateStatus;
    }

    public void setRebateStatus(String rebateStatus) {
        this.rebateStatus = rebateStatus;
    }

    public String getRebateMail() {
        return rebateMail;
    }

    public void setRebateMail(String rebateMail) {
        this.rebateMail = rebateMail;
    }

    public String getRebatePhone() {
        return rebatePhone;
    }

    public void setRebatePhone(String rebatePhone) {
        this.rebatePhone = rebatePhone;
    }
}
