package com.iuni.dp.persist.datastat.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 仓库实体
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public class IuniWmsWarehouse implements Serializable {

    private Long id;

    private String warehouseCode;

    private String warehouseName;

    private String warehouseAddress;

    private String warehousePhone;

    private String warehouseContact;

    private String warehouseType;

    private Date createTime;

    private String defaultStatus;

    private String remark;

    private String enabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public String getWarehousePhone() {
        return warehousePhone;
    }

    public void setWarehousePhone(String warehousePhone) {
        this.warehousePhone = warehousePhone;
    }

    public String getWarehouseContact() {
        return warehouseContact;
    }

    public void setWarehouseContact(String warehouseContact) {
        this.warehouseContact = warehouseContact;
    }

    public String getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(String warehouseType) {
        this.warehouseType = warehouseType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(String defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", warehouseCode='" + warehouseCode + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", warehouseAddress='" + warehouseAddress + '\'' +
                ", warehousePhone='" + warehousePhone + '\'' +
                ", warehouseContact='" + warehouseContact + '\'' +
                ", warehouseType='" + warehouseType + '\'' +
                ", createTime=" + createTime +
                ", defaultStatus='" + defaultStatus + '\'' +
                ", remark='" + remark + '\'' +
                ", enabled='" + enabled + '\'' +
                '}';
    }
}
