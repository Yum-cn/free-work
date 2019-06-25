package com.anhuay.audit.controller;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
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
	
	@GetMapping("/exportXml")
	public void exportXml(@RequestParam Map<String, Object> params,HttpServletRequest request, HttpServletResponse response) {
		try {
			String fileName = new String(("主机审计日志 " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).getBytes(), "UTF-8");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xml");
			List<Map<String, Object>> excelList = new ArrayList<Map<String, Object>>();
			BaseResult<Object> statisticsResult = logService.exportList(params);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> statisticsList = (List<Map<String, Object>>) statisticsResult.getData();
			/*<?xml version="1.0" encoding="UTF-8"?>
			<Log Pri="16" tablename="" Remark="">
			     <LogID Remark="记录ID"></LogID>
			     <Subject Remark="事件主体">
			        <Node Remark="主体IP地址">192.168.200.94</Node>
			        <NodeID Remark="主体ID"></NodeID>
			        <ComputerName Remark="计算机名称"></ComputerName>
			        <UserName Remark="用户名或责任人"></UserName>
			        <Department Remark="部门"></Department>
			        <Program Remark="部件"></Program>
			        <Faceility Remark="类型"></Faceility>
			            </Subject>
			     <Object Remark="事件客体">
			         <ObjectName Remark="文件名">A.doc</ObjectName>
			     </Object>
			     <Details Remark="事件内容">访问C：\A.doc文件</Details> 
			     <Result Remark="事件结果">成功</Result> 
			     <EntryStamp Remark="发生时间">YYYY-MM-DD HH:mm:ss</EntryStamp> 
			     <Level Remark="事件风险级别">7</Level> 
			     <Type Remark="事件种类"></Type> 
			     <ProductType Remark="产品类型"></ProductType> 
			     <BehaviourType Remark="行为类别"></BehaviourType> 
			     <Reservation Remark="保留项"></Reservation> 
			</Log>
			客户端IP地址	事件客体	事件内容(详细信息)	事件发生时间	风险级别	事件种类	行为类别	日志分类
192.168.186.129	本地文件审计	计算机账户：;硬盘序列号：0x2aeb2df6;;IP地址：192.168.186.129;;MAC地址：00:0c:29:3c:09:dc;;操作类型:文件修改;位置：/home/test/server/app.log;事件发生时间:2019-05-15 11:43:21;	2019-05-15	警告	用户操作	违规行为	本地文件审计

			*/   
			Document document = DocumentHelper.createDocument();
			document.setXMLEncoding("UTF-8");
			Element Logs = document.addElement("Logs");
			Logs.addAttribute("Remark", "");
			
			for (int i = 0; i < statisticsList.size(); i++) {
				
				Map<String, Object> map = statisticsList.get(i);
				
				Element Log = Logs.addElement("Log");
				Log.addAttribute("Pri", map.get("id")==null?"":String.valueOf(map.get("id")));
				Log.addAttribute("tablename", "cli_log");
				Log.addAttribute("Remark", "");
				
				Element LogID = Log.addElement("LogID");
				LogID.addAttribute("Remark", "记录ID");
				LogID.addText(map.get("logId")==null?"":String.valueOf(map.get("logId")));
				
				Element Subject = Log.addElement("Subject");
				Subject.addAttribute("Remark", "事件主体");
				
				Element Node = Subject.addElement("Node");
				Node.addAttribute("Remark", "主体IP地址");
				Node.addText(map.get("osIp")==null?"":String.valueOf(map.get("osIp")));
				
				Element NodeID = Subject.addElement("NodeID");
				NodeID.addAttribute("Remark", "主体ID");
				NodeID.addText(map.get("nodeId")==null?"":String.valueOf(map.get("nodeId")));
				
				Element ComputerName = Subject.addElement("ComputerName");
				ComputerName.addAttribute("Remark", "计算机名称");
				ComputerName.addText(map.get("computerName")==null?"":String.valueOf(map.get("computerName")));

				Element UserName = Subject.addElement("UserName");
				UserName.addAttribute("Remark", "用户名或责任人");
				UserName.addText(map.get("userName")==null?"":String.valueOf(map.get("userName")));

				Element Department = Subject.addElement("Department");
				Department.addAttribute("Remark", "部门");
				Department.addText(map.get("department")==null?"":String.valueOf(map.get("department")));

				Element Program = Subject.addElement("Program");
				Program.addAttribute("Remark", "部件");
				Program.addText(map.get("program")==null?"":String.valueOf(map.get("program")));

				Element Faceility = Subject.addElement("Faceility");
				Faceility.addAttribute("Remark", "类型");
				Faceility.addText(map.get("faceility")==null?"":String.valueOf(map.get("faceility")));
				
				Element Object = Log.addElement("Object");
				Object.addAttribute("Remark", "事件客体");
				Element ObjectName = Object.addElement("ObjectName");
				ObjectName.addAttribute("Remark", "类型");
				ObjectName.addText(map.get("info")==null?"":String.valueOf(map.get("info")));

				Element Details = Log.addElement("Details");
				Details.addAttribute("Remark", "事件内容");
				Details.addText(map.get("details")==null?"":String.valueOf(map.get("details")));
				
				Element Result = Log.addElement("Result");
				Result.addAttribute("Remark", "事件结果");
				Result.addText(map.get("result")==null?"":String.valueOf(map.get("result")));
				
				Element EntryStamp = Log.addElement("EntryStamp");
				EntryStamp.addAttribute("Remark", "发生时间");
				EntryStamp.addText(map.get("entryTime")==null?"":String.valueOf(map.get("entryTime")));
				
				Element Level = Log.addElement("Level");
				Level.addAttribute("Remark", "事件风险级别");
				Level.addText(map.get("level")==null?"":String.valueOf(map.get("level")));
				
				Element Type = Log.addElement("Type");
				Type.addAttribute("Remark", "事件种类");
				Type.addText(map.get("type")==null?"":String.valueOf(map.get("type")));
				
				Element ProductType = Log.addElement("ProductType");
				ProductType.addAttribute("Remark", "产品类型");
				ProductType.addText(map.get("productType")==null?"":String.valueOf(map.get("productType")));
				
				Element BehaviourType = Log.addElement("BehaviourType");
				BehaviourType.addAttribute("Remark", "行为类别");
				BehaviourType.addText(map.get("behaviourType")==null?"":String.valueOf(map.get("behaviourType")));
				
				Element Reservation = Log.addElement("Reservation");
				Reservation.addAttribute("Remark", "保留项");
				Reservation.addText(map.get("reservation")==null?"":String.valueOf(map.get("reservation")));
				
			}
			
			/*response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=\"log.xml\";");
			response.setHeader("Content-Transfer-Encoding","binary");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");*/
			//response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xml");
			response.setHeader("Content-Disposition", "attachment; filename=\"log"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+".xml\";");
			response.setContentType( "text/xml; charset=UTF-8" );
			String responseString = XMLToString(document);
			
			responseString = responseString.replace("GB2312", "utf-8");
			
			PrintWriter printWriter = response.getWriter();
			printWriter.print( responseString );
			
			
			
			/*String[] columnNames = { "客户端IP地址", "事件客体", "事件内容(详细信息)", "事件发生时间", "风险级别", "事件种类","行为类别","日志分类"};// 列名
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
			
			ExcelUtil.refactorExcel(response, excelList, fileName);*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	  /**
     *把树状结构Document转换为字符串
     *@param document :需要转换的XML；
     *@author zzb
     */
    public String XMLToString(Document document){
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("GB2312");
            StringWriter strWriter = new StringWriter();
            XMLWriter writer = new XMLWriter(strWriter, format);
            String XMLData = null;


            writer.write(document);
            XMLData = strWriter.toString();
            writer.flush();
            writer.close();


            return XMLData;
        } catch (Exception ioe) {
            return "";
        }
    }
	
}
