package com.iuni.dp.persist.datastat.model;

import java.util.Date;

/**
 * 微信公众帐号点击日志
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public class WxClickLog {
	
	/**
     * 主键
     */
    private Long id;
	
	/**
	 * 微信用户id
	 */
	private String userId;
	
	/**
	 * 微信公众帐号id
	 */
	private String publicUserId;
	
	/**
	 * 图片素材id
	 */
	private Integer imgId;
	
	/**
	 * 文本素材id
	 */
	private Integer txtId;
	
	/**
	 * 上报时间
	 */
	private Date logTime;
	
	/**
	 * 入库时间
	 */
	private Date createTime;

	public WxClickLog() {
		super();
	}

	public WxClickLog(String userId, String publicUserId, Integer imgId,
			Integer txtId, Date logTime, Date createTime) {
		super();
		this.userId = userId;
		this.publicUserId = publicUserId;
		this.imgId = imgId;
		this.txtId = txtId;
		this.logTime = logTime;
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPublicUserId() {
		return publicUserId;
	}

	public void setPublicUserId(String publicUserId) {
		this.publicUserId = publicUserId;
	}

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public Integer getTxtId() {
		return txtId;
	}

	public void setTxtId(Integer txtId) {
		this.txtId = txtId;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "WxClickLog [userId=" + userId + ", publicUserId="
				+ publicUserId + ", imgId=" + imgId + ", txtId=" + txtId
				+ ", logTime=" + logTime + ", createTime=" + createTime + "]";
	}
	
}
