$(function(){
	var flag = $("#flag_h").val();
	var beginDate = $("#beginDate_h").val() ? $("#beginDate_h").val() : "";
	var endDate = $("#endDate_h").val() ? $("#endDate_h").val() : "";
	
	var params = "&flag=" + flag + "&statParams['beginDate']=" + beginDate + "&statParams['endDate']=" + endDate;
	
	actionManager.initLink(params);
	actionManager.initButton(params);
	actionManager.renderChartData(flag,beginDate,endDate);
});

var actionManager = {
	initLink : function(params) {
		$.each($("a[name='navi_a']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = basePath+"dp/stat/newFansStatView.action?page.currentPage=" + curPage + params;
			});
		});
	},
	initButton : function(params){
		$("#flag_h").val(null);
		
		$("#beginDate").bind("click", function(){
			WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});
		});
		$("#endDate").bind("click", function(){
			WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginDate\',{M:0,d:0,m:1});}'});
		});
		
		$("#btn_query").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/newFansStatView.action");
			$("#queryFrm").submit();
		});
		
		$("#btn_exportExcel").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/wxNewFansStat2Excel.action");
			$("#queryFrm").submit();
		});
		
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			var url_pre = basePath+"dp/stat/newFansStatView.action?page.currentPage=";
			if(page < 1) {
				window.location.href = url_pre + 1 + params;
			} else if(page > totalPage) {
				window.location.href = url_pre + totalPage + params;
			} else {
				window.location.href = url_pre + page + params;
			}
		});
	},
	
	renderChartData : function(flag,beginDate,endDate){
		$.post(basePath+"dp/stat/getWxNewFansStatByJSON.action", 
			{
				"flag": flag,
				"statParams['beginDate']": beginDate,
				"statParams['endDate']": endDate
			}, 
			function(data){
				var scheduledChart = null;
				if(!FusionCharts("snapshotChartId")) {
					scheduledChart = new FusionCharts( basePath+"template/FusionCharts/swf/ZoomLine.swf", 
							"snapshotChartId", "100%", "600", "0" );
					scheduledChart.setChartData(data, "json");
					scheduledChart.render("chartContainer");
				} else {
					scheduledChart = FusionCharts("snapshotChartId");
					scheduledChart.setChartData(data, "json");
				}
			},'json');
	}
};