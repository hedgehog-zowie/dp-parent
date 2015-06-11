$(function() {
	actionManage.initLink();
	actionManage.initButton();
});

var actionManage = {
	initLink : function() {
		$.each($("a[name='navi_a']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				var curUrl = "/dp/alarm/relReceiverView.action?page.currentPage=" + curPage;
				$("#mainFrm").attr("action", curUrl);
				$("#mainFrm").submit();
			});
		});
	},
	initButton : function() {
		var flag = $("#flag").val();
		$("#btn_ok").click(function() {
			if("update" == flag) {
				$("#mainFrm").attr("action","/dp/alarm/updateMsgType.action");
			} else {
				$("#mainFrm").attr("action","/dp/alarm/addMsgType.action");
			}
			$("#mainFrm").submit();
		});
		$("#btn_pre").click(function(){
			var msgTypeId = $("#msgTypeId").val();
			window.location.href="/dp/alarm/msgTypeEditView.action?msgType.msgTypeId="+msgTypeId+"&flag=update";
		});
		$("#btn_cancel").click(function(){
			window.location.href="/dp/alarm/msgTypeListView.action";
		});
		
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			var curUrl = "/dp/alarm/relReceiverView.action?page.currentPage=";
			if(page < 1) {
				curUrl += 1;
			} else if(page > totalPage) {
				curUrl += totalPage;
			} else {
				curUrl += page;
			}
			$("#mainFrm").attr("action", curUrl);
			$("#mainFrm").submit();
		});
	}

};