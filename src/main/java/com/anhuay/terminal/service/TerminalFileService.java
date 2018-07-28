package com.anhuay.terminal.service;

import com.anhuay.terminal.domain.TerminalFileDO;

import java.util.List;
import java.util.Map;

/**
 * 终端程序上传
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-28 15:55:35
 */
public interface TerminalFileService {
	
	TerminalFileDO get(Long id);
	
	List<TerminalFileDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TerminalFileDO terminalFile);
	
	int update(TerminalFileDO terminalFile);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
    int updateStatus(Long id);
    
    int batchUpdateStatus(Long[] ids);
}
