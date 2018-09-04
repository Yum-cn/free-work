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

import com.anhuay.strategy.domain.IrregularConnectionDO;
import com.anhuay.strategy.service.IrregularConnectionService;
import com.anhuay.common.controller.BaseController;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

/**
 * 违规外联表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-21 17:18:00
 */
 
@Controller
@RequestMapping("/strategy/irregularConnection")
public class IrregularConnectionController  extends BaseController{
	@Autowired
	private IrregularConnectionService irregularConnectionService;
	
	@GetMapping()
	@RequiresPermissions("strategy:irregularConnection:irregularConnection")
	String IrregularConnection(){
	    return "strategy/irregularConnection/irregularConnection";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("strategy:irregularConnection:irregularConnection")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<IrregularConnectionDO> irregularConnectionList = irregularConnectionService.list(query);
		int total = irregularConnectionService.count(query);
		PageUtils pageUtils = new PageUtils(irregularConnectionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("strategy:irregularConnection:add")
	String add(){
	    return "strategy/irregularConnection/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("strategy:irregularConnection:edit")
	String edit(@PathVariable("id") Long id,Model model){
		IrregularConnectionDO irregularConnection = irregularConnectionService.get(id);
		model.addAttribute("irregularConnection", irregularConnection);
	    return "strategy/irregularConnection/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("strategy:irregularConnection:add")
	public R save( IrregularConnectionDO irregularConnection){
		irregularConnection.setId(getId());
		if(irregularConnectionService.save(irregularConnection)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("strategy:irregularConnection:edit")
	public R update( IrregularConnectionDO irregularConnection){
		irregularConnectionService.update(irregularConnection);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("strategy:irregularConnection:remove")
	public R remove( Long id){
		if(irregularConnectionService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("strategy:irregularConnection:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		irregularConnectionService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
