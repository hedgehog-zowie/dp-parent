package com.iuni.dp.service.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.iuni.dp.persist.common.constants.TypeEnum;
import com.iuni.dp.service.common.exception.BusinessException;

/**
 * 文件操作公共类
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public class FileToolkit {

	/**
	 * @Description: 创建文件目录
	 * @param String fileFullPath 目录
	 * @return void
	 * @throws BusinessException
	 */
	public static void mkdirs(String fileFullPath) {
		try {
			File file = new File(fileFullPath);
			if (!file.exists()) {
				file.mkdirs();
			}
		} catch (Exception e) {
			throw new BusinessException(TypeEnum.BusiExcEnum.FILE_MKDIR_FAIL.getKey(),TypeEnum.BusiExcEnum.FILE_MKDIR_FAIL.getValue());
		}
	}

	/**
	 * @Description:移动文件或者目录,移动前后文件完全一样,如果目标文件夹不存在则创建。
	 * @param resFilePath 源文件路径
	 * @param distFilePath 目标文件路径(移动后的目标文件路径)
	 * @return void
	 * @throws BusinessException
	 */
	public static void moveFile(String resFilePath, String distFilePath) {
		try {
			File resFile = new File(resFilePath);
			File distFile = new File(distFilePath);
			if (resFile.isDirectory()) {
				FileUtils.moveDirectoryToDirectory(resFile, distFile, true);
			} else if (resFile.isFile()) {
				FileUtils.moveFileToDirectory(resFile, distFile, true);
			}
		} catch (IOException e) {
			throw new BusinessException(TypeEnum.BusiExcEnum.FILE_MOVE_FAIL.getKey(),TypeEnum.BusiExcEnum.FILE_MOVE_FAIL.getValue());
		}
	}

	/**
	 * @Description:重命名文件或文件夹
	 * @param oldFileName 源文件路径
	 * @param newFileName 重命名
	 * @return boolean
	 * @throws BusinessException
	 */
	public static boolean renameFile(String oldFileName, String newFileName) {
		try {
			File oldFile = new File(oldFileName);
			File newFile = new File(newFileName);
			return oldFile.renameTo(newFile);
		} catch (Exception e) {
			throw new BusinessException(TypeEnum.BusiExcEnum.FILE_RENAME_FAIL.getKey(),TypeEnum.BusiExcEnum.FILE_RENAME_FAIL.getValue());
		}
	}
	
	/**
	 * @Description:批量行写文件
	 * @param List<String> dataList 待写的行数据列表
	 * @param String separator      行的文件分隔符
	 * @param String fileFullPath   待写文件全路径
	 * @param String encode         文件编码
	 * @return void
	 * @throws BusinessException
	 */
	public static void batchWriteLines(List<String> dataList, String separator,String fileFullPath, String encode) {
		File f = new File(fileFullPath);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f, true);
			IOUtils.writeLines(dataList, separator, fos, encode);
		} catch (Exception e) {
			throw new BusinessException(TypeEnum.BusiExcEnum.FILE_BATCH_WRITE_LINES_FAIL.getKey(),TypeEnum.BusiExcEnum.FILE_BATCH_WRITE_LINES_FAIL.getValue());
		} finally {
			IOUtils.closeQuietly(fos);
		}
	}
	
	public static void batchWriteLines(List<String> dataList,String fileFullPath) {
		if (dataList != null && dataList.size() > 0) {
			batchWriteLines(dataList, SEPARATOR, fileFullPath, ENCODE);
		}
	}
	
	/**
	 * @Description:批量行读文件
	 * @param String fileFullPath   待读文件全路径
	 * @param String encode         文件编码
	 * @return List<String>         读出来的行数据列表
	 * @throws BusinessException
	 */
	public static List<String> batchReadLines(String fileFullPath, String encode) {
		File f = new File(fileFullPath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
			return IOUtils.readLines(fis, encode);
		} catch (Exception e) {
			throw new BusinessException(TypeEnum.BusiExcEnum.FILE_BATCH_READ_LINES_FAIL.getKey(),TypeEnum.BusiExcEnum.FILE_BATCH_READ_LINES_FAIL.getValue());
		} finally {
			IOUtils.closeQuietly(fis);
		}
	}
	
	public static List<String> batchReadLines(String fileFullPath) {
		return batchReadLines(fileFullPath, ENCODE);
	}
	
	/**
	 * @Description:返回新生成文件的文件名称
	 *              比如：0_20130313152104125_601.txt
	 * @param Date fileCreateTime   文件创建时间
	 * @param int index             创建当前文件的线程编号
	 * @return String
	 * @throws 
	 */
	public static String getWriteFileName(Date fileCreateTime, int index) {
		//写当前文件系统时间的毫秒级数字串
		String fileCreateTimeStr = DateUtil.dateToDateString(fileCreateTime,DateUtil.MSEL_STR_FORMAT);
		StringBuilder builder = new StringBuilder();
		builder.append(index).append(FILE_NAME_SEPARATOR)
			    .append(fileCreateTimeStr).append(FILE_NAME_SEPARATOR)
				.append(FILE_NAME_RANDOM).append(DOT)
				.append(FILE_EXT_NAME);
		return builder.toString();
	}
	
	/**
	 * @Description:返回新生成文件的文件名称对应的重命名后的文件名
	 *              比如：E://drs//branches//drs-1.0.0//src//main//resources//ftp//file//0_20130313163114609_759_wf.txt
	 * @param String writeFileFullPath   新生成文件的文件名称
	 *              比如：E://drs//branches//drs-1.0.0//src//main//resources//ftp//file//0_20130313163114609_759.txt
	 * @return String
	 * @throws 
	 */
	public static String getRenameFullPathName(String writeFileFullPath) {
		String renamePrefixPath = writeFileFullPath.substring(0, writeFileFullPath.lastIndexOf(DOT));
		StringBuilder builder = new StringBuilder();
		builder.append(renamePrefixPath)
		       .append(FILE_NAME_SEPARATOR)
		       .append(FILE_WRITE_FINISH)
		       .append(DOT).append(FILE_EXT_NAME);
		return builder.toString();
	}
	
	/**
	 * @Description:列出某个路径下所有文件的全文件名列表(支持文件递归)
	 * @param File file  文件目录
	 * @param List<String> filesPathList 用于存放全文件名的列表
	 * @return List<String> 返回某个路径下所有文件的全文件名列表
	 */
	public static List<String> allFileList(File file,List<String> filesPathList) {
		File[] files = file.listFiles();
		int length = (files != null) ? files.length : 0;
		for (int i = 0; i < length; i++) {
			File f = files[i];
			if (f != null) {
				if (f.isFile()) {
					filesPathList.add(f.getAbsolutePath());
					continue;
				} else if (f.isDirectory()) {
					allFileList(f,filesPathList);
				}
			}
		}
		return filesPathList;
	}
	
	/**
	 * @Description: 返回某个路径下特定扩展名的文件列表
	 * @param File file 路径文件对象
	 * @param String extName 扩展名
	 * @return  List<String> 返回某个路径下特定扩展名的文件列表
	 */
	public static List<String> allFileList(File file, String extName) {
		List<String> filterFilePathList = new ArrayList<String>();
		List<String> allFilePathList = new ArrayList<String>();
		for (String filePathFullName : allFileList(file, allFilePathList)) {
			if (StringUtils.isNotEmpty(filePathFullName)) {
				String fileExt = getFileExtName(filePathFullName);
				if (extName.equalsIgnoreCase(fileExt)) {
					filterFilePathList.add(filePathFullName);
				}
			}
		}
		return filterFilePathList;
	}
	
	/**
	 * @Description: 返回某个路径下所有满足条件的全文件名列表
	 * @param File file 路径文件对象
	 * @param String extName 扩展名
	 * @param String index   写文件线程编号
	 * @param String writeFinish  文件以此后缀结尾（出去扩展名外）
	 * @return  List<String> 返回某个路径下所有满足条件的全文件名列表
	 */
	public static List<String> allFileList(File file, String extName,int index, String writeFinish) {
		List<String> filterFilePathList = new ArrayList<String>();
		List<String> allFilePathList = new ArrayList<String>();
		for (String filePathFullName : allFileList(file, allFilePathList)) {
			if (StringUtils.isNotEmpty(filePathFullName)) {
				String fileExt = getFileExtName(filePathFullName);
				String prefix = getFileNamePrefix(filePathFullName);
				String suffix = getFileNameSuffix(filePathFullName);
				if (extName.equalsIgnoreCase(fileExt) && (String.valueOf(index)).equals(prefix) && writeFinish.equals(suffix)) {
					filterFilePathList.add(filePathFullName);
				}
			}
		}
		return filterFilePathList;
	}
	
	/**
	 * @Description: 返回某个路径下所有满足条件的全文件名列表
	 * @param file 路径文件对象
	 * @param extName 扩展名
	 * @param fileFlag 写文件数据类型  + 线程编号
	 * @param writeFinish 文件以此后缀结尾（出去扩展名外）
	 * @return List<String> 返回某个路径下所有满足条件的全文件名列表
	 */
	public static List<String> allFileList(File file, String extName,String fileFlag, String writeFinish) {
		List<String> filterFilePathList = new ArrayList<String>();
		List<String> allFilePathList = new ArrayList<String>();
		for (String filePathFullName : allFileList(file, allFilePathList)) {
			if (StringUtils.isNotEmpty(filePathFullName)) {
				String fileExt = getFileExtName(filePathFullName);
				String prefix = getFileNamePrefix(filePathFullName);
				String suffix = getFileNameSuffix(filePathFullName);
				if (extName.equalsIgnoreCase(fileExt) && fileFlag.equals(prefix) && writeFinish.equals(suffix)) {
					filterFilePathList.add(filePathFullName);
				}
			}
		}
		return filterFilePathList;
	}
	
	public static List<String> allFileList2(File file, String extName,String fileFlag, String writeFinish) {
		List<String> filterFilePathList = new ArrayList<String>();
		List<String> allFilePathList = new ArrayList<String>();
		for (String filePathFullName : allFileList(file, allFilePathList)) {
			if (StringUtils.isNotEmpty(filePathFullName)) {
				String fileExt = getFileExtName(filePathFullName);
				String prefix = getFileNamePrefix2(filePathFullName);
				String suffix = getFileNameSuffix(filePathFullName);
				if (extName.equalsIgnoreCase(fileExt) && fileFlag.equals(prefix) && writeFinish.equals(suffix)) {
					filterFilePathList.add(filePathFullName);
				}
			}
		}
		return filterFilePathList;
	}
	
	public static List<String> findAllFileList4Dp(File file, String extName,String fileFlag) {
		List<String> filterFilePathList = new ArrayList<String>();
		List<String> allFilePathList = new ArrayList<String>();
		for (String filePathFullName : allFileList(file, allFilePathList)) {
			if (StringUtils.isNotEmpty(filePathFullName)) {
				String fileExt = getFileExtName(filePathFullName);
				String prefix = getFileNamePrefix4Dp(filePathFullName);
				if (extName.equalsIgnoreCase(fileExt) && fileFlag.equals(prefix)) {
					filterFilePathList.add(filePathFullName);
				}
			}
		}
		return filterFilePathList;
	}
	
	/**
	 * 按前缀条件查询某一文件下符合的文件列表
	 * @param file
	 * @param prefix
	 * @param suffix
	 * @return List
	 */
	public static List<String> findAllFileListByPrefix(File file, String prefix) {
		List<String> filterFilePathList = new ArrayList<String>();
		List<String> allFilePathList = new ArrayList<String>();
		for(String filePathFullName : allFileList(file, allFilePathList)) {
			if (StringUtils.isNotEmpty(filePathFullName)) {
				String file_prefix = getFileNamePrefixByFlag(filePathFullName, "\\.");
				if (prefix.equals(file_prefix)) {
					filterFilePathList.add(filePathFullName);
				}
			}
		}
		
		return filterFilePathList;
	}
	
	/**
	 * @Description:取得文件的扩展名
	 * @param pathName 文件的路径
	 * @return 文件的扩展名
	 */
	public static String getFileExtName(String pathName) {
		int start = pathName.lastIndexOf(DOT);
		return pathName.substring(start + 1);
	}
	
	/**
	 * @Description:取得文件名前缀
	 * @param String filePathFullName 文件的全路径名称
	 * @return 返回文件全路径名的前缀
	 */
	public static String getFileNamePrefix(String filePathFullName) {
		int start = filePathFullName.indexOf(FILE_NAME_SEPARATOR) != -1 
				    ? filePathFullName.indexOf(FILE_NAME_SEPARATOR) - 1 : 0;
		return filePathFullName.substring(start, start + 1);
	}
	
	/**
	 * @Description:取得文件名前缀
	 * 适配Linux、Windows系统
	 * @param filePathFullName
	 * @return String
	 */
	public static String getFileNamePrefix2(String filePathFullName) {
		String result = "";
		String[] detail = filePathFullName.replaceAll("\\\\", "/").split("/");
		if(null != detail && detail.length > 0) {
			filePathFullName = detail[detail.length - 1];
			String[] details = filePathFullName.split("_");
			if(null != details && details.length >= 2) {
				result = details[0] + '_' + details[1];
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param filePathFullName
	 * @return
	 */
	public static String getFileNamePrefix4Dp(String filePathFullName) {
		String result = "";
		String[] detail = filePathFullName.replaceAll("\\\\", "/").split("/");
		if(null != detail && detail.length > 0) {
			filePathFullName = detail[detail.length - 1];
			String[] details = filePathFullName.split("-");
			if(null != details && details.length >= 2) {
				result = details[0];
			}
		}
		
		return result;
	}
	
	/**
	 * 按照标记分隔符获取文件前缀
	 * @param filePathFullName
	 * @param flag
	 * @return String
	 */
	public static String getFileNamePrefixByFlag(String filePathFullName, String flag) {
		String result = "";
		String[] detail = filePathFullName.replaceAll("\\\\", "/").split("/");
		if(null != detail && detail.length > 0) {
			filePathFullName = detail[detail.length - 1];
			String[] details = filePathFullName.split(flag);
			if(null != details && details.length >= 2) {
				result = details[0];
			}
		}
		
		return result;
	}
	
	/**
	 * @Description:由文件完整路径获取文件名
	 * @param filePathFullName
	 * @return String
	 */
	public static String getFileNameFromPath(String filePathFullName) {
		String result = "";
		
		String[] detail = filePathFullName.replaceAll("\\\\", "/").split("/");
		if(null != detail && detail.length > 0) {
			result = detail[detail.length - 1];
		}
		
		return result;
	}
	
	/**
	 * @Description:取得文件名后缀
	 * @param String filePathFullName 文件的全路径名称
	 * @return 返回文件全路径名的后缀
	 */
	public static String getFileNameSuffix(String filePathFullName) {
		int start = filePathFullName.lastIndexOf(FILE_NAME_SEPARATOR) + 1;
		int end = filePathFullName.lastIndexOf(DOT);
		return filePathFullName.substring(start, end);
	}
	
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String sPath) {  
		boolean flag = false;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	} 
	
	public static final String SEPARATOR = "\r\n";
	public static final String ENCODE = "UTF-8";
	public static final String FILE_NAME_SEPARATOR = "_"; // 生成文件文件名分隔符_
	public static final String DOT = ".";                 // 点.
	public static final int FILE_NAME_RANDOM = SystemUtil.getBetweenRandom(100,999); // 生成文件文件名三位随机数
	public static final String FILE_WRITE_FINISH = "wf"; // 文件写完成标志
	public static final String FILE_EXT_NAME = "txt"; // 生成文件扩展名
	
	public static void main(String[] args) {
		
		//String writeFileFullPath = getWriteFileName(new Date(),0);
		//System.out.println(ParseProperties.FILE_PATH+writeFileFullPath);
		
		//mkdirs(writeFileFullPath);
		
		//String oldFilePathName = "E://drs//branches//drs-1.0.0//src//main//resources//ftp//file//0_20130313152104125_601.txt";
		//String newFilePathName = "E://drs//branches//drs-1.0.0//src//main//resources//ftp//file//0_20130313152104125_601_wf.txt";
		//renameFile(oldFilePathName,newFilePathName);
		
		//String renameFullPathName = getRenameFullPathName(oldFilePathName);
		//System.out.println(renameFullPathName);
		
		String filePath = "E://drs//branches//drs-1.0.0//src//main//resources//ftp//file";
		File file = new File(filePath);
		//List<String> filesPath =new ArrayList<String>();
		//List<String> list = allFileList(file,filesPath);
		//if (list!=null && list.size()>0) {
		//	for (String temp : list) {
		//	  System.out.println(temp);	
		//	}
		//}
		
		//System.out.println(getFileNamePrefix(newFilePathName));
		//System.out.println(getFileNameSuffix(newFilePathName));
		
		System.out.println(allFileList(file,FILE_EXT_NAME,0,FILE_WRITE_FINISH));
		
	}

}
