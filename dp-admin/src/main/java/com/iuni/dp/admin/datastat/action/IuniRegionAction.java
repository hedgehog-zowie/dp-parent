package com.iuni.dp.admin.datastat.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.persist.datastat.model.IuniRegion;
import com.iuni.dp.service.datastat.service.IuniRegionService;

/**
 * IuniRegion Action
 * @author Kenneth.Cai@iuni.com
 * @version dp-admin-1.1.2
 */
@Controller("iuniRegionAction")
@Scope("prototype")
public class IuniRegionAction extends BaseAction {

	private static final long serialVersionUID = 6593666799322426837L;

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IuniRegionService iuniRegionService;
	
	private Map<String, String> statParams = new HashMap<String, String>();
	
	/**
	 * 按JSON格式根据父级获取IUNI Region 
	 */
	public void getIuniRegionByParent() {
		List<IuniRegion> iuniRegionList = null;
		
		//生成查询参数Map
		Map<String, Object> params = genParamMap("iuniOSVersionDistribute");
		
		if (null == params.get("parentId")
				|| !StringUtils.isNumeric((String) params.get("parentId"))) {
			return;
		}
		
		iuniRegionList = iuniRegionService.queryIuniRegionMapByParent(params);
		
		printJson2(iuniRegionList, false, false);
		
		logger.debug("IuniRegionAction.getIuniRegionByParent invoke success.");
	}
	
	/**
	 * 生成查询参数Map
	 * @return Map<String, Object>
	 */
	private Map<String, Object> genParamMap(String condition) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(StringUtils.isNotBlank(statParams.get("parentId"))) {
			params.put("parentId", statParams.get("parentId"));			
		}
		
		return params;
	}

	public Map<String, String> getStatParams() {
		return statParams;
	}

	public void setStatParams(Map<String, String> statParams) {
		this.statParams = statParams;
	}
	
}
