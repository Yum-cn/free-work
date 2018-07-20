package com.anhuay.strategy.service;

import com.anhuay.strategy.domain.SoftDistributeDO;

import java.util.List;
import java.util.Map;

/**
 * 软件分发表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-19 20:25:53
 */
public interface SoftDistributeService {
	
	SoftDistributeDO get(Long id);
	
	List<SoftDistributeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SoftDistributeDO softDistribute);
	
	int update(SoftDistributeDO softDistribute);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
    int updateStatus(Long id);
    
    int batchUpdateStatus(Long[] ids);
}
