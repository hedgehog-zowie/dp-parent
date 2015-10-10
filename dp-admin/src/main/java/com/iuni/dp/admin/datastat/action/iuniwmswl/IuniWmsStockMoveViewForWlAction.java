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
import com.iuni.dp.persist.datastat.common.model.OrderType;
import com.iuni.dp.service.datastat.service.common.OrderSourceService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.admin.datastat.constants.Channel;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.wmswl.IuniWmsStockMoveViewForWlService;

/**
 * WMS（物流）销售出库明细报表
 *
 * @author dan.wang@iuni.com
 */
@Controller("iuniWmsStockMoveViewForWlAction")
@Scope("prototype")
public class IuniWmsStockMoveViewForWlAction extends BaseAction {

    private static final long serialVersionUID = -3452828069720328443L;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Map<String, String> statParams = new HashMap<String, String>();

    private String flag;

    private InputStream excelStream;

    private String fileName;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IuniWmsStockMoveViewForWlService iuniWmsStockMoveViewForWlService;

    private String channelOfTransferCodesStr;
    private String channelOfOrderCodesStr;
    private String[] channelOfTransferCodes;
    private String[] channelOfOrderCodes;

    @Autowired
    private OrderSourceService orderSourceService;

    private List<OrderSource> orderSourceList;
    private List<OrderSource> transferSourceList;

    private String orderType;
    private List<OrderType> orderTypeList;

    private void initChannel() {
        orderTypeList = OrderType.getAllOrderType();

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

    public String iuniWmsStockMoveViewForWl() {
        initChannel();

        List<Map<String, Object>> stockMoveList = new ArrayList<Map<String, Object>>();
        try {
            Map<String, Object> queryParams = genParamMap();

            Integer count = iuniWmsStockMoveViewForWlService.queryIuniWmsStockMoveForWlCount(queryParams);

            page = Page.genPage(page.getCurrentPage(), count, page.getPageSize());
            queryParams.put("startRec", page.getStartRec());
            queryParams.put("endRec", page.getEndRec());

            stockMoveList = iuniWmsStockMoveViewForWlService.queryIuniWmsStockMoveForWl(queryParams);
            request.setAttribute("stockMoveList", stockMoveList);
        } catch (DBException e) {
            logger.error("IuniWmsStockMoveViewForWlAction.iuniWmsStockMoveViewForWl found DBException", e);
            return ERROR;
        } catch (Exception e) {
            logger.error("IuniWmsStockMoveViewForWlAction.iuniWmsStockMoveViewForWl found DBException", e);
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }


    public String iuniWmsStockMoveViewForWl2Excel() {

        List<Map<String, Object>> stockMove2ExcelList = iuniWmsStockMoveViewForWlService.queryIuniWmsStockMoveForWl2Excel(genParamMap());
        if (CollectionUtils.isNotEmpty(stockMove2ExcelList)) {
            String sheetName = "IUNI WMS销售出库明细报表（物流）";
            fileName = sheetName + "_" + dateFormat.format(new Date());
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            XSSFWorkbook workbook = ExcelUtil.getWorkbook4Xssf(gencolumnNames(), gencolumns(), stockMove2ExcelList, sheetName);
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
        columns.add("orderSource");
        columns.add("orderType");
        columns.add("sku");
        columns.add("waresName");
        columns.add("skuName");
        columns.add("materialCode");
        columns.add("orderCode");
        columns.add("outerOrderCode");
        columns.add("payNo");
        columns.add("deliveryCode");
        columns.add("price");
        columns.add("quantity");
        return columns;
    }


    private List<String> gencolumnNames() {
        List<String> columnNames = new ArrayList<String>();
        columnNames.add("序号");
        columnNames.add("日期");
        columnNames.add("销售渠道/类型");
        columnNames.add("订单类型");
        columnNames.add("SKU");
        columnNames.add("商品类型");
        columnNames.add("名称规格");
        columnNames.add("物料编码");
        columnNames.add("订单号");
        columnNames.add("外部单号");
        columnNames.add("支付流水号");
        columnNames.add("出库单号");
        columnNames.add("单价");
        columnNames.add("数量");
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

        if (StringUtils.isNotBlank(orderType)) {
            map.put("orderType", orderType);
        }

        map.put("transferList", channelOfTransferCodes != null && channelOfTransferCodes.length == 0 ? new String[]{""} : channelOfTransferCodes);
        map.put("orderList", channelOfOrderCodes != null && channelOfOrderCodes.length == 0 ? new String[]{""} : channelOfOrderCodes);
        return map;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public List<OrderType> getOrderTypeList() {
        return orderTypeList;
    }

    public void setOrderTypeList(List<OrderType> orderTypeList) {
        this.orderTypeList = orderTypeList;
    }
}
