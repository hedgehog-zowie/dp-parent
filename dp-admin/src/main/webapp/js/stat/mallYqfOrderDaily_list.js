$(function(){
	var beginDate = $("#beginDate").val();
	var endDate = $("#endDate").val();
	var cid = $("#cid").val();
	var source = $("#source").val();
	var channel = $("#channel").val();
	var params = "&beginDate="+beginDate+"&endDate="+endDate+"&cid="+cid+"&source="+source+"&beginDate="+channel;
	actionManager.initLink(params);
	actionManager.initButton(params);
});
var actionManager = {
	//分页
	initLink : function(params) {
		$.each($("a[name='navi_a']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = basePath+"dp/stat/queryMallYqfOrderDailyStats.action?page.currentPage=" + curPage+params;
			});
		});
	},
	initButton : function(params){
		$("#btn_query").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/queryMallYqfOrderDailyStats.action");
			$("#queryFrm").submit();
		});
		
		$("#btn_exportExcel").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/mallYqfOrderDailyStats2Excel.action");
			$("#queryFrm").submit();
		});
		//分页
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			if(!page){
				alert("请输入页号!");
				return;
			}
			var url_pre = basePath+"dp/stat/queryMallYqfOrderDailyStats.action?page.currentPage=";
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