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

import com.anhuay.strategy.domain.AssetManagementDO;
import com.anhuay.strategy.service.AssetManagementService;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

/**
 * 资产管理表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-19 20:25:52
 */
 
@Controller
@RequestMapping("/strategy/assetManagement")
public class AssetManagementController {
	@Autowired
	private AssetManagementService assetManagementService;
	
	@GetMapping()
	@RequiresPermissions("strategy:assetManagement:assetManagement")
	String AssetManagement(){
	    return "strategy/assetManagement/assetManagement";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("strategy:assetManagement:assetManagement")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AssetManagementDO> assetManagementList = assetManagementService.list(query);
		int total = assetManagementService.count(query);
		PageUtils pageUtils = new PageUtils(assetManagementList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("strategy:assetManagement:add")
	String add(){
	    return "strategy/assetManagement/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("strategy:assetManagement:edit")
	String edit(@PathVariable("id") Long id,Model model){
		AssetManagementDO assetManagement = assetManagementService.get(id);
		model.addAttribute("assetManagement", assetManagement);
	    return "strategy/assetManagement/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("strategy:assetManagement:add")
	public R save( AssetManagementDO assetManagement){
		if(assetManagementService.save(assetManagement)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("strategy:assetManagement:edit")
	public R update( AssetManagementDO assetManagement){
		assetManagementService.update(assetManagement);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("strategy:assetManagement:remove")
	public R remove( Long id){
		if(assetManagementService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("strategy:assetManagement:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		assetManagementService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
