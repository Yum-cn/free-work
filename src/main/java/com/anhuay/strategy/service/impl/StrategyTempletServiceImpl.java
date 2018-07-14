package com.anhuay.strategy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.strategy.dao.StrategyTempletDao;
import com.anhuay.strategy.domain.StrategyTempletDO;
import com.anhuay.strategy.service.StrategyTempletService;



@Service
public class StrategyTempletServiceImpl implements StrategyTempletService {
	@Autowired
	private StrategyTempletDao strategyTempletDao;
	
	@Override
	public StrategyTempletDO get(Long id){
		return strategyTempletDao.get(id);
	}
	
	@Override
	public List<StrategyTempletDO> list(Map<String, Object> map){
		return strategyTempletDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return strategyTempletDao.count(map);
	}
	
	@Override
	public int save(StrategyTempletDO strategyTemplet){
		return strategyTempletDao.save(strategyTemplet);
	}
	
	@Override
	public int update(StrategyTempletDO strategyTemplet){
		return strategyTempletDao.update(strategyTemplet);
	}
	
	@Override
	public int remove(Long id){
		return strategyTempletDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return strategyTempletDao.batchRemove(ids);
	}
	
	@Override
	public int updateStatus(Long id){
		return strategyTempletDao.updateStatus(id);
	}
	
	@Override
	public int batchUpdateStatus(Long[] ids){
		return strategyTempletDao.batchUpdateStatus(ids);
	}
	
}
