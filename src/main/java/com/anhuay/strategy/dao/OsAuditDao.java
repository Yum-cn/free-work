package com.anhuay.strategy.dao;

import com.anhuay.strategy.domain.OsAuditDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 主机审计表
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-15 17:32:57
 */
@Mapper
public interface OsAuditDao {

	OsAuditDO get(Long id);
	
	List<OsAuditDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OsAuditDO osAudit);
	
	int update(OsAuditDO osAudit);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	int updateStatus(Long id);
	
	int batchUpdateStatus(Long[] ids);
}
