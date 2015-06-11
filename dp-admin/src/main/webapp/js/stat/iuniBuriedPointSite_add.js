$(function() {
	actionProcess.initButton();
});

var actionProcess = {
	initButton : function() {
		$("#bizDate").bind("click", function(){
			WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});
		});
		
		var flag = $("#flag").val();
		if("update" == flag) {
			$("#mainFrm").attr("action",basePath+"dp/stat/updateIuniBuriedPoint.action");
		} else {
			$("#mainFrm").attr("action",basePath+"dp/stat/addIuniBuriedPoint.action");
		}
		
		$("#btn_ok").click(function() {
			$("#mainFrm").submit();
		});
		$("#btn_cancel").click(function(){
			window.location.href=basePath+"dp/stat/iuniBuriedPointListView.action";
		});
	}
	
};