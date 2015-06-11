package com.iuni.dp.admin.datastat.action.iuniwmswl;

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

    private List<BackStatus> backStatusList;
    private List<ExchangeStatus> exchangeStatusList;
    private List<RepairStatus> repairStatusList;

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
            backStatusList = BackStatus.getAllStatus();
        if (exchangeStatusList == null)
            exchangeStatusList = ExchangeStatus.getAllStatus();
        if (repairStatusList == null)
            repairStatusList = RepairStatus.getAllStatus();
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

    public List<BackStatus> getBackStatusList() {
        return backStatusList;
    }

    public void setBackStatusList(List<BackStatus> backStatusList) {
        this.backStatusList = backStatusList;
    }

    public List<ExchangeStatus> getExchangeStatusList() {
        return exchangeStatusList;
    }

    public void setExchangeStatusList(List<ExchangeStatus> exchangeStatusList) {
        this.exchangeStatusList = exchangeStatusList;
    }

    public List<RepairStatus> getRepairStatusList() {
        return repairStatusList;
    }

    public void setRepairStatusList(List<RepairStatus> repairStatusList) {
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

    /**
     * 退货单状态
     */
    enum BackStatus {
        application("application", "申请中"),
        audit("audit", "已审核待确认退款"),
        cancelled("cancelled", "已取消"),
        unusual("unusual", "收包异常"),
        receivedPacket("receivedPacket", "已收包待审核"),
        completed("completed", "已退款"),
        auditNoPass("auditNoPass", "审核不通过"),;
        private String code;
        private String name;

        BackStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        static List<BackStatus> getAllStatus() {
            List<BackStatus> statusList = new ArrayList<BackStatus>();
            statusList.add(BackStatus.application);
            statusList.add(BackStatus.audit);
            statusList.add(BackStatus.cancelled);
            statusList.add(BackStatus.unusual);
            statusList.add(BackStatus.receivedPacket);
            statusList.add(BackStatus.completed);
            statusList.add(BackStatus.auditNoPass);
            return statusList;
        }
    }

    /**
     * 换货单状态
     */
    enum ExchangeStatus {
        EX_INFO_STATUS_QX("cancle", "已取消"),
        EX_INFO_STATUS_QXDFH("cancleWaitDelivery", "取消待发货"),
        EX_INFO_STATUS_DSB("waitReceivePackage", "待收包"),
        EX_INFO_STATUS_SBYC("receivePackageUnusual", "换货收包异常"),
        EX_INFO_STATUS_DCJ("waitInitialCheck", "待售后初检"),
        EX_INFO_STATUS_DSHSH("waitGassAudit", "待售后审核"),
        EX_INFO_STATUS_YFH("shipped", "已发货"),
        EX_INFO_STATUS_YQS("signed", "已签收"),
        EX_INFO_STATUS_YWC("completed", "已完成"),
        EX_INFO_STATUS_DKFSH("waitCustomerAudit", "待客服审核"),
        EX_INFO_STATUS_DFH("waitDelivery", "待发货"),
        EX_INFO_STATUS_ZBX("cancleToRepairs", "转报修"),
        EX_INFO_STATUS_ZTH("cancleToReturn", "转退货"),
        EX_INFO_STATUS_QT("cancleToOther", "作废"),;

        private String code;
        private String name;

        ExchangeStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        static List<ExchangeStatus> getAllStatus() {
            List<ExchangeStatus> statusList = new ArrayList<ExchangeStatus>();
            statusList.add(EX_INFO_STATUS_QX);
            statusList.add(EX_INFO_STATUS_QXDFH);
            statusList.add(EX_INFO_STATUS_DSB);
            statusList.add(EX_INFO_STATUS_SBYC);
            statusList.add(EX_INFO_STATUS_DCJ);
            statusList.add(EX_INFO_STATUS_DSHSH);
            statusList.add(EX_INFO_STATUS_YFH);
            statusList.add(EX_INFO_STATUS_YQS);
            statusList.add(EX_INFO_STATUS_YWC);
            statusList.add(EX_INFO_STATUS_DKFSH);
            statusList.add(EX_INFO_STATUS_DFH);
            statusList.add(EX_INFO_STATUS_ZBX);
            statusList.add(EX_INFO_STATUS_ZTH);
            statusList.add(EX_INFO_STATUS_QT);
            return statusList;
        }
    }

    /**
     * 维修单状态
     */
    enum RepairStatus {
        REP_INFO_STATUS_QX("0", "已取消"),
        REP_INFO_STATUS_DSB("1", "待收包"),
        REP_INFO_STATUS_SBYC("4", "报修收包异常"),
        REP_INFO_STATUS_DSHCJ("3", "待售后初检"),
        REP_INFO_STATUS_DKFSH("14", "待客服审核"),
        REP_INFO_STATUS_DSHWX("5", "待售后维修"),
        REP_INFO_STATUS_DFK("15", "待付款"),
        REP_INFO_STATUS_DFH("2", "待发货"),
        REP_INFO_STATUS_YFH("6", "已发货"),
        REP_INFO_STATUS_YQS("8", "已签收"),
        REP_INFO_STATUS_YWC("10", "已完成"),
        REP_INFO_STATUS_ZTH("16", "转退货"),
        REP_INFO_STATUS_ZHH("17", "转换货"),
        REP_INFO_STATUS_QT("18", "作废"),
        REP_INFO_STATUS_QXDFH("19", "取消待发货"),;
        private String code;
        private String name;

        RepairStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        static List<RepairStatus> getAllStatus() {
            List<RepairStatus> statusList = new ArrayList<RepairStatus>();
            statusList.add(REP_INFO_STATUS_QX);
            statusList.add(REP_INFO_STATUS_DSB);
            statusList.add(REP_INFO_STATUS_SBYC);
            statusList.add(REP_INFO_STATUS_DSHCJ);
            statusList.add(REP_INFO_STATUS_DKFSH);
            statusList.add(REP_INFO_STATUS_DSHWX);
            statusList.add(REP_INFO_STATUS_DFK);
            statusList.add(REP_INFO_STATUS_DFH);
            statusList.add(REP_INFO_STATUS_YFH);
            statusList.add(REP_INFO_STATUS_YQS);
            statusList.add(REP_INFO_STATUS_YWC);
            statusList.add(REP_INFO_STATUS_ZTH);
            statusList.add(REP_INFO_STATUS_ZHH);
            statusList.add(REP_INFO_STATUS_QT);
            statusList.add(REP_INFO_STATUS_QXDFH);
            return statusList;
        }
    }
}
