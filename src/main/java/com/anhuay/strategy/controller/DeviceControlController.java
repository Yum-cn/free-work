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

import com.anhuay.strategy.domain.DeviceControlDO;
import com.anhuay.strategy.service.DeviceControlService;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

/**
 * 设备控制表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-07 11:11:50
 */
 
@Controller
@RequestMapping("/strategy/deviceControl")
public class DeviceControlController {
	@Autowired
	private DeviceControlService deviceControlService;
	
	private String prefix="strategy/deviceControl"  ;
	
	@RequiresPermissions("strategy:deviceControl:deviceControl")
	@GetMapping("")
	String user(Model model) {
		return prefix + "/deviceControl";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("strategy:deviceControl:deviceControl")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DeviceControlDO> deviceControlList = deviceControlService.list(query);
		int total = deviceControlService.count(query);
		PageUtils pageUtils = new PageUtils(deviceControlList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("strategy:deviceControl:add")
	String add(){
	    return "strategy/deviceControl/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("strategy:deviceControl:edit")
	String edit(@PathVariable("id") Long id,Model model){
		DeviceControlDO deviceControl = deviceControlService.get(id);
		model.addAttribute("deviceControl", deviceControl);
	    return "strategy/deviceControl/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("strategy:deviceControl:add")
	public R save( DeviceControlDO deviceControl){
		if(deviceControlService.save(deviceControl)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("strategy:deviceControl:edit")
	public R update( DeviceControlDO deviceControl){
		deviceControlService.update(deviceControl);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("strategy:deviceControl:remove")
	public R remove( Long id){
		if(deviceControlService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("strategy:deviceControl:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		deviceControlService.batchRemove(ids);
		return R.ok();
	}
	
}
