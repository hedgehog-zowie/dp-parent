package com.iuni.dp.persist.datastat.model;

/**
 * 站外推广数据(总表)
 * 
 * @author ZuoChangjun 2013-7-10
 * @version dp-admin-1.0.0
 */
public class MallAdPvuvOrderTotal {

	private Long adId;//广告ID
	private String adName;//广告名
	private String channelName;//渠道名
	private Integer totalPv;//广告对应的总PV
	private Integer totalUv;//广告对应的总UV
	private Integer validOrderNum;//广告对应的总有效订单数
	private Integer totalOrderNum;//广告对应的总订单数
	private Integer backOrderNum;//广告对应的总回退订单数
	
	public Long getAdId() {
		return adId;
	}
	public void setAdId(Long adId) {
		this.adId = adId;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public Integer getTotalPv() {
		return totalPv;
	}
	public void setTotalPv(Integer totalPv) {
		if(totalPv==null){
			this.totalPv = 0;
		}else{
			this.totalPv = totalPv;
		}
	}
	public Integer getTotalUv() {
		return totalUv;
	}
	public void setTotalUv(Integer totalUv) {
		if(totalUv==null){
			this.totalUv = 0;
		}else{
			this.totalUv = totalUv;
		}
	}
	public Integer getValidOrderNum() {
		return validOrderNum;
	}
	public void setValidOrderNum(Integer validOrderNum) {
		if(validOrderNum==null){
			this.validOrderNum = 0;
		}else{
			this.validOrderNum = validOrderNum;
		}
	}
	public Integer getTotalOrderNum() {
		return totalOrderNum;
	}
	public void setTotalOrderNum(Integer totalOrderNum) {
		if(totalOrderNum==null){
			this.totalOrderNum = 0;
		}else{
			this.totalOrderNum = totalOrderNum;
		}
	}
	public Integer getBackOrderNum() {
		return backOrderNum;
	}
	public void setBackOrderNum(Integer backOrderNum) {
		if(backOrderNum==null){
			this.backOrderNum = 0;
		}else{
			this.backOrderNum = backOrderNum;
		}
	}
	
}
