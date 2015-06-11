package com.iuni.dp.service.datareport.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iuni.dp.persist.common.utils.ParseProperties;
import com.iuni.dp.service.common.utils.FileToolkit;
import com.iuni.dp.service.datareport.constants.ReportDataType;

/**
 * 数据上报数据写文件工具类
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public class Data2FileUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(Data2FileUtil.class);
	
	/**
	 * 数据序列写文件操作
	 * @param List<String> dataList
	 * @param Integer index
	 */
	public static <T> void fileProcessForBean(List<T> dataList, Integer index, ReportDataType rptDataType) {
		// 构建新建文件的全路径名称
		String writeFileFullPath = ParseProperties.FILE_PATH + rptDataType.getValue() + "_" + FileToolkit.getWriteFileName(new Date(),index);
		
		// 若目录不存在，生成相应的文件目录
		FileToolkit.mkdirs(ParseProperties.FILE_PATH);
		
		// List<T>转换为List<String>
		List<String> writeDataList = dataListToStringList(dataList);
		
		// 批量写文件操作
		FileToolkit.batchWriteLines(writeDataList, writeFileFullPath);
		// 构建重命名后的新文件名
		String newFileName = FileToolkit.getRenameFullPathName(writeFileFullPath);
		// 文件重命名操作
		FileToolkit.renameFile(writeFileFullPath, newFileName);
		
		logger.debug("Data2FileUtil.fileProcessForBean write file success, rptDataType: " 
				+ rptDataType.getValue() + " index: " + index + " filePath: " + newFileName);
	}
	
	/**
	 * List<T>转换为List<String>
	 * @param dataList
	 * @return List<String>
	 */
	public static <T> List<String> dataListToStringList(List<T> dataList) {
		List<String> writeDataList = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(dataList)) {
			for (int i = 0; i < dataList.size(); i++) {
				T t = dataList.get(i);
				String data = JSON.toJSONString(t, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue);
				writeDataList.add(data);
			}
		}
		return writeDataList;
	}
	
	/**
	 * 数据序列写文件操作
	 * @param List<String> dataList
	 * @param Integer index
	 */
	public static void fileProcess(List<String> dataList, Integer index, String dataType) {
		//构建新建文件的全路径名称
		String writeFileFullPath = ParseProperties.FILE_PATH + dataType + "_" + FileToolkit.getWriteFileName(new Date(),index);
		//若目录不存在，生成相应的文件目录
		FileToolkit.mkdirs(ParseProperties.FILE_PATH);
		//批量写文件操作
		FileToolkit.batchWriteLines(dataList, writeFileFullPath);
		//构建重命名后的新文件名
		String newFileName = FileToolkit.getRenameFullPathName(writeFileFullPath);
		//文件重命名操作
		FileToolkit.renameFile(writeFileFullPath, newFileName);
	}
	
}
