$(function(){
//	var flag = $("#flag_h").val();
	var statDate = $("#statDate_h").val() ? $("#statDate_h").val() : "";
	
	var params = "&statParams['statDate']=" + statDate;
	
	actionManager.initDialog();
	actionManager.initLink(params);
	actionManager.initButton(params);
});

var actionManager = {
	initLink : function(params) {
		$.each($("a[name='navi_a']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = basePath+"dp/stat/iuniNetflowSumTodayStatView.action?page.currentPage=" + curPage + params;
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
		
		$("#statDate").bind("click", function(){
			WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d',maxDate:'%y-%M-%d'});
		});
		
		$("#btn_query").click(function(){
			var statDate = $("#statDate").val() ? $("#statDate").val() : "";
			if(statDate == "") {
				$("#qv_msg").html("统计日期不能为空");
				$("#qv_dialog").dialog("open");
				return false;
			}
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniNetflowSumTodayStatView.action");
			$("#queryFrm").submit();
		});
		
		$("#btn_exportExcel").click(function(){
			var statDate = $("#statDate").val() ? $("#statDate").val() : "";
			if(statDate == "") {
				$("#qv_msg").html("统计日期不能为空");
				$("#qv_dialog").dialog("open");
				return false;
			}
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniNetflowSumTodayStat2Excel.action");
			$("#queryFrm").submit();
		});
		
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			var url_pre = basePath+"dp/stat/iuniNetflowSumTodayStatView.action?page.currentPage=";
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