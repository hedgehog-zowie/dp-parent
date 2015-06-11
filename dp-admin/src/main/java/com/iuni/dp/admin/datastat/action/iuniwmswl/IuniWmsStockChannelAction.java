package com.iuni.dp.admin.datastat.action.iuniwmswl;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.admin.datastat.constants.Channel;
import com.iuni.dp.admin.datastat.constants.ChannelOfOrderType;
import com.iuni.dp.admin.datastat.constants.ChannelOfTransferType;
import com.iuni.dp.persist.datastat.common.model.OrderSource;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.common.OrderSourceService;
import com.iuni.dp.service.datastat.service.wmswl.IuniWmsStockChannelForWlService;
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
 * WMS(物流)各渠道进退换数量汇总报表
 *
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
@Controller("iuniWmsStockChannelAction")
@Scope("prototype")
public class IuniWmsStockChannelAction extends BaseAction {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static Calendar calendar = Calendar.getInstance();

    private final static Logger logger = LoggerFactory.getLogger(IuniWmsStockChannelAction.class);

    private Map<String, String> statParams = new HashMap<String, String>();

    private InputStream excelStream;

    private String fileName;

    private String flag;

    @Autowired
    private IuniWmsStockChannelForWlService iuniWmsStockChannelForWlService;

    /**
     * action名称 -- 各渠道进退换货数量汇总报表
     */
    private final static String ACTION_NAME_STOCK_CHANNEL = "iuniWmsStockChannelForWl";
    /**
     * action名称 -- 各渠道进退换货数量汇总报表导出
     */
    private final static String ACTION_NAME_STOCK_CHANNEL_EXCEL = "iuniWmsStockChannelForWl2Excel";
    /**
     * 返回页面的结果名
     */
    private final static String RETURN_NAME_STOCK = "iuniWmsStockChannelForWl";

    private String channelOfTransferCodesStr;
    private String[] channelOfTransferCodes;
    private String channelOfOrderCodesStr;
    private String[] channelOfOrderCodes;

    @Autowired
    private OrderSourceService orderSourceService;
    private List<OrderSource> orderSourceList;
    private List<OrderSource> transferSourceList;

    /**
     * 初始化渠道信息
     */
    private void initChannel() {
        transferSourceList = orderSourceService.getAllWmsTransferSource();
        orderSourceList = orderSourceService.getAllWmsOrderSource();

        if (channelOfTransferCodesStr != null)
            channelOfTransferCodes = channelOfTransferCodesStr.split(",");
        if (channelOfTransferCodes == null) {
            channelOfTransferCodes = new String[transferSourceList.size()];
            for (int i = 0; i < transferSourceList.size(); i++)
                channelOfTransferCodes[i] = transferSourceList.get(i).getSourceCode();
        }

        if (channelOfOrderCodesStr != null)
            channelOfOrderCodes = channelOfOrderCodesStr.split(",");
        if (channelOfOrderCodes == null) {
            channelOfOrderCodes = new String[orderSourceList.size()];
            for (int i = 0; i < orderSourceList.size(); i++)
                channelOfOrderCodes[i] = orderSourceList.get(i).getSourceCode();
        }
    }

    /**
     * IUNI WMS 各渠道进退换数量汇总报表（物流）
     *
     * @return
     */
    public String iuniWmsStockChannelForWl() {
        initChannel();
        // 结果集
        List<Map<String, Object>> iuniWmsStockChannelForWl;
        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap(ACTION_NAME_STOCK_CHANNEL);
            // 记录条数
            Integer totalRecord = iuniWmsStockChannelForWlService.queryWmsStockChannelForWlCount(params);
            //根据当前页、总记录数、页大小获得Page
            page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
            //新增Page至参数Map
            setPageInfo2Map(page, params);
            iuniWmsStockChannelForWl = iuniWmsStockChannelForWlService.queryWmsStockChannelForWlByPage(params);
        } catch (DBException dbex) {
            logger.error("IuniWmsWlStockAction.iuniWmsStockForWl found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsWlStockAction.iuniWmsStockForWl found Exception", ex);
            ex.printStackTrace();
            return ERROR;
        }
        if (iuniWmsStockChannelForWl == null)
            iuniWmsStockChannelForWl = new ArrayList<Map<String, Object>>();

        request.setAttribute(RETURN_NAME_STOCK, iuniWmsStockChannelForWl);

        return SUCCESS;
    }

    /**
     * IUNI WMS 各渠道进退换数量汇总报表（物流）导出EXCEL
     *
     * @return
     */
    public String iuniWmsStockChannelForWl2Excel() {
        List<Map<String, Object>> iuniWmsStockChannelList;
        List<String> columnNames;
        List<String> columns;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap(ACTION_NAME_STOCK_CHANNEL_EXCEL);
            iuniWmsStockChannelList = iuniWmsStockChannelForWlService.queryWmsStockChannelForWlByPage(params);
            // 生成导出数据列名列表
            columnNames = genColNames();
            // 生成导出数据列名变量列表
            columns = genColVars();

            if (CollectionUtils.isNotEmpty(iuniWmsStockChannelList) && CollectionUtils.isNotEmpty(columnNames)
                    && CollectionUtils.isNotEmpty(columns)) {
                // 按Sheet数据列表
                Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();

                String sheetName = "IUNI WMS各渠道进退换数量汇总报表（物流部）";
                fileName = sheetName + "_" + params.get("beginDate") + "-" + params.get("endDate");
                fileName = new String(fileName.getBytes(), "ISO8859-1");

                sheetDataList.put(sheetName, iuniWmsStockChannelList);

                XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);

                ByteArrayOutputStream baos = null;
                try {
                    baos = new ByteArrayOutputStream();
                    workbook.write(baos);
                    byte[] ba = baos.toByteArray();
                    excelStream = new ByteArrayInputStream(ba);
                } catch (IOException e) {
                    logger.error("IuniWmsStockChannelAction.iuniWmsStockChannelForWl2Excel found IOException.", e);
                } finally {
                    if (null != baos) {
                        try {
                            baos.close();
                        } catch (IOException e) {
                            logger.error("IuniWmsStockChannelAction.iuniWmsStockChannelForWl2Excel found IOException.", e);
                        }
                    }
                }
            } else {
                return ERROR;
            }
        } catch (DBException dbex) {
            logger.error("IuniWmsStockChannelAction.iuniWmsStockChannelForWl2Excel found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsStockChannelAction.iuniWmsStockChannelForWl2Excel found Exception", ex);
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 生成导出数据列名变量列表
     *
     * @return List<String>
     */
    private List<String> genColVars() {
        List<String> cols = new ArrayList<String>();
        cols.add("rowNum");
        cols.add("DATETIME");
        cols.add("SOURCE_NAME");
        cols.add("SKU_CODE");
        cols.add("CAT_NAME");
        cols.add("SKU_NAME");
        cols.add("MATERIAL_CODE");
        cols.add("MEASURE_UNIT");
        cols.add("FH");
        cols.add("TH");
        cols.add("HH");
        cols.add("JS");
        cols.add("SDTH");
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
        colNames.add("日期");
        colNames.add("渠道");
        colNames.add("SKU");
        colNames.add("商品类型");
        colNames.add("名称规格");
        colNames.add("ERP物料编码");
        colNames.add("单位");
        colNames.add("发货");
        colNames.add("退货");
        colNames.add("换货");
        colNames.add("拒收");
        colNames.add("刷单退回");
        return colNames;
    }

    /**
     * 生成查询参数Map
     *
     * @return Map<String, Object>
     * @throws java.text.ParseException
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

        if (channelOfOrderCodes != null) {
            if (channelOfOrderCodes.length == 0)
                channelOfOrderCodes = new String[]{""};
            params.put("channelOfOrderCodes", channelOfOrderCodes);
        }

        if (channelOfTransferCodes != null) {
            if (channelOfTransferCodes.length == 0)
                channelOfTransferCodes = new String[]{""};
            params.put("channelOfTransferCodes", channelOfTransferCodes);
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

    public String[] getChannelOfTransferCodes() {
        return channelOfTransferCodes;
    }

    public void setChannelOfTransferCodes(String[] channelOfTransferCodes) {
        this.channelOfTransferCodes = channelOfTransferCodes;
    }

    public String[] getChannelOfOrderCodes() {
        return channelOfOrderCodes;
    }

    public void setChannelOfOrderCodes(String[] channelOfOrderCodes) {
        this.channelOfOrderCodes = channelOfOrderCodes;
    }

    public String getChannelOfTransferCodesStr() {
        return channelOfTransferCodesStr;
    }

    public void setChannelOfTransferCodesStr(String channelOfTransferCodesStr) {
        this.channelOfTransferCodesStr = channelOfTransferCodesStr;
    }

    public String getChannelOfOrderCodesStr() {
        return channelOfOrderCodesStr;
    }

    public void setChannelOfOrderCodesStr(String channelOfOrderCodesStr) {
        this.channelOfOrderCodesStr = channelOfOrderCodesStr;
    }

    public List<OrderSource> getOrderSourceList() {
        return orderSourceList;
    }

    public void setOrderSourceList(List<OrderSource> orderSourceList) {
        this.orderSourceList = orderSourceList;
    }

    public List<OrderSource> getTransferSourceList() {
        return transferSourceList;
    }

    public void setTransferSourceList(List<OrderSource> transferSourceList) {
        this.transferSourceList = transferSourceList;
    }

}
