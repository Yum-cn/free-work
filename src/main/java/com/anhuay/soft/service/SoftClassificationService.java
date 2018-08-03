package com.anhuay.soft.service;

import com.anhuay.common.domain.DictDO;
import com.anhuay.common.domain.Tree;
import com.anhuay.soft.domain.SoftClassificationDO;

import java.util.List;
import java.util.Map;

/**
 * 软件分类
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-31 21:17:58
 */
public interface SoftClassificationService {
	
	SoftClassificationDO get(Long id);
	
	List<SoftClassificationDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SoftClassificationDO softClassification);
	
	int update(SoftClassificationDO softClassification);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
    int updateStatus(Long id);
    
    int batchUpdateStatus(Long[] ids);

	Tree<DictDO> getTree(Map<String, Object> map);
}
