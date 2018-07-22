package com.anhuay.strategy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.strategy.dao.OsStrategyDao;
import com.anhuay.strategy.domain.OsStrategyDO;
import com.anhuay.strategy.service.OsStrategyService;



@Service
public class OsStrategyServiceImpl implements OsStrategyService {
	@Autowired
	private OsStrategyDao osStrategyDao;
	
	@Override
	public OsStrategyDO get(Long id){
		return osStrategyDao.get(id);
	}
	
	@Override
	public List<OsStrategyDO> list(Map<String, Object> map){
		return osStrategyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return osStrategyDao.count(map);
	}
	
	@Override
	public int save(OsStrategyDO osStrategy){
		return osStrategyDao.save(osStrategy);
	}
	
	@Override
	public int update(OsStrategyDO osStrategy){
		return osStrategyDao.update(osStrategy);
	}
	
	@Override
	public int remove(Long id){
		return osStrategyDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return osStrategyDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return osStrategyDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return osStrategyDao.batchUpdateStatus(ids);
    }
}
