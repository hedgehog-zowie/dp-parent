package com.iuni.dp.persist.datastat.model;

import org.apache.commons.lang.StringUtils;

import com.iuni.dp.persist.common.utils.ParseProperties;
import com.iuni.dp.persist.datastat.constants.StatConstant;

/**
 * 微信图片下载排行
 * 
 * @author ZuoChangjun 2013-8-10
 * @version dp-admin-1.0.0
 */
public class WxImageDownloadRank {
	
	private final static String WEIXIN_IMG_URL = ParseProperties.getPropertiesValue(StatConstant.WEIXIN_IMG_URL);
	
	private Long imgId;// 图片ID
	private String imgName;// 图片名
	private String imgUrl;// 图片Url
	private String tagName;// 标签名
	private Integer showCount;// 展示数
	private Integer clickCount;// 点击数
	private String clickRate;// 点击率

	public Long getImgId() {
		return imgId;
	}

	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		if (StringUtils.isBlank(imgUrl)) {
			this.imgUrl = WEIXIN_IMG_URL + "default.jpg";
		} else {
			this.imgUrl = WEIXIN_IMG_URL + imgUrl;
		}
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Integer getShowCount() {
		return showCount;
	}

	public void setShowCount(Integer showCount) {
		this.showCount = showCount;
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public String getClickRate() {
		return clickRate;
	}

	public void setClickRate(String clickRate) {
		this.clickRate = clickRate;
	}

}
