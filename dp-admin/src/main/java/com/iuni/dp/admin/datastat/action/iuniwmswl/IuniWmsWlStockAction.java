package com.iuni.dp.admin.datastat.action.iuniwmswl;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.admin.datastat.constants.DateStyle;
import com.iuni.dp.persist.datastat.common.model.IuniWmsWarehouse;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.common.IuniWarehouseService;
import com.iuni.dp.service.datastat.service.wmswl.IuniWmsStockForWlService;
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
 * WMS(物流)仓库出入库数量汇总报表
 *
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
@Controller("iuniWmsWlStockAction")
@Scope("prototype")
public class IuniWmsWlStockAction extends BaseAction {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static Calendar calendar = Calendar.getInstance();

    private final static Logger logger = LoggerFactory.getLogger(IuniWmsWlStockAction.class);

    @Autowired
    private IuniWmsStockForWlService iuniWmsStockForWlService;
    @Autowired
    private IuniWarehouseService warehouseService;

    private Map<String, String> statParams = new HashMap<String, String>();

    private InputStream excelStream;

    private String fileName;

    private String flag;

    /**
     * 仓库代码
     */
    private static String warehouseCode = "0769";

    /**
     * 日期格式
     */
    private String dateStyle = "YYYY-MM-DD";

    private static Map<String, String> dateStyleMap = DateStyle.getDateStyleMap();

    // 仓库列表
    private List<IuniWmsWarehouse> warehouseList = new ArrayList<IuniWmsWarehouse>();

    private String skuCodeStr;

    private final static String SPLITER = ",";
    /**
     * 汇总页
     */
    private Page onePage = new Page();
    /**
     * 明细页
     */
    private Page twoPage = new Page();

    /**
     * action名称 -- 仓库出入库数量汇总报表
     */
    private final static String ACTION_NAME_STOCK = "iuniWmsStockForWl";
    /**
     * action名称 -- 仓库出入库数量汇总报表导出
     */
    private final static String ACTION_NAME_STOCK_EXCEL = "iuniWmsStockForWl2Excel";
    /**
     * 返回页面的结果名 -- 汇总数据
     */
    private final static String RETURN_NAME_STOCK_SUM = "iuniWmsStockForWlSum";
    /**
     * 返回页面的结果名 -- 明细数据
     */
    private final static String RETURN_NAME_STOCK = "iuniWmsStockForWl";

    /**
     * 初始化仓库列表
     */
    private void initWarehouse() {
        warehouseList = warehouseService.queryAllWarehouse();
    }

    /**
     * 仓库出入库数量汇总报表（物流）
     *
     * @return
     */
    public String iuniWmsStockForWl() {
        initWarehouse();
        // 汇总结果集
        List<Map<String, Object>> iuniWmsStockForWlSum;
        // 明细结果集
        List<Map<String, Object>> iuniWmsStockForWl;
        try {
            //生成查询参数Map
            Map<String, Object> paramsSum = genParamMap(ACTION_NAME_STOCK);

            // 汇总记录条数
            Integer totalRecordSum = iuniWmsStockForWlService.querySumWmsStockForWlCount(paramsSum);
            //根据当前页、总记录数、页大小获得Page
            onePage.setPageSize(5);
            onePage = Page.genPage(onePage.getCurrentPage(), totalRecordSum, onePage.getPageSize());
            //新增Page至参数Map
            setPageInfo2Map(onePage, paramsSum);
            iuniWmsStockForWlSum = iuniWmsStockForWlService.querySumWmsStockForWlByPage(paramsSum);

            //生成查询参数Map
            Map<String, Object> paramsDetail = genParamMap(ACTION_NAME_STOCK);
            // 明细记录条数
            Integer totalRecord = iuniWmsStockForWlService.queryWmsStockForWlCount(paramsDetail);
            //根据当前页、总记录数、页大小获得Page
            twoPage = Page.genPage(twoPage.getCurrentPage(), totalRecord, twoPage.getPageSize());
            //新增Page至参数Map
            setPageInfo2Map(twoPage, paramsDetail);
            iuniWmsStockForWl = iuniWmsStockForWlService.queryWmsStockForWlByPage(paramsDetail);

        } catch (DBException dbex) {
            logger.error("IuniWmsWlStockAction.iuniWmsStockForWl found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsWlStockAction.iuniWmsStockForWl found Exception", ex);
            ex.printStackTrace();
            return ERROR;
        }
        if (iuniWmsStockForWlSum == null)
            iuniWmsStockForWlSum = new ArrayList<Map<String, Object>>();
        if (iuniWmsStockForWl == null)
            iuniWmsStockForWl = new ArrayList<Map<String, Object>>();

        request.setAttribute(RETURN_NAME_STOCK_SUM, iuniWmsStockForWlSum);
        request.setAttribute(RETURN_NAME_STOCK, iuniWmsStockForWl);

        return SUCCESS;
    }

    /**
     * 仓库出入库数量汇总报表（物流）导出EXCEL
     */
    public String iuniWmsStockForWl2Excel() {
        List<Map<String, Object>> iuniWmsStockList_sum;
        List<Map<String, Object>> iuniWmsStockList_detail;
        List<String> sumColumnNames;
        List<String> detailColumnNames;
        List<String> sumColumns;
        List<String> detailColumns;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap(ACTION_NAME_STOCK_EXCEL);
            iuniWmsStockList_sum = iuniWmsStockForWlService.querySumWmsStockForWlByPage(params);
            iuniWmsStockList_detail = iuniWmsStockForWlService.queryWmsStockForWlByPage(params);

            // 生成导出数据列名列表 - 汇总
            sumColumnNames = genSumColNames();
            // 生成导出数据列名列表 - 明细
            detailColumnNames = genDetailColNames();

            // 生成导出数据列名变量列表 - 汇总
            sumColumns = genColVars();
            // 生成导出数据列名变量列表 - 明细
            detailColumns = genColVars();

            if(CollectionUtils.isNotEmpty(iuniWmsStockList_sum) && CollectionUtils.isNotEmpty(iuniWmsStockList_detail)
                    && CollectionUtils.isNotEmpty(sumColumnNames) && CollectionUtils.isNotEmpty(detailColumnNames)
                    && CollectionUtils.isNotEmpty(sumColumns) && CollectionUtils.isNotEmpty(sumColumns)) {
                // 按Sheet数据列表
                Map<String, List<String>> columnNamesMap = new HashMap<String, List<String>>();
                Map<String, List<String>> columnsMap = new HashMap<String, List<String>>();
                Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();

                String sheetName_sum = "汇总";
                String sheetName_detail = "明细";
                String exportDate = dateFormat.format(new Date());
                fileName = "IUNI WMS仓库出入库数量汇总报表（物流部）" + "_" + params.get("beginDate") + "-" + params.get("endDate");
                fileName = new String(fileName.getBytes(), "ISO8859-1");

                columnNamesMap.put(sheetName_sum, sumColumnNames);
                columnNamesMap.put(sheetName_detail, detailColumnNames);
                columnsMap.put(sheetName_sum, sumColumns);
                columnsMap.put(sheetName_detail, detailColumns);
                sheetDataList.put(sheetName_sum, iuniWmsStockList_sum);
                sheetDataList.put(sheetName_detail, iuniWmsStockList_detail);

                XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNamesMap, columnsMap, sheetDataList);

                ByteArrayOutputStream baos = null;
                try {
                    baos = new ByteArrayOutputStream();
                    workbook.write(baos);
                    byte[] ba = baos.toByteArray();
                    excelStream = new ByteArrayInputStream(ba);
                } catch (IOException e) {
                    logger.error("IuniWmsStockAction.iuniWmsStockForWl2Excel found IOException.", e);
                }  finally {
                    if(null != baos) {
                        try {
                            baos.close();
                        } catch (IOException e) {
                            logger.error("IuniWmsStockAction.iuniWmsStockForWl2Excel found IOException.", e);
                        }
                    }
                }
            } else {
                return ERROR;
            }
        } catch (DBException dbex) {
            logger.error("IuniWmsStockAction.iuniWmsStockForWl2Excel found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsStockAction.iuniWmsStockForWl2Excel found Exception", ex);
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 生成导出数据列名变量列表
     * @return List<String>
     */
    private List<String> genColVars() {
        List<String> cols = new ArrayList<String>();

        cols.add("rowNum");
        cols.add("DATETIME");
        cols.add("WAREHOUSE");
        cols.add("SKUCODE");
        cols.add("GOODSNAME");
        cols.add("SKUNAME");
        cols.add("MATERIALCODE");
        cols.add("MEASUREUNIT");
        cols.add("INSTOCKQTY");
        cols.add("OUTSTOCKQTY");
        cols.add("ENDSTOCKQTY");
        cols.add("ENDNONDEFEQTY");
        cols.add("ENDDEFEQTY");
        cols.add("OCCUPYSTOCKQTY");

        return cols;
    }

    /**
     * 生成导出数据列名列表 - 汇总
     * @return List<String>
     */
    private List<String> genSumColNames() {
        List<String> colNames = new ArrayList<String>();

        colNames.add("序号");
        colNames.add("时间段");
        colNames.add("仓库");
        colNames.add("SKU");
        colNames.add("商品类型");
        colNames.add("名称规格");
        colNames.add("ERP物料编码");
        colNames.add("单位");
        colNames.add("入库");
        colNames.add("出库");
        colNames.add("总库存");
        colNames.add("可销库存");
        colNames.add("不可销库存");
        colNames.add("占用库存");

        return colNames;
    }

    /**
     * 生成导出数据列名列表 - 汇总
     * @return List<String>
     */
    private List<String> genDetailColNames() {
        List<String> colNames = new ArrayList<String>();

        colNames.add("序号");
        colNames.add("日期");
        colNames.add("仓库");
        colNames.add("SKU");
        colNames.add("商品类型");
        colNames.add("名称规格");
        colNames.add("ERP物料编码");
        colNames.add("单位");
        colNames.add("入库");
        colNames.add("出库");
        colNames.add("总库存");
        colNames.add("可销库存");
        colNames.add("不可销库存");
        colNames.add("占用库存");

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

        if (StringUtils.isNotBlank(skuCodeStr)) {
            String[] skuCodes = skuCodeStr.split(SPLITER);
            for (int i = 0; i < skuCodes.length; i++)
                skuCodes[i] = skuCodes[i].trim();
            params.put("skuCodes", skuCodes);
        }

        if (StringUtils.isNotBlank(dateStyle)) {
            params.put("dateStyle", dateStyle);
        }

        if (StringUtils.isNotBlank(warehouseCode)) {
            params.put("warehouseCode", warehouseCode);
        }

        return params;
    }

    /**
     * 新增Page至参数Map
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

    public String getDateStyle() {
        return dateStyle;
    }

    public void setDateStyle(String dateStyle) {
        this.dateStyle = dateStyle;
    }

    public Map getDateStyleMap() {
        return dateStyleMap;
    }

    public void setDateStyleMap(Map dateStyleMap) {
        this.dateStyleMap = dateStyleMap;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public Page getOnePage() {
        return onePage;
    }

    public void setOnePage(Page onePage) {
        this.onePage = onePage;
    }

    public Page getTwoPage() {
        return twoPage;
    }

    public void setTwoPage(Page twoPage) {
        this.twoPage = twoPage;
    }

    public String getSkuCodeStr() {
        return skuCodeStr;
    }

    public void setSkuCodeStr(String skuCodeStr) {
        this.skuCodeStr = skuCodeStr;
    }

    public List<IuniWmsWarehouse> getWarehouseList() {
        return warehouseList;
    }

    public void setWarehouseList(List<IuniWmsWarehouse> warehouseList) {
        this.warehouseList = warehouseList;
    }
}