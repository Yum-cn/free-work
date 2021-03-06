package com.anhuay.soft.service;

import com.anhuay.soft.domain.SoftUpgradeTaskDO;
import com.anhuay.terminal.domain.UpgradeTaskDO;

import java.util.List;
import java.util.Map;

/**
 * 软件分发升级任务管理
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-25 11:42:52
 */
public interface SoftUpgradeTaskService {
	
	SoftUpgradeTaskDO get(Long id);
	
	List<SoftUpgradeTaskDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SoftUpgradeTaskDO softUpgradeTask);
	
	int update(SoftUpgradeTaskDO softUpgradeTask);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
    int updateStatus(Long id);
    
    int batchUpdateStatus(Long[] ids);

	int updateTaskStatus(SoftUpgradeTaskDO upgradeTask);
}
