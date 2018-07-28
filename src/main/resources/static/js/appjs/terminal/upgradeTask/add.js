$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/terminal/upgradeTask/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}


function selectTemplet() {
	// 获取当前窗口名称
	layer.open({
		type : 2,
		title : '选择终端程序升级文件',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/terminal/terminalFile/select' // iframe的url
	});

}

function loadTemplet(id, name) {
	var html = '<option value="' + id + '">' + name + '</option>';
	$("#templet_name_show").html(html);
	$("#templet_name_show option:first").attr("selected", true);
	dealOptions("templet_name_show", "terminalFileId", "terminalFileDownloadUrl");
}

function removeTemplet() {
	$("#templet_name_show option:selected").remove();
	$("#templet_name_show option:first").attr("selected", true);
	dealOptions("templet_name_show", "terminalFileId", "terminalFileDownloadUrl");
}



function selectOsInfo() {
	// 获取当前窗口名称
	layer.open({
		type : 2,
		title : '选择终端程序升级文件',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/os/osInfo/select' // iframe的url
	});

}

function loadOsInfo(id, name) {
	console.log(id);
	console.log(name);
	
	var html = '<option value="' + id + '">' + name + '</option>';
	$("#os_info_show").html(html);
	$("#os_info_show option:first").attr("selected", true);
	dealOptions("os_info_show", "terminalFileId", "terminalFileDownloadUrl");
}

function removeOsInfo() {
	$("#os_info_show option:selected").remove();
	$("#os_info_show option:first").attr("selected", true);
	dealOptions("os_info_show", "terminalFileId", "terminalFileDownloadUrl");
}




function dealOptions(sourceId, targetId, targetName) {

	var allIds = "";
	var allValues = "";
	$("#" + sourceId + " option").each(function() {

		if (allIds != "") {
			allIds += ";";
			allValues += ";";
		}
		allIds += $(this).attr("value");
		allValues += $(this).text();
	});
	$("#" + targetId).val(allIds);
	$("#" + targetName).val(allValues);
}