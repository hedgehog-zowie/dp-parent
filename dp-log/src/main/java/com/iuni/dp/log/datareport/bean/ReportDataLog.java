package com.iuni.dp.log.datareport.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * ReportData类型上报数据日志
 * @author CaiKe
 * @version dp-log-1.0.0
 */
public class ReportDataLog implements Serializable {

	private static final long serialVersionUID = -1721720930300138405L;

	private String rptDataType;
	
	private String fileName;
	
	private Integer fileSize;
	
	private Integer normalDataSize;
	
	private Integer abNormalDataSize;
	
	private Integer write2DbNormalDataSize;
	
	private Integer write2DbAbNormalDataSize;
	
	private Long costTime;
	
	private Date writeTime;

	public ReportDataLog() {
		super();
	}

	public ReportDataLog(String fileName, Integer fileSize,
			Integer normalDataSize, Integer abNormalDataSize,
			Integer write2DbNormalDataSize, Integer write2DbAbNormalDataSize,
			Long costTime, Date writeTime) {
		super();
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.normalDataSize = normalDataSize;
		this.abNormalDataSize = abNormalDataSize;
		this.write2DbNormalDataSize = write2DbNormalDataSize;
		this.write2DbAbNormalDataSize = write2DbAbNormalDataSize;
		this.costTime = costTime;
		this.writeTime = writeTime;
	}

	public ReportDataLog(String rptDataType, String fileName, Integer fileSize,
			Integer normalDataSize, Integer abNormalDataSize,
			Integer write2DbNormalDataSize, Integer write2DbAbNormalDataSize,
			Long costTime, Date writeTime) {
		super();
		this.rptDataType = rptDataType;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.normalDataSize = normalDataSize;
		this.abNormalDataSize = abNormalDataSize;
		this.write2DbNormalDataSize = write2DbNormalDataSize;
		this.write2DbAbNormalDataSize = write2DbAbNormalDataSize;
		this.costTime = costTime;
		this.writeTime = writeTime;
	}

	public String getRptDataType() {
		return rptDataType;
	}

	public void setRptDataType(String rptDataType) {
		this.rptDataType = rptDataType;
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

	public Integer getNormalDataSize() {
		return normalDataSize;
	}

	public void setNormalDataSize(Integer normalDataSize) {
		this.normalDataSize = normalDataSize;
	}

	public Integer getAbNormalDataSize() {
		return abNormalDataSize;
	}

	public void setAbNormalDataSize(Integer abNormalDataSize) {
		this.abNormalDataSize = abNormalDataSize;
	}

	public Integer getWrite2DbNormalDataSize() {
		return write2DbNormalDataSize;
	}

	public void setWrite2DbNormalDataSize(Integer write2DbNormalDataSize) {
		this.write2DbNormalDataSize = write2DbNormalDataSize;
	}

	public Integer getWrite2DbAbNormalDataSize() {
		return write2DbAbNormalDataSize;
	}

	public void setWrite2DbAbNormalDataSize(Integer write2DbAbNormalDataSize) {
		this.write2DbAbNormalDataSize = write2DbAbNormalDataSize;
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
		return "ReportDataLog [rptDataType=" + rptDataType + ", fileName="
				+ fileName + ", fileSize=" + fileSize + ", normalDataSize="
				+ normalDataSize + ", abNormalDataSize=" + abNormalDataSize
				+ ", write2DbNormalDataSize=" + write2DbNormalDataSize
				+ ", write2DbAbNormalDataSize=" + write2DbAbNormalDataSize
				+ ", costTime=" + costTime + ", writeTime=" + writeTime + "]";
	}

}
