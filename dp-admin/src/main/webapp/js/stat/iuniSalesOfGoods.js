$(function(){
//	var flag = $("#flag_h").val();
	var beginDate = $("#beginDate_h").val() ? $("#beginDate_h").val() : "";
	var endDate = $("#endDate_h").val() ? $("#endDate_h").val() : "";
	
	var params = "&statParams['beginDate']=" + beginDate + "&statParams['endDate']=" + endDate;
	
	actionManager.initLink(params);
	actionManager.initButton(params);
	
	toggleSkuName();//查询时判断规格型号的隐藏还是显示。
	
	$("#goodsTypeId").change(function(){  
		$("#skuNameCode").empty();  
		var goodsTypeId = document.getElementById("goodsTypeId").value;
		toggleSkuName();
		 ///
	    $.ajax({  
	        type:'post',  
	        url:basePath+"dp/stat/iuniSalesOfGoodsSelect.action" ,
	        data:'goodsType='+goodsTypeId,  
	        dataType:'json',  
	        success:function(json){  
		        if(null != json){  
		         $("#skuNameCode").append("<option value='0'>全选</option>");  
		         for(var i=0; i< json.length;i++){
		           $("#skuNameCode").append("<option value='"+json[i].id+"'>"  +json[i].skuName+"</option>");  
		         }  
		        }   
	        },  
	        error:function(){        
	        }  
	       });///end ajax
	});//
	checkSelectAll();
	 $("#selectAll").bind("click", function () {
		 var orderSourceList = document.getElementsByName("orderSourceId");
         if (this.checked) {
             for (var i = 0; i < orderSourceList.length; i++) {
            	 orderSourceList[i].checked = 1;
             }
         } else {
             for (var j = 0; j < orderSourceList.length; j++) {
            	 orderSourceList[j].checked = 0;
             }
         }
	 });//
     $("input[name='orderSourceId']").bind("click", function () {
         if (this.checked)
             checkSelectAll();
         else
             document.getElementById("selectAll").checked = 0;
     });
	
});


function verify(){
	var checklist = document.getElementsByName("orderSourceId");
	var count = 0;
	for (var i = 0; i < checklist.length; i++) {
        if (checklist[i].checked) {
        	count = 1;
            break;
        }
    }
	if(count==1){
		return true;
	}else{
		return false;
	}
/*	$("orderSourceId input[type=checkbox]").each(function(){
		if(this.checked) return true;	
	});
	return false;*/
}


function toggleSkuName(){//当商品类型为全选，隐藏规格型号
	//var goodsTypeId  = $("goodsTypeId").val();
	var goodsTypeId = document.getElementById("goodsTypeId").value;
	if(goodsTypeId==0){
		 target1=document.getElementById("skuNameCodeDiv1");
		 target1.style.display="none";
		 target2=document.getElementById("skuNameCodeDiv2");
		 target2.style.display="none";
	}else{
		 target1=document.getElementById("skuNameCodeDiv1");
		 target1.style.display="block";
		 target2=document.getElementById("skuNameCodeDiv2");
		 target2.style.display="block";
	}
}
function checkSelectAll(){
    var checklist = document.getElementsByName("orderSourceId");
    var allFlag = 1;
    if(allFlag) {
        for (var i = 0; i < checklist.length; i++) {
            if (!(checklist[i].checked)) {
                allFlag = 0;
                break;
            }
        }
    }
    document.getElementById("selectAll").checked = allFlag;
}
var actionManager = {
	initLink : function(params) {
		$.each($("a[name='navi_a']"), function(index, val){
			$(this).click(function(){
				var curPage = $(this).attr("relval");
				window.location.href = basePath+"dp/stat/iuniSalesOfGoods.action?page.currentPage=" + curPage + params;
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
			if(!verify()){
				alert("请至少选择一项订单来源！");
			}else{
				$("#queryFrm").attr("action",basePath+"dp/stat/iuniSalesOfGoods.action");
				$("#queryFrm").submit();
			}

		});
		
		$("#btn_exportExcel").click(function(){
			if(!verify()){
				alert("请至少选择一项订单来源！");
			}else{
				$("#queryFrm").attr("action",basePath+"dp/stat/iuniSalesOfGoods2Excel.action");
				$("#queryFrm").submit();
			}

		});
		
		var totalPage = parseInt($("#totalPage").val(), 10);
		$("#goPageBtn").click(function(){
			var page = parseInt($("#gotoPage").val(), 10);
			var url_pre = basePath+"dp/stat/iuniSalesOfGoods.action?page.currentPage=";
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