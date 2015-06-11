package com.iuni.dp.admin.datastat.vo;

/**
 * 调度类统计结果VO
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public class StatScheduledResultVo {

	/**
	 * 上报数据来源ID
	 */
	private String sourceId;
	
	
	/**
	 * 统计分析计划ID
	 */
	private String statSchemeId;
	
	/**
	 * 统计分析结果创建时间
	 */
	private String scheduledTime;

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getStatSchemeId() {
		return statSchemeId;
	}

	public void setStatSchemeId(String statSchemeId) {
		this.statSchemeId = statSchemeId;
	}

	public String getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(String scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	@Override
	public String toString() {
		return "StatScheduledResultVo [sourceId=" + sourceId
				+ ", statSchemeId=" + statSchemeId + ", scheduledTime="
				+ scheduledTime + "]";
	}

}
