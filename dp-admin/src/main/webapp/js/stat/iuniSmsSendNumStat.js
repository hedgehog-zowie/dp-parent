$(function(){
//	var flag = $("#flag_h").val();
	var beginDate = $("#beginDate_h").val() ? $("#beginDate_h").val() : "";
	var endDate = $("#endDate_h").val() ? $("#endDate_h").val() : "";
	
	var params = "&statParams['beginDate']=" + beginDate + "&statParams['endDate']=" + endDate;
	
	actionManager.initDialog();
	actionManager.initLink(params);
	actionManager.initButton(params);
});

var actionManager = {
	initLink : function(params) {
		$.each($("a[name='navi_a']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = basePath+"dp/stat/iuniSmsSendNumStatView.action?page.currentPage=" + curPage + params;
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
	initButton : function(params){
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
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniSmsSendNumStatView.action");
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
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniSmsSendNumStat2Excel.action");
			$("#queryFrm").submit();
		});
		
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			var url_pre = basePath+"dp/stat/iuniSmsSendNumStatView.action?page.currentPage=";
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