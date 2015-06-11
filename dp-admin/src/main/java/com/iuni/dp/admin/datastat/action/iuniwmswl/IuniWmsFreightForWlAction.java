package com.iuni.dp.admin.datastat.action.iuniwmswl;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.persist.datastat.common.model.OrderSource;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.common.OrderSourceService;
import com.iuni.dp.service.datastat.service.wmswl.IuniWmsFreightForWlService;
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
@Controller("iuniWmsFreightForWlAction")
@Scope("prototype")
public class IuniWmsFreightForWlAction extends BaseAction {

    private final static Logger logger = LoggerFactory.getLogger(IuniWmsFreightForWlAction.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static Calendar calendar = Calendar.getInstance();

    private Map<String, String> statParams = new HashMap<String, String>();

    private InputStream excelStream;

    private String fileName;

    private String flag;

    private String direction;

    @Autowired
    private IuniWmsFreightForWlService freightForWlService;

    private final static String RETURN_RESULT_NAME = "result";

    /**
     * 运费报表
     *
     * @return
     */
    public String iuniWmsFreight() {
        // 结果集
        List<Map<String, Object>> result;
        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap();
            // 记录条数
            Integer totalRecord = freightForWlService.queryWmsFreightForWlCount(params);
            //根据当前页、总记录数、页大小获得Page
            page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
            //新增Page至参数Map
            setPageInfo2Map(page, params);
            result = freightForWlService.queryWmsFreightForWlByPage(params);
        } catch (DBException dbex) {
            logger.error("IuniWmsFreightForWlAction.iuniWmsFreight found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsFreightForWlAction.iuniWmsFreight found Exception", ex);
            ex.printStackTrace();
            return ERROR;
        }
        if (result == null)
            result = new ArrayList<Map<String, Object>>();

        request.setAttribute(RETURN_RESULT_NAME, result);

        return SUCCESS;
    }

    /**
     * 运费报表 导出EXCEL
     *
     * @return
     */
    public String iuniWmsFreight2Excel() {
        List<Map<String, Object>> result;
        List<String> columnNames;
        List<String> columns;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap();
            result = freightForWlService.queryWmsFreightForWlByPage(params);
            // 生成导出数据列名列表
            columnNames = genColNames();
            // 生成导出数据列名变量列表
            columns = genColVars();

            if (CollectionUtils.isNotEmpty(result) && CollectionUtils.isNotEmpty(columnNames)
                    && CollectionUtils.isNotEmpty(columns)) {
                // 按Sheet数据列表
                Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();

                String sheetName = "IUNI WMS运费报表（物流部）";
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
                    logger.error("IuniWmsFreightForWlAction.iuniWmsFreight2Excel found IOException.", e);
                } finally {
                    if (null != baos) {
                        try {
                            baos.close();
                        } catch (IOException e) {
                            logger.error("IuniWmsFreightForWlAction.iuniWmsFreight2Excel found IOException.", e);
                        }
                    }
                }
            } else {
                return ERROR;
            }
        } catch (DBException dbex) {
            logger.error("IuniWmsFreightForWlAction.iuniWmsFreight2Excel found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsFreightForWlAction.iuniWmsFreight2Excel found Exception", ex);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * 生成导出数据列名变量列表 - 退货
     *
     * @return List<String>
     */
    private List<String> genColVars() {
        List<String> cols = new ArrayList<String>();
        cols.add("rowNum");
        cols.add("shippingTime");
        cols.add("deliveryCode");
        cols.add("orderCode");
        cols.add("status");
        cols.add("userName");
        cols.add("address");
        cols.add("weight");
        cols.add("amount");
        cols.add("protectFee");
        cols.add("deliveryFee");
        return cols;
    }

    /**
     * 生成导出数据列名列表 - 退货
     *
     * @return List<String>
     */
    private List<String> genColNames() {
        List<String> colNames = new ArrayList<String>();
        colNames.add("序号");
        colNames.add("发货时间");
        colNames.add("运单号");
        colNames.add("订单号");
        colNames.add("签收情况");
        colNames.add("收货人姓名");
        colNames.add("收货人地址");
        colNames.add("重量");
        colNames.add("保价金额");
        colNames.add("保价费用");
        colNames.add("运输费用");
        colNames.add("订单类型");
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

        params.put("direction", "all");
        if (StringUtils.isNotBlank(direction))
            params.put("direction", direction);

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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
