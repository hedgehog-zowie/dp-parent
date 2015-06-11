$(function(){
//	var flag = $("#flag_h").val();
	var beginDate = $("#beginDate_h").val() ? $("#beginDate_h").val() : "";
	var endDate = $("#endDate_h").val() ? $("#endDate_h").val() : "";
	var materialCode = $("#materialCodeId").val() ? $("#materialCodeId").val() : "";
	var originalCode = $("#originalCodeId").val() ? $("#originalCodeId").val() : ""; 
	var supplierId = $("#supplierId").val() ? $("#supplierId").val() : "";
	var receiveCode = $("#receiveCode").val() ? $("#receiveCode").val() : "";

	var params = "&statParams['beginDate']=" + beginDate + "&statParams['endDate']=" + endDate
		+"&materialCode="+materialCode+"&originalCode="+originalCode+"&supplierId="+supplierId + "&receiveCode=" + receiveCode;
	
	actionManager.initLink(params);
	actionManager.initButton(params);

	$('#pageSize').change(function(){
		var pageSize = $("#pageSize").val() ? $("#pageSize").val() : 10; 
		params = params + "&page.pageSize="+pageSize;
		window.location.href = basePath+"dp/stat/iuniWmsProcurementDetail.action?"+ params;
	});
});


var actionManager = {
		
	initLink : function(params) {
		$.each($("a[name='navi_a']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = basePath+"dp/stat/iuniWmsProcurementDetail.action?page.currentPage=" + curPage + params;
			});
		});
	},
	initButton : function(params){
		$("#flag_h").val(null);
		
		$("#beginDate").bind("click", function(){
			WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});
		});
		$("#endDate").bind("click", function(){
			WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginDate\',{M:0,d:0,s:1});}'});
		});
		
		$("#btn_query").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniWmsProcurementDetail.action");
			$("#queryFrm").submit();
		});
		
		$("#btn_exportExcel").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniWmsProcurementDetail2Excel.action");
			$("#queryFrm").submit();
		});
		
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			var url_pre = basePath+"dp/stat/iuniWmsProcurementDetail.action?page.currentPage=";
			if(page < 1) {
				window.location.href = url_pre + 1 + params;
			} else if(page > totalPage) {
				window.location.href = url_pre + totalPage + params;
			} else {
				window.location.href = url_pre + page + params;
			}
		});
	}
	
};