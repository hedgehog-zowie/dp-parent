package com.iuni.dp.admin.datastat.action.iuniwms;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.wms.IuniRepairsService;
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
 * 非保修维修单报表
 *
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
@Controller("iuniWmsNotInWarrantyDetailAction")
@Scope("prototype")
public class IuniWmsNotInWarrantyDetail extends BaseAction {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static Calendar calendar = Calendar.getInstance();

    private final static Logger logger = LoggerFactory.getLogger(IuniWmsNotInWarrantyDetail.class);

    private Map<String, String> statParams = new HashMap<String, String>();

    private InputStream excelStream;

    private String fileName;

    private String flag;

    private final static String SPLITER = ",";

    @Autowired
    private IuniRepairsService iuniRepairsService;

    /**
     * 返回页面的结果名
     */
    private final static String RETURN_NAME_STOCK = "notInWarrantyList";

    /**
     * 非保修维修单报表
     *
     * @return
     */
    public String iuniWmsNotInWarrantyDetail() {
        // 结果集
        List<Map<String, Object>> notInWarrantyList;
        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap();
            // 记录条数
            Integer totalRecord = iuniRepairsService.queryNotInWarrantyRepairsCount(params);
            //根据当前页、总记录数、页大小获得Page
            page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
            //新增Page至参数Map
            setPageInfo2Map(page, params);
            notInWarrantyList = iuniRepairsService.queryNotInWarrantyRepairsForWlByPage(params);
        } catch (DBException dbex) {
            logger.error("IuniWmsNotInWarrantyDetail.iuniWmsNotInWarrantyDetail found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsNotInWarrantyDetail.iuniWmsNotInWarrantyDetail found Exception", ex);
            ex.printStackTrace();
            return ERROR;
        }
        if (notInWarrantyList == null)
            notInWarrantyList = new ArrayList<Map<String, Object>>();

        request.setAttribute(RETURN_NAME_STOCK, notInWarrantyList);

        return SUCCESS;
    }

    /**
     * 非保修维修单报表 导出EXCEL
     *
     * @return
     */
    public String iuniWmsNotInWarrantyDetail2Excel() {
        List<Map<String, Object>> notInWarrantyList;
        List<String> columnNames;
        List<String> columns;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap();
            notInWarrantyList = iuniRepairsService.queryNotInWarrantyRepairsForWlByPage(params);
            // 生成导出数据列名列表
            columnNames = genColNames();
            // 生成导出数据列名变量列表
            columns = genColVars();

            if (CollectionUtils.isNotEmpty(notInWarrantyList) && CollectionUtils.isNotEmpty(columnNames)
                    && CollectionUtils.isNotEmpty(columns)) {
                // 按Sheet数据列表
                Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();

                String sheetName = "IUNI非保修维修单报表";
                fileName = sheetName + "_" + params.get("beginDate") + "-" + params.get("endDate");
                fileName = new String(fileName.getBytes(), "ISO8859-1");

                sheetDataList.put(sheetName, notInWarrantyList);

                XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);

                ByteArrayOutputStream baos = null;
                try {
                    baos = new ByteArrayOutputStream();
                    workbook.write(baos);
                    byte[] ba = baos.toByteArray();
                    excelStream = new ByteArrayInputStream(ba);
                } catch (IOException e) {
                    logger.error("IuniWmsNotInWarrantyDetail.iuniWmsNotInWarrantyDetail2Excel found IOException.", e);
                } finally {
                    if (null != baos) {
                        try {
                            baos.close();
                        } catch (IOException e) {
                            logger.error("IuniWmsNotInWarrantyDetail.iuniWmsNotInWarrantyDetail2Excel found IOException.", e);
                        }
                    }
                }
            } else {
                return ERROR;
            }
        } catch (DBException dbex) {
            logger.error("IuniWmsNotInWarrantyDetail.iuniWmsNotInWarrantyDetail2Excel found DBException", dbex);
            return ERROR;

        } catch (Exception ex) {
            logger.error("IuniWmsNotInWarrantyDetail.iuniWmsNotInWarrantyDetail2Excel found Exception", ex);
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

        if (StringUtils.isNotBlank(statParams.get("payers"))) {
            String[] payerArray = statParams.get("payers").split(SPLITER);
            List<String> payerList = new ArrayList<String>();
            for (int i = 0; i < payerArray.length; i++) {
                if (StringUtils.isNotBlank(payerArray[i]))
                    payerList.add(payerArray[i].trim());
            }
            params.put("payers", payerList.toArray());
        }

        if (StringUtils.isNotBlank(statParams.get("clients"))) {
            String[] clientArray = statParams.get("clients").split(SPLITER);
            List<String> clientList = new ArrayList<String>();
            for (int i = 0; i < clientArray.length; i++) {
                if (StringUtils.isNotBlank(clientArray[i]))
                    clientList.add(clientArray[i].trim());
            }
            params.put("clients", clientList.toArray());
        }

        if (StringUtils.isNotBlank(statParams.get("phones"))) {
            String[] phoneArray = statParams.get("phones").split(SPLITER);
            List<String> phoneList = new ArrayList<String>();
            for (int i = 0; i < phoneArray.length; i++)
                if (StringUtils.isNotBlank(phoneArray[i]))
                    phoneList.add(phoneArray[i].trim());
            params.put("phones", phoneList.toArray());
        }

        return params;
    }

    /**
     * 生成导出数据变量列表
     *
     * @return
     */
    private List<String> genColVars() {
        List<String> cols = new ArrayList<String>();
        cols.add("rowNum");
        cols.add("PAY_CONFIRM_TIME");
        cols.add("PAYER");
        cols.add("REPAIRS_SN");
        cols.add("USERNAME");
        cols.add("MOBILE");
        cols.add("OIMEI");
        cols.add("MATTER_CODE");
        cols.add("MATTER_NAME");
        cols.add("QUANTITY");
        cols.add("MATTER_FEE");
        cols.add("HAND_FEE");
        cols.add("INVOICE_TIME");
        cols.add("INVOICE_CODE");
        cols.add("FEE");
        cols.add("REMARK");

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
        colNames.add("收款日期");
        colNames.add("付款人名称");
        colNames.add("维修单编号");
        colNames.add("客户名称");
        colNames.add("客户电话");
        colNames.add("IEMI号");
        colNames.add("物料编码");
        colNames.add("物料名称");
        colNames.add("数量");
        colNames.add("物料金额");
        colNames.add("维修等级金额");
        colNames.add("开票日期");
        colNames.add("发票号码");
        colNames.add("合计金额");
        colNames.add("备注");

        return colNames;
    }

    /**
     * 新增Page至参数Map
     *
     * @param params
     * @Param page
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
