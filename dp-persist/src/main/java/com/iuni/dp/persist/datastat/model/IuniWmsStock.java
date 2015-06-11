package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;

/**
 * IuniWmsStock MODEL
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.5
 */
public class IuniWmsStock implements Serializable {

	private static final long serialVersionUID = 6966594285768701467L;

	private String id;
	
	private String skuId;
	
	private String warehouseId;
	
	private Integer totalQuantity;
	
	private Integer salesQuantity;
	
	private Integer unsalesQuantity;
	
	private Integer occupyQuantity;
	
	private Integer lockQuantity;
	
	private Integer virtualQuantity;
	
	private Integer airQuantity;
	
	private Integer limitUpper;
	
	private Integer limitLower;
	
	private String checkId;
	
	private String checkCode;

	public IuniWmsStock() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Integer getSalesQuantity() {
		return salesQuantity;
	}

	public void setSalesQuantity(Integer salesQuantity) {
		this.salesQuantity = salesQuantity;
	}

	public Integer getUnsalesQuantity() {
		return unsalesQuantity;
	}

	public void setUnsalesQuantity(Integer unsalesQuantity) {
		this.unsalesQuantity = unsalesQuantity;
	}

	public Integer getOccupyQuantity() {
		return occupyQuantity;
	}

	public void setOccupyQuantity(Integer occupyQuantity) {
		this.occupyQuantity = occupyQuantity;
	}

	public Integer getLockQuantity() {
		return lockQuantity;
	}

	public void setLockQuantity(Integer lockQuantity) {
		this.lockQuantity = lockQuantity;
	}

	public Integer getVirtualQuantity() {
		return virtualQuantity;
	}

	public void setVirtualQuantity(Integer virtualQuantity) {
		this.virtualQuantity = virtualQuantity;
	}

	public Integer getAirQuantity() {
		return airQuantity;
	}

	public void setAirQuantity(Integer airQuantity) {
		this.airQuantity = airQuantity;
	}

	public Integer getLimitUpper() {
		return limitUpper;
	}

	public void setLimitUpper(Integer limitUpper) {
		this.limitUpper = limitUpper;
	}

	public Integer getLimitLower() {
		return limitLower;
	}

	public void setLimitLower(Integer limitLower) {
		this.limitLower = limitLower;
	}

	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	@Override
	public String toString() {
		return "IuniWmsStock [skuId=" + skuId + ", warehouseId=" + warehouseId
				+ ", totalQuantity=" + totalQuantity + ", salesQuantity="
				+ salesQuantity + ", unsalesQuantity=" + unsalesQuantity
				+ ", occupyQuantity=" + occupyQuantity + ", lockQuantity="
				+ lockQuantity + ", virtualQuantity=" + virtualQuantity
				+ ", airQuantity=" + airQuantity + ", limitUpper=" + limitUpper
				+ ", limitLower=" + limitLower + ", checkId=" + checkId
				+ ", checkCode=" + checkCode + "]";
	}

}
