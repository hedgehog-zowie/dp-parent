package com.iuni.dp.admin.datastat.action.iuniwms;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.wms.IuniAfterSalesService;
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
@Controller("iuniAfterSalesAction")
@Scope("prototype")
public class IuniAfterSalesAction extends BaseAction {
    private static final long serialVersionUID = 6480886298470413701L;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static Calendar calendar = Calendar.getInstance();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Map<String, String> statParams = new HashMap<String, String>();

    private InputStream excelStream;

    private String fileName;

    private String flag;

    @Autowired
    private IuniAfterSalesService afterSalesService;

    /**
     * IUNI普通订单售后次数统计报表
     * @return String
     */
    public String iuniAfterSalesNum() {
        List<Map<String, Object>> resultList = null;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap();

            Integer totalRecord = afterSalesService.queryIuniAfterSalesNumCount(params);

            //根据当前页、总记录数、页大小获得Page
            page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());

            //新增Page至参数Map
            setPageInfo2Map(page, params);

            resultList = afterSalesService.queryIuniAfterSalesNumByPage(params);

            request.setAttribute("resultList", resultList);

        } catch (DBException dbex) {
            logger.error("IuniAfterSalesAction.iuniAfterSalesNum found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniAfterSalesAction.iuniAfterSalesNum found Exception", ex);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * IUNI普通订单售后次数统计报表导出至Excel
     * @return String
     */
    public String iuniAfterSalesNum2Excel() {
        List<Map<String, Object>> iuniAlipayList = null;
        List<String> columnNames;
        List<String> columns;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap();
            iuniAlipayList = afterSalesService.queryIuniAfterSalesNumByPage(params);
            // 生成导出数据列名列表
            columnNames = genIuniAlipayColNames();
            // 生成导出数据列名变量列表
            columns = genIuniAlipayCols();

            if(CollectionUtils.isNotEmpty(iuniAlipayList) && CollectionUtils.isNotEmpty(columnNames)
                    && CollectionUtils.isNotEmpty(columns)) {
                // 按Sheet数据列表
                Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();

                String sheetName = "IUNI普通订单售后次数统计报表";
                fileName = sheetName + "_" + params.get("beginDate") + "-" + params.get("endDate");
                fileName = new String(fileName.getBytes(), "ISO8859-1");

                sheetDataList.put(sheetName, iuniAlipayList);

                XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);

                ByteArrayOutputStream baos = null;
                try {
                    baos = new ByteArrayOutputStream();
                    workbook.write(baos);
                    byte[] ba = baos.toByteArray();
                    excelStream = new ByteArrayInputStream(ba);

                } catch (IOException e) {
                    logger.error("IuniAfterSalesAction.iuniAfterSalesNum2Excel found IOException.", e);
                }  finally {
                    if(null != baos) {
                        try {
                            baos.close();
                        } catch (IOException e) {
                            logger.error("IuniAfterSalesAction.iuniAfterSalesNum2Excel found IOException.", e);
                        }
                    }
                }
            } else {
                return ERROR;
            }

        } catch (DBException dbex) {
            logger.error("IuniAfterSalesAction.iuniAfterSalesNum2Excel found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniAfterSalesAction.iuniAfterSalesNum2Excel found Exception", ex);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * 生成导出数据列名变量列表
     * @return List<String>
     */
    private List<String> genIuniAlipayCols() {
        List<String> cols = new ArrayList<String>();
        cols.add("rowNum");
        cols.add("orderSn");
        cols.add("addTime");
        cols.add("userName");
        cols.add("mobile");
        cols.add("type");
        cols.add("sn");
        cols.add("createTime");
        cols.add("status");
        cols.add("causeInfo");
        cols.add("remark");
        return cols;
    }

    /**
     * 生成导出数据列名列表
     * @return List<String>
     */
    private List<String> genIuniAlipayColNames() {
        List<String> colNames = new ArrayList<String>();
        colNames.add("序号");
        colNames.add("订单号");
        colNames.add("下单时间");
        colNames.add("收件人姓名");
        colNames.add("收货人手机");
        colNames.add("售后类型");
        colNames.add("换货/维修单号");
        colNames.add("退/换/修申请时间");
        colNames.add("售后单当前状态");
        colNames.add("申请原因");
        colNames.add("售后审核结果");

        return colNames;
    }

    /**
     * 生成查询参数Map
     * @return Map<String, Object>
     * @throws ParseException
     */
    private Map<String, Object> genParamMap() throws ParseException {
        Map<String, Object> params = new HashMap<String, Object>();

        if("default".equals(flag)) {
            calendar.setTime(new Date());
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-1);
            String eDate = dateFormat.format(calendar.getTime());
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-6);
            String sDate = dateFormat.format(calendar.getTime());
            params.put("beginDate", sDate);
            params.put("endDate", eDate);

            // 设置日期过滤框默认值
            statParams.put("beginDate", sDate);
            statParams.put("endDate", eDate);
        }

        if(StringUtils.isNotBlank(statParams.get("beginDate"))) {
            params.put("beginDate", statParams.get("beginDate"));
        }

        if(StringUtils.isNotBlank(statParams.get("endDate"))) {
            params.put("endDate", statParams.get("endDate"));
        }

        return params;
    }

    /**
     * 新增Page至参数Map
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
}
