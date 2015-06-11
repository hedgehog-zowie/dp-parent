$(function(){
	var flag = $("#flag_h").val();
	var statRate = $("#statRate_h").val();
	var beginDate = $("#beginDate_h").val() ? $("#beginDate_h").val() : "";
	var endDate = $("#endDate_h").val() ? $("#endDate_h").val() : "";
	
	var params = "&flag=" + flag + "&statParams['statRate']=" + statRate + "&statParams['beginDate']=" + beginDate 
		+ "&statParams['endDate']=" + endDate;
	
	actionManager.initLink(params);
	actionManager.initButton(params, statRate);
	actionManager.renderChartData(flag,statRate,beginDate,endDate);
});

var actionManager = {
	initLink : function(params) {
		$.each($("a[name='navi_a']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = basePath+"dp/stat/activeUserStatView.action?page.currentPage=" + curPage + params;
			});
		});
	},
	initButton : function(params, statRate){
		$("#flag_h").val(null);
		
		if("byMonth" == statRate) {
			$("#beginDate").bind("click", function(){
				WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'});
			});
			$("#endDate").bind("click", function(){
				WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'beginDate\',{M:0,d:0,m:1});}'});
			});
		} else {
			$("#beginDate").bind("click", function(){
				WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});
			});
			$("#endDate").bind("click", function(){
				WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginDate\',{M:0,d:0,m:1});}'});
			});
		}
		
		$("#statRate").bind("change", function(){
			$("#beginDate").val("");
			$("#endDate").val("");
			if("byMonth" == $(this).val()) {
				$("#beginDate").unbind("click");
				$("#beginDate").bind("click", function(){
					WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'});
				});
				$("#endDate").unbind("click");
				$("#endDate").bind("click", function(){
					WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'beginDate\',{M:0,d:0,m:1});}'});
				});
			} else  {
				$("#beginDate").unbind("click");
				$("#beginDate").bind("click", function(){
					WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});
				});
				$("#endDate").unbind("click");
				$("#endDate").bind("click", function(){
					WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginDate\',{M:0,d:0,m:1});}'});
				});
			}
		});
		
		$("#btn_query").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/activeUserStatView.action");
			$("#queryFrm").submit();
		});
		
		$("#btn_exportExcel").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/wxActiveUserStat2Excel.action");
			$("#queryFrm").submit();
		});
		
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			var url_pre = basePath+"dp/stat/activeUserStatView.action?page.currentPage=";
			if(page < 1) {
				window.location.href = url_pre + 1 + params;
			} else if(page > totalPage) {
				window.location.href = url_pre + totalPage + params;
			} else {
				window.location.href = url_pre + page + params;
			}
		});
	},
	
	renderChartData : function(flag,statRate,beginDate,endDate){
		$.post(basePath+"dp/stat/getWxActiveUserStatByJSON.action", 
			{
				"flag": flag,
				"statParams['statRate']": statRate,
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