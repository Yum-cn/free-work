package com.anhuay.terminal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.terminal.dao.TerminalFileDao;
import com.anhuay.terminal.domain.TerminalFileDO;
import com.anhuay.terminal.service.TerminalFileService;



@Service
public class TerminalFileServiceImpl implements TerminalFileService {
	@Autowired
	private TerminalFileDao terminalFileDao;
	
	@Override
	public TerminalFileDO get(Long id){
		return terminalFileDao.get(id);
	}
	
	@Override
	public List<TerminalFileDO> list(Map<String, Object> map){
		return terminalFileDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return terminalFileDao.count(map);
	}
	
	@Override
	public int save(TerminalFileDO terminalFile){
		return terminalFileDao.save(terminalFile);
	}
	
	@Override
	public int update(TerminalFileDO terminalFile){
		return terminalFileDao.update(terminalFile);
	}
	
	@Override
	public int remove(Long id){
		return terminalFileDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return terminalFileDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return terminalFileDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return terminalFileDao.batchUpdateStatus(ids);
    }
}
