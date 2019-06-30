package com.anhuay.statistics.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anhuay.alarm.domain.EmailConfigDO;
import com.anhuay.alarm.service.EmailConfigService;
import com.anhuay.common.annotation.Log;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;
import com.anhuay.oa.domain.NotifyDO;
import com.common.constant.CommonEnum;

/**
 * 邮箱配置
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-04 14:42:51
 */
 
@Controller
@RequestMapping("/alarm/statistics")
public class StatisticsController {

	@Resource
	private JdbcTemplate jdbcTemplate;

	
	@GetMapping("/list")
	@RequiresPermissions("alarm:statistics:list")
	String list(@RequestParam Map<String, Object> params,Model model){
		try{

			//风险级别
			String sql = "select level,count(level) num from cli_log group by level  order by level+0 asc";
			List fxjblist = jdbcTemplate.queryForList(sql);
			model.addAttribute("result", fxjblist);
			//违规行为
			sql = "select be_type,count(be_type) num from cli_log group by be_type order by be_type+0 asc";
			List wgxwlist = jdbcTemplate.queryForList(sql);
			model.addAttribute("wgxwlist", wgxwlist);
			//日志分类
			sql = "select log_type,count(log_type) num from cli_log group by log_type order by log_type+0 asc";
			List rzfllist = jdbcTemplate.queryForList(sql);
			model.addAttribute("rzfllist", rzfllist);
			
//			HashMap result = new HashMap();
//			if(fxjblist!=null && fxjblist.size()>0){
//				for(int i=0;i<fxjblist.size();i++){
//					Map map = (Map)fxjblist.get(i);
//					System.out.println(String.valueOf(map.get("level")));
//					String level = String.valueOf(map.get("level"));
//					String num = String.valueOf(map.get("num"));
//					result.put("level", num);
//				}
//			}
			
//			System.out.println(">>>>>"+list);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	    return "statistics/list";
	}
	
	@PostMapping("/search")
	String search(@RequestParam Map<String, Object> params,Model model){
		try{
			String startDate = String.valueOf(params.get("startDate"));
			String endDate = String.valueOf(params.get("endDate"));
			String username = String.valueOf(params.get("username"));
			String ip = String.valueOf(params.get("ip"));
			String deptname = String.valueOf(params.get("deptname"));
			String tian = String.valueOf(params.get("tian"));
			String xieyi = String.valueOf(params.get("xieyi"));
			String fangwenliang = String.valueOf(params.get("fangwenliang"));
			String wenjianming = String.valueOf(params.get("wenjianming"));
			String sjjg = String.valueOf(params.get("sjjg"));
			String fxjb = String.valueOf(params.get("fxjb"));
			String xwlb = String.valueOf(params.get("xwlb"));
			System.out.println("startDate>>>>>"+startDate);
			//风险级别
			String sql = "select level,count(level) num from cli_log  where 1=1 ";
			if(startDate!=null && !"".equals(startDate) && endDate!=null && !"".equals(endDate)){
				sql = sql + " and (startDate>='"+startDate+"' or endDate<='"+endDate+"') ";
			}else{
				if(startDate!=null && !"".equals(startDate)){
					sql = sql + " and startDate>='"+startDate+"'  ";
				}
				if(endDate!=null && !"".equals(endDate)){
					sql = sql + " and endDate<='"+endDate+"'  ";
				}
			}
			if(username!=null && !"".equals(username)){
				sql = sql + " and details like '%"+username+"%'  ";
			}
			if(ip!=null && !"".equals(ip)){
				sql = sql + " and details like '%"+ip+"%'  ";
			}
			if(deptname!=null && !"".equals(deptname)){
				sql = sql + " and details like '%"+deptname+"%'  ";
			}
			if(sjjg!=null && !"".equals(sjjg)){
				sql = sql + " and type like '%"+sjjg+"%'  ";
			}
			if(fxjb!=null && !"".equals(fxjb)){
				sql = sql + " and level like '%"+fxjb+"%'  ";
			}
			if(xwlb!=null && !"".equals(xwlb)){
				sql = sql + " and be_type like '%"+xwlb+"%'  ";
			}
			sql = sql + " group by level order by level+0 asc";
			List fxjblist = jdbcTemplate.queryForList(sql);
			model.addAttribute("result", fxjblist);
			//违规行为
			sql = "select be_type,count(be_type) num from cli_log where 1=1 ";
			if(startDate!=null && !"".equals(startDate) && endDate!=null && !"".equals(endDate)){
				sql = sql + " and (startDate>='"+startDate+"' or endDate<='"+endDate+"') ";
			}else{
				if(startDate!=null && !"".equals(startDate)){
					sql = sql + " and startDate>='"+startDate+"'  ";
				}
				if(endDate!=null && !"".equals(endDate)){
					sql = sql + " and endDate<='"+endDate+"'  ";
				}
			}
			if(username!=null && !"".equals(username)){
				sql = sql + " and details like '%"+username+"%'  ";
			}
			if(ip!=null && !"".equals(ip)){
				sql = sql + " and details like '%"+ip+"%'  ";
			}
			if(deptname!=null && !"".equals(deptname)){
				sql = sql + " and details like '%"+deptname+"%'  ";
			}
			if(sjjg!=null && !"".equals(sjjg)){
				sql = sql + " and type like '%"+sjjg+"%'  ";
			}
			if(fxjb!=null && !"".equals(fxjb)){
				sql = sql + " and level like '%"+fxjb+"%'  ";
			}
			if(xwlb!=null && !"".equals(xwlb)){
				sql = sql + " and be_type like '%"+xwlb+"%'  ";
			}
			sql = sql + " group by be_type  order by be_type+0 asc";
			List wgxwlist = jdbcTemplate.queryForList(sql);
			model.addAttribute("wgxwlist", wgxwlist);
			//日志分类
			sql = "select log_type,count(log_type) num from cli_log  where 1=1 ";
			if(startDate!=null && !"".equals(startDate) && endDate!=null && !"".equals(endDate)){
				sql = sql + " and (startDate>='"+startDate+"' or endDate<='"+endDate+"') ";
			}else{
				if(startDate!=null && !"".equals(startDate)){
					sql = sql + " and startDate>='"+startDate+"'  ";
				}
				if(endDate!=null && !"".equals(endDate)){
					sql = sql + " and endDate<='"+endDate+"'  ";
				}
			}
			if(username!=null && !"".equals(username)){
				sql = sql + " and details like '%"+username+"%'  ";
			}
			if(ip!=null && !"".equals(ip)){
				sql = sql + " and details like '%"+ip+"%'  ";
			}
			if(deptname!=null && !"".equals(deptname)){
				sql = sql + " and details like '%"+deptname+"%'  ";
			}
			if(sjjg!=null && !"".equals(sjjg)){
				sql = sql + " and type like '%"+sjjg+"%'  ";
			}
			if(fxjb!=null && !"".equals(fxjb)){
				sql = sql + " and level like '%"+fxjb+"%'  ";
			}
			if(xwlb!=null && !"".equals(xwlb)){
				sql = sql + " and be_type like '%"+xwlb+"%'  ";
			}
			sql = sql + " group by log_type order by log_type+0 asc";
			List rzfllist = jdbcTemplate.queryForList(sql);
			model.addAttribute("rzfllist", rzfllist);

		}catch(Exception ex){
			ex.printStackTrace();
		}
	    return "statistics/list";
	}
	@GetMapping("/cascade")
	String cascade(@RequestParam Map<String, Object> params,Model model){
		try{


		}catch(Exception ex){
			ex.printStackTrace();
		}
	    return "statistics/cascade";
	}
	@ResponseBody
	@GetMapping("/cascadeList")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		String sql = "SELECT id,ip FROM ahy_cascade";
		List wgxwlist = jdbcTemplate.queryForList(sql);
//		Query query = new Query(params);
//		List<NotifyDO> notifyList = notifyService.list(query);
		int total = 0;
		if(wgxwlist!=null && wgxwlist.size()>0){
			total = wgxwlist.size();
		}
		PageUtils pageUtils = new PageUtils(wgxwlist, total);
		return pageUtils;
	}
	//下发策略按钮
	@ResponseBody
	@GetMapping("/offerStrategy")
	String offerStrategy(@RequestParam Map<String, Object> params,Model model) {
		JSONObject json = new JSONObject();
		
		String ip = String.valueOf(params.get("ip"));
		System.out.println("ip>>>"+ip);
		String sql = "select * from  ahy_dept_strategy";
		List anhy_dept_strategy_list = jdbcTemplate.queryForList(sql);
		String anhy_dept_strategy_json = JSONObject.toJSONString(anhy_dept_strategy_list);
		json.put("ahy_dept_strategy", anhy_dept_strategy_json);
//		System.out.println(">>>"+JSONObject.toJSONString(anhy_dept_strategy_list));
		
		sql = "select * from  ahy_os_group_strategy";
		List anhy_os_group_strategy_list = jdbcTemplate.queryForList(sql);
		String ahy_os_group_strategy_json = JSONObject.toJSONString(anhy_os_group_strategy_list);
		json.put("ahy_os_group_strategy", ahy_os_group_strategy_json);
		
		sql = "select * from  ahy_os_strategy";
		List anhy_os_strategy_list = jdbcTemplate.queryForList(sql);
		String ahy_os_strategy_json = JSONObject.toJSONString(anhy_os_strategy_list);
		json.put("ahy_os_strategy", ahy_os_strategy_json);
		
		sql = "select * from  ahy_strategy_templet";
		List anhy_os_templet_list = jdbcTemplate.queryForList(sql);
		String ahy_strategy_templet_json = JSONObject.toJSONString(anhy_os_templet_list);
		json.put("ahy_strategy_templet", ahy_strategy_templet_json);
		String result = json.toJSONString();
		String sr=HttpRequest.sendPost(ip+"/alarm/statistics/syncStrategy", "data="+result);
		return null;
//		return pageUtils;
	}
	//同步策略接口
	@PostMapping("/syncStrategy")
	void syncStrategy(@RequestParam Map<String, Object> params,Model model) {
		String data = String.valueOf(params.get("data"));
		JSONObject dataobject = JSONObject.parseObject(data);
		JSONArray jsonArray = JSONArray.parseArray(dataobject.getString("ahy_os_group_strategy"));
		if(jsonArray!=null && jsonArray.size()>0){
			String sql = "delete from ahy_os_group_strategy";
			jdbcTemplate.execute(sql);
			for(int i=0;i<jsonArray.size();i++){
				JSONObject obj = (JSONObject)jsonArray.get(i);
				String id = obj.getString("id");
				String templet_id = obj.getString("templet_id");
				String templet_name = obj.getString("templet_name");
				String os_group_ids = obj.getString("os_group_ids");
				String os_group_names = obj.getString("os_group_names");
				String status = obj.getString("status");
				sql = "INSERT INTO ahy_os_group_strategy (id, templet_id, templet_name, os_group_ids, os_group_names, status) VALUES ('"+id+"', '"+templet_id+"', '"+templet_name+"', '"+os_group_ids+"', '"+os_group_names+"', '"+status+"');";
				jdbcTemplate.execute(sql);
			}
		}
		
		
		jsonArray = JSONArray.parseArray(dataobject.getString("ahy_dept_strategy"));
		if(jsonArray!=null && jsonArray.size()>0){
			String sql = "delete from ahy_dept_strategy";
			jdbcTemplate.execute(sql);
			for(int i=0;i<jsonArray.size();i++){
				JSONObject obj = (JSONObject)jsonArray.get(i);
				String id = obj.getString("id");
				String templet_id = obj.getString("templet_id");
				String templet_name = obj.getString("templet_name");
				String dept_ids = obj.getString("dept_ids");
				String dept_names = obj.getString("dept_names");
				String status = obj.getString("status");
				sql = "INSERT INTO ahy_dept_strategy (id, templet_id, templet_name, dept_ids, dept_names, status) VALUES ('"+id+"', '"+templet_id+"', '"+templet_name+"', '"+dept_ids+"', '"+dept_names+"', '"+status+"')";
				jdbcTemplate.execute(sql);
			}
		}
		
		jsonArray = JSONArray.parseArray(dataobject.getString("ahy_os_strategy"));
		if(jsonArray!=null && jsonArray.size()>0){
			String sql = "delete from ahy_os_strategy";
			jdbcTemplate.execute(sql);
			for(int i=0;i<jsonArray.size();i++){
				JSONObject obj = (JSONObject)jsonArray.get(i);
				String id = obj.getString("id");
				String templet_id = obj.getString("templet_id");
				String templet_name = obj.getString("templet_name");
				String os_ids = obj.getString("os_ids");
				String os_names = obj.getString("os_names");
				String status = obj.getString("status");
				sql = "INSERT INTO ahy_os_strategy (id, templet_id, templet_name, os_ids, os_names, status) VALUES ('"+id+"', '"+templet_id+"', '"+templet_name+"', '"+os_ids+"', '"+os_names+"', '"+status+"')";
				jdbcTemplate.execute(sql);
			}
		}
		jsonArray = JSONArray.parseArray(dataobject.getString("ahy_strategy_templet"));
		if(jsonArray!=null && jsonArray.size()>0){
			String sql = "delete from ahy_strategy_templet";
			jdbcTemplate.execute(sql);
			for(int i=0;i<jsonArray.size();i++){
				JSONObject obj = (JSONObject)jsonArray.get(i);
				String id = obj.getString("id");
				String templet_name = obj.getString("templet_name");
				String templet_desc = obj.getString("templet_desc");
				String templet_type = obj.getString("templet_type");
				String os_audit_id = obj.getString("os_audit_id");
				String irregular_connection_id = obj.getString("irregular_connection_id");
				String device_control_id = obj.getString("device_control_id");
				String disk_record_id = obj.getString("disk_record_id");
				String asset_management_id = obj.getString("asset_management_id");
				String soft_distribute_id = obj.getString("soft_distribute_id");
				String status = obj.getString("status");
				sql = "INSERT INTO ahy_strategy_templet (id, templet_name, templet_desc, templet_type, os_audit_id, irregular_connection_id, device_control_id, disk_record_id, asset_management_id, soft_distribute_id, status) "
						+ "VALUES ('"+id+"', '"+templet_name+"', '"+templet_desc+"', '"+templet_type+"', '"+os_audit_id+"', '"+irregular_connection_id+"', '"+device_control_id+"', '"+disk_record_id+"', '"+asset_management_id+"', '"+soft_distribute_id+"', '"+status+"')";
				jdbcTemplate.execute(sql);
			}
		}
//		return null;
//		return pageUtils;
	}
}
