package com.anhuay.terminal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.terminal.dao.AutoupdateDao;
import com.anhuay.terminal.domain.AutoupdateDO;
import com.anhuay.terminal.service.AutoupdateService;



@Service
public class AutoupdateServiceImpl implements AutoupdateService {
	@Autowired
	private AutoupdateDao autoupdateDao;
	
	@Override
	public AutoupdateDO get(Integer id){
		return autoupdateDao.get(id);
	}
	
	@Override
	public List<AutoupdateDO> list(Map<String, Object> map){
		return autoupdateDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return autoupdateDao.count(map);
	}
	
	@Override
	public int save(AutoupdateDO autoupdate){
		return autoupdateDao.save(autoupdate);
	}
	
	@Override
	public int update(AutoupdateDO autoupdate){
		return autoupdateDao.update(autoupdate);
	}
	
	@Override
	public int remove(Integer id){
		return autoupdateDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return autoupdateDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Integer id){
        return autoupdateDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Integer[] ids){
        return autoupdateDao.batchUpdateStatus(ids);
    }
}
