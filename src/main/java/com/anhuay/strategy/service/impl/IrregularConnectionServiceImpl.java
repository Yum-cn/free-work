package com.anhuay.strategy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.strategy.dao.IrregularConnectionDao;
import com.anhuay.strategy.domain.IrregularConnectionDO;
import com.anhuay.strategy.service.IrregularConnectionService;



@Service
public class IrregularConnectionServiceImpl implements IrregularConnectionService {
	@Autowired
	private IrregularConnectionDao irregularConnectionDao;
	
	@Override
	public IrregularConnectionDO get(Long id){
		return irregularConnectionDao.get(id);
	}
	
	@Override
	public List<IrregularConnectionDO> list(Map<String, Object> map){
		return irregularConnectionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return irregularConnectionDao.count(map);
	}
	
	@Override
	public int save(IrregularConnectionDO irregularConnection){
		return irregularConnectionDao.save(irregularConnection);
	}
	
	@Override
	public int update(IrregularConnectionDO irregularConnection){
		return irregularConnectionDao.update(irregularConnection);
	}
	
	@Override
	public int remove(Long id){
		return irregularConnectionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return irregularConnectionDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return irregularConnectionDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return irregularConnectionDao.batchUpdateStatus(ids);
    }
}
