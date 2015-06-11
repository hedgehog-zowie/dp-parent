package com.iuni.dp.admin.datastat.action.iuniwmswl;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.persist.datastat.common.model.IuniWmsWarehouse;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.common.IuniWarehouseService;
import com.iuni.dp.service.datastat.service.wmswl.IuniWmsStockSourceForWlService;
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
 * WMS(物流)仓库出入库来源汇总报表
 *
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
@Controller("iuniWmsStockSourceAction")
@Scope("prototype")
public class IuniWmsStockSourceAction extends BaseAction {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static Calendar calendar = Calendar.getInstance();

    private final static Logger logger = LoggerFactory.getLogger(IuniWmsStockSourceAction.class);

    private Map<String, String> statParams = new HashMap<String, String>();

    private InputStream excelStream;

    private String fileName;

    private String flag;

    @Autowired
    private IuniWmsStockSourceForWlService iuniWmsStockSourceForWlService;
    @Autowired
    private IuniWarehouseService warehouseService;

    /**
     * 汇总页
     */
    private Page onePage = new Page();
    /**
     * 明细页
     */
    private Page twoPage = new Page();

    /**
     * 仓库代码
     */
    private static String warehouseCode = "0769";

    /**
     * 仓库列表
     */
    private List<IuniWmsWarehouse> warehouseList;

    /**
     * 返回页面的结果名 -- 汇总数据
     */
    private final static String RETURN_NAME_STOCK_SOURCE_SUM = "iuniWmsStockSourceForWlSum";
    /**
     * 返回页面的结果名 -- 明细数据
     */
    private final static String RETURN_NAME_STOCK_SOURCE = "iuniWmsStockSourceForWl";

    /**
     * action名称 -- 仓库出入库来源汇总报表
     */
    private final static String ACTION_NAME_STOCK_SOURCE = "iuniWmsStockSourceForWl";
    /**
     * action名称 -- 仓库出入库来源汇总报表导出
     */
    private final static String ACTION_NAME_STOCK_SOURCE_EXCEL = "iuniWmsStockSourceForWl2Excel";

    /**
     * 初始化仓库列表
     */
    private void initWarehouse() {
        warehouseList = warehouseService.queryAllWarehouse();
    }

    /**
     * IUNI WMS 仓库出入库来源汇总报表（物流）
     *
     * @return
     */
    public String iuniWmsStockSourceForWl() {
        initWarehouse();
        // 汇总结果集
        List<Map<String, Object>> iuniWmsStockSourceForWlSum;
        // 明细结果集
        List<Map<String, Object>> iuniWmsStockSourceForWl;
        try {
            //生成查询参数Map
            Map<String, Object> paramsSum = genParamMap(ACTION_NAME_STOCK_SOURCE);

            // 汇总记录条数
            Integer totalRecordSum = iuniWmsStockSourceForWlService.querySumWmsStockSourceForWlCount(paramsSum);
            //根据当前页、总记录数、页大小获得Page
            onePage.setPageSize(5);
            onePage = Page.genPage(onePage.getCurrentPage(), totalRecordSum, onePage.getPageSize());
            //新增Page至参数Map
            setPageInfo2Map(onePage, paramsSum);
            iuniWmsStockSourceForWlSum = iuniWmsStockSourceForWlService.querySumWmsStockSourceForWlByPage(paramsSum);

            //生成查询参数Map
            Map<String, Object> paramsDetail = genParamMap(ACTION_NAME_STOCK_SOURCE);
            // 明细记录条数
            Integer totalRecord = iuniWmsStockSourceForWlService.queryWmsStockSourceForWlCount(paramsDetail);
            //根据当前页、总记录数、页大小获得Page
            twoPage = Page.genPage(twoPage.getCurrentPage(), totalRecord, twoPage.getPageSize());
            //新增Page至参数Map
            setPageInfo2Map(twoPage, paramsDetail);
            iuniWmsStockSourceForWl = iuniWmsStockSourceForWlService.queryWmsStockSourceForWlByPage(paramsDetail);

        } catch (DBException dbex) {
            logger.error("IuniWmsStockSourceAction.iuniWmsStockSourceForWl found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsStockSourceAction.iuniWmsStockSourceForWl found Exception", ex);
            ex.printStackTrace();
            return ERROR;
        }
        if (iuniWmsStockSourceForWlSum == null)
            iuniWmsStockSourceForWlSum = new ArrayList<Map<String, Object>>();
        if (iuniWmsStockSourceForWl == null)
            iuniWmsStockSourceForWl = new ArrayList<Map<String, Object>>();

        request.setAttribute(RETURN_NAME_STOCK_SOURCE_SUM, iuniWmsStockSourceForWlSum);
        request.setAttribute(RETURN_NAME_STOCK_SOURCE, iuniWmsStockSourceForWl);

        return SUCCESS;
    }

    /**
     * IUNI WMS 仓库出入库来源汇总报表（物流）导出EXCEL
     *
     * @return
     */
    public String iuniWmsStockSourceForWl2Excel() {
        List<Map<String, Object>> iuniWmsStockList_sum;
        List<Map<String, Object>> iuniWmsStockList_detail;
        List<String> sumColumnNames;
        List<String> detailColumnNames;
        List<String> sumColumns;
        List<String> detailColumns;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap(ACTION_NAME_STOCK_SOURCE_EXCEL);
            iuniWmsStockList_sum = iuniWmsStockSourceForWlService.querySumWmsStockSourceForWlByPage(params);
            iuniWmsStockList_detail = iuniWmsStockSourceForWlService.queryWmsStockSourceForWlByPage(params);

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

                fileName = "IUNI WMS仓库出入库来源汇总报表（物流部）" + "_" + params.get("beginDate") + "-" + params.get("endDate");
                fileName = new String(fileName.getBytes(), "ISO8859-1");

                String sheetName_sum = "汇总";
                String sheetName_detail = "明细";

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
                    logger.error("IuniWmsStockSourceAction.iuniWmsStockSourceForWl2Excel found IOException.", e);
                }  finally {
                    if(null != baos) {
                        try {
                            baos.close();
                        } catch (IOException e) {
                            logger.error("IuniWmsStockSourceAction.iuniWmsStockSourceForWl2Excel found IOException.", e);
                        }
                    }
                }
            } else {
                return ERROR;
            }
        } catch (DBException dbex) {
            logger.error("IuniWmsStockSourceAction.iuniWmsStockSourceForWl2Excel found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsStockSourceAction.iuniWmsStockSourceForWl2Excel found Exception", ex);
            return ERROR;
        }
        return SUCCESS;
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

        if (StringUtils.isNotBlank(warehouseCode)) {
            params.put("warehouseCode", warehouseCode);
        }

        return params;
    }

    /**
     * 生成导出数据列名变量列表
     * @return List<String>
     */
    private List<String> genColVars() {
        List<String> cols = new ArrayList<String>();

        cols.add("rowNum");
        cols.add("DATETIME");
        cols.add("WAREHOUSE_NAME");
        cols.add("SOURCE_NAME");
        cols.add("BIZ_TYPE");
        cols.add("SKU_CODE");
        cols.add("CAT_NAME");
        cols.add("SKU_NAME");
        cols.add("MATERIAL_CODE");
        cols.add("MEASURE_UNIT");
        cols.add("QUANTITY");

        return cols;
    }

    /**
     * 生成导出数据列名列表
     * @return List<String>
     */
    private List<String> genSumColNames() {
        List<String> colNames = new ArrayList<String>();

        colNames.add("序号");
        colNames.add("时间段");
        colNames.add("仓库");
        colNames.add("订单来源");
        colNames.add("业务类型");
        colNames.add("SKU");
        colNames.add("商品类型");
        colNames.add("名称规格");
        colNames.add("ERP物料编码");
        colNames.add("单位");
        colNames.add("数量");

        return colNames;
    }

    /**
     * 生成导出数据列名列表
     * @return List<String>
     */
    private List<String> genDetailColNames() {
        List<String> colNames = new ArrayList<String>();

        colNames.add("序号");
        colNames.add("日期");
        colNames.add("仓库");
        colNames.add("订单来源");
        colNames.add("业务类型");
        colNames.add("SKU");
        colNames.add("商品类型");
        colNames.add("名称规格");
        colNames.add("ERP物料编码");
        colNames.add("单位");
        colNames.add("数量");

        return colNames;
    }

    /**
     * 新增Page至参数Map
     *
     * @param Map<String, Object> params
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

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public List<IuniWmsWarehouse> getWarehouseList() {
        return warehouseList;
    }

    public void setWarehouseList(List<IuniWmsWarehouse> warehouseList) {
        this.warehouseList = warehouseList;
    }

}
