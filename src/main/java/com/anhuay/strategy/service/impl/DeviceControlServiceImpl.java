package com.anhuay.strategy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.strategy.dao.DeviceControlDao;
import com.anhuay.strategy.domain.DeviceControlDO;
import com.anhuay.strategy.service.DeviceControlService;



@Service
public class DeviceControlServiceImpl implements DeviceControlService {
	@Autowired
	private DeviceControlDao deviceControlDao;
	
	@Override
	public DeviceControlDO get(Long id){
		return deviceControlDao.get(id);
	}
	
	@Override
	public List<DeviceControlDO> list(Map<String, Object> map){
		return deviceControlDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return deviceControlDao.count(map);
	}
	
	@Override
	public int save(DeviceControlDO deviceControl){
		return deviceControlDao.save(deviceControl);
	}
	
	@Override
	public int update(DeviceControlDO deviceControl){
		return deviceControlDao.update(deviceControl);
	}
	
	@Override
	public int remove(Long id){
		return deviceControlDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return deviceControlDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return deviceControlDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return deviceControlDao.batchUpdateStatus(ids);
    }
}
