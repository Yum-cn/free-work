package com.anhuay.strategy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.strategy.dao.DeptStrategyDao;
import com.anhuay.strategy.domain.DeptStrategyDO;
import com.anhuay.strategy.service.DeptStrategyService;



@Service
public class DeptStrategyServiceImpl implements DeptStrategyService {
	@Autowired
	private DeptStrategyDao deptStrategyDao;
	
	@Override
	public DeptStrategyDO get(Long id){
		return deptStrategyDao.get(id);
	}
	
	@Override
	public List<DeptStrategyDO> list(Map<String, Object> map){
		return deptStrategyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return deptStrategyDao.count(map);
	}
	
	@Override
	public int save(DeptStrategyDO deptStrategy){
		return deptStrategyDao.save(deptStrategy);
	}
	
	@Override
	public int update(DeptStrategyDO deptStrategy){
		return deptStrategyDao.update(deptStrategy);
	}
	
	@Override
	public int remove(Long id){
		return deptStrategyDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return deptStrategyDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return deptStrategyDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return deptStrategyDao.batchUpdateStatus(ids);
    }

	@Override
	public String selectDeptStrategy(String[] deptIds) {
		return deptStrategyDao.selectDeptStrategy(deptIds);
	}
}
