package com.anhuay.strategy.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anhuay.strategy.domain.OsAuditDO;
import com.anhuay.strategy.service.OsAuditService;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

/**
 * 主机审计表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-07 19:26:32
 */
 
@Controller
@RequestMapping("/strategy/osAudit")
public class OsAuditController {
	@Autowired
	private OsAuditService osAuditService;
	
	@GetMapping()
	@RequiresPermissions("strategy:osAudit:osAudit")
	String OsAudit(){
	    return "strategy/osAudit/osAudit";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("strategy:osAudit:osAudit")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OsAuditDO> osAuditList = osAuditService.list(query);
		int total = osAuditService.count(query);
		PageUtils pageUtils = new PageUtils(osAuditList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("strategy:osAudit:add")
	String add(){
	    return "strategy/osAudit/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("strategy:osAudit:edit")
	String edit(@PathVariable("id") Long id,Model model){
		OsAuditDO osAudit = osAuditService.get(id);
		model.addAttribute("osAudit", osAudit);
	    return "strategy/osAudit/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("strategy:osAudit:add")
	public R save( OsAuditDO osAudit){
		if(osAuditService.save(osAudit)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("strategy:osAudit:edit")
	public R update( OsAuditDO osAudit){
		osAuditService.update(osAudit);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("strategy:osAudit:remove")
	public R remove( Long id){
		if(osAuditService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("strategy:osAudit:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		osAuditService.batchRemove(ids);
		return R.ok();
	}
	
}
