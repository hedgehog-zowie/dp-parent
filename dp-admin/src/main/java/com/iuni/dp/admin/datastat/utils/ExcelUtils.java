/*
 * @(#)ExcelUtils.java 2013-7-17
 *
 * Copyright 2013 Shenzhen Gionee,Inc. All rights reserved.
 */
package com.iuni.dp.admin.datastat.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.iuni.dp.persist.datastat.model.IuniDayOrderConvertRate;
import com.iuni.dp.persist.datastat.model.IuniDayPvuvUserReg;
import com.iuni.dp.persist.datastat.model.IuniSaleDailyStat;
import com.iuni.dp.persist.datastat.model.MallAdGoodsOrderDetail;
import com.iuni.dp.persist.datastat.model.MallAdPvuvOrderDaily;
import com.iuni.dp.persist.datastat.model.MallAdPvuvOrderTotal;
import com.iuni.dp.persist.datastat.model.MallSaleDailyStat;
import com.iuni.dp.persist.datastat.model.MallUserOrderDailyStat;
import com.iuni.dp.persist.datastat.model.MallYqfOrderDailyStat;
import com.iuni.dp.persist.datastat.model.WmsOrderGoods;

/**
 * 
 * @author ZuoChangjun 2013-7-17
 * @version dp-admin-1.0.0
 */
public class ExcelUtils {

	private static String DATE_FORMAT = "m/d/yy h:mm";
	private static String NUMBER_FORMAT = "#,##0.00";
	private String xlsFileName;
	private HttpServletResponse response;
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private HSSFRow row;
	private HSSFCellStyle cellStyle;
	private String[] goodsStatusArray = { "未确认", "已确认", "已取消", "无效", "退货","已分单", "部分分单" };
	private static Map<Integer, String> goodsStatusMap = new HashMap<Integer, String>();
	static {
		goodsStatusMap.put(0, "未确认");
		goodsStatusMap.put(1, "已确认");
		goodsStatusMap.put(2, "已取消");
		goodsStatusMap.put(3, "无效");
		goodsStatusMap.put(4, "退货");
		goodsStatusMap.put(5, "已分单");
		goodsStatusMap.put(6, "部分分单");
	}

	public ExcelUtils(HttpServletResponse response, String fileName,
			long columnNum) {
		this.xlsFileName = fileName;
		this.response = response;
		this.workbook = new HSSFWorkbook();
		this.sheet = this.workbook.createSheet();
		this.cellStyle = this.workbook.createCellStyle();
		for (int i = 0; i < columnNum; i++) {
			this.sheet.setColumnWidth(i, 5000);
		}
	}

	public ExcelUtils(String fileName, long columnNum) {
		this.xlsFileName = fileName;
		this.workbook = new HSSFWorkbook();
		this.sheet = this.workbook.createSheet();
		this.cellStyle = this.workbook.createCellStyle();
		for (int i = 0; i < columnNum; i++) {
			this.sheet.setColumnWidth(i, 5000);
		}
	}

	public void createRow(int index) {
		this.row = this.sheet.createRow(index);
	}

	/**
	 * 设置字符串值
	 * 
	 * @param index
	 * @param value
	 */
	public void setCell(int index, String value) {
		HSSFCell cell = this.row.createCell(index);
		cell.setCellType(1);
		cell.setCellValue(value);
	}

	/**
	 * 设置链接地址
	 * 
	 * @param index
	 * @param value
	 * @param filename
	 */
	public void setCell(int index, String value, String filename) {
		HSSFCell cell = this.row.createCell(index);
		cell.setCellType(1);
		cell.setCellValue(value);
		HSSFHyperlink link = new HSSFHyperlink(4);
		link.setAddress(filename);
		cell.setHyperlink(link);
	}

	/**
	 * 设置日期值
	 * 
	 * @param index
	 * @param value
	 */
	public void setCell(int index, Calendar value) {
		HSSFCell cell = this.row.createCell(index);
		cell.setCellValue(value.getTime());
		//HSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(DATE_FORMAT));
		cell.setCellStyle(cellStyle);
	}

	/**
	 * 设置日期值
	 * 
	 * @param index
	 * @param value
	 */
	public void setCell(int index, Date value) {
		HSSFCell cell = this.row.createCell(index);
		if(value != null){
			cell.setCellValue(value);
			//HSSFCellStyle cellStyle = this.workbook.createCellStyle();

			HSSFDataFormat format = this.workbook.createDataFormat();
			cellStyle.setDataFormat(format.getFormat("yyyy-mm-dd HH:mm:ss"));
			cell.setCellStyle(cellStyle);
		}else{
			cell.setCellType(1);
			cell.setCellValue("");
		}

	}

	public void setCell(int index, Integer value) {
		HSSFCell cell = this.row.createCell(index);

		cell.setCellValue(value);
	}

	/**
	 * 设置Double值
	 * 
	 * @param index
	 * @param value
	 */
	public void setCell(int index, Double value) {
		HSSFCell cell = this.row.createCell(index);
		cell.setCellType(0);
		cell.setCellValue(value);
		//HSSFCellStyle cellStyle = this.workbook.createCellStyle();
		HSSFDataFormat format = this.workbook.createDataFormat();
		cellStyle.setDataFormat(format.getFormat(NUMBER_FORMAT));
		cell.setCellStyle(cellStyle);
	}

	public static void main(String[] args) {
		System.out.println(" 开始导出Excel文件 ");
		ExcelUtils e = new ExcelUtils("d:/test.xls", 4);

		e.createRow(0);
		e.row.setHeight((short) 500);
		e.setCell(0, " 编号 ");
		e.setCell(1, " 名称 ");
		e.setCell(2, " 日期 ");
		e.setCell(3, " 金额 ");

		e.createRow(1);
		e.setCell(0, 1);
		e.setCell(1, " 工商银fgfgf放东方大飞地方行 ");
		e.setCell(2, Calendar.getInstance());
		e.setCell(3, 111123.99000000001D);

		e.createRow(2);
		e.setCell(0, 2);
		e.setCell(1, " 招商银行 ");
		e.setCell(2, new Date());
		e.setCell(3, 222456.88D);

		try {
			System.out.println(" 导出Excel文件[成功] ");
		} catch (Exception e1) {
			System.out.println(" 导出Excel文件[失败] ");
			e1.printStackTrace();
		}
	}
	/**
	 * 站外推广(总表)导出
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void mallAdPvuvOrderTotal2Excel(List<MallAdPvuvOrderTotal> list)
			throws Exception {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		try {
			// 创建表头
			createRow(0);
			this.row.setHeight((short) 500);

			setCell(0, "广告名称");
			setCell(1, "点击来源");
			setCell(2, "PV");
			setCell(3, "UV");
			setCell(4, "有效订单数");
			setCell(5, "总订单数");
			setCell(6, "退单数");

			// 创建表数据
			for (int i = 0; i < list.size(); i++) {
				MallAdPvuvOrderTotal mallAdPvuvOrderTotal = (MallAdPvuvOrderTotal) list
						.get(i);
				if (mallAdPvuvOrderTotal == null) {
					continue;
				}
				createRow(i + 1);
				setCell(0, mallAdPvuvOrderTotal.getAdName());
				setCell(1, mallAdPvuvOrderTotal.getChannelName());
				setCell(2, mallAdPvuvOrderTotal.getTotalPv());
				setCell(3, mallAdPvuvOrderTotal.getTotalUv());
				setCell(4, mallAdPvuvOrderTotal.getValidOrderNum());
				setCell(5, mallAdPvuvOrderTotal.getTotalOrderNum());
				setCell(6, mallAdPvuvOrderTotal.getBackOrderNum());
			}

			this.response.reset();
			this.response.resetBuffer();
			this.response.setContentType("application/vnd.ms-excel");

			this.response
					.setHeader("Content-Disposition", "attachment;filename=\""
							+ this.xlsFileName + ".xls" + "\"");
			OutputStream outPutStrm = this.response.getOutputStream();

			this.workbook.write(outPutStrm);
			outPutStrm.flush();
			outPutStrm.close();
		} catch (FileNotFoundException e) {
			throw new Exception(" 生成导出Excel文件出错! ", e);
		} catch (IOException e) {
			throw new Exception(" 写入Excel文件出错! ", e);
		}
	}

	/**
	 * 站外推广(日表)导出
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void mallAdPvuvOrderDaily2Excel(List<MallAdPvuvOrderDaily> list)
			throws Exception {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		try {
			// 创建表头
			createRow(0);
			this.row.setHeight((short) 500);

			setCell(0, "订单日期");
			setCell(1, "广告名称");
			setCell(2, "点击来源");
			setCell(3, "PV");
			setCell(4, "UV");
			setCell(5, "有效订单数");
			setCell(6, "总订单数");
			setCell(7, "退单数");

			// 创建表数据
			for (int i = 0; i < list.size(); i++) {
				MallAdPvuvOrderDaily mallAdPvuvOrderDaily = (MallAdPvuvOrderDaily) list
						.get(i);
				if (mallAdPvuvOrderDaily == null) {
					continue;
				}
				createRow(i + 1);
				setCell(0, mallAdPvuvOrderDaily.getBizDate());
				setCell(1, mallAdPvuvOrderDaily.getAdName());
				setCell(2, mallAdPvuvOrderDaily.getChannelName());
				setCell(3, mallAdPvuvOrderDaily.getChannelPv());
				setCell(4, mallAdPvuvOrderDaily.getChannelUv());
				setCell(5, mallAdPvuvOrderDaily.getValidOrderNum());
				setCell(6, mallAdPvuvOrderDaily.getTotalOrderNum());
				setCell(7, mallAdPvuvOrderDaily.getBackOrderNum());
			}

			this.response.reset();
			this.response.resetBuffer();
			this.response.setContentType("application/vnd.ms-excel");

			this.response
					.setHeader("Content-Disposition", "attachment;filename=\""
							+ this.xlsFileName + ".xls" + "\"");
			OutputStream outPutStrm = this.response.getOutputStream();

			this.workbook.write(outPutStrm);
			outPutStrm.flush();
			outPutStrm.close();
		} catch (FileNotFoundException e) {
			throw new Exception(" 生成导出Excel文件出错! ", e);
		} catch (IOException e) {
			throw new Exception(" 写入Excel文件出错! ", e);
		}
	}

	/**
	 * 站外推广(推广数据表)导出
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void mallAdPvuvOrderDailysForFinal2Excel(
			List<MallAdPvuvOrderDaily> list) throws Exception {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		try {
			// 创建表头
			createRow(0);
			this.row.setHeight((short) 500);
			setCell(0, "出表日期");
			setCell(1, "订单日期");
			setCell(2, "广告名称");
			setCell(3, "点击来源");
			setCell(4, "PV");
			setCell(5, "UV");
			setCell(6, "有效订单数");
			setCell(7, "总订单数");
			setCell(8, "退单数");

			// 创建表数据
			for (int i = 0; i < list.size(); i++) {
				MallAdPvuvOrderDaily mallAdPvuvOrderDaily = (MallAdPvuvOrderDaily) list
						.get(i);
				if (mallAdPvuvOrderDaily == null) {
					continue;
				}
				createRow(i + 1);
				setCell(0, mallAdPvuvOrderDaily.getOutDate());
				setCell(1, mallAdPvuvOrderDaily.getBizDate());
				setCell(2, mallAdPvuvOrderDaily.getAdName());
				setCell(3, mallAdPvuvOrderDaily.getChannelName());
				setCell(4, mallAdPvuvOrderDaily.getChannelPv());
				setCell(5, mallAdPvuvOrderDaily.getChannelUv());
				setCell(6, mallAdPvuvOrderDaily.getValidOrderNum());
				setCell(7, mallAdPvuvOrderDaily.getTotalOrderNum());
				setCell(8, mallAdPvuvOrderDaily.getBackOrderNum());
			}

			this.response.reset();
			this.response.resetBuffer();
			this.response.setContentType("application/vnd.ms-excel");

			this.response
					.setHeader("Content-Disposition", "attachment;filename=\""
							+ this.xlsFileName + ".xls" + "\"");
			OutputStream outPutStrm = this.response.getOutputStream();

			this.workbook.write(outPutStrm);
			outPutStrm.flush();
			outPutStrm.close();
		} catch (FileNotFoundException e) {
			throw new Exception(" 生成导出Excel文件出错! ", e);
		} catch (IOException e) {
			throw new Exception(" 写入Excel文件出错! ", e);
		}
	}

	/**
	 * 站外推广(总表日表/推广数据表之明细表)导出
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void mallAdPvuvOrderDetails2Excel(List<MallAdGoodsOrderDetail> list)
			throws Exception {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		try {
			// 创建表头
			createRow(0);
			this.row.setHeight((short) 500);
			setCell(0, "订单日期");
			setCell(1, "商品名称");
			setCell(2, "商品编号");
			setCell(3, "商品数量");
			setCell(4, "商品单价");
			setCell(5, "商品金额");
			setCell(6, "订单编号");
			setCell(7, "订单状态");
			setCell(8, "结算时间");

			// 创建表数据
			for (int i = 0; i < list.size(); i++) {
				MallAdGoodsOrderDetail mallAdGoodsOrderDetail = (MallAdGoodsOrderDetail) list
						.get(i);
				if (mallAdGoodsOrderDetail == null) {
					continue;
				}
				createRow(i + 1);
				setCell(0, mallAdGoodsOrderDetail.getBizDate());
				setCell(1, mallAdGoodsOrderDetail.getGoodsName());
				setCell(2, mallAdGoodsOrderDetail.getGoodsSn());
				setCell(3, mallAdGoodsOrderDetail.getGoodsNum());
				setCell(4, mallAdGoodsOrderDetail.getGoodsPrice());
				setCell(5, mallAdGoodsOrderDetail.getGoodsPrice()
						* mallAdGoodsOrderDetail.getGoodsNum());
				setCell(6, mallAdGoodsOrderDetail.getOredrSn());
				// setCell(7,
				// goodsStatusArray[mallAdGoodsOrderDetail.getOrderStatus()]);
				setCell(7, goodsStatusMap.get(mallAdGoodsOrderDetail
						.getOrderStatus()));
				setCell(8, mallAdGoodsOrderDetail.getPayTime());
			}

			this.response.reset();
			this.response.resetBuffer();
			this.response.setContentType("application/vnd.ms-excel");

			this.response
					.setHeader("Content-Disposition", "attachment;filename=\""
							+ this.xlsFileName + ".xls" + "\"");
			OutputStream outPutStrm = this.response.getOutputStream();

			this.workbook.write(outPutStrm);
			outPutStrm.flush();
			outPutStrm.close();
		} catch (FileNotFoundException e) {
			throw new Exception(" 生成导出Excel文件出错! ", e);
		} catch (IOException e) {
			throw new Exception(" 写入Excel文件出错! ", e);
		}
	}

	/**
	 * 导出Excel微信公众帐号图片下载排行
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void wxImageDownloadRanks2Excel(List<Map<String, Object>> list)
			throws Exception {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		try {
			// 创建表头
			createRow(0);
			this.row.setHeight((short) 500);

			setCell(0, "名称");
			setCell(1, "图片");
			setCell(2, "标签");
			setCell(3, "下发量");
			setCell(4, "点击量");
			setCell(5, "点击率");

			// 创建表数据
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				if (map == null) {
					continue;
				}
				createRow(i + 1);
				setCell(0, map.get("imgName") + "");
				setCell(1, map.get("imgUrl") + "");
				setCell(2, map.get("tagName") + "");
				setCell(3, map.get("showCount") + "");
				setCell(4, map.get("clickCount") + "");
				setCell(5, map.get("clickRate") + "");
/*				if (map.get("showCount") == null
						|| ((BigDecimal) map.get("showCount")).intValue() == 0) {
					setCell(5, 0);
				} else {
					setCell(5,((BigDecimal) map.get("clickCount")).divide((BigDecimal)map.get("showCount")) + "");
				}*/
			}

			this.response.reset();
			this.response.resetBuffer();
			this.response.setContentType("application/vnd.ms-excel");

			this.response
					.setHeader("Content-Disposition", "attachment;filename=\""
							+ this.xlsFileName + ".xls" + "\"");
			OutputStream outPutStrm = this.response.getOutputStream();

			this.workbook.write(outPutStrm);
			outPutStrm.flush();
			outPutStrm.close();
		} catch (FileNotFoundException e) {
			throw new Exception(" 生成导出Excel文件出错! ", e);
		} catch (IOException e) {
			throw new Exception(" 写入Excel文件出错! ", e);
		}
	}

	
	/**
	 * 导出Excel商城销售数据
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void mallSaleDailyStats2Excel(List<MallSaleDailyStat> list)
			throws Exception {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		try {
			// 创建表头
			createRow(0);
			this.row.setHeight((short) 500);
			setCell(0, "日期");
			setCell(1, "订单总数");
			setCell(2, "订单总金额");
			setCell(3, "订单商品总件数");
			setCell(4, "在线支付订单数");
			setCell(5, "货到付款订单数");
			setCell(6, "退货订单数");
			setCell(7, "拒收订单数");
			setCell(8, "有效订单数");
			setCell(9, "有效订单金额");
			setCell(10, "有效订单商品总件数");
			setCell(11, "已支付订单数");
			setCell(12, "预约且已支付订单数");
			setCell(13, "有效订单比率");
			setCell(14, "客单价");
			setCell(15, "客件数");

			// 创建表数据
			for (int i = 0; i < list.size(); i++) {
				MallSaleDailyStat mallSaleDailyStat = (MallSaleDailyStat) list
						.get(i);
				if (mallSaleDailyStat == null) {
					continue;
				}
				createRow(i + 1);
				setCell(0, mallSaleDailyStat.getBizDate());
				setCell(1, mallSaleDailyStat.getTotalOrderNum());
				setCell(2, mallSaleDailyStat.getTotalOrderAmount());
				setCell(3, mallSaleDailyStat.getTotalGoodsNum());
				setCell(4, mallSaleDailyStat.getOnlinePayOrderNum());
				setCell(5, mallSaleDailyStat.getOfflinePayOrderNum());
				setCell(6, mallSaleDailyStat.getReturnedOrderNum());
				setCell(7, mallSaleDailyStat.getRefusedOrderNum());
				setCell(8, mallSaleDailyStat.getValidOrderNum());
				setCell(9, mallSaleDailyStat.getValidOrderAmount());
				setCell(10, mallSaleDailyStat.getValidGoodsNum());
				setCell(11, mallSaleDailyStat.getPayedOrderNum());
				setCell(12, mallSaleDailyStat.getPrePayedOrderNum());
				setCell(13, mallSaleDailyStat.getValidOrderRate());
				setCell(14, mallSaleDailyStat.getAmountPerOrder());
				setCell(15, mallSaleDailyStat.getGoodsNumPerOrder());
			}

			this.response.reset();
			this.response.resetBuffer();
			this.response.setContentType("application/vnd.ms-excel");

			this.response
					.setHeader("Content-Disposition", "attachment;filename=\""
							+ this.xlsFileName + ".xls" + "\"");
			OutputStream outPutStrm = this.response.getOutputStream();

			this.workbook.write(outPutStrm);
			outPutStrm.flush();
			outPutStrm.close();
		} catch (FileNotFoundException e) {
			throw new Exception(" 生成导出Excel文件出错! ", e);
		} catch (IOException e) {
			throw new Exception(" 写入Excel文件出错! ", e);
		}
	}
	
	/**
	 * 导出Excel商城注册会员
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void mallUserOrderDailyStats2Excel(List<MallUserOrderDailyStat>  list)
			throws Exception {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		try {
			// 创建表头
			createRow(0);
			this.row.setHeight((short) 500);
			setCell(0, "注册日期");
			setCell(1, "注册用户数");
			setCell(2, "下单总数");
			setCell(3, "下单总金额");
			setCell(4, "会员有效订单总数");
			setCell(5, "会员有效订单总金额");
			setCell(6, "会员平均有效订单数");
			setCell(7, "会员平均有效订单金额");

			// 创建表数据
			for (int i = 0; i < list.size(); i++) {
				MallUserOrderDailyStat mallUserOrderDailyStat = (MallUserOrderDailyStat) list
						.get(i);
				if (mallUserOrderDailyStat == null) {
					continue;
				}
				createRow(i + 1);
				setCell(0, mallUserOrderDailyStat.getBizDate());
				setCell(1, mallUserOrderDailyStat.getRegUserNum());
				setCell(2, mallUserOrderDailyStat.getTotalOrderNum());
				setCell(3, mallUserOrderDailyStat.getTotalOrderAmount());
				setCell(4, mallUserOrderDailyStat.getValidOrderNum());
				setCell(5, mallUserOrderDailyStat.getValidOrderAmount());
				setCell(6, mallUserOrderDailyStat.getOrderNumPerUser());
				setCell(7, mallUserOrderDailyStat.getAmountPerUser());
			}

			this.response.reset();
			this.response.resetBuffer();
			this.response.setContentType("application/vnd.ms-excel");

			this.response
					.setHeader("Content-Disposition", "attachment;filename=\""
							+ this.xlsFileName + ".xls" + "\"");
			OutputStream outPutStrm = this.response.getOutputStream();

			this.workbook.write(outPutStrm);
			outPutStrm.flush();
			outPutStrm.close();
		} catch (FileNotFoundException e) {
			throw new Exception(" 生成导出Excel文件出错! ", e);
		} catch (IOException e) {
			throw new Exception(" 写入Excel文件出错! ", e);
		}
	}
	
	/**
	 * 导出Excel商城CPS推广
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void mallYqfOrderDailyStats2Excel(List<MallYqfOrderDailyStat>  list)
			throws Exception {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		try {
			// 创建表头
			createRow(0);
			this.row.setHeight((short) 500);
			setCell(0, "统计日期");
			setCell(1, "广告ID");
			setCell(2, "数据来源");
			setCell(3, "推广渠道");
			setCell(4, "订单总数");
			setCell(5, "有效订单数");
			setCell(6, "拒收订单数");
			setCell(7, "退单数");

			// 创建表数据
			for (int i = 0; i < list.size(); i++) {
				MallYqfOrderDailyStat mallYqfOrderDailyStat = (MallYqfOrderDailyStat) list
						.get(i);
				if (mallYqfOrderDailyStat == null) {
					continue;
				}
				createRow(i + 1);
				setCell(0, mallYqfOrderDailyStat.getBizDate());
				if (i == list.size() - 1 || mallYqfOrderDailyStat.getCid() == null) {
					setCell(1, "");
				}
				else{
					setCell(1, mallYqfOrderDailyStat.getCid()+"");
				}
				setCell(2, mallYqfOrderDailyStat.getSource());
				setCell(3, mallYqfOrderDailyStat.getChannel());
				setCell(4, mallYqfOrderDailyStat.getTotalOrderNum());
				setCell(5, mallYqfOrderDailyStat.getValidOrderNum());
				setCell(6, mallYqfOrderDailyStat.getRefusedOrderNum());
				setCell(7, mallYqfOrderDailyStat.getReturnedOrderNum());
			}

			this.response.reset();
			this.response.resetBuffer();
			this.response.setContentType("application/vnd.ms-excel");

			this.response
					.setHeader("Content-Disposition", "attachment;filename=\""
							+ this.xlsFileName + ".xls" + "\"");
			OutputStream outPutStrm = this.response.getOutputStream();

			this.workbook.write(outPutStrm);
			outPutStrm.flush();
			outPutStrm.close();
		} catch (FileNotFoundException e) {
			throw new Exception(" 生成导出Excel文件出错! ", e);
		} catch (IOException e) {
			throw new Exception(" 写入Excel文件出错! ", e);
		}
	}

	
	/**
	 * 导出Excel IUNI销售数据
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void iuniSaleDailyStats2Excel(List<IuniSaleDailyStat> list)
			throws Exception {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		try {
			// 创建表头
			createRow(0);
			this.row.setHeight((short) 500);
			setCell(0, "日期");
			setCell(1, "订单总数");
			setCell(2, "订单总金额");
			setCell(3, "订单商品总件数");
			setCell(4, "在线支付订单数");
			setCell(5, "货到付款订单数");
			setCell(6, "退货订单数");
			setCell(7, "拒收订单数");
			setCell(8, "有效订单数");
			setCell(9, "有效订单金额");
			setCell(10, "有效订单商品总件数");
			setCell(11, "已支付订单数");
			setCell(12, "预约且已支付订单数");
			setCell(13, "有效订单比率");
			setCell(14, "客单价");
			setCell(15, "客件数");

			// 创建表数据
			for (int i = 0; i < list.size(); i++) {
				IuniSaleDailyStat iuniSaleDailyStat = (IuniSaleDailyStat) list
						.get(i);
				if (iuniSaleDailyStat == null) {
					continue;
				}
				createRow(i + 1);
				setCell(0, iuniSaleDailyStat.getBizDate());
				setCell(1, iuniSaleDailyStat.getTotalOrderNum());
				setCell(2, iuniSaleDailyStat.getTotalOrderAmount());
				setCell(3, iuniSaleDailyStat.getTotalGoodsNum());
				setCell(4, iuniSaleDailyStat.getOnlinePayOrderNum());
				setCell(5, iuniSaleDailyStat.getOfflinePayOrderNum());
				setCell(6, iuniSaleDailyStat.getReturnedOrderNum());
				setCell(7, iuniSaleDailyStat.getRefusedOrderNum());
				setCell(8, iuniSaleDailyStat.getValidOrderNum());
				setCell(9, iuniSaleDailyStat.getValidOrderAmount());
				setCell(10, iuniSaleDailyStat.getValidGoodsNum());
				setCell(11, iuniSaleDailyStat.getPayedOrderNum());
				setCell(12, iuniSaleDailyStat.getPrePayedOrderNum());
				setCell(13, iuniSaleDailyStat.getValidOrderRate());
				setCell(14, iuniSaleDailyStat.getAmountPerOrder());
				setCell(15, iuniSaleDailyStat.getGoodsNumPerOrder());
			}

			this.response.reset();
			this.response.resetBuffer();
			this.response.setContentType("application/vnd.ms-excel");

			this.response
					.setHeader("Content-Disposition", "attachment;filename=\""
							+ this.xlsFileName + ".xls" + "\"");
			OutputStream outPutStrm = this.response.getOutputStream();

			this.workbook.write(outPutStrm);
			outPutStrm.flush();
			outPutStrm.close();
		} catch (FileNotFoundException e) {
			throw new Exception(" 生成导出Excel文件出错! ", e);
		} catch (IOException e) {
			throw new Exception(" 写入Excel文件出错! ", e);
		}
	}

	/**
	 * 导出Excel IUNI订单转化率
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void iuniDayOrderConvertRates2Excel(List<IuniDayOrderConvertRate> list)
			throws Exception {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		try {
			// 创建表头
			createRow(0);
			this.row.setHeight((short) 500);
			setCell(0, "统计日期");
			setCell(1, "商城PV");
			setCell(2, "商城UV");
			setCell(3, "订单总数");
			setCell(4, "下单转化率");
			setCell(5, "已支付订单数");
			setCell(6, "已支付订单比例");
			setCell(7, "在线支付订单数");
			setCell(8, "在线支付订单比例");

			// 创建表数据
			for (int i = 0; i < list.size(); i++) {
				IuniDayOrderConvertRate iuniDayOrderConvertRate = (IuniDayOrderConvertRate) list
						.get(i);
				if (iuniDayOrderConvertRate == null) {
					continue;
				}
				createRow(i + 1);
				setCell(0, iuniDayOrderConvertRate.getBizDate());
				setCell(1, iuniDayOrderConvertRate.getPv());
				setCell(2, iuniDayOrderConvertRate.getUv());
				setCell(3, iuniDayOrderConvertRate.getTotalOrderNum());
				setCell(4, iuniDayOrderConvertRate.getOrderConvertRate());
				setCell(5, iuniDayOrderConvertRate.getPayedOrderNum());
				setCell(6, iuniDayOrderConvertRate.getPayedOrderRate());
				setCell(7, iuniDayOrderConvertRate.getOnlinePayOrderNum());
				setCell(8, iuniDayOrderConvertRate.getOnlinePayOrderRate());
			}

			this.response.reset();
			this.response.resetBuffer();
			this.response.setContentType("application/vnd.ms-excel");

			this.response
					.setHeader("Content-Disposition", "attachment;filename=\""
							+ this.xlsFileName + ".xls" + "\"");
			OutputStream outPutStrm = this.response.getOutputStream();

			this.workbook.write(outPutStrm);
			outPutStrm.flush();
			outPutStrm.close();
		} catch (FileNotFoundException e) {
			throw new Exception(" 生成导出Excel文件出错! ", e);
		} catch (IOException e) {
			throw new Exception(" 写入Excel文件出错! ", e);
		}
	}
	
	/**
	 * 导出Excel IUNI用户注册
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void iuniUserRegDailyStats2Excel(List<IuniDayPvuvUserReg> list)
			throws Exception {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		try {
			// 创建表头
			createRow(0);
			this.row.setHeight((short) 500);
			setCell(0, "统计日期");
			setCell(1, "注册页PV");
			setCell(2, "注册页UV");
			setCell(3, "商城注册会员数");
			setCell(4, "注册成功率");
			setCell(5, "新浪微博联合登录");
			setCell(6, "QQ联合登录");
			setCell(7, "支付宝联合登录");
			setCell(8, "豆瓣联合登录");
			setCell(9, "访问活跃用户数");
			setCell(10, "购买活跃用户数");

			// 创建表数据
			for (int i = 0; i < list.size(); i++) {
				IuniDayPvuvUserReg iuniDayPvuvUserReg = (IuniDayPvuvUserReg) list
						.get(i);
				if (iuniDayPvuvUserReg == null) {
					continue;
				}
				createRow(i + 1);
				setCell(0, iuniDayPvuvUserReg.getBizDate());
				setCell(1, iuniDayPvuvUserReg.getPv() == null?0:iuniDayPvuvUserReg.getPv());
				setCell(2, iuniDayPvuvUserReg.getUv() == null?0:iuniDayPvuvUserReg.getUv());
				setCell(3, iuniDayPvuvUserReg.getGioneeRegNum() == null?0:iuniDayPvuvUserReg.getGioneeRegNum());
				setCell(4, iuniDayPvuvUserReg.getRegSuccRate());
				setCell(5, iuniDayPvuvUserReg.getSinaRegNum() == null?0:iuniDayPvuvUserReg.getSinaRegNum());
				setCell(6, iuniDayPvuvUserReg.getQqRegNum() == null?0:iuniDayPvuvUserReg.getQqRegNum());
				setCell(7, iuniDayPvuvUserReg.getZfbRegNum() == null?0:iuniDayPvuvUserReg.getZfbRegNum());
				setCell(8, iuniDayPvuvUserReg.getDoubanRegNum() == null?0:iuniDayPvuvUserReg.getDoubanRegNum());
				setCell(9, iuniDayPvuvUserReg.getLoginUserNum() == null?0:iuniDayPvuvUserReg.getLoginUserNum());
				setCell(10, iuniDayPvuvUserReg.getBuyUserNum() == null?0:iuniDayPvuvUserReg.getBuyUserNum());
			}

			this.response.reset();
			this.response.resetBuffer();
			this.response.setContentType("application/vnd.ms-excel");

			this.response
					.setHeader("Content-Disposition", "attachment;filename=\""
							+ this.xlsFileName + ".xls" + "\"");
			OutputStream outPutStrm = this.response.getOutputStream();

			this.workbook.write(outPutStrm);
			outPutStrm.flush();
			outPutStrm.close();
		} catch (FileNotFoundException e) {
			throw new Exception(" 生成导出Excel文件出错! ", e);
		} catch (IOException e) {
			throw new Exception(" 写入Excel文件出错! ", e);
		}
	}
	
	/**
	 * 导出WMS销售数据
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void wmsOrderGoodsList2Excel(List<WmsOrderGoods> list)
			throws Exception {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		try {
			// 创建表头
			createRow(0);
			this.row.setHeight((short) 500);
			setCell(0, "下单时间");
			setCell(1, "订单来源");
			setCell(2, "订单号");
			setCell(3, "支付类型");
			setCell(4, "交易号");
			setCell(5, "付款人");
			setCell(6, "SKU名称");
			setCell(7, "数量");
			setCell(8, "商品单价");
			setCell(9, "商品总价");
			setCell(10, "发票金额");
			setCell(11, "发票抬头");
			setCell(12, "快递类型");
			setCell(13, "运单号");
			setCell(14, "拣货时间");
			setCell(15, "拣货批次号");
			setCell(16, "发货状态");
			setCell(17, "收货人");
			setCell(18, "收货地址");
			setCell(19, "订单附言");
			setCell(20, "客服备注");

			// 创建表数据
			for (int i = 0; i < list.size(); i++) {
				WmsOrderGoods wmsOrderGoods = (WmsOrderGoods) list
						.get(i);
				if (wmsOrderGoods == null) {
					continue;
				}
				createRow(i + 1);
				setCell(0, wmsOrderGoods.getOrderTime());
				setCell(1, wmsOrderGoods.getOrderSource());
				setCell(2, wmsOrderGoods.getOrderCode());
				setCell(3, wmsOrderGoods.getPaymentName());
				setCell(4, wmsOrderGoods.getPayNo());
				setCell(5, wmsOrderGoods.getOrderUser());
				setCell(6, wmsOrderGoods.getSkuName());
				setCell(7, wmsOrderGoods.getQuantity());
				setCell(8, wmsOrderGoods.getUnitPrice()+"");
				setCell(9, wmsOrderGoods.getSubtotalPrice()+"");
				setCell(10, wmsOrderGoods.getInvoiceAmount()+"");
				setCell(11, wmsOrderGoods.getInvoiceTitle());
				setCell(12, wmsOrderGoods.getShippingName());
				setCell(13, wmsOrderGoods.getShippingNo());
				setCell(14, wmsOrderGoods.getJhTime());
				setCell(15, wmsOrderGoods.getBatchCode());
				setCell(16, wmsOrderGoods.getOrderStatus());
				setCell(17, wmsOrderGoods.getConsignee());
				setCell(18, wmsOrderGoods.getAddress());
				setCell(19, wmsOrderGoods.getPostscript());
				setCell(20, wmsOrderGoods.getRemark());
			}

			this.response.reset();
			this.response.resetBuffer();
			this.response.setContentType("application/vnd.ms-excel");

			this.response
					.setHeader("Content-Disposition", "attachment;filename=\""
							+ this.xlsFileName + ".xls" + "\"");
			OutputStream outPutStrm = this.response.getOutputStream();

			this.workbook.write(outPutStrm);
			outPutStrm.flush();
			outPutStrm.close();
		} catch (FileNotFoundException e) {
			throw new Exception(" 生成导出Excel文件出错! ", e);
		} catch (IOException e) {
			throw new Exception(" 写入Excel文件出错! ", e);
		}
	}
}
