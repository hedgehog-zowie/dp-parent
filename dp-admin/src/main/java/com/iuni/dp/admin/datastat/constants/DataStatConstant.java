package com.iuni.dp.admin.datastat.constants;

import com.iuni.dp.persist.common.utils.ParseProperties;

/**
 * 数据统计常量 
 * @author CaiKe
 * @version dp-admin-1.0.0
 */
public interface DataStatConstant {

	//微信图片服务器
	public final static String WEIXIN_IMG_URL = ParseProperties.getPropertiesValue("ias.weixin.img.url");
	
}
