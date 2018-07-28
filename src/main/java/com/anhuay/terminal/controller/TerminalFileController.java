package com.anhuay.terminal.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.anhuay.common.domain.FileDO;
import com.anhuay.common.service.FileService;
import com.anhuay.common.utils.FileType;
import com.anhuay.common.utils.FileUtil;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;
import com.anhuay.terminal.domain.TerminalFileDO;
import com.anhuay.terminal.service.TerminalFileService;
import com.common.constant.CommonEnum;
import com.common.id.IdWorker;
import com.common.util.BaseResult;
import com.common.util.BaseResultHelper;
import com.common.util.Crc;

/**
 * 终端程序上传
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-28 15:55:35
 */
 
@Controller
@RequestMapping("/terminal/terminalFile")
public class TerminalFileController  extends BaseController {
	@Autowired
	private TerminalFileService terminalFileService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FileService sysFileService;
	
	@GetMapping()
	@RequiresPermissions("terminal:terminalFile:terminalFile")
	String TerminalFile(){
	    return "terminal/terminalFile/terminalFile";
	}
	
	@GetMapping("/select")
	@RequiresPermissions("terminal:terminalFile:terminalFile")
	String TerminalFileSelect(){
	    return "terminal/terminalFile/terminalFileSelect";
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("terminal:terminalFile:terminalFile")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TerminalFileDO> terminalFileList = terminalFileService.list(query);
		int total = terminalFileService.count(query);
		PageUtils pageUtils = new PageUtils(terminalFileList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@PostMapping("/upload")
	Object upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

		String sourceFileName = file.getOriginalFilename();
		//System.out.println(StringUtils.indexOf(sourceFileName, "tar.gz"));
		//System.out.println("value:"+StringUtils.substringAfterLast(sourceFileName, "tar.gz"));
		if(StringUtils.indexOf(sourceFileName, "tar.gz")<0||StringUtils.isNotBlank(StringUtils.substringAfterLast(sourceFileName, "tar.gz"))){
			
			return BaseResultHelper.error("请上传后缀为tar.gz格式的文件！");
		}
		
		//String fileName = FileUtil.renameToUUID(sourceFileName);
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
		
		TerminalFileDO terminalFile = new TerminalFileDO();
		terminalFile.setId(id);
		terminalFile.setUploadUrl(bootdoConfig.getUploadPath() + tempFilePath+sourceFileName);
		terminalFile.setFileName(sourceFileName);
		terminalFile.setFileTag(file.getContentType());
		terminalFile.setFileSize(file.getSize());
		terminalFile.setUploadTime(System.currentTimeMillis()/1000);
		terminalFile.setStatus(CommonEnum.STATUS.ONE.value);
		terminalFile.setUpdateTime(System.currentTimeMillis() / 1000);
		terminalFile.setCreateTime(System.currentTimeMillis() / 1000);
		try {
			long crcValue = Crc.checksumBufferedInputStream(bootdoConfig.getUploadPath() + tempFilePath+sourceFileName);
			terminalFile.setCrcValue(String.valueOf(crcValue));
		} catch (IOException e) {
			e.printStackTrace();
		}
		terminalFile.setDownUrl(bootdoConfig.getDownloadUrl()+tempFilePath+sourceFileName);
		terminalFileService.save(terminalFile);
		
		return BaseResultHelper.success(terminalFile);
	}

	@RequestMapping("/download/{contentOne}/{contentTwo}/{fileName:.+}")
    public String downLoad(@PathVariable("contentOne") String contentOne,@PathVariable("contentTwo") String contentTwo,@PathVariable("fileName") String fileName,HttpServletResponse response){
        //String filename="2.jpg";
        //String filePath = "F:/test" ;
        File file = new File(bootdoConfig.getUploadPath() +contentOne+"/"+contentTwo+"/"+ fileName);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
            
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;
            
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file); 
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }
                
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download" + fileName);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
	
	
	
	@GetMapping("/add")
	@RequiresPermissions("terminal:terminalFile:add")
	String add(){
	    return "terminal/terminalFile/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("terminal:terminalFile:edit")
	String edit(@PathVariable("id") Long id,Model model){
		TerminalFileDO terminalFile = terminalFileService.get(id);
		model.addAttribute("terminalFile", terminalFile);
	    return "terminal/terminalFile/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("terminal:terminalFile:add")
	public R save( TerminalFileDO terminalFile){
		if(terminalFileService.save(terminalFile)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("terminal:terminalFile:edit")
	public R update( TerminalFileDO terminalFile){
		terminalFileService.update(terminalFile);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("terminal:terminalFile:remove")
	public R remove( Long id){
		if(terminalFileService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("terminal:terminalFile:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		terminalFileService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
