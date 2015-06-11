$(function(){
//	var flag = $("#flag_h").val();
    var beginDate = $("#beginDate_h").val() ? $("#beginDate_h").val() : "";
    var endDate = $("#endDate_h").val() ? $("#endDate_h").val() : "";
    var page1Index = $("#page1Index").val();
    var page2Index = $("#page2Index").val();
    var warehouseCode = $("#warehouseCode").val();

    var params = "&statParams['beginDate']=" + beginDate + "&statParams['endDate']=" + endDate
        + "&warehouseCode" + warehouseCode;

    actionManager.initLink(params, page1Index, page2Index);
    actionManager.initButton(params, page1Index, page2Index);
});

var actionManager = {
    initLink : function(params, page1Index, page2Index) {
        $.each($("a[name='navi_a_1']"), function(index, val){
            $(this).click(function(){
                var curPage = $(this).attr("relval");
                window.location.href = basePath+"dp/stat/iuniWmsStockSourceForWl.action?onePage.currentPage=" + curPage
                    + "&twoPage.currentPage=" + page2Index + params;
            });
        });
        $.each($("a[name='navi_a_2']"), function(index, val){
            $(this).click(function(){
                var curPage = $(this).attr("relval");
                window.location.href = basePath+"dp/stat/iuniWmsStockSourceForWl.action?twoPage.currentPage=" + curPage
                    + "&onePage.currentPage=" + page1Index + params;
            });
        });
    },
    initButton : function(params, page1Index, page2Index){
        $("#flag_h").val(null);

        $("#beginDate").bind("click", function(){
            WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});
        });
        $("#endDate").bind("click", function(){
            WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginDate\',{M:0,d:0,m:1});}'});
        });

        $("#btn_query").click(function(){
            $("#queryFrm").attr("action",basePath+"dp/stat/iuniWmsStockSourceForWl.action");
            $("#queryFrm").submit();
        });

        $("#btn_exportExcel").click(function(){
            $("#queryFrm").attr("action",basePath+"dp/stat/iuniWmsStockSourceForWl2Excel.action");
            $("#queryFrm").submit();
        });

        var totalPage_1 = parseInt($("#totalPage_1").val(), 10);
        $("#goPageBtn_1").click(function(){
            var page = parseInt($("#gotoPage_1").val(), 10);
            var url_pre = basePath+"dp/stat/iuniWmsStockSourceForWl.action?onePage.currentPage=";
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
            var url_pre = basePath+"dp/stat/iuniWmsStockSourceForWl.action?twoPage.currentPage=";
            if(page < 1) {
                window.location.href = url_pre + 1 + "&onePage.currentPage=" + page1Index + params;
            } else if(page > totalPage_2) {
                window.location.href = url_pre + totalPage_2 + "&onePage.currentPage=" + page1Index + params;
            } else {
                window.location.href = url_pre + page + "&onePage.currentPage=" + page1Index + params;
            }
        });
    }
};
