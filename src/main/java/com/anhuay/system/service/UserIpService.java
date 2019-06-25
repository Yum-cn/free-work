package com.anhuay.system.service;

import com.anhuay.system.domain.UserIpDO;

import java.util.List;
import java.util.Map;

/**
 * 用户访问IP记录
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2019-06-25 12:13:24
 */
public interface UserIpService {
	
	UserIpDO get(Long id);
	
	List<UserIpDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserIpDO userIp);
	
	int update(UserIpDO userIp);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
    int updateStatus(Long id);
    
    int batchUpdateStatus(Long[] ids);
}
