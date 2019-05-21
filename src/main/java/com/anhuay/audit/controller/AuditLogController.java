package com.anhuay.audit.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

import com.anhuay.audit.domain.AuditAlarmLogVO;
import com.anhuay.audit.domain.AuditLogDO;
import com.anhuay.audit.service.AuditLogService;
import com.anhuay.common.utils.DateUtils;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;
import com.common.util.BaseResult;
import com.common.util.ExcelUtil;

/**
 * 
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-30 14:46:55
 */
 
@Controller
@RequestMapping("/audit/log")
public class AuditLogController {
	@Autowired
	private AuditLogService logService;
	
	//（1.本地文件审计，2.U 盘文件审计，3.开机关机审计，4.进程监控审计，5.打印监控审计，6.主机信息审计，
	//7.系统日志审计，8.账户监控审计，9.共享监控审计， 10.异常监控审计，11.主机配置审计， 12.移动介质审计，
	//13.端口监控审计，14.服务监控审计，15.连接监控审计，16.网络流量检测，17.磁盘空间检测，18.文件控制，19.光盘刻录审计）
	@GetMapping("/{type}")
	@RequiresPermissions("audit:log")
	String Log(@PathVariable("type") Long type,Model model){
		model.addAttribute("type", type);
	    return "audit/log/log";
	}
	
	@GetMapping("/alarm")
	@RequiresPermissions("audit:log")
	String LogAlarm(){
	    return "audit/log/alarm";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("audit:log:log")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AuditLogDO> logList = logService.list(query);
		int total = logService.count(query);
		PageUtils pageUtils = new PageUtils(logList, total);
		return pageUtils;
	}
	
	
	@ResponseBody
	@GetMapping("/listAlarm")
	@RequiresPermissions("audit:log:log")
	public PageUtils listAlarm(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AuditAlarmLogVO> logList = logService.listAlarm(query);
		int total = logService.countAlarm(query);
		PageUtils pageUtils = new PageUtils(logList, total);
		return pageUtils;
	}
	
	
	@GetMapping("/add")
	@RequiresPermissions("audit:log:add")
	String add(){
	    return "audit/log/add";
	}

	@GetMapping("/edit/{logid}")
	@RequiresPermissions("audit:log:edit")
	String edit(@PathVariable("logid") Long logid,Model model){
		AuditLogDO log = logService.get(logid);
		model.addAttribute("log", log);
	    return "audit/log/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("audit:log:add")
	public R save( AuditLogDO log){
		if(logService.save(log)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("audit:log:edit")
	public R update( AuditLogDO log){
		logService.update(log);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("audit:log:remove")
	public R remove( Long logid){
		if(logService.updateStatus(logid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("audit:log:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] logids){
		logService.batchUpdateStatus(logids);
		return R.ok();
	}
	
	@GetMapping("/export")
	public void export(@RequestParam Map<String, Object> params,HttpServletRequest request, HttpServletResponse response) {
		try {

			
			String fileName = new String(
					("主机审计日志 " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).getBytes(), "UTF-8");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");

			List<Map<String, Object>> excelList = new ArrayList<Map<String, Object>>();

			// TODO 分页
			BaseResult<Object> statisticsResult = logService.exportList(params);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> statisticsList = (List<Map<String, Object>>) statisticsResult.getData();
			
			String[] columnNames = { "客户端IP地址", "事件客体", "事件内容(详细信息)", "事件发生时间", "风险级别", "事件种类","行为类别","日志分类"};// 列名
			String[] keys = { "osIp",  "info", "details",
					 "entryTime", "level", "type", "beType", "logType" };// map中的key
			Map<String, Object> map1 = new HashMap<String, Object>();
			// map1.put("sheetName", "数据统计"+new SimpleDateFormat("yyyy-MM-dd
			// HH:mm:ss").format(new Date()));
			map1.put("sheetName", "审计日志" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "-"
					+ StringUtils.substring(UUID.randomUUID() + "", 0, 5));
			map1.put("columnNames", columnNames);
			map1.put("keys", keys);
			map1.put("data", CollectionUtils.isEmpty(statisticsList)?new ArrayList<Map<String, Object>>():statisticsList);
			excelList.add(map1);

			ExcelUtil.refactorExcel(response, excelList, fileName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
