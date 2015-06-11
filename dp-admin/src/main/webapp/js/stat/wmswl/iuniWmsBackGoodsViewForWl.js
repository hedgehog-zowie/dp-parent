$(function(){
//	var flag = $("#flag_h").val();
	var beginDate = $("#beginDate_h").val() ? $("#beginDate_h").val() : "";
	var endDate = $("#endDate_h").val() ? $("#endDate_h").val() : "";
	
	var checklistReceive = document.getElementsByName("backChannelOfReceiveCodes");
    var checklistBack = document.getElementsByName("backChannelOfbackCodes");
    var backChannelOfReceiveCodes = new Array();
    for(var i=0 ;i<checklistReceive.length;i++){
    	if(checklistReceive[i].checked){
    		backChannelOfReceiveCodes.push(checklistReceive[i].value);
    	}
    }
    var backChannelOfbackCodes = new Array();
    for(var i=0 ;i<checklistBack.length;i++){
    	if(checklistBack[i].checked){
    		backChannelOfbackCodes.push(checklistBack[i].value);
    	}
    }
	
	var params = "&statParams['beginDate']=" + beginDate + "&statParams['endDate']=" + endDate
					+"&backChannelOfReceiveCodesStr="+backChannelOfReceiveCodes+"&backChannelOfbackCodesStr="+backChannelOfbackCodes;
	
	actionManager.initLink(params);
	actionManager.initButton(params);
	actionManager.initCheckBox();
});

function checkSelectAll(){
    var checkListReceive = document.getElementsByName("backChannelOfReceiveCodes");
    var checkListBack = document.getElementsByName("backChannelOfbackCodes");
    var allFlag = 1;
    if(allFlag) {
        for (var i = 0; i < checkListReceive.length; i++) {
            if (!(checkListReceive[i].checked)) {
                allFlag = 0;
                break;
            }
        }
    }
    if(allFlag){
        for (var i = 0; i < checkListBack.length; i++) {
            if (!(checkListBack[i].checked)) {
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
	            var checkListReceive = document.getElementsByName("backChannelOfReceiveCodes");
	            var checkListBack = document.getElementsByName("backChannelOfbackCodes");
	            if (this.checked) {
	                for (var i = 0; i < checkListReceive.length; i++) {
	                	checkListReceive[i].checked = 1;
	                }
	                for (var i = 0; i < checkListBack.length; i++) {
	                	checkListBack[i].checked = 1;
	                }
	            } else {
	                for (var j = 0; j < checkListReceive.length; j++) {
	                	checkListReceive[j].checked = 0;
	                }
	                for (var j = 0; j < checkListBack.length; j++) {
	                	checkListBack[j].checked = 0;
	                }
	            }
	        });
	        $("input[name='backChannelOfReceiveCodes']").bind("click", function () {
	            if (this.checked)
	                checkSelectAll();
	            else
	                document.getElementById("selectAll").checked = 0;
	        });
	        $("input[name='backChannelOfbackCodes']").bind("click", function () {
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
				window.location.href = basePath+"dp/stat/iuniWmsBackGoodsViewForWl.action?page.currentPage=" + curPage + params;
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
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniWmsBackGoodsViewForWl.action");
			$("#queryFrm").submit();
		});
		
		$("#btn_exportExcel").click(function(){
			$("#queryFrm").attr("action",basePath+"dp/stat/iuniWmsBackGoodsViewForWl2Excel.action");
			$("#queryFrm").submit();
		});
		
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			var url_pre = basePath+"dp/stat/iuniWmsBackGoodsViewForWl.action?page.currentPage=";
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