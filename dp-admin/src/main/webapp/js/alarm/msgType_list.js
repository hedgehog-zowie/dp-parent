$(function(){
	actionManager.initLink();
	actionManager.initButton();
});

var actionManager = {
	initLink: function() {
		$("a[name='a_receivers']").each(function(index,val){
			$(this).click(function(){
				var msgTypeId = $(this).attr("relval");
				window.location.href="/dp/alarm/receiverListView.action?msgTypeId="+msgTypeId+"&flag=view";
			});
		});
//		$.each($("a[name='a_add']"), function(){
//			$(this).click(function(){
//				window.location.href="/alarm/msgTypeEditView.action";
//			});
//		});
		$.each($("a[name='a_update']"),function(index,val){
			$(this).click(function(){
				var msgTypeId = $(this).attr("relval"); 
				window.location.href="/dp/alarm/msgTypeEditView.action?msgType.msgTypeId="+msgTypeId+"&flag=update";
			});
		});
		$.each($("a[name='a_remove']"),function(){
			$(this).click(function(){
				var msgTypeId = $(this).attr("relval"); 
				window.location.href="/dp/alarm/removeMsgType.action?msgType.msgTypeId="+msgTypeId;
			});
		});
		$.each($("a[name='navi_a']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = "/dp/alarm/msgTypeListView.action?page.currentPage=" + curPage;
			});
		});
	},
	initButton: function(){
		$("#btn_add").click(function(){
			window.location.href="/dp/alarm/msgTypeEditView.action";
		});
		
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			if(page < 1) {
				window.location.href = "/dp/alarm/msgTypeListView.action?page.currentPage=" + 1;
			} else if(page > totalPage) {
				window.location.href = "/dp/alarm/msgTypeListView.action?page.currentPage=" + totalPage;
			} else {
				window.location.href = "/dp/alarm/msgTypeListView.action?page.currentPage=" + page;
			}
		});
	}
};