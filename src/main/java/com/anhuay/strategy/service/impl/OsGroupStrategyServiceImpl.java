package com.anhuay.strategy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.strategy.dao.OsGroupStrategyDao;
import com.anhuay.strategy.domain.OsGroupStrategyDO;
import com.anhuay.strategy.service.OsGroupStrategyService;



@Service
public class OsGroupStrategyServiceImpl implements OsGroupStrategyService {
	@Autowired
	private OsGroupStrategyDao osGroupStrategyDao;
	
	@Override
	public OsGroupStrategyDO get(Long id){
		return osGroupStrategyDao.get(id);
	}
	
	@Override
	public List<OsGroupStrategyDO> list(Map<String, Object> map){
		return osGroupStrategyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return osGroupStrategyDao.count(map);
	}
	
	@Override
	public int save(OsGroupStrategyDO osGroupStrategy){
		return osGroupStrategyDao.save(osGroupStrategy);
	}
	
	@Override
	public int update(OsGroupStrategyDO osGroupStrategy){
		return osGroupStrategyDao.update(osGroupStrategy);
	}
	
	@Override
	public int remove(Long id){
		return osGroupStrategyDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return osGroupStrategyDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return osGroupStrategyDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return osGroupStrategyDao.batchUpdateStatus(ids);
    }
}
