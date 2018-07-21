package com.anhuay.strategy.service;

import com.anhuay.strategy.domain.IrregularConnectionDO;

import java.util.List;
import java.util.Map;

/**
 * 违规外联表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-21 17:18:00
 */
public interface IrregularConnectionService {
	
	IrregularConnectionDO get(Long id);
	
	List<IrregularConnectionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(IrregularConnectionDO irregularConnection);
	
	int update(IrregularConnectionDO irregularConnection);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
    int updateStatus(Long id);
    
    int batchUpdateStatus(Long[] ids);
}
