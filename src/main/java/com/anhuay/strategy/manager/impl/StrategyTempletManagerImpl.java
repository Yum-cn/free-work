package com.anhuay.strategy.manager.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
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
import com.anhuay.strategy.domain.OsAuditDO;
import com.anhuay.strategy.domain.StrategyTempletDO;
import com.anhuay.strategy.manager.StrategyTempletManager;
import com.anhuay.strategy.service.OsAuditService;
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

			if (extendParamJson.optLong("osAuditId") > 0) {
				osAuditId = extendParamJson.optLong("osAuditId");

				osAudit.setId(osAuditId);
				osAudit.setUpdateTime(System.currentTimeMillis() / 1000);
				JSONObject processMonitorJson = body.optJSONObject("processMonitorForm");
				if (processMonitorJson != null && checkJsonKey(processMonitorJson)) {
					osAudit.setProcessMonitorStatus(CommonEnum.STATUS.ONE.value);
					osAudit.setProcessMonitorRules(processMonitorJson.toString());
					saveCofigProcessForProcess(strategyTempletId, processMonitorJson.optString("process_black_rules"),
							processMonitorJson.optString("process_white_rules"),
							processMonitorJson.optInt("process_alarm_level"), 1);
					osAuditSaveTag = true;

				} else {
					osAudit.setProcessMonitorStatus(CommonEnum.STATUS.ZERO.value);
					osAudit.setProcessMonitorRules(null);
					saveCofigProcessForProcess(strategyTempletId, null, null, 0, 0);
					osAuditSaveTag = true;
				}

				JSONObject serverMonitorJson = body.optJSONObject("serverMonitorForm");
				if (processMonitorJson != null && checkJsonKey(serverMonitorJson)) {
					osAudit.setServerMonitorStatus(CommonEnum.STATUS.ONE.value);
					osAudit.setServerMonitorRules(serverMonitorJson.toString());
					saveCofigProcessForServer(strategyTempletId, serverMonitorJson.optString("server_black_rules"),
							serverMonitorJson.optString("server_white_rules"),
							serverMonitorJson.optString("server_connection_black_addresses"),
							serverMonitorJson.optInt("server_alarm_level"), 1);
					osAuditSaveTag = true;
				} else {
					osAudit.setServerMonitorStatus(CommonEnum.STATUS.ZERO.value);
					osAudit.setServerMonitorRules(null);
					saveCofigProcessForServer(strategyTempletId, null, null, null, 0, 0);

					osAuditSaveTag = true;
				}
				osAuditService.update(osAudit);

			} else {
				
				osAudit.setTempletId(strategyTempletId);
				osAudit.setStatus(CommonEnum.STATUS.ONE.value);
				osAudit.setUpdateTime(System.currentTimeMillis() / 1000);
				osAudit.setCreateTime(System.currentTimeMillis() / 1000);

				JSONObject processMonitorJson = body.optJSONObject("processMonitorForm");
				if (processMonitorJson != null && checkJsonKey(processMonitorJson)) {
					osAudit.setProcessMonitorStatus(CommonEnum.STATUS.ONE.value);
					osAudit.setProcessMonitorRules(processMonitorJson.toString());
					osAuditSaveTag = true;
					saveCofigProcessForProcess(strategyTempletId, processMonitorJson.optString("process_black_rules"),
							processMonitorJson.optString("process_white_rules"),
							processMonitorJson.optInt("process_alarm_level"), 1);
				}

				JSONObject serverMonitorJson = body.optJSONObject("serverMonitorForm");
				if (processMonitorJson != null && checkJsonKey(serverMonitorJson)) {
					osAudit.setServerMonitorStatus(CommonEnum.STATUS.ONE.value);
					osAudit.setServerMonitorRules(serverMonitorJson.toString());
					osAuditSaveTag = true;
					saveCofigProcessForServer(strategyTempletId, serverMonitorJson.optString("server_black_rules"),
							serverMonitorJson.optString("server_white_rules"),
							serverMonitorJson.optString("server_connection_black_addresses"),
							serverMonitorJson.optInt("server_alarm_level"), 1);

				}

				if (osAuditSaveTag) {
					osAuditId = idWorker.nextId();
					osAudit.setId(osAuditId);
					osAuditService.save(osAudit);
				}
			}

			if(osAuditId>0){
				StrategyTempletDO strategyTemplet = strategyTempletService.get(strategyTempletId);
				strategyTemplet.setOsAuditId(osAuditId);
				strategyTemplet.setUpdateTime(System.currentTimeMillis() / 1000);
				strategyTempletService.update(strategyTemplet);
			}

			return BaseResultHelper.success(osAudit);
		} catch (Exception e) {
			e.printStackTrace();
			return BaseResultHelper.error();
		}
	}

	/**
	 * 进程监控
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
			queryMap = jdbcTemplate.queryForMap(querySql, new Object[]{strategyTempletId,0});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(queryMap!=null&&NumberUtils.toLong(String.valueOf(queryMap.get("id")))>0){
			id = NumberUtils.toLong(String.valueOf(queryMap.get("id")));
		}else{
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
		// listObject.add(objectArray);
		//[954882595944927232, 954878891468525568, 0, 1, 11, 1, 22, 2,
		//2018-07-14 16:37:04, 1, 1, 超级管理员, 2018-07-14 16:37:04, 超级管理员, 2018-07-14 16:37:04]
		jdbcTemplate.update(processSql, objectArray);

	}

	/**
	 * 服务监控
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
			queryMap = jdbcTemplate.queryForMap(querySql, new Object[]{strategyTempletId,1});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(queryMap!=null&&NumberUtils.toLong(String.valueOf(queryMap.get("id")))>0){
			id = NumberUtils.toLong(String.valueOf(queryMap.get("id")));
		}else{
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
		// listObject.add(objectArray);

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
