package com.anhuay.terminal.dao;

import com.anhuay.terminal.domain.TerminalFileDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 终端程序上传
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-28 15:55:35
 */
@Mapper
public interface TerminalFileDao {

	TerminalFileDO get(Long id);
	
	List<TerminalFileDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TerminalFileDO terminalFile);
	
	int update(TerminalFileDO terminalFile);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	int updateStatus(Long id);
	
	int batchUpdateStatus(Long[] ids);
}
