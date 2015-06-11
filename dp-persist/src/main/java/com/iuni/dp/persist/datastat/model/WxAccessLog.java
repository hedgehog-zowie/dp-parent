package com.iuni.dp.persist.datastat.model;

import java.util.Date;

/**
 * 微信公众帐号访问日志Model
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public class WxAccessLog {

	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 发送方帐号(一个openID)
	 */
	private String userId;
	
	/**
	 * 微信公众帐号id
	 */
	private String publicUserId;
	
	/**
	 * 4种消息类型：text,event,location,image
	 */
	private String msgType;
	
	/**
	 * 发送文本内容(mst_type=evnet时为空)
	 */
	private String keyword;
	
	/**
	 * 0: 关键字 , 1: 非关键字, 2: 表情 (mst_type=evnet时为空)
	 */
	private Integer keywordType;
	
	/**
	 * subscribe(订阅)、unsubscribe(取消订阅)、CLICK(自定义菜单点击事件)(mst_type=text时为空)
	 */
	private String eventType;

	/**
	 * 0:无结果 1:有结果
	 */
	private Integer hasResult;
	
	/**
	 * 查询到的规则id,无结果时为空或默认规则(2)
	 */
	private Integer ruleId;
	
	/**
	 * 回复的图片素材id,无图片为空
	 */
	private Integer imgId;
	
	/**
	 * 图片名
	 */
	private String imgName;
	
	/**
	 * 图片url(相对路径)
	 */
	private String imgUrl;
	
	/**
	 * 标签名
	 */
	private String tagName;
	
	/**
	 * 回复的文本素材id,无文字为空
	 */
	private Integer txtId;
	
	/**
	 * 查询耗时(单位：ms)
	 */
	private Integer costTime;
	
	/**
	 * 上报时间
	 */
	private Date logTime;
	
	/**
	 * 入库时间
	 */
	private Date createTime;

	public WxAccessLog() {
		super();
	}

	public WxAccessLog(String userId, String publicUserId, String msgType,
			String keyword, Integer keywordType, String eventType,
			Integer hasResult, Integer ruleId, Integer imgId, String imgName,
			String imgUrl, String tagName, Integer txtId, Integer costTime,
			Date logTime, Date createTime) {
		super();
		this.userId = userId;
		this.publicUserId = publicUserId;
		this.msgType = msgType;
		this.keyword = keyword;
		this.keywordType = keywordType;
		this.eventType = eventType;
		this.hasResult = hasResult;
		this.ruleId = ruleId;
		this.imgId = imgId;
		this.imgName = imgName;
		this.imgUrl = imgUrl;
		this.tagName = tagName;
		this.txtId = txtId;
		this.costTime = costTime;
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

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getKeywordType() {
		return keywordType;
	}

	public void setKeywordType(Integer keywordType) {
		this.keywordType = keywordType;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Integer getHasResult() {
		return hasResult;
	}

	public void setHasResult(Integer hasResult) {
		this.hasResult = hasResult;
	}

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Integer getTxtId() {
		return txtId;
	}

	public void setTxtId(Integer txtId) {
		this.txtId = txtId;
	}

	public Integer getCostTime() {
		return costTime;
	}

	public void setCostTime(Integer costTime) {
		this.costTime = costTime;
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
		return "WxAccessLog [userId=" + userId + ", publicUserId="
				+ publicUserId + ", msgType=" + msgType + ", keyword="
				+ keyword + ", keywordType=" + keywordType + ", eventType="
				+ eventType + ", hasResult=" + hasResult + ", ruleId=" + ruleId
				+ ", imgId=" + imgId + ", imgName=" + imgName + ", imgUrl="
				+ imgUrl + ", tagName=" + tagName + ", txtId=" + txtId
				+ ", costTime=" + costTime + ", logTime=" + logTime
				+ ", createTime=" + createTime + "]";
	}
	
}
