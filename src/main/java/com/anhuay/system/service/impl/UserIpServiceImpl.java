package com.anhuay.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.system.dao.UserIpDao;
import com.anhuay.system.domain.UserIpDO;
import com.anhuay.system.service.UserIpService;



@Service
public class UserIpServiceImpl implements UserIpService {
	@Autowired
	private UserIpDao userIpDao;
	
	@Override
	public UserIpDO get(Long id){
		return userIpDao.get(id);
	}
	
	@Override
	public List<UserIpDO> list(Map<String, Object> map){
		return userIpDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userIpDao.count(map);
	}
	
	@Override
	public int save(UserIpDO userIp){
		return userIpDao.save(userIp);
	}
	
	@Override
	public int update(UserIpDO userIp){
		return userIpDao.update(userIp);
	}
	
	@Override
	public int remove(Long id){
		return userIpDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return userIpDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return userIpDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return userIpDao.batchUpdateStatus(ids);
    }

	@Override
	public int unLockAll() {
		return userIpDao.unLockAll();
	}
}
