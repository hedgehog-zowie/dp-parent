$(function(){
	var detailType = $("#detailType").val();
	var bizDate = $("#bizDate").val();
	var adId = $("#adId").val();
	var adName = $("#adName").val();
	var channelName = $("#channelName").val();
	var params = "&detailType="+detailType+"&bizDate="+bizDate+"&adId="+adId+"&adName="+adName+"&channelName="+channelName;
	actionManager.initLink(params);
	actionManager.initButton(params);
});
var actionManager = {
		//分页
	initLink : function(params) {
			$.each($("a[name='navi_a']"), function(index, val){
				$(this).click(function(){
					var curPage = $(this).attr("relval");
					window.location.href = basePath+"dp/stat/queryMallAdPvuvOrderDetails.action?page.currentPage=" + curPage+params;
				});
			});
		},
	initButton : function(params){
		$("#btn_exportExcel").click(function(){
			var detailType=$("#detailType").val();
			if(detailType==1){
				$("#queryFrm").attr("action",basePath+"dp/stat/mallAdPvuvOrderDetailsForTotal2Excel.action");
			}else if(detailType==2 || detailType==3){
				$("#queryFrm").attr("action",basePath+"dp/stat/mallAdPvuvOrderDetailsForDaily2Excel.action");
			}
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
			var url_pre = basePath+"dp/stat/queryMallAdPvuvOrderDetails.action?page.currentPage=";
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