package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;
import java.util.Date;

/**
 * IuniWmsDailyStock MODEL
 */
public class IuniWmsDailyStock implements Serializable {

	private static final long serialVersionUID = 6966594285768701467L;

    private String id;

	private String skuCode;

    private Date reportDate;

    private Long startstockqty;

    private Long outstockqty;

    private Long occupystockqty;

    private Long endstockqty;

    private Date createdate;

    private Long startdefeqty;

    private Long startnondefeqty;

    private Long outdefeqty;

    private Long outnondefeqty;

    private Long instockqty;

    private Long indefeqty;

    private Long innondefeqty;

    private Long enddefeqty;

    private Long endnondefeqty;

    private String skuName;

    private String stockId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Long getStartstockqty() {
        return startstockqty;
    }

    public void setStartstockqty(Long startstockqty) {
        this.startstockqty = startstockqty;
    }

    public Long getOutstockqty() {
        return outstockqty;
    }

    public void setOutstockqty(Long outstockqty) {
        this.outstockqty = outstockqty;
    }

    public Long getOccupystockqty() {
        return occupystockqty;
    }

    public void setOccupystockqty(Long occupystockqty) {
        this.occupystockqty = occupystockqty;
    }

    public Long getEndstockqty() {
        return endstockqty;
    }

    public void setEndstockqty(Long endstockqty) {
        this.endstockqty = endstockqty;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Long getStartdefeqty() {
        return startdefeqty;
    }

    public void setStartdefeqty(Long startdefeqty) {
        this.startdefeqty = startdefeqty;
    }

    public Long getStartnondefeqty() {
        return startnondefeqty;
    }

    public void setStartnondefeqty(Long startnondefeqty) {
        this.startnondefeqty = startnondefeqty;
    }

    public Long getOutdefeqty() {
        return outdefeqty;
    }

    public void setOutdefeqty(Long outdefeqty) {
        this.outdefeqty = outdefeqty;
    }

    public Long getOutnondefeqty() {
        return outnondefeqty;
    }

    public void setOutnondefeqty(Long outnondefeqty) {
        this.outnondefeqty = outnondefeqty;
    }

    public Long getInstockqty() {
        return instockqty;
    }

    public void setInstockqty(Long instockqty) {
        this.instockqty = instockqty;
    }

    public Long getIndefeqty() {
        return indefeqty;
    }

    public void setIndefeqty(Long indefeqty) {
        this.indefeqty = indefeqty;
    }

    public Long getInnondefeqty() {
        return innondefeqty;
    }

    public void setInnondefeqty(Long innondefeqty) {
        this.innondefeqty = innondefeqty;
    }

    public Long getEnddefeqty() {
        return enddefeqty;
    }

    public void setEnddefeqty(Long enddefeqty) {
        this.enddefeqty = enddefeqty;
    }

    public Long getEndnondefeqty() {
        return endnondefeqty;
    }

    public void setEndnondefeqty(Long endnondefeqty) {
        this.endnondefeqty = endnondefeqty;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    @Override
    public String toString() {
        return "IuniWmsDailyStock{" +
                "id='" + id + '\'' +
                ", skuCode='" + skuCode + '\'' +
                ", reportDate=" + reportDate +
                ", startstockqty=" + startstockqty +
                ", outstockqty=" + outstockqty +
                ", occupystockqty=" + occupystockqty +
                ", endstockqty=" + endstockqty +
                ", createdate=" + createdate +
                ", startdefeqty=" + startdefeqty +
                ", startnondefeqty=" + startnondefeqty +
                ", outdefeqty=" + outdefeqty +
                ", outnondefeqty=" + outnondefeqty +
                ", instockqty=" + instockqty +
                ", indefeqty=" + indefeqty +
                ", innondefeqty=" + innondefeqty +
                ", enddefeqty=" + enddefeqty +
                ", endnondefeqty=" + endnondefeqty +
                ", skuName='" + skuName + '\'' +
                ", stockId='" + stockId + '\'' +
                '}';
    }
}
