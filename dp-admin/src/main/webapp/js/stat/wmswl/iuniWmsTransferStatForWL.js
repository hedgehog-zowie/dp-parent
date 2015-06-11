$(function(){
//	var flag = $("#flag_h").val();
	var beginDate = $("#beginDate_h").val() ? $("#beginDate_h").val() : "";
	var endDate = $("#endDate_h").val() ? $("#endDate_h").val() : "";
	
	
	var params = "&statParams['beginDate']=" + beginDate + "&statParams['endDate']=" + endDate+"&transType="+getCurrTranType();

	actionManager.initLink(params);
	actionManager.initButton(params);
	check(getCurrTranType());
});

function getCurrTranType(){
	var tranId = document.getElementById("transferId");
	var transType = tranId.options[tranId.selectedIndex].value;
	//alert("transType is "+transType);
	return transType;
}

var actionManager = {
	initLink : function(params) {
		
		$.each($("a[name='navi_a_1']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = basePath+"dp/stat/iuniWmsTransferStatViewForWl.action?onePage.currentPage=" + curPage + params;
			});
		});
		
		$.each($("a[name='navi_a_2']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = basePath+"dp/stat/iuniWmsTransferStatViewForWl.action?twoPage.currentPage=" + curPage + params;
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
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniWmsTransferStatViewForWl.action");
			$("#queryFrm").submit();
		});
		
		$("#btn_exportExcel").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniWmsTransferStatViewForWl2Excel.action");
			$("#queryFrm").submit();
		});
	/*	
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			var url_pre = basePath+"dp/stat/iuniWmsTransferStatViewForWl.action?page.currentPage=";
			if(page < 1) {
				window.location.href = url_pre + 1 + params;
			} else if(page > totalPage) {
				window.location.href = url_pre + totalPage + params;
			} else {
				window.location.href = url_pre + page + params;
			}
		});*/
		
		var totalPage_1 = parseInt($("#totalPage_1").val(), 10);
		$("#goPageBtn_1").click(function(){
			var page = parseInt($("#gotoPage_1").val(), 10);
			var url_pre = basePath+"dp/stat/iuniWmsTransferStatViewForWl.action?page.currentPage=";
			if(page < 1) {
				window.location.href = url_pre + 1 + params;
			} else if(page > totalPage_1) {
				window.location.href = url_pre + totalPage_1 + params;
			} else {
				window.location.href = url_pre + page + params;
			}
		});
		
		var totalPage_2 = parseInt($("#totalPage_2").val(), 10);
		$("#goPageBtn_2").click(function(){
			var page = parseInt($("#gotoPage_2").val(), 10);
			var url_pre = basePath+"dp/stat/iuniWmsTransferStatViewForWl.action?page.currentPage=";
			if(page < 1) {
				window.location.href = url_pre + 1 + params;
			} else if(page > totalPage_2) {
				window.location.href = url_pre + totalPage_2 + params;
			} else {
				window.location.href = url_pre + page + params;
			}
		});
	}
	
};

function check(value){
	//transType = value;
	//alert("transType is "+transType);
	if(value=="receive"){
		document.getElementById("transferDiv").style.display="none";
		document.getElementById("receiveDiv").style.display="";
	}else if(value=="transfer"){
		document.getElementById("receiveDiv").style.display="none";
		document.getElementById("transferDiv").style.display="";
	}
	
}