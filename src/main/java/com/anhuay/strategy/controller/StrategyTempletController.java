package com.anhuay.strategy.controller;

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
@RequestMapping("/strategy/strategyTemplet")
public class StrategyTempletController {
	@Autowired
	private StrategyTempletService strategyTempletService;
	@Autowired
	private OsAuditService osAuditService;
	@Autowired
	private StrategyTempletManager strategyTempletManager;
	
	private String prefix="strategy/strategyTemplet"  ;
	
	@GetMapping("")
	@RequiresPermissions("strategy:strategyTemplet")
	String StrategyTemplet(){
	    return "strategy/strategyTemplet/strategyTemplet";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("strategy:strategyTemplet")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StrategyTempletDO> strategyTempletList = strategyTempletService.list(query);
		int total = strategyTempletService.count(query);
		PageUtils pageUtils = new PageUtils(strategyTempletList, total);
		return pageUtils;
	}
	
	@GetMapping("/toAddStrategyTemplet")
	@RequiresPermissions("strategy:strategyTemplet:add")
	String 	toAddTemplet(){
	    return "strategy/strategyTemplet/addStrategyTemplet";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/addStrategyTemplet")
	@RequiresPermissions("strategy:strategyTemplet:add")
	public Object addTemplet(@RequestBody JSONObject body, StrategyTempletDO strategyTemplet){
		
		BaseResult<Object> result = strategyTempletManager.saveStrategyTemplet(body);
		
		return result;
	}
	
	@GetMapping("/toEditStrategyTemplet/{id}")
	@RequiresPermissions("strategy:strategyTemplet:edit")
	String 	toEditTemplet(@PathVariable("id") Long id,Model model){
		
		StrategyTempletDO strategyTemplet = strategyTempletService.get(id);
		model.addAttribute("strategyTemplet", strategyTemplet);
	    return "strategy/strategyTemplet/addStrategyTemplet";
	}
	
	/**
	 * 编辑
	 */
	@ResponseBody
	@PostMapping("/editStrategyTemplet")
	@RequiresPermissions("strategy:strategyTemplet:edit")
	public Object editTemplet(@RequestBody JSONObject body, StrategyTempletDO strategyTemplet){
		
		BaseResult<Object> result = strategyTempletManager.saveStrategyTemplet(body);
		
		return result;
	}
	
	@GetMapping("/toAddStrategyTempletTab")
	@RequiresPermissions("strategy:strategyTemplet:add")
	String 	toAddStrategyTempletTab(Long strategyTempletId,Model model){
		StrategyTempletDO strategyTemplet = strategyTempletService.get(strategyTempletId);
		
		if(strategyTemplet!=null) {
			
			if(strategyTemplet.getOsAuditId()!=null && strategyTemplet.getOsAuditId()>0) {
				OsAuditDO osAudit = osAuditService.get(strategyTemplet.getOsAuditId());
				model.addAttribute("osAudit", JsonTool.fromObject(osAudit));//JsonTool.fromObject(osAudit)
			}
			
		}
		model.addAttribute("strategyTemplet", strategyTemplet);
		model.addAttribute("test", 123);
	    return "strategy/strategyTemplet/addStrategyTempletTab";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/addStrategyTempletTab")
	@RequiresPermissions("strategy:strategyTemplet:add")
	public Object save(@RequestBody JSONObject body, StrategyTempletDO strategyTemplet){
		
		BaseResult<Object> result = strategyTempletManager.saveStrategyTempletTab(body);
		return result;
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("strategy:strategyTemplet:remove")
	public Object remove( Long id){
		BaseResult<Object> result = strategyTempletManager.removeStrategyTemplet(id);
		return result;
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("strategy:strategyTemplet:batchRemove")
	public Object remove(@RequestParam("ids[]") Long[] ids){
		BaseResult<Object> result = strategyTempletManager.batchRemoveStrategyTemplet(ids);
		return result;
	}
	
}
