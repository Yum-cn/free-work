package com.anhuay.common.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.aspectj.weaver.tools.cache.AsynchronousFileCacheBacking.RemoveCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anhuay.common.annotation.Log;
import com.anhuay.common.domain.LogDO;
import com.anhuay.common.domain.PageDO;
import com.anhuay.common.service.LogService;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

@RequestMapping("/common/log")
@Controller
public class LogController  extends BaseController{
	@Autowired
	LogService logService;
	String prefix = "common/log";

	@GetMapping()
	String log() {
		return prefix + "/log";
	}

	@ResponseBody
	@GetMapping("/list")
	PageDO<LogDO> list(@RequestParam Map<String, Object> params) {
		
		if(getUserId()==6){
			params.put("userIdArray", new Long[]{11L});
		}else if(getUserId()==11){
			params.put("userIdArray", new Long[]{6L,11L,12L});
		}else if(getUserId()==12){
			params.put("userIdArray", new Long[]{9999999999999L});
		}else if(getUserId()==13){
			
		}
		
		Query query = new Query(params);
		
		/*6	secadm	安全管理员
		11	auditdam	审计管理员
		12	root	系统管理员*/
		
		PageDO<LogDO> page = logService.queryList(query);
		return page;
	}
	@Log("删除操作日志")
	@ResponseBody
	@PostMapping("/remove")
	R remove(Long id) {
		if (logService.remove(id)>0) {
			return R.ok();
		}
		return R.error();
	}

	@ResponseBody
	@PostMapping("/batchRemove")
	R batchRemove(@RequestParam("ids[]") Long[] ids) {
		int r = logService.batchRemove(ids);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}
}
