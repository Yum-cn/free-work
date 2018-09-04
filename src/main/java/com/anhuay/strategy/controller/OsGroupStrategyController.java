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

import com.anhuay.strategy.domain.OsGroupStrategyDO;
import com.anhuay.strategy.service.OsGroupStrategyService;
import com.common.constant.CommonEnum;
import com.anhuay.common.annotation.Log;
import com.anhuay.common.controller.BaseController;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

/**
 * 主机组策略表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-22 17:07:47
 */
 
@Controller
@RequestMapping("/strategy/osGroupStrategy")
public class OsGroupStrategyController  extends BaseController{
	@Autowired
	private OsGroupStrategyService osGroupStrategyService;
	
	@GetMapping()
	@RequiresPermissions("strategy:osGroupStrategy:osGroupStrategy")
	String OsGroupStrategy(){
	    return "strategy/osGroupStrategy/osGroupStrategy";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("strategy:osGroupStrategy:osGroupStrategy")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OsGroupStrategyDO> osGroupStrategyList = osGroupStrategyService.list(query);
		int total = osGroupStrategyService.count(query);
		PageUtils pageUtils = new PageUtils(osGroupStrategyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("strategy:osGroupStrategy:add")
	String add(){
	    return "strategy/osGroupStrategy/add";
	}
	@Log("编辑主机组策略")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("strategy:osGroupStrategy:edit")
	String edit(@PathVariable("id") Long id,Model model){
		OsGroupStrategyDO osGroupStrategy = osGroupStrategyService.get(id);
		model.addAttribute("osGroupStrategy", osGroupStrategy);
	    return "strategy/osGroupStrategy/add";
	}
	
	/**
	 * 保存
	 */
	@Log("保存主机组策略")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("strategy:osGroupStrategy:add")
	public R save( OsGroupStrategyDO osGroupStrategy){
		
		osGroupStrategy.setUpdateTime(System.currentTimeMillis() / 1000);
		if(osGroupStrategy.getId()!=null && osGroupStrategy.getId()>0){
			if(osGroupStrategyService.update(osGroupStrategy)>0){
				return R.ok();
			}
		}else{
			osGroupStrategy.setStatus(CommonEnum.STATUS.ONE.value);
			osGroupStrategy.setCreateTime(System.currentTimeMillis() / 1000);
			osGroupStrategy.setId(getId());
			if(osGroupStrategyService.save(osGroupStrategy)>0){
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
	@RequiresPermissions("strategy:osGroupStrategy:edit")
	public R update( OsGroupStrategyDO osGroupStrategy){
		osGroupStrategyService.update(osGroupStrategy);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@Log("删除主机组策略")
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("strategy:osGroupStrategy:remove")
	public R remove( Long id){
		if(osGroupStrategyService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("strategy:osGroupStrategy:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		osGroupStrategyService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
