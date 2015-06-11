$(function(){
//	var flag = $("#flag_h").val();
	var beginDate = $("#beginDate_h").val() ? $("#beginDate_h").val() : "";
	var endDate = $("#endDate_h").val() ? $("#endDate_h").val() : "";
	var page1Index = $("#page1Index").val();
	var page2Index = $("#page2Index").val();
	var sourceCode = $("#sourceCode").val();
	var params = "&statParams['beginDate']=" + beginDate + "&statParams['endDate']=" + endDate
		+ "&sourceCode=" + sourceCode;
	
	actionManager.initDialog();
	actionManager.initLink(params, page1Index, page2Index);
	actionManager.initButton(params, page1Index, page2Index);
	actionManager.initButton2();
});

var actionManager = {
	initLink : function(params, page1Index, page2Index) {
		$.each($("a[name='navi_a_1']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = basePath+"dp/stat/iuniOrderAreaStatView.action?onePage.currentPage=" + curPage 
					+ "&twoPage.currentPage=" + page2Index + params;
			});
		});
		$.each($("a[name='navi_a_2']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = basePath+"dp/stat/iuniOrderAreaStatView.action?twoPage.currentPage=" + curPage 
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
		$('#submit').on( 'click', function(){

			var ids = Select.getIds();

			$( '#ids' ).html( JSON.stringify( ids ) );

		});
		
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
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniOrderAreaStatView.action");
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
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniOrderAreaStat2Excel.action");
			$("#queryFrm").submit();
		});
		
		var totalPage_1 = parseInt($("#totalPage_1").val(), 10);
		$("#goPageBtn_1").click(function(){
			var page = parseInt($("#gotoPage_1").val(), 10);
			var url_pre = basePath+"dp/stat/iuniOrderAreaStatView.action?onePage.currentPage=";
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
			var url_pre = basePath+"dp/stat/iuniOrderAreaStatView.action?twoPage.currentPage=";
			if(page < 1) {
				window.location.href = url_pre + 1 + "&onePage.currentPage=" + page1Index + params;
			} else if(page > totalPage_2) {
				window.location.href = url_pre + totalPage_2 + "&onePage.currentPage=" + page1Index + params;
			} else {
				window.location.href = url_pre + page + "&onePage.currentPage=" + page1Index + params;
			}
		});
	}, 
	initButton2 : function() {
	
		$("#province").html("<option value=''>选择省/自治区</option>");
		$("#city").html("<option value=''>选择城市/地区/自治州</option>");
		$("#district").html("<option value=''>选择区/县</option>");
		
		// init province
		$.getJSON(basePath+"dp/stat/getIuniRegionByParent.action", {"statParams['parentId']": 1}, function(data){
			console.dir(data);
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
		
				var rid = $("#province")[0].options[$("#province")[0].selectedIndex].getAttribute( 'rid' );
		
				$.getJSON(basePath+"dp/stat/getIuniRegionByParent.action", {"statParams['parentId']": rid}, function(data){
	
					$.each(data, function(i, item){
						console.log('*************');
						console.dir(data);
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
					
					// init city
			
					if(city_h) {
						$("#district").html("<option value=''>选择区/县</option>");
						var rid = $("#city")[0].options[$("#city")[0].selectedIndex].getAttribute( 'rid' );
		
						$.getJSON(basePath+"dp/stat/getIuniRegionByParent.action", {"statParams['parentId']": rid}, function(data){
							$.each(data, function(i, item){
								$("#district").append("<option value="+item.regionName+" rid = "+item.regionId+">"+item.regionName+"</option>");
							});
							
							var district_h = $("#district_h").val();
							
							if(district_h) {
								var d_options=$("#district").find("option");
								var cur_did=-1;
								for(var i=0,len=d_options.length;i<len;i++){
									if(d_options.eq(i).attr("value")==district_h){
										cur_did=i;
										break;
									}
								}
								if(cur_did!==-1){
									d_options.eq(cur_did).attr("selected","selected");
								}
							}
						});
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
		$("#city").bind("change", function(){
			var cityId = $(this).find("option:checked").attr("rid");
			$("#district").html("<option value=''>选择区/县</option>");
			$.getJSON(basePath+"dp/stat/getIuniRegionByParent.action", {"statParams['parentId']": cityId}, function(data){
				$.each(data, function(i, item){
					$("#district").append("<option value="+item.regionName+" rid = "+item.regionId+">"+item.regionName+"</option>");
				});
			});
		});
	}
};