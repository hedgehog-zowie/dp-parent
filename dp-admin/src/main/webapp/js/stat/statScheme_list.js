$(function(){
	var code = $("#code").val();
	var name = $("#name").val();
	var status = $("#status").val();
	var statStrategyCode = $("#statStrategyCode").val();
	var rptDataType = $("#rptDataType").val();
	var statField = $("#statField").val();
	var beginDate = $("#beginDate").val();
	var endDate = $("#endDate").val();
	
	var params = "&statSchemeVo.code=" + code + "&statSchemeVo.name=" + name + "&statSchemeVo.status=" + status 
		+ "&statSchemeVo.statStrategyCode=" + statStrategyCode + "&statSchemeVo.rptDataType=" + rptDataType 
		+ "&statSchemeVo.statField=" + statField + "&statSchemeVo.beginDate=" + beginDate + "&statSchemeVo.endDate=" + endDate;
	
	actionManager.initLink(params);
	actionManager.initButton(params);
});

var actionManager = {
	initLink : function(params) {
		$("a[name='a_update']").each(function(index,val){
			$(this).click(function(){
				var id= $(this).attr("relval");
				var url = basePath+"dp/stat/statSchemeEditView.action";
				var param = "?statScheme.id="+id+"&flag=update";
				window.location.href = url + param;
			});
		});
		
		$.each($("a[name='a_remove']"), function(index,val){
			$(this).click(function(){
				var id= $(this).attr("relval");
				var url = basePath+"dp/stat/removeStatScheme.action";
				var param = "?statScheme.id="+id;
				window.location.href = url + param;
			});
		});
		
		$.each($("a[name='navi_a']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = basePath+"dp/stat/statSchemeListView.action?page.currentPage=" + curPage + params;
			});
		});
	},
	initButton : function(params){
		$("#btn_add").click(function(){
			window.location.href = basePath+"dp/stat/statSchemeEditView.action";
		});
		
		$("#btn_query").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/statSchemeListView.action");
			$("#queryFrm").submit();
		});
		
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			if(!page){
				alert("请输入页号!");
				return;
			}
			var url_pre = basePath+"dp/stat/statSchemeListView.action?page.currentPage=";
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