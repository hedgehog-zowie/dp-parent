package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 商城商品PV/UV基础数据日统计Model
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public class MallGoodsPvuvDailyStat implements Serializable {

	private static final long serialVersionUID = -8383000380730715304L;

	/**
	 * ID
	 */
	private Integer id;
	
	/**
     * 商品ID
     */
    private Integer goodsId;

    /**
     * 商品每日PV汇总数
     */
    private Integer pv;

    /**
     * 商品每日UV汇总数
     */
    private Integer uv;
    
    /**
     * 商品每日独立IP汇总数
     */
    private Integer ip;

    /**
     * 商品业务数据所属日期
     */
    private String bizDate;

    /**
     * 记录创建时间
     */
    private Timestamp createTime;

	public MallGoodsPvuvDailyStat() {
		super();
	}

	public MallGoodsPvuvDailyStat(Integer goodsId, Integer pv, Integer uv,
			Integer ip, String bizDate, Timestamp createTime) {
		super();
		this.goodsId = goodsId;
		this.pv = pv;
		this.uv = uv;
		this.ip = ip;
		this.bizDate = bizDate;
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getPv() {
		return pv;
	}

	public void setPv(Integer pv) {
		this.pv = pv;
	}

	public Integer getUv() {
		return uv;
	}

	public void setUv(Integer uv) {
		this.uv = uv;
	}

	public Integer getIp() {
		return ip;
	}

	public void setIp(Integer ip) {
		this.ip = ip;
	}

	public String getBizDate() {
		return bizDate;
	}

	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "MallGoodsPvuvDailyStat [goodsId=" + goodsId + ", pv=" + pv
				+ ", uv=" + uv + ", ip=" + ip + ", bizDate=" + bizDate
				+ ", createTime=" + createTime + "]";
	}

}
