$(function() {
	actionProcess.initButton();
});

var actionProcess = {
	initButton : function() {
		var flag = $("#flag").val();
		if("update" == flag) {
			$("#mainFrm").attr("action","/dp/alarm/updateReceiver.action");
		}
		
		$("#btn_ok").click(function() {
			$("#mainFrm").submit();
//			if ('update' == flag) {
//				var id = $("#id").val();
//				var code = $("#code").val();
//				var name = $("#name").val();
//				var mobile = $("#mobile").val();
//				var email = $("#email").val();
//				var remark = $("#remark").val();
//				var url = "/alarm/updateReceiver.action";
//				$.post(url, {
//					"receivePerson.receivePersonId" : id,
//					"receivePerson.code" : code,
//					"receivePerson.name" : name,
//					"receivePerson.mobile" : mobile,
//					"receivePerson.email" : email,
//					"receivePerson.remark" : remark
//				}, function(result) {
//					window.location.href = "/alarm/receiverListView.action";
//				}, "html");
//			} else {
//				$("#mainFrm").submit();
//			}
		});
		$("#btn_cancel").click(function(){
			window.location.href = "/dp/alarm/receiverListView.action";
		});
	}
	
};