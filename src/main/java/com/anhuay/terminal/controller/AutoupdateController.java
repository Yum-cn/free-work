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

import com.anhuay.terminal.domain.AutoupdateDO;
import com.anhuay.terminal.service.AutoupdateService;
import com.anhuay.common.controller.BaseController;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

/**
 * 
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-25 13:56:37
 */
 
@Controller
@RequestMapping("/terminal/autoupdate")
public class AutoupdateController  extends BaseController{
	@Autowired
	private AutoupdateService autoupdateService;
	
	@GetMapping()
	@RequiresPermissions("terminal:autoupdate:autoupdate")
	String Autoupdate(){
	    return "terminal/autoupdate/autoupdate";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("terminal:autoupdate:autoupdate")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AutoupdateDO> autoupdateList = autoupdateService.list(query);
		int total = autoupdateService.count(query);
		PageUtils pageUtils = new PageUtils(autoupdateList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("terminal:autoupdate:add")
	String add(){
	    return "terminal/autoupdate/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("terminal:autoupdate:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		AutoupdateDO autoupdate = autoupdateService.get(id);
		model.addAttribute("autoupdate", autoupdate);
	    return "terminal/autoupdate/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("terminal:autoupdate:add")
	public R save( AutoupdateDO autoupdate){
		if(autoupdateService.save(autoupdate)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("terminal:autoupdate:edit")
	public R update( AutoupdateDO autoupdate){
		autoupdateService.update(autoupdate);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("terminal:autoupdate:remove")
	public R remove( Integer id){
		if(autoupdateService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("terminal:autoupdate:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		autoupdateService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
