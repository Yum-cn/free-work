package com.anhuay.strategy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.strategy.dao.OsAuditDao;
import com.anhuay.strategy.domain.OsAuditDO;
import com.anhuay.strategy.service.OsAuditService;



@Service
public class OsAuditServiceImpl implements OsAuditService {
	@Autowired
	private OsAuditDao osAuditDao;
	
	@Override
	public OsAuditDO get(Long id){
		return osAuditDao.get(id);
	}
	
	@Override
	public List<OsAuditDO> list(Map<String, Object> map){
		return osAuditDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return osAuditDao.count(map);
	}
	
	@Override
	public int save(OsAuditDO osAudit){
		return osAuditDao.save(osAudit);
	}
	
	@Override
	public int update(OsAuditDO osAudit){
		return osAuditDao.update(osAudit);
	}
	
	@Override
	public int remove(Long id){
		return osAuditDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return osAuditDao.batchRemove(ids);
	}
	
}
