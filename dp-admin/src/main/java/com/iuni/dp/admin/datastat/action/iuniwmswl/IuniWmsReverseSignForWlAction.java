package com.iuni.dp.admin.datastat.action.iuniwmswl;

import com.iuni.dp.admin.common.Status;
import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.persist.datastat.common.model.OrderSource;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.common.OrderSourceService;
import com.iuni.dp.service.datastat.service.wmswl.IuniWmsReverseSignForWlService;
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
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
@Controller("iuniWmsReverseSignForWlAction")
@Scope("prototype")
public class IuniWmsReverseSignForWlAction extends BaseAction {

    private final static Logger logger = LoggerFactory.getLogger(IuniWmsReverseSignForWlAction.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static Calendar calendar = Calendar.getInstance();

    private Map<String, String> statParams = new HashMap<String, String>();

    private InputStream excelStream;

    private String fileName;

    private String flag;

    private String orderSource;
    private String status;

    private List<Status.BackStatus> backStatusList;
    private List<Status.ExchangeStatus> exchangeStatusList;
    private List<Status.RepairStatus> repairStatusList;

    @Autowired
    private OrderSourceService orderSourceService;
    private List<OrderSource> orderSourceList;

    @Autowired
    private IuniWmsReverseSignForWlService reverseSignForWlService;

    private final static String RETURN_RESULT_NAME = "result";

    private void init() {
        if (orderSourceList == null) {
            orderSourceList = orderSourceService.getAllOmOrderSource();
            orderSourceList.addAll(orderSourceService.getAllOmBigAccount());
        }
        if (backStatusList == null)
            backStatusList = Status.BackStatus.getAllStatus();
        if (exchangeStatusList == null)
            exchangeStatusList = Status.ExchangeStatus.getAllStatus();
        if (repairStatusList == null)
            repairStatusList = Status.RepairStatus.getAllStatus();
    }

    /**
     * 逆向签收表 - 退货
     *
     * @return
     */
    public String iuniWmsReverseSignOfBack() {
        init();

        // 结果集
        List<Map<String, Object>> result;
        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap();
            // 记录条数
            Integer totalRecord = reverseSignForWlService.queryWmsReverseSignOfBackForWlCount(params);
            //根据当前页、总记录数、页大小获得Page
            page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
            //新增Page至参数Map
            setPageInfo2Map(page, params);
            result = reverseSignForWlService.queryWmsReverseSignOfBackForWlByPage(params);
        } catch (DBException dbex) {
            logger.error("IuniWmsReverseSignForWlAction.iuniWmsReverseSignOfBack found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsReverseSignForWlAction.iuniWmsReverseSignOfBack found Exception", ex);
            ex.printStackTrace();
            return ERROR;
        }
        if (result == null)
            result = new ArrayList<Map<String, Object>>();

        request.setAttribute(RETURN_RESULT_NAME, result);

        return SUCCESS;
    }

    /**
     * 逆向签收表 - 退货 导出EXCEL
     *
     * @return
     */
    public String iuniWmsReverseSignOfBack2Excel() {
        List<Map<String, Object>> result;
        List<String> columnNames;
        List<String> columns;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap();
            result = reverseSignForWlService.queryWmsReverseSignOfBackForWlByPage(params);
            // 生成导出数据列名列表
            columnNames = genBackColNames();
            // 生成导出数据列名变量列表
            columns = genBackColVars();

            if (CollectionUtils.isNotEmpty(result) && CollectionUtils.isNotEmpty(columnNames)
                    && CollectionUtils.isNotEmpty(columns)) {
                // 按Sheet数据列表
                Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();

                String sheetName = "IUNI WMS逆向签收表 - 退货（物流部）";
                fileName = sheetName + "_" + params.get("beginDate") + "-" + params.get("endDate");
                fileName = new String(fileName.getBytes(), "ISO8859-1");

                sheetDataList.put(sheetName, result);

                XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);

                ByteArrayOutputStream baos = null;
                try {
                    baos = new ByteArrayOutputStream();
                    workbook.write(baos);
                    byte[] ba = baos.toByteArray();
                    excelStream = new ByteArrayInputStream(ba);
                } catch (IOException e) {
                    logger.error("IuniWmsReverseSignForWlAction.iuniWmsReverseSignOfBack2Excel found IOException.", e);
                } finally {
                    if (null != baos) {
                        try {
                            baos.close();
                        } catch (IOException e) {
                            logger.error("IuniWmsReverseSignForWlAction.iuniWmsReverseSignOfBack2Excel found IOException.", e);
                        }
                    }
                }
            } else {
                return ERROR;
            }
        } catch (DBException dbex) {
            logger.error("IuniWmsReverseSignForWlAction.iuniWmsReverseSignOfBack2Excel found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsReverseSignForWlAction.iuniWmsReverseSignOfBack2Excel found Exception", ex);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * 逆向签收表 - 换货
     *
     * @return
     */
    public String iuniWmsReverseSignOfExchange() {
        init();

        // 结果集
        List<Map<String, Object>> result;
        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap();
            // 记录条数
            Integer totalRecord = reverseSignForWlService.queryWmsReverseSignOfExchangeForWlCount(params);
            //根据当前页、总记录数、页大小获得Page
            page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
            //新增Page至参数Map
            setPageInfo2Map(page, params);
            result = reverseSignForWlService.queryWmsReverseSignOfExchangeForWlByPage(params);
        } catch (DBException dbex) {
            logger.error("IuniWmsReverseSignForWlAction.iuniWmsReverseSignOfExchange found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsReverseSignForWlAction.iuniWmsReverseSignOfExchange found Exception", ex);
            ex.printStackTrace();
            return ERROR;
        }
        if (result == null)
            result = new ArrayList<Map<String, Object>>();

        request.setAttribute(RETURN_RESULT_NAME, result);

        return SUCCESS;
    }

    /**
     * 逆向签收表 - 换货 导出EXCEL
     *
     * @return
     */
    public String iuniWmsReverseSignOfExchange2Excel() {
        List<Map<String, Object>> result;
        List<String> columnNames;
        List<String> columns;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap();
            result = reverseSignForWlService.queryWmsReverseSignOfExchangeForWlByPage(params);
            // 生成导出数据列名列表
            columnNames = genExchangeColNames();
            // 生成导出数据列名变量列表
            columns = genExchangeColVars();

            if (CollectionUtils.isNotEmpty(result) && CollectionUtils.isNotEmpty(columnNames)
                    && CollectionUtils.isNotEmpty(columns)) {
                // 按Sheet数据列表
                Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();

                String sheetName = "IUNI WMS逆向签收表 - 换货（物流部）";
                fileName = sheetName + "_" + params.get("beginDate") + "-" + params.get("endDate");
                fileName = new String(fileName.getBytes(), "ISO8859-1");

                sheetDataList.put(sheetName, result);

                XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);

                ByteArrayOutputStream baos = null;
                try {
                    baos = new ByteArrayOutputStream();
                    workbook.write(baos);
                    byte[] ba = baos.toByteArray();
                    excelStream = new ByteArrayInputStream(ba);
                } catch (IOException e) {
                    logger.error("IuniWmsReverseSignForWlAction.iuniWmsReverseSignOfExchange2Excel found IOException.", e);
                } finally {
                    if (null != baos) {
                        try {
                            baos.close();
                        } catch (IOException e) {
                            logger.error("IuniWmsReverseSignForWlAction.iuniWmsReverseSignOfExchange2Excel found IOException.", e);
                        }
                    }
                }
            } else {
                return ERROR;
            }
        } catch (DBException dbex) {
            logger.error("IuniWmsReverseSignForWlAction.iuniWmsReverseSignOfExchange2Excel found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsReverseSignForWlAction.iuniWmsReverseSignOfExchange2Excel found Exception", ex);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * 逆向签收表 - 维修
     *
     * @return
     */
    public String iuniWmsReverseSignOfRepair() {
        init();

        // 结果集
        List<Map<String, Object>> result;
        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap();
            // 记录条数
            Integer totalRecord = reverseSignForWlService.queryWmsReverseSignOfRepairForWlCount(params);
            //根据当前页、总记录数、页大小获得Page
            page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
            //新增Page至参数Map
            setPageInfo2Map(page, params);
            result = reverseSignForWlService.queryWmsReverseSignOfRepairForWlByPage(params);
        } catch (DBException dbex) {
            logger.error("IuniWmsReverseSignForWlAction.iuniWmsReverseSignOfRepair found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsReverseSignForWlAction.iuniWmsReverseSignOfRepair found Exception", ex);
            ex.printStackTrace();
            return ERROR;
        }
        if (result == null)
            result = new ArrayList<Map<String, Object>>();

        request.setAttribute(RETURN_RESULT_NAME, result);

        return SUCCESS;
    }

    /**
     * 逆向签收表 - 维修 导出EXCEL
     *
     * @return
     */
    public String iuniWmsReverseSignOfRepair2Excel() {
        List<Map<String, Object>> result;
        List<String> columnNames;
        List<String> columns;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap();
            result = reverseSignForWlService.queryWmsReverseSignOfRepairForWlByPage(params);
            // 生成导出数据列名列表
            columnNames = genRepairColNames();
            // 生成导出数据列名变量列表
            columns = genRepairColVars();

            if (CollectionUtils.isNotEmpty(result) && CollectionUtils.isNotEmpty(columnNames)
                    && CollectionUtils.isNotEmpty(columns)) {
                // 按Sheet数据列表
                Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();

                String sheetName = "IUNI WMS逆向签收表 - 维修（物流部）";
                fileName = sheetName + "_" + params.get("beginDate") + "-" + params.get("endDate");
                fileName = new String(fileName.getBytes(), "ISO8859-1");

                sheetDataList.put(sheetName, result);

                XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);

                ByteArrayOutputStream baos = null;
                try {
                    baos = new ByteArrayOutputStream();
                    workbook.write(baos);
                    byte[] ba = baos.toByteArray();
                    excelStream = new ByteArrayInputStream(ba);
                } catch (IOException e) {
                    logger.error("IuniWmsReverseSignForWlAction.iuniWmsReverseSignOfRepair2Excel found IOException.", e);
                } finally {
                    if (null != baos) {
                        try {
                            baos.close();
                        } catch (IOException e) {
                            logger.error("IuniWmsReverseSignForWlAction.iuniWmsReverseSignOfRepair2Excel found IOException.", e);
                        }
                    }
                }
            } else {
                return ERROR;
            }
        } catch (DBException dbex) {
            logger.error("IuniWmsReverseSignForWlAction.iuniWmsReverseSignOfRepair2Excel found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsReverseSignForWlAction.iuniWmsReverseSignOfRepair2Excel found Exception", ex);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * 生成导出数据列名变量列表 - 退货
     *
     * @return List<String>
     */
    private List<String> genBackColVars() {
        List<String> cols = new ArrayList<String>();
        cols.add("rowNum");
        cols.add("orderSn");
        cols.add("referer");
        cols.add("deliverySn");
        cols.add("status");
        cols.add("isInvoice");
        cols.add("goodsName");
        cols.add("userName");
        cols.add("mobile");
        cols.add("address");
        cols.add("processTime");
        cols.add("checkTime");
        cols.add("auditTime");
        return cols;
    }

    /**
     * 生成导出数据列名列表 - 退货
     *
     * @return List<String>
     */
    private List<String> genBackColNames() {
        List<String> colNames = new ArrayList<String>();
        colNames.add("序号");
        colNames.add("订单号");
        colNames.add("订单来源");
        colNames.add("退货单号");
        colNames.add("退货状态");
        colNames.add("是否退回发票");
        colNames.add("退回实物");
        colNames.add("客户姓名");
        colNames.add("联系电话");
        colNames.add("联系地址");
        colNames.add("签收时间");
        colNames.add("售后检测时间");
        colNames.add("客服审核时间");
        return colNames;
    }

    /**
     * 生成导出数据列名变量列表 - 换货
     *
     * @return List<String>
     */
    private List<String> genExchangeColVars() {
        List<String> cols = new ArrayList<String>();
        cols.add("rowNum");
        cols.add("orderSn");
        cols.add("referer");
        cols.add("exchangeSn");
        cols.add("status");
        cols.add("goodsName");
        cols.add("userName");
        cols.add("mobile");
        cols.add("address");
        cols.add("receiveTime");
        cols.add("checkTime");
        cols.add("auditTime");
        cols.add("shippingTime");
        return cols;
    }

    /**
     * 生成导出数据列名列表 - 换货
     *
     * @return List<String>
     */
    private List<String> genExchangeColNames() {
        List<String> colNames = new ArrayList<String>();
        colNames.add("序号");
        colNames.add("订单号");
        colNames.add("订单来源");
        colNames.add("换货单号");
        colNames.add("换货状态");
        colNames.add("退回实物");
        colNames.add("客户姓名");
        colNames.add("联系电话");
        colNames.add("联系地址");
        colNames.add("签收时间");
        colNames.add("售后检测时间");
        colNames.add("客服审核时间");
        colNames.add("发货时间");
        return colNames;
    }

    /**
     * 生成导出数据列名变量列表 - 维修
     *
     * @return List<String>
     */
    private List<String> genRepairColVars() {
        List<String> cols = new ArrayList<String>();
        cols.add("rowNum");
        cols.add("orderSn");
        cols.add("referer");
        cols.add("repairSn");
        cols.add("status");
        cols.add("goodsName");
        cols.add("userName");
        cols.add("mobile");
        cols.add("address");
        cols.add("receiveTime");
        cols.add("checkTime");
        cols.add("auditTime");
        cols.add("shippingTime");
        return cols;
    }

    /**
     * 生成导出数据列名列表 - 维修
     *
     * @return List<String>
     */
    private List<String> genRepairColNames() {
        List<String> colNames = new ArrayList<String>();
        colNames.add("序号");
        colNames.add("订单号");
        colNames.add("订单来源");
        colNames.add("维修单号");
        colNames.add("维修状态");
        colNames.add("退回实物");
        colNames.add("客户姓名");
        colNames.add("联系电话");
        colNames.add("联系地址");
        colNames.add("签收时间");
        colNames.add("售后检测时间");
        colNames.add("客服审核时间");
        colNames.add("发货时间");
        return colNames;
    }

    /**
     * 生成查询参数Map
     *
     * @return Map<String, Object>
     * @throws java.text.ParseException
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

        if (StringUtils.isNotBlank(orderSource)) {
            params.put("orderSource", orderSource);
        }

        if (StringUtils.isNotBlank(status)) {
            params.put("status", status);
        }

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

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Status.BackStatus> getBackStatusList() {
        return backStatusList;
    }

    public void setBackStatusList(List<Status.BackStatus> backStatusList) {
        this.backStatusList = backStatusList;
    }

    public List<Status.ExchangeStatus> getExchangeStatusList() {
        return exchangeStatusList;
    }

    public void setExchangeStatusList(List<Status.ExchangeStatus> exchangeStatusList) {
        this.exchangeStatusList = exchangeStatusList;
    }

    public List<Status.RepairStatus> getRepairStatusList() {
        return repairStatusList;
    }

    public void setRepairStatusList(List<Status.RepairStatus> repairStatusList) {
        this.repairStatusList = repairStatusList;
    }

    public List<OrderSource> getOrderSourceList() {
        return orderSourceList;
    }

    public void setOrderSourceList(List<OrderSource> orderSourceList) {
        this.orderSourceList = orderSourceList;
    }

    public Map<String, String> getStatParams() {
        return statParams;
    }

    public void setStatParams(Map<String, String> statParams) {
        this.statParams = statParams;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getExcelStream() {
        return excelStream;
    }

    public void setExcelStream(InputStream excelStream) {
        this.excelStream = excelStream;
    }

}
