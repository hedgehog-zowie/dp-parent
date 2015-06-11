package com.iuni.dp.admin.datastat.task;

/**
 * Quartz Job Detail
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public class QtzJobDetail {

	public static final int JS_DISABLED = 0;
	
	public static final int JS_ENABLED = 1;
	
	public static final int JS_DELETE = 2;
	
	private String jobId;
	
	private String jobName;
	
	private String jobGroup;
	
	private Integer jobStatus;
	
	private String cronExpression;
	
	private String description;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public Integer getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(Integer jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTriggerName() {
		return jobId + "Trigger";
	}

	@Override
	public String toString() {
		return "QtzJob [jobId=" + jobId + ", jobName=" + jobName
				+ ", jobGroup=" + jobGroup + ", jobStatus=" + jobStatus
				+ ", cronExpression=" + cronExpression + ", description="
				+ description + "]";
	}
	
}
