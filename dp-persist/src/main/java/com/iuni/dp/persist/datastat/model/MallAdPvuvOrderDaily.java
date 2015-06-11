package com.iuni.dp.persist.datastat.model;

/**
 * 站外推广数据(日表)
 * @author ZuoChangjun 2013-7-10
 * @version dp-admin-1.0.0
 */
public class MallAdPvuvOrderDaily {
	
	private Long adId;//广告ID
	private String adName;//广告名
	private String channelName;//渠道名
	private Integer channelPv;//广告和推广渠道组合对应的总PV
	private Integer channelUv;//广告和推广渠道组合对应的总UV
	private Integer validOrderNum;//广告对应的总有效订单数
	private Integer totalOrderNum;//广告对应的总订单数
	private Integer backOrderNum;//广告对应的总回退订单数
	private String bizDate;//业务数据所属日期
	private String outDate;//出表日期,延时15+7个自然日出表，即当天只能看到22天前的数据，每个月25日结上个月的款项
	
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
	public Integer getChannelPv() {
		return channelPv;
	}
	public void setChannelPv(Integer channelPv) {
		if(channelPv==null){
			this.channelPv = 0;
		}else{
			this.channelPv = channelPv;
		}
	}
	public Integer getChannelUv() {
		return channelUv;
	}
	public void setChannelUv(Integer channelUv) {
		if(channelUv==null){
			this.channelUv = 0;
		}else{
			this.channelUv = channelUv;
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
	public String getBizDate() {
		return bizDate;
	}
	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}
	public String getOutDate() {
		return outDate;
	}
	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}
	
}
