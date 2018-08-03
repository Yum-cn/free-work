package com.anhuay.soft.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.anhuay.common.config.BootdoConfig;
import com.anhuay.common.controller.BaseController;
import com.anhuay.common.domain.DictDO;
import com.anhuay.common.domain.FileDO;
import com.anhuay.common.domain.Tree;
import com.anhuay.common.service.FileService;
import com.anhuay.common.utils.FileType;
import com.anhuay.common.utils.FileUtil;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;
import com.anhuay.soft.domain.SoftClassificationDO;
import com.anhuay.soft.service.SoftClassificationService;
import com.common.constant.CommonEnum;
import com.common.id.IdWorker;
import com.common.util.BaseResultHelper;
import com.common.util.Crc;

/**
 * 软件分类
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-31 21:17:58
 */
 
@Controller
@RequestMapping("/soft/softClassification")
public class SoftClassificationController   extends BaseController{
	@Autowired
	private SoftClassificationService softClassificationService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FileService sysFileService;
	
	
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
	
	// 类别已经指定增加
	@GetMapping("/add/{dictId}/{dictName}")
	@RequiresPermissions("soft:softClassification:add")
	String addDefault(Model model, @PathVariable("dictId") Long dictId, @PathVariable("dictName") String dictName) {
		
		SoftClassificationDO softClassification = new SoftClassificationDO();
		softClassification.setDictId(dictId);
		softClassification.setDictName(dictName);
		model.addAttribute("softClassification", softClassification);
		return "soft/softClassification/add";
	}


	@GetMapping("/edit/{id}")
	@RequiresPermissions("soft:softClassification:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SoftClassificationDO softClassification = softClassificationService.get(id);
		model.addAttribute("softClassification", softClassification);
	    return "soft/softClassification/add";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("soft:softClassification:add")
	public R save( SoftClassificationDO softClassification){
		
		softClassification.setUpdateTime(System.currentTimeMillis() / 1000);
		if(softClassification.getId()!=null && softClassification.getId()>0){
			if(softClassificationService.update(softClassification)>0){
				return R.ok();
			}
		}else{
			softClassification.setStatus(CommonEnum.STATUS.ONE.value);
			softClassification.setCreateTime(System.currentTimeMillis() / 1000);
			
			if(softClassificationService.save(softClassification)>0){
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
	
	@GetMapping("/tree")
	@ResponseBody
	public Tree<DictDO> tree(@RequestParam Map<String, Object> params) {
		Tree<DictDO> tree = new Tree<DictDO>();
		tree = softClassificationService.getTree(params);
		return tree;
	}
	
	@ResponseBody
	@PostMapping("/upload")
	Object upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

		String sourceFileName = file.getOriginalFilename();
		//System.out.println(StringUtils.indexOf(sourceFileName, "tar.gz"));
		//System.out.println("value:"+StringUtils.substringAfterLast(sourceFileName, "tar.gz"));
		String dateStr = DateFormat.getDateInstance(DateFormat.MEDIUM).format(new Date());
		String tempFilePath = dateStr+ "/"+UUID.randomUUID()+"/";
		
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath()+tempFilePath, sourceFileName);
		} catch (Exception e) {
			return BaseResultHelper.error();
		}
		IdWorker idWorker = new IdWorker(2);
		long id = idWorker.nextId();
		
		
		FileDO sysFile = new FileDO(FileType.fileType(sourceFileName), bootdoConfig.getUploadPath()+tempFilePath+sourceFileName, new Date(),sourceFileName);
		sysFile.setId(id);
		sysFileService.save(sysFile);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("uploadTime", System.currentTimeMillis()/1000);
		resultMap.put("fileSize",file.getSize());
		
		try {
			long crcValue = Crc.checksumBufferedInputStream(bootdoConfig.getUploadPath() + tempFilePath+sourceFileName);
			resultMap.put("crcValue",crcValue);
		} catch (IOException e) {
			e.printStackTrace();
		}
		resultMap.put("downUrl",bootdoConfig.getDownloadUrl()+tempFilePath+sourceFileName);
		
		return BaseResultHelper.success(resultMap);
	}
	
}
