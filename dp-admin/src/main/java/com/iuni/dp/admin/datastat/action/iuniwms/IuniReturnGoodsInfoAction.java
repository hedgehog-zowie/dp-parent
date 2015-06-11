package com.iuni.dp.admin.datastat.action.iuniwms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.IuniReturnGoodsInfoService;

/**
 * IuniReturnGoodsInfo Action
 *
 * @author Kenneth.Cai@iuni.com
 * @version dp-admin-1.1.5
 */
@Controller("iuniReturnGoodsInfoAction")
@Scope("prototype")
public class IuniReturnGoodsInfoAction extends BaseAction {

    private static final long serialVersionUID = 4492656256713292607L;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static Calendar calendar = Calendar.getInstance();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IuniReturnGoodsInfoService iuniReturnGoodsInfoService;

    private Map<String, String> statParams = new HashMap<String, String>();

    private InputStream excelStream;

    private String fileName;

    private String flag;

    // 查询条件
    private String orderCode;
    private String materialCode;

    /**
     * IUNI订单退款明细按条件统计
     *
     * @return String
     */
    public String iuniOrderRefundDetailsView() {
        List<Map<String, Object>> iuniOrderRefundDetailsList = null;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap("iuniOrderRefundDetails");

            Integer totalRecord = iuniReturnGoodsInfoService.queryIuniOrderRefundDetailsCount(params);

            //根据当前页、总记录数、页大小获得Page
            //page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
            page = Page.genPage(page.getCurrentPage(), totalRecord, 50);

            //新增Page至参数Map
            setPageInfo2Map(page, params);

            iuniOrderRefundDetailsList = iuniReturnGoodsInfoService.queryIuniOrderRefundDetailsByPage(params);

            request.setAttribute("iuniOrderRefundDetailsList", iuniOrderRefundDetailsList);

        } catch (DBException dbex) {
            logger.error("IuniReturnGoodsInfoAction.iuniOrderRefundDetailsView found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniReturnGoodsInfoAction.iuniOrderRefundDetailsView found Exception", ex);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * IUNI订单退款明细按条件统计列表导出至Excel
     *
     * @return String
     */
    public String iuniOrderRefundDetails2Excel() {
        List<Map<String, Object>> iuniOrderRefundDetailsList = null;
        List<String> columnNames = null;
        List<String> columns = null;

        try {
            //生成查询参数Map
            Map<String, Object> params = genParamMap("iuniOrderRefundDetails");
            iuniOrderRefundDetailsList = iuniReturnGoodsInfoService.queryIuniOrderRefundDetailsByExample(params);
            // 生成导出数据列名列表
            columnNames = genIuniOrderRefundDetailsColNames();
            // 生成导出数据列名变量列表
            columns = genIuniOrderRefundDetailsCols();

            if (CollectionUtils.isNotEmpty(iuniOrderRefundDetailsList) && CollectionUtils.isNotEmpty(columnNames)
                    && CollectionUtils.isNotEmpty(columns)) {
                // 按Sheet数据列表
                Map<String, List<Map<String, Object>>> sheetDataList = new HashMap<String, List<Map<String, Object>>>();

                String sheetName = "IUNI订单退款明细表";
                String exportDate = dateFormat.format(new Date());
                fileName = sheetName + "_" + exportDate;
                fileName = new String(fileName.getBytes(), "ISO8859-1");

                sheetDataList.put(sheetName, iuniOrderRefundDetailsList);

                XSSFWorkbook workbook = ExcelUtil.getWorkbook4XssfOnSheet(columnNames, columns, sheetDataList);

                ByteArrayOutputStream baos = null;
                try {
                    baos = new ByteArrayOutputStream();
                    workbook.write(baos);
                    byte[] ba = baos.toByteArray();
                    excelStream = new ByteArrayInputStream(ba);

                } catch (IOException e) {
                    logger.error("IuniReturnGoodsInfoAction.iuniOrderRefundDetails2Excel found IOException.", e);
                } finally {
                    if (null != baos) {
                        try {
                            baos.close();
                        } catch (IOException e) {
                            logger.error("IuniReturnGoodsInfoAction.iuniOrderRefundDetails2Excel found IOException.", e);
                        }
                    }
                }
            } else {
                return ERROR;
            }
        } catch (DBException dbex) {
            logger.error("IuniReturnGoodsInfoAction.iuniOrderRefundDetails2Excel found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniReturnGoodsInfoAction.iuniOrderRefundDetails2Excel found Exception", ex);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * 生成导出数据列名变量列表
     *
     * @return List<String>
     */
    private List<String> genIuniOrderRefundDetailsCols() {
        List<String> cols = new ArrayList<String>();
        cols.add("rowNum");
        cols.add("orderCode");
        cols.add("outerOrderCode");
        cols.add("addTime");
        cols.add("logTime");
        cols.add("skuCode");
        cols.add("materialCode");
        cols.add("goodsName");
        cols.add("goodsNum");
        cols.add("goodsAttr");
        cols.add("goodsPrice");
        cols.add("goodsAmount");
        cols.add("orderStatus");
        cols.add("payName");
        cols.add("bonus");
        cols.add("orderSource");

        return cols;
    }

    /**
     * 生成导出数据列名列表
     *
     * @return List<String>
     */
    private List<String> genIuniOrderRefundDetailsColNames() {
        List<String> colNames = new ArrayList<String>();
        colNames.add("序号");
        colNames.add("订单号");
        colNames.add("外部订单号");
        colNames.add("退款业务创建时间");
        colNames.add("退款时间");
        colNames.add("SKU");
        colNames.add("物料编码");
        colNames.add("商品名称");
        colNames.add("数量");
        colNames.add("属性");
        colNames.add("商品价格");
        colNames.add("商品金额");
        colNames.add("订单状态");
        colNames.add("支付方式");
        colNames.add("优惠券");
        colNames.add("订单来源");

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

        // 订单号
        if (StringUtils.isNotBlank(orderCode))
            params.put("orderCode", orderCode.trim());
        // 物料编码
        if (StringUtils.isNotBlank(materialCode))
            params.put("materialCode", materialCode.trim());

        return params;
    }

    /**
     * 新增Page至参数Map
     *
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
}
