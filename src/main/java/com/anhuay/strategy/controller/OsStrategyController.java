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

import com.anhuay.strategy.domain.OsStrategyDO;
import com.anhuay.strategy.service.OsStrategyService;
import com.common.constant.CommonEnum;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

/**
 * 主机策略表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-22 17:07:47
 */
 
@Controller
@RequestMapping("/strategy/osStrategy")
public class OsStrategyController {
	@Autowired
	private OsStrategyService osStrategyService;
	
	@GetMapping()
	@RequiresPermissions("strategy:osStrategy:osStrategy")
	String OsStrategy(){
	    return "strategy/osStrategy/osStrategy";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("strategy:osStrategy:osStrategy")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OsStrategyDO> osStrategyList = osStrategyService.list(query);
		int total = osStrategyService.count(query);
		PageUtils pageUtils = new PageUtils(osStrategyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("strategy:osStrategy:add")
	String add(){
	    return "strategy/osStrategy/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("strategy:osStrategy:edit")
	String edit(@PathVariable("id") Long id,Model model){
		OsStrategyDO osStrategy = osStrategyService.get(id);
		model.addAttribute("osStrategy", osStrategy);
	    return "strategy/osStrategy/add";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("strategy:osStrategy:add")
	public R save( OsStrategyDO osStrategy){
		osStrategy.setUpdateTime(System.currentTimeMillis() / 1000);
		if(osStrategy.getId()!=null && osStrategy.getId()>0){
			if(osStrategyService.update(osStrategy)>0){
				return R.ok();
			}
		}else{
			osStrategy.setStatus(CommonEnum.STATUS.ONE.value);
			osStrategy.setCreateTime(System.currentTimeMillis() / 1000);
			
			if(osStrategyService.save(osStrategy)>0){
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
	@RequiresPermissions("strategy:osStrategy:edit")
	public R update( OsStrategyDO osStrategy){
		osStrategyService.update(osStrategy);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("strategy:osStrategy:remove")
	public R remove( Long id){
		if(osStrategyService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("strategy:osStrategy:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		osStrategyService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
