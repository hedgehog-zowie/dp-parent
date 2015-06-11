$(function(){
	actionManager.initLink();
	actionManager.initButton();
});

var actionManager = {
	initLink : function() {
//		$.each($("a[name='a_add']"), function(){
//			$(this).click(function(){
//				window.location.href="/alarm/receiverEditView.action";
//			});
//		});
		
		$("a[name='a_update']").each(function(index,val){
			$(this).click(function(){
				var id= $(this).attr("relval");
				var url = "/dp/alarm/receiverEditView.action";
				var param = "?receivePerson.receivePersonId="+id+"&flag=update";
				window.location.href=url + param;
			});
		});
		
		$.each($("a[name='a_remove']"), function(index,val){
			$(this).click(function(){
				var id= $(this).attr("relval");
				var url = "/dp/alarm/removeReceiver.action";
				var param = "?receivePerson.receivePersonId="+id;
				window.location.href=url + param;
			});
		});
		
		$.each($("a[name='navi_a']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = "/dp/alarm/receiverListView.action?page.currentPage=" + curPage;
			});
		});
	},
	initButton : function(){
		$("#btn_add").click(function(){
			window.location.href="/dp/alarm/receiverEditView.action";
		});
		$("#btn_back").click(function(){
			window.location.href = "/dp/alarm/msgTypeListView.action";
		});
		
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			if(page < 1) {
				window.location.href = "/dp/alarm/receiverListView.action?page.currentPage=" + 1;
			} else if(page > totalPage) {
				window.location.href = "/dp/alarm/receiverListView.action?page.currentPage=" + totalPage;
			} else {
				window.location.href = "/dp/alarm/receiverListView.action?page.currentPage=" + page;
			}
		});
	}
};