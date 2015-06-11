package com.iuni.dp.persist.datastat.model;

import java.io.Serializable;

/**
 * IuniRegion Model
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.2
 */
public class IuniRegion implements Serializable {

	private static final long serialVersionUID = 7667646412564438662L;

	private Long regionId;
	
	private Long parentId;
	
	private String regionName;
	
	private Integer regionType;

	public IuniRegion() {
		super();
	}

	public IuniRegion(Long regionId, Long parentId, String regionName,
			Integer regionType) {
		super();
		this.regionId = regionId;
		this.parentId = parentId;
		this.regionName = regionName;
		this.regionType = regionType;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getRegionType() {
		return regionType;
	}

	public void setRegionType(Integer regionType) {
		this.regionType = regionType;
	}

	@Override
	public String toString() {
		return "IuniRegion [regionId=" + regionId + ", parentId=" + parentId
				+ ", regionName=" + regionName + ", regionType=" + regionType
				+ "]";
	}
	
}
