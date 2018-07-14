package com.anhuay.strategy.service;

import com.anhuay.strategy.domain.StrategyTempletDO;

import java.util.List;
import java.util.Map;

/**
 * 策略模板表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-07 14:05:30
 */
public interface StrategyTempletService {
	
	StrategyTempletDO get(Long id);
	
	List<StrategyTempletDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StrategyTempletDO strategyTemplet);
	
	int update(StrategyTempletDO strategyTemplet);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	int updateStatus(Long id);
	
	int batchUpdateStatus(Long[] ids);
}
