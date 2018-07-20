package com.anhuay.strategy.dao;

import com.anhuay.strategy.domain.SoftDistributeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 软件分发表
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-19 20:25:53
 */
@Mapper
public interface SoftDistributeDao {

	SoftDistributeDO get(Long id);
	
	List<SoftDistributeDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SoftDistributeDO softDistribute);
	
	int update(SoftDistributeDO softDistribute);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	int updateStatus(Long id);
	
	int batchUpdateStatus(Long[] ids);
}
