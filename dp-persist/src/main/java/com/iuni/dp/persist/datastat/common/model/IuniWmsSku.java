package com.iuni.dp.persist.datastat.common.model;

import java.util.Date;

/**
 * SKU实体
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public class IuniWmsSku {
    private String id;

    private String waresId;

    private String skuName;

    private String skuCode;

    private String skuBarcode;
    private String itemIds;

    private Date createTime;

    private String remark;

    private String enabled;

    private String materialCode;

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column GIONEE_DRS.IUNI_WMS_SKU.ID
     *
     * @return the value of GIONEE_DRS.IUNI_WMS_SKU.ID
     *
     * @abatorgenerated Thu Nov 20 17:43:15 CST 2014
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column GIONEE_DRS.IUNI_WMS_SKU.ID
     *
     * @param id the value for GIONEE_DRS.IUNI_WMS_SKU.ID
     *
     * @abatorgenerated Thu Nov 20 17:43:15 CST 2014
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column GIONEE_DRS.IUNI_WMS_SKU.WARES_ID
     *
     * @return the value of GIONEE_DRS.IUNI_WMS_SKU.WARES_ID
     *
     * @abatorgenerated Thu Nov 20 17:43:15 CST 2014
     */
    public String getWaresId() {
        return waresId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column GIONEE_DRS.IUNI_WMS_SKU.WARES_ID
     *
     * @param waresId the value for GIONEE_DRS.IUNI_WMS_SKU.WARES_ID
     *
     * @abatorgenerated Thu Nov 20 17:43:15 CST 2014
     */
    public void setWaresId(String waresId) {
        this.waresId = waresId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column GIONEE_DRS.IUNI_WMS_SKU.SKU_NAME
     *
     * @return the value of GIONEE_DRS.IUNI_WMS_SKU.SKU_NAME
     *
     * @abatorgenerated Thu Nov 20 17:43:15 CST 2014
     */
    public String getSkuName() {
        return skuName;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column GIONEE_DRS.IUNI_WMS_SKU.SKU_NAME
     *
     * @param skuName the value for GIONEE_DRS.IUNI_WMS_SKU.SKU_NAME
     *
     * @abatorgenerated Thu Nov 20 17:43:15 CST 2014
     */
    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column GIONEE_DRS.IUNI_WMS_SKU.SKU_CODE
     *
     * @return the value of GIONEE_DRS.IUNI_WMS_SKU.SKU_CODE
     *
     * @abatorgenerated Thu Nov 20 17:43:15 CST 2014
     */
    public String getSkuCode() {
        return skuCode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column GIONEE_DRS.IUNI_WMS_SKU.SKU_CODE
     *
     * @param skuCode the value for GIONEE_DRS.IUNI_WMS_SKU.SKU_CODE
     *
     * @abatorgenerated Thu Nov 20 17:43:15 CST 2014
     */
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column GIONEE_DRS.IUNI_WMS_SKU.SKU_BARCODE
     *
     * @return the value of GIONEE_DRS.IUNI_WMS_SKU.SKU_BARCODE
     *
     * @abatorgenerated Thu Nov 20 17:43:15 CST 2014
     */
    public String getSkuBarcode() {
        return skuBarcode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column GIONEE_DRS.IUNI_WMS_SKU.SKU_BARCODE
     *
     * @param skuBarcode the value for GIONEE_DRS.IUNI_WMS_SKU.SKU_BARCODE
     *
     * @abatorgenerated Thu Nov 20 17:43:15 CST 2014
     */
    public void setSkuBarcode(String skuBarcode) {
        this.skuBarcode = skuBarcode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column GIONEE_DRS.IUNI_WMS_SKU.ITEM_IDS
     *
     * @return the value of GIONEE_DRS.IUNI_WMS_SKU.ITEM_IDS
     *
     * @abatorgenerated Thu Nov 20 17:43:15 CST 2014
     */
    public String getItemIds() {
        return itemIds;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column GIONEE_DRS.IUNI_WMS_SKU.ITEM_IDS
     *
     * @param itemIds the value for GIONEE_DRS.IUNI_WMS_SKU.ITEM_IDS
     *
     * @abatorgenerated Thu Nov 20 17:43:15 CST 2014
     */
    public void setItemIds(String itemIds) {
        this.itemIds = itemIds;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column GIONEE_DRS.IUNI_WMS_SKU.CREATE_TIME
     *
     * @return the value of GIONEE_DRS.IUNI_WMS_SKU.CREATE_TIME
     *
     * @abatorgenerated Thu Nov 20 17:43:15 CST 2014
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column GIONEE_DRS.IUNI_WMS_SKU.CREATE_TIME
     *
     * @param createTime the value for GIONEE_DRS.IUNI_WMS_SKU.CREATE_TIME
     *
     * @abatorgenerated Thu Nov 20 17:43:15 CST 2014
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column GIONEE_DRS.IUNI_WMS_SKU.REMARK
     *
     * @return the value of GIONEE_DRS.IUNI_WMS_SKU.REMARK
     *
     * @abatorgenerated Thu Nov 20 17:43:15 CST 2014
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column GIONEE_DRS.IUNI_WMS_SKU.REMARK
     *
     * @param remark the value for GIONEE_DRS.IUNI_WMS_SKU.REMARK
     *
     * @abatorgenerated Thu Nov 20 17:43:15 CST 2014
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column GIONEE_DRS.IUNI_WMS_SKU.ENABLED
     *
     * @return the value of GIONEE_DRS.IUNI_WMS_SKU.ENABLED
     *
     * @abatorgenerated Thu Nov 20 17:43:15 CST 2014
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column GIONEE_DRS.IUNI_WMS_SKU.ENABLED
     *
     * @param enabled the value for GIONEE_DRS.IUNI_WMS_SKU.ENABLED
     *
     * @abatorgenerated Thu Nov 20 17:43:15 CST 2014
     */
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column GIONEE_DRS.IUNI_WMS_SKU.MATERIAL_CODE
     *
     * @return the value of GIONEE_DRS.IUNI_WMS_SKU.MATERIAL_CODE
     *
     * @abatorgenerated Thu Nov 20 17:43:15 CST 2014
     */
    public String getMaterialCode() {
        return materialCode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column GIONEE_DRS.IUNI_WMS_SKU.MATERIAL_CODE
     *
     * @param materialCode the value for GIONEE_DRS.IUNI_WMS_SKU.MATERIAL_CODE
     *
     * @abatorgenerated Thu Nov 20 17:43:15 CST 2014
     */
    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }
}