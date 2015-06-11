$(function(){
//	var flag = $("#flag_h").val();
	var beginDate = $("#beginDate_h").val() ? $("#beginDate_h").val() : "";
	var endDate = $("#endDate_h").val() ? $("#endDate_h").val() : "";
	var page1Index = $("#page1Index").val();
	var page2Index = $("#page2Index").val();
	
	var params = "&statParams['beginDate']=" + beginDate + "&statParams['endDate']=" + endDate;
	
	actionManager.initDialog();
	actionManager.initLink(params, page1Index, page2Index);
	actionManager.initButton(params, page1Index, page2Index);
});

var actionManager = {
	initLink : function(params, page1Index, page2Index) {
		$.each($("a[name='navi_a_1']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = basePath+"dp/stat/iuniPageNetflowDailyStatView.action?onePage.currentPage=" + curPage 
					+ "&twoPage.currentPage=" + page2Index + params;
			});
		});
		$.each($("a[name='navi_a_2']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = basePath+"dp/stat/iuniPageNetflowDailyStatView.action?twoPage.currentPage=" + curPage 
					+ "&onePage.currentPage=" + page1Index + params;
			});
		});
	},
	initDialog : function(){
		var qv_dailogOpts = {
				autoOpen:false,  
		        title:'报表查询参数提示',  
		        width:500,
		        height:150, 
		        modal:true,
		        buttons: [{
		        	text:"确定",
		        	id:"qv_ok",
		        	style:"margin-right:200px;width:60px;",
		        	click:function(){
			            $(this).dialog('close');
		        	}
		        }]
		};
		
		$("#qv_dialog").dialog(qv_dailogOpts);
	},
	initButton : function(params, page1Index, page2Index){
		$("#flag_h").val(null);
		
		$("#beginDate").bind("click", function(){
			WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});
		});
		$("#endDate").bind("click", function(){
			WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginDate\',{M:0,d:0,m:1});}'});
		});
		
		$("#btn_query").click(function(){
			var beginDate = $("#beginDate").val() ? $("#beginDate").val() : "";
			var endDate = $("#endDate").val() ? $("#endDate").val() : "";
			if(beginDate == "") {
				$("#qv_msg").html("统计开始日期不能为空");
				$("#qv_dialog").dialog("open");
				return false;
			}
			if(endDate == "") {
				$("#qv_msg").html("统计结束日期不能为空");
				$("#qv_dialog").dialog("open");
				return false;
			}
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniPageNetflowDailyStatView.action");
			$("#queryFrm").submit();
		});
		
		$("#btn_exportExcel").click(function(){
			var beginDate = $("#beginDate").val() ? $("#beginDate").val() : "";
			var endDate = $("#endDate").val() ? $("#endDate").val() : "";
			if(beginDate == "") {
				$("#qv_msg").html("统计开始日期不能为空");
				$("#qv_dialog").dialog("open");
				return false;
			}
			if(endDate == "") {
				$("#qv_msg").html("统计结束日期不能为空");
				$("#qv_dialog").dialog("open");
				return false;
			}
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniPageNetflowDailyStat2Excel.action");
			$("#queryFrm").submit();
		});
		
		var totalPage_1 = parseInt($("#totalPage_1").val(), 10);
		$("#goPageBtn_1").click(function(){
			var page = parseInt($("#gotoPage_1").val(), 10);
			var url_pre = basePath+"dp/stat/iuniPageNetflowDailyStatView.action?onePage.currentPage=";
			if(page < 1) {
				window.location.href = url_pre + 1 + "&twoPage.currentPage=" + page2Index + params;
			} else if(page > totalPage_1) {
				window.location.href = url_pre + totalPage_1 + "&twoPage.currentPage=" + page2Index + params;
			} else {
				window.location.href = url_pre + page + "&twoPage.currentPage=" + page2Index + params;
			}
		});
		
		var totalPage_2 = parseInt($("#totalPage_2").val(), 10);
		$("#goPageBtn_2").click(function(){
			var page = parseInt($("#gotoPage_2").val(), 10);
			var url_pre = basePath+"dp/stat/iuniPageNetflowDailyStatView.action?twoPage.currentPage=";
			if(page < 1) {
				window.location.href = url_pre + 1 + "&onePage.currentPage=" + page1Index + params;
			} else if(page > totalPage_2) {
				window.location.href = url_pre + totalPage_2 + "&onePage.currentPage=" + page1Index + params;
			} else {
				window.location.href = url_pre + page + "&onePage.currentPage=" + page1Index + params;
			}
		});
	}
	
};