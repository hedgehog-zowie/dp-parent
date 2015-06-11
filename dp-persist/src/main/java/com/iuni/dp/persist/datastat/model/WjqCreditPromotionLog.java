package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;

/**
 * 玩机圈推广记录日志
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public class WjqCreditPromotionLog implements Serializable {

	private static final long serialVersionUID = -5688782447170783820L;

	/**
	 * 记录日志主键ID
	 */
	private Long cprid;
	
	/**
	 * 推广人ID
	 */
	private Long uid;
	
	/**
	 * 积分规则ID
	 */
	private Integer rid;
	
	/**
	 * 注册ID
	 */
	private Integer ruid;
	
	/**
	 * IP
	 */
	private String ip;
	
	/**
	 * 
	 */
	private Integer cycleNum;
	
	/**
	 * 积分1
	 */
	private Integer extcredits1;
	
	/**
	 * 积分2
	 */
	private Integer extcredits2;
	
	/**
	 * 积分3
	 */
	private Integer extcredits3;
	
	/**
	 * 积分4
	 */
	private Integer extcredits4;
	
	/**
	 * 积分5
	 */
	private Integer extcredits5;
	
	/**
	 * 积分6
	 */
	private Integer extcredits6;
	
	/**
	 * 积分7
	 */
	private Integer extcredits7;
	
	/**
	 * 积分8
	 */
	private Integer extcredits8;
	
	/**
	 * 记录日志创建时间
	 */
	private Integer dateline;
	
	/**
	 * 注册次数
	 */
	private Integer regtime;
	
	/**
	 * 浏览次数
	 */
	private Integer visitime;

	public WjqCreditPromotionLog() {
		super();
	}

	public Long getCprid() {
		return cprid;
	}

	public void setCprid(Long cprid) {
		this.cprid = cprid;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getRuid() {
		return ruid;
	}

	public void setRuid(Integer ruid) {
		this.ruid = ruid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getCycleNum() {
		return cycleNum;
	}

	public void setCycleNum(Integer cycleNum) {
		this.cycleNum = cycleNum;
	}

	public Integer getExtcredits1() {
		return extcredits1;
	}

	public void setExtcredits1(Integer extcredits1) {
		this.extcredits1 = extcredits1;
	}

	public Integer getExtcredits2() {
		return extcredits2;
	}

	public void setExtcredits2(Integer extcredits2) {
		this.extcredits2 = extcredits2;
	}

	public Integer getExtcredits3() {
		return extcredits3;
	}

	public void setExtcredits3(Integer extcredits3) {
		this.extcredits3 = extcredits3;
	}

	public Integer getExtcredits4() {
		return extcredits4;
	}

	public void setExtcredits4(Integer extcredits4) {
		this.extcredits4 = extcredits4;
	}

	public Integer getExtcredits5() {
		return extcredits5;
	}

	public void setExtcredits5(Integer extcredits5) {
		this.extcredits5 = extcredits5;
	}

	public Integer getExtcredits6() {
		return extcredits6;
	}

	public void setExtcredits6(Integer extcredits6) {
		this.extcredits6 = extcredits6;
	}

	public Integer getExtcredits7() {
		return extcredits7;
	}

	public void setExtcredits7(Integer extcredits7) {
		this.extcredits7 = extcredits7;
	}

	public Integer getExtcredits8() {
		return extcredits8;
	}

	public void setExtcredits8(Integer extcredits8) {
		this.extcredits8 = extcredits8;
	}

	public Integer getDateline() {
		return dateline;
	}

	public void setDateline(Integer dateline) {
		this.dateline = dateline;
	}

	public Integer getRegtime() {
		return regtime;
	}

	public void setRegtime(Integer regtime) {
		this.regtime = regtime;
	}

	public Integer getVisitime() {
		return visitime;
	}

	public void setVisitime(Integer visitime) {
		this.visitime = visitime;
	}

	@Override
	public String toString() {
		return "WjqCreditPromotionLog [uid=" + uid + ", rid=" + rid + ", ruid="
				+ ruid + ", ip=" + ip + ", cycleNum=" + cycleNum
				+ ", extcredits1=" + extcredits1 + ", extcredits2="
				+ extcredits2 + ", extcredits3=" + extcredits3
				+ ", extcredits4=" + extcredits4 + ", extcredits5="
				+ extcredits5 + ", extcredits6=" + extcredits6
				+ ", extcredits7=" + extcredits7 + ", extcredits8="
				+ extcredits8 + ", dateline=" + dateline + ", regtime="
				+ regtime + ", visitime=" + visitime + "]";
	}
	
}
