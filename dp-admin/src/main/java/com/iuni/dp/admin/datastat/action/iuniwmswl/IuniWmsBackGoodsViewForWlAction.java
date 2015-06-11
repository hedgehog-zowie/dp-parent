package com.iuni.dp.admin.datastat.action.iuniwmswl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.common.model.OrderSource;
import com.iuni.dp.service.datastat.service.common.OrderSourceService;
import org.apache.commons.collections.CollectionUtils;
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
import com.iuni.dp.service.datastat.service.wmswl.IuniWmsBackGoodsViewForWlService;

/**
 * WMS(物流)退货明细报表
 *
 * @author dan.wang@iuni.com
 */
@Controller("iuniWmsBackGoodsViewForWlAction")
@Scope("prototype")
public class IuniWmsBackGoodsViewForWlAction extends BaseAction {

    private static final long serialVersionUID = 1096230515140119403L;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Map<String, String> statParams = new HashMap<String, String>();

    private String flag;

    private InputStream excelStream;

    private String fileName;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String backChannelOfReceiveCodesStr;
    private String backChannelOfbackCodesStr;
    private String[] backChannelOfReceiveCodes;
    private String[] backChannelOfbackCodes;

    @Autowired
    private IuniWmsBackGoodsViewForWlService iuniWmsBackGoodsViewForWlService;

    @Autowired
    private OrderSourceService orderSourceService;
    private List<OrderSource> orderSourceList;
    private List<OrderSource> transferSourceList;

    private void initChannel() {
        transferSourceList = orderSourceService.getAllWmsTransferSource();
        orderSourceList = orderSourceService.getAllWmsOrderSource();

        if (backChannelOfReceiveCodesStr != null)
            backChannelOfReceiveCodes = backChannelOfReceiveCodesStr.split(",");
        if (backChannelOfReceiveCodes == null) {
            backChannelOfReceiveCodes = new String[transferSourceList.size()];
            for (int i = 0; i < transferSourceList.size(); i++)
                backChannelOfReceiveCodes[i] = transferSourceList.get(i).getSourceCode();
        }

        if (backChannelOfbackCodesStr != null)
            backChannelOfbackCodes = backChannelOfbackCodesStr.split(",");
        if (backChannelOfbackCodes == null) {
            backChannelOfbackCodes = new String[orderSourceList.size()];
            for (int i = 0; i < orderSourceList.size(); i++)
                backChannelOfbackCodes[i] = orderSourceList.get(i).getSourceCode();
        }
    }

    public String iuniWmsBackGoodsViewForWl() {
        initChannel();
        transferSourceList = orderSourceService.getAllWmsTransferSource();
        orderSourceList = orderSourceService.getAllWmsOrderSource();
        List<Map<String, Object>> backGoodsList = new ArrayList<Map<String, Object>>();
        try {
            Map<String, Object> queryParams = genParamMap();

            Integer count = iuniWmsBackGoodsViewForWlService.queryIuniWmsBackGoodsForWlCount(queryParams);
            page = Page.genPage(page.getCurrentPage(), count, page.getPageSize());
            queryParams.put("startRec", page.getStartRec());
            queryParams.put("endRec", page.getEndRec());

            backGoodsList = iuniWmsBackGoodsViewForWlService.queryIuniWmsBackGoodsForWlByPage(queryParams);
        } catch (DBException e) {
            logger.error("IuniWmsBackGoodsViewForWlAction.iuniWmsBackGoodsViewForWl found DBException", e);
            return ERROR;
        } catch (Exception e) {
            logger.error("IuniWmsBackGoodsViewForWlAction.iuniWmsBackGoodsViewForWl found Exception", e);
            e.printStackTrace();
            return ERROR;
        }

        request.setAttribute("backGoodsList", backGoodsList);
        return SUCCESS;
    }

    public String iuniWmsBackGoodsViewForWl2Excel() {
        Map<String, Object> queryParams = genParamMap();
        List<Map<String, Object>> backGoodsList2ExcelList = iuniWmsBackGoodsViewForWlService.queryIuniWmsBackGoodsForWl2Excel(queryParams);
        if (CollectionUtils.isNotEmpty(backGoodsList2ExcelList)) {
            String sheetName = "退货明细报表（物流）";
            fileName = sheetName + "_" + dateFormat.format(new Date());
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                logger.error("IuniWmsBackGoodsViewForWlAction.iuniWmsBackGoodsViewForWl2Excel found UnsupportedEncodingException.", e);
            }
            XSSFWorkbook workbook = ExcelUtil.getWorkbook4Xssf(gencolumnNames(), gencolumns(), backGoodsList2ExcelList, sheetName);
            //io
            ByteArrayOutputStream bos = null;
            try {
                bos = new ByteArrayOutputStream();
                workbook.write(bos);
                byte[] b = bos.toByteArray();
                excelStream = new ByteArrayInputStream(b);
            } catch (IOException e) {
                logger.error("IuniWmsStockMoveViewForWlAction.iuniWmsStockMoveViewForWl2Excel found IOException", e);
            } finally {
                try {
                    bos.close();
                } catch (IOException e) {
                    logger.error("IuniWmsStockMoveViewForWlAction.iuniWmsStockMoveViewForWl2Excel found IOException", e);
                }
            }//end io
        } else return ERROR;
        return SUCCESS;
    }

    private List<String> gencolumns() {
        List<String> columns = new ArrayList<String>();
        columns.add("RN");
        columns.add("time");
        columns.add("backChannel");
        columns.add("orderCode");
        columns.add("orderUser");
        columns.add("sku");
        columns.add("skuName");
        columns.add("quantity");
        columns.add("backReason");
        columns.add("remark");
        columns.add("invoice");
        columns.add("shippingName");
        columns.add("backCode");
        columns.add("IMEI");
        columns.add("handledBy");
        return columns;
    }

    private List<String> gencolumnNames() {
        List<String> columnNames = new ArrayList<String>();
        columnNames.add("序号");
        columnNames.add("日期");
        columnNames.add("退货渠道");
        columnNames.add("订单号");
        columnNames.add("客户姓名");
        columnNames.add("SKU");
        columnNames.add("名称规格");
        columnNames.add("数量");
        columnNames.add("退回原因");
        columnNames.add("备注");
        columnNames.add("发票");
        columnNames.add("退回物流");
        columnNames.add("退回单号");
        columnNames.add("IMEI");
        columnNames.add("快递签收人");
        return columnNames;
    }

    private Map<String, Object> genParamMap() {
        Map<String, Object> map = new HashMap<String, Object>();

        //默认查询日期
        if ("default".equals(flag)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
            String eDate = dateFormat.format(calendar.getTime());
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 6);
            String sDate = dateFormat.format(calendar.getTime());
            map.put("beginDate", sDate);
            map.put("endDate", eDate);

            // 设置日期过滤框默认值
            statParams.put("beginDate", sDate);
            statParams.put("endDate", eDate);

        }

        map.put("beginDate", statParams.get("beginDate"));
        map.put("endDate", statParams.get("endDate"));

        map.put("receiveOption", backChannelOfReceiveCodes != null && backChannelOfReceiveCodes.length == 0 ? new String[]{""} : backChannelOfReceiveCodes);
        map.put("backOption", backChannelOfbackCodes != null && backChannelOfbackCodes.length == 0 ? new String[]{""} : backChannelOfbackCodes);

        return map;
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


    public String[] getBackChannelOfReceiveCodes() {
        return backChannelOfReceiveCodes;
    }


    public void setBackChannelOfReceiveCodes(String[] backChannelOfReceiveCodes) {
        this.backChannelOfReceiveCodes = backChannelOfReceiveCodes;
    }


    public String[] getBackChannelOfbackCodes() {
        return backChannelOfbackCodes;
    }


    public void setBackChannelOfbackCodes(String[] backChannelOfbackCodes) {
        this.backChannelOfbackCodes = backChannelOfbackCodes;
    }

    public String getBackChannelOfReceiveCodesStr() {
        return backChannelOfReceiveCodesStr;
    }

    public void setBackChannelOfReceiveCodesStr(String backChannelOfReceiveCodesStr) {
        this.backChannelOfReceiveCodesStr = backChannelOfReceiveCodesStr;
    }

    public String getBackChannelOfbackCodesStr() {
        return backChannelOfbackCodesStr;
    }

    public void setBackChannelOfbackCodesStr(String backChannelOfbackCodesStr) {
        this.backChannelOfbackCodesStr = backChannelOfbackCodesStr;
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
