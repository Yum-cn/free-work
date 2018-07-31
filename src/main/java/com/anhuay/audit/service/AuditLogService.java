package com.anhuay.audit.service;

import com.anhuay.audit.domain.AuditLogDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-30 14:46:55
 */
public interface AuditLogService {
	
	AuditLogDO get(Long logid);
	
	List<AuditLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AuditLogDO log);
	
	int update(AuditLogDO log);
	
	int remove(Long logid);
	
	int batchRemove(Long[] logids);
	
    int updateStatus(Long logid);
    
    int batchUpdateStatus(Long[] logids);
}
