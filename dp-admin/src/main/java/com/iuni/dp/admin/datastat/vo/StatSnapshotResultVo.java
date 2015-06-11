package com.iuni.dp.admin.datastat.vo;

/**
 * 快照类统计结果VO
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public class StatSnapshotResultVo {

	/**
	 * 上报数据来源ID
	 */
	private String sourceId;
	
	
	/**
	 * 统计分析计划ID
	 */
	private String statSchemeId;
	
	/**
	 * 统计分析时间
	 */
	private String snapshotTime;

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

	public String getSnapshotTime() {
		return snapshotTime;
	}

	public void setSnapshotTime(String snapshotTime) {
		this.snapshotTime = snapshotTime;
	}

	@Override
	public String toString() {
		return "StatSnapshotResultVo [sourceId=" + sourceId + ", statSchemeId="
				+ statSchemeId + ", snapshotTime=" + snapshotTime + "]";
	}
	
}
