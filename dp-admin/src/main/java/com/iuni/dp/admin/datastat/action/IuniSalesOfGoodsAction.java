package com.iuni.dp.admin.datastat.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.common.model.OrderSource;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.common.OrderSourceService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.admin.datastat.constants.DateStyle;
import com.iuni.dp.admin.datastat.constants.OrderRefer;
import com.iuni.dp.admin.datastat.constants.SalesOfGoodType;
import com.iuni.dp.persist.datastat.common.model.IuniWmsSku;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.utils.ExcelUtil;
import com.iuni.dp.service.datastat.service.IuniSalesOfGoodsService;
import com.iuni.dp.service.datastat.service.common.IuniWmsSkuService;
import com.iuni.dp.service.datastat.service.common.impl.IuniWmsSkuServiceImpl;

/**
 * 商品销售报表controller
 *
 * @author dan.wang@iuni.com
 */
@Controller("iuniSalesOfGoodsAction")
@Scope("prototype")
public class IuniSalesOfGoodsAction extends BaseAction {

    private static final long serialVersionUID = -4626275603773126275L;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private Map<String, String> statParams = new HashMap<String, String>();

    private InputStream excelStream;

    private String fileName;

    private String flag;

    @Autowired
    private IuniSalesOfGoodsService iuniSalesOfGoodsService;
    @Autowired
    private IuniWmsSkuService iuniWmsSkuService;

    //订单来源
    @Autowired
    private OrderSourceService orderSourceService;
    private List<OrderSource> orderSourceList;
//    private static Map<String, String> orderSourceMap = OrderRefer.getOrderReferMapForIuniSalesOfGoodsAction();
    private static String[] orderSourceId;
    //商品类型
    private static Map<String, String> goodsTypeMap = SalesOfGoodType.getFGoodsTypeMap();
    private static String goodsType;
    //规格型号
    private static List<IuniWmsSku> skuNameList = new ArrayList<IuniWmsSku>();
    private static String skuNameCode;
    //展示方式
    private static Map<String, String> dateStyleMap = DateStyle.getDatestyleMap2();
    private static String dateStyle;

    private void initOrderSource() {
        if(orderSourceList == null || orderSourceList.size() == 0)
            orderSourceList = orderSourceService.getAllOmOrderSource();
    }

    public String iuniSalesOfGoods() {
        initOrderSource();
        List<Map<String, Object>> salesOfGoodsList = new ArrayList<Map<String, Object>>();
        try {
            //生成参数
            Map<String, Object> paramsMap = genParamMap();
            //数量
            Integer count = iuniSalesOfGoodsService.queryIuniSalesOfGoodsCount(paramsMap);
            page = Page.genPage(page.getCurrentPage(), count, page.getPageSize());
            paramsMap.put("startRec", page.getStartRec());
            paramsMap.put("endRec", page.getEndRec());
            //分页查询
            salesOfGoodsList = iuniSalesOfGoodsService.queryIuniSalesOfGoodsBypage(paramsMap);
        } catch (Exception e) {
            logger.error("IuniSalesOfGoodsAction.iuniSalesOfGoods() Exception", e);
            return ERROR;
        }
        request.setAttribute("salesOfGoodsList", salesOfGoodsList);
        return SUCCESS;
    }

    public String iuniSalesOfGoodsSelect() {
        initOrderSource();
        try {
            skuNameList = iuniWmsSkuService.queryIuniWmsSku(goodsType);
            String json = JSON.toJSONString(skuNameList);
            PrintWriter out;
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.println(json);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("IuniSalesOfGoodsAction.iuniSalesOfGoodsSelect exception.");
        }

        return SUCCESS;
    }

    public String iuniSalesOfGoods2Excel() {
        initOrderSource();
        Map<String, Object> paramsMap = genParamMap();
        List<Map<String, Object>> backGoodsList2ExcelList;
        try {
            backGoodsList2ExcelList = iuniSalesOfGoodsService.queryIuniSalesOfGoods2Excel(paramsMap);
            if (CollectionUtils.isNotEmpty(backGoodsList2ExcelList)) {
                String sheetName = "商品销售报表";
                fileName = sheetName + "_" + dateFormat.format(new Date());
                try {
                    fileName = new String(fileName.getBytes(), "ISO8859-1");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
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
        }catch (DBException dbex) {
            logger.error("IuniWmsStockMoveViewForWlAction.iuniWmsStockMoveViewForWl2Excel found DBException", dbex);
            return ERROR;
        } catch (Exception ex) {
            logger.error("IuniWmsStockMoveViewForWlAction.iuniWmsStockMoveViewForWl2Excel found Exception", ex);
            return ERROR;
        }
        return SUCCESS;
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
            //默认订单来源
            orderSourceId = new String[]{OrderRefer.IUNI.toString(), OrderRefer.TAOBAO.toString()};
            //默认商品类型
            goodsType = SalesOfGoodType.i1.toString();
            //生成默认规格型号列表
            skuNameList = iuniWmsSkuService.queryIuniWmsSku(goodsType);
            //默认规格型号
            skuNameCode = "0";
            //默认展示方式
            dateStyle = "YYYY-MM-DD";
        } else {
            if (!"0".equals(skuNameCode)) {
                map.put("skuId", skuNameCode);
            }
        }

        map.put("beginDate", statParams.get("beginDate"));
        map.put("endDate", statParams.get("endDate"));
        //流量来源
        map.put("orderSource", orderSourceId);
        IuniWmsSkuServiceImpl.paramsConvert(goodsType, map);

        map.put("dateStyle", dateStyle);

        return map;
    }

    private List<String> gencolumns() {
        List<String> columns = new ArrayList<String>();
        columns.add("RN");
        columns.add("paytime");
        columns.add("orderSource");
        columns.add("goodsName");
        columns.add("wareName");
        columns.add("sku");
        columns.add("num");
        columns.add("salePrice");
        return columns;
    }

    private List<String> gencolumnNames() {
        List<String> columnNames = new ArrayList<String>();
        columnNames.add("序号");
        columnNames.add("支付时间");
        columnNames.add("销售渠道");
        columnNames.add("规格型号");
        columnNames.add("商品");
        columnNames.add("SKU");
        columnNames.add("数量");
        columnNames.add("支付金额");
        return columnNames;
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

    public static List<IuniWmsSku> getSkuNameList() {
        return skuNameList;
    }

    public static void setSkuNameList(List<IuniWmsSku> skuNameList) {
        IuniSalesOfGoodsAction.skuNameList = skuNameList;
    }

//    public static Map<String, String> getOrderSourceMap() {
//        return orderSourceMap;
//    }

//    public static void setOrderSourceMap(Map<String, String> orderSourceMap) {
//        IuniSalesOfGoodsAction.orderSourceMap = orderSourceMap;
//    }

    public List<OrderSource> getOrderSourceList() {
        return orderSourceList;
    }

    public void setOrderSourceList(List<OrderSource> orderSourceList) {
        this.orderSourceList = orderSourceList;
    }

    public static String[] getOrderSourceId() {
        return orderSourceId;
    }

    public static void setOrderSourceId(String[] orderSourceId) {
        IuniSalesOfGoodsAction.orderSourceId = orderSourceId;
    }

    public static Map<String, String> getGoodsTypeMap() {
        return goodsTypeMap;
    }

    public static void setGoodsTypeMap(Map<String, String> goodsTypeMap) {
        IuniSalesOfGoodsAction.goodsTypeMap = goodsTypeMap;
    }

    public static String getGoodsType() {
        return goodsType;
    }

    public static void setGoodsType(String goodsType) {
        IuniSalesOfGoodsAction.goodsType = goodsType;
    }

    public static String getSkuNameCode() {
        return skuNameCode;
    }

    public static void setSkuNameCode(String skuNameCode) {
        IuniSalesOfGoodsAction.skuNameCode = skuNameCode;
    }

    public static Map<String, String> getDateStyleMap() {
        return dateStyleMap;
    }

    public static void setDateStyleMap(Map<String, String> dateStyleMap) {
        IuniSalesOfGoodsAction.dateStyleMap = dateStyleMap;
    }

    public static String getDateStyle() {
        return dateStyle;
    }

    public static void setDateStyle(String dateStyle) {
        IuniSalesOfGoodsAction.dateStyle = dateStyle;
    }


}
