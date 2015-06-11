package com.iuni.dp.service.common.bean;

/**
 * 前端页面分页对象
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public class Page {

	/**
	 * 当前页
	 */
	private Integer currentPage = 0;

	/**
	 * 每页显示的条数
	 */
	private Integer pageSize = 10;

	/**
	 * 总页数
	 */
	private Integer totalPage = 0;

	/**
	 * 总记录数
	 */
	private Integer totalRecord = 0;

	/**
	 * 起始行
	 */
	private Integer startRec = 0;

	/**
	 * 结束行
	 */
	private Integer endRec = 10;
	
	/**
	 * 分页标签连续导航标签数目
	 */
	private Integer naviPage = 5;
	
	public Page() {
		
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	/**
	 * 处理当前页
	 * @return void
	 * @param currentPage
	 */
	public void setCurrentPage(Integer currentPage) {
		if (currentPage > totalPage && totalPage != 0) {
			this.currentPage = totalPage;
		} else if (currentPage < 1) {
			this.currentPage = 1;
		} else {
			this.currentPage = currentPage;
		}
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	/**
	 * 处理总页数
	 * 
	 * @param totalPage
	 */
	public void setTotalPage() {
		if (totalRecord % pageSize != 0) {
			totalPage = totalRecord / pageSize + 1;
		} else {
			totalPage = totalRecord / pageSize;
		}
	}

	public Integer getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}

	public Integer getStartRec() {
		return startRec;
	}

	public void setStartRecForMySql() {
		if (totalPage == 0) {
			startRec = 0;
		} else {
			startRec = (currentPage - 1) * pageSize;
		}
	}
	
    public void setStartRecForOrcl(){
    	if(totalPage == 0){
            startRec = 0;
        }else{
            startRec = (currentPage - 1) * pageSize + 1;
        }
    }

	public Integer getEndRec() {
		return endRec;
	}

	public void setEndRecForMySql() {
		endRec = pageSize;
	}
	
	public void setEndRecForOrcl() {
		if(totalPage <= 1){
            endRec = totalRecord;
        }else if(currentPage == totalPage){
            endRec = totalRecord;
        }else{
            endRec = (startRec + pageSize) - 1;
        }
	}

	public Integer getNaviPage() {
		return naviPage;
	}

	public void setNaviPage(Integer naviPage) {
		this.naviPage = naviPage;
	}

	/**
	 * 根据当前页、总记录数、页大小获得Page
	 * @param currentPage
	 * @param totalRecord
	 * @param pageSize
	 * @return Page
	 */
	public static Page genPage(Integer currentPage, Integer totalRecord,
			Integer pageSize) {
		Page page = new Page();
		// 填充总记录数
		page.setTotalRecord(totalRecord);
		// 填充每页显示的条数
		page.setPageSize(pageSize);
		// 计算总页数
		page.setTotalPage();
		// 填充当前页
		page.setCurrentPage(currentPage);

		// 计算起止行 Mysql
//		page.setStartRecForMySql();
//		page.setEndRecForMySql();
		
		// 计算起止行 Oracle
		page.setStartRecForOrcl();
		page.setEndRecForOrcl();

		return page;
	}

}