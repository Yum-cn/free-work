package com.anhuay.strategy.manager.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.anhuay.common.utils.ShiroUtils;
import com.anhuay.strategy.domain.DeviceControlDO;
import com.anhuay.strategy.domain.DiskRecordDO;
import com.anhuay.strategy.domain.IrregularConnectionDO;
import com.anhuay.strategy.domain.OsAuditDO;
import com.anhuay.strategy.domain.SoftDistributeDO;
import com.anhuay.strategy.domain.StrategyTempletDO;
import com.anhuay.strategy.manager.StrategyTempletManager;
import com.anhuay.strategy.service.DeviceControlService;
import com.anhuay.strategy.service.DiskRecordService;
import com.anhuay.strategy.service.IrregularConnectionService;
import com.anhuay.strategy.service.OsAuditService;
import com.anhuay.strategy.service.SoftDistributeService;
import com.anhuay.strategy.service.StrategyTempletService;
import com.anhuay.system.domain.UserDO;
import com.common.constant.CommonEnum;
import com.common.manager.impl.BaseManagerImpl;
import com.common.util.BaseResult;
import com.common.util.BaseResultHelper;
import com.common.util.SqlUtil;

import net.sf.json.JSONObject;

@Service(value = "testManager")
public class StrategyTempletManagerImpl extends BaseManagerImpl implements StrategyTempletManager {

	private Logger logger = LoggerFactory.getLogger(StrategyTempletManagerImpl.class);

	@Autowired
	private StrategyTempletService strategyTempletService;
	@Autowired
	private OsAuditService osAuditService;
	@Autowired
	private DeviceControlService deviceControlService;
	@Autowired
	private DiskRecordService diskRecordService;
	@Autowired
	private SoftDistributeService softDistributeService;
	@Autowired
	private IrregularConnectionService irregularConnectionService;
	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 保存策略模板
	 * 
	 * @author Yum
	 */
	@Override
	public BaseResult<Object> saveStrategyTemplet(JSONObject body) {

		try {
			StrategyTempletDO strategyTemplet = new StrategyTempletDO();

			long strategyTempletId = body.optLong("id");
			if (body.optLong("id") > 0) {

				strategyTemplet = strategyTempletService.get(strategyTempletId);
				if (StringUtils.isNotBlank(body.optString("templetName"))) {
					strategyTemplet.setTempletName(body.optString("templetName"));
				}
				if (StringUtils.isNotBlank(body.optString("templetDesc"))) {
					strategyTemplet.setTempletDesc(body.optString("templetDesc"));
				}
				strategyTemplet.setUpdateTime(System.currentTimeMillis() / 1000);
				strategyTempletService.update(strategyTemplet);
			} else {

				strategyTempletId = idWorker.nextId();
				strategyTemplet.setId(strategyTempletId);
				strategyTemplet.setTempletName(body.optString("templetName"));
				strategyTemplet.setTempletDesc(body.optString("templetDesc"));
				strategyTemplet.setTempletType(CommonEnum.STATUS.ZERO.value);
				strategyTemplet.setStatus(CommonEnum.STATUS.ONE.value);
				strategyTemplet.setUpdateTime(System.currentTimeMillis() / 1000);
				strategyTemplet.setCreateTime(System.currentTimeMillis() / 1000);
				strategyTempletService.save(strategyTemplet);
			}

			return BaseResultHelper.success(strategyTemplet);
		} catch (Exception e) {
			e.printStackTrace();
			return BaseResultHelper.error();
		}
	}

	/**
	 * 保存策略模板-标签项
	 * 
	 * @author Yum
	 */
	@Override
	public BaseResult<Object> saveStrategyTempletTab(JSONObject body) {

		try {

			JSONObject extendParamJson = body.optJSONObject("extendParamForm");
			if (extendParamJson == null) {
				return BaseResult.unsuccess("数据异常！");
			}

			long strategyTempletId = extendParamJson.optLong("strategyTempletId");

			Long osAuditId = 0L;
			OsAuditDO osAudit = new OsAuditDO();
			boolean osAuditSaveTag = false;

			if (extendParamJson.optLong("osAuditId") > 0) {// EDIT
				osAuditId = extendParamJson.optLong("osAuditId");
				osAudit.setId(osAuditId);
				osAudit.setUpdateTime(System.currentTimeMillis() / 1000);
				
				saveLocalFile(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);
				saveDiskFile(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);
				saveUdiskFile(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);
				saveOsOnoff(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);
				
				saveProcessMonitor(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);
				
				savePrintMonitor(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);
				saveOsInfo(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);
				saveSystemLog(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);
				saveAccountMonitor(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);
				savePrintMonitor(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);
				saveOsInfo(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);
				saveSystemLog(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);
				saveShareMonitor(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);
				saveExceptionMonitor(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);
				saveOsConfig(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);
				saveMoveMedia(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);
				savePortMonitor(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);

				saveServerMonitor(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);
				
				saveConnectionMonitor(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);
				saveNetworkFlow(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);
				saveDiskSpace(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);
				saveFileControl(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.EDIT.name);

				osAuditService.update(osAudit);

			} else {// ADD

				osAudit.setTempletId(strategyTempletId);
				osAudit.setStatus(CommonEnum.STATUS.ONE.value);
				osAudit.setUpdateTime(System.currentTimeMillis() / 1000);
				osAudit.setCreateTime(System.currentTimeMillis() / 1000);

				// 本地文件
				osAuditSaveTag = saveLocalFile(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.ADD.name) ? true: osAuditSaveTag;
				// 光盘文件
				osAuditSaveTag = saveDiskFile(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.ADD.name) ? true: osAuditSaveTag;
				// U盘文件
				osAuditSaveTag = saveUdiskFile(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.ADD.name) ? true: osAuditSaveTag;
				// 开机关机
				osAuditSaveTag = saveOsOnoff(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.ADD.name) ? true: osAuditSaveTag;

				// 进程
				osAuditSaveTag = saveProcessMonitor(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.ADD.name)? true : osAuditSaveTag;

				// 打印
				osAuditSaveTag = savePrintMonitor(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.ADD.name) ? true: osAuditSaveTag;
				// 主机信息
				osAuditSaveTag = saveOsInfo(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.ADD.name) ? true: osAuditSaveTag;
				// 系统日志
				osAuditSaveTag = saveSystemLog(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.ADD.name) ? true: osAuditSaveTag;
				// 账户监控
				osAuditSaveTag = saveAccountMonitor(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.ADD.name)? true : osAuditSaveTag;
				// 共享
				osAuditSaveTag = saveShareMonitor(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.ADD.name) ? true: osAuditSaveTag;
				// 异常监控
				osAuditSaveTag = saveExceptionMonitor(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.ADD.name)? true : osAuditSaveTag;
				// 主机配置
				osAuditSaveTag = saveOsConfig(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.ADD.name) ? true: osAuditSaveTag;
				// 移动介质
				osAuditSaveTag = saveMoveMedia(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.ADD.name) ? true: osAuditSaveTag;
				// 端口监控
				osAuditSaveTag = savePortMonitor(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.ADD.name) ? true: osAuditSaveTag;

				// 服务监控
				osAuditSaveTag = saveServerMonitor(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.ADD.name)? true : osAuditSaveTag;

				// 连接监控
				osAuditSaveTag = saveConnectionMonitor(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.ADD.name)? true : osAuditSaveTag;
				// 网络流量
				osAuditSaveTag = saveNetworkFlow(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.ADD.name) ? true: osAuditSaveTag;
				// 磁盘空间
				osAuditSaveTag = saveDiskSpace(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.ADD.name) ? true: osAuditSaveTag;
				// 文件控制
				osAuditSaveTag = saveFileControl(body, strategyTempletId, osAudit, CommonEnum.SAVETYPE.ADD.name) ? true: osAuditSaveTag;

				if (osAuditSaveTag) {
					osAuditId = idWorker.nextId();
					osAudit.setId(osAuditId);
					osAuditService.save(osAudit);
				}
			}

			if (osAuditId > 0) {
				StrategyTempletDO strategyTemplet = strategyTempletService.get(strategyTempletId);
				strategyTemplet.setOsAuditId(osAuditId);
				strategyTemplet.setUpdateTime(System.currentTimeMillis() / 1000);
				strategyTemplet.setStatus(CommonEnum.STATUS.ONE.value);
				strategyTempletService.update(strategyTemplet);
			}
			
			//违规外联
			IrregularConnectionDO irregularConnection = saveIrregularConnection(body, extendParamJson,
					strategyTempletId);
			//设备控制
			DeviceControlDO deviceControl = saveDeviceControl(body, extendParamJson,
					strategyTempletId);
			//光盘刻录
			DiskRecordDO diskRecord = saveDiskRecord(body, extendParamJson,
					strategyTempletId);
			//软件分发
			SoftDistributeDO softDistribute = saveSoftDistribute(body, extendParamJson,
					strategyTempletId);

			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("osAuditId", osAudit.getId());
			resultMap.put("irregularConnectionId", irregularConnection.getId());
			resultMap.put("deviceControlId", deviceControl.getId());
			resultMap.put("diskRecordId", diskRecord.getId());
			resultMap.put("softDistributeId", softDistribute.getId());
			
			return BaseResultHelper.success(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			return BaseResultHelper.error();
		}
	}

	
	
	private SoftDistributeDO saveSoftDistribute(JSONObject body, JSONObject extendParamJson,
			long strategyTempletId) {
		JSONObject saveJson = body.optJSONObject("softDistributeForm");
		Long id = 0L;
		boolean saveTag = false;
		SoftDistributeDO bean = new SoftDistributeDO();
		if (saveJson != null && checkJsonKey(saveJson)) {
			
			bean.setSoftDistributeStatus(NumberUtils.toInt(saveJson.optString("soft_distribute_status")));
			bean.setSoftDistributeRules(saveJson.toString());
			saveTag = true;
		}else{
			bean.setSoftDistributeStatus(CommonEnum.STATUS.ZERO.value);
			bean.setSoftDistributeRules(null);
		}
		
		if (extendParamJson.optLong("softDistributeId") > 0) {// EDIT
			id = extendParamJson.optLong("softDistributeId");
			bean.setId(id);
			bean.setUpdateTime(System.currentTimeMillis() / 1000);
			softDistributeService.update(bean);

		} else {// ADD

			bean.setTempletId(strategyTempletId);
			bean.setStatus(CommonEnum.STATUS.ONE.value);
			bean.setUpdateTime(System.currentTimeMillis() / 1000);
			bean.setCreateTime(System.currentTimeMillis() / 1000);

			if (saveTag) {
				id = idWorker.nextId();
				bean.setId(id);
				softDistributeService.save(bean);
			}
		}
		if (id > 0) {
			StrategyTempletDO strategyTemplet = strategyTempletService.get(strategyTempletId);
			strategyTemplet.setSoftDistributeId(id);
			strategyTemplet.setUpdateTime(System.currentTimeMillis() / 1000);
			strategyTempletService.update(strategyTemplet);
		}
		return bean;
	}
	
	private DiskRecordDO saveDiskRecord(JSONObject body, JSONObject extendParamJson,
			long strategyTempletId) {
		JSONObject saveJson = body.optJSONObject("diskRecordForm");
		Long id = 0L;
		boolean saveTag = false;
		DiskRecordDO bean = new DiskRecordDO();
		if (saveJson != null && checkJsonKey(saveJson)) {
			
			bean.setDiskRecordStatus(NumberUtils.toInt(saveJson.optString("disk_record_status")));
			bean.setDiskRecordRules(saveJson.toString());
			saveTag = true;
		}else{
			bean.setDiskRecordStatus(CommonEnum.STATUS.ZERO.value);
			bean.setDiskRecordRules(null);
		}
		
		if (extendParamJson.optLong("diskRecordId") > 0) {// EDIT
			id = extendParamJson.optLong("diskRecordId");
			bean.setId(id);
			bean.setUpdateTime(System.currentTimeMillis() / 1000);
			diskRecordService.update(bean);
			
		} else {// ADD
			
			bean.setTempletId(strategyTempletId);
			bean.setStatus(CommonEnum.STATUS.ONE.value);
			bean.setUpdateTime(System.currentTimeMillis() / 1000);
			bean.setCreateTime(System.currentTimeMillis() / 1000);
			
			if (saveTag) {
				id = idWorker.nextId();
				bean.setId(id);
				diskRecordService.save(bean);
			}
		}
		if (id > 0) {
			StrategyTempletDO strategyTemplet = strategyTempletService.get(strategyTempletId);
			strategyTemplet.setDiskRecordId(id);
			strategyTemplet.setUpdateTime(System.currentTimeMillis() / 1000);
			strategyTempletService.update(strategyTemplet);
		}
		return bean;
	}
	
	private DeviceControlDO saveDeviceControl(JSONObject body, JSONObject extendParamJson,
			long strategyTempletId) {
		JSONObject saveJson = body.optJSONObject("deviceControlForm");
		Long id = 0L;
		boolean saveTag = false;
		DeviceControlDO bean = new DeviceControlDO();
		if (saveJson != null && checkJsonKey(saveJson)) {
			
			bean.setDeviceControlStatus(NumberUtils.toInt(saveJson.optString("device_control_status")));
			bean.setDeviceControlRules(saveJson.toString());
			saveTag = true;
		}else{
			bean.setDeviceControlStatus(CommonEnum.STATUS.ZERO.value);
			bean.setDeviceControlRules(null);
		}
		
		if (extendParamJson.optLong("deviceControlId") > 0) {// EDIT
			id = extendParamJson.optLong("deviceControlId");
			bean.setId(id);
			bean.setUpdateTime(System.currentTimeMillis() / 1000);
			deviceControlService.update(bean);

		} else {// ADD

			bean.setTempletId(strategyTempletId);
			bean.setStatus(CommonEnum.STATUS.ONE.value);
			bean.setUpdateTime(System.currentTimeMillis() / 1000);
			bean.setCreateTime(System.currentTimeMillis() / 1000);

			if (saveTag) {
				id = idWorker.nextId();
				bean.setId(id);
				deviceControlService.save(bean);
			}
		}
		if (id > 0) {
			StrategyTempletDO strategyTemplet = strategyTempletService.get(strategyTempletId);
			strategyTemplet.setDeviceControlId(id);
			strategyTemplet.setUpdateTime(System.currentTimeMillis() / 1000);
			strategyTempletService.update(strategyTemplet);
		}
		return bean;
	}
	
	private IrregularConnectionDO saveIrregularConnection(JSONObject body, JSONObject extendParamJson,
			long strategyTempletId) {
		JSONObject saveJson = body.optJSONObject("irregularConnectionForm");
		Long id = 0L;
		boolean saveTag = false;
		IrregularConnectionDO bean = new IrregularConnectionDO();
		if (saveJson != null && checkJsonKey(saveJson)) {
			
			bean.setMonitorStatus(NumberUtils.toInt(saveJson.optString("monitor_status")));
			bean.setMonitorRules(saveJson.toString());
			saveTag = true;
		}else{
			bean.setMonitorStatus(CommonEnum.STATUS.ZERO.value);
			bean.setMonitorRules(null);
		}
		
		if (extendParamJson.optLong("irregularConnectionId") > 0) {// EDIT
			id = extendParamJson.optLong("irregularConnectionId");
			bean.setId(id);
			bean.setUpdateTime(System.currentTimeMillis() / 1000);
			irregularConnectionService.update(bean);

		} else {// ADD

			bean.setTempletId(strategyTempletId);
			bean.setStatus(CommonEnum.STATUS.ONE.value);
			bean.setUpdateTime(System.currentTimeMillis() / 1000);
			bean.setCreateTime(System.currentTimeMillis() / 1000);

			if (saveTag) {
				id = idWorker.nextId();
				bean.setId(id);
				irregularConnectionService.save(bean);
			}
		}
		if (id > 0) {
			StrategyTempletDO strategyTemplet = strategyTempletService.get(strategyTempletId);
			strategyTemplet.setIrregularConnectionId(id);
			strategyTemplet.setUpdateTime(System.currentTimeMillis() / 1000);
			strategyTempletService.update(strategyTemplet);
		}
		return bean;
	}

	
	private boolean saveLocalFile(JSONObject body, long strategyTempletId, OsAuditDO osAudit, String saveType) {
		boolean osAuditSaveTag = false;
		
		JSONObject saveJson = body.optJSONObject("localFileForm");
		if (saveJson != null && checkJsonKey(saveJson)) {
			osAudit.setLocalFileStatus(CommonEnum.STATUS.ONE.value);
			osAudit.setLocalFileRules(saveJson.toString());
			osAuditSaveTag = true;
			return osAuditSaveTag;
		}
		
		if (StringUtils.equals(saveType, CommonEnum.SAVETYPE.EDIT.name)) {
			osAudit.setLocalFileStatus(CommonEnum.STATUS.ZERO.value);
			osAudit.setLocalFileRules(null);
		}
		
		return osAuditSaveTag;
	}

	private boolean saveDiskFile(JSONObject body, long strategyTempletId, OsAuditDO osAudit, String saveType) {
		boolean osAuditSaveTag = false;

		JSONObject saveJson = body.optJSONObject("diskFileForm");
		if (saveJson != null && checkJsonKey(saveJson)) {
			osAudit.setDiskFileStatus(CommonEnum.STATUS.ONE.value);
			osAudit.setDiskFileRules(saveJson.toString());
			osAuditSaveTag = true;
			return osAuditSaveTag;
		}

		if (StringUtils.equals(saveType, CommonEnum.SAVETYPE.EDIT.name)) {
			osAudit.setDiskFileStatus(CommonEnum.STATUS.ZERO.value);
			osAudit.setDiskFileRules(null);
		}
		
		return osAuditSaveTag;
	}

	private boolean saveUdiskFile(JSONObject body, long strategyTempletId, OsAuditDO osAudit, String saveType) {
		boolean osAuditSaveTag = false;

		JSONObject saveJson = body.optJSONObject("udiskFileForm");
		if (saveJson != null && checkJsonKey(saveJson)) {
			osAudit.setUdiskFileStatus(CommonEnum.STATUS.ONE.value);
			osAudit.setUdiskFileRules(saveJson.toString());
			osAuditSaveTag = true;
			return osAuditSaveTag;
		}

		if (StringUtils.equals(saveType, CommonEnum.SAVETYPE.EDIT.name)) {
			osAudit.setUdiskFileStatus(CommonEnum.STATUS.ZERO.value);
			osAudit.setUdiskFileRules(null);
		}

		return osAuditSaveTag;
	}

	private boolean saveOsOnoff(JSONObject body, long strategyTempletId, OsAuditDO osAudit, String saveType) {
		boolean osAuditSaveTag = false;

		JSONObject saveJson = body.optJSONObject("osOnoffForm");
		if (saveJson != null && checkJsonKey(saveJson)) {
			osAudit.setOsOnoffStatus(CommonEnum.STATUS.ONE.value);
			osAudit.setOsOnoffRules(saveJson.toString());
			osAuditSaveTag = true;
			return osAuditSaveTag;
		}

		if (StringUtils.equals(saveType, CommonEnum.SAVETYPE.EDIT.name)) {
			osAudit.setOsOnoffStatus(CommonEnum.STATUS.ZERO.value);
			osAudit.setOsOnoffRules(null);
		}
		return osAuditSaveTag;
	}

	private boolean savePrintMonitor(JSONObject body, long strategyTempletId, OsAuditDO osAudit, String saveType) {
		boolean osAuditSaveTag = false;

		JSONObject saveJson = body.optJSONObject("printMonitorForm");
		if (saveJson != null && checkJsonKey(saveJson)) {
			osAudit.setPrintMonitorStatus(CommonEnum.STATUS.ONE.value);
			osAudit.setPrintMonitorRules(saveJson.toString());
			osAuditSaveTag = true;
			return osAuditSaveTag;
		}

		if (StringUtils.equals(saveType, CommonEnum.SAVETYPE.EDIT.name)) {
			osAudit.setPrintMonitorStatus(CommonEnum.STATUS.ZERO.value);
			osAudit.setPrintMonitorRules(null);
		}
		return osAuditSaveTag;
	}

	private boolean saveOsInfo(JSONObject body, long strategyTempletId, OsAuditDO osAudit, String saveType) {
		boolean osAuditSaveTag = false;

		JSONObject saveJson = body.optJSONObject("osInfoForm");
		if (saveJson != null && checkJsonKey(saveJson)) {
			osAudit.setOsInfoStatus(CommonEnum.STATUS.ONE.value);
			osAudit.setOsInfoRules(saveJson.toString());
			osAuditSaveTag = true;
			return osAuditSaveTag;
		}

		if (StringUtils.equals(saveType, CommonEnum.SAVETYPE.EDIT.name)) {
			osAudit.setOsInfoStatus(CommonEnum.STATUS.ZERO.value);
			osAudit.setOsInfoRules(null);
		}
		return osAuditSaveTag;
	}

	private boolean saveSystemLog(JSONObject body, long strategyTempletId, OsAuditDO osAudit, String saveType) {
		boolean osAuditSaveTag = false;

		JSONObject saveJson = body.optJSONObject("systemLogForm");
		if (saveJson != null && checkJsonKey(saveJson)) {
			osAudit.setSystemLogStatus(CommonEnum.STATUS.ONE.value);
			osAudit.setSystemLogRules(saveJson.toString());
			osAuditSaveTag = true;
			return osAuditSaveTag;
		}

		if (StringUtils.equals(saveType, CommonEnum.SAVETYPE.EDIT.name)) {
			osAudit.setSystemLogStatus(CommonEnum.STATUS.ZERO.value);
			osAudit.setSystemLogRules(null);
		}
		return osAuditSaveTag;
	}

	private boolean saveAccountMonitor(JSONObject body, long strategyTempletId, OsAuditDO osAudit, String saveType) {
		boolean osAuditSaveTag = false;

		JSONObject saveJson = body.optJSONObject("accountMonitorForm");
		if (saveJson != null && checkJsonKey(saveJson)) {
			osAudit.setAccountMonitorStatus(CommonEnum.STATUS.ONE.value);
			osAudit.setAccountMonitorRules(saveJson.toString());
			osAuditSaveTag = true;
			return osAuditSaveTag;
		}

		if (StringUtils.equals(saveType, CommonEnum.SAVETYPE.EDIT.name)) {
			osAudit.setAccountMonitorStatus(CommonEnum.STATUS.ZERO.value);
			osAudit.setAccountMonitorRules(null);
		}
		return osAuditSaveTag;
	}

	private boolean saveShareMonitor(JSONObject body, long strategyTempletId, OsAuditDO osAudit, String saveType) {
		boolean osAuditSaveTag = false;

		JSONObject saveJson = body.optJSONObject("shareMonitorForm");
		if (saveJson != null && checkJsonKey(saveJson)) {
			osAudit.setShareMonitorStatus(CommonEnum.STATUS.ONE.value);
			osAudit.setShareMonitorRules(saveJson.toString());
			osAuditSaveTag = true;
			return osAuditSaveTag;
		}

		if (StringUtils.equals(saveType, CommonEnum.SAVETYPE.EDIT.name)) {
			osAudit.setShareMonitorStatus(CommonEnum.STATUS.ZERO.value);
			osAudit.setShareMonitorRules(null);
		}
		return osAuditSaveTag;
	}

	private boolean saveExceptionMonitor(JSONObject body, long strategyTempletId, OsAuditDO osAudit, String saveType) {
		boolean osAuditSaveTag = false;

		JSONObject saveJson = body.optJSONObject("exceptionMonitorForm");
		if (saveJson != null && checkJsonKey(saveJson)) {
			osAudit.setExceptionMonitorStatus(CommonEnum.STATUS.ONE.value);
			osAudit.setExceptionMonitorRules(saveJson.toString());
			osAuditSaveTag = true;
			return osAuditSaveTag;
		}

		if (StringUtils.equals(saveType, CommonEnum.SAVETYPE.EDIT.name)) {
			osAudit.setExceptionMonitorStatus(CommonEnum.STATUS.ZERO.value);
			osAudit.setExceptionMonitorRules(null);
		}
		return osAuditSaveTag;
	}

	private boolean saveOsConfig(JSONObject body, long strategyTempletId, OsAuditDO osAudit, String saveType) {
		boolean osAuditSaveTag = false;

		JSONObject saveJson = body.optJSONObject("osConfigForm");
		if (saveJson != null && checkJsonKey(saveJson)) {
			osAudit.setOsConfigStatus(CommonEnum.STATUS.ONE.value);
			osAudit.setOsConfigRules(saveJson.toString());
			osAuditSaveTag = true;
			return osAuditSaveTag;
		}

		if (StringUtils.equals(saveType, CommonEnum.SAVETYPE.EDIT.name)) {
			osAudit.setOsConfigStatus(CommonEnum.STATUS.ZERO.value);
			osAudit.setOsConfigRules(null);
		}
		return osAuditSaveTag;
	}

	private boolean saveMoveMedia(JSONObject body, long strategyTempletId, OsAuditDO osAudit, String saveType) {
		boolean osAuditSaveTag = false;

		JSONObject saveJson = body.optJSONObject("moveMediaForm");
		if (saveJson != null && checkJsonKey(saveJson)) {
			osAudit.setMoveMediaStatus(CommonEnum.STATUS.ONE.value);
			osAudit.setMoveMediaRules(saveJson.toString());
			osAuditSaveTag = true;
			return osAuditSaveTag;
		}

		if (StringUtils.equals(saveType, CommonEnum.SAVETYPE.EDIT.name)) {
			osAudit.setMoveMediaStatus(CommonEnum.STATUS.ZERO.value);
			osAudit.setMoveMediaRules(null);
		}
		return osAuditSaveTag;
	}

	private boolean savePortMonitor(JSONObject body, long strategyTempletId, OsAuditDO osAudit, String saveType) {
		boolean osAuditSaveTag = false;

		JSONObject saveJson = body.optJSONObject("portMonitorForm");
		if (saveJson != null && checkJsonKey(saveJson)) {
			osAudit.setPortMonitorStatus(CommonEnum.STATUS.ONE.value);
			osAudit.setPortMonitorRules(saveJson.toString());
			osAuditSaveTag = true;
			return osAuditSaveTag;
		}

		if (StringUtils.equals(saveType, CommonEnum.SAVETYPE.EDIT.name)) {
			osAudit.setPortMonitorStatus(CommonEnum.STATUS.ZERO.value);
			osAudit.setPortMonitorRules(null);
		}
		return osAuditSaveTag;
	}

	private boolean saveConnectionMonitor(JSONObject body, long strategyTempletId, OsAuditDO osAudit, String saveType) {
		boolean osAuditSaveTag = false;

		JSONObject saveJson = body.optJSONObject("connectionMonitorForm");
		if (saveJson != null && checkJsonKey(saveJson)) {
			osAudit.setConnectionMonitorStatus(CommonEnum.STATUS.ONE.value);
			osAudit.setConnectionMonitorRules(saveJson.toString());
			osAuditSaveTag = true;
			return osAuditSaveTag;
		}

		if (StringUtils.equals(saveType, CommonEnum.SAVETYPE.EDIT.name)) {
			osAudit.setConnectionMonitorStatus(CommonEnum.STATUS.ZERO.value);
			osAudit.setConnectionMonitorRules(null);
		}
		return osAuditSaveTag;
	}

	private boolean saveNetworkFlow(JSONObject body, long strategyTempletId, OsAuditDO osAudit, String saveType) {
		boolean osAuditSaveTag = false;

		JSONObject saveJson = body.optJSONObject("networkFlowForm");
		if (saveJson != null && checkJsonKey(saveJson)) {
			osAudit.setNetworkFlowStatus(CommonEnum.STATUS.ONE.value);
			osAudit.setNetworkFlowRules(saveJson.toString());
			osAuditSaveTag = true;
			return osAuditSaveTag;
		}

		if (StringUtils.equals(saveType, CommonEnum.SAVETYPE.EDIT.name)) {
			osAudit.setNetworkFlowStatus(CommonEnum.STATUS.ZERO.value);
			osAudit.setNetworkFlowRules(null);
		}
		return osAuditSaveTag;
	}

	private boolean saveDiskSpace(JSONObject body, long strategyTempletId, OsAuditDO osAudit, String saveType) {
		boolean osAuditSaveTag = false;

		JSONObject saveJson = body.optJSONObject("diskSpaceForm");
		if (saveJson != null && checkJsonKey(saveJson)) {
			osAudit.setDiskSpaceStatus(CommonEnum.STATUS.ONE.value);
			osAudit.setDiskSpaceRules(saveJson.toString());
			osAuditSaveTag = true;
			return osAuditSaveTag;
		}

		if (StringUtils.equals(saveType, CommonEnum.SAVETYPE.EDIT.name)) {
			osAudit.setDiskSpaceStatus(CommonEnum.STATUS.ZERO.value);
			osAudit.setDiskSpaceRules(null);
		}
		return osAuditSaveTag;
	}

	/**
	 * 文件控制
	 * 
	 * @author Yum
	 */
	private boolean saveFileControl(JSONObject body, long strategyTempletId, OsAuditDO osAudit, String saveType) {
		
		boolean osAuditSaveTag = false;

		JSONObject saveJson = body.optJSONObject("fileControlForm");
		if (saveJson != null && checkJsonKey(saveJson)) {
			osAudit.setFileControlStatus(CommonEnum.STATUS.ONE.value);
			osAudit.setFileControlRules(saveJson.toString());
			osAuditSaveTag = true;
			return osAuditSaveTag;
		}

		if (StringUtils.equals(saveType, CommonEnum.SAVETYPE.EDIT.name)) {
			osAudit.setFileControlStatus(CommonEnum.STATUS.ZERO.value);
			osAudit.setFileControlRules(null);
		}
		return osAuditSaveTag;
	}
	
	/**
	 * 保存服务监控
	 * 
	 * @author Yum
	 */
	private boolean saveServerMonitor(JSONObject body, long strategyTempletId, OsAuditDO osAudit, String saveType) {

		boolean osAuditSaveTag = false;

		JSONObject serverMonitorJson = body.optJSONObject("serverMonitorForm");
		if (serverMonitorJson != null && checkJsonKey(serverMonitorJson)) {
			osAudit.setServerMonitorStatus(CommonEnum.STATUS.ONE.value);
			osAudit.setServerMonitorRules(serverMonitorJson.toString());
			osAuditSaveTag = true;
			saveCofigProcessForServer(strategyTempletId, serverMonitorJson.optString("server_black_rules"),
					serverMonitorJson.optString("server_white_rules"),
					serverMonitorJson.optString("server_connection_black_addresses"),
					serverMonitorJson.optInt("server_alarm_level"), 1);
			return osAuditSaveTag;
		}

		if (StringUtils.equals(saveType, CommonEnum.SAVETYPE.EDIT.name)) {
			osAudit.setServerMonitorStatus(CommonEnum.STATUS.ZERO.value);
			osAudit.setServerMonitorRules(null);
			saveCofigProcessForServer(strategyTempletId, null, null, null, 0, 0);
		}

		return osAuditSaveTag;
	}

	/**
	 * 保存进程监控
	 * 
	 * @author Yum
	 */
	private boolean saveProcessMonitor(JSONObject body, long strategyTempletId, OsAuditDO osAudit, String saveType) {

		boolean osAuditSaveTag = false;

		JSONObject processMonitorJson = body.optJSONObject("processMonitorForm");
		if (processMonitorJson != null && checkJsonKey(processMonitorJson)) {
			osAudit.setProcessMonitorStatus(CommonEnum.STATUS.ONE.value);
			osAudit.setProcessMonitorRules(processMonitorJson.toString());
			osAuditSaveTag = true;
			saveCofigProcessForProcess(strategyTempletId, processMonitorJson.optString("process_black_rules"),
					processMonitorJson.optString("process_white_rules"),
					processMonitorJson.optInt("process_alarm_level"), 1);
			return osAuditSaveTag;
		}

		if (StringUtils.equals(saveType, CommonEnum.SAVETYPE.EDIT.name)) {
			osAudit.setProcessMonitorStatus(CommonEnum.STATUS.ZERO.value);
			osAudit.setProcessMonitorRules(null);
			saveCofigProcessForProcess(strategyTempletId, null, null, 0, 0);
		}

		return osAuditSaveTag;
	}


	/**
	 * 保存外部进程监控数据
	 * 
	 * @author Yum
	 */
	public void saveCofigProcessForProcess(long strategyTempletId, String blackRules, String whiteRules, int alarmLevel,
			int isUsed) {

		String processSql = "INSERT INTO `cfg_process` (`id`,`strategy_templet_id`, `is_service`, `is_black`, `black_short_name`, `is_white`"
				+ ", `white_short_name`,`alarm_level`, `send_time`, `is_used`, `enable_flag`, `create_by`"
				+ ", `create_time`, `update_by`, `update_time`)  VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
				+ " ON DUPLICATE KEY UPDATE is_black=VALUES(is_black),black_short_name=VALUES(black_short_name)"
				+ ",is_white=VALUES(is_white),white_short_name=VALUES(white_short_name)"
				+ ",alarm_level=VALUES(alarm_level),send_time=VALUES(send_time)"
				+ ",is_used=VALUES(is_used),enable_flag=VALUES(enable_flag),update_by=VALUES(update_by)"
				+ " ,update_time=VALUES(update_time) ";

		UserDO user = ShiroUtils.getUser();
		// List<Object[]> listObject = new ArrayList<Object[]>();

		Object[] objectArray = new Object[15];
		long id = 0;
		String querySql = "select * from `cfg_process` where `strategy_templet_id`=? and `is_service`=? limit 1";
		Map<String, Object> queryMap = null;
		try {
			queryMap = jdbcTemplate.queryForMap(querySql, new Object[] { strategyTempletId, 0 });
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if (queryMap != null && NumberUtils.toLong(String.valueOf(queryMap.get("id"))) > 0) {
			id = NumberUtils.toLong(String.valueOf(queryMap.get("id")));
		} else {
			id = idWorker.nextId();
		}

		objectArray[0] = id;
		objectArray[1] = strategyTempletId;
		objectArray[2] = 0;

		if (StringUtils.isNotBlank(blackRules)) {
			objectArray[3] = 1;
			objectArray[4] = blackRules;
		} else {
			objectArray[3] = 0;
			objectArray[4] = null;
		}
		if (StringUtils.isNotBlank(whiteRules)) {
			objectArray[5] = 1;
			objectArray[6] = whiteRules;
		} else {
			objectArray[5] = 0;
			objectArray[6] = null;
		}
		objectArray[7] = alarmLevel;

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sendTime = df.format(new Date());

		objectArray[8] = sendTime;
		objectArray[9] = isUsed;
		objectArray[10] = 1;
		objectArray[11] = user != null ? user.getName() : null;
		objectArray[12] = sendTime;
		objectArray[13] = user != null ? user.getName() : null;
		objectArray[14] = sendTime;
		jdbcTemplate.update(processSql, objectArray);

	}

	/**
	 * 保存外部服务监控数据
	 * 
	 * @author Yum
	 */
	public void saveCofigProcessForServer(long strategyTempletId, String blackRules, String whiteRules,
			String netAddress, int alarmLevel, int isUsed) {

		String processSql = "INSERT INTO `cfg_process` (`id`,`strategy_templet_id`, `is_service`, `is_black`, `black_short_name`, `is_white`"
				+ ", `white_short_name`, `net_status`,`net_address`,`alarm_level`, `send_time`, `is_used`, `enable_flag`, `create_by`"
				+ ", `create_time`, `update_by`, `update_time`)  VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
				+ " ON DUPLICATE KEY UPDATE is_black=VALUES(is_black),black_short_name=VALUES(black_short_name)"
				+ ",is_white=VALUES(is_white),white_short_name=VALUES(white_short_name)"
				+ ",net_status=VALUES(net_status),net_address=VALUES(net_address),alarm_level=VALUES(alarm_level),send_time=VALUES(send_time)"
				+ ",is_used=VALUES(is_used),enable_flag=VALUES(enable_flag),update_by=VALUES(update_by)"
				+ " ,update_time=VALUES(update_time) ";

		UserDO user = ShiroUtils.getUser();
		// List<Object[]> listObject = new ArrayList<Object[]>();

		Object[] objectArray = new Object[17];
		long id = 0;
		String querySql = "select * from `cfg_process` where `strategy_templet_id`=? and `is_service`=? limit 1";
		Map<String, Object> queryMap = null;
		try {
			queryMap = jdbcTemplate.queryForMap(querySql, new Object[] { strategyTempletId, 1 });
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if (queryMap != null && NumberUtils.toLong(String.valueOf(queryMap.get("id"))) > 0) {
			id = NumberUtils.toLong(String.valueOf(queryMap.get("id")));
		} else {
			id = idWorker.nextId();
		}

		objectArray[0] = id;
		objectArray[1] = strategyTempletId;
		objectArray[2] = 1;

		if (StringUtils.isNotBlank(blackRules)) {
			objectArray[3] = 1;
			objectArray[4] = blackRules;
		} else {
			objectArray[3] = 0;
			objectArray[4] = null;
		}
		if (StringUtils.isNotBlank(whiteRules)) {
			objectArray[5] = 1;
			objectArray[6] = whiteRules;
		} else {
			objectArray[5] = 0;
			objectArray[6] = null;
		}
		if (StringUtils.isNotBlank(netAddress)) {
			objectArray[7] = 1;
			objectArray[8] = netAddress;
		} else {
			objectArray[7] = 0;
			objectArray[8] = null;
		}
		objectArray[9] = alarmLevel;

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sendTime = df.format(new Date());

		objectArray[10] = sendTime;
		objectArray[11] = isUsed;
		objectArray[12] = 1;
		objectArray[13] = user != null ? user.getName() : null;
		objectArray[14] = sendTime;
		objectArray[15] = user != null ? user.getName() : null;
		objectArray[16] = sendTime;

		jdbcTemplate.update(processSql, objectArray);

	}

	/**
	 * 检测空值
	 * 
	 * @author Yum
	 */
	public static boolean checkJsonKey(JSONObject jsonObject) {

		boolean jsonBlankTag = false;

		if (jsonObject == null) {
			return jsonBlankTag;
		}

		Collection collection = jsonObject.values();
		for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
			String value = (String) iterator.next();
			if (StringUtils.isNotBlank(value)) {
				jsonBlankTag = true;
				return jsonBlankTag;
			}
		}

		return jsonBlankTag;
	}

	/**
	 * 逻辑删除
	 * 
	 * @author Yum
	 */
	@Override
	public BaseResult<Object> removeStrategyTemplet(Long id) {

		try {
			UserDO user = ShiroUtils.getUser();

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String updateTime = df.format(new Date());

			strategyTempletService.updateStatus(id);
			String sql = "update `cfg_process` set enable_flag = ?,update_by=?,update_time=? where strategy_templet_id = ?";
			jdbcTemplate.update(sql, new Object[] { 0, user != null ? user.getName() : "", updateTime, id });
			return BaseResultHelper.success(null);
		} catch (Exception e) {
			e.printStackTrace();
			return BaseResultHelper.error();
		}
	}

	/**
	 * 批量逻辑删除
	 * 
	 * @author Yum
	 */
	@Override
	public BaseResult<Object> batchRemoveStrategyTemplet(Long[] ids) {

		try {
			UserDO user = ShiroUtils.getUser();

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String updateTime = df.format(new Date());

			strategyTempletService.batchUpdateStatus(ids);

			List list = Arrays.asList(ids);
			String sqlPart = SqlUtil.getSQLIn(list, 500, " strategy_templet_id ");
			String sql = "update `cfg_process` set enable_flag = ?,update_by=?,update_time=? where " + sqlPart;
			jdbcTemplate.update(sql, new Object[] { 0, user != null ? user.getName() : "", updateTime });
			return BaseResultHelper.success(null);
		} catch (Exception e) {
			e.printStackTrace();
			return BaseResultHelper.error();
		}
	}

}
