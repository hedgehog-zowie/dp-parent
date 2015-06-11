package com.iuni.dp.log.datareport.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Log Data Log Bean
 * @author Kenneth.Cai@iuni.com
 * @version dp-log-1.1.4
 */
public class LogDataLog implements Serializable {

	private static final long serialVersionUID = 8171444283336591110L;

	private String logDataType;
	
	private String fileName;
	
	private Integer fileSize;
	
	private Integer dataSize;
	
	private Integer writeDataSize;
	
	private Long costTime;
	
	private Date writeTime;

	public LogDataLog() {
		super();
	}

	public LogDataLog(String logDataType, String fileName, Integer fileSize,
			Integer dataSize, Integer writeDataSize, Long costTime,
			Date writeTime) {
		super();
		this.logDataType = logDataType;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.dataSize = dataSize;
		this.writeDataSize = writeDataSize;
		this.costTime = costTime;
		this.writeTime = writeTime;
	}

	public String getLogDataType() {
		return logDataType;
	}

	public void setLogDataType(String logDataType) {
		this.logDataType = logDataType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public Integer getDataSize() {
		return dataSize;
	}

	public void setDataSize(Integer dataSize) {
		this.dataSize = dataSize;
	}

	public Integer getWriteDataSize() {
		return writeDataSize;
	}

	public void setWriteDataSize(Integer writeDataSize) {
		this.writeDataSize = writeDataSize;
	}

	public Long getCostTime() {
		return costTime;
	}

	public void setCostTime(Long costTime) {
		this.costTime = costTime;
	}

	public Date getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}

	@Override
	public String toString() {
		return "LogDataLog [logDataType=" + logDataType + ", fileName="
				+ fileName + ", fileSize=" + fileSize + ", dataSize="
				+ dataSize + ", writeDataSize=" + writeDataSize + ", costTime="
				+ costTime + ", writeTime=" + writeTime + "]";
	}

}
