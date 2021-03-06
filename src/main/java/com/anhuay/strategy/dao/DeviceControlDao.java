package com.anhuay.strategy.dao;

import com.anhuay.strategy.domain.DeviceControlDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 设备控制表
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-19 20:25:53
 */
@Mapper
public interface DeviceControlDao {

	DeviceControlDO get(Long id);
	
	List<DeviceControlDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DeviceControlDO deviceControl);
	
	int update(DeviceControlDO deviceControl);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	int updateStatus(Long id);
	
	int batchUpdateStatus(Long[] ids);
}
