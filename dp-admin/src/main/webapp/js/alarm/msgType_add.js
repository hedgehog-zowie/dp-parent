$(function() {
	actionManager.initButton();

});

var actionManager = {
	initButton : function() {
		$("#btn_next").click(function(){
			$("#mainFrm").submit();
			
		});
		$("#btn_cancel").click(function(){
			window.location.href = "/dp/alarm/msgTypeListView.action";
		});
	}

};