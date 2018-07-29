package com.anhuay.os.controller;

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

import com.anhuay.os.domain.OsInfoDO;
import com.anhuay.os.service.OsInfoService;
import com.common.constant.CommonEnum;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

/**
 * 主机信息表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-24 14:45:03
 */
 
@Controller
@RequestMapping("/os/osInfo")
public class OsInfoController {
	@Autowired
	private OsInfoService osInfoService;
	
	@GetMapping()
	@RequiresPermissions("os:osInfo:osInfo")
	String OsInfo(){
	    return "os/osInfo/osInfo";
	}
	
	@GetMapping("/select")
	@RequiresPermissions("os:osInfo:osInfo")
	String OsInfoSelect(){
	    return "os/osInfo/osInfoSelect";
	}
	
	@GetMapping("/v2")
	@RequiresPermissions("os:osInfo:osInfo")
	String OsInfoSelectV2(){
		return "os/osInfo/osInfoV2";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("os:osInfo:osInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OsInfoDO> osInfoList = osInfoService.list(query);
		int total = osInfoService.count(query);
		PageUtils pageUtils = new PageUtils(osInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("os:osInfo:add")
	String add(){
	    return "os/osInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("os:osInfo:edit")
	String edit(@PathVariable("id") Long id,Model model){
		OsInfoDO osInfo = osInfoService.get(id);
		model.addAttribute("osInfo", osInfo);
	    return "os/osInfo/add";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("os:osInfo:add")
	public R save( OsInfoDO osInfo){

		osInfo.setUpdateTime(System.currentTimeMillis() / 1000);
		if(osInfo.getId()!=null && osInfo.getId()>0){
			if(osInfoService.update(osInfo)>0){
				return R.ok();
			}
		}else{
			osInfo.setStatus(CommonEnum.STATUS.ONE.value);
			osInfo.setCreateTime(System.currentTimeMillis() / 1000);
			
			if(osInfoService.save(osInfo)>0){
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
	@RequiresPermissions("os:osInfo:edit")
	public R update( OsInfoDO osInfo){
		osInfoService.update(osInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("os:osInfo:remove")
	public R remove( Long id){
		if(osInfoService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("os:osInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		osInfoService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}