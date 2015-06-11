$(function(){
//	var flag = $("#flag_h").val();
	var beginDate = $("#beginDate_h").val() ? $("#beginDate_h").val() : "";
	var endDate = $("#endDate_h").val() ? $("#endDate_h").val() : "";
	
	var checklistTransfer = document.getElementsByName("channelOfTransferCodes");
    var checklistOrder = document.getElementsByName("channelOfOrderCodes");

    var channelOfTransferCodes = new Array();
    for (var i = 0; i < checklistTransfer.length; i++) {
        if (checklistTransfer[i].checked)
            channelOfTransferCodes.push(checklistTransfer[i].value);
    }

    var channelOfOrderCodes = new Array();
    for (var i = 0; i < checklistOrder.length; i++) {
        if (checklistOrder[i].checked)
            channelOfOrderCodes.push(checklistOrder[i].value);

    }	
	
	var params = "&statParams['beginDate']=" + beginDate + "&statParams['endDate']=" + endDate
				    + "&channelOfTransferCodesStr=" + channelOfTransferCodes
				    + "&channelOfOrderCodesStr=" + channelOfOrderCodes;
	
	actionManager.initLink(params);
	actionManager.initButton(params);
	actionManager.initCheckBox();
});

function checkSelectAll(){
    var checklistTransfer = document.getElementsByName("channelOfTransferCodes");
    var checklistOrder = document.getElementsByName("channelOfOrderCodes");
    var allFlag = 1;
    if(allFlag) {
        for (var i = 0; i < checklistTransfer.length; i++) {
            if (!(checklistTransfer[i].checked)) {
                allFlag = 0;
                break;
            }
        }
    }
    if(allFlag){
        for (var i = 0; i < checklistOrder.length; i++) {
            if (!(checklistOrder[i].checked)) {
                allFlag = 0;
                break;
            }
        }
    }
    document.getElementById("selectAll").checked = allFlag;
}

var actionManager = {
	    initCheckBox: function () {
	        checkSelectAll();
	        $("#selectAll").bind("click", function () {
	            var checklistTransfer = document.getElementsByName("channelOfTransferCodes");
	            var checklistOrder = document.getElementsByName("channelOfOrderCodes");
	            if (this.checked) {
	                for (var i = 0; i < checklistTransfer.length; i++) {
	                    checklistTransfer[i].checked = 1;
	                }
	                for (var i = 0; i < checklistOrder.length; i++) {
	                    checklistOrder[i].checked = 1;
	                }
	            } else {
	                for (var j = 0; j < checklistTransfer.length; j++) {
	                    checklistTransfer[j].checked = 0;
	                }
	                for (var j = 0; j < checklistOrder.length; j++) {
	                    checklistOrder[j].checked = 0;
	                }
	            }
	        });
	        $("input[name='channelOfTransferCodes']").bind("click", function () {
	            if (this.checked)
	                checkSelectAll();
	            else
	                document.getElementById("selectAll").checked = 0;
	        });
	        $("input[name='channelOfOrderCodes']").bind("click", function () {
	            if (this.checked)
	                checkSelectAll();
	            else
	                document.getElementById("selectAll").checked = 0;
	        });
	    },
		
	initLink : function(params) {
		$.each($("a[name='navi_a']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = basePath+"dp/stat/iuniWmsStockMoveViewForWl.action?page.currentPage=" + curPage + params;
			});
		});
	},
	initButton : function(params){
		$("#flag_h").val(null);
		
		$("#beginDate").bind("click", function(){
			WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});
		});
		$("#endDate").bind("click", function(){
			WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginDate\',{M:0,d:0,s:1});}'});
		});
		
		$("#btn_query").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniWmsStockMoveViewForWl.action");
			$("#queryFrm").submit();
		});
		
		$("#btn_exportExcel").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniWmsStockMoveViewForWl2Excel.action");
			$("#queryFrm").submit();
		});
		
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			var url_pre = basePath+"dp/stat/iuniWmsStockMoveViewForWl.action?page.currentPage=";
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