package com.anhuay.soft.dao;

import com.anhuay.soft.domain.SoftClassificationDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 软件分类
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-31 21:17:58
 */
@Mapper
public interface SoftClassificationDao {

	SoftClassificationDO get(Long id);
	
	List<SoftClassificationDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SoftClassificationDO softClassification);
	
	int update(SoftClassificationDO softClassification);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	int updateStatus(Long id);
	
	int batchUpdateStatus(Long[] ids);
}
