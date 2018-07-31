package com.anhuay.terminal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.terminal.dao.UpgradeTaskDao;
import com.anhuay.terminal.domain.UpgradeTaskDO;
import com.anhuay.terminal.service.UpgradeTaskService;



@Service
public class UpgradeTaskServiceImpl implements UpgradeTaskService {
	@Autowired
	private UpgradeTaskDao upgradeTaskDao;
	
	@Override
	public UpgradeTaskDO get(Long id){
		return upgradeTaskDao.get(id);
	}
	
	@Override
	public List<UpgradeTaskDO> list(Map<String, Object> map){
		return upgradeTaskDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return upgradeTaskDao.count(map);
	}
	
	@Override
	public int save(UpgradeTaskDO upgradeTask){
		return upgradeTaskDao.save(upgradeTask);
	}
	
	@Override
	public int update(UpgradeTaskDO upgradeTask){
		return upgradeTaskDao.update(upgradeTask);
	}
	
	@Override
	public int remove(Long id){
		return upgradeTaskDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return upgradeTaskDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return upgradeTaskDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return upgradeTaskDao.batchUpdateStatus(ids);
    }
    
    @Override
    public int updateTaskStatus(UpgradeTaskDO upgradeTask){
    	return upgradeTaskDao.updateTaskStatus(upgradeTask);
    }
}
