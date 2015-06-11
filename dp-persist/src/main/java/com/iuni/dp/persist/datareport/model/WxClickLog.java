/*
 * @(#)WxClickLog.java 2013-8-5
 *
 * Copyright 2013 Shenzhen Gionee,Inc. All rights reserved.
 */
package com.iuni.dp.persist.datareport.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信公众帐号点击日志
 * @author ZuoChangjun 2013-8-5
 * @version dp-persist-1.0.0
 */
public class WxClickLog implements Serializable{


    private static final long serialVersionUID = 137567335792169594L;

    /**
     * column WX_CLICK_LOG.ID
     */
    private Long id;

    /**
     * column WX_CLICK_LOG.USER_ID
     */
    private String userId;

    /**
     * column WX_CLICK_LOG.PUBLIC_USER_ID
     */
    private String publicUserId;

    /**
     * column WX_CLICK_LOG.IMG_ID
     */
    private Long imgId;

    /**
     * column WX_CLICK_LOG.TXT_ID
     */
    private Long txtId;

    /**
     * column WX_CLICK_LOG.LOG_TIME
     */
    private Date logTime;

    /**
     * column WX_CLICK_LOG.CREATE_TIME
     */
    private Date createTime;

    public WxClickLog() {
        super();
    }

    public WxClickLog(Long id, String userId, String publicUserId, Long imgId, Long txtId, Date logTime, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.publicUserId = publicUserId;
        this.imgId = imgId;
        this.txtId = txtId;
        this.logTime = logTime;
        this.createTime = createTime;
    }

    /**
     * getter for Column WX_CLICK_LOG.ID
     */
    public Long getId() {
        return id;
    }

    /**
     * setter for Column WX_CLICK_LOG.ID
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * getter for Column WX_CLICK_LOG.USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * setter for Column WX_CLICK_LOG.USER_ID
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * getter for Column WX_CLICK_LOG.PUBLIC_USER_ID
     */
    public String getPublicUserId() {
        return publicUserId;
    }

    /**
     * setter for Column WX_CLICK_LOG.PUBLIC_USER_ID
     * @param publicUserId
     */
    public void setPublicUserId(String publicUserId) {
        this.publicUserId = publicUserId;
    }

    /**
     * getter for Column WX_CLICK_LOG.IMG_ID
     */
    public Long getImgId() {
        return imgId;
    }

    /**
     * setter for Column WX_CLICK_LOG.IMG_ID
     * @param imgId
     */
    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    /**
     * getter for Column WX_CLICK_LOG.TXT_ID
     */
    public Long getTxtId() {
        return txtId;
    }

    /**
     * setter for Column WX_CLICK_LOG.TXT_ID
     * @param txtId
     */
    public void setTxtId(Long txtId) {
        this.txtId = txtId;
    }

    /**
     * getter for Column WX_CLICK_LOG.LOG_TIME
     */
    public Date getLogTime() {
        return logTime;
    }

    /**
     * setter for Column WX_CLICK_LOG.LOG_TIME
     * @param logTime
     */
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    /**
     * getter for Column WX_CLICK_LOG.CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * setter for Column WX_CLICK_LOG.CREATE_TIME
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}
