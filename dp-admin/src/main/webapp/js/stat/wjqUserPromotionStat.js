$(function(){
	var flag = $("#flag_h").val();
	var userId = $("#userId_h").val();
	var beginDate = $("#beginDate_h").val() ? $("#beginDate_h").val() : "";
	var endDate = $("#endDate_h").val() ? $("#endDate_h").val() : "";
	var isGroupedByDay = $("#isGroupedByDay_h").val();
	
	var params = "&flag=" + flag + "&statParams['isGroupedByDay']=" + isGroupedByDay + "&statParams['userId']=" + userId 
		+ "&statParams['beginDate']=" + beginDate + "&statParams['endDate']=" + endDate;
	
	actionManager.initLink(params);
	actionManager.initButton(params, isGroupedByDay);
});

var actionManager = {
	initLink : function(params) {
		$.each($("a[name='navi_a']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = basePath+"dp/stat/wjqUserPromotionStatView.action?page.currentPage=" + curPage + params;
			});
		});
	},
	initButton : function(params, isGroupedByDay){
		$("#flag_h").val(null);
		
		if("groupedByDay" == isGroupedByDay) {
			$("#isGroupedByDay").attr("checked","checked");
		}
		
		$("#beginDate").bind("click", function(){
			WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});
		});
		$("#endDate").bind("click", function(){
			WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginDate\',{M:0,d:0,m:1});}'});
		});
		
		$("#btn_query").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/wjqUserPromotionStatView.action");
			$("#queryFrm").submit();
		});
		
		$("#btn_exportExcel").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/wjqUserPromotionStat2Excel.action");
			$("#queryFrm").submit();
		});
		
		$("#btn_preview").click(function(){
			$.post(basePath+"dp/stat/wjqUserPromotionPreviewByJSON.action", function(data){
				$("table.listtable").html('');
				$("div.pagenavi").remove();
				var tbl = $("table.listtable");
				tbl.append('<caption class="cp1">玩机圈用户推广汇总预览</caption>');
				tbl.append('<thead><tr><th>序号</th><th>统计日期</th><th>推广注册</th><th>推广访问</th>' 
						+ '<th>PV</th><th>UV</th><th>IP</th></tr></thead>');
				tbl.append('<tbody>');
				$.each(data, function(index, val){
					var regNum = val.regNum == null ? 0 : val.regNum;
					var visitNum = val.visitNum == null ? 0 : val.visitNum;
					var tbl_ctx = '<tr>' + '<td class="mytd">' + (index+1) + '</td>' 
						+ '<td class="mytd">' + val.bizDate + '</td>'
						+ '<td class="mytd">' + regNum + '</td>'
						+ '<td class="mytd">' + visitNum + '</td>'
						+ '<td class="mytd">' + val.pv + '</td>'
						+ '<td class="mytd">' + val.uv + '</td>'
						+ '<td class="mytd">' + val.ip + '</td>' + '</tr>';
					tbl.append(tbl_ctx);
				});
				tbl.append('</tbody>');
			}, 'json');
		});
		
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			var url_pre = basePath+"dp/stat/wjqUserPromotionStatView.action?page.currentPage=";
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