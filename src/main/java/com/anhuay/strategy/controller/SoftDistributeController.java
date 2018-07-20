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

import com.anhuay.strategy.domain.SoftDistributeDO;
import com.anhuay.strategy.service.SoftDistributeService;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

/**
 * 软件分发表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-19 20:25:53
 */
 
@Controller
@RequestMapping("/strategy/softDistribute")
public class SoftDistributeController {
	@Autowired
	private SoftDistributeService softDistributeService;
	
	@GetMapping()
	@RequiresPermissions("strategy:softDistribute:softDistribute")
	String SoftDistribute(){
	    return "strategy/softDistribute/softDistribute";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("strategy:softDistribute:softDistribute")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SoftDistributeDO> softDistributeList = softDistributeService.list(query);
		int total = softDistributeService.count(query);
		PageUtils pageUtils = new PageUtils(softDistributeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("strategy:softDistribute:add")
	String add(){
	    return "strategy/softDistribute/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("strategy:softDistribute:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SoftDistributeDO softDistribute = softDistributeService.get(id);
		model.addAttribute("softDistribute", softDistribute);
	    return "strategy/softDistribute/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("strategy:softDistribute:add")
	public R save( SoftDistributeDO softDistribute){
		if(softDistributeService.save(softDistribute)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("strategy:softDistribute:edit")
	public R update( SoftDistributeDO softDistribute){
		softDistributeService.update(softDistribute);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("strategy:softDistribute:remove")
	public R remove( Long id){
		if(softDistributeService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("strategy:softDistribute:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		softDistributeService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
