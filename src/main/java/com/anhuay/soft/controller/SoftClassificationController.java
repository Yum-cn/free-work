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

import com.anhuay.soft.domain.SoftClassificationDO;
import com.anhuay.soft.service.SoftClassificationService;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

/**
 * 软件分类
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-31 21:17:58
 */
 
@Controller
@RequestMapping("/soft/softClassification")
public class SoftClassificationController {
	@Autowired
	private SoftClassificationService softClassificationService;
	
	@GetMapping()
	@RequiresPermissions("soft:softClassification:softClassification")
	String SoftClassification(){
	    return "soft/softClassification/softClassification";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("soft:softClassification:softClassification")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SoftClassificationDO> softClassificationList = softClassificationService.list(query);
		int total = softClassificationService.count(query);
		PageUtils pageUtils = new PageUtils(softClassificationList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("soft:softClassification:add")
	String add(){
	    return "soft/softClassification/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("soft:softClassification:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SoftClassificationDO softClassification = softClassificationService.get(id);
		model.addAttribute("softClassification", softClassification);
	    return "soft/softClassification/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("soft:softClassification:add")
	public R save( SoftClassificationDO softClassification){
		if(softClassificationService.save(softClassification)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("soft:softClassification:edit")
	public R update( SoftClassificationDO softClassification){
		softClassificationService.update(softClassification);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("soft:softClassification:remove")
	public R remove( Long id){
		if(softClassificationService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("soft:softClassification:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		softClassificationService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
