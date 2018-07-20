package com.anhuay.strategy.service;

import com.anhuay.strategy.domain.AssetManagementDO;

import java.util.List;
import java.util.Map;

/**
 * 资产管理表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-19 20:25:52
 */
public interface AssetManagementService {
	
	AssetManagementDO get(Long id);
	
	List<AssetManagementDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AssetManagementDO assetManagement);
	
	int update(AssetManagementDO assetManagement);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
    int updateStatus(Long id);
    
    int batchUpdateStatus(Long[] ids);
}
