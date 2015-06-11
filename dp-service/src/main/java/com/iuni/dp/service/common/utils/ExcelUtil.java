package com.iuni.dp.service.common.utils;

import java.awt.Color;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel数据导入导出工具类
 * @author CaiKe
 * @version dp-service-1.0.0
 * @since dp-service-1.0.0
 */
public class ExcelUtil {
	
	// 定制日期格式
    private static final String DATE_FORMAT_SHORT = "yyyy-MM-dd";
    private static final String DATE_FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

	// 定制时间戳格式
	private static final String TIMESTAMP_FORMAT_SHORT = "yyyy-MM-dd";
    private static final String TIMESTAMP_FORMAT_LONG = "yyyy-MM-dd HH:mm:ss.0";
	
	// 定制浮点数格式  
//	private static final String NUMBER_FORMAT_1 = "#,##0"; 
	
	private static final Color CORNFLOWER_BLUE_COLOR = new Color(153, 153, 255);
	
	private static final Color GREY_40_PERCENT_COLOR = new Color(150, 150, 150);
	
	/**
	 * 单Sheet输出Excel
	 * @param columnNames
	 * @param columns
	 * @param dataList
	 * @param sheetName
	 * @return HSSFWorkbook
	 */
	public static XSSFWorkbook getWorkbook4Xssf(List<String> columnNames,
			List<String> columns, List<Map<String, Object>> dataList,
			String sheetName) {

		// 创建Excel文件
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Generate Common Cell Styles for Excel Template
		Map<String, XSSFCellStyle> commonCellStyles = genCommonCellStyle4Xssf(workbook);
		
		// 创建Sheet
		XSSFSheet sheet = workbook.createSheet(sheetName);

		Integer rowIndex = -1;

		// 创建表头
		XSSFRow col_row = sheet.createRow(++rowIndex);
		col_row.setHeightInPoints(26);
		for (int i = 0; i < columnNames.size(); i++) {
			XSSFCell cell = col_row.createCell(i);
			cell.setCellValue(columnNames.get(i));
			cell.setCellStyle(commonCellStyles.get("headCellStyle"));
		}

		// 创建数据
		for (Map<String, Object> data : dataList) {
			XSSFRow data_row = sheet.createRow(++rowIndex);
			data_row.setHeightInPoints(18);
			for(int i = 0; i < columns.size(); i++) {
				XSSFCell data_cell = data_row.createCell(i);
				// Fill Cell Data and set Cell Style
				setCellData4Xssf(data_cell, data.get(columns.get(i)), commonCellStyles);
			}
		}
		
		// 列宽自适应
//		int columnSize = sheet.getRow(0).getPhysicalNumberOfCells();
		for(int i = 0;i < columnNames.size(); i++) {
//			sheet.setColumnWidth(i, columnNames.get(i).getBytes().length*2*256);
			sheet.autoSizeColumn(i, true);
            int width = sheet.getColumnWidth(i) + 1024;
            int maxWidth = 10000;
            if(width > maxWidth)
                width = maxWidth;
            sheet.setColumnWidth(i, width);
		}

		return workbook;
	}

	/**
	 * 按Sheet输出Excel
	 * @param columnNames
	 * @param columns
	 * @param sheetDataList
	 * @return HSSFWorkbook
	 */
	public static XSSFWorkbook getWorkbook4XssfOnSheet(List<String> columnNames,
			List<String> columns, Map<String, List<Map<String, Object>>> sheetDataList) {

		// 创建Excel文件
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		// Generate Common Cell Styles for Excel Template
		Map<String, XSSFCellStyle> commonCellStyles = genCommonCellStyle4Xssf(workbook);
		
		for(Entry<String, List<Map<String, Object>>> entry : sheetDataList.entrySet()) {
			String sheetName = entry.getKey();
			List<Map<String, Object>> dataList = entry.getValue();
			
			// 创建Sheet
			XSSFSheet sheet = workbook.createSheet(sheetName);
			
			Integer rowIndex = -1;
			
			// 创建表头
			XSSFRow col_row = sheet.createRow(++rowIndex);
			col_row.setHeightInPoints(26);
			for (int i = 0; i < columnNames.size(); i++) {
				XSSFCell cell = col_row.createCell(i);
				cell.setCellValue(columnNames.get(i));
				cell.setCellStyle(commonCellStyles.get("headCellStyle"));
			}
			
			// 创建数据
			for (Map<String, Object> data : dataList) {
				XSSFRow data_row = sheet.createRow(++rowIndex);
				data_row.setHeightInPoints(18);
				for(int i = 0; i < columns.size(); i++) {
					XSSFCell data_cell = data_row.createCell(i);
					// Fill Cell Data and set Cell Style
					setCellData4Xssf(data_cell, data.get(columns.get(i)), commonCellStyles);
				}
			}
			
			// 列宽自适应
//			int columnSize = sheet.getRow(0).getPhysicalNumberOfCells();
			for(int i = 0;i < columnNames.size(); i++) {
//				sheet.setColumnWidth(i, columnNames.get(i).getBytes().length*2*256);
				sheet.autoSizeColumn(i,true);
                sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 1024);
			}
		}

		return workbook;
	}

    /**
     * 多个列名不同的SHEET
     * @param columnNamesMap
     * @param columnsMap
     * @param sheetDataList
     * @return HSSFWorkbook
     */
    public static XSSFWorkbook getWorkbook4XssfOnSheet(Map<String, List<String>> columnNamesMap,
                                                       Map<String, List<String>> columnsMap,
                                                       Map<String, List<Map<String, Object>>> sheetDataList) {

        // 创建Excel文件
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Generate Common Cell Styles for Excel Template
        Map<String, XSSFCellStyle> commonCellStyles = genCommonCellStyle4Xssf(workbook);

        for(Entry<String, List<Map<String, Object>>> entry : sheetDataList.entrySet()) {
            String sheetName = entry.getKey();
            List<Map<String, Object>> dataList = entry.getValue();

            // 创建Sheet
            XSSFSheet sheet = workbook.createSheet(sheetName);

            Integer rowIndex = -1;

            // 创建表头
            XSSFRow col_row = sheet.createRow(++rowIndex);
            col_row.setHeightInPoints(26);
            List<String> columnNames = columnNamesMap.get(sheetName);
            for (int i = 0; i < columnNames.size(); i++) {
                XSSFCell cell = col_row.createCell(i);
                cell.setCellValue(columnNames.get(i));
                cell.setCellStyle(commonCellStyles.get("headCellStyle"));
            }

            // 创建数据
            for (Map<String, Object> data : dataList) {
                XSSFRow data_row = sheet.createRow(++rowIndex);
                data_row.setHeightInPoints(18);
                List<String> columns = columnsMap.get(sheetName);
                for(int i = 0; i < columns.size(); i++) {
                    XSSFCell data_cell = data_row.createCell(i);
                    // Fill Cell Data and set Cell Style
                    setCellData4Xssf(data_cell, data.get(columns.get(i)), commonCellStyles);
                }
            }

            // 列宽自适应
//			int columnSize = sheet.getRow(0).getPhysicalNumberOfCells();
            for(int i = 0;i < columnNames.size(); i++) {
//				sheet.setColumnWidth(i, columnNames.get(i).getBytes().length*2*256);
                sheet.autoSizeColumn(i,true);
                sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 1024);
            }
        }

        return workbook;
    }
	
	/**
	 * 单Sheet输出Excel
	 * @param columnNames
	 * @param columns
	 * @param dataList
	 * @param sheetName
	 * @return HSSFWorkbook
	 */
	public static HSSFWorkbook getWorkbook4Hssf(List<String> columnNames,
			List<String> columns, List<Map<String, Object>> dataList,
			String sheetName) {

		// 创建Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();

		// Generate Common Cell Styles for Excel Template
		Map<String, HSSFCellStyle> commonCellStyles = genCommonCellStyle4Hssf(workbook);
		
		// 创建Sheet
		HSSFSheet sheet = workbook.createSheet(sheetName);

		Integer rowIndex = -1;

		// 创建表头
		HSSFRow col_row = sheet.createRow(++rowIndex);
		col_row.setHeightInPoints(26);
		for (int i = 0; i < columnNames.size(); i++) {
			HSSFCell cell = col_row.createCell(i);
			cell.setCellValue(columnNames.get(i));
			cell.setCellStyle(commonCellStyles.get("headCellStyle"));
		}

		// 创建数据
		for (Map<String, Object> data : dataList) {
			HSSFRow data_row = sheet.createRow(++rowIndex);
			data_row.setHeightInPoints(18);
			for(int i = 0; i < columns.size(); i++) {
				HSSFCell data_cell = data_row.createCell(i);
				// Fill Cell Data and set Cell Style
				setCellData4Hssf(data_cell, data.get(columns.get(i)), commonCellStyles);
			}
		}
		
		// 列宽自适应
//		int columnSize = sheet.getRow(0).getPhysicalNumberOfCells();
		for(int i = 0;i < columnNames.size(); i++) {
//			sheet.setColumnWidth(i, columnNames.get(i).getBytes().length*2*256);
			sheet.autoSizeColumn(i,true);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 1024);
		}

		return workbook;
	}
	
	/**
	 * 按Sheet输出Excel
	 * @param columnNames
	 * @param columns
	 * @param sheetDataList
	 * @return HSSFWorkbook
	 */
	public static HSSFWorkbook getWorkbook4HssfOnSheet(List<String> columnNames,
			List<String> columns, Map<String, List<Map<String, Object>>> sheetDataList) {

		// 创建Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		// Generate Common Cell Styles for Excel Template
		Map<String, HSSFCellStyle> commonCellStyles = genCommonCellStyle4Hssf(workbook);
		
		for(Entry<String, List<Map<String, Object>>> entry : sheetDataList.entrySet()) {
			String sheetName = entry.getKey();
			List<Map<String, Object>> dataList = entry.getValue();
			
			// 创建Sheet
			HSSFSheet sheet = workbook.createSheet(sheetName);
			
			Integer rowIndex = -1;
			
			// 创建表头
			HSSFRow col_row = sheet.createRow(++rowIndex);
			col_row.setHeightInPoints(26);
			for (int i = 0; i < columnNames.size(); i++) {
				HSSFCell cell = col_row.createCell(i);
				cell.setCellValue(columnNames.get(i));
				cell.setCellStyle(commonCellStyles.get("headCellStyle"));
			}
			
			// 创建数据
			for (Map<String, Object> data : dataList) {
				HSSFRow data_row = sheet.createRow(++rowIndex);
				data_row.setHeightInPoints(18);
				for(int i = 0; i < columns.size(); i++) {
					HSSFCell data_cell = data_row.createCell(i);
					// Fill Cell Data and set Cell Style
					setCellData4Hssf(data_cell, data.get(columns.get(i)), commonCellStyles);
				}
			}
			
			// 列宽自适应
//			int columnSize = sheet.getRow(0).getPhysicalNumberOfCells();
			for(int i = 0;i < columnNames.size(); i++) {
//				sheet.setColumnWidth(i, columnNames.get(i).getBytes().length*2*256);
				sheet.autoSizeColumn(i,true);
                sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 1024);
			}
		}

		return workbook;
	}
	
	/**
	 * Generate Common Cell Styles for Excel Template
	 * @param workbook
	 * @return Map
	 */
	protected static Map<String, XSSFCellStyle> genCommonCellStyle4Xssf(XSSFWorkbook workbook) {
		Map<String, XSSFCellStyle> commonCellStyles = new HashMap<String, XSSFCellStyle>();
		
		// 设置表头字体
		XSSFFont headFont = workbook.createFont(); 
		headFont.setFontHeightInPoints((short)12);
		// 设置表体字体
		XSSFFont bodyFont = workbook.createFont(); 
		bodyFont.setFontHeightInPoints((short)10);
		
		XSSFColor CORNFLOWER_BLUE = new XSSFColor(CORNFLOWER_BLUE_COLOR);
		XSSFColor GREY_40_PERCENT = new XSSFColor(GREY_40_PERCENT_COLOR);
		
		// 表头单元格样式对象
		XSSFCellStyle headCellStyle = workbook.createCellStyle();
		headCellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		headCellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		headCellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		headCellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		headCellStyle.setBottomBorderColor(GREY_40_PERCENT);
		headCellStyle.setRightBorderColor(GREY_40_PERCENT);
		headCellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		headCellStyle.setFillForegroundColor(CORNFLOWER_BLUE);
		headCellStyle.setFont(headFont);
		
		// 表体单元格样式
		XSSFCellStyle bodyCellStyle = workbook.createCellStyle();
		bodyCellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		bodyCellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		bodyCellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		bodyCellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		bodyCellStyle.setBottomBorderColor(GREY_40_PERCENT);
		bodyCellStyle.setRightBorderColor(GREY_40_PERCENT);
		bodyCellStyle.setFont(bodyFont);
		
		// 数字单元格样式
		XSSFCellStyle numberCellStyle = workbook.createCellStyle();
		numberCellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		numberCellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		numberCellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		numberCellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		numberCellStyle.setBottomBorderColor(GREY_40_PERCENT);
		numberCellStyle.setRightBorderColor(GREY_40_PERCENT);
		numberCellStyle.setFont(bodyFont);
//		numberCellStyle.setDataFormat(workbook.createDataFormat().getFormat(NUMBER_FORMAT_1));
		
		// 日期单元格样式
		XSSFCellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		dateCellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		dateCellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		dateCellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		dateCellStyle.setBottomBorderColor(GREY_40_PERCENT);
		dateCellStyle.setRightBorderColor(GREY_40_PERCENT);
		dateCellStyle.setFont(bodyFont);
		dateCellStyle.setDataFormat(workbook.createDataFormat().getFormat(DATE_FORMAT_SHORT));
		
		// 时间戳单元格样式
		XSSFCellStyle timestampCellStyle = workbook.createCellStyle();
		timestampCellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		timestampCellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		timestampCellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		timestampCellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		timestampCellStyle.setBottomBorderColor(GREY_40_PERCENT);
		timestampCellStyle.setRightBorderColor(GREY_40_PERCENT);
		timestampCellStyle.setFont(bodyFont);
		timestampCellStyle.setDataFormat(workbook.createDataFormat().getFormat(TIMESTAMP_FORMAT_SHORT));
		
		commonCellStyles.put("headCellStyle", headCellStyle);
		commonCellStyles.put("bodyCellStyle", bodyCellStyle);
		commonCellStyles.put("numberCellStyle", numberCellStyle);
		commonCellStyles.put("dateCellStyle", dateCellStyle);
		commonCellStyles.put("timestampCellStyle", timestampCellStyle);
		return commonCellStyles;
	}
	
	/**
	 * Fill Cell Data and set Cell Style
	 * @param cell
	 * @param value
	 * @param commonCellStyles
	 */
	protected static void setCellData4Xssf(XSSFCell cell, Object value,
			Map<String, XSSFCellStyle> commonCellStyles) {
		cell.setCellStyle(commonCellStyles.get("bodyCellStyle"));
		if (value instanceof String) {
			cell.setCellValue((String) value);
		} else if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Float) {
			cell.setCellValue(((Float) value));
			cell.setCellStyle(commonCellStyles.get("numberCellStyle"));
		} else if (value instanceof Double) {
			cell.setCellValue(((Double) value));
			cell.setCellStyle(commonCellStyles.get("numberCellStyle"));
		} else if (value instanceof BigDecimal) {
			cell.setCellValue(((BigDecimal) value).doubleValue());
			cell.setCellStyle(commonCellStyles.get("numberCellStyle"));
		} else if (value instanceof Timestamp) {
			cell.setCellValue(((Timestamp) value));
			cell.setCellStyle(commonCellStyles.get("timestampCellStyle"));
		} else if (value instanceof Date || value instanceof java.sql.Date) {
			cell.setCellValue(((Date) value));
			cell.setCellStyle(commonCellStyles.get("dateCellStyle"));
		} else {
			if(null != value) {
				cell.setCellValue(value.toString());
			}
		}
	}
	
	/**
	 * Generate Common Cell Styles for Excel Template
	 * @param workbook
	 * @return Map
	 */
	protected static Map<String, HSSFCellStyle> genCommonCellStyle4Hssf(HSSFWorkbook workbook) {
		Map<String, HSSFCellStyle> commonCellStyles = new HashMap<String, HSSFCellStyle>();
		
		// 设置表头字体
		HSSFFont headFont = workbook.createFont(); 
		headFont.setFontHeightInPoints((short)12);
		// 设置表体字体
		HSSFFont bodyFont = workbook.createFont(); 
		bodyFont.setFontHeightInPoints((short)10);
		
		// 表头单元格样式对象
		HSSFCellStyle headCellStyle = workbook.createCellStyle();
		headCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		headCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headCellStyle.setBottomBorderColor(HSSFColor.GREY_40_PERCENT.index);
		headCellStyle.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
		headCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headCellStyle.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);
		headCellStyle.setFont(headFont);
		
		// 表体单元格样式
		HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
		bodyCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		bodyCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		bodyCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		bodyCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		bodyCellStyle.setBottomBorderColor(HSSFColor.GREY_40_PERCENT.index);
		bodyCellStyle.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
		bodyCellStyle.setFont(bodyFont);
		
		// 数字单元格样式
		HSSFCellStyle numberCellStyle = workbook.createCellStyle();
		numberCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		numberCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		numberCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		numberCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		numberCellStyle.setBottomBorderColor(HSSFColor.GREY_40_PERCENT.index);
		numberCellStyle.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
		numberCellStyle.setFont(bodyFont);
//		numberCellStyle.setDataFormat(workbook.createDataFormat().getFormat(NUMBER_FORMAT_1));
		
		// 日期单元格样式
		HSSFCellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		dateCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		dateCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		dateCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		dateCellStyle.setBottomBorderColor(HSSFColor.GREY_40_PERCENT.index);
		dateCellStyle.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
		dateCellStyle.setFont(bodyFont);
		dateCellStyle.setDataFormat(workbook.createDataFormat().getFormat(DATE_FORMAT_SHORT));
		
		// 时间戳单元格样式
		HSSFCellStyle timestampCellStyle = workbook.createCellStyle();
		timestampCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		timestampCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		timestampCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		timestampCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		timestampCellStyle.setBottomBorderColor(HSSFColor.GREY_40_PERCENT.index);
		timestampCellStyle.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
		timestampCellStyle.setFont(bodyFont);
		timestampCellStyle.setDataFormat(workbook.createDataFormat().getFormat(TIMESTAMP_FORMAT_SHORT));
		
		commonCellStyles.put("headCellStyle", headCellStyle);
		commonCellStyles.put("bodyCellStyle", bodyCellStyle);
		commonCellStyles.put("numberCellStyle", numberCellStyle);
		commonCellStyles.put("dateCellStyle", dateCellStyle);
		commonCellStyles.put("timestampCellStyle", timestampCellStyle);
		return commonCellStyles;
	}
	
	/**
	 * Fill Cell Data and set Cell Style
	 * @param cell
	 * @param value
	 * @param commonCellStyles
	 */
	protected static void setCellData4Hssf(HSSFCell cell, Object value,
			Map<String, HSSFCellStyle> commonCellStyles) {
		cell.setCellStyle(commonCellStyles.get("bodyCellStyle"));
		if (value instanceof String) {
			cell.setCellValue((String) value);
		} else if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Float) {
			cell.setCellValue(((Float) value));
			cell.setCellStyle(commonCellStyles.get("numberCellStyle"));
		} else if (value instanceof Double) {
			cell.setCellValue(((Double) value));
			cell.setCellStyle(commonCellStyles.get("numberCellStyle"));
		} else if (value instanceof BigDecimal) {
			cell.setCellValue(((BigDecimal) value).doubleValue());
			cell.setCellStyle(commonCellStyles.get("numberCellStyle"));
		} else if (value instanceof Timestamp) {
			cell.setCellValue(((Timestamp) value));
			cell.setCellStyle(commonCellStyles.get("timestampCellStyle"));
		} else if (value instanceof Date || value instanceof java.sql.Date) {
			cell.setCellValue(((Date) value));
			cell.setCellStyle(commonCellStyles.get("dateCellStyle"));
		} else {
			if(null != value) {
				cell.setCellValue(value.toString());
			}
		}
	}

}
