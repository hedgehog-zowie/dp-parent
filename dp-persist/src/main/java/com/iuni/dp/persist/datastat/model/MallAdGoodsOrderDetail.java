package com.iuni.dp.persist.datastat.model;

/**
 * 站外推广数据(明细表)
 * @author ZuoChangjun 2013-7-10
 * @version dp-admin-1.0.0
 */
public class MallAdGoodsOrderDetail {
	
	private String goodsName;//商品名
	private String goodsSn;//商品编码
	private Integer goodsNum;//商品数量
	private Double goodsPrice;//商品价格
	private Double orderAmount;//实际成交总金额
	private String oredrSn;//订单编号
	private Integer orderStatus;//订单状态
	private String payTime;//支付(结算)日期
	private String bizDate;//业务数据所属日期
	
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsSn() {
		return goodsSn;
	}
	public void setGoodsSn(String goodsSn) {
		this.goodsSn = goodsSn;
	}
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public Double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getOredrSn() {
		return oredrSn;
	}
	public void setOredrSn(String oredrSn) {
		this.oredrSn = oredrSn;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getBizDate() {
		return bizDate;
	}
	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}
}
