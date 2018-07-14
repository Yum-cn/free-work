package com.anhuay.strategy.service;

import com.anhuay.strategy.domain.DeviceControlDO;

import java.util.List;
import java.util.Map;

/**
 * 设备控制表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-07 11:11:50
 */
public interface DeviceControlService {
	
	DeviceControlDO get(Long id);
	
	List<DeviceControlDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeviceControlDO deviceControl);
	
	int update(DeviceControlDO deviceControl);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
