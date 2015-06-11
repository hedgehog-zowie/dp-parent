package com.iuni.dp.persist.datastat.common.model;

/**
 * WMS调拨客户
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public class IuniWmsTransferPartner {

    private String id;
    private String name;
    private String code;
    private String remark;
    private String cParentId;
    private String cIsleaf;
    private String cEnable;
    private String cLevel;
    private String cType;
    private String cLongCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getcParentId() {
        return cParentId;
    }

    public void setcParentId(String cParentId) {
        this.cParentId = cParentId;
    }

    public String getcIsleaf() {
        return cIsleaf;
    }

    public void setcIsleaf(String cIsleaf) {
        this.cIsleaf = cIsleaf;
    }

    public String getcEnable() {
        return cEnable;
    }

    public void setcEnable(String cEnable) {
        this.cEnable = cEnable;
    }

    public String getcLevel() {
        return cLevel;
    }

    public void setcLevel(String cLevel) {
        this.cLevel = cLevel;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public String getcLongCode() {
        return cLongCode;
    }

    public void setcLongCode(String cLongCode) {
        this.cLongCode = cLongCode;
    }
}
