$(function () {
    var beginDate = $("#beginDate_h").val() ? $("#beginDate_h").val() : "";
    var endDate = $("#endDate_h").val() ? $("#endDate_h").val() : "";

    var payers = $("#payers").val();
    var clients = $("#clients").val();
    var phones = $("#phones").val();

    var params = "&statParams['beginDate']=" + beginDate + "&statParams['endDate']=" + endDate
        + "&statParams['payers']=" + payers + "&statParams['clients']=" + clients + "&statParams['phones']=" + phones;

    actionManager.initLink(params);
    actionManager.initButton(params);
});

var actionManager = {
    initLink: function (params) {
        $.each($("a[name='navi_a']"), function (index, val) {
            $(this).click(function () {
                var curPage = $(this).attr("relval");
                window.location.href = basePath + "dp/stat/iuniWmsNotInWarrantyDetail.action?page.currentPage=" + curPage + params;
            });
        });
    },
    initButton: function (params) {
        $("#flag_h").val(null);

        $("#beginDate").bind("click", function () {
            WdatePicker({skin: 'whyGreen', dateFmt: 'yyyy-MM-dd'});
        });
        $("#endDate").bind("click", function () {
            WdatePicker({skin: 'whyGreen', dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'beginDate\',{M:0,d:0,s:1});}'});
        });

        $("#btn_query").click(function () {
            $("#queryFrm").attr("action", basePath + "dp/stat/iuniWmsNotInWarrantyDetail.action");
            $("#queryFrm").submit();
        });

        $("#btn_exportExcel").click(function () {
            $("#queryFrm").attr("action", basePath + "dp/stat/iuniWmsNotInWarrantyDetail2Excel.action");
            $("#queryFrm").submit();
        });

        var totalPage = parseInt($("#totalPage").val(), 10);
        $("#goPageBtn").click(function () {
            var page = parseInt($("#gotoPage").val(), 10);
            var url_pre = basePath + "dp/stat/iuniWmsNotInWarrantyDetail.action?page.currentPage=";
            if (page < 1) {
                window.location.href = url_pre + 1 + params;
            } else if (page > totalPage) {
                window.location.href = url_pre + totalPage + params;
            } else {
                window.location.href = url_pre + page + params;
            }
        });
    }

};
