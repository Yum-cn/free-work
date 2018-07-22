package com.anhuay.strategy.service;

import com.anhuay.strategy.domain.OsStrategyDO;

import java.util.List;
import java.util.Map;

/**
 * 主机策略表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-22 17:07:47
 */
public interface OsStrategyService {
	
	OsStrategyDO get(Long id);
	
	List<OsStrategyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OsStrategyDO osStrategy);
	
	int update(OsStrategyDO osStrategy);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
    int updateStatus(Long id);
    
    int batchUpdateStatus(Long[] ids);
}
