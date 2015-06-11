$(function(){
	var website = $("#website_h").val() ? $("#website_h").val() : "";
	var pageName = $("#pageName_h").val() ? $("#pageName_h").val() : "";
	var pagePosition = $("#pagePosition_h").val() ? $("#pagePosition_h").val() : "";
	var pointFlag = $("#pointFlag_h").val() ? $("#pointFlag_h").val() : "";
	var pointType = $("#pointType_h").val() ? $("#pointType_h").val() : "";
	var beginDate = $("#beginDate_h").val() ? $("#beginDate_h").val() : "";
	var endDate = $("#endDate_h").val() ? $("#endDate_h").val() : "";
	
	var params = "&queryParams['website']=" + website + "&queryParams['pageName']=" + pageName 
		+ "&queryParams['pagePosition']=" + pagePosition 
		+ "&queryParams['pointFlag']=" + pointFlag + "&queryParams['pointType']=" + pointType
		+ "&queryParams['beginDate']=" + beginDate + "&queryParams['endDate']=" + endDate;
	
	var currentPage = $("#curPage").val();
	actionManager.initLink(params,currentPage);
	actionManager.initButton(params,currentPage);
});

var actionManager = {
	initLink: function(params,currentPage) {
		$.each($("a[name='a_update']"),function(index,val){
			$(this).click(function(){
				var id = $(this).attr("relval"); 
				window.location.href=basePath+"/dp/stat/mallBuriedPointEditView.action?mallBuriedPointSite.id="+id+"&flag=update"+"&page.currentPage="+currentPage;
			});
		});
		$.each($("a[name='a_remove']"),function(){
			$(this).click(function(){
				var id = $(this).attr("relval"); 
				window.location.href=basePath+"/dp/stat/removeMallBuriedPoint.action?mallBuriedPointSite.id="+id+"&page.currentPage="+currentPage;
			});
		});
		$.each($("a[name='navi_a']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href=basePath+"dp/stat/mallBuriedPointListView.action?page.currentPage="+curPage + params;
			});
		});
	},
	initButton: function(params,currentPage){
		$("#beginDate").bind("click", function(){
			WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});
		});
		$("#endDate").bind("click", function(){
			WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginDate\',{M:0,d:0,m:1});}'});
		});
		
		$("#btn_query").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/mallBuriedPointListView.action");
			$("#queryFrm").submit();
		});
		
		$("#btn_add").click(function(){
			window.location.href=basePath+"dp/stat/mallBuriedPointEditView.action?page.currentPage="+currentPage;
		});
		
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			if(page < 1) {
				window.location.href = basePath+"dp/stat/mallBuriedPointListView.action?page.currentPage=" + 1 + params;
			} else if(page > totalPage) {
				window.location.href = basePath+"dp/stat/mallBuriedPointListView.action?page.currentPage=" + totalPage + params;
			} else {
				window.location.href = basePath+"dp/stat/mallBuriedPointListView.action?page.currentPage=" + page + params;
			}
		});
	}
};