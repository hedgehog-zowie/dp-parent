$(function() {
	actionProcess.initButton();
	actionProcess.initDialog();
});

var actionProcess = {
	initButton : function() {
		
		var qv_dailogOpts = {
				autoOpen:false,  
		        title:'定时调度类型统计分析报表查询参数提示',  
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
		
		var renderChartData = function(scheduledTime,statSchemeId,sourceId){
			$.post(basePath+"dp/stat/getScheduledReportByJSON.action", 
				{
					"statScheduledResultVo.scheduledTime": scheduledTime,
					"statScheduledResultVo.statSchemeId" : statSchemeId,
					"statScheduledResultVo.sourceId" : sourceId
				}, 
				function(data){
					var scheduledChart = null;
					if(!FusionCharts("scheduledChartId")) {
						scheduledChart = new FusionCharts( basePath+"template/FusionCharts/swf/Column3D.swf", 
								"scheduledChartId", "100%", "600", "0" );
						scheduledChart.setChartData(data, "json");
						scheduledChart.render("chartContainer");
					} else {
						scheduledChart = FusionCharts("scheduledChartId");
						scheduledChart.setChartData(data, "json");
					}
				},'json');
		};
		
		$("#btn_query").click(function(){
			var scheduledTime = $("#scheduledTime").val();
			var statSchemeId = $("#statSchemeId").val();
			var sourceId = $("#sourceId").val();
			if(statSchemeId == "") {
				$("#qv_msg").html("统计分析计划不能为空");
				$("#qv_dialog").dialog("open");
				return false;
			}
			if(sourceId == "") {
				$("#qv_msg").html("上报数据来源不能为空");
				$("#qv_dialog").dialog("open");
				return false;
			}
			if($.trim(scheduledTime).length == 0) {
				$("#qv_msg").html("统计分析时间不能为空");
				$("#qv_dialog").dialog("open");
				return false;
			}
			
			renderChartData(scheduledTime,statSchemeId,sourceId);
		});
	},
	
	initDialog : function(){
		var ss_dialogOpts = {
			autoOpen:false,  
	        title:'统计分析计划列表',  
	        width:1200,
	        height:500, 
	        modal:true,  
	        buttons: [{
	        	text:"确定",
	        	id:"ss_ok",
	        	style:"margin-right:180px;width:100px;",
	        	click:function(){
	        		$("#statSchemeId").attr("value", $("input:radio[name='id']:checked").val());
		        	var td_typeName = $("input:radio[name='id']:checked").parent().parent().children("td")[2];
		        	$("#statSchemeName").attr("value", $(td_typeName).text());
		            $(this).dialog('close');
	        	}
	        },{
	        	text:"取消",
	        	id:"ss_ok",
	        	style:"margin-right:400px;width:100px;",
	        	click:function(){
	        		$(this).dialog('close');
	        	}
	        }]
		};
		
			
		$("#ss_dialog").dialog(ss_dialogOpts);
		
		$("#btn_statScheme").click(function(){
			$.getJSON(basePath+"dp/stat/getStatSchemeListByJSON.action", function(data,status,xhr){
				$("#ss_data").empty();  
	            var strHTML = ""; 
	            var count = 0;
	            
	            $.each(data,function(InfoIndex,Info){
	            	count++;
	            	var statStyCode = "";
	            	var statSdTime = Info["statScheduledTime"];
	            	var statSpTime = Info["statSnapshotTime"];
	            	var status = Info.status;
	            	
	            	if(Info["statStrategyCode"] == '11') {
	            		statStyCode = "定时调度统计分析类型";
	            	} else if(Info["statStrategyCode"] == '12') {
	            		statStyCode = "间隔快照统计分析类型";
	            	}
	            	
	            	if(null == statSdTime) {
	            		statSdTime = "";
	            	} else {
	            		var statSdTimeDetail = statSdTime.trim().split(" ");
	            		if(null != statSdTimeDetail && statSdTimeDetail.length > 1) {
	            			statSdTime = statSdTimeDetail[1];
	            		}
	            	}
	            	if(null == statSpTime) {
	            		statSpTime = "";
	            	}
	            	
		            if(status == 0) {
		            	status = "未启用 ";
		            } else if(status == 1) {
		            	status = "启用";
		            }
	            	
	                strHTML += "<tr class='ui-widget-content'>";  
	                strHTML += "<td class='mytd'>"+count+"</td>";
	                strHTML += "<td class='mytd'>"+Info["code"]+"</td>";  
	                strHTML += "<td class='mytd'>"+Info["name"]+"</td>";
	                strHTML += "<td class='mytd'>"+statStyCode+"</td>";
	                strHTML += "<td class='mytd'>"+statSdTime+"</td>";
	                strHTML += "<td class='mytd'>"+statSpTime+"</td>";
	                strHTML += "<td class='mytd'>"+Info.rptDataType+"</td>";
	                strHTML += "<td class='mytd'>"+Info.statField+"</td>";
	                strHTML += "<td class='mytd'>"+status+"</td>";
	                strHTML += "<td class='mytd'>"+Info["createTime"]+"</td>";
	                strHTML += "<td class='mytd'>"+Info.creator.user_name+"</td>";
	                strHTML += "<td class='mytd'>"+Info["remark"]+"</td>";
	                strHTML += "<td class='mytd'> <input type='radio' name='id' value='"+Info["id"]+"'> </td>";
	                strHTML += "</tr>";  
	            });  
	            $("#ss_data").html(strHTML);  
			});
			$("#ss_dialog").dialog("open");
		});
		
	}
	
};