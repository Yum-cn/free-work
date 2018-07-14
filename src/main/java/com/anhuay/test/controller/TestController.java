package com.anhuay.test.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;
import com.anhuay.strategy.domain.OsAuditDO;
import com.anhuay.strategy.domain.StrategyTempletDO;
import com.anhuay.strategy.manager.StrategyTempletManager;
import com.anhuay.strategy.service.OsAuditService;
import com.anhuay.strategy.service.StrategyTempletService;
import com.common.util.BaseResult;
import com.common.util.JsonTool;

import net.sf.json.JSONObject;

/**
 * 策略模板表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-07 14:05:30
 */
 
@Controller
@RequestMapping("/test")
public class TestController {
	
	@GetMapping("/sse")
	String StrategyTemplet(){
	    return "test/test";
	}
	
	
}
