package com.anhuay.audit.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhuay.audit.dao.AuditLogDao;
import com.anhuay.audit.domain.AuditAlarmLogVO;
import com.anhuay.audit.domain.AuditLogDO;
import com.anhuay.audit.service.AuditLogService;
import com.anhuay.common.utils.DateUtils;
import com.common.util.BaseResult;

@Service
public class AuditLogServiceImpl implements AuditLogService {
	@Autowired
	private AuditLogDao logDao;

	@Override
	public AuditLogDO get(Long logid) {
		return logDao.get(logid);
	}

	@Override
	public List<AuditLogDO> list(Map<String, Object> map) {
		return logDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return logDao.count(map);
	}

	@Override
	public int save(AuditLogDO log) {
		return logDao.save(log);
	}

	@Override
	public int update(AuditLogDO log) {
		return logDao.update(log);
	}

	@Override
	public int remove(Long logid) {
		return logDao.remove(logid);
	}

	@Override
	public int batchRemove(Long[] logids) {
		return logDao.batchRemove(logids);
	}

	@Override
	public int updateStatus(Long logid) {
		return logDao.updateStatus(logid);
	}

	@Override
	public int batchUpdateStatus(Long[] logids) {
		return logDao.batchUpdateStatus(logids);
	}

	@Override
	public List<AuditAlarmLogVO> listAlarm(Map<String, Object> map) {
		return logDao.listAlarm(map);
	}

	@Override
	public int countAlarm(Map<String, Object> map) {
		return logDao.countAlarm(map);
	}

	@Override
	public List<AuditLogDO> listLog(Map<String, Object> map) {
		return logDao.listLog(map);
	}

	@Override
	public BaseResult<Object> exportList(Map<String, Object> map) {

		List<AuditAlarmLogVO> queryList = logDao.listAlarm(map);
		;

		if (CollectionUtils.isEmpty(queryList)) {
			return BaseResult.unsuccess("暂无数据");
		}

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < queryList.size(); i++) {
			Map<String, Object> refactorMap = new HashMap<String, Object>();

			AuditAlarmLogVO tempBean = queryList.get(i);
			if (tempBean == null) {
				continue;
			}
			
			//select t.`logid`,t.`os_ip`,t.`os_id`,t.`info`,t.`details`,t.`result`,t.`entry_time`,
			//t.`level`,t.`type`,t.`be_type`,t.`log_type`,t.`notes`,t.`create_by`,t.`create_time`,t.`update_by`,t.`update_time`
	        //,oi.dept_name,oi.person_liable_name,oi.os_name,t.node_id,t.log_id as log_id,t.program
			
			refactorMap.put("osIp", tempBean.getOsIp());
			refactorMap.put("info", tempBean.getInfo());
			refactorMap.put("details", tempBean.getDetails());
			refactorMap.put("entryTime", DateUtils.format(tempBean.getEntryTime()));
			refactorMap.put("result", getResult(tempBean.getResult()));
			refactorMap.put("level", getLevel(tempBean.getLevel()));
			refactorMap.put("type",  getType(tempBean.getType()));
			refactorMap.put("beType", getBeType(tempBean.getBeType()));
			refactorMap.put("logType", getLogType(tempBean.getLogType()));
			
			refactorMap.put("id", tempBean.getId());
			refactorMap.put("logId", tempBean.getLogId());
			refactorMap.put("nodeId", tempBean.getNodeId());
			refactorMap.put("computerName", tempBean.getOsName());
			refactorMap.put("userName", tempBean.getPersonLiableName());
			refactorMap.put("department", tempBean.getDeptName());
			refactorMap.put("program", tempBean.getProgram());
			refactorMap.put("productType", getLogType(tempBean.getLogType()));

			/*
			 * //日志id private Long logid; //客户端ip地址 private String osIp;
			 * //预留与系统主机ID对应 private String osId; //事件客体 private String info;
			 * //事件内容（详细信息） private String details; //事件结果（1-成功，0-失败） private
			 * String result; //事件发生时间 private Date entryTime;
			 * //风险级别（0-紧急，1-警报，2-关键，3-错误，4-警告，5-通知，6-信息，7-调试） private String
			 * level; //事件种类（m-管理操作，a-用户操作，s-其他） private String type;
			 * //行为类别（3-违规行为，2-异常行为，1-一般行为） private String beType;
			 * //日志分类（1.本地文件审计，2.U
			 * 盘文件审计，3.开机关机审计，4.进程监控审计，5.打印监控审计，6.主机信息审计，7.系统日志审计，8.账户监控审计，9.
			 * 共享监控审计， 10.异常监控审计，11.主机配置审计，
			 * 12.移动介质审计，13.端口监控审计，14.服务监控审计，15.连接监控审计，16.网络流量检测，17.磁盘空间检测，18.
			 * 文件控制，19.光盘刻录审计） private String logType; //说明（保留字段） private String
			 * notes; //创建者（保留字段） private String createBy; //创建时间（保留字段） private
			 * Date createTime; //最后更新者（保留字段） private String updateBy;
			 * //最后更新时间（保留字段） private Date updateTime;
			 */

			resultList.add(refactorMap);
		}

		return BaseResult.success(resultList);
	}

	public static String getLevel(String level) {
		String levelDesc = "";
		switch (level) {
		case "0":
			levelDesc = "紧急";
			break;
		case "1":
			levelDesc = "警报";
			break;
		case "2":
			levelDesc = "关键";
			break;
		case "3":
			levelDesc = "错误";
			break;
		case "4":
			levelDesc = "警告";
			break;
		case "5":
			levelDesc = "通知";
			break;
		case "6":
			levelDesc = "信息";
			break;
		case "7":
			levelDesc = "调试";
			break;
			
		}
		return levelDesc;
	}

	public static String getType(String value) {
		String desc = "";
		switch (value) {
		case "m":
			desc = "管理操作";
			break;
		case "a":
			desc = "用户操作";
			break;
		case "s":
			desc = "其他";
			break;
			
		}
		return desc;
	}
	
	public static String getBeType(String value) {
		String desc = "";
		switch (value) {
		case "1":
			desc = "一般行为";
			break;
		case "2":
			desc = "异常行为";
			break;
		case "3":
			desc = "违规行为";
			break;
			
		}
		return desc;
	}
	
	public static String getResult(String value) {
		String desc = "";
		switch (value) {
		case "1":
			desc = "成功";
			break;
		case "0":
			desc = "失败";
			break;
			
		}
		return desc;
	}
	
	//日志分类（1.本地文件审计，2.U盘文件审计，3.开机关机审计，4.，5.，6.，7.，8.，9.， 10.，11.， 12.，13.，14.，15.，16.，17.，18.，19.）
	public static String getLogType(String value) {
		String desc = "";
		switch (value) {
		case "1":
			desc = "本地文件审计";
			break;
		case "2":
			desc = "U盘文件审计";
			break;
		case "3":
			desc = "开机关机审计";
			break;
		case "4":
			desc = "进程监控审计";
			break;
		case "5":
			desc = "打印监控审计";
			break;
		case "6":
			desc = "主机信息审计";
			break;
		case "7":
			desc = "系统日志审计";
			break;
		case "8":
			desc = "账户监控审计";
			break;
		case "9":
			desc = "共享监控审计";
			break;
		case "10":
			desc = "异常监控审计";
			break;
		case "11":
			desc = "主机配置审计";
			break;
		case "12":
			desc = "移动介质审计";
			break;
		case "13":
			desc = "端口监控审计";
			break;
		case "14":
			desc = "服务监控审计";
			break;
		case "15":
			desc = "连接监控审计";
		case "16":
			desc = "网络流量检测";
			break;
		case "17":
			desc = "磁盘空间检测";
			break;
		case "18":
			desc = "文件控制";
		case "19":
			desc = "光盘刻录审计";
			break;
		
			
		}
		return desc;
	}
}
