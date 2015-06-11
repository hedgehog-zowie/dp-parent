$(function(){
	var gdName = $("#gdName").val();
	var pvFrom = $("#pvFrom").val();
	var pvTo = $("#pvTo").val();
	var uvFrom = $("#uvFrom").val();
	var uvTo = $("#uvTo").val();
	var ordNumFrom = $("#ordNumFrom").val();
	var ordNumTo = $("#ordNumTo").val();
	var payNumFrom = $("#payNumFrom").val();
	var payNumTo = $("#payNumTo").val();
	var beginDate = $("#beginDate").val();
	var endDate = $("#endDate").val();
	
	var params = "&statParams['gdName']" + gdName + "&statParams['pvFrom']=" + pvFrom + "&statParams['pvTo']=" + pvTo + "&statParams['uvFrom']=" + uvFrom + "&statParams['uvTo']=" + uvTo 
		+ "&statParams['ordNumFrom']=" + ordNumFrom + "&statParams['ordNumTo']=" + ordNumTo + "&statParams['payNumFrom']=" + payNumFrom 
		+ "&statParams['payNumTo']=" + payNumTo + "&statParams['beginDate']=" + beginDate + "&statParams['endDate']=" + endDate;
	
	actionManager.initLink(params);
	actionManager.initButton(params);
});
var actionManager = {
	initLink : function(params) {
		$.each($("a[name='navi_a']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = basePath+"dp/stat/mallGoodsDailyStatListView.action?page.currentPage=" + curPage + params;
			});
		});
	},
	initButton : function(params){
		$("#btn_query").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/mallGoodsDailyStatListView.action");
			$("#queryFrm").submit();
		});
		
		$("#btn_exportExcel").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/mallGoodsDailyStatList2Excel.action");
			$("#queryFrm").submit();
		});
		
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			if(!page){
				alert("请输入页号!");
				return;
			}
			var url_pre = basePath+"dp/stat/mallGoodsDailyStatListView.action?page.currentPage=";
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