package com.anhuay.common.service;

import com.anhuay.common.domain.TaskValueDO;

import java.util.List;
import java.util.Map;

/**
 * 同步数据表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-12 12:09:47
 */
public interface TaskValueService {
	
	TaskValueDO get(Integer id);
	
	List<TaskValueDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TaskValueDO taskValue);
	
	int update(TaskValueDO taskValue);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
    int updateStatus(Integer id);
    
    int batchUpdateStatus(Integer[] ids);
}
