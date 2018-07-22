package com.anhuay.strategy.dao;

import com.anhuay.strategy.domain.OsGroupStrategyDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 主机组策略表
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-22 17:07:47
 */
@Mapper
public interface OsGroupStrategyDao {

	OsGroupStrategyDO get(Long id);
	
	List<OsGroupStrategyDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OsGroupStrategyDO osGroupStrategy);
	
	int update(OsGroupStrategyDO osGroupStrategy);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	int updateStatus(Long id);
	
	int batchUpdateStatus(Long[] ids);
}
