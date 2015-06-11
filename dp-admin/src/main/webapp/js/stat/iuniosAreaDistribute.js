$(function(){
	var flag = $("#flag_h").val();
	var channelName = $("#channelName_h").val() ? $("#channelName_h").val() : "";
	var appNames = $("#appNames_h").val() ? $("#appNames_h").val() : "";
	var apkVersions = $("#apkVersions_h").val() ? $("#apkVersions_h").val() : "";
	var country = $("#country_h").val() ? $("#country_h").val() : "";
	var province = $("#province_h").val() ? $("#province_h").val() : "";
	var city = $("#city_h").val() ? $("#city_h").val() : "";
	var beginDate = $("#beginDate_h").val() ? $("#beginDate_h").val() : "";
	var endDate = $("#endDate_h").val() ? $("#endDate_h").val() : "";
	
	var params = "&statParams['channelName']=" + channelName + "&statParams['appNames']=" + appNames 
		+ "&statParams['apkVersions']=" + apkVersions + "&statParams['country']=" + country 
		+ "&statParams['province']=" + province + "&statParams['city']=" + city
		+ "&statParams['beginDate']=" + beginDate + "&statParams['endDate']=" + endDate;
	
	actionManager.initDialog();
	actionManager.initLink(params);
	actionManager.initButton(params);
	actionManager.initButton2();
	actionManager.renderChartData(flag, channelName, appNames, apkVersions,
			country, province, city, beginDate, endDate);
});

var actionManager = {
	initLink : function(params) {
		$.each($("a[name='navi_a']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = basePath+"dp/stat/iuniosAreaDistributeView.action?page.currentPage=" + curPage + params;
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
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniosAreaDistributeView.action");
			$("#queryFrm").submit();
		});
		
		$("#btn_exportExcel").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniosAreaDistribute2Excel.action");
			$("#queryFrm").submit();
		});
		
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			var url_pre = basePath+"dp/stat/iuniosAreaDistributeView.action?page.currentPage=";
			if(page < 1) {
				window.location.href = url_pre + 1 + params;
			} else if(page > totalPage) {
				window.location.href = url_pre + totalPage + params;
			} else {
				window.location.href = url_pre + page + params;
			}
		});
	},
	initButton2 : function() {
		var country_h = $("#country_h").val();
		if(country_h) {
			$("#country")[0].options[1].setAttribute("selected","selected");
		}
		
		$("#province").html("<option value=''>选择省/自治区</option>");
		$("#city").html("<option value=''>选择城市/地区/自治州</option>");
		
		// init province
		$.getJSON(basePath+"dp/stat/getIuniRegionByParent.action", {"statParams['parentId']": 1}, function(data){
//			console.dir(data);
			$.each(data, function(i, item){
				$("#province").append("<option value="+item.regionName+" rid = "+item.regionId+">"+item.regionName+"</option>");
			});
			
			var province_h = $("#province_h").val();
	
			if(province_h) {
				var p_options=$("#province").find("option");
				var cur_pid=-1;
				for(var i=0,len=p_options.length;i<len;i++){
					if(p_options.eq(i).attr("value")==province_h){
						cur_pid=i;
						break;
					}
				}
				if(cur_pid!==-1){
					p_options.eq(cur_pid).attr("selected","selected");
				}
			}

			// init city
			if(province_h) {
				$("#city").html("<option value=''>选择城市/地区/自治州</option>");
		
				var rid = $("#province")[0].options[$("#province")[0].selectedIndex].getAttribute('rid');
		
				$.getJSON(basePath+"dp/stat/getIuniRegionByParent.action", {"statParams['parentId']": rid}, function(data){
					$.each(data, function(i, item){
//						console.log('*************');
//						console.dir(data);
						$("#city").append("<option value="+item.regionName+" rid = "+item.regionId+">"+item.regionName+"</option>");
					});
					
					var city_h = $("#city_h").val();
					if(city_h) {
						var c_options=$("#city").find("option");
						var cur_cid=-1;
						for(var i=0,len=c_options.length;i<len;i++){
							if(c_options.eq(i).attr("value")==city_h){
								cur_cid=i;
								break;
							}
						}
						if(cur_cid!==-1){
							c_options.eq(cur_cid).attr("selected","selected");
						}
					}
				});
			}
		});
		
		$("#province").bind("change", function(){
			var provinceId = $(this).find("option:checked").attr("rid");
			$("#city").html("<option value=''>选择城市/地区/自治州</option>");
			$.getJSON(basePath+"dp/stat/getIuniRegionByParent.action", {"statParams['parentId']": provinceId}, function(data){
				$.each(data, function(i, item){
					$("#city").append("<option value="+item.regionName+" rid = "+item.regionId+">"+item.regionName+"</option>");
				});
			});
		});
	},
	renderChartData : function(flag,channelName,appNames,apkVersions,country,province,city,beginDate,endDate){
		$.post(basePath+"dp/stat/getIuniosAreaDistributeByJSON.action", 
			{
				"flag": flag,
				"statParams['channelName']": channelName,
				"statParams['appNames']": appNames,
				"statParams['apkVersions']": apkVersions,
				"statParams['country']": country,
				"statParams['province']": province,
				"statParams['city']": city,
				"statParams['beginDate']": beginDate,
				"statParams['endDate']": endDate
			}, 
			function(data){
				var scheduledChart = null;
				if(!FusionCharts("pieChartId")) {
					scheduledChart = new FusionCharts( basePath+"template/FusionCharts/swf/Pie3D.swf", 
							"snapshotChartId", "100%", "450", "0" );
					scheduledChart.setChartData(data, "json");
					scheduledChart.render("chartContainer");
				} else {
					scheduledChart = FusionCharts("pieChartId");
					scheduledChart.setChartData(data, "json");
				}
			},'json');
	}
};