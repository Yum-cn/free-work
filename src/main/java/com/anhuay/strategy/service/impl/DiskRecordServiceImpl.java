package com.anhuay.strategy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.strategy.dao.DiskRecordDao;
import com.anhuay.strategy.domain.DiskRecordDO;
import com.anhuay.strategy.service.DiskRecordService;



@Service
public class DiskRecordServiceImpl implements DiskRecordService {
	@Autowired
	private DiskRecordDao diskRecordDao;
	
	@Override
	public DiskRecordDO get(Long id){
		return diskRecordDao.get(id);
	}
	
	@Override
	public List<DiskRecordDO> list(Map<String, Object> map){
		return diskRecordDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return diskRecordDao.count(map);
	}
	
	@Override
	public int save(DiskRecordDO diskRecord){
		return diskRecordDao.save(diskRecord);
	}
	
	@Override
	public int update(DiskRecordDO diskRecord){
		return diskRecordDao.update(diskRecord);
	}
	
	@Override
	public int remove(Long id){
		return diskRecordDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return diskRecordDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return diskRecordDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return diskRecordDao.batchUpdateStatus(ids);
    }
}
