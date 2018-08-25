package com.anhuay.terminal.service;

import com.anhuay.terminal.domain.AutoupdateDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-25 13:56:37
 */
public interface AutoupdateService {
	
	AutoupdateDO get(Integer id);
	
	List<AutoupdateDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AutoupdateDO autoupdate);
	
	int update(AutoupdateDO autoupdate);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
    int updateStatus(Integer id);
    
    int batchUpdateStatus(Integer[] ids);
}
