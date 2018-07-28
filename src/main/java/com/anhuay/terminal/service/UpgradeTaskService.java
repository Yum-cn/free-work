package com.anhuay.terminal.service;

import com.anhuay.terminal.domain.UpgradeTaskDO;

import java.util.List;
import java.util.Map;

/**
 * 升级任务管理
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-28 15:55:35
 */
public interface UpgradeTaskService {
	
	UpgradeTaskDO get(Long id);
	
	List<UpgradeTaskDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UpgradeTaskDO upgradeTask);
	
	int update(UpgradeTaskDO upgradeTask);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
    int updateStatus(Long id);
    
    int batchUpdateStatus(Long[] ids);
}
