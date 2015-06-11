$(function() {
	actionProcess.initButton();
});

var actionProcess = {
	initButton : function() {
		var flag = $("#flag").val();
		if("update" == flag) {
			$("#mainFrm").attr("action",basePath+"dp/stat/updateStatScheme.action");
		} else {
			$("#mainFrm").attr("action",basePath+"dp/stat/addStatScheme.action");
		}
		
		$("#btn_ok").click(function() {
			$("#mainFrm").submit();
		});
		
		$("#btn_cancel").click(function(){
			window.location.href = basePath+"dp/stat/statSchemeListView.action";
		});
	}
};