$(function(){
//	var flag = $("#flag_h").val();
	var appNames = $("#appNames_h").val() ? $("#appNames_h").val() : "";
	var channelName = $("#channelName_h").val() ? $("#channelName_h").val() : "";
	var beginDate = $("#beginDate_h").val() ? $("#beginDate_h").val() : "";
	var endDate = $("#endDate_h").val() ? $("#endDate_h").val() : "";
	
	var params = "&statParams['appNames']=" + appNames + "&statParams['channelName']=" + channelName 
		+ "&statParams['beginDate']=" + beginDate + "&statParams['endDate']=" + endDate;
	
	actionManager.initDialog();
	actionManager.initLink(params);
	actionManager.initButton(params);
});

var actionManager = {
	initLink : function(params) {
		$.each($("a[name='navi_a']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = basePath+"dp/stat/iuniosTotalUserStatView.action?page.currentPage=" + curPage + params;
			});
		});
	},
	initDialog : function(){
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
		
		$("#btn_query").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniosTotalUserStatView.action");
			$("#queryFrm").submit();
		});
		
		$("#btn_exportExcel").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniosTotalUserStat2Excel.action");
			$("#queryFrm").submit();
		});
		
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			var url_pre = basePath+"dp/stat/iuniosTotalUserStatView.action?page.currentPage=";
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