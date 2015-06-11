package com.iuni.dp.service.common.bean;

/**
 * 返回的结果对象
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public class Result extends BaseResult {

	private static final long serialVersionUID = -6399582536850454137L;
	
	private Page page = new Page();     //Page页面对象 用于列表分页 
	
	public Result(){
		
	}

	public Result(Object data, String state, String errorCode,
			String errorMessage, Page page) {
		super(data, state, errorCode, errorMessage);
		this.page = page;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "Result [page=" + page + ", state=" + state + ", errorCode="
				+ errorCode + ", errorMessage=" + errorMessage + "]";
	}
	
}
