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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anhuay.common.annotation.Log;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;
import com.anhuay.strategy.domain.OsAuditDO;
import com.anhuay.strategy.service.OsAuditService;

/**
 * 主机审计表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-15 17:32:57
 */
 
@Controller
@RequestMapping("/strategy/osAudit")
public class OsAuditController {
	@Autowired
	private OsAuditService osAuditService;
	
	@GetMapping()
	@RequiresPermissions(":osAudit:osAudit")
	String OsAudit(){
	    return "/osAudit/osAudit";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions(":osAudit:osAudit")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OsAuditDO> osAuditList = osAuditService.list(query);
		int total = osAuditService.count(query);
		PageUtils pageUtils = new PageUtils(osAuditList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions(":osAudit:add")
	String add(){
	    return "/osAudit/add";
	}
	@Log("编辑主机审计信息")
	@GetMapping("/edit/{id}")
	@RequiresPermissions(":osAudit:edit")
	String edit(@PathVariable("id") Long id,Model model){
		OsAuditDO osAudit = osAuditService.get(id);
		model.addAttribute("osAudit", osAudit);
	    return "/osAudit/edit";
	}
	
	/**
	 * 保存
	 */
	@Log("保存主机审计信息")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions(":osAudit:add")
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
	@RequiresPermissions(":osAudit:edit")
	public R update( OsAuditDO osAudit){
		osAuditService.update(osAudit);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions(":osAudit:remove")
	public R remove( Long id){
		if(osAuditService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions(":osAudit:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		osAuditService.batchUpdateStatus(ids);
		return R.ok();
	}
	
	
	@GetMapping("/add2")
	String addtest(){
	    return "/osAudit/add";
	}
	
}
