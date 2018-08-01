package com.anhuay.soft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.soft.dao.SoftClassificationDao;
import com.anhuay.soft.domain.SoftClassificationDO;
import com.anhuay.soft.service.SoftClassificationService;



@Service
public class SoftClassificationServiceImpl implements SoftClassificationService {
	@Autowired
	private SoftClassificationDao softClassificationDao;
	
	@Override
	public SoftClassificationDO get(Long id){
		return softClassificationDao.get(id);
	}
	
	@Override
	public List<SoftClassificationDO> list(Map<String, Object> map){
		return softClassificationDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return softClassificationDao.count(map);
	}
	
	@Override
	public int save(SoftClassificationDO softClassification){
		return softClassificationDao.save(softClassification);
	}
	
	@Override
	public int update(SoftClassificationDO softClassification){
		return softClassificationDao.update(softClassification);
	}
	
	@Override
	public int remove(Long id){
		return softClassificationDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return softClassificationDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return softClassificationDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return softClassificationDao.batchUpdateStatus(ids);
    }
}
