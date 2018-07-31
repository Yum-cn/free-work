package com.anhuay.terminal.controller;

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

import com.anhuay.terminal.domain.UpgradeTaskDO;
import com.anhuay.terminal.service.UpgradeTaskService;
import com.common.constant.CommonEnum;

import net.sf.json.JSONObject;

import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

/**
 * 升级任务管理
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-28 15:55:35
 */
 
@Controller
@RequestMapping("/terminal/upgradeTask")
public class UpgradeTaskController {
	@Autowired
	private UpgradeTaskService upgradeTaskService;
	
	@GetMapping()
	@RequiresPermissions("terminal:upgradeTask:upgradeTask")
	String UpgradeTask(){
	    return "terminal/upgradeTask/upgradeTask";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("terminal:upgradeTask:upgradeTask")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UpgradeTaskDO> upgradeTaskList = upgradeTaskService.list(query);
		int total = upgradeTaskService.count(query);
		PageUtils pageUtils = new PageUtils(upgradeTaskList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("terminal:upgradeTask:add")
	String add(){
	    return "terminal/upgradeTask/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("terminal:upgradeTask:edit")
	String edit(@PathVariable("id") Long id,Model model){
		UpgradeTaskDO upgradeTask = upgradeTaskService.get(id);
		model.addAttribute("upgradeTask", upgradeTask);
	    return "terminal/upgradeTask/add";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("terminal:upgradeTask:add")
	public R save( UpgradeTaskDO upgradeTask){
		

		upgradeTask.setUpgradeRules(JSONObject.fromObject(upgradeTask).toString());
		
		upgradeTask.setUpdateTime(System.currentTimeMillis() / 1000);
		if(upgradeTask.getId()!=null && upgradeTask.getId()>0){
			if(upgradeTaskService.update(upgradeTask)>0){
				return R.ok();
			}
		}else{
			upgradeTask.setStatus(CommonEnum.STATUS.ONE.value);
			upgradeTask.setTaskStatus(CommonEnum.STATUS.ONE.value);
			upgradeTask.setCreateTime(System.currentTimeMillis() / 1000);
			
			if(upgradeTaskService.save(upgradeTask)>0){
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
	@RequiresPermissions("terminal:upgradeTask:edit")
	public R update( UpgradeTaskDO upgradeTask){
		upgradeTaskService.update(upgradeTask);
		return R.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/updateTaskStatus")
	@RequiresPermissions("terminal:upgradeTask:edit")
	public R updateTaskStatus( UpgradeTaskDO upgradeTask){
		upgradeTaskService.updateTaskStatus(upgradeTask);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("terminal:upgradeTask:remove")
	public R remove( Long id){
		if(upgradeTaskService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("terminal:upgradeTask:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		upgradeTaskService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
