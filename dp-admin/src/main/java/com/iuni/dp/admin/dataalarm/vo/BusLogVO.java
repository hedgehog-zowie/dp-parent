package com.iuni.dp.admin.dataalarm.vo;

/**
 * 业务日志VO类
 * @author CaiKe
 * @version V1.0.0
 */
public class BusLogVO {
	
	/**
	 * 业务类型
	 */
	private String busType;
	
	/**
	 * 业务执行结果
	 *  1:sucess 0:fail 缺省值为1
	 */
    private Integer executResult ;
    
    /**
     * 开始时间
     */
    private String beginDate;
    
    /**
     * 结束时间
     */
    private String endDate;
    
	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public Integer getExecutResult() {
		return executResult;
	}

	public void setExecutResult(Integer executResult) {
		this.executResult = executResult;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "BusLogVO [busType=" + busType + ", executResult="
				+ executResult + ", beginDate=" + beginDate + ", endDate="
				+ endDate + "]";
	}
	
}
