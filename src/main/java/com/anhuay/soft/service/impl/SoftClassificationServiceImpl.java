package com.anhuay.soft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.anhuay.common.dao.DictDao;
import com.anhuay.common.domain.DictDO;
import com.anhuay.common.domain.Tree;
import com.anhuay.common.utils.BuildTree;
import com.anhuay.soft.dao.SoftClassificationDao;
import com.anhuay.soft.domain.SoftClassificationDO;
import com.anhuay.soft.service.SoftClassificationService;
import com.anhuay.system.domain.DeptDO;



@Service
public class SoftClassificationServiceImpl implements SoftClassificationService {
	@Autowired
	private SoftClassificationDao softClassificationDao;
	@Autowired
    private DictDao dictDao;
	
	@Override
	public SoftClassificationDO get(Long id){
		return softClassificationDao.get(id);
	}
	
	@Override
	public List<SoftClassificationDO> list(Map<String, Object> map){
		return softClassificationDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return softClassificationDao.count(map);
	}
	
	@Override
	public int save(SoftClassificationDO softClassification){
		return softClassificationDao.save(softClassification);
	}
	
	@Override
	public int update(SoftClassificationDO softClassification){
		return softClassificationDao.update(softClassification);
	}
	
	@Override
	public int remove(Long id){
		return softClassificationDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return softClassificationDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return softClassificationDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return softClassificationDao.batchUpdateStatus(ids);
    }

	@Override
	public Tree<DictDO> getTree(Map<String, Object> map) {
		List<Tree<DictDO>> trees = new ArrayList<Tree<DictDO>>();
		List<DictDO> dicts = dictDao.list(map);
		for (DictDO dict : dicts) {
			Tree<DictDO> tree = new Tree<DictDO>();
			tree.setId(String.valueOf(dict.getId()));
			tree.setParentId(null);
			tree.setText(dict.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<DictDO> t = BuildTree.build(trees);
		return t;
	}
    
    
}
