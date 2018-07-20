package com.anhuay.strategy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.strategy.dao.SoftDistributeDao;
import com.anhuay.strategy.domain.SoftDistributeDO;
import com.anhuay.strategy.service.SoftDistributeService;



@Service
public class SoftDistributeServiceImpl implements SoftDistributeService {
	@Autowired
	private SoftDistributeDao softDistributeDao;
	
	@Override
	public SoftDistributeDO get(Long id){
		return softDistributeDao.get(id);
	}
	
	@Override
	public List<SoftDistributeDO> list(Map<String, Object> map){
		return softDistributeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return softDistributeDao.count(map);
	}
	
	@Override
	public int save(SoftDistributeDO softDistribute){
		return softDistributeDao.save(softDistribute);
	}
	
	@Override
	public int update(SoftDistributeDO softDistribute){
		return softDistributeDao.update(softDistribute);
	}
	
	@Override
	public int remove(Long id){
		return softDistributeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return softDistributeDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return softDistributeDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return softDistributeDao.batchUpdateStatus(ids);
    }
}
