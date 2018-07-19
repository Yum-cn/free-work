$().ready(function() {
	loadType();
	initData();
	validateRule();
    //console.log(osAudit);
	$("#temp_addresses").dblclick(function() {
		$("option:selected", this).remove();
		$("#temp_addresses option:first").attr("selected", true);
		dealOptionsValue();
	});

	$("#remove").click(function() {
		$("#temp_addresses option:selected").remove();
		$("#temp_addresses option:first").attr("selected", true);
		dealOptionsValue();
	});
	
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});


function initData() {
	
	//var obj = JSON.parse(osAudit);
	//debugger;
	//console.log(">>>>>>");
	console.log(osAudit);
	
	if(osAudit == null||osAudit == undefined){
		return;
	}
	
	//var processMonitorRules = JSON.parse(osAudit.processMonitorRules);
	console.log(osAudit.id);
	$("#osAuditId").val(osAudit.id);
	var processMonitor = osAudit.processMonitorRules;
	if(processMonitor!=undefined){
		
		if(processMonitor.process_black_status=='1'){
			 $("#process_black_status").attr("checked",true);
		}
		if(processMonitor.process_black_rules!=undefined){
			$("#process_black_rules").val(processMonitor.process_black_rules);
		}
		if(processMonitor.process_white_status=='1'){
			$("#process_white_status").attr("checked",true);
		}
		if(processMonitor.process_white_rules!=undefined){
			$("#process_white_rules").val(processMonitor.process_white_rules);
		}
		if(processMonitor.process_alarm_level!=undefined){
			loadType("process_alarm_level",processMonitor.process_alarm_level);
		}
		//console.log(processMonitorRules.process_black_status);
	}
	var serverMonitor = osAudit.serverMonitorRules;
	console.log(serverMonitor);
	if(serverMonitor!=undefined){
		
		if(serverMonitor.server_black_status=='1'){
			 $("#server_black_status").attr("checked",true);
		}
		if(serverMonitor.server_black_rules!=undefined){
			$("#server_black_rules").val(serverMonitor.server_black_rules);
		}
		if(serverMonitor.server_white_status=='1'){
			$("#server_white_status").attr("checked",true);
		}
		if(serverMonitor.server_white_rules!=undefined){
			$("#server_white_rules").val(serverMonitor.server_white_rules);
		}
		if(serverMonitor.server_connection_black_status=='1'){
			$("#server_connection_black_status").attr("checked",true);
		}
		var addressValue = serverMonitor.server_connection_black_addresses;
		if(addressValue!=undefined&&addressValue!=""){
			
			$("#server_connection_black_addresses").val(addressValue);
			var addressValueArray=addressValue.split(";");
		    for(var i in addressValueArray){
		    	var html = '<option value="' +addressValueArray[i] + '">' + addressValueArray[i] + '</option>';
				$("#temp_addresses").append(html);
		    }
			
		}
		if(serverMonitor.server_alarm_level!=undefined){
			loadType("server_alarm_level",serverMonitor.server_alarm_level);
		}
		//console.log(processMonitorRules.process_black_status);
	}
	
	
}

function addStrategyTempletTab() {
	
	
	var localFileForm = $("#localFileForm").serializeJson();
	var diskFileForm = $("#diskFileForm").serializeJson();
	var udiskFileForm = $("#udiskFileForm").serializeJson();
	var osOnoffForm = $("#osOnoffForm").serializeJson();
	//localFileForm local_file_status local_file_rules
	//diskFileForm disk_file_status disk_file_rules
	//udiskFileForm udisk_file_status udisk_file_rules
	//osOnoffForm os_onoff_status workDay startWorkTime endWorkTime
	var processMonitorForm = $("#processMonitorForm").serializeJson();// 进程监控
	var printMonitorForm = $("#printMonitorForm").serializeJson();// 打印
	var osInfoForm = $("#osInfoForm").serializeJson();// 主机信息
	var systemLogForm = $("#systemLogForm").serializeJson();// 系统日志
	var accountMonitorForm = $("#accountMonitorForm").serializeJson();// 账户监控
	var shareMonitorForm = $("#shareMonitorForm").serializeJson();// 进程监控
	var exceptionMonitorForm = $("#exceptionMonitorForm").serializeJson();// 进程监控
	var osConfigForm = $("#osConfigForm").serializeJson();// 进程监控
	var moveMediaForm = $("#moveMediaForm").serializeJson();// 进程监控
	var portMonitorForm = $("#portMonitorForm").serializeJson();// 进程监控
	//printMonitorForm print_monitor_status
	//osInfoForm os_info_status
	//systemLogForm system_log_status others_system_log_status
	//accountMonitorForm account_monitor_status 
	//shareMonitorForm share_monitor_status share_monitor_type
	//exceptionMonitorForm exception_monitor_status exception_monitor_memory exception_monitor_cpu exception_monitor_runtime
	//osConfigForm os_config_black_status os_config_black_rules os_config_black_alarm_level_selected os_config_black_alarm_level os_config_white_status os_config_white_rules os_config_white_alarm_level
	//moveMediaForm move_media_white_status move_media_input addMoveMedia() move_media_show move_media_names removeMoveMedia
	//portMonitorForm port_monitor_input addPortMonitor
	var serverMonitorForm = $("#serverMonitorForm").serializeJson();// 服务监控
	//connectionMonitorForm connection_black_status connection_black_rules connection_black_alarm_level connection_white_status connection_white_rules connection_white_alarm_level connection_white_status connection_white_addresses
	//networkFlowForm network_flow_status network_flow_size network_flow_alarm_level
	//diskSpaceForm disk_space_status disk_space_utilizationrate disk_space_alarm_level
	
	var extendParamForm = $("#extendParamForm").serializeJson();// 扩展参数
	

	// var jsonString = '{"bar":"property","baz":3}';
	// var jsObject = JSON.parse(proRuestl_1); //转换为json对象
	var jsons = "{" 
			+ "\"processMonitorForm\":" + JSON.stringify(processMonitorForm)
			+ ",\"serverMonitorForm\":" + JSON.stringify(serverMonitorForm) 
			+ ",\"extendParamForm\":" + JSON.stringify(extendParamForm) 
			+ "}"; // 转换为json类型的字符串

	$.ajax({
		cache : true,

		dataType : "json",
		contentType : "application/json; charset=utf-8",
		type : "POST",
		url : "/strategy/strategyTemplet/addStrategyTempletTab",
		data : jsons,// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			//debugger;
			if (data.success == true) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var id = data.data.id;
				$("#osAuditId").val(id);
				//var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				//parent.layer.close(index);
			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}

function saveServerConnectionBlackAddress() {
    
	var ip = $("#server_connection_black_ip").val();
	var ports = $("#server_connection_black_ports").val();
	//debugger;
	
	//校验修改密码表单
	var flag = $("#server_connection_black_ip,#server_connection_black_ports").valid();
	//var flag = $("#signupForm").valid();
	//var flag = $("#signupForm").validate().element($("#server_connection_black_ip"));
    if(!flag){
        //没有通过验证
        return;
    }
	var serverConnectionBlackAddress = ip+":"+ports;
	
    var html = '<option value="' +serverConnectionBlackAddress + '">' + serverConnectionBlackAddress + '</option>';
    $("#temp_addresses").append(html);
    $("#temp_addresses option:first").attr("selected", true);
    dealOptionsValue();
    
}

function dealOptionsValue(){
	
    
    var all = "";
    $("#temp_addresses option").each(function() {
    	
    	if(all!=""){
    		all +=";";
    	}
        all += $(this).attr("value");
    });
    $("#server_connection_black_addresses").val(all);
}





function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#server_connection_black_ip").validate({
		rules : {
			server_connection_black_ip : {
				required : true
			}
		},
		messages : {
			server_connection_black_ip : {
				required : icon + "IP/域名地址"
			}
		}
	})
}

function loadType(idName,checkedValue){
	var html = "";
	$.ajax({
		url : '/common/dict/list/alarm_level',
		success : function(data) {
			
			//debugger;
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			
			if(idName==undefined && checkedValue==undefined){
				$(".chosen-select").append(html);
			}
			$(".chosen-select").chosen({
				maxHeight : 200
			});
			if(idName!=undefined && checkedValue!=undefined){
				$("#"+idName).val(checkedValue);
			}
			$(".chosen-select").trigger("chosen:updated");
			//点击事件
			$('.chosen-select').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}


