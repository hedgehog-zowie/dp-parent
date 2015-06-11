$(function(){
	var flag = $("#flag_h").val();
	var channelName = $("#channelName_h").val() ? $("#channelName_h").val() : "";
	var appNames = $("#appNames_h").val() ? $("#appNames_h").val() : "";
	var apkVersions = $("#apkVersions_h").val() ? $("#apkVersions_h").val() : "";
	var remainType = $("#remainType_h").val() ? $("#remainType_h").val() : "";
	var beginDate = $("#beginDate_h").val() ? $("#beginDate_h").val() : "";
	var endDate = $("#endDate_h").val() ? $("#endDate_h").val() : "";
	
	var params = "&statParams['channelName']=" + channelName + "&statParams['appNames']=" + appNames 
		+ "&statParams['apkVersions']=" + apkVersions + "&statParams['remainType']=" + remainType
		+ "&statParams['beginDate']=" + beginDate + "&statParams['endDate']=" + endDate;
	
	actionManager.initDialog();
	actionManager.initLink(params);
	actionManager.initButton(params);
	actionManager.renderChartData(flag,channelName,appNames,apkVersions,remainType,beginDate,endDate);
});

var actionManager = {
	initLink : function(params) {
		$.each($("a[name='navi_a']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = basePath+"dp/stat/iuniosRemainUserOnDayStatView.action?page.currentPage=" + curPage + params;
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
		
		var appName_dialogOpts = {
				autoOpen:false,  
		        title:'机型列表',  
		        width:700,
		        height:500, 
		        modal:true,  
		        buttons: [{
		        	text:"确定",
		        	id:"appNames_ok",
		        	style:"margin-right:90px;width:100px;",
		        	click:function(){
		        		var appNamesArr = $("input:checkbox[name='appNames']:checked");
		        		var appNames = new Array();
		        		var appNamesStr = "";
		        		$.each(appNamesArr, function(key,val){
		        			var arrVal = $(val).val();
		        			appNames.push(arrVal);
		        			if(key < appNamesArr.length-1) {
		        				appNamesStr += arrVal + " , ";
 		        			} else {
 		        				appNamesStr += arrVal;
		        			}
		        		});
		        		$("#apps").attr("value", appNames);
			        	$("#appNames").attr("value", appNamesStr);
			        	$("#apkVersions").attr("value", "");
			        	$("#apkVersionNames").attr("value", "");
			            $(this).dialog('close');
		        	}
		        },{
		        	text:"取消",
		        	id:"appNames_cancel",
		        	style:"margin-right:190px;width:100px;",
		        	click:function(){
		        		$(this).dialog('close');
		        	}
		        }]
			};
				
		$("#appName_dialog").dialog(appName_dialogOpts);
		
		var apkVersion_dialogOpts = {
				autoOpen:false,  
		        title:'OS版本列表',  
		        width:700,
		        height:500, 
		        modal:true,  
		        buttons: [{
		        	text:"确定",
		        	id:"apkVersions_ok",
		        	style:"margin-right:90px;width:100px;",
		        	click:function(){
		        		var apkVersionsArr = $("input:checkbox[name='apkVersions']:checked");
		        		var apkVersions = new Array();
		        		var apkVersionsStr = "";
		        		$.each(apkVersionsArr, function(key,val){
		        			var arrVal = $(val).val();
		        			apkVersions.push(arrVal);
		        			if(key < apkVersionsArr.length-1) {
		        				apkVersionsStr += arrVal + " , ";
 		        			} else {
 		        				apkVersionsStr += arrVal;
		        			}
		        		});
		        		$("#apkVersions").attr("value", apkVersions);
			        	$("#apkVersionNames").attr("value", apkVersionsStr);
			            $(this).dialog('close');
		        	}
		        },{
		        	text:"取消",
		        	id:"apkVersions_cancel",
		        	style:"margin-right:190px;width:100px;",
		        	click:function(){
		        		$(this).dialog('close');
		        	}
		        }]
			};
				
		$("#apkVersion_dialog").dialog(apkVersion_dialogOpts);
	},
	initButton : function(params){
		$("#flag_h").val(null);
		
		$("#beginDate").bind("click", function(){
			WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});
		});
		$("#endDate").bind("click", function(){
			WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginDate\',{M:0,d:0,m:1});}'});
		});
		
		$("#btn_appNames").click(function(){
			var appName_params = "?statParams['channelName']=" + $("#channelName option:selected").val();
			$.getJSON(basePath+"dp/stat/getIuniOSAppNamesByJSON.action" + appName_params, function(data,status,xhr){
				$("#appName_data").empty();
				var strHtml = "";
				var count = 0;
				$.each(data, function(infoIndex, info){
					count++;
					strHtml += "<tr class='ui-widget-content'>";  
					strHtml += "<td class='mytd'>"+count+"</td>";
					strHtml += "<td class='mytd'>"+info['appName']+"</td>";  
					strHtml += "<td class='mytd'> <input type='checkbox' name='appNames' value='"+info["appName"]+"'> </td>";
					strHtml += "</tr>";
				});
				$("#appName_data").html(strHtml);
			});
			$("#appName_dialog").dialog('open');
		});
		
		$("#btn_apkVersions").click(function(){
			var appNames = "";
			if($("#apps").val()) { appNames = $("#apps").val(); }
			var apk_params = "?statParams['appNames']=" + appNames;
			$.getJSON(basePath+"dp/stat/getIuniOSApkVersionsByJSON.action" + apk_params, function(data,status,xhr){
				$("#apkVersion_data").empty();
				var strHtml = "";
				var count = 0;
				$.each(data, function(infoIndex, info){
					count++;
					strHtml += "<tr class='ui-widget-content'>";  
					strHtml += "<td class='mytd'>"+count+"</td>";
					strHtml += "<td class='mytd'>"+info['apkVersion']+"</td>";  
					strHtml += "<td class='mytd'> <input type='checkbox' name='apkVersions' value='"+info["apkVersion"]+"'> </td>";
					strHtml += "</tr>";
				});
				$("#apkVersion_data").html(strHtml);
			});
			$("#apkVersion_dialog").dialog('open');
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
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniosRemainUserOnDayStatView.action");
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
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniosRemainUserOnDayStat2Excel.action");
			$("#queryFrm").submit();
		});
		
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			var url_pre = basePath+"dp/stat/iuniosRemainUserOnDayStatView.action?page.currentPage=";
			if(page < 1) {
				window.location.href = url_pre + 1 + params;
			} else if(page > totalPage) {
				window.location.href = url_pre + totalPage + params;
			} else {
				window.location.href = url_pre + page + params;
			}
		});
	},
	renderChartData : function(flag,channelName,appNames,apkVersions,remainType,beginDate,endDate){
		$.post(basePath+"dp/stat/getiuniosRemainUserOnDayStatByJSON.action", 
			{
				"flag": flag,
				"statParams['channelName']": channelName,
				"statParams['appNames']": appNames,
				"statParams['apkVersions']": apkVersions,
				"statParams['remainType']": remainType,
				"statParams['beginDate']": beginDate,
				"statParams['endDate']": endDate
			}, 
			function(data){
				var scheduledChart = null;
				if(!FusionCharts("snapshotChartId")) {
					scheduledChart = new FusionCharts( basePath+"template/FusionCharts/swf/ZoomLine.swf", 
							"snapshotChartId", "100%", "600", "0" );
					scheduledChart.setChartData(data, "json");
					scheduledChart.render("chartContainer");
				} else {
					scheduledChart = FusionCharts("snapshotChartId");
					scheduledChart.setChartData(data, "json");
				}
			},'json');
	}
};