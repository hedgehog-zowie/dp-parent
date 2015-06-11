/*
 * @(#)WxAccessLog.java 2013-8-5
 *
 * Copyright 2013 Shenzhen Gionee,Inc. All rights reserved.
 */
package com.iuni.dp.persist.datareport.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信公众帐号访问日志(微信推送消息和回复内容进行日志记录)
 * @author ZuoChangjun 2013-8-5
 * @version dp-persist-1.0.0
 */
public class WxAccessLog implements Serializable{


    private static final long serialVersionUID = 137567335054898392L;

    /**
     * column WX_ACCESS_LOG.ID
     */
    private Long id;

    /**
     * column WX_ACCESS_LOG.USER_ID
     */
    private String userId;

    /**
     * column WX_ACCESS_LOG.PUBLIC_USER_ID
     */
    private String publicUserId;

    /**
     * column WX_ACCESS_LOG.MSG_TYPE
     */
    private String msgType;

    /**
     * column WX_ACCESS_LOG.KEYWORD
     */
    private String keyword;

    /**
     * column WX_ACCESS_LOG.KEYWORD_TYPE
     */
    private Integer keywordType;

    /**
     * column WX_ACCESS_LOG.EVNET_TYPE
     */
    private String evnetType;

    /**
     * column WX_ACCESS_LOG.HAS_RESULT
     */
    private Integer hasResult;

    /**
     * column WX_ACCESS_LOG.RULE_ID
     */
    private Long ruleId;

    /**
     * column WX_ACCESS_LOG.IMG_ID
     */
    private Long imgId;

    /**
     * column WX_ACCESS_LOG.IMG_NAME
     */
    private String imgName;

    /**
     * column WX_ACCESS_LOG.IMG_URL
     */
    private String imgUrl;

    /**
     * column WX_ACCESS_LOG.TAG_NAME
     */
    private String tagName;

    /**
     * column WX_ACCESS_LOG.TXT_ID
     */
    private Long txtId;

    /**
     * column WX_ACCESS_LOG.COST_TIME
     */
    private Integer costTime;

    /**
     * column WX_ACCESS_LOG.LOG_TIME
     */
    private Date logTime;

    /**
     * column WX_ACCESS_LOG.CREATE_TIME
     */
    private Date createTime;

    public WxAccessLog() {
        super();
    }

    public WxAccessLog(Long id, String userId, String publicUserId, String msgType, String keyword, Integer keywordType, String evnetType, Integer hasResult, Long ruleId, Long imgId, String imgName, String imgUrl, String tagName, Long txtId, Integer costTime, Date logTime, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.publicUserId = publicUserId;
        this.msgType = msgType;
        this.keyword = keyword;
        this.keywordType = keywordType;
        this.evnetType = evnetType;
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

    /**
     * getter for Column WX_ACCESS_LOG.ID
     */
    public Long getId() {
        return id;
    }

    /**
     * setter for Column WX_ACCESS_LOG.ID
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * getter for Column WX_ACCESS_LOG.USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * setter for Column WX_ACCESS_LOG.USER_ID
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * getter for Column WX_ACCESS_LOG.PUBLIC_USER_ID
     */
    public String getPublicUserId() {
        return publicUserId;
    }

    /**
     * setter for Column WX_ACCESS_LOG.PUBLIC_USER_ID
     * @param publicUserId
     */
    public void setPublicUserId(String publicUserId) {
        this.publicUserId = publicUserId;
    }

    /**
     * getter for Column WX_ACCESS_LOG.MSG_TYPE
     */
    public String getMsgType() {
        return msgType;
    }

    /**
     * setter for Column WX_ACCESS_LOG.MSG_TYPE
     * @param msgType
     */
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

	/**
     * getter for Column WX_ACCESS_LOG.EVNET_TYPE
     */
    public String getEvnetType() {
        return evnetType;
    }

    /**
     * setter for Column WX_ACCESS_LOG.EVNET_TYPE
     * @param evnetType
     */
    public void setEvnetType(String evnetType) {
        this.evnetType = evnetType;
    }

    /**
     * getter for Column WX_ACCESS_LOG.HAS_RESULT
     */
    
    public Integer getHasResult() {
		return hasResult;
	}

    /**
     * setter for Column WX_ACCESS_LOG.HAS_RESULT
     * @param hasResult
     */
	public void setHasResult(Integer hasResult) {
		this.hasResult = hasResult;
	}

	/**
     * getter for Column WX_ACCESS_LOG.RULE_ID
     */
    public Long getRuleId() {
        return ruleId;
    }

    /**
     * setter for Column WX_ACCESS_LOG.RULE_ID
     * @param ruleId
     */
    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    /**
     * getter for Column WX_ACCESS_LOG.IMG_ID
     */
    public Long getImgId() {
        return imgId;
    }

    /**
     * setter for Column WX_ACCESS_LOG.IMG_ID
     * @param imgId
     */
    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    /**
     * getter for Column WX_ACCESS_LOG.IMG_NAME
     */
    public String getImgName() {
        return imgName;
    }

    /**
     * setter for Column WX_ACCESS_LOG.IMG_NAME
     * @param imgName
     */
    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    /**
     * getter for Column WX_ACCESS_LOG.IMG_URL
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * setter for Column WX_ACCESS_LOG.IMG_URL
     * @param imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * getter for Column WX_ACCESS_LOG.TAG_NAME
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * setter for Column WX_ACCESS_LOG.TAG_NAME
     * @param tagName
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    /**
     * getter for Column WX_ACCESS_LOG.TXT_ID
     */
    public Long getTxtId() {
        return txtId;
    }

    /**
     * setter for Column WX_ACCESS_LOG.TXT_ID
     * @param txtId
     */
    public void setTxtId(Long txtId) {
        this.txtId = txtId;
    }

    /**
     * getter for Column WX_ACCESS_LOG.COST_TIME
     */
    public Integer getCostTime() {
        return costTime;
    }

    /**
     * setter for Column WX_ACCESS_LOG.COST_TIME
     * @param costTime
     */
    public void setCostTime(Integer costTime) {
        this.costTime = costTime;
    }

    /**
     * getter for Column WX_ACCESS_LOG.LOG_TIME
     */
    public Date getLogTime() {
        return logTime;
    }

    /**
     * setter for Column WX_ACCESS_LOG.LOG_TIME
     * @param logTime
     */
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    /**
     * getter for Column WX_ACCESS_LOG.CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * setter for Column WX_ACCESS_LOG.CREATE_TIME
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
