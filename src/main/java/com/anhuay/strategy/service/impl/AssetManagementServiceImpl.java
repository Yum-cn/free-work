package com.anhuay.strategy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.strategy.dao.AssetManagementDao;
import com.anhuay.strategy.domain.AssetManagementDO;
import com.anhuay.strategy.service.AssetManagementService;



@Service
public class AssetManagementServiceImpl implements AssetManagementService {
	@Autowired
	private AssetManagementDao assetManagementDao;
	
	@Override
	public AssetManagementDO get(Long id){
		return assetManagementDao.get(id);
	}
	
	@Override
	public List<AssetManagementDO> list(Map<String, Object> map){
		return assetManagementDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return assetManagementDao.count(map);
	}
	
	@Override
	public int save(AssetManagementDO assetManagement){
		return assetManagementDao.save(assetManagement);
	}
	
	@Override
	public int update(AssetManagementDO assetManagement){
		return assetManagementDao.update(assetManagement);
	}
	
	@Override
	public int remove(Long id){
		return assetManagementDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return assetManagementDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return assetManagementDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return assetManagementDao.batchUpdateStatus(ids);
    }
}
