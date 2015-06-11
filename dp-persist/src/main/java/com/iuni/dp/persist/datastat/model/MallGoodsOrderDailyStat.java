package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 商城订单商品基础数据日统计Model
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public class MallGoodsOrderDailyStat implements Serializable {

	private static final long serialVersionUID = 2683882532628320594L;

	/**
	 * ID
	 */
	private Integer id;
	
	/**
	 * 商品ID
	 */
	private Integer goodsId;
	
	/**
	 * 商城商品每日订单汇总数
	 */
	private Integer orderNum;
	
	/**
	 * 商城商品每日订单支付汇总数
	 */
	private Integer payNum;
	
	/**
     * 业务数据所属日期
     */
    private String bizDate;

    /**
     * 记录创建时间
     */
    private Timestamp createTime;

	public MallGoodsOrderDailyStat() {
		super();
	}

	public MallGoodsOrderDailyStat(Integer goodsId, Integer orderNum,
			Integer payNum, String bizDate, Timestamp createTime) {
		super();
		this.goodsId = goodsId;
		this.orderNum = orderNum;
		this.payNum = payNum;
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

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getPayNum() {
		return payNum;
	}

	public void setPayNum(Integer payNum) {
		this.payNum = payNum;
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
		return "MallOrdGoodsDailystatBase [goodsId=" + goodsId + ", orderNum="
				+ orderNum + ", payNum=" + payNum + ", bizDate=" + bizDate
				+ ", createTime=" + createTime + "]";
	}
	
}
