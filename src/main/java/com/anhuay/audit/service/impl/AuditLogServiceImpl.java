package com.anhuay.audit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.audit.dao.AuditLogDao;
import com.anhuay.audit.domain.AuditAlarmLogVO;
import com.anhuay.audit.domain.AuditLogDO;
import com.anhuay.audit.service.AuditLogService;



@Service
public class AuditLogServiceImpl implements AuditLogService {
	@Autowired
	private AuditLogDao logDao;
	
	@Override
	public AuditLogDO get(Long logid){
		return logDao.get(logid);
	}
	
	@Override
	public List<AuditLogDO> list(Map<String, Object> map){
		return logDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return logDao.count(map);
	}
	
	@Override
	public int save(AuditLogDO log){
		return logDao.save(log);
	}
	
	@Override
	public int update(AuditLogDO log){
		return logDao.update(log);
	}
	
	@Override
	public int remove(Long logid){
		return logDao.remove(logid);
	}
	
	@Override
	public int batchRemove(Long[] logids){
		return logDao.batchRemove(logids);
	}
	
	   
    @Override
    public int updateStatus(Long logid){
        return logDao.updateStatus(logid);
    }
    
    @Override
    public int batchUpdateStatus(Long[] logids){
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
}
