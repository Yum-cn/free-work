package com.anhuay.soft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.soft.dao.SoftUpgradeTaskDao;
import com.anhuay.soft.domain.SoftUpgradeTaskDO;
import com.anhuay.soft.service.SoftUpgradeTaskService;
import com.anhuay.terminal.domain.UpgradeTaskDO;



@Service
public class SoftUpgradeTaskServiceImpl implements SoftUpgradeTaskService {
	@Autowired
	private SoftUpgradeTaskDao softUpgradeTaskDao;
	
	@Override
	public SoftUpgradeTaskDO get(Long id){
		return softUpgradeTaskDao.get(id);
	}
	
	@Override
	public List<SoftUpgradeTaskDO> list(Map<String, Object> map){
		return softUpgradeTaskDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return softUpgradeTaskDao.count(map);
	}
	
	@Override
	public int save(SoftUpgradeTaskDO softUpgradeTask){
		return softUpgradeTaskDao.save(softUpgradeTask);
	}
	
	@Override
	public int update(SoftUpgradeTaskDO softUpgradeTask){
		return softUpgradeTaskDao.update(softUpgradeTask);
	}
	
	@Override
	public int remove(Long id){
		return softUpgradeTaskDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return softUpgradeTaskDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return softUpgradeTaskDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return softUpgradeTaskDao.batchUpdateStatus(ids);
    }

	@Override
	public int updateTaskStatus(SoftUpgradeTaskDO upgradeTask) {
		return softUpgradeTaskDao.updateTaskStatus(upgradeTask);
		
	}
}
