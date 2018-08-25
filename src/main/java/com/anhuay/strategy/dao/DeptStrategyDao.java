package com.anhuay.strategy.dao;

import com.anhuay.strategy.domain.DeptStrategyDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 部门策略表
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-22 17:07:47
 */
@Mapper
public interface DeptStrategyDao {

	DeptStrategyDO get(Long id);
	
	List<DeptStrategyDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DeptStrategyDO deptStrategy);
	
	int update(DeptStrategyDO deptStrategy);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	int updateStatus(Long id);
	
	int batchUpdateStatus(Long[] ids);

	String selectDeptStrategy(String[] deptIds);
}
