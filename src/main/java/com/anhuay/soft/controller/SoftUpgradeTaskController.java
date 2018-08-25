package com.anhuay.soft.controller;

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

import com.anhuay.soft.domain.SoftUpgradeTaskDO;
import com.anhuay.soft.service.SoftUpgradeTaskService;
import com.anhuay.terminal.domain.UpgradeTaskDO;
import com.common.constant.CommonEnum;

import net.sf.json.JSONObject;

import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

/**
 * 软件分发升级任务管理
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-25 11:42:52
 */
 
@Controller
@RequestMapping("/soft/softUpgradeTask")
public class SoftUpgradeTaskController {
	@Autowired
	private SoftUpgradeTaskService softUpgradeTaskService;
	
	@GetMapping()
	@RequiresPermissions("soft:softUpgradeTask:softUpgradeTask")
	String SoftUpgradeTask(){
	    return "soft/softUpgradeTask/softUpgradeTask";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("soft:softUpgradeTask:softUpgradeTask")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SoftUpgradeTaskDO> softUpgradeTaskList = softUpgradeTaskService.list(query);
		int total = softUpgradeTaskService.count(query);
		PageUtils pageUtils = new PageUtils(softUpgradeTaskList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("soft:softUpgradeTask:add")
	String add(){
	    return "soft/softUpgradeTask/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("soft:softUpgradeTask:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SoftUpgradeTaskDO softUpgradeTask = softUpgradeTaskService.get(id);
		model.addAttribute("upgradeTask", softUpgradeTask);
	    return "soft/softUpgradeTask/add";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("soft:softUpgradeTask:add")
	public R save( SoftUpgradeTaskDO softUpgradeTask){
		softUpgradeTask.setUpgradeRules(JSONObject.fromObject(softUpgradeTask).toString());
		
		softUpgradeTask.setUpdateTime(System.currentTimeMillis() / 1000);
		if(softUpgradeTask.getId()!=null && softUpgradeTask.getId()>0){
			if(softUpgradeTaskService.update(softUpgradeTask)>0){
				return R.ok();
			}
		}else{
			softUpgradeTask.setStatus(CommonEnum.STATUS.ONE.value);
			softUpgradeTask.setTaskStatus(CommonEnum.STATUS.ONE.value);
			softUpgradeTask.setCreateTime(System.currentTimeMillis() / 1000);
			
			if(softUpgradeTaskService.save(softUpgradeTask)>0){
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
	@RequiresPermissions("soft:softUpgradeTask:edit")
	public R update( SoftUpgradeTaskDO softUpgradeTask){
		softUpgradeTaskService.update(softUpgradeTask);
		return R.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/updateTaskStatus")
	public R updateTaskStatus( SoftUpgradeTaskDO upgradeTask){
		softUpgradeTaskService.updateTaskStatus(upgradeTask);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("soft:softUpgradeTask:remove")
	public R remove( Long id){
		if(softUpgradeTaskService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("soft:softUpgradeTask:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		softUpgradeTaskService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
