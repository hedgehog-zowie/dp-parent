$(function(){
	actionManager.initNaviLink();
	actionManager.initButton();
});

var actionManager = {
	initNaviLink : function(){
		$.each($("a[name='navi_a']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = "/dp/alarm/busLogListView.action?page.currentPage=" + curPage;
			});
		});
	},
	initButton : function(){
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			if(page < 1) {
				window.location.href = "/dp/alarm/busLogListView.action?page.currentPage=" + 1;
			} else if(page > totalPage) {
				window.location.href = "/dp/alarm/busLogListView.action?page.currentPage=" + totalPage;
			} else {
				window.location.href = "/dp/alarm/busLogListView.action?page.currentPage=" + page;
			}
		});
	}
};