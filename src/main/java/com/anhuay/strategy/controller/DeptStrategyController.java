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

import com.anhuay.strategy.domain.DeptStrategyDO;
import com.anhuay.strategy.service.DeptStrategyService;
import com.common.constant.CommonEnum;
import com.anhuay.common.annotation.Log;
import com.anhuay.common.controller.BaseController;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

/**
 * 部门策略表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-22 17:07:47
 */
 
@Controller
@RequestMapping("/strategy/deptStrategy")
public class DeptStrategyController  extends BaseController{
	@Autowired
	private DeptStrategyService deptStrategyService;
	
	@GetMapping()
	@RequiresPermissions("strategy:deptStrategy:deptStrategy")
	String DeptStrategy(){
	    return "strategy/deptStrategy/deptStrategy";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("strategy:deptStrategy:deptStrategy")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DeptStrategyDO> deptStrategyList = deptStrategyService.list(query);
		int total = deptStrategyService.count(query);
		PageUtils pageUtils = new PageUtils(deptStrategyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("strategy:deptStrategy:add")
	String add(){
	    return "strategy/deptStrategy/add";
	}
	@Log("编辑部门策略")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("strategy:deptStrategy:edit")
	String edit(@PathVariable("id") Long id,Model model){
		DeptStrategyDO deptStrategy = deptStrategyService.get(id);
		model.addAttribute("deptStrategy", deptStrategy);
	    return "strategy/deptStrategy/add";
	}
	
	/**
	 * 保存
	 */
	@Log("保存部门策略")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("strategy:deptStrategy:add")
	public R save( DeptStrategyDO deptStrategy){
		
		deptStrategy.setUpdateTime(System.currentTimeMillis() / 1000);
		if(deptStrategy.getId()!=null && deptStrategy.getId()>0){
			if(deptStrategyService.update(deptStrategy)>0){
				return R.ok();
			}
		}else{
			deptStrategy.setStatus(CommonEnum.STATUS.ONE.value);
			deptStrategy.setCreateTime(System.currentTimeMillis() / 1000);
			deptStrategy.setId(getId());
			if(deptStrategyService.save(deptStrategy)>0){
				return R.ok();
			}
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("strategy:deptStrategy:edit")
	public R update( DeptStrategyDO deptStrategy){
		deptStrategyService.update(deptStrategy);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@Log("删除部门策略")
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("strategy:deptStrategy:remove")
	public R remove( Long id){
		if(deptStrategyService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("strategy:deptStrategy:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		deptStrategyService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
