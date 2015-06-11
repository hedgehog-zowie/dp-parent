package com.iuni.dp.admin.datastat.constants;

/**
 * 统计策略
 * @author CaiKe
 * @version V1.0.0
 */
public enum StatStrategy {

	Scheduled(11), Snapshot(12);
	
	private Integer value;

	private StatStrategy(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	
}
