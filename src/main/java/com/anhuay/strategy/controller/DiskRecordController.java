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

import com.anhuay.strategy.domain.DiskRecordDO;
import com.anhuay.strategy.service.DiskRecordService;
import com.anhuay.common.controller.BaseController;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

/**
 * 光盘刻录表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-19 20:25:53
 */
 
@Controller
@RequestMapping("/strategy/diskRecord")
public class DiskRecordController  extends BaseController{
	@Autowired
	private DiskRecordService diskRecordService;
	
	@GetMapping()
	@RequiresPermissions("strategy:diskRecord:diskRecord")
	String DiskRecord(){
	    return "strategy/diskRecord/diskRecord";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("strategy:diskRecord:diskRecord")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DiskRecordDO> diskRecordList = diskRecordService.list(query);
		int total = diskRecordService.count(query);
		PageUtils pageUtils = new PageUtils(diskRecordList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("strategy:diskRecord:add")
	String add(){
	    return "strategy/diskRecord/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("strategy:diskRecord:edit")
	String edit(@PathVariable("id") Long id,Model model){
		DiskRecordDO diskRecord = diskRecordService.get(id);
		model.addAttribute("diskRecord", diskRecord);
	    return "strategy/diskRecord/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("strategy:diskRecord:add")
	public R save( DiskRecordDO diskRecord){
		diskRecord.setId(getId());
		if(diskRecordService.save(diskRecord)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("strategy:diskRecord:edit")
	public R update( DiskRecordDO diskRecord){
		diskRecordService.update(diskRecord);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("strategy:diskRecord:remove")
	public R remove( Long id){
		if(diskRecordService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("strategy:diskRecord:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		diskRecordService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
