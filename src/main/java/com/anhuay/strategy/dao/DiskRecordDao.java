package com.anhuay.strategy.dao;

import com.anhuay.strategy.domain.DiskRecordDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 光盘刻录表
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-19 20:25:53
 */
@Mapper
public interface DiskRecordDao {

	DiskRecordDO get(Long id);
	
	List<DiskRecordDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DiskRecordDO diskRecord);
	
	int update(DiskRecordDO diskRecord);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	int updateStatus(Long id);
	
	int batchUpdateStatus(Long[] ids);
}
